package com.iranplanner.tourism.iranplanner.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.mainscreen.DaggerMainScreenComponent;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenContract;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenModule;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenPresenter;
import com.iranplanner.tourism.iranplanner.mainscreen.mm;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.ramotion.foldingcell.FoldingCell;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import autoComplet.MyFilterableAdapterAttraction;
import autoComplet.MyFilterableAdapterCity;
import autoComplet.MyFilterableAdapterProvince;
import autoComplet.ReadJsonAttraction;
import autoComplet.ReadJsonProvince;
import autoComplet.readJsonCity;
import entity.Attraction;
import entity.City;
import entity.Province;
import entity.ResultItinerary;
import entity.ResultItineraryList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.utilMvp.App;


public class MainSearchFragment extends StandardFragment implements MainScreenContract.View, View.OnClickListener/*, Callback<ResultItineraryList>*/ {
    @Inject
    MainScreenPresenter mainPresenter;


    CardView card_view_city, card_view_privence, card_view_Iran, card_view_attraction;
    FoldingCell fcProvince, folding_cell_City_City, folding_cell_city, folding_cell_attraction;
    List<Province> provinces;
    String provinceName;
    String provinceId = null;
    AutoCompleteTextView textProvience;
    ProgressDialog progressDialog;
    Button searchOk_provience;
    List<Province> tempProvince;
    List<City> tempCity1, tempCity2, tempcity;
    AutoCompleteTextView fromCity_city, endCity_city, fromCity, fromCity_attraction, endAttraction;
    List<City> cities;
    Button searchOk_city_city, searchOk_city, searchOk_attraction;
    String cityCityFrom, cityEnd, cityFrom, cityFromAttraction;
    boolean checkfragment = false;
    LinearLayout city_city_layout, city_layout, province_layout, events_layout;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    ItineraryListFragment itineraryListFragment;
    List<Attraction> attractions;
    List<City> tempAttractionCity;
    List<Attraction> tempAttraction;
    String attractionEnd;
    Button mmActivity;

    public static MainSearchFragment newInstance() {
        MainSearchFragment fragment = new MainSearchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View view = inflater.inflate(R.layout.search_main_fragment, container, false);
        city_city_layout = (LinearLayout) view.findViewById(R.id.city_city_layout);
        city_layout = (LinearLayout) view.findViewById(R.id.city_layout);
        province_layout = (LinearLayout) view.findViewById(R.id.province_layout);
        events_layout = (LinearLayout) view.findViewById(R.id.events_layout);
        mmActivity = (Button) view.findViewById(R.id.mmActivity);
        mmActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "chi shod", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), mm.class);
                getActivity().startActivity(intent);


            }
        });
        //province
        textProvience = (AutoCompleteTextView) view.findViewById(R.id.textProvience);
        searchOk_provience = (Button) view.findViewById(R.id.searchOk_provience);
        tempProvince = autoCompleteProvince(textProvience);
        fcProvince = (FoldingCell) view.findViewById(R.id.folding_cell_province);
        // -------------attach click listener to folding cell
        fcProvince.setOnClickListener(this);
        searchOk_provience.setOnClickListener(this);
        //attraction
        folding_cell_attraction = (FoldingCell) view.findViewById(R.id.folding_cell_attraction);
        fromCity_attraction = (AutoCompleteTextView) view.findViewById(R.id.fromCity_attraction);
        endAttraction = (AutoCompleteTextView) view.findViewById(R.id.endAttraction);
        searchOk_attraction = (Button) view.findViewById(R.id.searchOk_attraction);
        tempAttractionCity = autoCompleteCity(fromCity_attraction);
        tempAttraction = autoCompleteAttraction(endAttraction);
        folding_cell_attraction.setOnClickListener(this);
        searchOk_attraction.setOnClickListener(this);
        // city_city
        fromCity_city = (AutoCompleteTextView) view.findViewById(R.id.fromCity_city);
        endCity_city = (AutoCompleteTextView) view.findViewById(R.id.endCity_city);
        searchOk_city_city = (Button) view.findViewById(R.id.searchOk_city_city);
        fromCity_city.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        tempCity1 = autoCompleteCity(fromCity_city);
        tempCity2 = autoCompleteCity(endCity_city);
        folding_cell_City_City = (FoldingCell) view.findViewById(R.id.folding_cell_City_City);
        // -------------attach click listener to folding cell
        folding_cell_City_City.setOnClickListener(this);
        searchOk_city_city.setOnClickListener(this);


        //city
        fromCity = (AutoCompleteTextView) view.findViewById(R.id.fromCity);
        searchOk_city = (Button) view.findViewById(R.id.searchOk_city);
        tempcity = autoCompleteCity(fromCity);
        folding_cell_city = (FoldingCell) view.findViewById(R.id.folding_cell_city);

        // -------------attach click listener to folding cell
        folding_cell_city.setOnClickListener(this);
        searchOk_city.setOnClickListener(this);

        //------------
