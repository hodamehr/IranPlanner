package com.iranplanner.tourism.iranplanner.ui.activity.login;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class LoginModule {
    private final LoginContract.View mView;


    public LoginModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    LoginContract.View a() {
        return mView;
    }
}
