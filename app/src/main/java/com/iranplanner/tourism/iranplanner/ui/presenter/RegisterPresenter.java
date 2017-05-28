package com.iranplanner.tourism.iranplanner.ui.presenter;


import com.iranplanner.tourism.iranplanner.ui.presenter.abs.RegisterContract;

import javax.inject.Inject;

import entity.RegisterReqSend;
import entity.ResultLodgingList;
import entity.ResultRegister;
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
public class RegisterPresenter extends RegisterContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public RegisterPresenter(Retrofit retrofit, View mView) {
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
    public void getRegisterResult(String action, String email, String password, String fname, String lname, String gender, String cid, String phone) {
        mView.showProgress();
        retrofit.create(RegisterService.class).getRegisterResult(action, email, password, fname, lname, gender, cid, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultRegister>() {

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
                    public void onNext(ResultRegister resultRegister) {
                        mView.showRegisterMessage(resultRegister);


                    }
                });
    }

    @Override
    public void getRegisterLoginResult(RegisterReqSend registerReqSend,String cid, String andId) {
        mView.showProgress();
        retrofit.create(RegisterService.class).getRegisterPostResult(registerReqSend,cid,andId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultRegister>() {

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
                    public void onNext(ResultRegister resultRegister) {
                        mView.showRegisterMessage(resultRegister);
                        mView.dismissProgress();
                    }
                });
    }

    public interface RegisterService {

        @GET("api-user.php")
        Observable<ResultRegister> getRegisterResult(@Query("action") String action,
                                                     @Query("email") String email,
                                                     @Query("password") String password,
                                                     @Query("fname") String fname,
                                                     @Query("lname") String lname,
                                                     @Query("gender") String gender,
                                                     @Query("cid") String cid,
                                                     @Query("phone") String phone);

        @POST("api-user.php?action=register")
        Observable<ResultRegister> getRegisterPostResult(@Body RegisterReqSend registerReqSend, @Query("cid") String cid, @Query("andId") String andId);
    }
}
