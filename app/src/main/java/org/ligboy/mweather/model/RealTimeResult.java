package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.model.realtime.RealTime;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class RealTimeResult extends BaseResult implements Parcelable {

    @SerializedName("tzshift")
    public int tzShift;
    @SerializedName("unit")
    public String unit;
    @SerializedName("result")
    public RealTime result;

    public RealTimeResult() {
    }

    protected RealTimeResult(Parcel in) {
        super(in);
        tzShift = in.readInt();
        unit = in.readString();
        result = in.readParcelable(RealTime.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(tzShift);
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
