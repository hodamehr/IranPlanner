package com.iranplanner.tourism.iranplanner.d;




import com.iranplanner.tourism.iranplanner.activity.MoreItemItineraryActivity;
import com.iranplanner.tourism.iranplanner.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.fragment.MainSearchFragment;
import com.iranplanner.tourism.iranplanner.mainscreen.MainScreenModule;
import com.iranplanner.tourism.iranplanner.mainscreen.mm;

import dagger.Component;
import data.component.NetComponent;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {AttractionModule.class})
public interface AtractionComponent {
    void inject(MoreItemItineraryActivity activity);

}



