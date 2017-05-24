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
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import autoComplet.MyFilterableAdapterProvince;
import autoComplet.ReadJsonProvince;
import entity.Province;
import entity.ResultItineraryList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.Config;
import server.getJsonInterface;

public class SearchProvinceFragment extends StandardFragment implements Callback<ResultItineraryList> {

    List<Province> provinces;
    String provinceName;
    String provinceId = null;
    AutoCompleteTextView textProvience;
    boolean checkfragment = false;
    ProgressDialog progressDialog;

    public static SearchCityCityFragment newInstance() {
        SearchCityCityFragment fragment = new SearchCityCityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View view = inflater.inflate(R.layout.fragment_provience_search, container, false);
        textProvience = (AutoCompleteTextView) view.findViewById(R.id.textProvience);
        Button searchOk_provience = (Button) view.findViewById(R.id.searchOk_provience);
        final List<Province> temp1 = autoComplete(textProvience);

        searchOk_provience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //// TODO: 17/01/2017 inja code asli hast
                provinceName = returnProvinceId(textProvience, temp1);
                if (provinceName != null) {
                    getItinerary(provinceName, "0");
                } else {
                    Toast.makeText(getActivity(), "please correct name of city ", Toast.LENGTH_SHORT).show();
                }
                Log.d("search ok clicked", "true");
            }
        });
        return view;
    }

    public List<Province> autoComplete(AutoCompleteTextView textProvience) {
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

    public void getItinerary(String provinceId, String offset) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysFromProvince("searchprovince", provinceId,offset);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
//        if (response.body() != null) {
//            Log.e("get result from server", response.body().toString());
//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> com.iranplanner.tourism.iranplanner.di.data = jsonResponse.getResultItinerary();
//            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("resuliItineraryList", (Serializable) com.iranplanner.tourism.iranplanner.di.data);
//            bundle.putString("fromWhere", "fromProvince");
//            bundle.putString("provinceId", provinceId);
//            bundle.putString("endCity", "");
//            bundle.putString("nextOffset", response.body().getStatistics().getOffsetNext().toString());
//            itineraryListFragment.setArguments(bundle);
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.replace(R.id.SearchHolder, itineraryListFragment);
//            ft.addToBackStack(null);
//            ft.commit();
//            progressDialog.dismiss();
//        }else {
//            Toast.makeText(getContext(), "برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
//        }
//        loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);
        progressDialog.dismiss();

        checkfragment = true;
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Log.e(" error from server", "error");
        progressDialog.dismiss();
    }

}
