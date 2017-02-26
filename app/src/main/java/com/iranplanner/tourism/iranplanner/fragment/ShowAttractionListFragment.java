package com.iranplanner.tourism.iranplanner.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.adapter.AttractionsListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.activity.attractionDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.ItineraryLodgingCity;
import entity.ResultItineraryAttraction;
import entity.ResultWidget;
import entity.ResultWidgetFull;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.Util;


public class ShowAttractionListFragment extends StandardFragment implements /*Callback<ResultItineraryList>,*/ DataTransferInterface {
    private Context context;
    private RecyclerView attractionRecyclerView;
    private AttractionsListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItineraryAttraction> itineraryActionList;
    int dayNumber, allDays;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_attraction_list, container, false);
        Bundle bundle = getArguments();
        attractionRecyclerView = (RecyclerView) view.findViewById(R.id.attractionListRecyclerView);
        TextView textDayNumber = (TextView) view.findViewById(R.id.textDayNumber);
        itineraryActionList = (List<ResultItineraryAttraction>) bundle.getSerializable("itineraryActionList");
        dayNumber = bundle.getInt("dayNumber");
        allDays = bundle.getInt("allDays");
        textDayNumber.setText(" روز " + Util.persianNumbers(String.valueOf(dayNumber)) + " از " + Util.persianNumbers(String.valueOf(allDays)));
        attractionRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        attractionRecyclerView.setLayoutManager(layoutManager);
        adapter = new AttractionsListAdapter(getActivity(), this, itineraryActionList, getContext(), R.layout.fragment_show_attraction_list_detail);
        attractionRecyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        attractionRecyclerView.setLayoutManager(mLayoutManager);
        attractionRecyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                LinearLayout navigateBtn = (LinearLayout) view.findViewById(R.id.navigateBtn);
                navigateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final LocationManager manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

                        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            // Call your Alert message
                            buildAlertMessageNoGps(position);
                        } else {
                            openMapFull(position);
                        }


                    }
                });

                RelativeLayout imageTextAttractionHolder = (RelativeLayout) view.findViewById(R.id.imageTextAttractionHolder);
                imageTextAttractionHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getIntrestResponce(position,itineraryActionList.get(position).getAttractionId(),Util.getUseRIdFromShareprefrence(getContext()));
                    }
                });
                LinearLayout moreInfoHolder = (LinearLayout) view.findViewById(R.id.moreInfoHolder);
                moreInfoHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getIntrestResponce(position,itineraryActionList.get(position).getAttractionId(),Util.getUseRIdFromShareprefrence(getContext()));
                    }
                });
//                ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
//                Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
//                intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
//                startActivity(intent);
//                DetailAttractionFragment fragment = new DetailAttractionFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ResultItineraryAttraction", ResultItineraryAttraction);

//                fragment.setArguments(bundle);
//                final FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.Holder, fragment, "NewFragmentTag");
//                ft.commit();
            }
        }));


        attractionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        Log.v("...", "Last Item Wow !");

                    }
                }
            }
//            }
        });
        return view;
    }

    private OkHttpClient setHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public void getIntrestResponce(final int position, String attractionId, String uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);

//        api.parsdid.com/iranplanner/app/api-data.php?action=nodeuser&id=29839&uid=792147600796866&ntype=attraction
        Call<ResultWidgetFull> callc = getJsonInterface.getWidgetResult("nodeuser", attractionId, uid, "attraction");
        callc.enqueue(new Callback<ResultWidgetFull>() {
            @Override
            public void onResponse(Call<ResultWidgetFull> call, Response<ResultWidgetFull> response) {
                Log.e("result of intresting", "true");

                if (response.body() != null) {
                    ResultWidgetFull res = response.body();
                    List<ResultWidget> resultWidget = res.getResultWidget();
                    Log.e("string", "item clicked");
                    ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                    Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                    intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                    intent.putExtra("resultWidget", (Serializable) resultWidget);
                    startActivity(intent);

                } else {
                    ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                    Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                    intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResultWidgetFull> call, Throwable t) {
                Log.e("result of intresting", "false");
                ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                startActivity(intent);
            }
        });
    }

    private void buildAlertMessageNoGps(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("GPS شما فعال نیست. آیا تمایل به روشن کردن آن دارید")
                .setCancelable(false)
//                // TODO: 06/02/2017  below
                // toye startActivityForResult be jaye code request posotion ro ferestam . ye joor kalak .
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), position);
                    }
                })
                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("map is ckicked", "true");
        openMapFull(requestCode);
//        if (resultCode == 1) {
//            switch (requestCode) {
//                case 1:
//                    Log.e("resultact","ok");
//                    break;
//            }
//        }
    }

    private void openMapFull(int position) {
        Intent intent = new Intent(getContext(), MapFullActivity.class);
        ItineraryLodgingCity i = new ItineraryLodgingCity();
        i.setCityPositionLat(itineraryActionList.get(position).getAttractionPositionLat());
        i.setCityPositionLon(itineraryActionList.get(position).getAttractionPositionOn());
        List<ItineraryLodgingCity> lodgingCities = new ArrayList<ItineraryLodgingCity>();
        lodgingCities.add(i);
        intent.putExtra("lodgingCities", (Serializable) lodgingCities);
        startActivity(intent);
    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    //    public void getItinerary(String cityId, String offset) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.parsdid.com/iranplanner/app/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
//        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset);
//        call.enqueue(this);
//    }
//
//    @Override
//    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
//        if (response.body() != null) {
//            loading = false;
//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> jj = jsonResponse.getResultItinerary();
//            data.addAll(jj);
//            adapter.notifyDataSetChanged();
//            waiting.setVisibility(View.INVISIBLE);
//        } else {
//            Log.e("Responce body", "null");
//            waiting.setVisibility(View.VISIBLE);
//        }
//
////        rotateloading.stop();
//    }
//
//    @Override
//    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
//        Log.e(" error from server", "error");
//    }
//
//
    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
