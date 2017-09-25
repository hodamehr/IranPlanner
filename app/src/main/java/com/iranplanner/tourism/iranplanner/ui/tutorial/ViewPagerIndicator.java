package com.iranplanner.tourism.iranplanner.ui.tutorial;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by MrCode on 9/25/17.
 */

public class ViewPagerIndicator extends RelativeLayout {

    private Context context;
    private AttributeSet attrs;

    private ArrayList<Integer> drawables;
    private ViewPager viewPager;

    public ViewPagerIndicator(Context context) {
        super(context);
        this.context = context;
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
    }

    public void init() {

        LinearLayout container = new LinearLayout(context);

        ViewGroup.LayoutParams params = container.getLayoutParams();
        if (params != null) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        container.setLayoutParams(params);

        addView(container);

        container.setGravity(Gravity.CENTER);

        container.requestLayout();

        for (Integer integer : drawables) {
            ImageView imageView = new ImageView(context);

            imageView.getLayoutParams().height = (int) dpToPx(15);
            imageView.getLayoutParams().width = (int) dpToPx(15);

            imageView.setImageResource(integer);

            imageView.requestLayout();

            container.addView(imageView);

        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setDrawables(ArrayList<Integer> drawables) {
        this.drawables = drawables;
    }

    private float dpToPx(int dp) {
        Resources r = getContext().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

}
