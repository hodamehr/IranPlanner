package com.iranplanner.tourism.iranplanner.ui.fragment.home;


import javax.inject.Inject;

import entity.GetHomeResult;
import entity.ShowAttractionListMore;
import entity.ShowAttractionMoreList;
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

    @Override
    public void getAttractionMore(String action, String lang, String city, String offset,String cid, String andId,String type) {
        mView.showProgress();
        retrofit.create(HomeService.class)
                .getAttractionMore(action, lang, city,offset, cid, andId,type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ShowAttractionListMore>() {

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
                    public void onNext(ShowAttractionListMore showAttractionList) {
                        mView.ShowAttractionLists(showAttractionList);
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

        //        https://api.parsdid.com//iranplanner/app/api-attraction.php?action=search&lang=fa&city=617
        @GET("api-attraction.php")
        Observable<ShowAttractionListMore> getAttractionMore(
                @Query("action") String action,
                @Query("lang") String lang,
                @Query("city") String value,
                @Query("offset") String offset,
                @Query("cid") String cid,
                @Query("andId") String androidId,
                @Query("type") String attractionType
                );
    }
}