package com.iranplanner.tourism.iranplanner.ui.activity.getToken;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {GetTokenModule.class})
public interface GetTokenComponent {
    void inject(GetTokenActivity getTokenActivity);
}



