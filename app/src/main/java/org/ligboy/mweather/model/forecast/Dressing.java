package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Dressing implements Parcelable {

    @SerializedName("index")
    public String index;
    @SerializedName("desc")
    public String desc;
    @SerializedName("datetime")
    public Date datetime;

    public Dressing() {
    }

    protected Dressing(Parcel in) {
        index = in.readString();
        desc = in.readString();
        long date = in.readLong();
        datetime = date != Long.MIN_VALUE ? new Date(date) : null;
    }

    public static final Creator<Dressing> CREATOR = new Creator<Dressing>() {
        @Override
        public Dressing createFromParcel(Parcel in) {
            return new Dressing(in);
        }

        @Override
        public Dressing[] newArray(int size) {
            return new Dressing[size];
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
