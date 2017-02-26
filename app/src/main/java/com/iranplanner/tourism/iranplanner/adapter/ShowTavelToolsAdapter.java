package com.iranplanner.tourism.iranplanner.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.fragment.LoginFragment;
import com.iranplanner.tourism.iranplanner.fragment.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.fragment.ShowAttractionListFragment;
import com.iranplanner.tourism.iranplanner.fragment.ShowTravelToolsFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.ResultItineraryAttraction;

import static android.R.id.list;

/**
 * Created by h.vahidimehr on 19/02/2017.
 */

public class ShowTavelToolsAdapter  extends FragmentPagerAdapter {
    @Override
    public float getPageWidth(int position) {
        return (0.65f);
    }

    public final int PAGE_COUNT = 3;

    public ShowTavelToolsAdapter(FragmentManager fm) {
        super(fm);
    }

//    @Override
//    public Object instantiateItem(ViewGroup container,final int position) {
//        LayoutInflater inflater = (LayoutInflater) container.getContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//        View page = inflater.inflate(R.layout.YOUR_PAGE, null);
//
//        page.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                //this will log the page number that was click
//                Log.i("TAG", "This page was clicked: " + position);
//            }
//        });
//
//
//        ((ViewPager) container).addView(page, 0);
//        return page;
//    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 2:
                Bundle bundleHandcraft = new Bundle();
                bundleHandcraft.putString("type", "handcraft");
                ShowTravelToolsFragment shohandcraft=  new ShowTravelToolsFragment();
                shohandcraft.setArguments(bundleHandcraft);
                return shohandcraft;
            case 1:
                Bundle bundleFood = new Bundle();
                bundleFood.putString("type", "food");
                ShowTravelToolsFragment showfood=  new ShowTravelToolsFragment();
                showfood.setArguments(bundleFood);
                return showfood;
            case 0:
                Bundle bundleSuggestion = new Bundle();
                bundleSuggestion.putString("type", "suggestion");
                ShowTravelToolsFragment showsuggestion=  new ShowTravelToolsFragment();
                showsuggestion.setArguments(bundleSuggestion);
                return showsuggestion;
        }
        return null;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


}
