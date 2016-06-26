package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class SunTime implements Parcelable {

    @SerializedName("time")
    public String time;

    public SunTime() {
    }

    protected SunTime(Parcel in) {
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SunTime> CREATOR = new Creator<SunTime>() {
        @Override
        public SunTime createFromParcel(Parcel in) {
            return new SunTime(in);
        }

        @Override
        public SunTime[] newArray(int size) {
            return new SunTime[size];
        }
    };
}
