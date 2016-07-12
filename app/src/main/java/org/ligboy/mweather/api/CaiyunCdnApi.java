package org.ligboy.mweather.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public interface CaiyunCdnApi {

    @GET("/etc/android_config.json")
    Call<Map<String, String>> getConfig();
}
