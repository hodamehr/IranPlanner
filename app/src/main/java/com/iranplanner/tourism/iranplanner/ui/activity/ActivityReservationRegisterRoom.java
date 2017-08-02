package com.iranplanner.tourism.iranplanner.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.TableLayout;

import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by HoDA on 7/31/2017.
 */

public class ActivityReservationRegisterRoom extends StandardActivity {
TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_register_room_activity);
        setupTablayout();

    }
    private void setupTablayout(){

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_input;
    }


}
