package com.iranplanner.tourism.iranplanner.di.model;

import android.app.Application;


import com.iranplanner.tourism.iranplanner.di.data.component.DaggerNetComponent;
import com.iranplanner.tourism.iranplanner.di.data.component.NetComponent;
import com.iranplanner.tourism.iranplanner.di.data.module.AppModule;
import com.iranplanner.tourism.iranplanner.di.data.module.NetModule;


/**
 * Created by Hoda
 */
public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://api.parsdid.com/iranplanner/app/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
