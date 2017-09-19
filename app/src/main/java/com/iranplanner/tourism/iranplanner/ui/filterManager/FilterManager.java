package com.iranplanner.tourism.iranplanner.ui.filterManager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by MrCode on 9/19/17.
 */

public class FilterManager implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "Filter Manager Rocks";

    public static final String FILTER_RECEIVED = "filter_received";

    private static final String BASE_URL = "";
    private static String url = "";

    private BroadcastReceiver receiver;

    private View root;

    //primitives used to enable or disable the filter types
    private boolean isSort, isPrice, isType, isRate;

    //Views corresponding with the Sort Filter
    private RadioGroup rgSort;
    private RadioButton rbSortMostSale, rbSortMinPrice, rbSortMostPrice, rbSortMostRate, rbSortMinRate;

    //Views corresponding with the Price Range Filter
    private SeekBar sbPrice;

    //Views corresponding with the Hotel Type Filter
    private CheckBox cbTypeHotel, cbTypeApartement, cbTypeLocal, cbTypeTraditional;

    //Views corresponding with the Hotel Rate Filter
    private CheckBox cbRateZero, cbRateOne, cbRateTwo, cbRateThree, cbRateFour, cbRateFive;

    public FilterManager(View root) {
        this.root = root;
    }

    private void updateUrl(String attribute, String type) {
        //update the url as a String reference for updateAdapter method to be able to use it
        //updating is recreating and concat the base url with it
    }

    private void updateAdapter() {
        //use this on filter changes
        //call update url here
        //show a progress dialog over the activity
        //make a request and get the fresh data set from api
        //disable the progress dialog
        //update the adapter used in activity with fresh data
    }

    private void initSort() {
        rgSort = (RadioGroup) root.findViewById(R.id.filterSortRg);

        rbSortMinPrice = (RadioButton) root.findViewById(R.id.filterSortMinPriceRb);
        rbSortMostSale = (RadioButton) root.findViewById(R.id.filterSortMostSaleRb);
        rbSortMostPrice = (RadioButton) root.findViewById(R.id.filterSortMostPriceRb);
        rbSortMostRate = (RadioButton) root.findViewById(R.id.filterSortRateMostRb);
        rbSortMinRate = (RadioButton) root.findViewById(R.id.filterSortMinRateRb);

        rgSort.setOnCheckedChangeListener(this);
    }

    //// TODO: 9/19/17 call the update adapter method here
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        RadioButton radioButton = (RadioButton) root.findViewById(radioGroup.getCheckedRadioButtonId());
        Log.e(TAG, String.valueOf(radioButton.getText()));
    }

    private void initPriceRange() {
        sbPrice = (SeekBar) root.findViewById(R.id.filterPriceSeekBar);

        sbPrice.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    //// TODO: 9/19/17 call the update adapter method here
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, String.valueOf(seekBar.getProgress()));
    }

    private void initHotelType() {
        cbTypeApartement = (CheckBox) root.findViewById(R.id.filterTypeApartementCb);
        cbTypeHotel = (CheckBox) root.findViewById(R.id.filterTypeHotelCb);
        cbTypeLocal = (CheckBox) root.findViewById(R.id.filterTypeLocalCb);
        cbTypeTraditional = (CheckBox) root.findViewById(R.id.filterTypeTraditionalCb);

        cbTypeHotel.setOnCheckedChangeListener(this);
        cbTypeLocal.setOnCheckedChangeListener(this);
        cbTypeApartement.setOnCheckedChangeListener(this);
        cbTypeTraditional.setOnCheckedChangeListener(this);
    }

    //// TODO: 9/19/17 call the update adapter method here
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // boolean b can be used to indicate if button is checked or not USEFUL crap
        Log.e(TAG, String.valueOf(compoundButton.getText()) + b);
    }

    private void initHotelRate() {
        cbRateZero = (CheckBox) root.findViewById(R.id.filterRate0StarCb);
        cbRateOne = (CheckBox) root.findViewById(R.id.filterRate1StarCb);
        cbRateTwo = (CheckBox) root.findViewById(R.id.filterRate2StarCb);
        cbRateThree = (CheckBox) root.findViewById(R.id.filterRate3StarCb);
        cbRateFour = (CheckBox) root.findViewById(R.id.filterRate4StarCb);
        cbRateFive = (CheckBox) root.findViewById(R.id.filterRate5StarCb);

        cbRateZero.setOnCheckedChangeListener(checkedChangeListener);
        cbRateOne.setOnCheckedChangeListener(checkedChangeListener);
        cbRateTwo.setOnCheckedChangeListener(checkedChangeListener);
        cbRateThree.setOnCheckedChangeListener(checkedChangeListener);
        cbRateFour.setOnCheckedChangeListener(checkedChangeListener);
        cbRateFive.setOnCheckedChangeListener(checkedChangeListener);
    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        //// TODO: 9/19/17 call the update adapter method here
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            // boolean b can be used to indicate if button is checked or not USEFUL crap
            Log.e(TAG, String.valueOf(compoundButton.getText()) + b);
        }
    };

    public void registerReceiver(Activity activity) {

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(TAG, "receiver is working probably ");
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(FILTER_RECEIVED);
        activity.registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(Activity activity) {
        activity.unregisterReceiver(receiver);

        Log.e(TAG, "receiver unregistered");
    }

    public String getBaseUrl() {
        return BASE_URL + url;
    }

    public void enableAll() {
        isType = true;
        isSort = true;
        isPrice = true;
        isRate = true;
    }

    public void enableSort() {
        isSort = true;

        //handle the view loadings here
        initSort();
    }

    public void enablePriceRange() {
        isPrice = true;

        //handle the view loadings here
        initPriceRange();
    }

    public void enablePlaceType() {
        isType = true;

        //handle the view loadings here
        initHotelType();
    }

    public void enablePlaceRate() {
        isRate = true;

        //handle the view Loadings here
        initHotelRate();
    }
}
