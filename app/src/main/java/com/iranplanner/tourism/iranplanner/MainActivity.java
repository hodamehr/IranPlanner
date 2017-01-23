package com.iranplanner.tourism.iranplanner;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.iranplanner.tourism.iranplanner.adapter.TabPagerAdapter;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import server.test;

public class MainActivity extends StandardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test test=new test();
//        test.getItinerary("342");
        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),this);
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);

       TabLayout mainTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mainTabLayout != null) {
            mainTabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < mainTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mainTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(pagerAdapter.getTabView(i));
            }
            mainTabLayout.getTabAt(0).getCustomView().setSelected(true);
        }

    }

}
