package com.iranplanner.tourism.iranplanner.ui.activity.forgetPassword;


import javax.inject.Inject;

import entity.ResetPassword;
import entity.SendEmailResetPassword;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hoda on 11-May-16.
 */
public class ForgetPasswordPresenter extends ForgetPasswordContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public ForgetPasswordPresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }




//    @Override
//    public void getLoginResult(String action, String email, String password, String token, String androidId) {
//        mView.showProgress();
//        retrofit.create(LoginService.class).getLoginResult(action, email, password, token, androidId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<LoginResult>() {
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
//                    public void onNext(LoginResult loginResult) {
//                        mView.showLoginResult(loginResult);
//                        mView.dismissProgress();
//                    }
//                });
//    }

//    @Override
//    public void getLoginPostResul(LoginReqSend loginReqSend, String cid, String androidId) {
//        mView.showProgress();
//        retrofit.create(LoginService.class).getLoginPostResul(loginReqSend, cid, androidId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<LoginResult>() {
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
//                    public void onNext(LoginResult loginResult) {
//                        mView.showLoginResult(loginResult);
//                    }
//                });
//    }
//
//    @Override
//    public void getGoogleLoginPostResult(GoogleLoginReqSend GoogleLoginReqSend, String cid, String androidId) {
//
//        mView.showProgress();
//        retrofit.create(LoginService.class).getGoogleLoginPostResult(GoogleLoginReqSend, cid, androidId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<LoginResult>() {
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
//                    public void onNext(LoginResult loginResult) {
//                        mView.showLoginResult(loginResult);
//                    }
//                });
//    }

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
    public void getResetPassword(SendEmailResetPassword SendEmailResetPassword, String token, String androidId) {
                mView.showProgress();
        retrofit.create(ForgetPasswordService.class).getResetPassword(SendEmailResetPassword, token, androidId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResetPassword>() {

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
                    public void onNext(ResetPassword resetPassword) {
                        mView.showForgetPassword(resetPassword);
                        mView.dismissProgress();
                    }
                });
    }

    public interface ForgetPasswordService {


//        https://api.parsdid.com/iranplanner/app/api-user.php?action=resetpassword&cid=fu-fMERhCSw:APA91bEfZm1aLg7QfqzrNh4M1zjEt4lEf_eCktkbKCyTHKvSGK87RBTmXoM45C1p8XLsH1sk_wo8lTEOJpbzVXUGTFsoBbq8qCKv2FBnhbFy4iNqeQDdqtfvhKI_YpPd9n9dXNywFHO_
        @POST("api-user.php?action=resetpassword")
        Observable<ResetPassword> getResetPassword(@Body SendEmailResetPassword SendEmailResetPassword, @Query("cid") String token,
                                                   @Query("andId") String androidId);



    }
}
