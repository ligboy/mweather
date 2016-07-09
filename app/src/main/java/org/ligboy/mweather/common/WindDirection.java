package org.ligboy.mweather.common;

import android.support.annotation.StringRes;

import org.ligboy.mweather.R;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public enum WindDirection {
    North(R.string.now_wind_direction_north), NorthEast(R.string.now_wind_direction_north_east),
    East(R.string.now_wind_direction_east), SouthEast(R.string.now_wind_direction_south_east),
    South(R.string.now_wind_direction_south), SouthWest(R.string.now_wind_direction_south_west),
    West(R.string.now_wind_direction_west), NorthWest(R.string.now_wind_direction_north_west);

    private int mStringRes;

    WindDirection(@StringRes int stringRes) {
        mStringRes = stringRes;
    }

    @StringRes
    public int getNameRes() {
        return mStringRes;
    }

    public static WindDirection valueOf(float direction) {
        if (direction < 0) {
            if (direction < -360) {
                direction %= 360;
            }
            direction += 360;
        } else  if (direction > 360) {
            direction %= 360;
        }
        if (direction >= 337.5F && direction < 22.5F) {
            return North;
        } else if (direction >= 22.5F && direction < 67.5F) {
            return NorthEast;
        } else if (direction >= 67.5F && direction < 112.5F) {
            return East;
        } else if (direction >= 112.5F && direction < 157.5F) {
            return SouthEast;
        } else if (direction >= 157.5F && direction < 202.5F) {
            return South;
        } else if (direction >= 202.5F && direction < 247.5F) {
            return SouthWest;
        } else if (direction >= 247.5F && direction < 292.5F) {
            return West;
        } else {
            return NorthWest;
        }
    }
}
