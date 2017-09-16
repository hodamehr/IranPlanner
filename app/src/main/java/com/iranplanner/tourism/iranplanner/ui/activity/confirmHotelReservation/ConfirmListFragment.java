package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.attractionDetails.attractionDetailActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.showAttractionList.AttractionsListAdapter;
import com.iranplanner.tourism.iranplanner.ui.fragment.showAttractionList.AttractionsListAdapterDay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.ItineraryLodgingCity;
import entity.ResulAttraction;
import entity.ResultAttractionList;
import entity.ResultItineraryAttraction;
import entity.ResultItineraryAttractionDay;
import tools.Util;


public class ConfirmListFragment extends StandardFragment  {
    private Context context;
    private RecyclerView attractionRecyclerView;
    private AttractionsListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<ResultItineraryAttraction> itineraryActionList;
    List<ResultItineraryAttractionDay> resultItineraryAttractionDays;
    ImageView arrowRight, arrowLeft;
    int dayNumber, allDays;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_attraction_list, container, false);
        return view;
    }


}
