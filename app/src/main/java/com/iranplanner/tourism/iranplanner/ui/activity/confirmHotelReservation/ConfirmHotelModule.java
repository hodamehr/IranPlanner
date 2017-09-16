package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.SettingContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ConfirmHotelModule {
    private final ConfirmHotelContract.View mView;
    private final SettingContract.View contractView;


    public ConfirmHotelModule(ConfirmHotelContract.View mView, SettingContract.View contractView) {
        this.mView = mView;
        this.contractView = contractView;
    }

    @CustomScope
    @Provides
    ConfirmHotelContract.View a() {
        return mView;
    }

    @CustomScope
    @Provides
    SettingContract.View showSetting() {
        return contractView;
    }
}
