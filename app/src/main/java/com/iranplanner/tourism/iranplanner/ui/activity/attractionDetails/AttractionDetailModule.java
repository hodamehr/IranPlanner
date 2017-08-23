package com.iranplanner.tourism.iranplanner.ui.activity.attractionDetails;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore.AttractionListMoreContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class AttractionDetailModule {
    private final AttractionDetailContract.View mView;
    private final AttractionListMoreContract.View mViewAttractionListMoreContract;


    public AttractionDetailModule(AttractionDetailContract.View mView,AttractionListMoreContract.View  mViewAttractionListMoreContract) {
        this.mView = mView;
        this.mViewAttractionListMoreContract=mViewAttractionListMoreContract;
    }

    @CustomScope
    @Provides
    AttractionDetailContract.View a() {
        return mView;
    }
    @CustomScope
    @Provides
    AttractionListMoreContract.View attractionV() {
        return mViewAttractionListMoreContract;
    }
}
