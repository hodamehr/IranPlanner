package com.iranplanner.tourism.iranplanner.ui.activity.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.util.ArrayList;

import entity.HomeEvent;

/**
 * Created by h.vahidimehr on 10/09/2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.Holder> {

    private ArrayList<HomeEvent> events;
    private Context context;
    private LayoutInflater inflater;

    public EventListAdapter(ArrayList<HomeEvent> events, Context context) {
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
        HomeEvent current = events.get(position);
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

        public void setData(HomeEvent current) {
            try {
                tvTitle.setText(current.getEventTitle());
                tvDate.setText(current.getEventDuration().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
