package com.iranplanner.tourism.iranplanner.ui.activity.attractionDetails;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.PolylineOptions;
import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultWidgetFull;


/**
 * Created by Hoda
 */
public abstract class AttractionDetailContract extends Presenter<AttractionDetailContract.View> {
    public interface View {

        void showError(String message);

        void showComplete();

        void showComment(ResultCommentList resultCommentList, String commentType);

        void setLoadWidget(ResultWidgetFull resultWidgetFull);

        void setIntrestedWidget(InterestResult InterestResult);

        void showAnimationWhenWaiting();

        void setIntrestValue(InterestResult InterestResult);

        ///--------------map


        public void showDirectionOnMap(PolylineOptions rectLine);
    }



    public abstract void getAttractionCommentList(String action, String nId, String nType, String offset, String cid, String andId);

    public abstract void getWidgetResult(String action, String id, String uid, String ntype, String cid, String andId);

    public abstract void getInterest(String action, String uid, String cid, String ntype, String nid, String gtype, String gvalue, String andId);

    public abstract void doWaitingAnimation(ImageView image);

    public abstract boolean doTranslateAnimationUp(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView);

    public abstract boolean doTranslateAnimationDown(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, int height);

    //------------------------map


    //    public abstract void getDirection(String origin, String destination);
    public abstract void getDirection(String origin, String destination);
}
