package com.iranplanner.tourism.iranplanner.ui.activity.showRoom;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ActivityHotelReservationConfirm;
import com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation.ConfirmHotelModule;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {ShowRoomModule.class})
public interface ShowRoomComponent {
    void injectShowRoom(ShowRoomActivity showRoomActivity);

}



