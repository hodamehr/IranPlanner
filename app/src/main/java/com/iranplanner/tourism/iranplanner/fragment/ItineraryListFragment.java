package com.iranplanner.tourism.iranplanner.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coinpany.core.android.widget.loading.RotateLoading;
import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.ItineraryListAdapter;
import com.iranplanner.tourism.iranplanner.mainscreen.DaggerMainScreenComponent;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenContract;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenModule;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenPresenter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import entity.ResultItinerary;
import entity.ResultItineraryList;
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
import tools.utilMvp.App;


/**
 * Created by h.vahidimehr on 10/01/2017.
 */

public class ItineraryListFragment extends StandardFragment implements MainScreenContract.View, Callback<ResultItineraryList>, DataTransferInterface {

    @Inject
    MainScreenPresenter mainPresenter;

    private Context context;
    private RecyclerView recyclerView;
    private ItineraryListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItinerary> data = null;
    boolean flag;
    RotateLoading rotateloading;
    TextView waiting;
    private boolean fromCityToCity;
    private boolean fromProvince;
    private boolean fromCity;
    private boolean fromAttraction;
    String provinceId;
    String nextOffset, endCity;
    String attractionId, cityFrom;

    private ProgressBar waitingLoading;

    private void checkFromWhereGetBundle() {
        Bundle bundle = getArguments();

        data = (List<ResultItinerary>) bundle.getSerializable("resuliItineraryList");
        String fromWhere = bundle.getString("fromWhere");
        provinceId = bundle.getString("provinceId");
        nextOffset = bundle.getString("nextOffset");
        endCity = bundle.getString("endCity");
        attractionId = bundle.getString("attractionId");
        cityFrom = bundle.getString("cityFrom");
        if (fromWhere.equals("fromProvince")) {
            fromCityToCity = false;
            fromProvince = true;
            fromCity = false;
            fromAttraction = false;
        } else if (fromWhere.equals("fromCityToCity")) {
            fromCityToCity = true;
            fromProvince = false;
            fromCity = false;
            fromAttraction = false;

        } else if (fromWhere.equals("fromCity")) {
            fromCityToCity = false;
            fromProvince = false;
            fromCity = true;
            fromAttraction = false;
        } else if (fromWhere.equals("fromAttraction")) {
            fromCityToCity = false;
            fromProvince = false;
            fromCity = false;
            fromAttraction = true;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itinerary_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        waitingLoading = (ProgressBar) view.findViewById(R.id.waitingLoading);
        checkFromWhereGetBundle();
        DaggerMainScreenComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().injectItineraryListFragment(this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItineraryListAdapter(getActivity(), this, data, getContext(), R.layout.fragment_itinerary_item);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imgItineraryListMore);
                TextView textView = (TextView) view.findViewById(R.id.itinerary_duration);
                textView.getText();
                MyThread m = new MyThread(imageView, position, textView.getText().toString());
                m.run();
            }
        }));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (fromCityToCity && loading) {
//                            getItineraryCityToCity(data.get(0).getItineraryFromCityId().toString(), nextOffset, endCity);
//                            String offset = "0";
//                            "list", "fa", cityId, "", offset, toCity
                            mainPresenter.loadItineraryFromCity("list", "fa", data.get(0).getItineraryFromCityId().toString(), "20", nextOffset, endCity);
                            waitingLoading.setVisibility(View.VISIBLE);
                            loading = false;
                        } else if (fromProvince) {
                            mainPresenter.loadItineraryFromProvince("searchprovince", provinceId, nextOffset);
                            waitingLoading.setVisibility(View.VISIBLE);

                        } else if (fromAttraction) {
                            getItineraryAttraction(cityFrom, nextOffset, attractionId);
                            waitingLoading.setVisibility(View.VISIBLE);

                        } else if (fromCity) {
                            getItineraryCity(cityFrom, nextOffset, cityFrom);
                            waitingLoading.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }

        });

        return view;
    }

    public void getIntrestResponce(ImageView img, final int position, final String duration, String cityid, String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        //api-data.php?action=nodeuser&id=28439&uid=323148788221963&ntype=itinerary
//    28439&uid=792147600796866

        Call<ResultWidgetFull> callc = getJsonInterface.getWidgetResult("nodeuser", cityid, name, "itinerary");
        callc.enqueue(new Callback<ResultWidgetFull>() {
            @Override
            public void onResponse(Call<ResultWidgetFull> call, Response<ResultWidgetFull> response) {
                Log.e("result of intresting", "true");

                if (response.body() != null) {
                    ResultWidgetFull res = response.body();
                    List<ResultWidget> resultWidget = res.getResultWidget();
                    Log.e("string", "item clicked");
                    Intent intent = new Intent(getActivity(), MoreItemItineraryActivity.class);
                    intent.putExtra("itineraryData", (Serializable) data.get(position));
                    intent.putExtra("duration", duration);
                    intent.putExtra("resultUserLogin", (Serializable) resultWidget);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), MoreItemItineraryActivity.class);
                    intent.putExtra("itineraryData", (Serializable) data.get(position));
                    intent.putExtra("duration", duration);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResultWidgetFull> call, Throwable t) {
                Log.e("result of intresting", "false");
                Intent intent = new Intent(getActivity(), MoreItemItineraryActivity.class);
                intent.putExtra("itineraryData", (Serializable) data.get(position));
                intent.putExtra("duration", duration);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void showItineraries(ResultItineraryList resultItineraryList) {
//        loading = true;
//        List<ResultItinerary> jj = resultItineraryList.getResultItinerary();
//        if (!nextOffset.equals(resultItineraryList.getStatistics().getOffsetNext().toString())) {
//            data.addAll(jj);
//            adapter.notifyDataSetChanged();
//            waitingLoading.setVisibility(View.INVISIBLE);
//            nextOffset = resultItineraryList.getStatistics().getOffsetNext().toString();
//
//        }
//
//    }

    @Override
    public void showItineraries(ResultItineraryList resultItineraryList, String typeOfSearch) {
        loading = true;
        List<ResultItinerary> jj = resultItineraryList.getResultItinerary();
        if (!nextOffset.equals(resultItineraryList.getStatistics().getOffsetNext().toString())) {
            data.addAll(jj);
            adapter.notifyDataSetChanged();
            waitingLoading.setVisibility(View.INVISIBLE);
            nextOffset = resultItineraryList.getStatistics().getOffsetNext().toString();

        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        loading = true;
    }

    @Override
    public void showComplete() {
        Toast.makeText(getContext(), "complete", Toast.LENGTH_LONG).show();
        loading = true;
    }

    class MyThread extends Thread {

        private ImageView img;
        int position;
        String duration;

        public MyThread(ImageView img, int position, String duration) {
            this.img = img;
            this.position = position;
            this.duration = duration;
        }

        @Override
        public void run() {
            String cityid = data.get(position).getItineraryId();
            String name = Util.getUseRIdFromShareprefrence(getContext());
            getIntrestResponce(img, position, duration, cityid, name);

        }
    }


    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private OkHttpClient setHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public void getItineraryCityToCity(String cityId, String offset, String toCity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(setHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset, toCity);
        call.enqueue(this);

//        private MainView mainView;
//        private FindItemsInteractor findItemsInteractor;
//
//        public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
//            this.mainView = mainView;
//        this.findItemsInteractor = findItemsInteractor;
//    }


    }

    public void getItineraryProvince(String offset, String provinceId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(setHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysFromProvince("searchprovince", provinceId, offset);
        call.enqueue(this);
    }

    public void getItineraryCity(String cityId, String offset, String toCity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(setHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset, toCity);
        call.enqueue(this);
    }

    public void getItineraryAttraction(String cityId, String offset, String attractionId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(setHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysAttraction("searchattractioncity", "fa", cityId, "", offset, attractionId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
        if (response.body() != null) {
            loading = false;
            ResultItineraryList jsonResponse = response.body();
            List<ResultItinerary> jj = jsonResponse.getResultItinerary();
            if (!nextOffset.equals(response.body().getStatistics().getOffsetNext().toString())) {
                data.addAll(jj);
                adapter.notifyDataSetChanged();
                waitingLoading.setVisibility(View.INVISIBLE);
                nextOffset = response.body().getStatistics().getOffsetNext().toString();
            }

        } else {
            Log.e("Responce body", "null");
            waitingLoading.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Log.e(" error from server", "error");
        Toast.makeText(getContext(), "عدم ارتباط با اینترنت", Toast.LENGTH_LONG).show();
    }


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

}
