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

import entity.HomeAttraction;
import tools.Util;

/**
 * Created by h.vahidimehr on 19/02/2017.
 */

public class ShowHomeBestAttractionAdapter extends PagerAdapter {
    @Override
    public float getPageWidth(int position) {
        return (0.55f);
    }


    Context context;
    Activity activity;
    int attractionPosition;
    List<HomeAttraction> homeAttractions;

    //    public ShowTavelToolsAdapter(FragmentManager fm) {
//        super(fm);
//    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }


    public ShowHomeBestAttractionAdapter(Context context, Activity activity, List<HomeAttraction> homeAttractions) {
        this.context = context;
        this.activity = activity;
        this.homeAttractions = homeAttractions;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View page = inflater.inflate(R.layout.content_home_best_hotel_show, null);
        TextView toolsText = (TextView) page.findViewById(R.id.toolsText);
        ImageView toolsImg = (ImageView) page.findViewById(R.id.toolsImg);
        toolsText.setText(homeAttractions.get(position).getAttractionTitle());
        if (homeAttractions.get(position).getImgUrl() != null) {
            Util.setImageView(homeAttractions.get(position).getImgUrl(), context, toolsImg);
        }

        attractionPosition = position;

//        page.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("click", String.valueOf(hotelPosition));
//            }
//        });
        ((ViewPager) container).addView(page, 0);
        return page;
    }

    @Override
    public int getCount() {
        return homeAttractions.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
