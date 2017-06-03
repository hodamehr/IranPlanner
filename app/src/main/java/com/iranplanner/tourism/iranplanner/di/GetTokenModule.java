package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.GetTokenContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.SendPhoneContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class GetTokenModule {
    private final GetTokenContract.View mView;


    public GetTokenModule(GetTokenContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    GetTokenContract.View a() {
        return mView;
    }
}
