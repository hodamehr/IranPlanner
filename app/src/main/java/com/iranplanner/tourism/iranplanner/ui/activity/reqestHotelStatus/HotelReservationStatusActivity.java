package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore.AttractionListMoreContract;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.RequestStatusAdapter;

import java.util.List;

import butterknife.ButterKnife;
import entity.ResultReqCount;

import static com.iranplanner.tourism.iranplanner.R.id.container;

/**
 * Created by h.vahidimehr on 30/08/2017.
 */

public class HotelReservationStatusActivity extends StandardActivity {
    List<ResultReqCount> resultReqCountList;
    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        resultReqCountList = (List<ResultReqCount>) extras.getSerializable("resultReqCountList");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_reservation_hotel);
        ButterKnife.inject(this);
        getExtras();
        initRequestStatusRecyclerView(resultReqCountList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status_reservation_hotel;
    }

    private void initRequestStatusRecyclerView( List<ResultReqCount> resultReqCountList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.settingRequestStatusRv);
        recyclerView.setAdapter(new RequestStatusAdapter(getApplicationContext(), resultReqCountList));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }
}
