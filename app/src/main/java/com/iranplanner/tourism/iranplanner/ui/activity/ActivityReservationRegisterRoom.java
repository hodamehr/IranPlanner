package com.iranplanner.tourism.iranplanner.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ActivityHotelReservationConfirm;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultLodging;
import entity.ResultRoom;
import tools.Util;

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
    TextView txtCheckInDateReserveValue;
    @InjectView(R.id.txtDurationTravel)
    TextView txtDurationTravel;
    @InjectView(R.id.imgHotel)
    ImageView imgHotel;
    @InjectView(R.id.ShowRoomHolder)
    RelativeLayout ShowRoomHolder;
    @InjectView(R.id.edtNameReservation)
    EditText edtNameReservation;
    @InjectView(R.id.edtEmailReservation)
    EditText edtEmailReservation;
    @InjectView(R.id.edtLastNameReservation)
    EditText edtLastNameReservation;
    @InjectView(R.id.textPhoneAddress)
    EditText textPhoneAddress;
    ResultLodging resultLodgingHotelDetail;
    String bundleId;
    private ProgressDialog progressDialog;

//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_register_room_activity);
        ButterKnife.inject(this);
        getExtra();

        Log.e("ReservationRegisterRoom", "Damn Son");

        edtNameReservation.setText(Util.getUserNameFromShareprefrence(getApplicationContext()));
        edtEmailReservation.setText(Util.getEmailFromShareprefrence(getApplicationContext()));

//        edtLastNameReservation.setText(Util.ge(getApplicationContext()));

//        setupTablayout();
        setHotelDescription();
        ShowRoomHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (validate()) {

                    Intent intentReservationRegisterRoom = new Intent(getApplicationContext(), ActivityHotelReservationConfirm.class);
                    intentReservationRegisterRoom.putExtra("selectedRooms", (Serializable) selectedRooms);
                    intentReservationRegisterRoom.putExtra("ResultRooms", (Serializable) ResultRooms);
                    intentReservationRegisterRoom.putExtra("resultLodgingHotelDetail", (Serializable) resultLodgingHotelDetail);
                    intentReservationRegisterRoom.putExtra("startOfTravel", startOfTravel);
                    intentReservationRegisterRoom.putExtra("durationTravel", durationTravel);
                    intentReservationRegisterRoom.putExtra("bundleId", bundleId);

                    intentReservationRegisterRoom.putExtra("edtNameReservation", edtNameReservation.getText().toString());
                    intentReservationRegisterRoom.putExtra("edtEmailReservation", edtEmailReservation.getText().toString());
                    intentReservationRegisterRoom.putExtra("edtLastNameReservation", edtLastNameReservation.getText().toString());
                    intentReservationRegisterRoom.putExtra("textPhoneAddress", textPhoneAddress.getText().toString());
                    startActivity(intentReservationRegisterRoom);
//                } else {
//                    Toast.makeText(getApplicationContext(), "مقادیر وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    public boolean validate() {
        boolean valid = true;
        String email = edtEmailReservation.getText().toString();
        String phoneNumber = textPhoneAddress.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtNameReservation.setError("فرمت ایمیل اشتباه است");
            valid = false;
        } else {
            edtNameReservation.setError(null);
        }
        if (edtLastNameReservation.getText().toString().equals("")) {
            valid = false;
            edtLastNameReservation.setError("نام وارد نشده است");
        } else {
            edtLastNameReservation.setError(null);
        }
        if (edtLastNameReservation.getText().toString().equals("")) {
            valid = false;
            edtLastNameReservation.setError("نام خانوادگی وارد نشده است");
        } else {
            edtLastNameReservation.setError(null);
        }
        if (!TextUtils.isEmpty(phoneNumber)) {

            if (phoneNumber.trim().length() < 10
                    || phoneNumber.trim().length() > 11
                    || (phoneNumber.trim().length() == 11 && !phoneNumber.trim().startsWith("09"))
                    || (phoneNumber.trim().length() == 10 && !phoneNumber.trim().startsWith("9"))) {
                String message = "شماره تلفن همراه وارد شده صحیح نیست.";
                textPhoneAddress.setError(message);
                valid = false;
            }
        } else if (TextUtils.isEmpty(phoneNumber)) {
            String message = "ثبت شماره تلفن اجباری است";
            textPhoneAddress.setError(message);
            valid = false;
        }

        return valid;
    }

    private String CreateBundle() {
        Date date = new Date();
        Random r = new Random();
        int rand1 = r.nextInt(9 - 1 + 1) + 1;
        int rand2 = r.nextInt(99 - 10 + 1) + 10;
        return String.valueOf(date.getTime()) + String.valueOf(rand2) + String.valueOf(rand1);
    }

    private void getExtra() {
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        bundleId =  bundle.getString("bundleId");
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
