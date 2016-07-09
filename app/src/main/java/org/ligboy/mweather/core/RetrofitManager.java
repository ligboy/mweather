package org.ligboy.mweather.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import com.google.gson.GsonBuilder;

import org.ligboy.library.stetho.StethoOkHttp3Interceptor;
import org.ligboy.mweather.BuildConfig;
import org.ligboy.mweather.api.deserializer.MyTypeAdapterFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

/**
 * Retrofit Manager
 *
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class RetrofitManager {

    private static final long CACHE_MAX_SIZE = 64 * 1024 * 1024;

    /**
     * Default OkHttpClient Builder
     */
    @SuppressWarnings("ConstantConditions")
    public final OkHttpClient.Builder HTTP_CLIENT_BUILDER;

    private final Retrofit.Builder RETROFIT_BUILDER;

    {
        HTTP_CLIENT_BUILDER = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HTTP_CLIENT_BUILDER.addInterceptor(new HttpLoggingInterceptor(new Logger() {
                @Override
                public void log(String message) {
                    Timber.d(message);
                }
            }).setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE));
            HTTP_CLIENT_BUILDER.addNetworkInterceptor(new StethoOkHttp3Interceptor());
        }

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(MyTypeAdapterFactory.INSTANCE);
        RETROFIT_BUILDER = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    /**
     * The Cache Pool of Retrofit Services
     */
    private final LruCache<Class, Object> mCachedServices = new LruCache<>(3);

    private Retrofit mRetrofit;

    public RetrofitManager(Context context, @NonNull String baseUrl) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null || !cacheDir.exists() || !cacheDir.canWrite()) {
            cacheDir = context.getCacheDir();
        }
        HTTP_CLIENT_BUILDER.cache(new Cache(cacheDir, CACHE_MAX_SIZE));
        mRetrofit = RETROFIT_BUILDER.client(HTTP_CLIENT_BUILDER.build()).baseUrl(baseUrl).build();
    }

    public RetrofitManager(Context context) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null || !cacheDir.exists() || !cacheDir.canWrite()) {
            cacheDir = context.getCacheDir();
        }
        HTTP_CLIENT_BUILDER.cache(new Cache(cacheDir, CACHE_MAX_SIZE));
        mRetrofit = RETROFIT_BUILDER.client(HTTP_CLIENT_BUILDER.build()).build();
    }

    private void init() {

    }
    /**
     * set Retrofit's BaseUrl
     * <p/>Create a new Retrofit instance.
     *
     * @param baseUrl baseUrl
     */
    public void setBaseUrl(String baseUrl) {
        mRetrofit = RETROFIT_BUILDER.baseUrl(baseUrl).build();
        mCachedServices.evictAll();
    }

    /**
     * set Retrofit's BaseUrl
     * <p/>Create a new Retrofit instance.
     *
     * @param baseUrl baseUrl
     */
    public void setBaseUrl(HttpUrl baseUrl) {
        mRetrofit = RETROFIT_BUILDER.baseUrl(baseUrl).build();
        mCachedServices.evictAll();
    }

    /**
     * Retrieve current Retrofit instance
     *
     * @return Retrofit
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * set OkHttpClient Retrofit used.
     * <p/>Create a new Retrofit instance.
     *
     * @param httpClient OkHttpClient
     */
    public void setHttpClient(OkHttpClient httpClient) {
        if (httpClient == null) {
            throw new NullPointerException("client == null");
        }
        mRetrofit = RETROFIT_BUILDER.client(httpClient).build();
        mCachedServices.evictAll();
    }

    /**
     * Create Retrofit Service
     *
     * @param service The Class of Service
     * @param <T>     Type of Service
     * @return the instance of Service
     */
    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {
        T serviceInstance = (T) mCachedServices.get(service);
        if (serviceInstance == null) {
            if (mRetrofit == null) {
                mRetrofit = RETROFIT_BUILDER.build();
            }
            serviceInstance = mRetrofit.create(service);
            mCachedServices.put(service, serviceInstance);
        }
        return serviceInstance;
    }

}
