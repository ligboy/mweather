package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class SkyCondition implements Parcelable {

    @SerializedName("value")
    public String value;

    @SerializedName("datetime")
    public Date datetime;

    public SkyCondition() {
    }

    protected SkyCondition(Parcel in) {
        value = in.readString();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new Date(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SkyCondition> CREATOR
            = new Creator<SkyCondition>() {
        @Override
        public SkyCondition createFromParcel(Parcel in) {
            return new SkyCondition(in);
        }

        @Override
        public SkyCondition[] newArray(int size) {
            return new SkyCondition[size];
        }
    };
}
