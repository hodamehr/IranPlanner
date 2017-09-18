package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.ClickableViewPager;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus.HotelReservationStatusActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.showRoom.ShowRoomActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.SettingContract;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.SettingPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.GetInfoResult;
import entity.ReqLodgingReservation;
import entity.RequestLodgingReservationMain;
import entity.ReservationRequestComplete;
import entity.ReservationRequestDeleteRoom;
import entity.ResultLodging;
import entity.ResultLodgingReservation;
import entity.ResultReqCount;
import entity.ResultReservationReqStatus;
import entity.ResultRoom;
import tools.CustomDialogNumberPicker;
import tools.Util;

/**
 * Created by HoDA on 8/5/2017.
 */

public class ActivityHotelReservationConfirm extends StandardActivity implements DataTransferInterface, View.OnClickListener, ConfirmHotelContract.View, SettingContract.View/*, HotelReservationConfirmListAdapter.GoBackInterface*/ {
    private HotelReservationConfirmListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultRoom> ResultRooms;
    Date startOfTravel;
    int durationTravel;
    RelativeLayout chooseHolder;
    TextView txtNumberRoom;
    ResultLodging resultLodgingHotelDetail;
    String bundleId;
    String edtNameReservation;
    String edtEmailReservation;
    String edtLastNameReservation;
    String textPhoneAddress;
    @Inject
    ConfirmHotelPresenter confirmHotelPresenter;
    Map<Integer, Integer> selectedRooms;
    ProgressDialog progressDialog;
    @Inject
    SettingPresenter settingPresenter;
    @InjectView(R.id.pager)
    ClickableViewPager pager;
    List<ReqLodgingReservation> ReqLodgingReservationList;
    View viewAdapter, viewEnds;
    ConfirmReservationViewPagerAdapter confirmReservationViewPagerAdapter;

    private void getExtra() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        selectedRooms = (Map<Integer, Integer>) bundle.getSerializable("selectedRooms");
        resultLodgingHotelDetail = (ResultLodging) bundle.getSerializable("resultLodgingHotelDetail");
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = (int) bundle.getSerializable("durationTravel");
        bundleId = bundle.getString("bundleId");
        edtNameReservation = bundle.getString("edtNameReservation");
        edtEmailReservation = bundle.getString("edtEmailReservation");
        edtLastNameReservation = bundle.getString("edtLastNameReservation");
        textPhoneAddress = bundle.getString("textPhoneAddress");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_room);

        Log.e("HotelReservationConfirm", "Damn Son");

        ButterKnife.inject(this);
        getExtra();
        DaggerConfirmHotelComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .confirmHotelModule(new ConfirmHotelModule(this, this))
                .build().injectConfirmHotel(this);
        ReqLodgingReservationList = new ArrayList<ReqLodgingReservation>();

        confirmReservationViewPagerAdapter = new ConfirmReservationViewPagerAdapter(getApplicationContext(), this, ResultRooms, durationTravel, startOfTravel);
        pager.setAdapter(confirmReservationViewPagerAdapter);
        pager.setCurrentItem(ResultRooms.size() - 1);
        confirmReservationViewPagerAdapter.setOnItemClickListener(new ConfirmReservationViewPagerAdapter.OnItemClickViewPagerListener() {
            @Override
            public void onItemClick(int position, String viewName, entity.ResultRoom room, View view, View viewEnd) {
                viewAdapter = view;
                viewEnds = viewEnd;
                String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
                String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
                if (viewName.equals("nextHolder")) {
                    pager.setCurrentItem(position - 1, true);

                } else if (viewName.equals("txtOkRoom")) {
//                    getRequestMain(position,room);
                    requestConfirmHotel(getRequestMain(position, room));
                } else if (viewName.equals("okEndHolder")) {

                    confirmHotelPresenter.getReservationRequestComplete("complete_bundle", bundleId, Util.getUseRIdFromShareprefrence(getApplicationContext()), cid, andId);
                } else if (viewName.equals("roomDelete")) {

                    confirmHotelPresenter.getReservationRequestDeleteRoom("complete_bundle", bundleId, Util.getUseRIdFromShareprefrence(getApplicationContext()), cid, andId);
                }
            }
        });

