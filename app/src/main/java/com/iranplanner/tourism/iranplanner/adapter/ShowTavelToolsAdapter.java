package com.iranplanner.tourism.iranplanner.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
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
            case 2:
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
