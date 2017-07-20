package com.iranplanner.tourism.iranplanner.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.util.List;

import entity.HomeEvent;
import entity.HomeLodging;
import tools.Util;

/**
 * Created by h.vahidimehr on 19/02/2017.
 */

public class ShowHomeEventAdapter extends PagerAdapter {
    Context context;
    Activity activity;
    int hotelPosition;
    List<HomeEvent> homeEvent;

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }


    public ShowHomeEventAdapter(Context context, Activity activity, List<HomeEvent> homeEvent) {
        this.context = context;
        this.activity = activity;
        this.homeEvent = homeEvent;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View page = inflater.inflate(R.layout.content_home_best_event_show, null);
        TextView toolsText = (TextView) page.findViewById(R.id.toolsText);
        ImageView toolsImg = (ImageView) page.findViewById(R.id.toolsImg);
        toolsText.setText(homeEvent.get(position).getEventTitle());
        if (homeEvent.get(position).getImgUrl() != null) {
            Util.setImageView(homeEvent.get(position).getImgUrl(), context, toolsImg);
        }

        hotelPosition = position;

        ((ViewPager) container).addView(page, 0);
        return page;
    }


    @Override
    public int getCount() {
        return homeEvent.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public void addItem() {

        notifyDataSetChanged();
    }

    public void removeItem() {


        notifyDataSetChanged();
    }
}
