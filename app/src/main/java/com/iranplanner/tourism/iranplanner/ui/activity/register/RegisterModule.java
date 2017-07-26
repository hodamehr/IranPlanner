package com.iranplanner.tourism.iranplanner.ui.activity.register;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.register.RegisterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class RegisterModule {
    private final RegisterContract.View mView;


    public RegisterModule(RegisterContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    RegisterContract.View a() {
        return mView;
    }
}
