package com.iranplanner.tourism.iranplanner.mainscreen;


import android.view.View;

import dagger.Module;
import dagger.Provides;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda
 */
@Module
public class MainScreenModule {
    private final MainSearchScreenContract.View mView;


    public MainScreenModule(MainSearchScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainSearchScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
