package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reservationHotelList.ReservationHotelListModule;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {HotelReservationStatusListModule.class})
public interface HotelReservationStatusListComponent {
    void injectHotelReservationStatusActivity(HotelReservationStatusActivity hotelReservationStatusActivity);
}



