package com.iranplanner.tourism.iranplanner.ui.activity.showRoom;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ConfirmHotelContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ShowRoomModule {
    private final ShowRoomContract.View mView;


    public ShowRoomModule(ShowRoomContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    ShowRoomContract.View a() {
        return mView;
    }
}
