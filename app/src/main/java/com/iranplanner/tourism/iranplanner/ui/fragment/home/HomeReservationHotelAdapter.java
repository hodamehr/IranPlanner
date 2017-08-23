package com.iranplanner.tourism.iranplanner.ui.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.List;

import entity.HomeLodging;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class HomeReservationHotelAdapter extends RecyclerView.Adapter<HomeReservationHotelAdapter.ViewHolder> {

    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;

    LayoutInflater inflater;

    List<HomeLodging> homeLodgings;


    public HomeReservationHotelAdapter(Activity a, DataTransferInterface dtInterface, List<HomeLodging> homeLodgings, Context context, int rowLayout) {
        this.homeLodgings = homeLodgings;
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public HomeReservationHotelAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeReservationHotelAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txtView.setText(homeLodgings.get(i).getLodgingTitle());
        if (homeLodgings.get(i).getImgUrl() != null) {
            Util.setImageView(homeLodgings.get(i).getImgUrl(), context, viewHolder.imageView,null);
        }
        if (homeLodgings.get(i).getLodgingRateInt()=="1") {
            viewHolder.starHolder.setVisibility(View.VISIBLE);
            viewHolder.imgStar3.setVisibility(View.VISIBLE);
        }else if(homeLodgings.get(i).getLodgingRateInt()=="2"){
            viewHolder.starHolder.setVisibility(View.VISIBLE);
            viewHolder.imgStar3.setVisibility(View.VISIBLE);
            viewHolder.imgStar4.setVisibility(View.VISIBLE);
        }else if(homeLodgings.get(i).getLodgingRateInt()=="3"){
            viewHolder.starHolder.setVisibility(View.VISIBLE);
            viewHolder.imgStar4.setVisibility(View.VISIBLE);
            viewHolder.imgStar2.setVisibility(View.VISIBLE);
            viewHolder.imgStar3.setVisibility(View.VISIBLE);
        }else if(homeLodgings.get(i).getLodgingRateInt()=="4"){
            viewHolder.starHolder.setVisibility(View.VISIBLE);
            viewHolder.imgStar1.setVisibility(View.VISIBLE);
            viewHolder.imgStar2.setVisibility(View.VISIBLE);
            viewHolder.imgStar3.setVisibility(View.VISIBLE);
            viewHolder.imgStar4.setVisibility(View.VISIBLE);
        }else if(homeLodgings.get(i).getLodgingRateInt()=="5"){
            viewHolder.starHolder.setVisibility(View.VISIBLE);
            viewHolder.imgStar1.setVisibility(View.VISIBLE);
            viewHolder.imgStar2.setVisibility(View.VISIBLE);
            viewHolder.imgStar3.setVisibility(View.VISIBLE);
            viewHolder.imgStar4.setVisibility(View.VISIBLE);
            viewHolder.imgStar5.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return homeLodgings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        private ImageView imgStar1,imageView;
        private ImageView imgStar2;
        private ImageView imgStar3;
        private ImageView imgStar4;
        private ImageView imgStar5;
        private RelativeLayout starHolder;

        public ViewHolder(View view) {
            super(view);
//            commentText = (TextView) view.findViewById(R.id.commentText);
//            commentSenderName = (TextView) view.findViewById(R.id.commentSenderName);
            txtView = (TextView) view.findViewById(R.id.txtView);
            imgStar1 = (ImageView) view.findViewById(R.id.imgStar1);
            imgStar2 = (ImageView) view.findViewById(R.id.imgStar2);
            imgStar3 = (ImageView) view.findViewById(R.id.imgStar3);
            imgStar4 = (ImageView) view.findViewById(R.id.imgStar4);
            imgStar5 = (ImageView) view.findViewById(R.id.imgStar5);
            imageView = (ImageView) view.findViewById(R.id.image);
            starHolder = (RelativeLayout) view.findViewById(R.id.starHolder);
        }
    }

}


