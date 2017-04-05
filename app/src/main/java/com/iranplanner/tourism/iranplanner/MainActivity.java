package com.iranplanner.tourism.iranplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.google.firebase.messaging.FirebaseMessaging;
import com.iranplanner.tourism.iranplanner.adapter.TabPagerAdapter;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
import server.Config;
import server.NotificationUtils;
import tools.Util;

public class MainActivity extends StandardActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "9ReBYxJ9ldP2AhMJStWyXF94Y";
    private static final String TWITTER_SECRET = "U6t7AUdJcuYKevlwNgCm3QpwWGQKxwOTNTltAbeIQQgkqGcW0C";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
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
Util.displayFirebaseRegId(this);

        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });


    }

}
