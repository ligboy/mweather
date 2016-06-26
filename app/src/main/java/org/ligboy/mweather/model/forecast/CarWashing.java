package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class CarWashing implements Parcelable {

    @SerializedName("index")
    public String index;
    @SerializedName("desc")
    public String desc;
    @SerializedName("datetime")
    public Date datetime;

    public CarWashing() {
    }

    protected CarWashing(Parcel in) {
        index = in.readString();
        desc = in.readString();
        long date = in.readLong();
        datetime = date != Long.MIN_VALUE ? new Date(date) : null;
    }

    public static final Creator<CarWashing> CREATOR = new Creator<CarWashing>() {
        @Override
        public CarWashing createFromParcel(Parcel in) {
            return new CarWashing(in);
        }

        @Override
        public CarWashing[] newArray(int size) {
            return new CarWashing[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(index);
        parcel.writeString(desc);
        parcel.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }
}
