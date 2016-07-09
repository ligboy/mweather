package org.ligboy.mweather.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import org.ligboy.mweather.R;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public enum  Weather {
    CLEAR_DAY(R.string.weather_condition_clear_day, R.drawable.weather_clear),
    CLEAR_NIGHT(R.string.weather_condition_clear_night, R.drawable.weather_clear_night),
    PARTLY_CLOUDY_DAY(R.string.weather_condition_partly_cloudy_day, R.drawable.weather_few_clouds),
    PARTLY_CLOUDY_NIGHT(R.string.weather_condition_partly_cloudy_night,
            R.drawable.weather_few_clouds_night),
    CLOUDY(R.string.weather_condition_cloudy, R.drawable.weather_clouds),
    RAIN(R.string.weather_condition_rain, R.drawable.weather_rain_night),
    SLEET(R.string.weather_condition_sleet, R.drawable.weather_snow_rain),
    SNOW(R.string.weather_condition_snow, R.drawable.weather_snow),
    WIND(R.string.weather_condition_windy, R.drawable.weather_wind),
    FOG(R.string.weather_condition_fog, R.drawable.weather_fog),
    HAZE(R.string.weather_condition_haze, R.drawable.weather_haze),
    UNKNOWN(R.string.weather_condition_unknown, R.drawable.weather_none_available);

    private int mStringRes;
    private int mIconRes;

    Weather(@StringRes int stringRes, @DrawableRes int iconRes) {
        mStringRes = stringRes;
        mIconRes = iconRes;
    }

    @StringRes
    public int getNameRes() {
        return mStringRes;
    }

    @DrawableRes
    public int getIconRes() {
        return mIconRes;
    }

    public static Weather from(String skyCondition) {
        try {
            return Weather.valueOf(Weather.class, skyCondition);
        } catch (Exception ignored) {

        }
        return Weather.UNKNOWN;
    }
}
