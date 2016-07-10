package org.ligboy.mweather;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.optimizely.Optimizely;

import org.ligboy.library.stetho.StethoTree;
import org.ligboy.library.stetho.StethoUtil;
import org.ligboy.mweather.api.Api;
import org.ligboy.mweather.util.AccountUtil;
import org.ligboy.mweather.util.ConfigurationUtil;
import org.ligboy.mweather.util.LocaleUtil;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class WApplication extends Application {

    private static Locale[] sLocales;

    private static String sPrimaryLanguage;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new StethoTree());
            Timber.plant(new Timber.DebugTree());
            StethoUtil.initialize(this);
        }
        //About Fabric.
        Fabric.with(this, new Crashlytics());
        Optimizely.startOptimizelyWithAPIToken(getString(R.string.com_optimizely_api_key), this);

        //Initialize the singleton of Api.
        Api.setup(this);
        //Initialize the info of locale.
        sLocales = ConfigurationUtil.getLocales(getResources().getConfiguration());
        sPrimaryLanguage = LocaleUtil.getLanguage(sLocales[0]);
        //Create the sync account.
        AccountUtil.createSyncAccount(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        sLocales = ConfigurationUtil.getLocales(newConfig);
        sPrimaryLanguage = LocaleUtil.getLanguage(sLocales[0]);
    }

    public static Locale[] getLocale() {
        return sLocales;
    }

    public static String getPrimaryLanguage() {
        return sPrimaryLanguage;
    }
}
