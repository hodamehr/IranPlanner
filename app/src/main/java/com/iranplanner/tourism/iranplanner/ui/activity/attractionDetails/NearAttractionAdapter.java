package com.iranplanner.tourism.iranplanner.ui.activity.attractionDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.util.List;

import entity.ResultAttractionList;
import tools.Util;


/**
 * Created by HoDA on 7/22/2017.
 */

public class NearAttractionAdapter extends RecyclerView.Adapter<NearAttractionAdapter.Holder> {

    private Context context;
    private List<ResultAttractionList> resultAttractionList;

    public NearAttractionAdapter(List<ResultAttractionList> resultAttractionList, Context context) {
        this.resultAttractionList = resultAttractionList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_home_souvenir_localfood, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;
        textViewName.setText(resultAttractionList.get(listPosition).getResulAttraction().getAttractionTitle());
        if (resultAttractionList.get(listPosition).getResulAttraction().getAttractionImgUrl() != null)
            Util.setImageView(String.valueOf(resultAttractionList.get(listPosition).getResulAttraction().getAttractionImgUrl()), context, imageView, null);
    }

    @Override
    public int getItemCount() {
        return resultAttractionList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;

        public Holder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.txtView);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}

