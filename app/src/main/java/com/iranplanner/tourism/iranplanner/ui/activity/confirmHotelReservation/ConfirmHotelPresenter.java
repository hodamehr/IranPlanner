package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;


import javax.inject.Inject;

import entity.RequestLodgingReservationMain;
import entity.ReservationRequestComplete;
import entity.ReservationRequestDeleteRoom;
import entity.ResultLodgingReservation;
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

    @Override
    public void getReservationRequestComplete(String origin, String bundleId, String uid, String nType, String offset) {
        mView.showProgress();
        retrofit.create(ConfirmHotelService.class).getReservationRequestComplete(origin, bundleId, uid, nType, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ReservationRequestComplete>() {

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
                    public void onNext(ReservationRequestComplete reservationRequestComplete) {
                        mView.showReservationRequestComplete(reservationRequestComplete);
                    }
                });
    }

    @Override
    public void getReservationRequestDeleteRoom(String origin, String bundleId, String uid, String nType, String offset) {
        mView.showProgress();
        retrofit.create(ConfirmHotelService.class).getReservationRequestDeleteRoom(origin, bundleId, uid, nType, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ReservationRequestDeleteRoom>() {

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
                    public void onNext(ReservationRequestDeleteRoom reservationRequestComplete) {
                        mView.showReservationRequestDeleteRoom(reservationRequestComplete);
                    }
                });
    }


    public interface ConfirmHotelService {
        //        https://api.parsdid.com//iranplanner/app/api-lodging.php?action=reservation
        //        https://api.parsdid.com//iranplanner/app/api-reservation.php?action=prereservation
        //        https://api.parsdid.com//iranplanner/app/api-reservation.php?action=prereservation
        @POST("api-reservation.php?action=updatereservation")
        Observable<RequestLodgingReservationMain> sendRequestReservation(@Body ResultLodgingReservation resultLodgingReservation,
                                                                         @Query("cid") String token,
                                                                         @Query("andId") String androidId);

        //        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=complete_bundle&id=1505305421080358&uid=642148896888068
        @GET("api-reservation.php?")
        Observable<ReservationRequestComplete> getReservationRequestComplete(@Query("action") String action,
                                                                             @Query("id") String bundleId,
                                                                             @Query("uid") String uid,
                                                                             @Query("cid") String cid,
                                                                             @Query("andId") String andId);
//      https://api.parsdid.com/iranplanner/app/api-reservation.php?action=delete_request&id=15053045751261880&uid=642148896888068
        @GET("api-reservation.php?")
        Observable<ReservationRequestDeleteRoom> getReservationRequestDeleteRoom(@Query("action") String action,
                                                                                 @Query("id") String bundleId,
                                                                                 @Query("uid") String uid,
                                                                                 @Query("cid") String cid,
                                                                                 @Query("andId") String andId);
    }
}
