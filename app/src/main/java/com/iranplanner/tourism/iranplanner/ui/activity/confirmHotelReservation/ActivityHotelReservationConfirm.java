package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.ActivityReservationRegisterRoom;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.showRoom.RoomListAdapter;
import com.iranplanner.tourism.iranplanner.ui.activity.showRoom.ShowRoomActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultLodging;
import entity.ResultRoom;
import tools.CustomDialogNumberPicker;

/**
 * Created by HoDA on 8/5/2017.
 */

public class ActivityHotelReservationConfirm extends StandardActivity implements DataTransferInterface, View.OnClickListener {
    private HotelReservationConfirmListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultRoom> ResultRooms;
    Date startOfTravel;
    int durationTravel;
    RelativeLayout chooseHolder;
    TextView txtNumberRoom;
    ResultLodging resultLodgingHotelDetail;

    //    RecyclerView recyclerView;
//    RelativeLayout hotelReservationOkHolder;
    Map<Integer, Integer> selectedRooms;

    @InjectView(R.id.reservationListRecyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.hotelReservationOkHolder)
    RelativeLayout hotelReservationOkHolder;
    @InjectView(R.id.txtNumber)
    TextView txtNumber;

    private void getExtra(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        selectedRooms = (Map<Integer, Integer>) bundle.getSerializable("selectedRooms");
        resultLodgingHotelDetail = (ResultLodging) bundle.getSerializable("resultLodgingHotelDetail");
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = (int) bundle.getSerializable("durationTravel");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        ButterKnife.inject(this);
        getExtra();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        hotelReservationOkHolder.setOnClickListener(this);
        adapter = new HotelReservationConfirmListAdapter(durationTravel,startOfTravel,ActivityHotelReservationConfirm.this, this, getApplicationContext(), R.layout.activity_reservation_room_detail,selectedRooms,ResultRooms);
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

    CustomDialogNumberPicker cdd;

    private void showDialogNumber(final int position, final TextView txtNumberRoom) {
        cdd = new CustomDialogNumberPicker(this, Integer.valueOf(ResultRooms.get(position).getRoomPriceQuantity()), 0,"تعداد اتاق های درخواستی",null);

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

                break;


        }

    }
}
