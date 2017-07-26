package com.iranplanner.tourism.iranplanner.ui.fragment.myaccount;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.fragment.myaccount.SettingContract;

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
