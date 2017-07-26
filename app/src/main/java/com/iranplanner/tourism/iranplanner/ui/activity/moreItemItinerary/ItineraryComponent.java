package com.iranplanner.tourism.iranplanner.ui.activity.moreItemItinerary;






import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {ItineraryModule.class})
public interface ItineraryComponent {
    void inject(MoreItemItineraryActivity activity);

}



