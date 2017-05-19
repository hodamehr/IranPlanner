package com.iranplanner.tourism.iranplanner.di;






import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {AttractionModule.class})
public interface AtractionComponent {
    void inject(MoreItemItineraryActivity activity);

}



