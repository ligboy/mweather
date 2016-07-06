package org.ligboy.mweather;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.optimizely.Optimizely;

import io.fabric.sdk.android.Fabric;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class WApplication extends Application {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Optimizely.startOptimizelyWithAPIToken(getString(R.string.com_optimizely_api_key), this);
    }
}
