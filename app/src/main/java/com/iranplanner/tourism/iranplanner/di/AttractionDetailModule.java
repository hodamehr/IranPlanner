package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class AttractionDetailModule {
    private final AttractionDetailContract.View mView;


    public AttractionDetailModule(AttractionDetailContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    AttractionDetailContract.View a() {
        return mView;
    }
}
