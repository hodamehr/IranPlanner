package com.iranplanner.tourism.iranplanner.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.MapsActivity;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.AttractionsListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.standard.attractionDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.ResultItineraryAttraction;


public class ShowAttractionListFragment extends StandardFragment implements /*Callback<ResultItineraryList>,*/ DataTransferInterface {
    private Context context;
    private RecyclerView attractionRecyclerView;
    private AttractionsListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItineraryAttraction> itineraryActionList;
    int dayNumber, allDays;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_attraction_list, container, false);
        Bundle bundle = getArguments();
        attractionRecyclerView = (RecyclerView) view.findViewById(R.id.attractionListRecyclerView);
        TextView textDayNumber = (TextView) view.findViewById(R.id.textDayNumber);
        itineraryActionList = (List<ResultItineraryAttraction>) bundle.getSerializable("itineraryActionList");
        dayNumber = bundle.getInt("dayNumber");
        allDays = bundle.getInt("allDays");
        textDayNumber.setText(" روز " + String.valueOf(dayNumber) + " از " + allDays);
        attractionRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        attractionRecyclerView.setLayoutManager(layoutManager);
        adapter = new AttractionsListAdapter(getActivity(), this, itineraryActionList, getContext(), R.layout.fragment_show_attraction_list_detail);
        attractionRecyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        attractionRecyclerView.setLayoutManager(mLayoutManager);
        attractionRecyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ResultItineraryAttraction ResultItineraryAttraction = itineraryActionList.get(position);
                Intent intent = new Intent(getActivity(), attractionDetailActivity.class);
                intent.putExtra("ResultItineraryAttraction", (Serializable) ResultItineraryAttraction);
                startActivity(intent);
//                DetailAttractionFragment fragment = new DetailAttractionFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ResultItineraryAttraction", ResultItineraryAttraction);

//                fragment.setArguments(bundle);
//                final FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.Holder, fragment, "NewFragmentTag");
//                ft.commit();
            }
        }));


        attractionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                        getItinerary(data.get(0).getItineraryFromCityId().toString(), String.valueOf(data.size() + 20));
//                        waiting.setVisibility(View.VISIBLE);
                    }
                }
            }
//            }
        });
        return view;
    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    //    public void getItinerary(String cityId, String offset) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.parsdid.com/iranplanner/app/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        getJsonInterface getJsonInterface = retrofit.create(getJsonInterface.class);
//        Call<ResultItineraryList> call = getJsonInterface.getItinerarys("list", "fa", cityId, "", offset);
//        call.enqueue(this);
//    }
//
//    @Override
//    public void onResponse(Call<ResultItineraryList> call, Response<ResultItineraryList> response) {
//        if (response.body() != null) {
//            loading = false;
//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> jj = jsonResponse.getResultItinerary();
//            data.addAll(jj);
//            adapter.notifyDataSetChanged();
//            waiting.setVisibility(View.INVISIBLE);
//        } else {
//            Log.e("Responce body", "null");
//            waiting.setVisibility(View.VISIBLE);
//        }
//
////        rotateloading.stop();
//    }
//
//    @Override
//    public void onFailure(Call<ResultItineraryList> call, Throwable t) {
//        Log.e(" error from server", "error");
//    }
//
//
    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
