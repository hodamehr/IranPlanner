package com.iranplanner.tourism.iranplanner.ui.tutorial.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.ui.tutorial.Car;

import tools.Util;

/**
 * Created by MrCode on 9/25/17.
 */

public class TutorialFragmentFour extends Fragment {

    private Car car;
    private ImageView booth, curtain, giftTop, giftBelow, giftFeshfeshe;
    private Button btnOk;

    private View boothContainer, giftContainer;

    private int feshfesheHeight, boothHeight, curtainHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_fragment_four, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        car = new Car(view.findViewById(R.id.tutWholeVanRl), getActivity());

        btnOk = (Button) view.findViewById(R.id.okBtn);
        hideOkBtn();

        booth = (ImageView) view.findViewById(R.id.tutBoothIv);
        curtain = (ImageView) view.findViewById(R.id.tutCurtainIv);
        giftTop = (ImageView) view.findViewById(R.id.tutGiftTop);
        giftBelow = (ImageView) view.findViewById(R.id.tutGiftBelow);
        giftFeshfeshe = (ImageView) view.findViewById(R.id.tutGiftFeshfeshe);

        boothContainer = view.findViewById(R.id.tutBoothRl);
        giftContainer = view.findViewById(R.id.tutGiftRl);

        booth.bringToFront();
        curtain.bringToFront();
        boothContainer.bringToFront();

        boothContainer.setAlpha(0f);
        giftContainer.setAlpha(0f);
        giftBelow.setAlpha(0f);
        giftTop.setAlpha(0f);

        feshfesheHeight = (int) (Util.dpToPx(getContext(), 40));
        boothHeight = (int) (Util.dpToPx(getContext(), 130) / 2);
        curtainHeight = (int) (Util.dpToPx(getContext(), 50) / 2);

        giftFeshfeshe.setTranslationY(feshfesheHeight);
        giftFeshfeshe.setAlpha(0f);
        giftFeshfeshe.setScaleX(0);
        giftFeshfeshe.setScaleY(0);

        booth.setTranslationY(-boothHeight);
        booth.setAlpha(0f);

        curtain.setTranslationY(-curtainHeight);
        curtain.setAlpha(0f);
    }

    private void init() {

        car.driveIn();
        car.spinTheWheels();
        car.stopTheWheels();

        boothContainer.animate().alpha(1).setDuration(1000);
        giftContainer.animate().alpha(1).setDuration(1000);
        giftBelow.animate().alpha(1).setDuration(1000).setStartDelay(500);
        giftTop.animate().alpha(1).setDuration(1000);

        giftTop.animate().rotation(-15).translationXBy(-Util.dpToPx(getContext(), 30)).translationYBy(Util.dpToPx(getContext(), 10)).setStartDelay(1300).setDuration(1000);
        giftFeshfeshe.animate().translationYBy(-feshfesheHeight).alpha(1).setStartDelay(2400);

        booth.animate().alpha(1).translationYBy(boothHeight).setDuration(600).setStartDelay(1000);
        curtain.animate().alpha(1).translationYBy(curtainHeight).setDuration(600).setStartDelay(1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                giftFeshfeshe.clearAnimation();
                YoYo.with(Techniques.Swing)
                        .repeat(YoYo.INFINITE)
                        .playOn(giftFeshfeshe);
            }
        }, 2500);

        showOkBtn();

    }

    private void hideOkBtnAnimate() {
        btnOk.setOnClickListener(null);
        btnOk.animate().translationYBy(Util.dpToPx(getContext(), 120)).setStartDelay(500);
    }

    private void hideOkBtn() {
        btnOk.setTranslationY(Util.dpToPx(getContext(), 120));
    }

    private void showOkBtn() {
//        btnOk.setOnClickListener(this);
        btnOk.animate().translationYBy(Util.dpToPx(getContext(), -120)).setStartDelay(2000);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            init();
    }
}
