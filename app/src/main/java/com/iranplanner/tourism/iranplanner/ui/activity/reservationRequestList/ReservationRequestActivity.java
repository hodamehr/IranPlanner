package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.mainActivity.MainActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import entity.ReservationRequestFull;
import entity.ReservationRequestList;
import entity.ResultReservationReqList;
import tools.Util;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ReservationRequestActivity extends StandardActivity implements ReservationRequestFullContract.View {
    @Inject
    ReservationRequestFullPresenter reservationRequestFullPresenter;

    private String title;

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
    }

    private List<ResultReservationReqList> getExtras() {
        Intent intent = getIntent();
        title = intent.getExtras().getString("status");
        return (List<ResultReservationReqList>) intent.getSerializableExtra(ReservationRequestList.INTENT_KEY_RESULT_RESERVATION);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("درخواست های " + title);
    }

    private void init(final List<ResultReservationReqList> resultReservationReqList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationRequestRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ReservationRequestAdapter(this, resultReservationReqList));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                reservationRequestFullPresenter.getReservationRequestFull("req_user_full", "fa", Util.getUseRIdFromShareprefrence(getApplicationContext()), resultReservationReqList.get(position).getRequest().getReqId(), "20", "0", Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
            }
        }));
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
        Log.e(TAG, "showReservationRequestFull");
        Intent intent = new Intent(ReservationRequestActivity.this, ReservationRequestDetailActivity.class);
        intent.putExtra(ReservationRequestList.INTENT_KEY_RESULT_RESERVATION, (Serializable) reservationRequestFull.getResultReservationReqFull());
        intent.putExtra("status", title);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message + " ERROR");
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
