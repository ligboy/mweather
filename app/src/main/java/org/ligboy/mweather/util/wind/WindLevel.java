package org.ligboy.mweather.util.wind;

import android.support.annotation.IntDef;
import android.support.annotation.StringRes;

import org.ligboy.mweather.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public final class WindLevel {
    public static final int CALM = 0;
    public static final int LIGHT_AIR = 1;
    public static final int LIGHT = 2;
    public static final int GENTLE = 3;
    public static final int MODERATE = 4;
    public static final int FRESH = 5;
    public static final int STRONG = 6;
    public static final int NEAR_GALE = 7;
    public static final int GALE = 8;
    public static final int STRONG_GALE = 9;
    public static final int STORM = 10;
    public static final int VIOLENT_STORM = 11;
    public static final int HURRICANE = 12;

    @IntDef({CALM, LIGHT_AIR, LIGHT, GENTLE, MODERATE, FRESH, STRONG, NEAR_GALE,
            GALE, STRONG_GALE, STORM, VIOLENT_STORM, HURRICANE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WindLevels {}

    @WindLevel.WindLevels
    public static int cn(float windSpeed) {
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

    @WindLevel.WindLevels
    public static int beaufort(float windSpeed) {
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

    @StringRes
    public static int name(@WindLevels int level) {
        switch (level) {
            case CALM:
                return R.string.now_wind_level_calm;
            case LIGHT_AIR:
                return R.string.now_wind_level_light_air;
            case LIGHT:
                return R.string.now_wind_level_light;
            case GENTLE:
                return R.string.now_wind_level_gentle;
            case MODERATE:
                return R.string.now_wind_level_moderate;
            case FRESH:
                return R.string.now_wind_level_fresh;
            case STRONG:
                return R.string.now_wind_level_strong;
            case NEAR_GALE:
                return R.string.now_wind_level_near_gale;
            case GALE:
                return R.string.now_wind_level_gale;
            case STRONG_GALE:
                return R.string.now_wind_level_strong_gale;
            case STORM:
                return R.string.now_wind_level_storm;
            case VIOLENT_STORM:
                return R.string.now_wind_level_violent_storm;
            case HURRICANE:
            default:
                return R.string.now_wind_level_hurricane;
        }
    }
}
