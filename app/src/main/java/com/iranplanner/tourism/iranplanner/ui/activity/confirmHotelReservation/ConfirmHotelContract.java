package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.RequestLodgingReservationMain;
import entity.ReservationRequestComplete;
import entity.ReservationRequestDeleteRoom;
import entity.ResultLodgingReservation;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Hoda
 */
public abstract class ConfirmHotelContract extends Presenter<ConfirmHotelContract.View> {
    public interface View {
        void showHotelReservationResult(RequestLodgingReservationMain loginResult);
        void showReservationRequestComplete(ReservationRequestComplete reservationRequestComplete);
        void showReservationRequestDeleteRoom(ReservationRequestDeleteRoom reservationRequestDeleteRoom);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }


    public abstract void sendRequestReservation(ResultLodgingReservation request, String cid,
                                                String androidId);

    public abstract void getReservationRequestComplete(String origin,
                                                       String bundleId,
                                                       String uid,
                                                       String nType,
                                                       String offset);

    public abstract void  getReservationRequestDeleteRoom(String origin,
                                                          String bundleId,
                                                          String uid,
                                                          String nType,
                                                          String offset);
}
