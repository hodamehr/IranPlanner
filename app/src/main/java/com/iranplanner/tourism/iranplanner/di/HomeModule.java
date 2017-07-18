package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionDetailContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.HomeContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.ReservationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class HomeModule {
    private final HomeContract.View mView;
    private final ReservationContract.View mViewReservation;


    public HomeModule(HomeContract.View mView,ReservationContract.View mViewReservation) {
        this.mView = mView;
        this.mViewReservation=mViewReservation;
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
}
