package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.LoginActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.LoginFragment;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {LoginModule1.class})
public interface LoginComponent1 {
    void inject(LoginFragment loginFragment);
    void inject(LoginActivity loginActivity);

}



