package com.iranplanner.tourism.iranplanner.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinpany.core.android.widget.CTouchyWebView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iranplanner.tourism.iranplanner.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.ItineraryLodgingCity;
import entity.ResultItineraryAttraction;
import tools.Util;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class attractionDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ResultItineraryAttraction attraction;
    TextView attractionName, attractionPlace, textTimeDuration, textEntranceFee, attractionType, textBody;
    Marker marker;
    protected CTouchyWebView contentFullDescription;
    ImageView imageTypeAttraction;
// override font iransans
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// for Override font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansMobile.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.fragment_attraction_detail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        contentFullDescription = (CTouchyWebView) findViewById(R.id.contentFullDescription);
        contentFullDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        contentFullDescription.setLongClickable(false);
        contentFullDescription.setHapticFeedbackEnabled(false);
        attractionName = (TextView) findViewById(R.id.attractionName);
        attractionPlace = (TextView) findViewById(R.id.attractionPlace);
        textTimeDuration = (TextView) findViewById(R.id.textTimeDuration);
        textEntranceFee = (TextView) findViewById(R.id.textEntranceFee);
        attractionType = (TextView) findViewById(R.id.attractionType);
        imageTypeAttraction = (ImageView) findViewById(R.id.imageTypeAttraction);
        ImageView imageAttraction = (ImageView) findViewById(R.id.imageAttraction);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        attraction = (entity.ResultItineraryAttraction) bundle.getSerializable("ResultItineraryAttraction");
        attractionName.setText(attraction.getAttractionTitle());
        attractionPlace.setText(attraction.getProvinceTitle() + " - " + attraction.getCityTitle());
        int totalMinute = Integer.parseInt(attraction.getAttarctionEstimatedTime());
        Util.convertMinuteToHour(totalMinute, textTimeDuration);
        String htmlText = " %s ";
        String myData = attraction.getAttarctionBody();
        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/IRANSansMobile.ttf\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + myData + pas;
        contentFullDescription.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);


//        contentFullDescription.loadData(String.format(htmlText, myData), "text/html", "utf-8");
//        contentFullDescription.loadData(String.format(htmlText, myData), "text/html; charset=utf-8", "utf-8");
//        contentFullDescription.loadDataWithBaseURL("file:///android_asset/", attraction.json.getAttarctionBody(), "text/html", "utf-8", null);

        if (attraction.getItineraryImgUrl() != null) {
            String url = attraction.getItineraryImgUrl();
            Glide.with(getApplicationContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(new RequestListener<String, GlideDrawable>() {

                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            //// TODO: 22/01/2017  get defeult picture
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageAttraction);

        } else {
            Glide.clear(imageAttraction);
            imageAttraction.setImageDrawable(null);
        }
        if (attraction.getAttractionPrice() == null) {
            textEntranceFee.setText("رایگان");
        } else {
            textEntranceFee.setText(Util.persianNumbers(attraction.getAttractionPrice().toString()) + "تومان");
        }
        attractionType.setText(attraction.getAttarctionItineraryTypeTitle());
        if(attraction.getAttarctionItineraryTypeId().equals("2930")){
            imageTypeAttraction.setImageDrawable(getResources().getDrawable(R.mipmap.ic_religious));
        }else if(attraction.getAttarctionItineraryTypeId().equals("2931")){
            imageTypeAttraction.setImageDrawable(getResources().getDrawable(R.mipmap.ic_nature));
        }else if(attraction.getAttarctionItineraryTypeId().equals("2932")){
            imageTypeAttraction.setImageDrawable(getResources().getDrawable(R.mipmap.ic_history));
        }
        else if(attraction.getAttarctionItineraryTypeId().equals("2933")){
            imageTypeAttraction.setImageDrawable(getResources().getDrawable(R.mipmap.ic_entertainment));
        }
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

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("map is ckicked", "true");
                Intent intent = new Intent(getApplicationContext(), MapFullActivity.class);
                ItineraryLodgingCity i = new ItineraryLodgingCity();
                i.setCityPositionLat(attraction.getAttractionPositionLat());
                i.setCityPositionLon(attraction.getAttractionPositionOn());
                List<ItineraryLodgingCity> lodgingCities = new ArrayList<ItineraryLodgingCity>();
                lodgingCities.add(i);
                intent.putExtra("lodgingCities", (Serializable) lodgingCities);
                intent.putExtra("attraction", (Serializable) attraction);
                startActivity(intent);
            }
        });
    }




}
