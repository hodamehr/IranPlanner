package com.iranplanner.tourism.iranplanner.ui.presenter;


import android.util.Log;

import com.iranplanner.tourism.iranplanner.ui.presenter.abs.CommentContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.ReservationContract;

import javax.inject.Inject;

import entity.CommentSend;
import entity.ResultCommentList;
import entity.ResultLodgingList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hoda on 11-May-16.
 */
public class CommentPresenter extends CommentContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public CommentPresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }


    @Override
    public void getCommentList(String action, String nid, String ntype, String offset) {
        retrofit.create(CommentService.class)
                .getItineraryCommentList(action, nid, ntype, offset).subscribeOn(Schedulers.io())
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
                    public void onNext(ResultCommentList resultItineraryList) {
                        mView.showComments(resultItineraryList);
                    }
                });
//        retrofit.create(AttractionPresenter.AttractionService.class)
//                .getItineraryCommentList(action, nid, ntype, offset).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<ResultCommentList>() {
//
//                    @Override
//                    public void onCompleted() {
//                        mView.showComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(ResultCommentList resultCommentList) {
//                        mView.showComments(resultCommentList);
//                        Log.e("fds","fsfs");
//                    }
//                });

    }

    @Override
    public void callInsertComment(CommentSend commentSend) {
        retrofit.create(CommentService.class)
                .callInsertComment(commentSend).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultCommentList>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.commentResult(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultCommentList resultItineraryList) {
                        mView.sendCommentMessage(resultItineraryList);
                    }
                });
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

    public interface CommentService {


//        @GET("api-com.iranplanner.tourism.iranplanner.di.data.php?action=pagecomments&nid=1&ntype=attraction&offset=10")
//        Observable<ResultCommentList> getCommentList(
//                @Query("action") String action,
//                @Query("nid") String nid,
//                @Query("ntype") String ntype,
//                @Query("offset") String offset);

        @GET("api-data.php?action=pagecomments&nid=1&ntype=attraction&offset=10")
        Observable<ResultCommentList> getItineraryCommentList(
                @Query("action") String action,
                @Query("nid") String nId,
                @Query("ntype") String nType,
                @Query("offset") String offset);

        @POST("api-data.php?action=comment")
        Observable<ResultCommentList> callInsertComment(@Body CommentSend request);
    }
}
