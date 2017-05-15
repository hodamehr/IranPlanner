package com.iranplanner.tourism.iranplanner.d;


import com.iranplanner.tourism.iranplanner.mainscreen.MainSearchScreenContract;

import dagger.Module;
import dagger.Provides;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda
 */
@Module
public class AttractionModule   {
    private final AttractionContract.View mView;


    public AttractionModule(AttractionContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    AttractionContract.View a() {
        return mView;
    }
}
