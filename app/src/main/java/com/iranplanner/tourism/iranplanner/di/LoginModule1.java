package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class LoginModule1 {
    private final LoginContract.View mView;


    public LoginModule1(LoginContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    LoginContract.View a() {
        return mView;
    }
}
