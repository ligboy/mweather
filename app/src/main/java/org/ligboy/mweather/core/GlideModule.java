package org.ligboy.mweather.core;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;

import org.ligboy.library.stetho.BuildConfig;
import org.ligboy.library.stetho.StethoOkHttp3Interceptor;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class GlideModule implements com.bumptech.glide.module.GlideModule {

    private static final OkHttpClient HTTP_CLIENT;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoOkHttp3Interceptor());
        }
        HTTP_CLIENT = builder.build();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /* no-op */
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(HTTP_CLIENT));
    }
}
