package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.AttractionModule;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.ReservationListActivity;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {ReservationModule.class})
public interface ReservationComponent {
    void inject(ReservationListActivity activity);
}



