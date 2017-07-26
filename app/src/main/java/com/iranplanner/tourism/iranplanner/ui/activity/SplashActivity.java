package com.iranplanner.tourism.iranplanner.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.DaggerHomeComponent;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomePresenter;
import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeContract;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;

import javax.inject.Inject;

import entity.GetHomeResult;
import entity.ResultLodgingList;
import tools.Util;

public class SplashActivity extends StandardActivity implements HomeContract.View ,ReservationContract.View {
    Thread splashTread;
    @Inject
    HomePresenter homePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimations();
//        getHomeResult("province", "309");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout mainLinearLayout = (RelativeLayout) findViewById(R.id.ll_main);
        mainLinearLayout.clearAnimation();
        mainLinearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash_img);
        RelativeLayout splashAnimHolder = (RelativeLayout) findViewById(R.id.splashAnimHolder);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {

                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

    private void getHomeResult(String destination, String selectId) {

        DaggerHomeComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .homeModule(new HomeModule(this,this))
                .build().inject(this);
        String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
        String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
        homePresenter.getHome("home", destination, selectId, cid, andId);
        StartAnimations();

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
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
    }

    @Override
    public void getHomeResult(GetHomeResult GetHomeResult) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    private void startHeavyProcessing() {
        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //some heavy processing resulting in a Data String
//            getHomeResult("province", "309");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "whatever result you have";
        }

        @Override
        protected void onPostExecute(String result) {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            i.putExtra("data", result);
            startActivity(i);
            finish();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}

