package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Ultraviolet implements Parcelable {

    @SerializedName("index")
    public String index;
    @SerializedName("desc")
    public String desc;
    @SerializedName("datetime")
    public Date datetime;

    public Ultraviolet() {
    }

    protected Ultraviolet(Parcel in) {
        index = in.readString();
        desc = in.readString();
        long date = in.readLong();
        datetime = date != Long.MIN_VALUE ? new Date(date) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(index);
        dest.writeString(desc);
        dest.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ultraviolet> CREATOR = new Creator<Ultraviolet>() {
        @Override
        public Ultraviolet createFromParcel(Parcel in) {
            return new Ultraviolet(in);
        }

        @Override
        public Ultraviolet[] newArray(int size) {
            return new Ultraviolet[size];
        }
    };
}
