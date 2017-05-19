package com.iranplanner.tourism.iranplanner.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

import autoComplet.MyFilterableAdapterCity;
import autoComplet.readJsonCity;
import entity.City;
import entity.ResultItineraryList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;

public class SearchCityFragment extends StandardFragment implements Callback<ResultItineraryList> {
    List<City> cities;
    String cityFrom;
    String cityEnd;
    AutoCompleteTextView fromCity_city;
    boolean checkfragment = false;
    ProgressDialog progressDialog;
    ProgressBar waitingForData;

    public static SearchCityFragment newInstance() {
        SearchCityFragment fragment = new SearchCityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        bara inke keybord bala nayad
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.fragment_search_city, container, false);
        fromCity_city = (AutoCompleteTextView) view.findViewById(R.id.fromCity_city);
        Button searchOk_city = (Button) view.findViewById(R.id.searchOk_city);

        final List<City> temp1 = autoComplete(fromCity_city);

        searchOk_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //// TODO: 17/01/2017 inja code asli hast
                cityFrom = returnCityId(fromCity_city, temp1);
                if (cityFrom != null ) {
                    getItinerary(cityFrom, "0", false,cityFrom);
                    showProgressDialog();

                } else {
                    Toast.makeText(getActivity(), "لطفا نام شهر را اصلاح کنید", Toast.LENGTH_SHORT).show();
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

    public List<City> autoComplete(AutoCompleteTextView city) {
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


    public void getItinerary(String cityId, String offset, boolean checkfragment,String toCity) {
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
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset,toCity);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
//        Log.e("get result from server", response.body().toString());
//        if (response.body() != null) {
//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> com.iranplanner.tourism.iranplanner.di.data = jsonResponse.getResultItinerary();
//            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("resuliItineraryList", (Serializable) com.iranplanner.tourism.iranplanner.di.data);
//            bundle.putString("fromWhere", "fromCity");
//            bundle.putString("cityFrom",cityFrom);
//            bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
//            itineraryListFragment.setArguments(bundle);
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.replace(R.id.SearchHolder, itineraryListFragment);
//            ft.addToBackStack(null);
//            ft.commit();
//            checkfragment = true;
//            progressDialog.dismiss();
//        }else {
//            Toast.makeText(getContext(), "برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
//        }
        progressDialog.dismiss();
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        Log.e(" error from server", "error");
        progressDialog.dismiss();

    }

}
