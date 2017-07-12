package com.iranplanner.tourism.iranplanner;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//import com.digits.sdk.android.AuthCallback;
//import com.digits.sdk.android.Digits;
//import com.digits.sdk.android.DigitsException;
//import com.digits.sdk.android.DigitsSession;
//import com.alirezaafkar.toolbar.RtlActionBarDrawerToggle;
//import com.alirezaafkar.toolbar.RtlToolbar;
import com.iranplanner.tourism.iranplanner.adapter.NavigationItemsAdapter;
import com.iranplanner.tourism.iranplanner.adapter.TabPagerAdapter;
import com.iranplanner.tourism.iranplanner.di.model.ForceUpdateChecker;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.FirstItem;
import com.iranplanner.tourism.iranplanner.ui.fragment.HomeFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.SettingFragment;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import entity.Data;
import tools.Util;

public class MainActivity extends StandardActivity implements ForceUpdateChecker.OnUpdateNeededListener {
    private StandardFragment currentTab;
//
//    private RtlToolbar mToolbar;
//    private DrawerLayout mDrawerLayout;


    protected String[] mNavigationDrawerItemTitles;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected Toolbar toolbar;
    protected CharSequence mDrawerTitle;
    protected CharSequence mTitle;

    protected ImageView toolbarToggle;
    protected ImageView toolbarToggleLeft;

    protected static int buildVersion;

    protected TextView toolbarTitle;

    protected static Typeface YEKAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        buildVersion = Build.VERSION.SDK_INT;

        Log.i("hi", String.valueOf(buildVersion));

        if (buildVersion > 20) {

//            toolbarToggle.setBackgroundResource(R.drawable.ripple);

        }

        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
//        toolbarTitle.setTypeface(YEKAN);


        ///

//        if (Util.getUseRIdFromShareprefrence(getApplicationContext()) == null || Util.getUseRIdFromShareprefrence(getApplicationContext()) == "") {
//            Intent intent = new Intent(this, LoginActivity.class);
//            finish();
//            startActivity(intent);
//        } else {


//        mToolbar = (RtlToolbar) findViewById(R.id.toolbar);
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        mToolbar.setOnMenuItemClickListener(this);
//
//        initDrawer();
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(mDrawerLayout, EMAIL, Snackbar.LENGTH_LONG)
//                        .setAction(R.string.send,
//                                new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        sendEmail();
//                                    }
//                                })
//                        .show();
//            }
//        });
        //--------------
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
        int position = 2;
        if (extras != null) {
            position = extras.getInt("viewpager_position");
        }
        mainTabLayout.getTabAt(position).getCustomView().setSelected(true);
        currentTab = (StandardFragment) pagerAdapter.getItem(position);
        viewPager.setCurrentItem(position);


        Util.displayFirebaseRegId(this);

        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();
//        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

//    private void initDrawer() {
//        RtlActionBarDrawerToggle drawerToggle = new RtlActionBarDrawerToggle(this, mDrawerLayout,
//                mToolbar, R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//    }
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


    //-------------

    public static MainActivity instance;
    private MainSearchFragment mainSearchFragment;
    private SettingFragment settingFragment;
    private HomeFragment homeFragment;
    private TabLayout allTabs;

    public static MainActivity getInstance() {
        return instance;
    }

    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tab_layout);
    }

    private void setupTabLayout() {
        mainSearchFragment = MainSearchFragment.newInstance();
        settingFragment = SettingFragment.newInstance();
        allTabs.addTab(allTabs.newTab().setText("حستجو"), true);
        allTabs.addTab(allTabs.newTab().setText("حساب من"));
    }

    private void bindWidgetsWithAnEvent() {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(mainSearchFragment);
                break;
            case 1:
                replaceFragment(settingFragment);
                break;
            case 3:
                replaceFragment(homeFragment);
        }
    }

    public void replaceFragment(StandardFragment fragment) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.frame_container, fragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.commit();
    }


    ///
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick
                (AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:

                fragment = new FirstItem();
                break;
            case 1:
                fragment = new FirstItem();
                break;
            case 2:
                fragment = new FirstItem();
                break;

            default:
                break;
        }

        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//
//            mDrawerList.setItemChecked(position, true);
//            mDrawerList.setSelection(position);
//            setTitle(mNavigationDrawerItemTitles[position]);
//            mDrawerLayout.closeDrawer(GravityCompat.END);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbarTitle.setText(mTitle);
    }

//    void setupToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//    }
}
