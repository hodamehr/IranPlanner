package com.iranplanner.tourism.iranplanner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

/**
 * Created by h.vahidimehr on 19/02/2017.
 */

public class ShowTravelToolsFragment extends StandardFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_tools_travel_show, container, false);
        ImageView toolsImg= (ImageView) view.findViewById(R.id.toolsImg);
        TextView toolsText= (TextView) view.findViewById(R.id.toolsText);
        toolsText.setText("jhjbjhhhh");
        return view;
    }
}
