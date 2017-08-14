package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.RequestLodgingReservationMain;
import entity.ResultLodgingReservation;


/**
 * Created by Hoda
 */
public abstract class ConfirmHotelContract extends Presenter<ConfirmHotelContract.View> {
    public interface View {
        void showHotelReservationResult(RequestLodgingReservationMain loginResult);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }


    public abstract void sendRequestReservation(ResultLodgingReservation request, String cid,
                                                String androidId);

}
