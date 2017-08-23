package com.iranplanner.tourism.iranplanner.ui.fragment.home;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore.AttractionListMoreContract;
import com.iranplanner.tourism.iranplanner.ui.activity.attractionDetails.AttractionDetailContract;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationContract;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListContract;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class HomeModule {
    private final HomeContract.View mView;
    private final ReservationContract.View mViewReservation;
    private final AttractionListMoreContract.View mViewAttractionListMoreContract;
    private final ReservationHotelListContract.View mViewReservationHotelListContract;


    public HomeModule(HomeContract.View mView,ReservationContract.View mViewReservation,AttractionListMoreContract.View mViewAttractionListMoreContract,ReservationHotelListContract.View mViewReservationHotelListContract) {
        this.mView = mView;
        this.mViewReservation=mViewReservation;
        this.mViewAttractionListMoreContract=mViewAttractionListMoreContract;
        this.mViewReservationHotelListContract=mViewReservationHotelListContract;
    }

    @CustomScope
    @Provides
    HomeContract.View a() {
        return mView;
    }
    @CustomScope
    @Provides
    ReservationContract.View sa() {
        return mViewReservation;
    }
    @CustomScope
    @Provides
    AttractionListMoreContract.View attractionV() {
        return mViewAttractionListMoreContract;
    }
    @CustomScope
    @Provides
    ReservationHotelListContract.View reservationV() {
        return mViewReservationHotelListContract;
    }
}
