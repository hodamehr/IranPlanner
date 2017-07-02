package com.iranplanner.tourism.iranplanner.ui.presenter.abs;




import javax.inject.Inject;

import entity.GetInfoReqSend;
import entity.GetInfoResult;
import entity.InfoResult;
import entity.LoginReqSend;
import entity.LoginResult;
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
public class SettingPresenter extends SettingContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public SettingPresenter(Retrofit retrofit, View mView) {
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
    public void getUserInfoPostResult(GetInfoReqSend getInfoReqSend, String cid, String androidId) {
        mView.showProgress();
        retrofit.create(GetInfoService.class).getInfoUserPostResul(getInfoReqSend, cid, androidId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<GetInfoResult>() {

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
                    public void onNext(GetInfoResult userInfo) {
                        mView.showInfoUserResult(userInfo);
                    }
                });
    }

    public interface GetInfoService {

       @POST("api-user.php?action=getinfo")
        Observable<GetInfoResult> getInfoUserPostResul(@Body GetInfoReqSend getInfoReqSend, @Query("cid") String token,
                                                  @Query("andId") String androidId);

    }
}
