package org.ligboy.mweather.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import org.ligboy.library.stetho.StethoOkHttp3Interceptor;
import org.ligboy.mweather.BuildConfig;
import org.ligboy.mweather.R;
import org.ligboy.mweather.api.deserializer.MyTypeAdapterFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public final class Api {

    private static final long CACHE_MAX_SIZE = 64 * 1024 * 1024;

    private static CaiyunV1Api CAIYUN_V_1;
    private static CaiyunV2Api CAIYUN_V_2;
    private static CaiyunCdnApi CAIYUN_CDN;

    private Api() {
        throw new IllegalAccessError("");
    }

    public static void setup(@NonNull Context context) {

        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(new HttpLoggingInterceptor(new Logger() {
                @Override
                public void log(String message) {
                    Timber.d(message);
                }
            }).setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE));
            okHttpBuilder.addNetworkInterceptor(new StethoOkHttp3Interceptor());
        }
        //Cache
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null || !cacheDir.exists() || !cacheDir.canWrite()) {
            cacheDir = context.getCacheDir();
        }
        okHttpBuilder.cache(new Cache(cacheDir, CACHE_MAX_SIZE));
        OkHttpClient okHttpClient = okHttpBuilder.build();
        //Gson
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(MyTypeAdapterFactory.INSTANCE);

        final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        Retrofit retrofitV1 = retrofitBuilder
                .baseUrl(context.getString(R.string.caiyun_base))
                .build();
        CAIYUN_V_1 = retrofitV1.create(CaiyunV1Api.class);

        Retrofit retrofitV2 = retrofitBuilder
                .baseUrl(context.getString(R.string.caiyun_api_base))
                .build();
        CAIYUN_V_2 = retrofitV2.create(CaiyunV2Api.class);

        Retrofit retrofitCdn = retrofitBuilder
                .baseUrl(context.getString(R.string.caiyun_cdn_base))
                .build();
        CAIYUN_CDN = retrofitCdn.create(CaiyunCdnApi.class);
    }

    public static CaiyunV1Api caiyunV1() {
        return CAIYUN_V_1;
    }

    public static CaiyunV2Api caiyunV2() {
        return CAIYUN_V_2;
    }

    public static CaiyunCdnApi caiyunCdn() {
        return CAIYUN_CDN;
    }
}
