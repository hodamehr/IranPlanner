package com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ReservationHotelListModule {
    private final ReservationHotelListContract.View mView;
    private final ReservationContract.View mViewReservation;

    public ReservationHotelListModule(ReservationHotelListContract.View mView,ReservationContract.View mViewReservation) {
        this.mView = mView;
        this.mViewReservation=mViewReservation;

    }

    @CustomScope
    @Provides
    ReservationHotelListContract.View providesMainScreenContractView() {
        return mView;
    }
    @CustomScope
    @Provides
    ReservationContract.View sa() {
        return mViewReservation;
    }

}
