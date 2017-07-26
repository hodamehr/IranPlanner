package com.iranplanner.tourism.iranplanner.ui.fragment.home;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.SplashActivity;

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



