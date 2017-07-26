package com.iranplanner.tourism.iranplanner.ui.activity.getPhoneNumber;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {SendPhoneModule.class})
public interface SendPhoneComponent {
    void inject(SendPhoneActivity sendPhoneActivity);
}



