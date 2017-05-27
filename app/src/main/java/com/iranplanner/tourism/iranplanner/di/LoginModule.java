package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.RegisterContract;

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
