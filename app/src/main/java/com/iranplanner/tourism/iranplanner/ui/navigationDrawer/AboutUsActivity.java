package com.iranplanner.tourism.iranplanner.ui.navigationDrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

public class AboutUsActivity extends StandardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("درباره ما");

        init();
    }

    private void init() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }
}
