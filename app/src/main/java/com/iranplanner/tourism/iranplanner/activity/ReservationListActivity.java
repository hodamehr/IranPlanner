package com.iranplanner.tourism.iranplanner.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.ItineraryListAdapter;
import com.iranplanner.tourism.iranplanner.adapter.ReseveDateListAdapter;
import com.iranplanner.tourism.iranplanner.adapter.ShowDetailItineraryAdapter;
import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import entity.ResultItinerary;
import entity.ResultItineraryAttraction;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class ReservationListActivity extends StandardActivity implements DataTransferInterface {
    private ReseveDateListAdapter adapter;
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
        ResultItinerary itineraryData= (ResultItinerary) extras.getSerializable("itineraryData");
        Date startOfTravel= (Date) extras.getSerializable("startOfTravel");

        adapter = new ReseveDateListAdapter(ReservationListActivity.this, this,itineraryData, getApplicationContext(), R.layout.fragment_itinerary_item,startOfTravel);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {


            }
        }));


    }

    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
