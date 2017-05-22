package com.iranplanner.tourism.iranplanner.di;


import com.iranplanner.tourism.iranplanner.activity.CommentListActivity;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.model.CustomScope;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.fragment.ItineraryListFragment;
import com.iranplanner.tourism.iranplanner.ui.fragment.MainSearchFragment;

import dagger.Component;

/**
 * Created by Hoda on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = {CommentModule.class})
public interface CommentComponent {
    void injectComment(CommentListActivity commentListActivity);
}



