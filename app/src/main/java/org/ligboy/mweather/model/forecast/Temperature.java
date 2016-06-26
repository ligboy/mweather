package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Temperature implements Parcelable {

    @SerializedName("value")
    public float value;

    @SerializedName("datetime")
    public Date datetime;

    public Temperature() {
    }

    protected Temperature(Parcel in) {
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

    public static final Parcelable.Creator<Temperature> CREATOR
            = new Parcelable.Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };
}
