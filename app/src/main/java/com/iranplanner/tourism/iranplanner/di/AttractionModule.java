package com.iranplanner.tourism.iranplanner.di;


import dagger.Module;
import dagger.Provides;

import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionContract;

/**
 * Created by Hoda
 */
@Module
public class AttractionModule   {
    private final AttractionContract.View mView;


    public AttractionModule(AttractionContract.View mView) {
        this.mView = mView;
    }
@CustomScope
    @Provides
    AttractionContract.View a() {
        return mView;
    }
}
