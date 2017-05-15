package com.iranplanner.tourism.iranplanner.d;


import android.widget.ImageView;
import android.widget.RelativeLayout;

import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultWidgetFull;
import ui.Presenter;


/**
 * Created by Hoda
 */
public abstract class AttractionContract extends Presenter<AttractionContract.View> {
    public interface View {
        void showAttraction(ResultItineraryAttractionList resultItineraryAttractionList);

        void showError(String message);

        void showComplete();

        void showItineraryComment(ResultCommentList resultCommentList, String commentType);

        void setLoadWidget(ResultWidgetFull resultWidgetFull);

        void setIntrestedWidget(InterestResult InterestResult);

        void showAnimationWhenWaiting();
        void setIntrestValue(InterestResult InterestResult);
    }


    public abstract void getItineraryAttractionList(String action,
                                                    String lang,
                                                    String id);

    public abstract void getItineraryCommentList(String action,
                                                 String nId,
                                                 String nType,
                                                 String offset);

    public abstract void getWidgetResult(String action,
                                         String id,
                                         String uid,
                                         String ntype);

    public abstract void getInterest(String action,
                                     String uid,
                                     String cid,
                                     String ntype,
                                     String nid,
                                     String gtype,
                                     String gvalue);

    public abstract void doWaitingAnimation(ImageView image);

    public abstract boolean doTranslateAnimationUp(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView);

    public abstract boolean doTranslateAnimationDown(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, int height);


}
