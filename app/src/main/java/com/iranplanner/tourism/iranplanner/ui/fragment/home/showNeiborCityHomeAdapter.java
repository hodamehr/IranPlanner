package com.iranplanner.tourism.iranplanner.ui.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.util.List;

import entity.HomeNeighborCity;
import entity.HomeSouvenir;
import tools.Util;


/**
 * Created by HoDA on 7/22/2017.
 */

public class showNeiborCityHomeAdapter extends RecyclerView.Adapter<showNeiborCityHomeAdapter.MyViewHolder> {

    List<HomeNeighborCity> homeNeighborCities;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.txtView);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public showNeiborCityHomeAdapter(List<HomeNeighborCity> homeNeighborCities, Context context) {
        this.homeNeighborCities = homeNeighborCities;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_home_souvenir_localfood, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;
        textViewName.setText(homeNeighborCities.get(listPosition).getName());
        if (homeNeighborCities.get(listPosition).getImageUrl() != null) {
            Util.setImageView(String.valueOf(homeNeighborCities.get(listPosition).getImageUrl()), context, imageView,null);
        }
    }

    @Override
    public int getItemCount() {
        return homeNeighborCities.size();
    }
}

