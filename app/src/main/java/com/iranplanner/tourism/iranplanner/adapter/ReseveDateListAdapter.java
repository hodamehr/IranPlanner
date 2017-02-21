package com.iranplanner.tourism.iranplanner.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ItineraryLodgingCity;
import entity.ResultItinerary;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class ReseveDateListAdapter extends RecyclerView.Adapter<ReseveDateListAdapter.ViewHolder> {
    private List<Integer> ii;

    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;

    LayoutInflater inflater;
    ResultItinerary itineraryData;
    List<Date> stayNights;
    Map<String, Integer> dateCity;
    Date startOfTravel;


    public ReseveDateListAdapter(Activity a, DataTransferInterface dtInterface,ResultItinerary resultItinerary, Context context, int rowLayout,Date startOfTravel) {
        this.itineraryData = resultItinerary;
//        ii.add(12);
//        ii.add(13);
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.startOfTravel=startOfTravel;
        resultLodging();
    }
    protected void resultLodging() {
        List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
        stayNights = new ArrayList<Date>();
        dateCity = new HashMap<String, Integer>();
        int index = 0;
        for (ItineraryLodgingCity lodgingCity : lodgingCities) {
            if (!lodgingCity.getLodgingLenght().equals("0")) {
                dateCity.put(lodgingCity.getCityTitle(), Integer.valueOf(lodgingCity.getLodgingLenght()));
            }
        }
        stayNights.add(startOfTravel);
        for (Integer integer : dateCity.values()) {
            System.out.println(integer);
            stayNights.add(Util.addDays(stayNights.get(index), integer));
            index++;
        }
        stayNights.remove(index);
    }
    @Override
    public ReseveDateListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.activity_reservation_hotel_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReseveDateListAdapter.ViewHolder viewHolder, int i) {

            viewHolder.txtDurationLodgingCity.setText(String.valueOf(stayNights.get(i)));
            viewHolder.txtProvinceName.setText("hghg");
            viewHolder.txtCityName.setText("hghg");
            viewHolder.lodgingCityName.setText("hghg");

//        viewHolder.imgHotelList.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_air_gret));

//        if (itineraries.get(i).getItineraryImgUrl() != null) {
//            String url = itineraries.get(i).getItineraryImgUrl();
////            Glide.with(context).load(url)   .into(viewHolder.imgItineraryList);
//
//            Glide.with(context)
//                    .load(url)
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .listener(new RequestListener<String, GlideDrawable>() {
//
//                        @Override
//                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                            //// TODO: 22/01/2017  get defeult picture
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            viewHolder.imageLoading.setVisibility(View.GONE);
//                            return false;
//                        }
//                    })
//                    .into(viewHolder.imgItineraryList)
//            ;
//        } else {
//            Glide.clear(viewHolder.imgItineraryList);
//            viewHolder.imgItineraryList.setImageDrawable(null);
//
//        }


    }


    @Override
    public int getItemCount() {
        return stayNights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */ {

        private ImageView imgHotelList;
        private ImageView ReservationBtn;
        private TextView txtDurationLodgingCity,txtProvinceName,txtCityName,lodgingCityName;
        private ProgressBar imageLoading;

        public ViewHolder(View view) {
            super(view);
            lodgingCityName= (TextView) view.findViewById(R.id.lodgingCityName);
            imgHotelList = (ImageView) view.findViewById(R.id.imgHotelList);
            txtDurationLodgingCity = (TextView) view.findViewById(R.id.txtDurationLodgingCity);
            txtProvinceName = (TextView) view.findViewById(R.id.txtProvinceName);
            ReservationBtn = (ImageView) view.findViewById(R.id.ReservationBtn);
            txtCityName = (TextView) view.findViewById(R.id.txtCityName);
        }
    }

}


