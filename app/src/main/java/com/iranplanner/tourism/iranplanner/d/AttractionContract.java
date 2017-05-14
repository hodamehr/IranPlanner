package com.iranplanner.tourism.iranplanner.d;


import android.widget.ImageView;
import android.widget.RelativeLayout;
import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultWidgetFull;


/**
 * Created by Hoda
 */
public interface AttractionContract {
    interface View {
        void showAttraction(ResultItineraryAttractionList resultItineraryAttractionList);

        void showError(String message);

        void showComplete();

        void showItineraryComment(ResultCommentList resultCommentList, String commentType);

        void setLoadWidget(ResultWidgetFull resultWidgetFull);

        void setIntrestedWidget(InterestResult InterestResult);

        void showAnimationWhenWaiting();
    }

    interface Presenter {

        void getItineraryAttractionList(String action,
                                        String lang,
                                        String id);

        void getItineraryCommentList(String action,
                                     String nId,
                                     String nType,
                                     String offset);

        void getWidgetResult(String action,
                             String id,
                             String uid,
                             String ntype);

        void getInterest(String action,
                         String uid,
                         String cid,
                         String ntype,
                         String nid,
                         String gtype,
                         String gvalue);

        void doWaitingAnimation(ImageView image);
        boolean doTranslateAnimationUp(RelativeLayout relativeLayout,RelativeLayout relativeLayout2, ImageView imageView);
        boolean doTranslateAnimationDown(RelativeLayout relativeLayout,RelativeLayout relativeLayout2, ImageView imageView,int height);
    }


}
