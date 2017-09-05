package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class HotelReservationStatusListModule {
    private final HotelReservationStatusContract.View mView;

    public HotelReservationStatusListModule(HotelReservationStatusContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    HotelReservationStatusContract.View hotelReservationStatusContract() {
        return mView;
    }


}
