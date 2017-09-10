package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.ReservationRequestList;
import entity.ResultReservationReqFull;
import tools.Util;

public class ReservationRequestDetailActivity extends StandardActivity {

    private String hotelName, roomType, reqCode, startDueDate, reqStatus, reqDate, supervisorName, startPrice, off, finalPrice;
    private TextView tvHotelName, tvRoomTypeTv, tvReqCode, tvStartDueDate, tvReqStatus, tvReqDate, tvSupervisorName, tvStartPrice, tvOff, tvFinalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();
        init();
        initToolbar();
    }

    private void init() {
        tvHotelName = (TextView) findViewById(R.id.hotelNameTv);
        tvRoomTypeTv = (TextView) findViewById(R.id.hotelRoomTypeTv);
        tvReqCode = (TextView) findViewById(R.id.hotelReqCodeTv);
        tvStartDueDate = (TextView) findViewById(R.id.hotelStartDueDate);
        tvReqStatus = (TextView) findViewById(R.id.hotelReqStatusTv);
        tvSupervisorName = (TextView) findViewById(R.id.hotelSupervisorTv);
        tvStartPrice = (TextView) findViewById(R.id.hotelStartPriceTv);
        tvOff = (TextView) findViewById(R.id.hotelOffTv);
        tvFinalPrice = (TextView) findViewById(R.id.hotelFinalPriceTv);

        tvHotelName.setText(hotelName);
        tvRoomTypeTv.setText(roomType);
        tvReqCode.setText(reqCode);
        tvStartDueDate.setText(startDueDate);
        tvReqStatus.setText(reqStatus);
        tvSupervisorName.setText(supervisorName);
        tvFinalPrice.setText(finalPrice);
        tvOff.setText(off);
        tvStartPrice.setText(startPrice);

    }

    private void getExtras() {
        Intent intent = getIntent();
        List<ResultReservationReqFull> reservationReqFulls
                = (List<ResultReservationReqFull>) intent.getSerializableExtra(ReservationRequestList.INTENT_KEY_RESULT_RESERVATION);

        hotelName = reservationReqFulls.get(0).getRequest().getReqLodgingTitle();
        roomType = "نوع اتاق : " + reservationReqFulls.get(0).getRequest().getReqRoomTitle();
        reqCode = "کد درخواست " + reservationReqFulls.get(0).getRequest().getReqId();
        startDueDate = "از " +
                Utils.getSimpleDateMilli(Long.valueOf(reservationReqFulls.get(0).getRequest().getReqDateFrom()) * 1000) +
                " تا " +
                Utils.getSimpleDateMilli(Long.valueOf(reservationReqFulls.get(0).getRequest().getReqDateTo()) * 1000);
        reqStatus = "وضعیت درخواست : " + reservationReqFulls.get(0).getRequest().getReqStatus();

        tvReqDate = (TextView) findViewById(R.id.hotelReqDateTv);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String dateString = reservationReqFulls.get(0).getRequest().getStatusTimestamp();
        Date date;
        try {
            date = (Date) dateFormat.parse(dateString);
            reqDate = "تاریخ ثبت درخواست : " + Utils.getSimpleDate(date);
            tvReqDate.setText(reqDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //+ Utils.get(new Date(reservationReqFulls.get(0).getRequest().getStatusTimestamp()));
        supervisorName = "نام سرپرست اتاق : " + reservationReqFulls.get(0).getRequest().getReqRoomNameFirst() + " " +
                reservationReqFulls.get(0).getRequest().getReqRoomNameLast();
        startPrice = reservationReqFulls.get(0).getRequest().getReqPriceSum() + " تومان";
        off = reservationReqFulls.get(0).getRequest().getReqPriceHalfIn() + " تومان";
        finalPrice = reservationReqFulls.get(0).getRequest().getReqPriceFinal() + " تومان";
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(hotelName);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.content_purchase_hotel_full;
    }

}