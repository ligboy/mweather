package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyAqi implements Parcelable {
    @SerializedName("max")
    public int max;
    @SerializedName("avg")
    public float avg;
    @SerializedName("min")
    public int min;
    @SerializedName("date")
    public CyDate date;

    public DailyAqi() {
    }

    protected DailyAqi(Parcel in) {
        max = in.readInt();
        avg = in.readFloat();
        min = in.readInt();
        long time = in.readLong();
        date = time != Long.MIN_VALUE ? new CyDate(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(max);
        dest.writeFloat(avg);
        dest.writeInt(min);
        dest.writeLong(date != null ? date.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyAqi> CREATOR
            = new Creator<DailyAqi>() {
        @Override
        public DailyAqi createFromParcel(Parcel in) {
            return new DailyAqi(in);
        }

        @Override
        public DailyAqi[] newArray(int size) {
            return new DailyAqi[size];
        }
    };
}
