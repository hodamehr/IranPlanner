package com.iranplanner.tourism.iranplanner.ui.activity.getToken;


import javax.inject.Inject;

import entity.ResultPhoneVerify;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hoda on 11-May-16.
 */
public class GetTokenPresenter extends GetTokenContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public GetTokenPresenter(Retrofit retrofit, View mView) {
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
    public void getToken(String action, String phone, String token, String cid, String androidID) {
        mView.showProgress();
        retrofit.create(GetTokenService.class).getPhoneResult(action, phone,token, cid, androidID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultPhoneVerify>() {

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
                    public void onNext(ResultPhoneVerify resultPhoneVerify) {
                        mView.getTokenResult(resultPhoneVerify);
                    }
                });
    }


//    @Override
//    public void sendPhoneResult(String action, String phone, String cid, String androidID) {
//        mView.showProgress();
//        retrofit.create(SendPhoneService.class).getPhoneResult(action, phone, cid, androidID)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<ResultSendPhone>() {
//
//                    @Override
//                    public void onCompleted() {
//                        mView.dismissProgress();
//                        mView.showComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissProgress();
//                        mView.showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(ResultSendPhone resultSendPhone) {
//                        mView.getPhoneResult(resultSendPhone);
//                    }
//                });
//    }




    public interface GetTokenService {

//        api.parsdid.com/iranplanner/app/api-user.php?action=phoneverify&phone=09350891239&token=24443
        @GET("api-user.php")
        Observable<ResultPhoneVerify> getPhoneResult(@Query("action") String action,
                                                     @Query("phone") String email,
                                                     @Query("token") String tokenNumbe,
                                                     @Query("cid") String tokenPhone,
                                                     @Query("andId") String androidId);


    }
}
