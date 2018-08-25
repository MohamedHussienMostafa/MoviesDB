package com.comelite.moviesdb.core;

import android.app.Application;

import com.comelite.moviesdb.R;
import com.comelite.moviesdb.core.constants.APIsConstants;
import com.comelite.moviesdb.core.di.components.DaggerNetComponent;
import com.comelite.moviesdb.core.di.components.NetComponent;
import com.comelite.moviesdb.core.di.modules.AppModule;
import com.comelite.moviesdb.core.di.modules.NetModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MoviesDBApplication extends Application {

    private static MoviesDBApplication instance;
    private NetComponent mNetComponent;


    public static MoviesDBApplication get() {
        if (instance == null)
            // This should never happen, but just in case
            throw new IllegalStateException("Application instance has not been initialized!");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(APIsConstants.BASE_URL))
                .build();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Dubai-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
