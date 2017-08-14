package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;


import javax.inject.Inject;

import entity.LoginReqSend;
import entity.RequestLodgingReservationMain;
import entity.ResultLodgingReservation;
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
public class ConfirmHotelPresenter extends ConfirmHotelContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public ConfirmHotelPresenter(Retrofit retrofit, View mView) {
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
    public void sendRequestReservation(ResultLodgingReservation request, String cid, String androidId) {
        mView.showProgress();
        retrofit.create(ConfirmHotelService.class).sendRequestReservation(request, cid, androidId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<RequestLodgingReservationMain>() {

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
                    public void onNext(RequestLodgingReservationMain loginResult) {
                        mView.showHotelReservationResult(loginResult);
                    }
                });
    }


    public interface ConfirmHotelService {
//        https://api.parsdid.com//iranplanner/app/api-lodging.php?action=reservation
        @POST("api-lodging.php?action=reservation")
        Observable<RequestLodgingReservationMain> sendRequestReservation(@Body ResultLodgingReservation resultLodgingReservation, @Query("cid") String token,
                                                                         @Query("andId") String androidId);


    }
}
