package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ActivityHotelReservationConfirm;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList.ReservationRequestActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import entity.ReservationRequestList;
import entity.ResultBundleStatus;
import entity.ResultReqBundle;
import entity.ResultReqCount;
import tools.Util;

/**
 * Created by h.vahidimehr on 30/08/2017.
 */

public class HotelReservationStatusActivity extends StandardActivity
        implements HotelReservationStatusContract.View {
    @Inject
    HotelReservationStatusListPresenter hotelReservationStatusListPresenter;
    List<ResultReqCount> resultReqCountList;
    private ProgressDialog progress;
    List<ResultReqBundle> resultReqBundleList;

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        resultReqCountList = (List<ResultReqCount>) extras.getSerializable("resultReqCountList");
        resultReqBundleList = (List<ResultReqBundle>) extras.getSerializable("resultReqBundleList");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_reservation_hotel);
        ButterKnife.inject(this);

        initToolbar();
        getExtras();
        initRequestStatusRecyclerView(resultReqCountList, resultReqBundleList);
        DaggerHotelReservationStatusListComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .hotelReservationStatusListModule(new HotelReservationStatusListModule(this))
                .build().injectHotelReservationStatusActivity(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("اقامتهای من");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status_reservation_hotel;
    }

    private void initRequestStatusRecyclerView(final List<ResultReqCount> resultReqCountList, final List<ResultReqBundle> resultReqBundleList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.settingRequestStatusRv);
        RecyclerView settingRequestBundle = (RecyclerView) findViewById(R.id.settingRequestBundle);
        settingRequestBundle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        settingRequestBundle.setAdapter(new RequestBundleAdapter(getApplicationContext(), resultReqBundleList));
        settingRequestBundle.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                String offset = "0";
                if (resultReqCountList.get(position).getReservationReqStatus().getStatusCount() != "0") {
                    //        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=req_user_bundle_full&uid=792147600796866&lang=fa&id=12150158259660
                    hotelReservationStatusListPresenter.getHotelReservationBundleFull("req_user_bundle_full", "fa", Util.getUseRIdFromShareprefrence(getApplicationContext()), resultReqBundleList.get(position).getBundleRequest().getBundleId(), Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                }

            }
        }));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RequestStatusAdapter(getApplicationContext(), resultReqCountList));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                String offset = "0";
                if (resultReqCountList.get(position).getReservationReqStatus().getStatusCount() != "0") {
                    //        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=req_user_list&lang=fa&uid=792147600796866&type=1

                    hotelReservationStatusListPresenter.getHotelReservationStatusList("req_user_list", "fa", Util.getUseRIdFromShareprefrence(getApplicationContext()), String.valueOf(position), "20", offset, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                }

            }
        }));
    }

    @Override
    public void showHotelReservationStatusList(ReservationRequestList reservationRequestList) {

        Intent intent = new Intent(HotelReservationStatusActivity.this, ReservationRequestActivity.class);
        intent.putExtra
                (ReservationRequestList.INTENT_KEY_RESULT_RESERVATION, (Serializable) reservationRequestList.getResultReservationReqList());
        startActivity(intent);
    }

    @Override
    public void showHotelReservationBundleStatus(ResultBundleStatus resultBundleStatus) {
        Intent intentReservationRegisterRoom = new Intent(getApplicationContext(), ActivityHotelReservationConfirm.class);
        entity.Bundle bb = (entity.Bundle) resultBundleStatus.getResultReservationBundleList().getBundle();
        intentReservationRegisterRoom.putExtra("RoomBundle", (Serializable) bb);
        intentReservationRegisterRoom.putExtra("BundleFrom", "HotelReservationStatusActivity");
        startActivity(intentReservationRegisterRoom);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void dismissProgress() {
        Util.dismissProgress(progress);
    }

    @Override
    public void showProgress() {
        progress = Util.showProgressDialog(getApplicationContext(), "لطفا منتظر بمانید", this);
    }
}
