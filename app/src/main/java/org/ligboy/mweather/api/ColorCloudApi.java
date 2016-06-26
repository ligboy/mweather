package org.ligboy.mweather.api;


import org.ligboy.mweather.model.ForecastResult;
import org.ligboy.mweather.model.RealtimeResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public interface ColorCloudApi {

    @GET("http://api.caiyunapp.com/v2/{token}/{lonlat}/forecast")
    Call<ForecastResult> forecast(@Path("token") String token, @Query("lang") String language,
                                  @Path("lonlat") String lonlat);

    @GET("http://api.caiyunapp.com/v2/{token}/{lonlat}/realtime")
    Call<RealtimeResult> realtime(@Path("token") String token, @Query("lang") String language,
                                  @Path("lonlat") String lonlat);


}
