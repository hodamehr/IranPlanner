package com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore;


import javax.inject.Inject;

import entity.ShowAtractionDetailMore;
import entity.ShowAttractionFull;
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
public class AttractionListMorePresenter extends AttractionListMoreContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public AttractionListMorePresenter(Retrofit retrofit, View mView) {
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
    public void getAttractionMore(String action, String lang, String city, String offset, String cid, String andId) {
        mView.showProgress();
        retrofit.create(AttractionMoreListService.class)
                .getAttractionMore(action, lang, city, offset, cid, andId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ShowAttractionMoreList>() {

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
                    public void onNext(ShowAttractionMoreList showAttractionList) {
                        mView.ShowAttractionLists(showAttractionList);
                    }
                });
    }

    @Override
    public void getAttractionDetailNear(String action, String id, String type, String offset, String cid, String androidId) {
        mView.showProgress();
        retrofit.create(AttractionMoreListService.class)
                .getAttractionDetailNear(action, id, type, offset, cid, androidId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ShowAtractionDetailMore>() {

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
                    public void onNext(ShowAtractionDetailMore showAttractionFull) {
                        mView.showAttractionDetail(showAttractionFull);
                    }
                });
    }


    //    action=home&type=city&value=309
    public interface AttractionMoreListService {


        @GET("api-attraction.php")
        Observable<ShowAttractionMoreList> getAttractionMore(
                @Query("action") String action,
                @Query("lang") String type,
                @Query("city") String value,
                @Query("offset") String offset,
                @Query("cid") String cid,
                @Query("andId") String androidId);

        //        https://api.parsdid.com/iranplanner/app/api-attraction.php?action=full&id=24283&lang=fa&offset=20
        @GET("api-attraction.php")
        Observable<ShowAtractionDetailMore> getAttractionDetailNear(
                @Query("action") String action,
                @Query("id") String id,
                @Query("lang") String type,
                @Query("offset") String offset,
                @Query("cid") String cid,
                @Query("andId") String androidId);
    }
}
