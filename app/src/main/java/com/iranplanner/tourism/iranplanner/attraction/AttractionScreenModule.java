package com.iranplanner.tourism.iranplanner.attraction;




import android.view.View;

//import com.iranplanner.tourism.iranplanner.mainscreen.MainSearchScreenContract;

import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class AttractionScreenModule {
    private final AttractionScreenContract.View mView;


    public AttractionScreenModule(AttractionScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    AttractionScreenContract.View providesMoreItemItineraryActivityScreenContractView() {
        return mView;
    }


}
