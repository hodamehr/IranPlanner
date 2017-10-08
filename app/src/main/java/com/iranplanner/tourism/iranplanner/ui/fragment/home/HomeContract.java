package com.iranplanner.tourism.iranplanner.ui.fragment.home;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.GetHomeResult;
import entity.ResultEvents;
import entity.ShowAttractionListMore;
import entity.ShowAttractionMoreList;


/**
 * Created by Hoda
 */
public abstract class HomeContract extends Presenter<HomeContract.View> {
    public interface View {

        void showError(String message);

        void showComplete();

        void ShowHomeResult(GetHomeResult GetHomeResult);

        void ShowAttractionLists(ShowAttractionListMore getAttractionList);
        void ShowEventLists(ResultEvents resultEvents);

        void showProgress();

        void dismissProgress();

    }


    public abstract void getHome(String action,
                                 String type,
                                 String value,
                                 String token,
                                 String androidId);

    public abstract void getAttractionMore(String action
            , String lang
            , String city
            , String offset
            , String cid
            , String andId
            , String type);

    public abstract void getEventMore(
            String action,
            String lang,
            String id,
            String type,
            String cid,
            String androidId);
}
