package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.SplashActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
    void inject(SplashActivity splashActivity);
}



