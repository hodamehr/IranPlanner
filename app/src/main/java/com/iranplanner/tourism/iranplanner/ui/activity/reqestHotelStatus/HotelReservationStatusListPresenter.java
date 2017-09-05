package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;


import javax.inject.Inject;

import entity.ReservationRequestList;
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
public class HotelReservationStatusListPresenter extends HotelReservationStatusContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public HotelReservationStatusListPresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }


    @Override
    public void getHotelReservationStatusList(String action, String lang, String uid, String type, String limit, String offset, String cid, String andId) {
        mView.showProgress();
        retrofit.create(HotelReservationStatusLisService.class).getHotelReservationStatusList(action, lang, uid, type, limit, offset, cid, andId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ReservationRequestList>() {

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
                    public void onNext(ReservationRequestList resultLodgingHotel) {
                        mView.showHotelReservationStatusList(resultLodgingHotel);
                    }
                });
    }


    public interface HotelReservationStatusLisService {
        //        https://api.parsdid.com/iranplanner/app/api-reservation.php?action=req_user_list&lang=fa&uid=792147600796866&type=1
        @GET("api-reservation.php")
        Observable<ReservationRequestList> getHotelReservationStatusList(@Query("action") String action,
                                                                         @Query("lang") String lang,
                                                                         @Query("uid") String uid,
                                                                         @Query("type") String type,
                                                                         @Query("limit") String limit,
                                                                         @Query("offset") String offset,
                                                                         @Query("cid") String cid,
                                                                         @Query("andId") String andId);

    }
}
