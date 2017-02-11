package com.iranplanner.tourism.iranplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;



import com.iranplanner.tourism.iranplanner.adapter.TabPagerAdapter;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;


public class MainActivity extends StandardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test test=new test();
//        test.getItinerary("342");
        // Setup the viewPager
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        View logo = getLayoutInflater().inflate(R.layout.custom_imageview_toolbar, null);
        toolbar.addView(logo);


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
