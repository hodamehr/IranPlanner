package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.PolylineOptions;
import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;


import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultWidgetFull;


/**
 * Created by Hoda
 */
public abstract class ItineraryContract extends Presenter<ItineraryContract.View> {
    public interface View {
        void showAttraction(ResultItineraryAttractionList resultItineraryAttractionList);

        void showError(String message);

        void showComplete();

        void showItineraryComment(ResultCommentList resultCommentList, String commentType);

        void setLoadWidget(ResultWidgetFull resultWidgetFull);

        void setIntrestedWidget(InterestResult InterestResult);

        void showAnimationWhenWaiting();

        void setIntrestValue(InterestResult InterestResult);

        ///--------------map
        public void removeMarkers();

        public void showMarkerAt(float latitude, float longitude);

        public void showDirectionOnMap(PolylineOptions rectLine);
    }


    public abstract void getItineraryAttractionList(String action, String lang, String id, String cid, String andId);

    public abstract void getItineraryCommentList(String action, String nId, String nType, String offset, String cid, String andId);

    public abstract void getWidgetResult(String action, String id, String uid, String ntype, String cid, String andId);

    public abstract void getInterest(String action, String uid, String cid, String ntype, String nid, String gtype, String gvalue, String andId);

    public abstract void doWaitingAnimation(ImageView image);

    public abstract boolean doTranslateAnimationUp(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView);

    public abstract boolean doTranslateAnimationDown(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, int height);

    //------------------------map


    //    public abstract void getDirection(String origin, String destination);
    public abstract void getDirection(String origin, String destination);
}
