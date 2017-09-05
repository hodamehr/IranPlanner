package com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import entity.ResultItinerary;
import entity.ResultLodging;
import entity.ResultLodgingList;
import tools.Util;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class ReservationListActivity extends StandardActivity implements DataTransferInterface, ReservationContract.View {
    private ReseveDateListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    Date startOfTravel;
    ResultItinerary itineraryData;
    int durationTravel = 1;
    ProgressDialog progressDialog;
    DaggerReservationComponent.Builder builder;
    @Inject
    ReservationPresenter reservationPresenter;
    protected Toolbar toolbar;
    ImageView toolbarBack, toolbarToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        Log.e("Aw cmn", "this is the activity im talkin bout");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        itineraryData = (ResultItinerary) extras.getSerializable("itineraryData");
        startOfTravel = (Date) extras.getSerializable("startOfTravel");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbarBack = (ImageView) findViewById(R.id.toolbarBack);
//        toolbarToggle = (ImageView) findViewById(R.id.toolbarToggle);

        setupToolbar();
        adapter = new ReseveDateListAdapter(ReservationListActivity.this, this, itineraryData, getApplicationContext(), R.layout.fragment_itinerary_item, startOfTravel);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//--------
        builder = DaggerReservationComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .reservationModule(new ReservationModule(this));
        builder.build().inject(this);

        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView reservationBtn = (ImageView) view.findViewById(R.id.ReservationBtn);
                TextView txtDurationLodgingCity = (TextView) view.findViewById(R.id.txtDurationLodgingCity);
                durationTravel = Integer.valueOf((String) txtDurationLodgingCity.getText());
                reservationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("reserve", "click");
                        reservationPresenter.getLodgingList("list", itineraryData.getItineraryLodgingCity().get(position + 1).getCityId(), Util.getTokenFromSharedPreferences(getApplicationContext()), "20", "0", Util.getAndroidIdFromSharedPreferences(getApplicationContext()), "");
                    }
                });

            }
        }));
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ddd", "dddddddddddddddddddddddddd");
            }
        });

    }

    void setupToolbar() {
        ((StandardActivity) this).setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        ((StandardActivity) this).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbarToggle.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_list;
    }

    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

    @Override
    public void showLodgingList(ResultLodgingList resultLodgingList) {
        List<ResultLodging> resultLodgings = resultLodgingList.getResultLodging();
        Intent intent = new Intent(getApplicationContext(), ReservationHotelListActivity.class);
        intent.putExtra("resultLodgings", (Serializable) resultLodgings);
        intent.putExtra("startOfTravel", startOfTravel);
        intent.putExtra("durationTravel", durationTravel);
        intent.putExtra("todayDate", resultLodgingList.getStatistics().getDateNow());
        startActivity(intent);
    }

    @Override
    public void showError(String message) {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (message.contains("Unable to resolve host ") || message.contains("Software caused connection abort")) {
            Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        }
        if (message.contains("HTTP 400 BAD REQUEST")) {
            Toast.makeText(getApplicationContext(), "مرکز اقامتی وجود ندارد", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }
}
