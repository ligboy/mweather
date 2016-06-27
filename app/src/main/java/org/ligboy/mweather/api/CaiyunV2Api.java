package org.ligboy.mweather.api;


import android.support.annotation.Nullable;

import org.ligboy.mweather.model.ForecastResult;
import org.ligboy.mweather.model.GeoentryResult;
import org.ligboy.mweather.model.RealTimeResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <p>http://api.caiyunapp.com</p>
 * @author Ligboy Liu ligboy@gmail.com
 */
public interface CaiyunV2Api {

    @GET("/v2/{token}/{lonlat}/forecast")
    Call<ForecastResult> forecast(@Path("token") String token, @Path("lonlat") String lonlat,
                                  @Nullable @Query("lang") String language);

    @GET("/v2/{token}/{lonlat}/realtime")
    Call<RealTimeResult> realtime(@Path("token") String token, @Path("lonlat") String lonlat,
                                  @Nullable @Query("lang") String language);

    @GET("/v2/geoentry/{lang}/{lonlat}")
    Call<GeoentryResult> pm25(@Path("lonlat") String lonlat, @Path("lang") String language);


}
