package com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ReservationModule {
    private final ReservationContract.View mView;


    public ReservationModule(ReservationContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    ReservationContract.View a() {
        return mView;
    }
}
