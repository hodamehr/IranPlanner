package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;


import javax.inject.Inject;

import entity.ReservationRequestFull;
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
public class ReservationRequestFullPresenter extends ReservationRequestFullContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public ReservationRequestFullPresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }


    @Override
    public void getReservationRequestFull(String action, String lang, String uid, String id, String limit, String offset, String cid, String andId) {
        mView.showProgress();
        retrofit.create(ReservationHotelFullService.class).getReservationRequestFull(action, lang, uid, id, limit, offset, cid, andId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ReservationRequestFull>() {

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
                    public void onNext(ReservationRequestFull reservationRequestFull) {
                        mView.showReservationRequestFull(reservationRequestFull);
                    }
                });
    }


    public interface ReservationHotelFullService {
        //        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=req_user_full&lang=fa&uid=792147600796866&id=1115026211657201
        @GET("api-reservation.php")
        Observable<ReservationRequestFull> getReservationRequestFull(@Query("action") String action,
                                                                     @Query("lang") String lang,
                                                                     @Query("uid") String uid,
                                                                     @Query("id") String id,
                                                                     @Query("limit") String limit,
                                                                     @Query("offset") String offset,
                                                                     @Query("cid") String cid,
                                                                     @Query("andId") String andId);

    }
}
