package com.iranplanner.tourism.iranplanner.ui.activity.moreItemItinerary;


import dagger.Module;
import dagger.Provides;

import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

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
