package com.iranplanner.tourism.iranplanner.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore.AttractionListMorePresenter;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListPresenter;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.DaggerHomeComponent;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeContract;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeModule;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomePresenter;

import javax.inject.Inject;

import entity.GetHomeResult;
import entity.ResultCommentList;
import entity.ResultLodgingHotel;
import entity.ResultLodgingList;
import entity.ShowAtractionDetailMore;
import entity.ShowAttractionListMore;
import entity.ShowAttractionMoreList;
import tools.CustomMessage;
import tools.Util;

public class SplashActivity extends StandardActivity implements HomeContract.View, ReservationContract.View, AttractionListMorePresenter.View, ReservationHotelListPresenter.View {
    Thread splashTread;
    @Inject
    HomePresenter homePresenter;

    private ImageView ivLogo, ivLogoType, ivLogoInner;
    private TextView tvInfo, tvWebSite;
    private View vLogoContainer, vLogoInfoContainer, vFellows;
    private boolean isAnimationDone = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()) {

                Toast.makeText(getApplicationContext(),
                        "عدم دسترسی به اینترنت ", Toast.LENGTH_SHORT).show();
            } else {
//                StartAnimations();
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!Util.isNetworkAvailable(getApplicationContext())) {
            CustomMessage customMessage = new CustomMessage(this, "عدم دسترسی به اینترنت");
            customMessage.show();
        } else {
            init();
//            new LongOperation().execute("");
            // we have internet connection, so it is save to connect to the internet here
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent i=new Intent(SplashActivity.this,LoginActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//            },1500);
//            StartAnimations();
        }

//        ShowHomeResult("province", "309");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void init() {
        ivLogo = (ImageView) findViewById(R.id.splashLogoIv);
        ivLogoType = (ImageView) findViewById(R.id.splashLogoTypeIv);
        ivLogoInner = (ImageView) findViewById(R.id.splashLogoInnerIv);

        tvInfo = (TextView) findViewById(R.id.splashInfoTv);
        tvWebSite = (TextView) findViewById(R.id.splashIranPlannerWebSiteTv);

        vLogoInfoContainer = findViewById(R.id.splashLogoInfoContainer);
        vLogoContainer = findViewById(R.id.splashLogoContainer);
        vFellows = findViewById(R.id.splashFellowView);

        vLogoContainer.setY(-100f);
        vFellows.setY(150f);

        tvWebSite.setAlpha(0f);
        ivLogo.setAlpha(0f);
        vLogoContainer.setAlpha(0f);
        vLogoInfoContainer.setAlpha(0f);

        startAnimation();
    }

    private void startAnimation() {
        tvInfo.setVisibility(View.VISIBLE);
        ivLogoType.setVisibility(View.VISIBLE);

        //Downward Translation animations
        vLogoContainer.animate().alpha(1).translationYBy(100f).setStartDelay(500).setDuration(500).start();
        ivLogo.animate().alpha(1).setStartDelay(500).setDuration(500).start();

        //Separation Animations
        vLogoInfoContainer.animate().alpha(1).translationXBy(-150f).setStartDelay(1300).setDuration(500).start();
        vLogoContainer.animate().translationXBy(150f).setStartDelay(1300).setDuration(500).start();

        //Fellow Layout Upward Translation animations
        vFellows.animate().translationYBy(-150).setStartDelay(1700).start();

        //WebSite TextView Alpha Animation
        tvWebSite.animate().alpha(1).setStartDelay(1700).setDuration(400).start();

        //Rotation Animation Used For the Inner Part Of The Logo
        Animation rotation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate);
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setStartOffset(1500);
        ivLogoInner.startAnimation(rotation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proceed();
            }
        }, 2600);
    }

    private void proceed() {
        getHomeResult("country", "311");
    }

//    private void StartAnimations() {
//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        anim.reset();
//        RelativeLayout mainLinearLayout = (RelativeLayout) findViewById(R.id.ll_main);
//        mainLinearLayout.clearAnimation();
//        mainLinearLayout.startAnimation(anim);
//
//        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
//        anim.reset();
//        ImageView iv = (ImageView) findViewById(R.id.splash_img);
//        RelativeLayout splashAnimHolder = (RelativeLayout) findViewById(R.id.splashAnimHolder);
//        iv.clearAnimation();
//        iv.startAnimation(anim);
//
//        splashTread = new Thread() {
//            @Override
//            public void run() {
//                try {
//
//                    int waited = 0;
//                    // Splash screen pause time
//                    while (waited < 3500) {
//                        sleep(100);
//                        waited += 100;
//                    }
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
//                    SplashActivity.this.finish();
//                } catch (InterruptedException e) {
//                    // do nothing
//                } finally {
//                    SplashActivity.this.finish();
//                }
//
//            }
//        };
//        splashTread.start();
//
//    }


    private void getHomeResult(String destination, String selectId) {

        DaggerHomeComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .homeModule(new HomeModule(this, this, this, this))
                .build().inject(this);
        String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
        String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
        homePresenter.getHome("home", destination, selectId, cid, andId);
//        StartAnimations();

    }

    @Override
    public void showLodgingList(ResultLodgingList resultLodgingList) {

    }

    @Override
    public void showComments(ResultCommentList resultCommentList) {

    }

    @Override
    public void sendCommentMessage(ResultCommentList resultCommentList) {

    }

    @Override
    public void showHotelReserveList(ResultLodgingHotel resultLodgingHotel) {

    }

    @Override
    public void showError(String message) {
        SplashActivity.this.finish();
    }

    @Override
    public void commentResult(String message) {

    }

    @Override
    public void showComplete() {
        SplashActivity.this.finish();
    }

    @Override
    public void ShowHomeResult(GetHomeResult GetHomeResult) {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("HomeResult", GetHomeResult);
        startActivity(intent);
//        SplashActivity.this.finish();
    }

    @Override
    public void ShowAttractionLists(ShowAttractionListMore getAttractionList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void ShowAttractionLists(ShowAttractionMoreList showAttractionList) {

    }

    @Override
    public void showAttractionDetail(ShowAtractionDetailMore showAttractionFull) {

    }

}

