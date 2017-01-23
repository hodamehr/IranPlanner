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

import entity.ItineraryPercentage;
import entity.ResultItinerary;
import entity.ResultItineraryList;
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

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itinerary_list, container, false);
        Bundle bundle = getArguments();
        data = (List<ResultItinerary>) bundle.getSerializable("resuliItineraryList");
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        waiting = (TextView) view.findViewById(R.id.waiting);
//        rotateloading= (RotateLoading) view.findViewById(R.id.rotateloading);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItineraryListAdapter(getActivity(), this, data, getContext(), R.layout.fragment_itinerary_item);
//        mViewCartAdpt = new ItineraryListAdapter()
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imgItineraryListMore);
                MyThread m = new MyThread(imageView, position);
                m.run();

//                        imageView.buildDrawingCache();
//                        Bitmap btm = imageView.getDrawingCache();
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        btm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        byte[] bytes = stream.toByteArray();
//                        Log.e("string", "item clicked");
//                        Intent intent = new Intent(getActivity(), MapsActivity.class);
//                        intent.putExtra("itineraryData", (Serializable) data.get(position));
//                        intent.putExtra("BMP", bytes);
//                        startActivity(intent);

            }
        }));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
//                    if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
//                            loading = false;
                        Log.v("...", "Last Item Wow !");
                        getItinerary(data.get(0).getItineraryFromCityId().toString(), String.valueOf(data.size() + 20));
                        waiting.setVisibility(View.VISIBLE);
                    }
                }
            }
//            }
        });
        return view;
    }

    class MyThread extends Thread {

        private ImageView img;
        int position;

        public MyThread(ImageView img, int position) {
            this.img = img;
            this.position = position;
        }

        @Override
        public void run() {
            img.buildDrawingCache();
            Bitmap btm = img.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            btm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();
            Log.e("string", "item clicked");
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("itineraryData", (Serializable) data.get(position));
            intent.putExtra("BMP", bytes);
            startActivity(intent);
        }
    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public void getItinerary(String cityId, String offset) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
        if (response.body() != null) {
            loading = false;
            ResultItineraryList jsonResponse = response.body();
            List<ResultItinerary> jj = jsonResponse.getResultItinerary();
            data.addAll(jj);
            adapter.notifyDataSetChanged();
            waiting.setVisibility(View.INVISIBLE);
        } else {
            Log.e("Responce body", "null");
            waiting.setVisibility(View.VISIBLE);
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
