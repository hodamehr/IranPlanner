package com.iranplanner.tourism.iranplanner.di;






import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.ui.activity.MoreItemItineraryActivity;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {ItineraryModule.class})
public interface ItineraryComponent {
    void inject(MoreItemItineraryActivity activity);

}



