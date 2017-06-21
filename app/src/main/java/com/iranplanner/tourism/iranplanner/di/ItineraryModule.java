package com.iranplanner.tourism.iranplanner.di;


import dagger.Module;
import dagger.Provides;

import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.ItineraryContract;

/**
 * Created by Hoda
 */
@Module
public class ItineraryModule {
    private final ItineraryContract.View mView;


    public ItineraryModule(ItineraryContract.View mView) {
        this.mView = mView;
    }
@CustomScope
    @Provides
    ItineraryContract.View a() {
        return mView;
    }
}
