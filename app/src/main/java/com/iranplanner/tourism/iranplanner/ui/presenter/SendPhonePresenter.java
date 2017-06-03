package com.iranplanner.tourism.iranplanner.ui.presenter;


import android.util.Log;

import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.SendPhoneContract;

import javax.inject.Inject;

import entity.LoginReqSend;
import entity.LoginResult;
import entity.ResultSendPhone;
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
public class SendPhonePresenter extends SendPhoneContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public SendPhonePresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
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


    @Override
    public void sendPhoneResult(String action, String phone, String cid, String androidID) {
        mView.showProgress();
        retrofit.create(SendPhoneService.class).getPhoneResult(action, phone, cid, androidID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultSendPhone>() {

                    @Override
                    public void onCompleted() {
                        mView.dismissProgress();
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultSendPhone resultSendPhone) {
                        mView.getPhoneResult(resultSendPhone);
                    }
                });
    }


    public interface SendPhoneService {

        //       api-user.php?action=phonetoken&phone=09122763719
        @GET("api-user.php")
        Observable<ResultSendPhone> getPhoneResult(@Query("action") String action,
                                                   @Query("phone") String email,
                                                   @Query("cid") String token,
                                                   @Query("andId") String androidId);


    }
}
