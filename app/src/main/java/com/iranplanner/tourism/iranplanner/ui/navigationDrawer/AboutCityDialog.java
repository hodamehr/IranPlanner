package com.iranplanner.tourism.iranplanner.ui.navigationDrawer;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by MrCode on 9/20/17.
 */

public class AboutCityDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity activity;

    private TextView tvDesc , tvTitle;
    private ImageView ivBanner;

    public AboutCityDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_about_city);

        tvDesc = (TextView) findViewById(R.id.aboutCityDescTv);
        tvTitle = (TextView) findViewById(R.id.aboutCityTitleTv);
        ivBanner = (ImageView) findViewById(R.id.aboutCityBannerIv);

    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setDesc(String desc){
        tvDesc.setText(Html.fromHtml(desc));
    }

    public void setImageResource(String url){
        Glide.with(activity)
                .load(url)
                .into(ivBanner);
    }

    @Override
    public void onClick(View v) {

    }
}
