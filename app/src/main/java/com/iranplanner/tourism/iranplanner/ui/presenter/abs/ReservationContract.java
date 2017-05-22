package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.PolylineOptions;
import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultLodgingList;
import entity.ResultWidgetFull;


/**
 * Created by Hoda
 */
public abstract class ReservationContract extends Presenter<ReservationContract.View> {
    public interface View {
        void showLodgingList(ResultLodgingList resultLodgingList);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }


    public abstract void getLodgingList(String action,
                                        String city);


}
