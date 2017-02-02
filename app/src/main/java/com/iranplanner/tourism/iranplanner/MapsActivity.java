package com.iranplanner.tourism.iranplanner;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.iranplanner.tourism.iranplanner.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.activity.ShowAttractionActivity;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.ItineraryLodgingCity;
import entity.ItineraryPercentage;
import entity.ResultItinerary;
import entity.ResultItineraryAttraction;
import entity.ResultItineraryAttractionList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.MapDirection;
import tools.Util;

public class MapsActivity extends StandardActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, Callback<ResultItineraryAttractionList> {

    private GoogleMap mMap;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    ResultItinerary itineraryData;
    private String itineraryId;
    List<ResultItineraryAttraction> itineraryActionList;
    CircularProgressBar progress1;
    CircularProgressBar progress2;
    CircularProgressBar progress3;
    TextView textTpeTravel1;
    TextView textTpeTravel2;
    TextView textTpeTravel3;
    List<Marker> markers;
    TextView textPercentage1;
    TextView textPercentage2;
    TextView textPercentage3;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        itineraryData = (ResultItinerary) bundle.getSerializable("itineraryData");
        String dutation = bundle.getString("dutation");
//        byte[] bytes = intent.getByteArrayExtra("BMP");
//        bundle.getByteArray("BMP");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        setContentView(R.layout.fragment_itinerary_item_more);
        TextView txtItinerary_attraction_Difficulty = (TextView) findViewById(R.id.txtItinerary_attraction_Difficulty);
        TextView txtItinerary_attraction_type = (TextView) findViewById(R.id.txtItinerary_attraction_type);
        TextView txtItinerary_count_attraction = (TextView) findViewById(R.id.txtItinerary_count_attraction);
        TextView itineraryDuration = (TextView) findViewById(R.id.itineraryDuration);
        TextView itinerary_name = (TextView) findViewById(R.id.itinerary_name);
        itineraryDuration.setText(dutation);
        itinerary_name.setText(itineraryData.getItineraryFromCityName() + "-" + itineraryData.getItineraryToCityName());
        TextView showItinerys = (TextView) findViewById(R.id.showItinerys);
        textTpeTravel1 = (TextView) findViewById(R.id.textTpeTravel1);
        textTpeTravel2 = (TextView) findViewById(R.id.textTpeTravel2);
        textTpeTravel3 = (TextView) findViewById(R.id.textTpeTravel3);
        progress1 = (CircularProgressBar) findViewById(R.id.progress1);
        progress2 = (CircularProgressBar) findViewById(R.id.progress2);
        progress3 = (CircularProgressBar) findViewById(R.id.progress3);
        textPercentage1 = (TextView) findViewById(R.id.textPercentage1);
        textPercentage2 = (TextView) findViewById(R.id.textPercentage2);
        textPercentage3 = (TextView) findViewById(R.id.textPercentage3);
        ImageView imgItineraryListMore = (ImageView) findViewById(R.id.imgItineraryListMore);

        if (itineraryData.getItineraryImgUrl() != null) {
            String url = itineraryData.getItineraryImgUrl();
//            Glide.with(context).load(url)   .into(viewHolder.imgItineraryList);

            imgItineraryListMore.setVisibility(View.VISIBLE);
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
                    .into(imgItineraryListMore)
            ;
        } else {
            Glide.clear(imgItineraryListMore);
            imgItineraryListMore.setImageDrawable(null);

        }

//        imgItineraryListMore.setImageBitmap(bmp);
        /*setMonth();*/
        SetPercentage();

        txtItinerary_attraction_Difficulty.setText(itineraryData.getItineraryDifficulty().getItineraryDifficultyGroup());
        txtItinerary_count_attraction.setText(Util.persianNumbers(itineraryData.getItineraryCountAttraction()) + " مکان دیدنی");
        itineraryId = itineraryData.getItineraryId();

