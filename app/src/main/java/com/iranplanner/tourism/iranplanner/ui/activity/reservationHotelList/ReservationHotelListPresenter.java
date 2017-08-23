package com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList;


import javax.inject.Inject;

import entity.ResultLodgingHotel;
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
public class ReservationHotelListPresenter extends ReservationHotelListContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public ReservationHotelListPresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void getHotelReserve(String action, String idHotel, String limit, String offset, String cid, String andID) {
        mView.showProgress();
        retrofit.create(ReservationHotelListService.class).getHotelReserve(action, idHotel, limit, offset, cid, andID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultLodgingHotel>() {

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
                    public void onNext(ResultLodgingHotel resultLodgingHotel) {
                        mView.showHotelReserveList(resultLodgingHotel);
                    }
                });
    }


    public interface ReservationHotelListService {

        @GET("api-lodging.php")
        Observable<ResultLodgingHotel> getHotelReserve(@Query("action") String action,
                                                       @Query("id") String idHotel,
                                                       @Query("limit") String limit,
                                                       @Query("offset") String offset,
                                                       @Query("cid") String cid,
                                                       @Query("andId") String andId);

    }
}
