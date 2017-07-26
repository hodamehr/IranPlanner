package com.iranplanner.tourism.iranplanner.ui.activity.getToken;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

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
