package com.iranplanner.tourism.iranplanner.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iranplanner.tourism.iranplanner.R;


/**
 * Created by phelat on 9/29/16.
 */
public class FirstItem extends Fragment {

    public FirstItem() {
        super();
    }

    @Override public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container    ,
                                       Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.first_item_fragment, container, false);

        return rootView;
    }

}
