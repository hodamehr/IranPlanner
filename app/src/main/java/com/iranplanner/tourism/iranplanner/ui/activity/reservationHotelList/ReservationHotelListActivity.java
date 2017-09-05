package com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelDetails.ReservationHotelDetailActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultLodging;
import entity.ResultLodgingHotel;
import entity.ResultLodgingList;
import tools.CustomDialogDate;
import tools.CustomDialogNumberPicker;
import tools.Util;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class ReservationHotelListActivity extends StandardActivity implements DataTransferInterface, ReservationHotelListContract.View, View.OnClickListener, ReservationContract.View {
    @Inject
    ReservationPresenter reservationPresenter;
    @Inject
    ReservationHotelListPresenter reservationHotelListPresenter;
    ProgressDialog progressDialog;
    private ReseveHotelListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    Date startOfTravel;
    List<ResultLodging> resultLodgings;
    int durationTravel;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private ProgressBar waitingLoading;
    private boolean loading = true;
//    @InjectView(R.id.toolbarBack)
//    ImageView toolbarBack;
//    @InjectView(R.id.toolbarToggle)
//    ImageView toolbarToggle;
    @InjectView(R.id.TypeAttractionHolder)
    RelativeLayout typeAttractionHolder;
    @InjectView(R.id.holderDate)
    RelativeLayout holderDate;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.reservationListRecyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.txtTypeHote)
    TextView txtTypeHotel;
    @InjectView(R.id.txtDurationHotel)
    TextView txtDurationHotel;
    private String nextOffset;
    private String todayDate;

    private void showDialogNumber() {
        CustomDialogNumberPicker cdd = new CustomDialogNumberPicker(this, 10, 1, "مدت زمان اقامت", null);
        cdd.show();
        cdd.setDialogResult(new CustomDialogNumberPicker.OnDialogNumberPick() {
            @Override
            public void finish(int result) {
                durationTravel = result;
                txtDurationHotel.setText(Util.persianNumbers(result + "شب"));
            }
        });
    }

    private void showDialogDate() {
        CustomDialogDate customDialogDate = new CustomDialogDate(this);
        customDialogDate.show();
        customDialogDate.setDialogDateResult(new CustomDialogDate.OnDialogDatePick() {
            @Override
            public void finish(Date result) {
                startOfTravel = result;
                txtTypeHotel.setText(Utils.getSimpleDate(result));
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ReseveHotelListAdapter(ReservationHotelListActivity.this, this, resultLodgings, getApplicationContext(), R.layout.fragment_itinerary_item);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                showProgressDialog();
                String offset = "0";
                reservationHotelListPresenter.getHotelReserve("full", String.valueOf(resultLodgings.get(position).getLodgingId()), "20", offset, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));

            }
        }));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && loading) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        reservationPresenter.getLodgingList("list", String.valueOf(resultLodgings.get(0).getLodgingCityId()), Util.getTokenFromSharedPreferences(getApplicationContext()), "20", nextOffset, Util.getAndroidIdFromSharedPreferences(getApplicationContext()), "");

                    }
                }
            }

        });
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();

        resultLodgings = (List<ResultLodging>) extras.getSerializable("resultLodgings");
        startOfTravel = (Date) extras.getSerializable("startOfTravel");

        durationTravel = (int) extras.getSerializable("durationTravel");
        nextOffset = extras.getString("nextOffset");
        todayDate = extras.getString("todayDate");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        Log.e("Aw cmn", "this is the activity im talkin bout");
        ButterKnife.inject(this);
        DaggerReservationHotelListComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .reservationHotelListModule(new ReservationHotelListModule(this, this))
                .build().injectReservationHotelList(this);

//
//        builder = DaggerReservationComponent.builder()
//                .netComponent(((App) getApplicationContext()).getNetComponent())
//                .reservationModule(new ReservationModule(this));
//        builder.build().inject()
        getExtras();
        txtDurationHotel.setText(Util.persianNumbers(durationTravel + "شب"));
        txtTypeHotel.setText(Utils.getSimpleDate(startOfTravel));

        setUpRecyclerView();
        setupToolbar();
//        toolbarBack.setOnClickListener(this);
        holderDate.setOnClickListener(this);
        typeAttractionHolder.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_list;
    }

    void setupToolbar() {
        ((StandardActivity) this).setSupportActionBar(toolbar);
        ((StandardActivity) this).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbarToggle.setVisibility(View.GONE);
    }

    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.holderDate:
                showDialogDate();
                break;

            case R.id.TypeAttractionHolder:
                showDialogNumber();
                break;
            case R.id.toolbarBack:
                Log.e("ddd", "toolbarback");
                break;
        }
    }

    @Override
    public void showHotelReserveList(ResultLodgingHotel resultLodgingHotel) {
        if (resultLodgingHotel != null) {
            ResultLodging resultLodgingHotelDetail = resultLodgingHotel.getResultLodging();
            Intent intent = new Intent(getApplicationContext(), ReservationHotelDetailActivity.class);
            intent.putExtra("resultLodgingHotelDetail", (Serializable) resultLodgingHotelDetail);
            intent.putExtra("startOfTravel", startOfTravel);
            intent.putExtra("durationTravel", durationTravel);
            intent.putExtra("todayDate", todayDate);
            startActivity(intent);
        }
    }

    @Override
    public void showLodgingList(ResultLodgingList resultLodgingList) {
        loading = true;
        List<ResultLodging> jj = resultLodgingList.getResultLodging();
        if (!nextOffset.equals(resultLodgingList.getStatistics().getOffsetNext().toString())) {
            resultLodgings.addAll(jj);
            adapter.notifyDataSetChanged();
//            waitingLoading.setVisibility(View.INVISIBLE);
            nextOffset = resultLodgingList.getStatistics().getOffsetNext().toString();
            loading = false;

        }
    }

    @Override
    public void showError(String message) {
//        progressDialog.dismiss();
//        if (message.contains("Unable to resolve host ") || message.contains("Software caused connection abort")) {
//            Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
//        }
//        if (message.contains("HTTP 400 BAD REQUEST")) {
//            Toast.makeText(getApplicationContext(), "در این مسیر برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void showComplete() {
        Util.dismissProgress(progressDialog);

//        progressDialog.dismiss();
    }


    @Override
    public void showProgress() {
        progressDialog = Util.showProgressDialog(getApplicationContext(), "لطفا منتظر بمانید", ReservationHotelListActivity.this);
    }

    @Override
    public void dismissProgress() {

        Util.dismissProgress(progressDialog);
    }
}
