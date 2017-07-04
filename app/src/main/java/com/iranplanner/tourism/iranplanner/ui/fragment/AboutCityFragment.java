package com.iranplanner.tourism.iranplanner.ui.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;


/**
 * Created by phelat on 9/29/16.
 */
public class AboutCityFragment extends StandardFragment {


    public AboutCityFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about_city, container, false);
        return rootView;
    }

    public static AboutCityFragment newInstance() {
        AboutCityFragment fragment = new AboutCityFragment();
        return fragment;
    }

}
