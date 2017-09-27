package com.iranplanner.tourism.iranplanner.ui.navigationDrawer;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinpany.core.android.widget.CTouchyWebView;
import com.iranplanner.tourism.iranplanner.R;

import static com.iranplanner.tourism.iranplanner.R.id.contentFullDescription;

/**
 * Created by MrCode on 9/20/17.
 */

public class AboutCityDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity activity;

    private CTouchyWebView webView;
    private TextView tvTitle;
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

        webView = (CTouchyWebView) findViewById(R.id.aboutCityDescWebView);
        tvTitle = (TextView) findViewById(R.id.aboutCityTitleTv);
        ivBanner = (ImageView) findViewById(R.id.aboutCityBannerIv);

        findViewById(R.id.aboutCityCloseBtn).setOnClickListener(this);

    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setDesc(String string) {

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setLongClickable(false);
        webView.setHapticFeedbackEnabled(false);

        String pish = "<html><head><style type=\"text/css\">@font-face {color:#737373;font-family: MyFont;src: url(\"file:///android_asset/fonts/IRANSansMobile.ttf\")}body {font-family: MyFont;font-size: small;text-align: justify;direction:rtl}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + string + pas;

        webView.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);
    }

    public void setImageResource(String url) {
        Glide.with(activity)
                .load(url)
                .into(ivBanner);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
