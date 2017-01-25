package com.iranplanner.tourism.iranplanner.standard;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iranplanner.tourism.iranplanner.R;

import entity.ResultItineraryAttraction;
import tools.Util;

public class attractionDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ResultItineraryAttraction attraction;
    TextView attractionName, attractionPlace, textTimeDuration, textEntranceFee, attractionType, textBody;

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
        attraction.getAttractionPositionLat();
        attraction.getAttractionPositionOn();

        LatLng point = new LatLng(Float.valueOf(attraction.getAttractionPositionLat()), Float.valueOf(attraction.getAttractionPositionOn()));

//        mMap.addMarker(new MarkerOptions().position(point).title(attraction.getCityTitle()));

        //--------------------
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,12));

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
//
//// Zoom in, animating the camera.
//        mMap.animateCamera(CameraUpdateFactory.zoomIn());
//
//// Zoom out to zoom level 10, animating with a duration of 2 seconds.
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//
//// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
//        CameraPosition cameraPosition = new CameraPosition.Builder()
////                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
//                .zoom(17)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
