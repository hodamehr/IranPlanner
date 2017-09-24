package com.iranplanner.tourism.iranplanner.ui.tutorial;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iranplanner.tourism.iranplanner.R;

import tools.Util;

/**
 * Created by MrCode on 9/24/17.
 */

public class Car {

    private ImageView leftWheel, rightWheel, body;
    private Context context;
    private View wholeVan;

    public static final int TRAVEL_TIME = 2000;

    private int vanMovementWidth;

    public Car(View root, Context context) {
        this.context = context;
        this.wholeVan = root;

        leftWheel = (ImageView) root.findViewById(R.id.tutLeftWheelIv);
        rightWheel = (ImageView) root.findViewById(R.id.tutRightWheelIv);
        body = (ImageView) root.findViewById(R.id.tutVanIv);

        int screenWidth = Util.getScreenWidth(context);

        int vanWidth = (int) Util.dpToPx(context, 140);

        vanMovementWidth = screenWidth / 2 + vanWidth;

        wholeVan.setTranslationX(-vanMovementWidth);

    }

    public void driveIn() {
        wholeVan.animate().translationXBy(vanMovementWidth).setDuration(TRAVEL_TIME);
    }

    public void driveOut() {
        wholeVan.animate().translationXBy(vanMovementWidth).setDuration(TRAVEL_TIME);
    }

    //this method rotates the wheels
    public void spinTheWheels() {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.tut_wheel_rotate);
        leftWheel.startAnimation(animation);
        rightWheel.startAnimation(animation);
    }


    //this method stops the wheels from rotating
    public void stopTheWheels() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                leftWheel.clearAnimation();
                rightWheel.clearAnimation();
            }
        }, TRAVEL_TIME);
    }

    //this method makes the car shake to give the feeling that the car's engine is on
    public void startEngine() {

    }

    //some cool method to make the car generate some smoke
    private void initSmokeGenerator() {

    }

}
