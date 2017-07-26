package com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelDetails.ReservationHotelDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.ResultLodging;
import entity.ResultLodgingHotel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.Config;
import server.getJsonInterface;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class ReservationHotelListActivity extends StandardActivity implements DataTransferInterface {
    private ReseveHotelListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    Date startOfTravel;
    List<ResultLodging> resultLodgings;
    int durationTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();

        resultLodgings = (List<ResultLodging>) extras.getSerializable("resultLodgings");
        startOfTravel = (Date) extras.getSerializable("startOfTravel");
        durationTravel = (int) extras.getSerializable("durationTravel");

        adapter = new ReseveHotelListAdapter(ReservationHotelListActivity.this, this, resultLodgings, getApplicationContext(), R.layout.fragment_itinerary_item);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                getResultOfHotelReservation(String.valueOf(resultLodgings.get(position).getLodgingId()));
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_list;
    }

    public void getResultOfHotelReservation(String hotelId) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl(Config.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultLodgingHotel> callc = getJsonInterface.getHotelReserve("full", hotelId);
        callc.enqueue(new Callback<ResultLodgingHotel>() {
            @Override
            public void onResponse(Call<ResultLodgingHotel> call, Response<ResultLodgingHotel> response) {
                Log.e("result of intresting", "true");
                if (response.body() != null) {
                    ResultLodgingHotel res = response.body();
                    ResultLodging resultLodgingHotelDetail = res.getResultLodging();
                    Intent intent = new Intent(getApplicationContext(), ReservationHotelDetailActivity.class);
                    intent.putExtra("resultLodgingHotelDetail", (Serializable) resultLodgingHotelDetail);
                    intent.putExtra("startOfTravel", startOfTravel);
                    intent.putExtra("durationTravel", durationTravel);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResultLodgingHotel> call, Throwable t) {
                Log.e("result of intresting", "false");
            }
        });
    }

    private OkHttpClient setHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
