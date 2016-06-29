package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Precipitation implements Parcelable {

    @SerializedName("value")
    public float value;

    @SerializedName("datetime")
    public CyDate datetime;

    public Precipitation() {
    }

    protected Precipitation(Parcel in) {
        value = in.readFloat();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new CyDate(time) : null;
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

    public static final Parcelable.Creator<Precipitation> CREATOR
            = new Parcelable.Creator<Precipitation>() {
        @Override
        public Precipitation createFromParcel(Parcel in) {
            return new Precipitation(in);
        }

        @Override
        public Precipitation[] newArray(int size) {
            return new Precipitation[size];
        }
    };
}
