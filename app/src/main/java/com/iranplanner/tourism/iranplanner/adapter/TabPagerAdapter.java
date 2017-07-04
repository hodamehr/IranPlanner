package com.iranplanner.tourism.iranplanner.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.fragment.HomeFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.LoginFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.SettingFragment;

/**
 * Created by Hoda on 10/01/2017.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public final int PAGE_COUNT = 3;
    private int[] mTabsIcons = {
            R.mipmap.ic_search,
            R.mipmap.ic_profile_grey,
            R.mipmap.ic_profile_grey

    };
    private StandardFragment currentTab;
    //    StandardFragment
    private final String[] mTabsTitle = {"جستجو", "حساب من","خانه"};

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTabsTitle[position]);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mTabsIcons[position]);
        return view;
    }

    @Override
    public Fragment getItem(int pos) {
        currentTab = null;
        switch (pos) {

            case 0:
                currentTab = MainSearchFragment.newInstance();
                return currentTab;

            case 1:
                currentTab = SettingFragment.newInstance();
                return currentTab;
            case 2:
                currentTab = HomeFragment.newInstance();
                return currentTab;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsTitle[position];
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}

