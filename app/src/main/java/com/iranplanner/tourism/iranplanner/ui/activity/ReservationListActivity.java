package com.iranplanner.tourism.iranplanner.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.ReseveDateListAdapter;
import com.iranplanner.tourism.iranplanner.di.AttractionModule;
import com.iranplanner.tourism.iranplanner.di.DaggerAtractionComponent;
import com.iranplanner.tourism.iranplanner.di.DaggerReservationComponent;
import com.iranplanner.tourism.iranplanner.di.ReservationModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.presenter.ReservationPresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.ReservationContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import entity.ResultItinerary;
import entity.ResultLodging;
import entity.ResultLodgingList;

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

public class ReservationListActivity extends StandardActivity implements DataTransferInterface, ReservationContract.View {
    private ReseveDateListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    Date startOfTravel;
    ResultItinerary itineraryData;
    int durationTravel = 1;
    ProgressDialog progressDialog;
    DaggerReservationComponent.Builder builder;
    @Inject
    ReservationPresenter reservationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservationListRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        itineraryData = (ResultItinerary) extras.getSerializable("itineraryData");
        startOfTravel = (Date) extras.getSerializable("startOfTravel");

        adapter = new ReseveDateListAdapter(ReservationListActivity.this, this, itineraryData, getApplicationContext(), R.layout.fragment_itinerary_item, startOfTravel);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//--------
        builder = DaggerReservationComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .reservationModule(new ReservationModule(this));
        builder.build().inject(this);

        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView reservationBtn = (ImageView) view.findViewById(R.id.ReservationBtn);
                TextView txtDurationLodgingCity = (TextView) view.findViewById(R.id.txtDurationLodgingCity);
                durationTravel = Integer.valueOf((String) txtDurationLodgingCity.getText());
                reservationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("reserve", "click");
//                        progressDialog = new ProgressDialog(getApplicationContext());

//                        Util.showProgressDialog(getApplicationContext(),progressDialog);
//                        getLodgingReservation(itineraryData.getItineraryLodgingCity().get(position + 1).getCityId());

                        reservationPresenter.getLodgingList("list", itineraryData.getItineraryLodgingCity().get(position + 1).getCityId());

                        //getLodgingResevationRoom(itineraryData.getItineraryLodgingCity().get(position+1).getCityId());
                    }
                });

            }
        }));


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_list;
    }
//
//
//    private OkHttpClient setHttpClient() {
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
//                .build();
//        return okHttpClient;
//    }

//    public void getLodgingReservation(String cityId) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(setHttpClient())
//                .baseUrl("http://api.parsdid.com/iranplanner/app/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
//
//        Call<ResultLodgingList> callc = getJsonInterface.getLodgingReserve("list", cityId);
//        callc.enqueue(new Callback<ResultLodgingList>() {
//            @Override
//            public void onResponse(Call<ResultLodgingList> call, Response<ResultLodgingList> response) {
//                Log.e("result of intresting", "true");
//
//                if (response.body() != null && response.body().getResultLodging().size() != 0) {
//                    ResultLodgingList res = response.body();
//
//                    List<ResultLodging> resultLodgings = res.getResultLodging();
//                    Intent intent = new Intent(getApplicationContext(), ReservationHotelListActivity.class);
//                    intent.putExtra("resultLodgings", (Serializable) resultLodgings);
//                    intent.putExtra("startOfTravel", startOfTravel);
//                    intent.putExtra("durationTravel", durationTravel);
//                    startActivity(intent);
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "مرکز اقامتی وجود ندارد", Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResultLodgingList> call, Throwable t) {
//                Log.e("result of intresting", "false");
//                progressDialog.dismiss();
//
//            }
//        });
//    }


//    public void getLodgingResevationRoom(String cityID) {
////        getResultLodgingRoomList
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(setHttpClient())
//                .baseUrl("http://api.parsdid.com/iranplanner/app/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
//        cityID = "22649";
//        Call<ResultLodgingRoomList> callc = getJsonInterface.getResultLodgingRoomList("room", cityID, "", "");
//        callc.enqueue(new Callback<ResultLodgingRoomList>() {
//            @Override
//            public void onResponse(Call<ResultLodgingRoomList> call, Response<ResultLodgingRoomList> response) {
//                Log.e("result of intresting", "true");
//            }
//
//            @Override
//            public void onFailure(Call<ResultLodgingRoomList> call, Throwable t) {
//                Log.e("result of intresting", "false");
//            }
//        });
//    }


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

    @Override
    public void showLodgingList(ResultLodgingList resultLodgingList) {

        List<ResultLodging> resultLodgings = resultLodgingList.getResultLodging();
        Intent intent = new Intent(getApplicationContext(), ReservationHotelListActivity.class);
        intent.putExtra("resultLodgings", (Serializable) resultLodgings);
        intent.putExtra("startOfTravel", startOfTravel);
        intent.putExtra("durationTravel", durationTravel);
        startActivity(intent);


    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "مرکز اقامتی وجود ندارد", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }
}
