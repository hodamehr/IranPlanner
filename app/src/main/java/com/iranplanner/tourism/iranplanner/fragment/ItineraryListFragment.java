package com.iranplanner.tourism.iranplanner.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.coinpany.core.android.widget.loading.RotateLoading;
import com.iranplanner.tourism.iranplanner.MapsActivity;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.ItineraryListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.ItineraryPercentage;
import entity.ResultItinerary;
import entity.ResultItineraryList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;

import static android.R.attr.bitmap;

/**
 * Created by h.vahidimehr on 10/01/2017.
 */

public class ItineraryListFragment extends StandardFragment implements Callback<ResultItineraryList>, DataTransferInterface {
    private Context context;
    private RecyclerView recyclerView;
    private ItineraryListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItinerary> data = null;

    RotateLoading rotateloading;
    TextView waiting;
    private boolean fromCityToCity;
    private boolean fromProvince;
    String provinceId;
    int dataSize;
    String nextOffset,endCity;
    private ProgressBar waitingLoading;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itinerary_list, container, false);
        Bundle bundle = getArguments();
        data = (List<ResultItinerary>) bundle.getSerializable("resuliItineraryList");
        String fromWhere = bundle.getString("fromWhere");
        provinceId = bundle.getString("provinceId");
        nextOffset = bundle.getString("nextOffset");
        endCity = bundle.getString("endCity");
        if (fromWhere.equals("fromProvince")) {
            fromCityToCity = false;
            fromProvince = true;
        } else if (fromWhere.equals("fromCityToCity")) {
            fromCityToCity = true;
            fromProvince = false;
        }
        dataSize = 20;
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
//        waiting = (TextView) view.findViewById(R.id.waiting);
        waitingLoading = (ProgressBar) view.findViewById(R.id.waitingLoading);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItineraryListAdapter(getActivity(), this, data, getContext(), R.layout.fragment_itinerary_item);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imgItineraryListMore);
                TextView textView = (TextView) view.findViewById(R.id.itinerary_duration);
                textView.getText();

                MyThread m = new MyThread(imageView, position, textView.getText().toString());
                m.run();
            }
        }));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    //// TODO: 25/01/2017 ina ro check kon data size
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (fromCityToCity) {
                            getItineraryc(data.get(0).getItineraryFromCityId().toString(), nextOffset,endCity);
//                            waiting.setVisibility(View.VISIBLE);
                            waitingLoading.setVisibility(View.VISIBLE);
                        } else if (fromProvince) {
                            getItineraryp(nextOffset, provinceId);
//                            waiting.setVisibility(View.VISIBLE);
                            waitingLoading.setVisibility(View.VISIBLE);

                        }

                    }
                }
            }

        });

        return view;
    }

    class MyThread extends Thread {

        private ImageView img;
        int position;
        String duration;

        public MyThread(ImageView img, int position, String duration) {
            this.img = img;
            this.position = position;
            this.duration = duration;
        }

        @Override
        public void run() {
//            img.buildDrawingCache();
//            Bitmap btm = img.getDrawingCache();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            btm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] bytes = stream.toByteArray();
            Log.e("string", "item clicked");
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("itineraryData", (Serializable) data.get(position));
            intent.putExtra("dutation", duration);

//            intent.putExtra("BMP", bytes);
            startActivity(intent);
        }
    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public void getItineraryc(String cityId, String offset, String toCity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        @GET("api-itinerary.php?action=list&lang=fa&from=342&limit=10&offset=0&to")
        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryList> callc = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset, toCity);
        callc.enqueue(this);
    }

    public void getItineraryp(String offset, String provinceId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarysFromProvince("searchprovince", provinceId, offset);
        call.enqueue(this);
    }

    public void getItineraryAttraction(String cityId, String offset, String attractionId) {
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
        if (response.body() != null) {
            loading = false;
            ResultItineraryList jsonResponse = response.body();
            List<ResultItinerary> jj = jsonResponse.getResultItinerary();
            if (!nextOffset.equals(response.body().getStatistics().getOffsetNext().toString())) {
                data.addAll(jj);
                adapter.notifyDataSetChanged();
//                waiting.setVisibility(View.INVISIBLE);
                waitingLoading.setVisibility(View.INVISIBLE);

                nextOffset = response.body().getStatistics().getOffsetNext().toString();
            }

        } else {
            Log.e("Responce body", "null");
//            waiting.setVisibility(View.INVISIBLE);
            waitingLoading.setVisibility(View.INVISIBLE);

        }
//        rotateloading.stop();
    }

    @Override
    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
        Log.e(" error from server", "error");
    }


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }

}
