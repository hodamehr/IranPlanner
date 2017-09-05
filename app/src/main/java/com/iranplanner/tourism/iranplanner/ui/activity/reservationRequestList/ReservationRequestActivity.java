package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.util.List;

import entity.ReservationRequestList;
import entity.ResultReservationReqList;

public class ReservationRequestActivity extends StandardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_request);


        init(getExtras());
        initToolbar();
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
        return 0;
    }
}
