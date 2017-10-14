package com.iranplanner.tourism.iranplanner.ui.activity.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iranplanner.tourism.iranplanner.R;

import java.util.ArrayList;
import java.util.List;

import entity.HomeEvent;
import entity.ResultEvent;

/**
 * Created by h.vahidimehr on 10/09/2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.Holder> {

    private List<ResultEvent> events;
    private Context context;
    private LayoutInflater inflater;

    public EventListAdapter(List<ResultEvent> events, Context context) {
        this.events = events;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.row_event_list, parent, false));
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ResultEvent current = events.get(position);
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvCity, tvDate;
        private ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.rowEventTitleTv);
            tvCity = (TextView) itemView.findViewById(R.id.rowEventCityTv);
            tvDate = (TextView) itemView.findViewById(R.id.rowEventDateTv);
            imageView = (ImageView) itemView.findViewById(R.id.rowEventIv);
        }

        public void setData(ResultEvent current) {
            try {
                Glide.with(context).load(current.getEventInfo().getImgUrl()).into(imageView);
//                tvTitle.setText(current.getEventInfo().getEventTitle());
                tvDate.setText(current.getEventInfo().getEventTitle());
                tvCity.setText(current.getEventInfo().getEventCityTitle());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
