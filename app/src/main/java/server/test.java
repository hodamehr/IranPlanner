package server;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iranplanner.tourism.iranplanner.adapter.ItineraryListAdapter;

import java.util.List;

import entity.ResultItinerary;
import entity.ResultItineraryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hoda on 10/01/2017.
 */
public class test implements Callback<ResultItineraryList> {
    private Context context;
    private RecyclerView recyclerView;
    private ItineraryListAdapter adapter;

    public test(RecyclerView recyclerView, Context context) {
        this.context=context;
        this.recyclerView=recyclerView;
    }


    public void getItinerary(String cityId) {
//        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

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
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId,"","");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
        Log.e("get result from server",response.body().toString());
//        ResultItineraryList jsonResponse = response.body();
//        List<ResultItinerary> data= jsonResponse.getResultItinerary();
//        adapter = new ItineraryListAdapter(data,,context);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Log.e(" error from server","error");
    }
}
