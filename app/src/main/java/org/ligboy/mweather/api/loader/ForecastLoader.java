package org.ligboy.mweather.api.loader;

import android.content.Context;

import org.ligboy.mweather.core.RetrofitLoader;
import org.ligboy.mweather.model.ForecastResult;

import retrofit2.Call;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class ForecastLoader extends RetrofitLoader<ForecastResult> {

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public ForecastLoader(Context context) {
        super(context);
    }

    @Override
    protected Call<ForecastResult> newCall() {
        return null;
    }
}
