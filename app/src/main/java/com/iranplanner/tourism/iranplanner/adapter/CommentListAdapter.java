package com.iranplanner.tourism.iranplanner.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.ItineraryLodgingCity;
import entity.ResultComment;
import entity.ResultItinerary;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;

    LayoutInflater inflater;

    List<ResultComment> resultComments;


    public CommentListAdapter(Activity a, DataTransferInterface dtInterface, List<ResultComment> resultComments, Context context, int rowLayout) {
        this.resultComments = resultComments;
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.fragment_comment_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentListAdapter.ViewHolder viewHolder, int i) {

        viewHolder.commentText.setText(resultComments.get(i).getGvalue());


    }


    @Override
    public int getItemCount() {
        return resultComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView commentText;
        public ViewHolder(View view) {
            super(view);

            commentText = (TextView) view.findViewById(R.id.commentText);

        }
    }

}


