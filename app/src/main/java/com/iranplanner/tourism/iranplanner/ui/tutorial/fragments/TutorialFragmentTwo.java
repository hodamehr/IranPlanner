package com.iranplanner.tourism.iranplanner.ui.tutorial.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class TutorialFragmentTwo extends Fragment implements View.OnClickListener {

    private Car car;
    private ImageView hotel, hotelText, grass;
    private View container;
    private Button btnOk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_fragment_two, container, false);

        initView(view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            init();
        }
    }

    private void init() {
        car.driveIn();
        car.spinTheWheels();
        car.stopTheWheels();

        hotel.animate().alpha(1f).setDuration(1000);
        hotelText.animate().alpha(1f).setDuration(1000);

        grass.animate().alpha(1f).translationYBy(-100f).setStartDelay(1000);

        YoYo.with(Techniques.StandUp)
                .duration(1000)
                .playOn(hotel);

        YoYo.with(Techniques.Pulse)
                .duration(1500)
                .repeat(YoYo.INFINITE)
                .playOn(hotelText);

        YoYo.with(Techniques.SlideInDown)
                .duration(1000)
                .playOn(hotelText);

        showOkBtn();
    }

    private void initView(View view) {
        car = new Car(view.findViewById(R.id.tutWholeVanRl), getActivity());

        btnOk = (Button) view.findViewById(R.id.okBtn);
        hideOkBtn();

        container = view.findViewById(R.id.tutMasterContainer);

        hotel = (ImageView) view.findViewById(R.id.tutHotelIv);
        hotelText = (ImageView) view.findViewById(R.id.tutHotelTextIv);
        grass = (ImageView) view.findViewById(R.id.tutHotelGrassIv);

        hotel.setAlpha(0f);
        hotelText.setAlpha(0f);
        grass.setAlpha(0f);

        hotel.bringToFront();
        grass.bringToFront();

        grass.setTranslationY(100f);

    }

    private void hideOkBtnAnimate() {
        btnOk.setOnClickListener(null);
        btnOk.animate().translationYBy(Util.dpToPx(getContext(), 120)).setStartDelay(500);
    }

    private void hideOkBtn() {
        btnOk.setTranslationY(Util.dpToPx(getContext(), 120));
    }

    private void showOkBtn() {
        btnOk.setOnClickListener(this);
        btnOk.animate().translationYBy(Util.dpToPx(getContext(), -120)).setStartDelay(2000);
    }

    @Override
    public void onClick(View view) {
        car.spinTheWheels();
        car.driveOut();
        container.animate().alpha(0f).setDuration(1000);
        hideOkBtnAnimate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((TutorialActivity) getActivity()).nextPage();
            }
        }, 2000);
    }
}
