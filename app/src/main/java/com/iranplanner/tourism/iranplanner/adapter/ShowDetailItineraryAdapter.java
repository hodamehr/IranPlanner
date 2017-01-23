package com.iranplanner.tourism.iranplanner.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iranplanner.tourism.iranplanner.fragment.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.fragment.PageFragment;
import com.iranplanner.tourism.iranplanner.fragment.ShowAttractionListFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ResultItineraryAttraction;

/**
 * Created by Hoda on 20/01/2017.
 */
public class ShowDetailItineraryAdapter extends FragmentPagerAdapter {
    Map<String, List<ResultItineraryAttraction>> map;
    List<ResultItineraryAttraction> itineraryActionList;

    public ShowDetailItineraryAdapter(FragmentManager fm, Map<String, List<ResultItineraryAttraction>> map) {
        super(fm);
        this.map=map;
    }

    @Override
    public Fragment getItem(int position) {

        itineraryActionList = map.get(String.valueOf(position+1));
        Bundle bundle = new Bundle();
        bundle.putSerializable("itineraryActionList", (Serializable) itineraryActionList);
        bundle.putInt("dayNumber",position+1);
        bundle.putInt("allDays",map.size());
        ShowAttractionListFragment fragment = new ShowAttractionListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getCount() {
       return map.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
