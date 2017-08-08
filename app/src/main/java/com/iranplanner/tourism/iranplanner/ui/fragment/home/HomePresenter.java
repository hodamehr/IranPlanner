package com.iranplanner.tourism.iranplanner.ui.fragment.home;


import android.view.View;

import com.iranplanner.tourism.iranplanner.ui.fragment.home.HomeContract;

import javax.inject.Inject;

import entity.GetHomeResult;
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
public class HomePresenter extends HomeContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public HomePresenter(Retrofit retrofit, View mView) {
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
    public void getHome(String action, String type, String value, String token, String androidId) {
        mView.showProgress();
        retrofit.create(HomeService.class)
                .getHomeResult(action, type, value, token, androidId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<GetHomeResult>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.dismissProgress();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.dismissProgress();

                    }

                    @Override
                    public void onNext(GetHomeResult getHomeResult) {
                        mView.ShowHomeResult(getHomeResult);
                    }
                });
    }

    //    action=home&type=city&value=309
    public interface HomeService {
        @GET("api-home.php")
        Observable<GetHomeResult> getHomeResult(
                @Query("action") String action,
                @Query("type") String type,
                @Query("value") String value,
                @Query("cid") String cid,
                @Query("andId") String androidId);

    }
}
