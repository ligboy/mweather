package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyTemperature implements Parcelable {
    @SerializedName("max")
    public float max;
    @SerializedName("avg")
    public float avg;
    @SerializedName("min")
    public float min;
    @SerializedName("date")
    public Date date;

    public DailyTemperature() {
    }

    protected DailyTemperature(Parcel in) {
        max = in.readFloat();
        avg = in.readFloat();
        min = in.readFloat();
        long time = in.readLong();
        date = time != Long.MIN_VALUE ? new Date(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(max);
        dest.writeFloat(avg);
        dest.writeFloat(min);
        dest.writeLong(date != null ? date.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyTemperature> CREATOR
            = new Creator<DailyTemperature>() {
        @Override
        public DailyTemperature createFromParcel(Parcel in) {
            return new DailyTemperature(in);
        }

        @Override
        public DailyTemperature[] newArray(int size) {
            return new DailyTemperature[size];
        }
    };
}
