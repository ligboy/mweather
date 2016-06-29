package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyWind implements Parcelable {
    @SerializedName("max")
    public Wind max;
    @SerializedName("avg")
    public Wind avg;
    @SerializedName("min")
    public Wind min;
    @SerializedName("date")
    public CyDate date;

    public DailyWind() {
    }

    protected DailyWind(Parcel in) {
        max = in.readParcelable(Wind.class.getClassLoader());
        avg = in.readParcelable(Wind.class.getClassLoader());
        min = in.readParcelable(Wind.class.getClassLoader());
        long time = in.readLong();
        date = time != Long.MIN_VALUE ? new CyDate(time) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyWind> CREATOR = new Creator<DailyWind>() {
        @Override
        public DailyWind createFromParcel(Parcel in) {
            return new DailyWind(in);
        }

        @Override
        public DailyWind[] newArray(int size) {
            return new DailyWind[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(max, flags);
        dest.writeParcelable(avg, flags);
        dest.writeParcelable(min, flags);
        dest.writeLong(date != null ? date.getTime() : Long.MIN_VALUE);
    }
}
