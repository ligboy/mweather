package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class CloudRate implements Parcelable {

    @SerializedName("value")
    public float value;

    @SerializedName("datetime")
    public CyDate datetime;

    public CloudRate() {
    }

    protected CloudRate(Parcel in) {
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

    public static final Parcelable.Creator<CloudRate> CREATOR
            = new Parcelable.Creator<CloudRate>() {
        @Override
        public CloudRate createFromParcel(Parcel in) {
            return new CloudRate(in);
        }

        @Override
        public CloudRate[] newArray(int size) {
            return new CloudRate[size];
        }
    };
}
