package com.iranplanner.tourism.iranplanner.ui.presenter;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import javax.inject.Inject;

import entity.InterestResult;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultWidgetFull;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionContract;

/**
 * Created by h.vahidimehr on 12/05/2017.
 */

public class AttractionPresenter extends AttractionContract {

    public Retrofit retrofit;
    AttractionContract.View mView;
    RotateAnimation rotate;

    @Inject
    public AttractionPresenter(Retrofit retrofit, AttractionContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void getItineraryAttractionList(String action, String lang, String id) {
        retrofit.create(AttractionService.class)
                .getItineraryAttractionList(action, lang, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultItineraryAttractionList>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultItineraryAttractionList resultItineraryList) {
//                        mView.showItineraries(resultItineraryList, "fromCityToCity");
                        mView.showAttraction(resultItineraryList);

                    }
                });
    }

    @Override
    public void getItineraryCommentList(String action, String nId, String nType, String offset) {
        retrofit.create(AttractionService.class)
                .getItineraryCommentList(action, nId, nType, offset).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultCommentList>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultCommentList resultCommentList) {
//                        mView.showAttraction(resultCommentList);
                        mView.showItineraryComment(resultCommentList, "Itinerary");

                    }
                });

    }

    @Override
    public void getWidgetResult(String action, String id, String uid, String ntype) {
        retrofit.create(AttractionService.class)
                .getWidgetResult(action, id, uid, ntype).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultWidgetFull>() {

                    @Override
                    public void onCompleted() {
//                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultWidgetFull resultWidgetFull) {
//                        mView.showAttraction(resultCommentList);
                        mView.setLoadWidget(resultWidgetFull);

                    }
                });
    }

    @Override
    public void getInterest(String action, String uid, String cid, String ntype, String nid, String gtype, String gvalue) {
        retrofit.create(AttractionService.class)
                .getInterest(action, uid, cid, ntype, nid, gtype, gvalue).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<InterestResult>() {

                    @Override
                    public void onCompleted() {
                        rotate.setRepeatCount(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        rotate.setRepeatCount(0);
                    }

                    @Override
                    public void onNext(InterestResult interestResult) {
                        mView.setIntrestedWidget(interestResult);
                        rotate.setRepeatCount(0);

                    }
                });
    }

    @Override
    public void doWaitingAnimation(ImageView image) {

        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(5);
        rotate.setDuration(5000);
        rotate.setInterpolator(new LinearInterpolator());
        image.startAnimation(rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mView.showAnimationWhenWaiting();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public boolean doTranslateAnimationUp(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView) {
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(relativeLayout, "translationY", 0),
                ObjectAnimator.ofFloat(relativeLayout2, "translationY", 0),
                ObjectAnimator.ofFloat(imageView, "translationY", 0));
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
        return false;

    }

    @Override
    public boolean doTranslateAnimationDown(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, int height) {
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(relativeLayout, "translationY", height),
                ObjectAnimator.ofFloat(relativeLayout2, "translationY", height),
                ObjectAnimator.ofFloat(imageView, "translationY", -55));
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
        return true;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }


    public interface AttractionService {
        @GET("api-itinerary.php")
        Observable<ResultItineraryAttractionList> getItineraryAttractionList(
                @Query("action") String action,
                @Query("lang") String lang,
                @Query("id") String id
        );

        @GET("api-com.iranplanner.tourism.iranplanner.di.data.php?action=pagecomments&nid=1&ntype=attraction&offset=10")
        Observable<ResultCommentList> getItineraryCommentList(
                @Query("action") String action,
                @Query("nid") String nId,
                @Query("ntype") String nType,
                @Query("offset") String offset);


        @GET("api-com.iranplanner.tourism.iranplanner.di.data.php?action=nodeuser&id=28439&uid=323148788221963&ntype=itinerary")
        Observable<ResultWidgetFull> getWidgetResult(@Query("action") String action,
                                                     @Query("id") String id,
                                                     @Query("uid") String uid,
                                                     @Query("ntype") String ntype);

        @GET("api-com.iranplanner.tourism.iranplanner.di.data.php?action=widget&uid=792147600796866&cid=1&ntype=itinerary&nid=21905&gtype=bookmark&gvalue=1")
        Observable<InterestResult> getInterest(
                @Query("action") String action,
                @Query("uid") String uid,
                @Query("cid") String cid,
                @Query("ntype") String ntype,
                @Query("nid") String nid,
                @Query("gtype") String gtype,
                @Query("gvalue") String gvalue);

    }
}
