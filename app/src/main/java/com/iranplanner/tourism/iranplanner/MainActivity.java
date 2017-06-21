package com.iranplanner.tourism.iranplanner;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;


//import com.digits.sdk.android.AuthCallback;
//import com.digits.sdk.android.Digits;
//import com.digits.sdk.android.DigitsException;
//import com.digits.sdk.android.DigitsSession;
import com.iranplanner.tourism.iranplanner.adapter.TabPagerAdapter;
import com.iranplanner.tourism.iranplanner.di.model.ForceUpdateChecker;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import tools.Util;

public class MainActivity extends StandardActivity implements ForceUpdateChecker.OnUpdateNeededListener {
    private StandardFragment currentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//        if (Util.getUseRIdFromShareprefrence(getApplicationContext()) == null || Util.getUseRIdFromShareprefrence(getApplicationContext()) == "") {
//            Intent intent = new Intent(this, LoginActivity.class);
//            finish();
//            startActivity(intent);
//        } else {

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);

        TabLayout mainTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mainTabLayout != null) {
            mainTabLayout.setupWithViewPager(viewPager);
            for (int i = 0; i < mainTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mainTabLayout.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(pagerAdapter.getTabView(i));
                }
            }

        }
        Bundle extras = getIntent().getExtras();
        int position = 0;
        if (extras != null) {
            position = extras.getInt("viewpager_position");
        }
        mainTabLayout.getTabAt(position).getCustomView().setSelected(true);
        currentTab = (StandardFragment) pagerAdapter.getItem(position);
        viewPager.setCurrentItem(position);
        Util.displayFirebaseRegId(this);

        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();
    }
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        //If the event returned false, then call the super.
        if (currentTab == null || !currentTab.onBackPressed())
            super.onBackPressed();
    }

    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("نسخه جدید برنامه قابل دانلود است")
                .setMessage("لطفا برنامه را به روز رسانی کنید ")
                .setPositiveButton("به روز رسانی",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).setNegativeButton("نه؛متشکرم",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create();
        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
