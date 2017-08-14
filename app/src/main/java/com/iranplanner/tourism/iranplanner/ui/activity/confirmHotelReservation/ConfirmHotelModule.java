package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ConfirmHotelModule {
    private final ConfirmHotelContract.View mView;


    public ConfirmHotelModule(ConfirmHotelContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    ConfirmHotelContract.View a() {
        return mView;
    }
}
