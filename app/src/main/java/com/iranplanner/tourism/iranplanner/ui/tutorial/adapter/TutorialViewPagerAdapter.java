package com.iranplanner.tourism.iranplanner.ui.tutorial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.iranplanner.tourism.iranplanner.ui.tutorial.fragments.TutorialFragmentOne;
import com.iranplanner.tourism.iranplanner.ui.tutorial.fragments.TutorialFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrCode on 9/23/17.
 */

public class TutorialViewPagerAdapter extends FragmentPagerAdapter {

    public TutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragments().get(position);
    }

    @Override
    public int getCount() {
        return getFragments().size();
    }

    private List<Fragment> getFragments(){
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new TutorialFragmentOne());
        fragments.add(new TutorialFragmentTwo());

        return fragments;
    }
}
