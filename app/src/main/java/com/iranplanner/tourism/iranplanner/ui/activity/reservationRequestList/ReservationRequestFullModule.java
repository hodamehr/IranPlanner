package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus.HotelReservationStatusContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ReservationRequestFullModule {
    private final ReservationRequestFullContract.View mView;

    public ReservationRequestFullModule(ReservationRequestFullContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    ReservationRequestFullContract.View hotelReservationStatusContract() {
        return mView;
    }


}
