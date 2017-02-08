package com.iranplanner.tourism.iranplanner.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.adapter.ShowDetailItineraryAdapter;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.ResultItineraryAttraction;

/**
 * Created by Hoda on 20/01/2017.
 */
public class ShowAttractionActivity extends StandardActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagershowdetail);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        Bundle extras = getIntent().getExtras();
        List<ResultItineraryAttraction> itineraryActionList = null;
        if (extras != null) {
            itineraryActionList = (List<ResultItineraryAttraction>) extras.getSerializable("ResultItineraryAttraction");
        }
        adapterViewPager = new ShowDetailItineraryAdapter(getSupportFragmentManager(),getDayGroup(itineraryActionList));
        vpPager.setAdapter(adapterViewPager);
    }

    public HashMap<String, List<ResultItineraryAttraction>> getDayGroup(List<ResultItineraryAttraction> itineraryActionList){
        HashMap<String, List<ResultItineraryAttraction>>  map = new HashMap<String, List<ResultItineraryAttraction>>();
        for (ResultItineraryAttraction att : itineraryActionList) {
            String key = att.getItineraryDayplanName();
            if (map.containsKey(key)) {
                List<ResultItineraryAttraction> list = map.get(key);
                list.add(att);
            } else {
                List<ResultItineraryAttraction> list = new ArrayList<ResultItineraryAttraction>();
                list.add(att);
                map.put(key, list);
            }

        }
        return map;
    }
}
