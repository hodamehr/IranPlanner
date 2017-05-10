package com.iranplanner.tourism.iranplanner.mainscreen;




import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.attraction.AttractionScreenModule;
import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.fragment.MainSearchFragment;

import dagger.Component;
import data.component.NetComponent;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {MainScreenModule.class,AttractionScreenModule.class})
public interface MainScreenComponent {
    void inject(mm activity);
    void injectionMainSearchFragment(MainSearchFragment fragment);
    void injectItineraryListFragment(ItineraryListFragment fragment);
    void inject(MoreItemItineraryActivity MoreItemItineraryActivity);
}



