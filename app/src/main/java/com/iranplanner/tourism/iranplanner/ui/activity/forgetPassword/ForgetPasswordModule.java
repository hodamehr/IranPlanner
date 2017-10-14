package com.iranplanner.tourism.iranplanner.ui.activity.forgetPassword;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class ForgetPasswordModule {
    private final ForgetPasswordContract.View mView;


    public ForgetPasswordModule(ForgetPasswordContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    ForgetPasswordContract.View a() {
        return mView;
    }
}
