package com.iranplanner.tourism.iranplanner.ui.tutorial.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by MrCode on 9/23/17.
 */

public class TutorialFragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_fragment_one, container, false);



        return view;
    }
}
