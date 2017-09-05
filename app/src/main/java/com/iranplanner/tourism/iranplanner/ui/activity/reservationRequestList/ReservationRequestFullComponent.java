package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus.HotelReservationStatusActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus.HotelReservationStatusListModule;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {ReservationRequestFullModule.class})
public interface ReservationRequestFullComponent {
    void injectReservationRequestActivity(ReservationRequestActivity reservationRequestActivity);
}



