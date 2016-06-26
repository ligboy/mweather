package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Aqi implements Parcelable {

    @SerializedName("value")
    public int value;

    @SerializedName("datetime")
    public Date datetime;

    public Aqi() {
    }

    protected Aqi(Parcel in) {
        value = in.readInt();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new Date(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
        dest.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Aqi> CREATOR
            = new Parcelable.Creator<Aqi>() {
        @Override
        public Aqi createFromParcel(Parcel in) {
            return new Aqi(in);
        }

        @Override
        public Aqi[] newArray(int size) {
            return new Aqi[size];
        }
    };
}
