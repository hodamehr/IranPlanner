package com.iranplanner.tourism.iranplanner.ui.tutorial;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.tutorial.adapter.TutorialViewPagerAdapter;

import static com.iranplanner.tourism.iranplanner.R.id.tutorialVp;

public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        init();

    }

    private void init() {
        viewPager = (ViewPager) findViewById(tutorialVp);
        viewPager.setAdapter(new TutorialViewPagerAdapter(getSupportFragmentManager()));
    }
}
