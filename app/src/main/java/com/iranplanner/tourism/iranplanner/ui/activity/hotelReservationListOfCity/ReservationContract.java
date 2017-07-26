package com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResultLodgingList;


/**
 * Created by Hoda
 */
public abstract class ReservationContract extends Presenter<ReservationContract.View> {
    public interface View {
        void showLodgingList(ResultLodgingList resultLodgingList);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }


    public abstract void getLodgingList(String action,
                                        String city,
                                        String cid,
                                        String andId);


}
