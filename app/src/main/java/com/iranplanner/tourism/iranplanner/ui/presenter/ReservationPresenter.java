package com.iranplanner.tourism.iranplanner.ui.presenter;


import android.util.Log;

import com.iranplanner.tourism.iranplanner.ui.presenter.abs.MainSearchContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.ReservationContract;

import javax.inject.Inject;

import entity.ResultItineraryList;
import entity.ResultLodgingList;
import retrofit2.Call;
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
public class ReservationPresenter extends ReservationContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public ReservationPresenter(Retrofit retrofit, View mView) {
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
    public void getLodgingList(String action, String city) {
        mView.showProgress();
        retrofit.create(ReservationService.class).getLodgingReserve(action, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultLodgingList>() {

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
                    public void onNext(ResultLodgingList resultItineraryList) {
                        mView.showLodgingList(resultItineraryList);


                    }
                });
    }

    public interface ReservationService {

        @GET("api-lodging.php?action=list&city=342")
        Observable<ResultLodgingList> getLodgingReserve(
                @Query("action") String action,
                @Query("city") String city);

    }
}