//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        hotelReservationOkHolder.setOnClickListener(this);
//        adapter = new HotelReservationConfirmListAdapter(durationTravel, startOfTravel, ActivityHotelReservationConfirm.this, this, getApplicationContext(), R.layout.activity_reservation_room_detail, selectedRooms, ResultRooms, this);
//        recyclerView.setAdapter(adapter);
//        mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, final int position) {
//                TextView txtOkRoom = (TextView) view.findViewById(R.id.txtOkRoom);
//
//                txtOkRoom.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.e("oktext clicked", "yes");
//
//                        if (!ResultRooms.get(position).getOkConfirmChange()) {
//                            getRequestMain(position);
//                            requestConfirmHotel(resultLodgingReservation);
//                        } else {
//                            Toast.makeText(getApplicationContext(), "سرپرست اتاق ها وارد نشده است", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                txtNumberRoom = (TextView) view.findViewById(R.id.txtNumberRoom);
//                chooseHolder.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showDialogNumber(position, txtNumberRoom);
//                    }
//                });

//            }
//        }));


    }

    private void requestConfirmHotel(ResultLodgingReservation resultLodgingReservation) {

        String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
        String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
        confirmHotelPresenter.sendRequestReservation(resultLodgingReservation, cid, andId);

    }

    CustomDialogNumberPicker cdd;

    private void showDialogNumber(final int position, final TextView txtNumberRoom) {
        cdd = new CustomDialogNumberPicker(this, Integer.valueOf(ResultRooms.get(position).getRoomPriceQuantity()), 0, "تعداد اتاق های درخواستی", null);

        cdd.show();
        cdd.setDialogResult(new CustomDialogNumberPicker.OnDialogNumberPick() {
            @Override
            public void finish(int result) {
//                txtNumberRoom.setText(String.valueOf(result));
                selectedRooms.put(position, result);
                sums();
            }
        });
    }

    private void sums() {
        int sumationRoomsd = 0;
        for (Integer number : selectedRooms.values()) {
            sumationRoomsd = sumationRoomsd + number;
        }
//        txtNumber.setText(sumationRoomsd + "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_room_viewpager;

    }

    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.hotelReservationOkHolder:
                boolean fillComplete = true;
                for (ResultRoom resultRoom : ResultRooms) {
                    if (!resultRoom.getOkConfirmChange()) {
                        fillComplete = false;
                    }
                }
