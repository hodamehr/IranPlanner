package com.iranplanner.tourism.iranplanner.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import autoComplet.MyFilterableAdapterAttraction;
import autoComplet.MyFilterableAdapterCity;
import autoComplet.ReadJsonAttraction;
import autoComplet.readJsonCity;
import entity.Attraction;
import entity.City;
import entity.ResultItinerary;
import entity.ResultItineraryList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;

public class SearchCityAttractionFragment extends StandardFragment implements Callback<ResultItineraryList> {
    List<City> cities;
    List<Attraction> attractions;
    String cityFrom;
    String attractionEnd;
    AutoCompleteTextView fromCity_attraction, endAttraction;
    boolean checkfragment = false;
    ProgressDialog progressDialog;
    ProgressBar waitingForData;

    public static SearchCityAttractionFragment newInstance() {
        SearchCityAttractionFragment fragment = new SearchCityAttractionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        bara inke keybord bala nayad
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View view = inflater.inflate(R.layout.fragment_search_city_attraction, container, false);
        fromCity_attraction = (AutoCompleteTextView) view.findViewById(R.id.fromCity_attraction);
        endAttraction = (AutoCompleteTextView) view.findViewById(R.id.endAttraction);
        Button searchOk_attraction = (Button) view.findViewById(R.id.searchOk_attraction);

        final List<City> temp1 = autoCompleteCity(fromCity_attraction);
        final List<Attraction> temp2 = autoCompleteAttraction(endAttraction);

        searchOk_attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //// TODO: 17/01/2017 inja code asli hast
                cityFrom = returnCityId(fromCity_attraction, temp1);
                attractionEnd = returnAttractionId(endAttraction, temp2);
                if (cityFrom != null && attractionEnd != null) {
                    getItinerary(cityFrom, "0", false,attractionEnd);
                    showProgressDialog();
                } else {
                    Toast.makeText(getActivity(), "نام شهر یا جاذبه ثبت نشده است", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
            }
        });
        return view;
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public List<City> autoCompleteCity(AutoCompleteTextView city) {
        readJsonCity readJsonCity = new readJsonCity();
        cities = readJsonCity.getListOfCity(getContext());
        MyFilterableAdapterCity adapter = new MyFilterableAdapterCity(getContext(), android.R.layout.simple_list_item_1, cities);
        city.setAdapter(adapter);
        return cities;
    }

    public List<Attraction> autoCompleteAttraction(AutoCompleteTextView city) {
        ReadJsonAttraction readJsonAttraction = new ReadJsonAttraction();
        attractions = readJsonAttraction.getListOfAttractin(getContext());
        MyFilterableAdapterAttraction adapter = new MyFilterableAdapterAttraction(getContext(), android.R.layout.simple_list_item_1, attractions);
        city.setAdapter(adapter);
        return attractions;
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
//    @GET("api-itinerary.php?action=searchattractioncity&lang=fa&from=342&limit=10&offset=0&attraction=id")

    public void getItinerary(String cityId, String offset, boolean checkfragment,String attractionId) {
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
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysAttraction("searchattractioncity", "fa", cityId, "", offset,attractionId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
//        Log.e("get result from server", response.body().toString());
        if (response.body() != null) {
            ResultItineraryList jsonResponse = response.body();
            List<ResultItinerary> data = jsonResponse.getResultItinerary();
            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("resuliItineraryList", (Serializable) data);
            bundle.putString("fromWhere", "fromAttraction");
            bundle.putString("cityFrom",cityFrom);
            bundle.putString("attractionId", attractionEnd);
            bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
            itineraryListFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, itineraryListFragment);
            ft.addToBackStack(null);
            ft.commit();
            checkfragment = true;
            progressDialog.dismiss();

        }else {
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

}
