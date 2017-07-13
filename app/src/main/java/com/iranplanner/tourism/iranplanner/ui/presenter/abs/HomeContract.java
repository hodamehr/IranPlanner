package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.PolylineOptions;
import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.GetHomeResult;
import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultWidgetFull;


/**
 * Created by Hoda
 */
public abstract class HomeContract extends Presenter<HomeContract.View> {
    public interface View {

        void showError(String message);
        void showComplete();
        void getHomeResult(GetHomeResult GetHomeResult);
        void showProgress();
        void dismissProgress();

    }


    public abstract void getHome(String action,
                                 String type,
                                 String value,
                                 String token,
                                 String androidId);
}