//                if (fillComplete) {
//                    getRequestMain(0,null);
//                    requestConfirmHotel(resultLodgingReservation);
//                } else {
//                    Toast.makeText(getApplicationContext(), "سرپرست اتاق ها وارد نشده است", Toast.LENGTH_LONG).show();
//                }
                break;
        }

    }

    ResultLodgingReservation resultLodgingReservation;

    private ResultLodgingReservation getRequestMain(int position, ResultRoom room) {
        resultLodgingReservation = new ResultLodgingReservation();
        resultLodgingReservation.setReqBundleId(bundleId);
        resultLodgingReservation.setReqHeadEmail(edtEmailReservation);
        resultLodgingReservation.setReqHeadMobile(textPhoneAddress);
        resultLodgingReservation.setReqHeadNameFirst(edtNameReservation);
        resultLodgingReservation.setReqHeadNameLast(edtLastNameReservation);
        resultLodgingReservation.setReqLodgingDateFrom(String.valueOf(startOfTravel.getTime()));
        resultLodgingReservation.setReqLodgingDateCount(String.valueOf(durationTravel));
        resultLodgingReservation.setReqLodgingId(String.valueOf(resultLodgingHotelDetail.getLodgingId()));
        resultLodgingReservation.setReqUid(Util.getUseRIdFromShareprefrence(getApplicationContext()));
        List<ReqLodgingReservation> reqLodgingReservations = getRequest(position, room);
        if (reqLodgingReservations.size() != 0) {
            resultLodgingReservation.setReqLodgingReservation(reqLodgingReservations);
        }
        return resultLodgingReservation;
    }

    private List<ReqLodgingReservation> getRequest(int position, ResultRoom resultRoom) {
        ReqLodgingReservationList.clear();


//        for (ResultRoom resultRoom : ResultRooms) {

        ReqLodgingReservation reqLodgingReservation = new ReqLodgingReservation();
        reqLodgingReservation.setReqRoomNo(position + "");
        reqLodgingReservation.setReqRoomId(position + "");
        reqLodgingReservation.setReqRoomReqId(bundleId + position + "");
        reqLodgingReservation.setReqRoomId(resultRoom.getRoomId());
        reqLodgingReservation.setReqRoomBundleId(bundleId);
        reqLodgingReservation.setReqRoomLodgingId(resultRoom.getRoomLodgingId());
        reqLodgingReservation.setReqRoomNameFirst(resultRoom.getHeadName());
        reqLodgingReservation.setReqRoomNameLast(resultRoom.getHeadLastName());
        reqLodgingReservation.setReqRoomNation((resultRoom.getSelectedForeign() == "0") ? "ir" : "for");
        reqLodgingReservation.setReqRoomExtraCount(resultRoom.getSelectedAddNumbers() != null ? resultRoom.getSelectedAddNumbers() : "0");
        reqLodgingReservation.setReqRoomHalfIn((resultRoom.getHalfIn() != null &&
                resultRoom.getHalfIn()) ? "1" : "0");
        reqLodgingReservation.setReqRoomHalfOut((resultRoom.getHalfOut() != null && resultRoom.getHalfOut()) ? "1" : "0");


        ReqLodgingReservationList.add(reqLodgingReservation);
        return ReqLodgingReservationList;
    }

    @Override
    public void showHotelReservationResult(RequestLodgingReservationMain loginResult) {
        pager.setCurrentItem(pager.getCurrentItem()  , true);
        ((TextView) viewAdapter.findViewById(R.id.txtOkRoom)).setText("ویرایش");
        if (0==pager.getCurrentItem()) {
            viewEnds.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showReservationRequestComplete(ReservationRequestComplete reservationRequestComplete) {
        if (reservationRequestComplete.getStatus().getStatus() == 200) {
            String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
            String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
            settingPresenter.getResultReservationReqStatus("req_user_count", Util.getUseRIdFromShareprefrence(getApplicationContext()), "fa", cid, andId);
        }
    }

    @Override
    public void showReservationRequestDeleteRoom(ReservationRequestDeleteRoom reservationRequestDeleteRoom) {
        if (reservationRequestDeleteRoom.getStatus().getStatus() == 200) {
            ResultRooms.remove(0);
            confirmReservationViewPagerAdapter.notifyDataSetChanged();
            Log.e("delete", "room");
        }
    }


    @Override
    public void showInfoUserResult(GetInfoResult infoResult) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "در عملیات ثبت اتاق اشکالی بوجود آمده است", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressDialog = Util.showProgressDialog(getApplicationContext(), "لطفا منتظر بمانید", this);
    }

    @Override
    public void dismissProgress() {
        Util.dismissProgress(progressDialog);
    }

    @Override
    public void showResultReservationReqStatus(ResultReservationReqStatus resultReservationReqStatus) {
        Intent intent = new Intent(getApplicationContext(), HotelReservationStatusActivity.class);
        List<ResultReqCount> resultReqCountList = resultReservationReqStatus.getResultReqCount();
        intent.putExtra("resultReqCountList", (Serializable) resultReqCountList);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityHotelReservationConfirm.this,
                ShowRoomActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
