package com.iranplanner.tourism.iranplanner.ui.activity.mainActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.ForceUpdateChecker;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.downloader.FileDownloadHelper;
import com.iranplanner.tourism.iranplanner.ui.fragment.FirstItem;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.SettingFragment;
import com.iranplanner.tourism.iranplanner.ui.tutorial.TutorialActivity;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import entity.GetHomeResult;
import tools.Util;

public class MainActivity extends StandardActivity implements ForceUpdateChecker.OnUpdateNeededListener {
    private StandardFragment currentTab;
    GetHomeResult homeResult;
//
//    private RtlToolbar mToolbar;
//    private DrawerLayout mDrawerLayout;


    protected String[] mNavigationDrawerItemTitles;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected Toolbar toolbar;
    protected CharSequence mDrawerTitle;
    protected CharSequence mTitle;
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    protected ImageView toolbarToggle;
    protected ImageView toolbarToggleLeft;

    protected static int buildVersion;

    protected TextView toolbarTitle;

    protected static Typeface YEKAN;

    boolean doubleBackToExitPressedOnce = false;
    private ViewPager viewPager;
    private TabPagerAdapter pagerAdapter;
    TabLayout mainTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Bundle extras = getIntent().getExtras();
        homeResult = (GetHomeResult) extras.getSerializable("HomeResult");
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this, homeResult);
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);

        mainTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mainTabLayout != null) {
            mainTabLayout.setupWithViewPager(viewPager);
            for (int i = 0; i < mainTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mainTabLayout.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(pagerAdapter.getTabView(i));
                }
            }

        }

//        startActivity(new Intent(this, TutorialActivity.class));

        int position = 2;
//        if (extras != null) {
//            position = extras.getInt("viewpager_position");
//        }
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


}
