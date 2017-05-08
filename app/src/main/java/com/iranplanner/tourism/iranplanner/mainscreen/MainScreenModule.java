package com.iranplanner.tourism.iranplanner.mainscreen;




import dagger.Module;
import dagger.Provides;
import tools.utilMvp.CustomScope;

/**
 * Created by Hoda
 */
@Module
public class MainScreenModule {
    private final MainScreenContract.View mView;


    public MainScreenModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
