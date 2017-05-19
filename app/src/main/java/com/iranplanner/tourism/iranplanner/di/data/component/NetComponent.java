package com.iranplanner.tourism.iranplanner.di.data.component;




import javax.inject.Singleton;

import dagger.Component;
import com.iranplanner.tourism.iranplanner.di.data.module.AppModule;
import com.iranplanner.tourism.iranplanner.di.data.module.NetModule;
import retrofit2.Retrofit;

/**
 *
 * Created by Hoda
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}
