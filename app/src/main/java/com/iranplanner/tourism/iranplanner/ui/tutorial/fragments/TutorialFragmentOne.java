package com.iranplanner.tourism.iranplanner.ui.tutorial.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.tutorial.Car;
import com.iranplanner.tourism.iranplanner.ui.tutorial.TutorialActivity;

import tools.Util;

/**
 * Created by MrCode on 9/23/17.
 */

public class TutorialFragmentOne extends Fragment implements View.OnClickListener {

    private ImageView dotTopLeft, dotTopRight, dotBottomLeft, dotBottomRight, dotCenter;
    private ImageView pinLeft, pinCenter, pinRight;
    private View wholeVan, container;

    private Car car;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_fragment_one, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        dotTopLeft = (ImageView) view.findViewById(R.id.tutLeftTopDotIv);
        dotTopRight = (ImageView) view.findViewById(R.id.tutRightTopDotIv);
        dotBottomLeft = (ImageView) view.findViewById(R.id.tutLeftBottomDotIv);
        dotBottomRight = (ImageView) view.findViewById(R.id.tutRightBottomDotIv);
        dotCenter = (ImageView) view.findViewById(R.id.tutCenterDotIv);

        pinLeft = (ImageView) view.findViewById(R.id.tutPinLeft);
        pinCenter = (ImageView) view.findViewById(R.id.tutPicCenter);
        pinRight = (ImageView) view.findViewById(R.id.tutPinRight);

        container = view.findViewById(R.id.tutMasterContainer);

        dotTopRight.setAlpha(0f);
        dotTopLeft.setAlpha(0f);
        dotBottomRight.setAlpha(0f);
        dotBottomLeft.setAlpha(0f);
        dotCenter.setAlpha(0f);

        pinLeft.setAlpha(0f);
        pinCenter.setAlpha(0f);
        pinRight.setAlpha(0f);

        view.findViewById(R.id.okBtn).setOnClickListener(this);

        car = new Car(view.findViewById(R.id.tutWholeVanRl), getActivity());

        car.spinTheWheels();
        car.driveIn();

        showDots();
    }

    private void showDots() {
        dotTopLeft.animate().alpha(1).setDuration(800);
        dotTopRight.animate().alpha(1).setDuration(800);
        dotBottomLeft.animate().alpha(1).setDuration(800);
        dotBottomRight.animate().alpha(1).setDuration(800);
        dotCenter.animate().alpha(1).setDuration(800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pinRight.animate().alpha(1);
                pinCenter.animate().alpha(1);
                pinLeft.animate().alpha(1);

                YoYo.with(Techniques.BounceInDown)
                        .playOn(pinLeft);
                YoYo.with(Techniques.BounceInUp)
                        .playOn(pinCenter);
                YoYo.with(Techniques.BounceInDown)
                        .playOn(pinRight);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Pulse)
                        .repeat(YoYo.INFINITE)
                        .playOn(pinLeft);
                YoYo.with(Techniques.Pulse)
                        .repeat(YoYo.INFINITE)
                        .playOn(pinCenter);
                YoYo.with(Techniques.Pulse)
                        .repeat(YoYo.INFINITE)
                        .playOn(pinRight);
            }
        }, 2000);

        car.stopTheWheels();
    }

    private void hideDots() {
        container.animate().alpha(0f).setDuration(1000);
    }

    @Override
    public void onClick(View view) {
        hideDots();
        car.spinTheWheels();
        car.driveOut();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((TutorialActivity) getActivity()).nextPage();
            }
        }, 2100);
    }
}
