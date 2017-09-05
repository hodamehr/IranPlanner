package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;


import entity.ReservationRequestFull;
import entity.ResultLodgingHotel;

/**
 * Created by Hoda
 */
public abstract class ReservationRequestFullContract {
    public interface View {
        void showReservationRequestFull(ReservationRequestFull reservationRequestFull);

        void showError(String message);

        void showComplete();


        void dismissProgress();

        void showProgress();
    }



    public abstract void getReservationRequestFull(String action,
                                                   String lang,
                                                   String uid,
                                                   String id,
                                                   String limit,
                                                   String offset,
                                                   String cid,
                                                   String andId);

}
