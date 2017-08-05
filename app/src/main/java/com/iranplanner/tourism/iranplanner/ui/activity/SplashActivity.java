package com.iranplanner.tourism.iranplanner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.DaggerHomeComponent;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeContract;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeModule;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomePresenter;

import javax.inject.Inject;

import entity.GetHomeResult;
import entity.ResultLodgingList;
import tools.CustomMessage;
import tools.Util;

public class SplashActivity extends StandardActivity implements HomeContract.View, ReservationContract.View {
    Thread splashTread;
    @Inject
    HomePresenter homePresenter;

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
            getHomeResult("country", "311");
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
                .homeModule(new HomeModule(this, this))
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
    public void showError(String message) {
        SplashActivity.this.finish();
    }

    @Override
    public void showComplete() {

        SplashActivity.this.finish();
    }

    @Override
    public void ShowHomeResult(GetHomeResult GetHomeResult) {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("HomeResult",GetHomeResult);
        startActivity(intent);
//        SplashActivity.this.finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

}

