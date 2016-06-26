package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyPrecipitation implements Parcelable {
    @SerializedName("max")
    public float max;
    @SerializedName("avg")
    public float avg;
    @SerializedName("min")
    public float min;
    @SerializedName("date")
    public Date date;

    public DailyPrecipitation() {
    }

    protected DailyPrecipitation(Parcel in) {
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

    public static final Creator<DailyPrecipitation> CREATOR
            = new Creator<DailyPrecipitation>() {
        @Override
        public DailyPrecipitation createFromParcel(Parcel in) {
            return new DailyPrecipitation(in);
        }

        @Override
        public DailyPrecipitation[] newArray(int size) {
            return new DailyPrecipitation[size];
        }
    };
}
