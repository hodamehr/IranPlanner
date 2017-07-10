package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionDetailContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.HomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class HomeModule {
    private final HomeContract.View mView;


    public HomeModule(HomeContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    HomeContract.View a() {
        return mView;
    }
}
