package com.iranplanner.tourism.iranplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ActivityHotelReservationConfirm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultLodging;
import entity.ResultRoom;

/**
 * Created by HoDA on 7/31/2017.
 */

public class ActivityReservationRegisterRoom extends StandardActivity {
    TabLayout tabLayout;
    Date startOfTravel;
    int durationTravel;
    List<ResultRoom> ResultRooms;
    Map<Integer, Integer> selectedRooms;
    @InjectView(R.id.txtHotelName)
    TextView txtHotelName;
    @InjectView(R.id.txtRoomSelected)
    TextView txtRoomSelected;
    @InjectView(R.id.txtCheckInDateReserve)
    TextView txtCheckInDateReserve;
//    @InjectView(R.id.txtDateCheckOutReserveValue)
//    TextView txtDateCheckOutReserveValue;
    @InjectView(R.id.txtTimeCheckoutValue)
    TextView txtTimeCheckoutValue;
    @InjectView(R.id.txtTimeCheckinValue)
    TextView txtTimeCheckinValue;
    @InjectView(R.id.txtHotelAddressValue)
    TextView txtHotelAddressValue;
    @InjectView(R.id.txtCheckInDateReserveValue)
    TextView txtCheckInDateReserveValue;@InjectView(R.id.txtDurationTravel)
    TextView txtDurationTravel;
    @InjectView(R.id.imgHotel)
    ImageView imgHotel;
    @InjectView(R.id.ShowRoomHolder)
    RelativeLayout ShowRoomHolder;
    ResultLodging resultLodgingHotelDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_register_room_activity);
        ButterKnife.inject(this);
       getExtra();
//        setupTablayout();
        setHotelDescription();
        ShowRoomHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentReservationRegisterRoom = new Intent(getApplicationContext(), ActivityHotelReservationConfirm.class);
                intentReservationRegisterRoom.putExtra("selectedRooms", (Serializable) selectedRooms);
                intentReservationRegisterRoom.putExtra("ResultRooms", (Serializable) ResultRooms);
                intentReservationRegisterRoom.putExtra("resultLodgingHotelDetail", (Serializable) resultLodgingHotelDetail);
                intentReservationRegisterRoom.putExtra("startOfTravel", startOfTravel);
                intentReservationRegisterRoom.putExtra("durationTravel", durationTravel);
                startActivity(intentReservationRegisterRoom);
            }
        });
    }
    private void getExtra(){
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = 0;
        durationTravel = (int) bundle.getSerializable("durationTravel");
        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        selectedRooms = (Map<Integer, Integer>) bundle.getSerializable("selectedRooms");
        resultLodgingHotelDetail = (ResultLodging) bundle.getSerializable("resultLodgingHotelDetail");
    }

    private void setHotelDescription() {
        txtHotelName.setText(resultLodgingHotelDetail.getLodgingName());
        txtRoomSelected.setText(selectedRooms.size() + "");
//        txtCheckInDateReserve.setText("");
//        txtDateCheckOutReserveValue.setText("");
        txtTimeCheckoutValue.setText(resultLodgingHotelDetail.getLodgingCheckout());
        txtTimeCheckinValue.setText(resultLodgingHotelDetail.getLodgingCheckin());
        txtHotelAddressValue.setText(resultLodgingHotelDetail.getLodgingAddress());
        txtCheckInDateReserveValue.setText(Utils.getSimpleDate(startOfTravel));
//        txtDurationTravel.setText(durationTravel);
    }

//    private void setupTablayout() {
//
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_input;
    }


}
