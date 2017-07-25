package com.iranplanner.tourism.iranplanner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.util.List;

import entity.HomeLocalfood;
import entity.HomeSouvenir;
import tools.Util;


/**
 * Created by HoDA on 7/22/2017.
 */

public class LocalFoodHomeAdapter extends RecyclerView.Adapter<LocalFoodHomeAdapter.MyViewHolder> {

    List<HomeLocalfood> homeLocalfood;
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

    public LocalFoodHomeAdapter(List<HomeLocalfood> homeLocalfood, Context context) {
        this.homeLocalfood = homeLocalfood;
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
        textViewName.setText(homeLocalfood.get(listPosition).getLocalfoodTitle());
        if (homeLocalfood.get(listPosition).getImgUrl() != null) {
            Util.setImageView(String.valueOf(homeLocalfood.get(listPosition).getImgUrl()), context, imageView);
        }
    }

    @Override
    public int getItemCount() {
        return homeLocalfood.size();
    }
}

