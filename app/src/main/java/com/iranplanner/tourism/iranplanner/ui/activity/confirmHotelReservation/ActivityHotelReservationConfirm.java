package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.showRoom.ShowRoomActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ReqLodgingReservation;
import entity.RequestLodgingReservationMain;
import entity.ResultLodging;
import entity.ResultLodgingReservation;
import entity.ResultRoom;
import tools.CustomDialogNumberPicker;
import tools.Util;

/**
 * Created by HoDA on 8/5/2017.
 */

public class ActivityHotelReservationConfirm extends StandardActivity implements DataTransferInterface, View.OnClickListener, ConfirmHotelContract.View, HotelReservationConfirmListAdapter.GoBackInterface {
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
    //    RecyclerView recyclerView;
//    RelativeLayout hotelReservationOkHolder;
    Map<Integer, Integer> selectedRooms;

    @InjectView(R.id.reservationListRecyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.hotelReservationOkHolder)
    RelativeLayout hotelReservationOkHolder;
    @InjectView(R.id.txtNumber)
    TextView txtNumber;
    List<ReqLodgingReservation> ReqLodgingReservationList;

    private void getExtra(){
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
        setContentView(R.layout.activity_show_room);
        ButterKnife.inject(this);
        getExtra();
        ReqLodgingReservationList = new ArrayList<ReqLodgingReservation>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        hotelReservationOkHolder.setOnClickListener(this);
        adapter = new HotelReservationConfirmListAdapter(durationTravel, startOfTravel, ActivityHotelReservationConfirm.this, this, getApplicationContext(), R.layout.activity_reservation_room_detail, selectedRooms, ResultRooms, this);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                chooseHolder = (RelativeLayout) view.findViewById(R.id.chooseHolder);
//                txtNumberRoom = (TextView) view.findViewById(R.id.txtNumberRoom);
//                chooseHolder.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showDialogNumber(position, txtNumberRoom);
//                    }
//                });

            }
        }));


    }

    private void requestConfirmHotel(ResultLodgingReservation resultLodgingReservation) {
        DaggerConfirmHotelComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .confirmHotelModule(new ConfirmHotelModule(this))
                .build().injectConfirmHotel(this);
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
                txtNumberRoom.setText(String.valueOf(result));
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
        txtNumber.setText(sumationRoomsd + "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_list;
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
                if (fillComplete) {
                    getRequestMain();
                    requestConfirmHotel(resultLodgingReservation);
                }else {
                    Toast.makeText(getApplicationContext(),"سرپرست اتاق ها وارد نشده است",Toast.LENGTH_LONG).show();
                }
                break;
        }

    }

    ResultLodgingReservation resultLodgingReservation;

    private void getRequestMain() {
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
        List<ReqLodgingReservation> reqLodgingReservations = getRequest();
        if (reqLodgingReservations.size() != 0) {
            resultLodgingReservation.setReqLodgingReservation(reqLodgingReservations);
        }
    }

    private List<ReqLodgingReservation> getRequest() {
        ReqLodgingReservationList.clear();



        int c = 0;

            for (ResultRoom resultRoom : ResultRooms) {

                ReqLodgingReservation reqLodgingReservation = new ReqLodgingReservation();
                reqLodgingReservation.setReqRoomNo(c + "");
                reqLodgingReservation.setReqRoomId(c + "");
                reqLodgingReservation.setReqRoomReqId(bundleId + c + "");
                reqLodgingReservation.setReqRoomId(resultRoom.getRoomId());
                reqLodgingReservation.setReqRoomBundleId(bundleId);
                reqLodgingReservation.setReqRoomLodgingId(resultRoom.getRoomLodgingId());
                reqLodgingReservation.setReqRoomNameFirst("d");//(resultRoom.getHeadName());
                reqLodgingReservation.setReqRoomNameLast("dd");//(resultRoom.getHeadLastName());
                reqLodgingReservation.setReqRoomNation((resultRoom.getSelectedNationality() == "0") ? "ir" : "for");
                reqLodgingReservation.setReqRoomExtraCount(resultRoom.getSelectedAddNumbers());
                reqLodgingReservation.setReqRoomPricePerson(resultRoom.getRoomPriceAdPeople());
                reqLodgingReservation.setReqRoomPriceNet(resultRoom.getLodgingRoomPrice());

                reqLodgingReservation.setReqRoomPriceHalfIn((resultRoom.getHalfIn() != null && resultRoom.getHalfIn()) ? resultRoom.getRoomPriceHalfboardIn() : "0");

                reqLodgingReservation.setReqRoomPriceHalfOut((resultRoom.getHalfOut() != null && resultRoom.getHalfOut()) ? resultRoom.getRoomPriceHalfboardOut() : "0");

                int a = Integer.valueOf(resultRoom.getRoom_price_final()) * durationTravel;
                int b = Integer.valueOf(resultRoom.getRoomPrice()) * durationTravel;
                reqLodgingReservation.setReqRoomPriceCalc(String.valueOf(Integer.valueOf(resultRoom.getRoom_price_final()) * durationTravel));
                reqLodgingReservation.setReqRoomPriceFinal(resultRoom.getRoom_price_final());
                reqLodgingReservation.setReqRoomPriceDiscount(String.valueOf(b - a));
                c++;
                ReqLodgingReservationList.add(reqLodgingReservation);
            }


        return ReqLodgingReservationList;
    }

    @Override
    public void showHotelReservationResult(RequestLodgingReservationMain loginResult) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityHotelReservationConfirm.this,
                ShowRoomActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void clearList() {
        onBackPressed();
    }
}
