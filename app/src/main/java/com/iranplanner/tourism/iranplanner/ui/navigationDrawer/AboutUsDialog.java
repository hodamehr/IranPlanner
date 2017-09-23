package com.iranplanner.tourism.iranplanner.ui.navigationDrawer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by MrCode on 9/20/17.
 */

public class AboutUsDialog extends Dialog implements
        android.view.View.OnClickListener {

    private TextView tvContent;
    private ImageView ivLogo;

    public AboutUsDialog(Activity activity) {
        super(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_about_us);

        tvContent = (TextView) findViewById(R.id.aboutUsTv);
        ivLogo = (ImageView) findViewById(R.id.aboutUsLogoTypeIv);

        tvContent.setAlpha(0f);
        ivLogo.setAlpha(0f);

        tvContent.animate().alpha(1).setStartDelay(600);
        ivLogo.animate().alpha(1).setStartDelay(500);

    }

    @Override
    public void onClick(View v) {

    }
}