//        card_view_city.setOnClickListener(this);
//        card_view_privence.setOnClickListener(this);
//        card_view_Iran.setOnClickListener(this);
//        card_view_attraction.setOnClickListener(this);

        //--------------


//        findMyLocation = (Button) view.findViewById(R.id.findMyLocation);
//        provinceTrip = (Button) view.findViewById(R.id.provinceTrip);
//        cityTrip = (Button) view.findViewById(R.id.cityTrip);
//        cityTrip.setOnClickListener(this);
//        provinceTrip.setOnClickListener(this);
//        findMyLocation.setOnClickListener(this);
        return view;
    }

    /*----Method to Check GPS is enable or disable ----- */
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("GPS شما فعال نیست. آیا تمایل به روشن کردن آن دارید")
                .setCancelable(false)
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {
        DaggerMainScreenComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().injectionMainSearchFragment(this);
        switch (v.getId()) {
//            case R.id.findMyLocation:
//                Intent intentMapActivity =new Intent(getContext(), MoreItemItineraryActivity.class);
//                startActivity(intentMapActivity);
//                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//                LocationListener locationListener = new LocationListinerGps(getContext());
//
//                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    buildAlertMessageNoGps();
//                }
//                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                }
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
//                break;
            case R.id.folding_cell_province:
                fcProvince.toggle(false);
                break;
            case R.id.folding_cell_City_City:
                folding_cell_City_City.toggle(false);
                break;
            case R.id.folding_cell_attraction:
                folding_cell_attraction.toggle(false);
                break;
            case R.id.searchOk_attraction:
                folding_cell_City_City.toggle(false);
                cityFromAttraction = returnCityId(fromCity_attraction, tempAttractionCity);
                attractionEnd = returnAttractionId(endAttraction, tempAttraction);
                if (cityFromAttraction != null && attractionEnd != null) {
                    getItinerary(cityFromAttraction, "0", false, attractionEnd, attractionEnd);
                    showProgressDialog();
//                    String offset = "0";
//                    mainPresenter.loadItinerary("list", "fa", cityCityFrom, "20", offset, cityEnd);
//                    showProgressDialog();
                } else {
                    Toast.makeText(getActivity(), "نام شهر یا جاذبه ثبت نشده است", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
                break;
            case R.id.searchOk_city:
                folding_cell_city.fold(false);
                cityFrom = returnCityId(fromCity, tempcity);
                cityEnd = cityFrom;
                if (cityFrom != null) {
//                    getItinerary(cityFrom, "0", false, cityFrom);
//                    showProgressDialog();
                    String offset = "0";
                    mainPresenter.loadItinerary("list", "fa", cityFrom, "20", offset, cityEnd);
                    showProgressDialog();
                } else {
                    Toast.makeText(getActivity(), "لطفا نام شهر را اصلاح کنید", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
                break;
            case R.id.folding_cell_city:
                folding_cell_city.toggle(false);
                break;
            case R.id.searchOk_city_city:
                folding_cell_City_City.fold(false);
                cityCityFrom = returnCityId(fromCity_city, tempCity1);
                cityEnd = returnCityId(endCity_city, tempCity2);
                if (endCity_city.getText() == null) {
                    cityEnd = "";
                }
                if (cityCityFrom != null) {
//                  getItinerary(cityCityFrom, "0", false, cityEnd);
                    String offset = "0";
                    mainPresenter.loadItinerary("list", "fa", cityCityFrom, "20", offset, cityEnd);
                    showProgressDialog();

                } else {
                    Toast.makeText(getActivity(), "لطفا نام شهر را اصلاح کنید", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
                break;
            case R.id.searchOk_provience:
                fcProvince.fold(false);
                provinceName = returnProvinceId(textProvience, tempProvince);
                if (provinceName != null) {
                    getItinerary(provinceName, "0");
                } else {
                    Toast.makeText(getActivity(), "لطفا نام استان را اصلاح کنید ", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
                break;
//            case R.id.card_view_Iran:
//                SearchCityCityFragment fragment = new SearchCityCityFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.SearchHolder, fragment);
//                ft.addToBackStack(null);
//                ft.commit();
//                break;
//            case R.id.card_view_privence:
//                SearchProvinceFragment fragmentProvience = new SearchProvinceFragment();
//                FragmentTransaction fts = getFragmentManager().beginTransaction();
//                fts.replace(R.id.SearchHolder, fragmentProvience);
//                fts.addToBackStack(null);
//                fts.commit();
//                break;
//
//            case R.id.card_view_attraction:
//                SearchCityAttractionFragment fragmentAttraction = new SearchCityAttractionFragment();
//                FragmentTransaction fta = getFragmentManager().beginTransaction();
//                fta.replace(R.id.SearchHolder, fragmentAttraction);
//                fta.addToBackStack(null);
//                fta.commit();
//                break;
//            case R.id.card_view_city:
//                SearchCityFragment searchCityFragment = new SearchCityFragment();
//                FragmentTransaction ftci = getFragmentManager().beginTransaction();
//                ftci.replace(R.id.SearchHolder, searchCityFragment);
//                ftci.addToBackStack(null);
//                ftci.commit();
//                break;
        }


    }

    @Override
    public void showItineraries(ResultItineraryList resultItineraryList) {
        List<ResultItinerary> data = resultItineraryList.getResultItinerary();
        itineraryListFragment = new ItineraryListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("resuliItineraryList", (Serializable) data);
        bundle.putString("fromWhere", "fromCityToCity");
        bundle.putString("endCity", cityEnd);
        bundle.putString("nextOffset", resultItineraryList.getStatistics().getOffsetNext().toString());
        itineraryListFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, itineraryListFragment);
        ft.addToBackStack(null);
        ft.commit();
        cityEnd = "";
        checkfragment = true;
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void showComplete() {
        progressDialog.dismiss();
    }

    public class GPSTracker extends Service implements LocationListener {

        private final Context mContext;

        // Flag for GPS status
        boolean isGPSEnabled = false;

        // Flag for network status
        boolean isNetworkEnabled = false;

        // Flag for GPS status
        boolean canGetLocation = false;

        Location location; // Location
        double latitude; // Latitude
        double longitude; // Longitude

        // The minimum distance to change Updates in meters
        private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

        // The minimum time between updates in milliseconds
        private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

        // Declaring a Location Manager
        protected LocationManager locationManager;

        public GPSTracker(Context context) {
            this.mContext = context;
            getLocation();
        }

        public Location getLocation() {
            try {
                locationManager = (LocationManager) mContext
                        .getSystemService(LOCATION_SERVICE);

                // Getting GPS status
                isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // Getting network status
                isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // No network provider is enabled
                } else {
                    this.canGetLocation = true;
                    if (isNetworkEnabled) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return null;
                        }
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("Network", "Network");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                    // If GPS enabled, get latitude/longitude using GPS Services
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            Log.d("GPS Enabled", "GPS Enabled");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return location;
        }


        /**
         * Stop using GPS listener
         * Calling this function will stop using GPS in your app.
         */
        public void stopUsingGPS() {
            if (locationManager != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.removeUpdates(GPSTracker.this);
            }
        }


        /**
         * Function to get latitude
         */
        public double getLatitude() {
            if (location != null) {
                latitude = location.getLatitude();
            }

            // return latitude
            return latitude;
        }


        /**
         * Function to get longitude
         */
        public double getLongitude() {
            if (location != null) {
                longitude = location.getLongitude();
            }

            // return longitude
            return longitude;
        }

        /**
         * Function to check GPS/Wi-Fi enabled
         *
         * @return boolean
         */
        public boolean canGetLocation() {
            return this.canGetLocation;
        }


        /**
         * Function to show settings alert dialog.
         * On pressing the Settings button it will launch Settings Options.
         */
        public void showSettingsAlert() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            // Setting Dialog Title
            alertDialog.setTitle("GPS is settings");

            // Setting Dialog Message
            alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

            // On pressing the Settings button.
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mContext.startActivity(intent);
                }
            });

            // On pressing the cancel button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }


        @Override
        public void onLocationChanged(Location location) {
        }


        @Override
        public void onProviderDisabled(String provider) {
        }


        @Override
        public void onProviderEnabled(String provider) {
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }


        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }
    }


    //province method
    public List<Province> autoCompleteProvince(AutoCompleteTextView textProvience) {
        ReadJsonProvince readJsonProvince = new ReadJsonProvince();
        provinces = readJsonProvince.getListOfProvience(getContext());
        MyFilterableAdapterProvince adapter = new MyFilterableAdapterProvince(getContext(), android.R.layout.simple_list_item_1, provinces);
        textProvience.setAdapter(adapter);
        return provinces;
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public String returnProvinceId(AutoCompleteTextView textView, List<Province> provinceList) {

        for (Province p : provinceList) {
            Log.e("Province", p.getProvinceName());
            if (p.getProvinceName().equals(textView.getText().toString())) {
                provinceId = p.getProvinceId();
                showProgressDialog();
            }
        }
        return provinceId;
    }

    public void getItinerary(final String provinceId, String offset) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysFromProvince("searchprovince", provinceId, offset);
        call.enqueue(new Callback<ResultItineraryList>() {
            @Override
            public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
                if (response.body() != null) {
                    Log.e("get result from server", response.body().toString());
                    ResultItineraryList jsonResponse = response.body();
                    List<ResultItinerary> data = jsonResponse.getResultItinerary();
                    itineraryListFragment = new ItineraryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resuliItineraryList", (Serializable) data);
                    bundle.putString("fromWhere", "fromProvince");
                    bundle.putString("provinceId", provinceId);
                    bundle.putString("endCity", "");
                    bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
                    itineraryListFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, itineraryListFragment);
                    ft.addToBackStack("TAG_FRAGMENT");
                    ft.commit();
                    progressDialog.dismiss();
//                    city_city_layout.setVisibility(View.GONE);
//                    city_layout.setVisibility(View.GONE);
//                    events_layout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
//        loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);
                progressDialog.dismiss();

                checkfragment = true;
            }

            @Override
            public void onFailure(Call<ResultItineraryList> call, Throwable t) {
                Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
                Log.e(" error from server", "error");
                progressDialog.dismiss();
            }
        });
    }

    //-------------------city_city
    public List<City> autoCompleteCity(AutoCompleteTextView city) {
        readJsonCity readJsonCity = new readJsonCity();
        cities = readJsonCity.getListOfCity(getContext());
        MyFilterableAdapterCity adapter = new MyFilterableAdapterCity(getContext(), android.R.layout.simple_list_item_1, cities);
        city.setAdapter(adapter);
        return cities;
    }

    public String returnCityId(AutoCompleteTextView textView, List<City> cityList) {
        String cityId = null;
        for (City c : cityList) {
            Log.e("city", c.getCityName());
            if (c.getCityName().equals(textView.getText().toString())) {
                cityId = c.getCityId();
            }
        }
        return cityId;
    }

    public void getItinerary(String cityId, String offset, boolean checkfragment, String toCity) {


        this.checkfragment = checkfragment;
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset, toCity);
        call.enqueue(new Callback<ResultItineraryList>() {
            @Override
            public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
                if (response.body() != null) {
                    ResultItineraryList jsonResponse = response.body();
                    List<ResultItinerary> data = jsonResponse.getResultItinerary();
                    itineraryListFragment = new ItineraryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resuliItineraryList", (Serializable) data);
                    bundle.putString("fromWhere", "fromCityToCity");
                    bundle.putString("endCity", cityEnd);
                    bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
                    itineraryListFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, itineraryListFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    cityEnd = "";
//                    checkfragment = true;
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResultItineraryList> call, Throwable t) {
                Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
                Log.e(" error from server", "error");
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onBackPressed() {

        return super.onBackPressed();
    }

    public List<Attraction> autoCompleteAttraction(AutoCompleteTextView city) {
        ReadJsonAttraction readJsonAttraction = new ReadJsonAttraction();
        attractions = readJsonAttraction.getListOfAttractin(getContext());
        MyFilterableAdapterAttraction adapter = new MyFilterableAdapterAttraction(getContext(), android.R.layout.simple_list_item_1, attractions);
        city.setAdapter(adapter);
        return attractions;
    }

    public String returnAttractionId(AutoCompleteTextView textView, List<Attraction> attractionList) {
        String attractionId = null;
        for (Attraction attraction : attractionList) {
            Log.e("city", attraction.getAttractionName());
            if (attraction.getAttractionName().equals(textView.getText().toString())) {
                attractionId = attraction.getAttractionId();
            }
        }
        return attractionId;
    }

    public void getItinerary(String cityId, String offset, boolean checkfragment, String attractionId, final String attractionEnd) {
        this.checkfragment = checkfragment;
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysAttraction("searchattractioncity", "fa", cityId, "", offset, attractionId);
        call.enqueue(new Callback<ResultItineraryList>() {
            @Override
            public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
                if (response.body() != null) {
                    ResultItineraryList jsonResponse = response.body();
                    List<ResultItinerary> data = jsonResponse.getResultItinerary();
                    ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resuliItineraryList", (Serializable) data);
                    bundle.putString("fromWhere", "fromAttraction");
                    bundle.putString("cityFrom", cityFrom);
                    bundle.putString("attractionId", attractionEnd);
                    bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
                    itineraryListFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, itineraryListFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(getContext(), "برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResultItineraryList> call, Throwable t) {
                Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
                Log.e(" error from server", "error");
                progressDialog.dismiss();
            }
        });
    }
}
