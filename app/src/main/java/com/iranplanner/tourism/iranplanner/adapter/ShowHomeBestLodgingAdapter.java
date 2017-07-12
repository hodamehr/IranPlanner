package com.iranplanner.tourism.iranplanner.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.GridActivity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.HomeLodging;
import entity.ResultSouvenir;
import entity.ResultSouvenirList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.Config;
import server.getJsonInterface;
import tools.Util;

/**
 * Created by h.vahidimehr on 19/02/2017.
 */

public class ShowHomeBestLodgingAdapter extends PagerAdapter {
    @Override
    public float getPageWidth(int position) {
        return (0.55f);
    }


    Context context;
    Activity activity;
    int hotelPosition;
    List<HomeLodging> homeLodgings;

    //    public ShowTavelToolsAdapter(FragmentManager fm) {
//        super(fm);
//    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }


    public ShowHomeBestLodgingAdapter(Context context, Activity activity, List<HomeLodging> homeLodgings) {
        this.context = context;
        this.activity = activity;
        this.homeLodgings = homeLodgings;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View page = inflater.inflate(R.layout.content_home_best_hotel_show, null);
        TextView toolsText = (TextView) page.findViewById(R.id.toolsText);
        ImageView toolsImg = (ImageView) page.findViewById(R.id.toolsImg);
        toolsText.setText(homeLodgings.get(position).getLodgingTitle());
        if (homeLodgings.get(position).getImgUrl() != null) {
            Util.setImageView(homeLodgings.get(position).getImgUrl(), context, toolsImg);
        }

        hotelPosition = position;

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
        return homeLodgings.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
