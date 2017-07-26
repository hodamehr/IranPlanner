package com.iranplanner.tourism.iranplanner.ui.activity.editprofile;


import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hoda
 */
@Module
public class EditProfileModule {
    private final EditProfileContract.View mView;


    public EditProfileModule(EditProfileContract.View mView) {
        this.mView = mView;
    }

    @CustomScope
    @Provides
    EditProfileContract.View a() {
        return mView;
    }
}
