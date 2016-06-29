package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class Pm25Result implements Parcelable {

    @SerializedName("status")
    public String status;
    @SerializedName("stid")
    public String stid;
    @SerializedName("pm_25")
    public int pm25;
    @SerializedName("source")
    public String source;
    @SerializedName("time")
    public long time;
    @SerializedName("aqi")
    public int api;
    @SerializedName("desc")
    public String desc;

    public Pm25Result() {
    }

    protected Pm25Result(Parcel in) {
        status = in.readString();
        stid = in.readString();
        pm25 = in.readInt();
        source = in.readString();
        time = in.readLong();
        api = in.readInt();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(stid);
        dest.writeInt(pm25);
        dest.writeString(source);
        dest.writeLong(time);
        dest.writeInt(api);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pm25Result> CREATOR = new Creator<Pm25Result>() {
        @Override
        public Pm25Result createFromParcel(Parcel in) {
            return new Pm25Result(in);
        }

        @Override
        public Pm25Result[] newArray(int size) {
            return new Pm25Result[size];
        }
    };
}
