package com.iranplanner.tourism.iranplanner.ui.fragment;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.ShowDetailItineraryAdapter;
import com.iranplanner.tourism.iranplanner.ui.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.adapter.AttractionsListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.activity.attractionDetailActivity;

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
import server.Config;
import server.getJsonInterface;
import tools.Util;


public class ShowAttractionListFragment extends StandardFragment implements DataTransferInterface {
    private Context context;
    private RecyclerView attractionRecyclerView;
    private AttractionsListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItineraryAttraction> itineraryActionList;
    ImageView arrowRight, arrowLeft;
    int dayNumber, allDays;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_attraction_list, container, false);
        Bundle bundle = getArguments();
        attractionRecyclerView = (RecyclerView) view.findViewById(R.id.attractionListRecyclerView);
        arrowLeft = (ImageView) view.findViewById(R.id.arrowLeft);
        arrowRight = (ImageView) view.findViewById(R.id.arrowRight);
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
                Button navigateBtn = (Button) view.findViewById(R.id.navigateBtn);
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
                        ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                        Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                        intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                        startActivity(intent);
                    }
                });
                Button moreInfoHolder = (Button) view.findViewById(R.id.moreInfoHolder);
                moreInfoHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                        Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                        intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                        startActivity(intent);
                    }
                });

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



    private void buildAlertMessageNoGps(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("مسیریاب شما فعال نیست، آیا تمایل به روشن کردن آن دارید؟")
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


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
