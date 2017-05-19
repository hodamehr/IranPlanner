package com.iranplanner.tourism.iranplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.RoomListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.ResultRoom;

/**
 * Created by h.vahidimehr on 28/02/2017.
 */

public class ShowRoomActivity extends StandardActivity implements DataTransferInterface {
    private RoomListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultRoom> ResultRooms;
    Date startOfTravel;
    int durationTravel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ResultRooms = (List<ResultRoom>) bundle.getSerializable("ResultRooms");
        startOfTravel = (Date) bundle.getSerializable("startOfTravel");
        durationTravel = (int) bundle.getSerializable("durationTravel");

//        adapter = new CommentListAdapter(ShowRoomActivity.this, this, ResultRooms, getApplicationContext(), R.layout.fragment_comment_item);
        adapter = new RoomListAdapter(ShowRoomActivity.this, this, ResultRooms, getApplicationContext(), R.layout.activity_reservation_room_detail);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                ImageView reservationBtn = (ImageView) view.findViewById(R.id.ReservationBtn);
//                reservationBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.e("reserve", "click");
//                    }
//                });

            }
        }));
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
