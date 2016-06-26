package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Humidity implements Parcelable {

    @SerializedName("value")
    public float value;

    @SerializedName("datetime")
    public Date datetime;

    public Humidity() {
    }

    protected Humidity(Parcel in) {
        value = in.readFloat();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new Date(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(value);
        dest.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Humidity> CREATOR
            = new Parcelable.Creator<Humidity>() {
        @Override
        public Humidity createFromParcel(Parcel in) {
            return new Humidity(in);
        }

        @Override
        public Humidity[] newArray(int size) {
            return new Humidity[size];
        }
    };
}
