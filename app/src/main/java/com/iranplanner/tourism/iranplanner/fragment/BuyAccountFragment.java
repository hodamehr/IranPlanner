package com.iranplanner.tourism.iranplanner.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;



public class BuyAccountFragment extends StandardFragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_itinerary_item, container, false);
        return rootView;
    }

}