        showItinerys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("open itinerarylist", "open");
                getAttraction(itineraryId);
                showProgressDialog();
//                progressLoadingHolderItineraryMore.setVisibility(View.VISIBLE);

            }
        });
        //-------------------map
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Initializing
        MarkerPoints = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MapsActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    /*   private void setMonth() {

           Button farvardin = (Button) findViewById(R.id.farvardin);
           Button ordibehesht = (Button) findViewById(R.id.ordibehesht);
           Button khordad = (Button) findViewById(R.id.khordad);
           Button tir = (Button) findViewById(R.id.tir);
           Button mordad = (Button) findViewById(R.id.mordad);
           Button shahrivar = (Button) findViewById(R.id.shahrivar);
           Button mehr = (Button) findViewById(R.id.mehr);
           Button aban = (Button) findViewById(R.id.aban);
           Button azar = (Button) findViewById(R.id.azar);
           Button dey = (Button) findViewById(R.id.dey);
           Button bahman = (Button) findViewById(R.id.bahman);
           Button esfand = (Button) findViewById(R.id.esfand);
           for (ItinerarySeasson itinerarySeasson : itineraryData.getItinerarySeasson()) {
               String month = itinerarySeasson.getMonthName();
               switch (month) {
                   case "1":
                       farvardin.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "2":
                       ordibehesht.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "3":
                       khordad.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "4":
                       tir.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "5":
                       mordad.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "6":
                       shahrivar.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "7":
                       mehr.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "8":
                       aban.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "9":
                       azar.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "10":
                       dey.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "11":
                       bahman.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "12":
                       esfand.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   default:
                       farvardin.setBackground(getDrawable(R.drawable.circle_tohi));
                       break;
               }
           }


       }*/
    private void SetPercentage() {
        ArrayList<String> addtypes = new ArrayList<>();
        ArrayList<String> addtypesper = new ArrayList<>();
        ArrayList<String> deleteType = new ArrayList<>();
        ArrayList<String> deleteTypeper = new ArrayList<>();
        deleteType.add("مذهبی");
        deleteType.add("تاریخی و فرهنگی");
        deleteType.add("طبیعی");
        deleteType.add("تفریحی و ورزشی");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        for (ItineraryPercentage p : itineraryData.getItineraryPercentage()) {
            addtypes.add(p.getItineraryAttractionType());
            addtypesper.add(p.getItineraryAttractionTypePercentage());
            if (deleteType.contains(p.getItineraryAttractionType())) {
                deleteType.remove(p.getItineraryAttractionType());
            }
        }
        if (addtypes.size() <= 3) {
            for (int i = addtypes.size(); i < 3; i++) {
                addtypes.add(deleteType.get(i - 1));
                addtypesper.add(deleteTypeper.get(i - 1));
            }
        }

        textTpeTravel1.setText(addtypes.get(0));
        textTpeTravel2.setText(addtypes.get(1));
        textTpeTravel3.setText(addtypes.get(2));
        progress1.setProgress(Float.valueOf(addtypesper.get(0)));
        progress2.setProgress(Float.valueOf(addtypesper.get(1)));
        progress3.setProgress(Float.valueOf(addtypesper.get(2)));
        float perc1 = Float.valueOf(addtypesper.get(0));
        float perc2 = Float.valueOf(addtypesper.get(1));
        float perc3 = Float.valueOf(addtypesper.get(2));

        textPercentage1.setText((Util.persianNumbers(String.valueOf((int) perc1)) + "%"));
        textPercentage2.setText((Util.persianNumbers(String.valueOf((int) perc2)) + "%"));
        textPercentage3.setText((Util.persianNumbers(String.valueOf((int) perc3)) + "%"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //------------- no zoom
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
        if (MarkerPoints.size() > 1) {
            MarkerPoints.clear();
            mMap.clear();
        }
        MapDirection mapDirection = new MapDirection(mMap, getApplicationContext(), lodgingCities, MarkerPoints);


        // Already two locations

        markers = mapDirection.readytoDirect();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("map is ckicked", "true");
                Intent intent = new Intent(getApplicationContext(), MapFullActivity.class);
                intent.putExtra("itineraryData", (Serializable) itineraryData);
                startActivity(intent);

            }
        });

    }

    //camera zoom to all of points
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus == true) {
            RelativeLayout myLinearLayout = (RelativeLayout) findViewById(R.id.mapHolder);
            int width = myLinearLayout.getWidth();
            int height = myLinearLayout.getHeight();
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            mMap.animateCamera(cu);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        //Place current location marker
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }


    public void getAttraction(String itineraryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface stackOverflowAPI = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryAttractionList> call = stackOverflowAPI.getItineraryAttractionList("attraction", "fa", itineraryId);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ResultItineraryAttractionList> call, Response<ResultItineraryAttractionList> response) {
        Log.e("ok", "ResultItineraryAttractionList");

        if (response.body() != null) {
            ResultItineraryAttractionList jsonResponse = response.body();
            itineraryActionList = jsonResponse.getResultItineraryAttraction();
            progressDialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), ShowAttractionActivity.class);
            intent.putExtra("ResultItineraryAttraction", (Serializable) itineraryActionList);
            startActivity(intent);

//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> data = jsonResponse.getResultItinerary();

//            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("ResultItineraryAttraction", (Serializable) itineraryActionList);
//            itineraryListFragment.setArguments(bundle);
//            loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);

//            for (ResultItineraryAttraction attraction : itineraryActionList) {
//                for (int a = 0; a <= itineraryActionList.size(); a++) {
//                    if (attraction.getItineraryDayplanName() == String.valueOf(a)) {
//
//                    }
//                }
//            }
        } else {
            Log.e("Responce body", "null");
        }

    }

    @Override
    public void onFailure(Call<ResultItineraryAttractionList> call, Throwable t) {

        Log.e("Responce  body", "itinerary detail null");
        Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();

    }
}
