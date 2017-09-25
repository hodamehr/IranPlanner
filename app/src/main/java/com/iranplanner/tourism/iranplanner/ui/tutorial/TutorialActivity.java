package com.iranplanner.tourism.iranplanner.ui.tutorial;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.tutorial.adapter.TutorialViewPagerAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.iranplanner.tourism.iranplanner.R.id.tutorialVp;
import static com.iranplanner.tourism.iranplanner.R.id.viewpager;

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

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

    }

    private ArrayList<Integer> getDrawables(){
        ArrayList<Integer> drawables = new ArrayList<>();

        drawables.add(R.drawable.ic_google);

        return drawables;
    }

    public void nextPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, false);
    }

}
