package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class BaseResult implements Parcelable {

    @SerializedName("status")
    public String status;
    @SerializedName("lang")
    public String language;
    @SerializedName("server_time")
    public long serverTime;
    @SerializedName("location")
    public double[] location;

    public BaseResult() {
    }

    protected BaseResult(Parcel in) {
        status = in.readString();
        language = in.readString();
        serverTime = in.readLong();
        location = in.createDoubleArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(language);
        dest.writeLong(serverTime);
        dest.writeDoubleArray(location);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseResult> CREATOR = new Creator<BaseResult>() {
        @Override
        public BaseResult createFromParcel(Parcel in) {
            return new BaseResult(in);
        }

        @Override
        public BaseResult[] newArray(int size) {
            return new BaseResult[size];
        }
    };
}
