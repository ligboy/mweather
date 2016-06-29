package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyHumidity implements Parcelable {
    @SerializedName("max")
    public float max;
    @SerializedName("avg")
    public float avg;
    @SerializedName("min")
    public float min;
    @SerializedName("date")
    public CyDate date;

    public DailyHumidity() {
    }

    protected DailyHumidity(Parcel in) {
        max = in.readFloat();
        avg = in.readFloat();
        min = in.readFloat();
        long time = in.readLong();
        date = time != Long.MIN_VALUE ? new CyDate(time) : null;
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

    public static final Creator<DailyHumidity> CREATOR
            = new Creator<DailyHumidity>() {
        @Override
        public DailyHumidity createFromParcel(Parcel in) {
            return new DailyHumidity(in);
        }

        @Override
        public DailyHumidity[] newArray(int size) {
            return new DailyHumidity[size];
        }
    };
}
