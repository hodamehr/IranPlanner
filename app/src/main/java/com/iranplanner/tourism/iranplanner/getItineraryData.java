package com.iranplanner.tourism.iranplanner;

import android.os.Bundle;
import android.util.Log;

import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;

import java.io.Serializable;
import java.util.List;

import entity.ResultItinerary;
import entity.ResultItineraryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;

/**
 * Created by Hoda on 12/01/2017.
 */
public class getItineraryData implements Callback<ResultItineraryList>  {


    public void getItinerary(String cityId,String offset) {
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
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId,"",offset);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
        Log.e("get result from server",response.body().toString());
        ResultItineraryList jsonResponse = response.body();
        List<ResultItinerary> data= jsonResponse.getResultItinerary();
        ItineraryListFragment itineraryListFragment=new ItineraryListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("resuliItineraryList", (Serializable) data);
        itineraryListFragment.setArguments(bundle);
//        Something something=new Something();
//        something.reportSomethingChanged(12);
//        loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);

    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Log.e(" error from server","error");
    }
}
