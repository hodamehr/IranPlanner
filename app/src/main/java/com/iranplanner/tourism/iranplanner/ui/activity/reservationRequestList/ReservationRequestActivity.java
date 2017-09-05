package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.util.List;

import javax.inject.Inject;

import entity.ReservationRequestFull;
import entity.ReservationRequestList;
import entity.ResultReservationReqList;
import tools.Util;

public class ReservationRequestActivity extends StandardActivity implements ReservationRequestFullContract.View {
    @Inject
    ReservationRequestFullPresenter reservationRequestFullPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_request);


        init(getExtras());
        initToolbar();
        DaggerReservationRequestFullComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .reservationRequestFullModule(new ReservationRequestFullModule(this))
                .build().injectReservationRequestActivity(this);
//        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=req_user_full&lang=fa&uid=792147600796866&id=1115026211657201
        String offset = "0";
        reservationRequestFullPresenter.getReservationRequestFull("req_user_full", "fa", "792147600796866", "1115026211657201", "20", offset, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));


    }

    private List<ResultReservationReqList> getExtras() {
        Intent intent = getIntent();
        return (List<ResultReservationReqList>) intent.getSerializableExtra(ReservationRequestList.INTENT_KEY_RESULT_RESERVATION);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("نتیجه");
    }

    private void init(List<ResultReservationReqList> resultReservationReqList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationRequestRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ReservationRequestAdapter(this, resultReservationReqList));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_request;
    }

    @Override
    public void showReservationRequestFull(ReservationRequestFull reservationRequestFull) {
//hi amin!!!!
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress() {

    }
}
