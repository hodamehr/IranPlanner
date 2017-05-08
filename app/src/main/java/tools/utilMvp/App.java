package tools.utilMvp;

import android.app.Application;

import data.component.DaggerNetComponent;
import data.component.NetComponent;
import data.module.AppModule;
import data.module.NetModule;


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
