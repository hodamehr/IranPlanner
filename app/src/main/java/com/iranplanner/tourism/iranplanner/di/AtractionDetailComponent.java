package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.attractionDetailActivity;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {AttractionDetailModule.class})
public interface AtractionDetailComponent {
    void inject(attractionDetailActivity activity);

}



