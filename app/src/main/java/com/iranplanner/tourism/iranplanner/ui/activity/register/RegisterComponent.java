package com.iranplanner.tourism.iranplanner.ui.activity.register;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.fragment.SignupFragment;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {RegisterModule.class})
public interface RegisterComponent {
    void inject(RegisterActivity activity);
    void injectFragment(SignupFragment fragment);
}



