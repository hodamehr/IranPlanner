package com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList;


import entity.ResultLodgingHotel;

/**
 * Created by Hoda
 */
public abstract class ReservationHotelListContract {
    public interface View {
        void showHotelReserveList(ResultLodgingHotel resultLodgingHotel);

        void showError(String message);

        void showComplete();


    }

    public abstract void getHotelReserve(String action,
                                         String idHotel,
                                         String limit,
                                         String offset,
                                         String cid,
                                         String andID);

}
