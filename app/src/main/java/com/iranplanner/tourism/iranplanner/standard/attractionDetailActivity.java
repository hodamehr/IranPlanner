package com.iranplanner.tourism.iranplanner.standard;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iranplanner.tourism.iranplanner.R;

import entity.ResultItineraryAttraction;
import tools.Util;

public class attractionDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ResultItineraryAttraction attraction;
    TextView attractionName, attractionPlace, textTimeDuration, textEntranceFee, attractionType, textBody;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_attraction_detail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        attractionName = (TextView) findViewById(R.id.attractionName);
        attractionPlace = (TextView) findViewById(R.id.attractionPlace);
        textTimeDuration = (TextView) findViewById(R.id.textTimeDuration);
        textEntranceFee = (TextView) findViewById(R.id.textEntranceFee);
        attractionType = (TextView) findViewById(R.id.attractionType);
        textBody = (TextView) findViewById(R.id.textBody);
        ImageView imageAttraction = (ImageView) findViewById(R.id.imageAttraction);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        attraction = (entity.ResultItineraryAttraction) bundle.getSerializable("ResultItineraryAttraction");
        attractionName.setText(attraction.getAttractionTitle());
        textBody.setText(Html.fromHtml(attraction.getAttarctionBody()));
        attractionPlace.setText(attraction.getProvinceTitle() + " - " + attraction.getCityTitle());
        int totalMinute = Integer.parseInt(attraction.getAttarctionEstimatedTime());
        Util.convertMinuteToHour(totalMinute, textTimeDuration);

        if (attraction.getAttractionPrice() == null) {
            textEntranceFee.setText("ندارد");
        } else {
            textEntranceFee.setText(attraction.getAttractionPrice().toString());
        }
        attractionType.setText(attraction.getAttarctionItineraryTypeTitle());
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        attraction.getAttractionPositionLat();
        attraction.getAttractionPositionOn();
        float lan = Float.valueOf(attraction.getAttractionPositionLat());
        float lon = Float.valueOf(attraction.getAttractionPositionOn());
        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));

        marker = mMap.addMarker(markerOptions
                .position(new LatLng(lan, lon))
                .title(attraction.getCityTitle())
                .snippet(":)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lan, lon), 15.0f));


    }


}
