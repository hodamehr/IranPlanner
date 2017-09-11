package com.iranplanner.tourism.iranplanner.ui.activity.event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.util.ArrayList;

import entity.HomeEvent;

public class EventListActivity extends StandardActivity implements RecyclerItemOnClickListener.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        initToolbar();
        init();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("مشاهده ی همه ی رویدادها");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(new EventListAdapter(getEvents(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(this, this));
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(EventListActivity.this, EventActivity.class));
    }

    private ArrayList<HomeEvent> getEvents() {
        ArrayList<HomeEvent> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HomeEvent event = new HomeEvent();
            event.setEventTitle("رویداد شماره ی " + String.valueOf(i));
            event.setEventDuration(String.valueOf(i));
            events.add(event);
        }
        return events;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_list;
    }
}
