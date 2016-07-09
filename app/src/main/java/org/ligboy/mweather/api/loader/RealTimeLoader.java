package org.ligboy.mweather.api.loader;

import android.content.Context;
import android.support.annotation.NonNull;

import org.ligboy.mweather.R;
import org.ligboy.mweather.WApplication;
import org.ligboy.mweather.api.Api;
import org.ligboy.mweather.core.RetrofitLoader;
import org.ligboy.mweather.model.RealTimeResult;

import retrofit2.Call;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class RealTimeLoader extends RetrofitLoader<RealTimeResult> {

    private String mLonlat;

    public RealTimeLoader(Context context, @NonNull String lonlat) {
        super(context);
        mLonlat = lonlat;
    }

    @Override
    protected Call<RealTimeResult> newCall() {
        final Context context = getContext();
        return Api.caiyunV2().realTime(context.getString(R.string.caiyun_token), mLonlat,
                WApplication.getPrimaryLanguage());
    }
}
