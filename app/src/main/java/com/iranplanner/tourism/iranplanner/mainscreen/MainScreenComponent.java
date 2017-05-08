package com.iranplanner.tourism.iranplanner.mainscreen;




import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.fragment.MainSearchFragment;

import dagger.Component;
import data.component.NetComponent;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(mm activity);
    void injectionMainSearchFragment(MainSearchFragment fragment);
    void injectItineraryListFragment(ItineraryListFragment fragment);
}

