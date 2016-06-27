package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.model.realtime.RealTime;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class RealTimeResult extends BaseResult implements Parcelable {

    @SerializedName("lang")
    public String language;
    @SerializedName("server_time")
    public Date serverTime;
    @SerializedName("tzshift")
    public int tzShift;
    @SerializedName("location")
    public double[] location;
    @SerializedName("unit")
    public String unit;
    @SerializedName("result")
    public RealTime result;

    public RealTimeResult() {
    }

    protected RealTimeResult(Parcel in) {
        super(in);
        language = in.readString();
        long time = in.readLong();
        serverTime = time != Long.MIN_VALUE ? new Date(time) : null;
        tzShift = in.readInt();
        location = in.createDoubleArray();
        unit = in.readString();
        result = in.readParcelable(RealTime.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(language);
        dest.writeLong(serverTime != null ? serverTime.getTime() : Long.MIN_VALUE);
        dest.writeInt(tzShift);
        dest.writeDoubleArray(location);
        dest.writeString(unit);
        dest.writeParcelable(result, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RealTimeResult> CREATOR = new Creator<RealTimeResult>() {
        @Override
        public RealTimeResult createFromParcel(Parcel in) {
            return new RealTimeResult(in);
        }

        @Override
        public RealTimeResult[] newArray(int size) {
            return new RealTimeResult[size];
        }
    };
}
