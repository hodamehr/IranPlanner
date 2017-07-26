package com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch;




import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.fragment.itineraryList.ItineraryListFragment;


import dagger.Component;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;

import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {MainSearchnModule.class})
public interface MainScreenComponent {
    void injectStandardActivity(StandardActivity standardActivity);
    void injectionMainSearchFragment(MainSearchFragment fragment);
    void injectItineraryListFragment(ItineraryListFragment fragment);
}



