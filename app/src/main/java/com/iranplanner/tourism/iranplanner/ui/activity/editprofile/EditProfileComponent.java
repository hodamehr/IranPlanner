package com.iranplanner.tourism.iranplanner.ui.activity.editprofile;


import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {EditProfileModule.class})
public interface EditProfileComponent {
    void inject(EditProfileActivity editProfileActivity);
}



