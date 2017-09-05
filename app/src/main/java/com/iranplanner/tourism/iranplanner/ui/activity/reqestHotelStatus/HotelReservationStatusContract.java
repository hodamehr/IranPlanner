package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;


import entity.ReservationRequestList;
import entity.ResultLodgingHotel;

/**
 * Created by Hoda
 */
public abstract class HotelReservationStatusContract {
    public interface View {
        void showHotelReservationStatusList(ReservationRequestList reservationRequestList);

        void showError(String message);

        void showComplete();

        void dismissProgress();

        void showProgress();
    }



    public abstract void getHotelReservationStatusList(String action,
                                                       String lang,
                                                       String uid,
                                                       String type,
                                                       String limit,
                                                       String offset,
                                                       String cid,
                                                       String andId);

}
