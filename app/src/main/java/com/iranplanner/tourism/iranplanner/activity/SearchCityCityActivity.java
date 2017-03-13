package com.iranplanner.tourism.iranplanner.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import java.io.Serializable;
import java.util.List;

import autoComplet.MyFilterableAdapter;
import autoComplet.readJsonCity;
import entity.City;
import entity.ResultItinerary;
import entity.ResultItineraryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.FontAwesome;
import tools.Util;

/**
 * Created by h.vahidimehr on 11/01/2017.
 */

public class SearchCityCityActivity extends StandardActivity implements Callback<ResultItineraryList> {
    List<City> cities;
    String cityFrom;
    String cityEnd;
    AutoCompleteTextView fromCity_city, endCity_city;
    boolean checkfragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_city_city);
        fromCity_city = (AutoCompleteTextView) findViewById(R.id.fromCity_city);
        endCity_city = (AutoCompleteTextView) findViewById(R.id.endCity_city);
        Typeface font = Util.getFontAwesome(getApplicationContext());
//         = Typeface.createFromAsset( getAssets(), "fonts/fontawesome-webfont.ttf" );

        Button searchOk_city = (Button) findViewById(R.id.searchOk_city);

        final List<City> temp1 = autoComplete(fromCity_city);
        final List<City> temp2 = autoComplete(endCity_city);

        searchOk_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //-----close keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//                imm.hideSoftInputFromWindow(EditTextName.getWindowToken(), 0);
                //--------------
                getItinerary("342", "0", false,"342" );

                //// TODO: 17/01/2017 inja code asli hast 
//                cityFrom = returnCityId(fromCity_city, temp1);
//                cityEnd = returnCityId(endCity_city, temp2);
//                if (cityFrom != null && cityEnd != null) {
////                    getItinerary(cityFrom, "0", false);
//                } else {
//                    Toast.makeText(getApplicationContext(), "please correct name of city ", Toast.LENGTH_SHORT).show();
//                }
//                Log.d("search ok clicked", "true");
            }
        });
    }

    public List<City> autoComplete(AutoCompleteTextView city) {
        readJsonCity readJsonCity = new readJsonCity();
        cities = readJsonCity.getListOfCity(getApplicationContext());
        MyFilterableAdapter adapter = new MyFilterableAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cities);
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


    public void getItinerary(String cityId, String offset, boolean checkfragment,String toCity) {
        this.checkfragment = checkfragment;
////        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(layoutManager);

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        OkHttpClient client = new OkHttpClient();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset,toCity);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
        if (response.body().getStatus().getStatus().equals("200")) {
            Log.e("get result from server", response.body().toString());
            ResultItineraryList jsonResponse = response.body();
            List<ResultItinerary> data = jsonResponse.getResultItinerary();
            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("resuliItineraryList", (Serializable) data);
            itineraryListFragment.setArguments(bundle);
            loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);
            checkfragment = true;
        }
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "خطا در برقراری ارتباط با اینترنت", Toast.LENGTH_LONG).show();
        Log.e(" error from server", "error");
    }
}
