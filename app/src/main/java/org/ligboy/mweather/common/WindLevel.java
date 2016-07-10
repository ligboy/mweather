package org.ligboy.mweather.common;

import android.support.annotation.StringRes;

import org.ligboy.mweather.R;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public enum WindLevel {
    CALM(R.string.now_wind_level_calm),
    LIGHT_AIR(R.string.now_wind_level_light_air),
    LIGHT(R.string.now_wind_level_light),
    GENTLE(R.string.now_wind_level_gentle),
    MODERATE(R.string.now_wind_level_moderate),
    FRESH(R.string.now_wind_level_fresh),
    STRONG(R.string.now_wind_level_strong),
    NEAR_GALE(R.string.now_wind_level_near_gale),
    GALE(R.string.now_wind_level_gale),
    STRONG_GALE(R.string.now_wind_level_strong_gale),
    STORM(R.string.now_wind_level_storm),
    VIOLENT_STORM(R.string.now_wind_level_violent_storm),
    HURRICANE(R.string.now_wind_level_hurricane);

    private int mStringRes;

    WindLevel(@StringRes int stringRes) {
        mStringRes = stringRes;
    }

    @StringRes
    public int getNameRes() {
        return mStringRes;
    }

    public static WindLevel cnValueOf(float windSpeed) {
        if (windSpeed < 1) {
            return CALM;
        } else if (windSpeed < 6) {
            return LIGHT_AIR;
        } else if (windSpeed < 12) {
            return LIGHT;
        } else if (windSpeed < 20) {
            return GENTLE;
        } else if (windSpeed < 29) {
            return MODERATE;
        } else if (windSpeed < 39) {
            return FRESH;
        } else if (windSpeed < 50) {
            return STRONG;
        } else if (windSpeed < 62) {
            return NEAR_GALE;
        } else if (windSpeed < 75) {
            return GALE;
        } else if (windSpeed < 89) {
            return STRONG_GALE;
        } else if (windSpeed < 103) {
            return STORM;
        } else if (windSpeed < 117) {
            return VIOLENT_STORM;
        } else {
            return HURRICANE;
        }
    }

    public static WindLevel beaufortValueOf(float windSpeed) {
        if (windSpeed < 2) {
            return CALM;
        } else if (windSpeed < 7) {
            return LIGHT_AIR;
        } else if (windSpeed < 13) {
            return LIGHT;
        } else if (windSpeed < 20) {
            return GENTLE;
        } else if (windSpeed < 31) {
            return MODERATE;
        } else if (windSpeed < 41) {
            return FRESH;
        } else if (windSpeed < 52) {
            return STRONG;
        } else if (windSpeed < 63) {
            return NEAR_GALE;
        } else if (windSpeed < 76) {
            return GALE;
        } else if (windSpeed < 88) {
            return STRONG_GALE;
        } else if (windSpeed < 104) {
            return STORM;
        } else if (windSpeed < 118) {
            return VIOLENT_STORM;
        } else {
            return HURRICANE;
        }
    }
}
