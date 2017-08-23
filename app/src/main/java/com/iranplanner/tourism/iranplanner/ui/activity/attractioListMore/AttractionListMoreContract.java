package com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore;


import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResultCommentList;
import entity.ShowAtractionDetailMore;
import entity.ShowAttractionFull;
import entity.ShowAttractionMoreList;

/**
 * Created by Hoda
 */
public abstract class AttractionListMoreContract extends Presenter<AttractionListMoreContract.View> {
    public interface View {
        void showComments(ResultCommentList resultCommentList);

        void sendCommentMessage(ResultCommentList resultCommentList);

        void showError(String message);

        void commentResult(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();

        void ShowAttractionLists(ShowAttractionMoreList showAttractionList);
        void showAttractionDetail(ShowAtractionDetailMore showAttractionFull);
    }

    public abstract void getAttractionMore(String action
            , String lang
            , String city
            , String offset
            , String cid
            , String andId);

    public abstract void getAttractionDetailNear(String action,
                                                 String id,
                                                 String type,
                                                 String offset,
                                                 String cid,
                                                 String androidId);

}
