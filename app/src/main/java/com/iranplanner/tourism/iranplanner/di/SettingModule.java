package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.SettingContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class SettingModule {
    private final SettingContract.View mView;


    public SettingModule(SettingContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    SettingContract.View a() {
        return mView;
    }
}
