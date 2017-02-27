package com.iranplanner.tourism.iranplanner.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.activity.GridActivity;
import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
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

public class ShowTavelToolsAdapter extends PagerAdapter {
    @Override
    public float getPageWidth(int position) {
        return (0.65f);
    }

    public final int PAGE_COUNT = 3;
    Context context;
    Activity activity;

    //    public ShowTavelToolsAdapter(FragmentManager fm) {
//        super(fm);
//    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }

    public ShowTavelToolsAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View page = inflater.inflate(R.layout.fragmen_tools_travel_show, null);
        TextView toolsText = (TextView) page.findViewById(R.id.toolsText);
        ImageView toolsImg = (ImageView) page.findViewById(R.id.toolsImg);
        if (position == 2) {
            toolsText.setText("غذای محلی");
            toolsImg.setImageDrawable(container.getContext().getResources().getDrawable(R.drawable.food));
        } else if (position == 1) {
            toolsText.setText("صنایع دستی");
            toolsImg.setImageDrawable(container.getContext().getResources().getDrawable(R.drawable.handcraft));

        } else if (position == 0) {
            toolsText.setText("پیشنهادات");
            toolsImg.setImageDrawable(container.getContext().getResources().getDrawable(R.drawable.suggestion));

        }


        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(activity, GridActivity.class);
                    activity.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(activity, GridActivity.class);
                    activity.startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(activity, GridActivity.class);
                    activity.startActivity(intent);
                }

            }
        });
        ((ViewPager) container).addView(page, 0);
        return page;


//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String a="dd";
//            }
//        });
//        return view;

    }

    private void startActivity(Intent intent) {
    }

    //    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 2:
//                Bundle bundleHandcraft = new Bundle();
//                bundleHandcraft.putString("type", "handcraft");
//                ShowTravelToolsFragment shohandcraft=  new ShowTravelToolsFragment();
//                shohandcraft.setArguments(bundleHandcraft);
//                return shohandcraft;
//            case 1:
//                Bundle bundleFood = new Bundle();
//                bundleFood.putString("type", "food");
//                ShowTravelToolsFragment showfood=  new ShowTravelToolsFragment();
//                showfood.setArguments(bundleFood);
//                return showfood;
//            case 0:
//                Bundle bundleSuggestion = new Bundle();
//                bundleSuggestion.putString("type", "suggestion");
//                ShowTravelToolsFragment showsuggestion=  new ShowTravelToolsFragment();
//                showsuggestion.setArguments(bundleSuggestion);
//                return showsuggestion;
//        }
//        return null;
//    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
