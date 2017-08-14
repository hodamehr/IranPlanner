package com.iranplanner.tourism.iranplanner.ui.activity.showRoom;

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
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.ActivityReservationRegisterRoom;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ReqLodgingReservation;
import entity.ResultLodging;
import entity.ResultRoom;
import tools.CustomDialogNumberPicker;

/**
 * Created by h.vahidimehr on 28/02/2017.
 */

public class ShowRoomActivity extends StandardActivity implements DataTransferInterface, View.OnClickListener {
    private RoomListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultRoom> ResultRooms;
    List<ResultRoom> newResultRooms;
    Date startOfTravel;
    int durationTravel;
    RelativeLayout chooseHolder;
    TextView txtNumberRoom,txtNumberChoose;
    ResultLodging resultLodgingHotelDetail;
    Set keys;
    List<ReqLodgingReservation> reqLodgingReservationList;

    //    RecyclerView recyclerView;
//    RelativeLayout hotelReservationOkHolder;

    Map<Integer, Integer> selectedRooms;
    List<String> t;


    @InjectView(R.id.reservationListRecyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.hotelReservationOkHolder)
    RelativeLayout hotelReservationOkHolder;
    @InjectView(R.id.txtNumber)
    TextView txtNumber;

    private void getExtra() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        selectedRooms = new HashMap<>();
        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        resultLodgingHotelDetail = (ResultLodging) bundle.getSerializable("resultLodgingHotelDetail");
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = 0;
        durationTravel = (int) bundle.getSerializable("durationTravel");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room);
        ButterKnife.inject(this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getExtra();
        hotelReservationOkHolder.setOnClickListener(this);
        adapter = new RoomListAdapter(ShowRoomActivity.this, this, ResultRooms, getApplicationContext(), R.layout.activity_reservation_room_detail);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                chooseHolder = (RelativeLayout) view.findViewById(R.id.chooseHolder);
                txtNumberRoom = (TextView) view.findViewById(R.id.txtNumberRoom);
                txtNumberChoose = (TextView) view.findViewById(R.id.txtNumberChoose);


                chooseHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogNumber(position, txtNumberRoom,txtNumberChoose);
                    }
                });

            }
        }));

        t = new ArrayList<>();
    }

    CustomDialogNumberPicker cdd;

    private void showDialogNumber(final int position, final TextView txtNumberRoom, final TextView txtNumberChoose) {
        cdd = new CustomDialogNumberPicker(this, Integer.valueOf(ResultRooms.get(position).getRoomPriceQuantity()), 0, "تعداد اتاق های درخواستی",null);
        cdd.show();
        cdd.setDialogResult(new CustomDialogNumberPicker.OnDialogNumberPick() {
            @Override
            public void finish(int result) {
                txtNumberRoom.setText(String.valueOf(result));
                selectedRooms.put(position, result);
                if(result!=0){
                    txtNumberChoose.setBackgroundColor(getResources().getColor(R.color.green));
                    txtNumberRoom.setBackgroundColor(getResources().getColor(R.color.greenpress));
                }

                sums();
                ss();
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

    private void ss() {
        keys = selectedRooms.keySet();
    }

    private void tt() {
        newResultRooms = new ArrayList<>();
        for (Object key : keys) {

            Log.e("D", "D");
            if (selectedRooms.containsKey(key)) {

//                for (Integer integer : selectedRooms.values()) {
//                    ResultRoom room = new ResultRoom();
//                    room = ResultRooms.get(Integer.valueOf(key.toString()));
//                    newResultRooms.add(room);
//                }
                int o = Integer.valueOf(selectedRooms.get(key));
                for (int i = 0; i < Integer.valueOf(selectedRooms.get(key)); i++) {
                    ResultRoom room = new ResultRoom();
                    room = ResultRooms.get(Integer.valueOf(key.toString()));
                    newResultRooms.add(room);
                }
            }
        }

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
                if (startOfTravel == null || durationTravel == 0) {
                    Toast.makeText(this, "تاریخ و مدت زمان اقامت نامشخص ", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedRooms.size() != 0) {
                        tt();
                        Intent intentReservationRegisterRoom = new Intent(getApplicationContext(), ActivityReservationRegisterRoom.class);
                        intentReservationRegisterRoom.putExtra("selectedRooms", (Serializable) selectedRooms);
                        intentReservationRegisterRoom.putExtra("ResultRooms", (Serializable) newResultRooms);

                        intentReservationRegisterRoom.putExtra("startOfTravel", startOfTravel);
                        intentReservationRegisterRoom.putExtra("durationTravel", durationTravel);
                        intentReservationRegisterRoom.putExtra("resultLodgingHotelDetail", (Serializable) resultLodgingHotelDetail);
                        startActivity(intentReservationRegisterRoom);
                    }
                }

                break;


        }

    }
}
