package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Pm25 implements Parcelable {

    @SerializedName("value")
    public int value;

    @SerializedName("datetime")
    public Date datetime;

    public Pm25() {
    }

    protected Pm25(Parcel in) {
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

    public static final Parcelable.Creator<Pm25> CREATOR
            = new Parcelable.Creator<Pm25>() {
        @Override
        public Pm25 createFromParcel(Parcel in) {
            return new Pm25(in);
        }

        @Override
        public Pm25[] newArray(int size) {
            return new Pm25[size];
        }
    };
}
