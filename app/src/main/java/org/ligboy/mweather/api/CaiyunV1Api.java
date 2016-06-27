package org.ligboy.mweather.api;

import org.ligboy.mweather.model.Pm25Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <p>http://caiyunapp.com/</p>
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public interface CaiyunV1Api {

    @GET("/fcgi-bin/v1/pm25.py")
    public Call<Pm25Result> getPm25(@Query("lonlat") String lonlat);

    @GET("/fcgi-bin/v1/api.py?format=json&product=minutes_prec")
    public Call<Pm25Result> getRealTimeWithRadar(@Query("token") String token,
                                           @Query("lonlat") String lonlat);

    @GET("/fcgi-bin/v1/img.py")
    public Call<Pm25Result> getRadarImages(@Query("token") String token);
}
