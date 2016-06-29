package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyPm25 implements Parcelable {
    @SerializedName("max")
    public float max;
    @SerializedName("avg")
    public float avg;
    @SerializedName("min")
    public float min;
    @SerializedName("date")
    public CyDate date;

    public DailyPm25() {
    }

    protected DailyPm25(Parcel in) {
        max = in.readFloat();
        avg = in.readFloat();
        min = in.readFloat();
        long time = in.readLong();
        date = time != Long.MIN_VALUE ? new CyDate(time) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyPm25> CREATOR = new Creator<DailyPm25>() {
        @Override
        public DailyPm25 createFromParcel(Parcel in) {
            return new DailyPm25(in);
        }

        @Override
        public DailyPm25[] newArray(int size) {
            return new DailyPm25[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(max);
        dest.writeFloat(avg);
        dest.writeFloat(min);
        dest.writeLong(date != null ? date.getTime() : Long.MIN_VALUE);
    }
}
