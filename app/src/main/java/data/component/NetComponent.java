package data.component;




import javax.inject.Singleton;

import dagger.Component;
import data.module.AppModule;
import data.module.NetModule;
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
