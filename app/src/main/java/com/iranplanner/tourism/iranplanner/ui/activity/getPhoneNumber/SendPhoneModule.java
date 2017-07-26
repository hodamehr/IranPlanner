package com.iranplanner.tourism.iranplanner.ui.activity.getPhoneNumber;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class SendPhoneModule {
    private final SendPhoneContract.View mView;


    public SendPhoneModule(SendPhoneContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    SendPhoneContract.View a() {
        return mView;
    }
}
