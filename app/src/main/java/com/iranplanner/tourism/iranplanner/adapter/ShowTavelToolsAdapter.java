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

    public final int PAGE_COUNT = 2;

    public ShowTavelToolsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:

                return  new ShowTravelToolsFragment();

            case 1:
                return  new ShowTravelToolsFragment();
//
//
//            case 3:
//                return  new ShowTravelToolsFragment();

        }
        return null;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


}
