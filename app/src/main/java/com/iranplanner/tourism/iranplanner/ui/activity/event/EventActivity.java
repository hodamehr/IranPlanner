package com.iranplanner.tourism.iranplanner.ui.activity.event;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.ItineraryLodgingCity;
import entity.ResultEvent;
import entity.ResultLodging;

public class EventActivity extends StandardActivity {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Marker marker;
    List<ResultEvent> resultEvent;

    private TextView tvEventStatus, tvEventName, tvEventCity, tvEventSubTitle, tvEventHoldingDate, tvEventVisitationHour, tvEventAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getExtra();
        initToolbar();
        init();
    }

    private void getExtra() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        resultEvent = (List<ResultEvent>) bundle.getSerializable("ResultEvent");
    }

    private void initToolbar() {
        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);

        collapsingToolbarLayout.setTitle("  ");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
//        mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//
//        mapFragment.getMapAsync(this);

        tvEventStatus = (TextView) findViewById(R.id.eventStatusTv);
        tvEventName = (TextView) findViewById(R.id.eventTitleTv);
        tvEventCity = (TextView) findViewById(R.id.eventCityTv);
        tvEventSubTitle = (TextView) findViewById(R.id.eventSubTitleTv);
        tvEventHoldingDate = (TextView) findViewById(R.id.eventHoldingDateTv);
        tvEventVisitationHour = (TextView) findViewById(R.id.eventVisitationHourTv);
        tvEventAddress = (TextView) findViewById(R.id.eventAddressTv);

//        tvEventStatus.setText(resultEvent.get(0).getEventInfo().get);
        tvEventName.setText(resultEvent.get(0).getEventInfo().getEventTitle());
        tvEventCity.setText(resultEvent.get(0).getEventInfo().getEventCityTitle());
        tvEventSubTitle.setText(resultEvent.get(0).getEventInfo().getEventBody());
        tvEventHoldingDate.setText(resultEvent.get(0).getEventInfo().getEventDateStart());
        tvEventVisitationHour.setText(resultEvent.get(0).getEventInfo().getEventDateDuration());
        tvEventAddress.setText(resultEvent.get(0).getEventInfo().getEventAddress());
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.getUiSettings().setAllGesturesEnabled(false);
//        mMap.getUiSettings().setMapToolbarEnabled(true);
//        mMap.getUiSettings().setZoomControlsEnabled(false);
//
//        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
//        Double lan = 35.6892;
//        Double lon = 51.3890;
//
//        marker = mMap.addMarker(markerOptions
//                .position(new LatLng(lan, lon))
//                .title("Amin is Awesome")
//        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lan, lon), 15.0f));
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event;
    }
}
