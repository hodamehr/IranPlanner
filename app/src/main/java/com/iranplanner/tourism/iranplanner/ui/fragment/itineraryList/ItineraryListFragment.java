package com.iranplanner.tourism.iranplanner.ui.fragment.itineraryList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coinpany.core.android.widget.loading.RotateLoading;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;

import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch.DaggerMainScreenComponent;
import com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch.MainSearchnModule;
import com.iranplanner.tourism.iranplanner.ui.activity.moreItemItinerary.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch.MainSearchPresenter;
import com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch.MainSearchContract;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import entity.ResultItinerary;
import entity.ResultItineraryList;

import tools.Util;


/**
 * Created by h.vahidimehr on 10/01/2017.
 */

public class ItineraryListFragment extends StandardFragment implements MainSearchContract.View,DataTransferInterface {

    @Inject
    MainSearchPresenter mainPresenter;
    private ProgressDialog progressDialog;

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

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private TextView tvSearch ;

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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View view = inflater.inflate(R.layout.fragment_itinerary_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        waitingLoading = (ProgressBar) view.findViewById(R.id.waitingLoading);
        checkFromWhereGetBundle();
        DaggerMainScreenComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainSearchnModule(new MainSearchnModule(this))
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
                            mainPresenter.loadItineraryFromCity("list", "fa", data.get(0).getItineraryFromCityId().toString(), "20", nextOffset, endCity, Util.getTokenFromSharedPreferences(getContext()), Util.getAndroidIdFromSharedPreferences(getContext()));
                            waitingLoading.setVisibility(View.VISIBLE);
                            loading = false;
                        } else if (fromProvince) {
                            mainPresenter.loadItineraryFromProvince("searchprovince", provinceId, nextOffset, Util.getTokenFromSharedPreferences(getContext()), Util.getAndroidIdFromSharedPreferences(getContext()));
                            waitingLoading.setVisibility(View.VISIBLE);

                        } else if (fromAttraction) {

                            mainPresenter.loadItineraryFromAttraction("searchattractioncity", "fa", cityFrom, "10", nextOffset, attractionId, Util.getTokenFromSharedPreferences(getContext()), Util.getAndroidIdFromSharedPreferences(getContext()));
                            waitingLoading.setVisibility(View.VISIBLE);
                        } else if (fromCity) {
//                            getItineraryCity(cityFrom, nextOffset, cityFrom);
//                            waitingLoading.setVisibility(View.VISIBLE);
                            mainPresenter.loadItineraryFromCity("list", "fa", data.get(0).getItineraryFromCityId().toString(), "20", nextOffset, endCity, Util.getTokenFromSharedPreferences(getContext()), Util.getAndroidIdFromSharedPreferences(getContext()));
                            waitingLoading.setVisibility(View.VISIBLE);
                            loading = false;
                        }

                    }
                }
            }

        });

        tvSearch = (TextView) view.findViewById(R.id.txtSearch);

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.e("heytag", "clicked");
            }
        });

        return view;
    }



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

        loading = false;

        if (message.contains("Unable to resolve host ") || message.contains("Software caused connection abort")) {
            Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        }
        if(waitingLoading.isEnabled()){
            waitingLoading.setVisibility(View.INVISIBLE);
        }

//        if (message.contains("HTTP 400 BAD REQUEST")) {
//            Toast.makeText(getContext(), "اتمام برنامه سفر", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void showComplete() {
//        Toast.makeText(getContext(), "complete", Toast.LENGTH_LONG).show();
        loading = true;
        waitingLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressDialog = Util.showProgressDialog(getContext(), "لطفا منتظر بمانید", getActivity());
    }

    @Override
    public void dismissProgress() {
        Util.dismissProgress(progressDialog);
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
//            getIntrestResponce(img, position, duration, cityid, name);
            Intent intent = new Intent(getActivity(), MoreItemItineraryActivity.class);
            intent.putExtra("itineraryData", (Serializable) data.get(position));
            intent.putExtra("duration", duration);
            startActivity(intent);

        }
    }
    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

}
