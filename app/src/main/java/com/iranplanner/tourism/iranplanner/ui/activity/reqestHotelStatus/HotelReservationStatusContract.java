package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;


import entity.ReservationRequestList;
import entity.ResultBundleStatus;
import entity.ResultLodgingHotel;
import entity.ResultReservationReqStatus;

/**
 * Created by Hoda
 */
public abstract class HotelReservationStatusContract {
    public interface View {
        void showHotelReservationStatusList(ReservationRequestList reservationRequestList);
        void showHotelReservationBundleStatus(ResultBundleStatus resultBundleStatus);

        void showError(String message);

        void showComplete();

        void dismissProgress();

        void showProgress();
        void  showResultReservationReqStatus(ResultReservationReqStatus resultReservationReqStatus);
    }


    public abstract void getHotelReservationStatusList(String action,
                                                       String lang,
                                                       String uid,
                                                       String type,
                                                       String limit,
                                                       String offset,
                                                       String cid,
                                                       String andId);

    public abstract void getHotelReservationBundleFull(String action,
                                                       String lang,
                                                       String uid,
                                                       String bundleId,
                                                       String cid,
                                                       String andId);
    public abstract void getResultReservationReqStatus(String action,
                                                       String type,
                                                       String value,
                                                       String cid,
                                                       String androidId);
}
