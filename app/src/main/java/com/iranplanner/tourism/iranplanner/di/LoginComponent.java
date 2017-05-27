package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.SignupActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.LoginFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.SignupFragment;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginFragment loginFragment);
}



