package com.iranplanner.tourism.iranplanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.CommentListAdapter;
import com.iranplanner.tourism.iranplanner.adapter.ReseveDateListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.ResultComment;
import entity.ResultItinerary;
import entity.ResultLodging;
import entity.ResultLodgingList;
import entity.ResultLodgingRoomList;
import entity.ResultRoom;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class CommentListActivity extends StandardActivity implements DataTransferInterface {
    private CommentListAdapter adapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        List<ResultComment> resultComments = (List<ResultComment>) extras.getSerializable("resultComments");
        adapter = new CommentListAdapter(CommentListActivity.this, this, resultComments, getApplicationContext(), R.layout.fragment_comment_item );
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

    private OkHttpClient setHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public void getLodgingReservation(String cityId) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);

        Call<ResultLodgingList> callc = getJsonInterface.getLodgingReserve("list", cityId);
        callc.enqueue(new Callback<ResultLodgingList>() {
            @Override
            public void onResponse(Call<ResultLodgingList> call, Response<ResultLodgingList> response) {
                Log.e("result of intresting", "true");

                if (response.body() != null && response.body().getResultLodging().size() != 0) {
                    ResultLodgingList res = response.body();
                    List<ResultLodging> resultLodgings = res.getResultLodging();
                    Intent intent = new Intent(getApplicationContext(), ReservationHotelListActivity.class);
                    intent.putExtra("resultLodgings", (Serializable) resultLodgings);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResultLodgingList> call, Throwable t) {
                Log.e("result of intresting", "false");

            }
        });
    }

    public void getLodgingResevationRoom(String cityID) {
//        getResultLodgingRoomList
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        cityID = "22649";
        Call<ResultLodgingRoomList> callc = getJsonInterface.getResultLodgingRoomList("room", cityID, "", "");
        callc.enqueue(new Callback<ResultLodgingRoomList>() {
            @Override
            public void onResponse(Call<ResultLodgingRoomList> call, Response<ResultLodgingRoomList> response) {
                Log.e("result of intresting", "true");


                if (response.body() != null && response.body().getResultRoom().size() != 0) {
                    ResultLodgingRoomList res = response.body();
                    List<ResultRoom> resultLodgings = res.getResultRoom();
                    Intent intent = new Intent(getApplicationContext(), ReservationHotelListActivity.class);
                    intent.putExtra("resultLodgings", (Serializable) resultLodgings);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResultLodgingRoomList> call, Throwable t) {
                Log.e("result of intresting", "false");

            }
        });
    }



    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
