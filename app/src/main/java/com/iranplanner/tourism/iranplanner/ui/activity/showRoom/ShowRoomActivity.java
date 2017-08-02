package com.iranplanner.tourism.iranplanner.ui.activity.showRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.ActivityReservationRegisterRoom;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultRoom;
import tools.CustomDialogNumberPicker;

/**
 * Created by h.vahidimehr on 28/02/2017.
 */

public class ShowRoomActivity extends StandardActivity implements DataTransferInterface {
    private RoomListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultRoom> ResultRooms;
    Date startOfTravel;
    int durationTravel;
    RelativeLayout chooseHolder;
    TextView txtNumberRoom;
//    RecyclerView recyclerView;
//    RelativeLayout hotelReservationOkHolder;
    Map<Integer, Integer> selectedRooms;

    @InjectView(R.id.reservationListRecyclerView)
    RecyclerView recyclerView; @InjectView(R.id.hotelReservationOkHolder)
    RelativeLayout hotelReservationOkHolder;
    @InjectView(R.id.txtNumber)
    TextView txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        ButterKnife.inject(this);
//        recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
//        hotelReservationOkHolder = (RelativeLayout) findViewById(R.id.hotelReservationOkHolder);
//        txtNumber = (TextView) findViewById(R.id.txtNumber);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        selectedRooms = new HashMap<>();
        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = (int) bundle.getSerializable("durationTravel");
        hotelReservationOkHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), ActivityReservationRegisterRoom.class);
                startActivity(intent1);
            }
        });
        adapter = new RoomListAdapter(ShowRoomActivity.this, this, ResultRooms, getApplicationContext(), R.layout.activity_reservation_room_detail);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                chooseHolder = (RelativeLayout) view.findViewById(R.id.chooseHolder);
                txtNumberRoom = (TextView) view.findViewById(R.id.txtNumberRoom);
                chooseHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogNumber(position, txtNumberRoom);
                    }
                });

            }
        }));


    }

    CustomDialogNumberPicker cdd;

    private void showDialogNumber(final int position, final TextView txtNumberRoom) {
        cdd = new CustomDialogNumberPicker(this, Integer.valueOf(ResultRooms.get(position).getRoomPriceQuantity()), 0, "تعداد اتاق های درخواستی");
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
}
