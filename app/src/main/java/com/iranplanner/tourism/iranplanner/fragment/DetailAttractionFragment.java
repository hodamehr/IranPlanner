package com.iranplanner.tourism.iranplanner.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import entity.ResultItineraryAttraction;
import tools.Util;

/**
 * Created by Hoda on 21/01/2017.
 */
public class DetailAttractionFragment extends StandardFragment {
    private Context context;
    TextView attractionName, attractionPlace, textTimeDuration, textEntranceFee, attractionType, textBody;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attraction_detail, container, false);
        attractionName = (TextView) view.findViewById(R.id.attractionName);
        attractionPlace = (TextView) view.findViewById(R.id.attractionPlace);
        textTimeDuration = (TextView) view.findViewById(R.id.textTimeDuration);
        textEntranceFee = (TextView) view.findViewById(R.id.textEntranceFee);
        attractionType = (TextView) view.findViewById(R.id.attractionType);
        textBody = (TextView) view.findViewById(R.id.textBody);
        ImageView imageAttraction = (ImageView) view.findViewById(R.id.imageAttraction);
        Bundle bundle = getArguments();
        ResultItineraryAttraction attraction = (entity.ResultItineraryAttraction) bundle.getSerializable("ResultItineraryAttraction");
        attractionName.setText(attraction.getAttractionTitle());
        textBody.setText(Html.fromHtml(attraction.getAttarctionBody()));
        attractionPlace.setText(attraction.getProvinceTitle() + " - " + attraction.getCityTitle());
        int totalMinute = Integer.parseInt(attraction.getAttarctionEstimatedTime());
        Util.convertMinuteToHour(totalMinute, textTimeDuration);


        if (attraction.getAttractionPrice() == null) {
            textEntranceFee.setText("ندارد");
        } else {
            textEntranceFee.setText(attraction.getAttractionPrice().toString());
        }
        attractionType.setText(attraction.getAttarctionItineraryTypeTitle());
        return view;
    }
}
