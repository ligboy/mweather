package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class GeoentryResult extends BaseResult implements Parcelable {

    @SerializedName("placeName")
    public String placeName;

    public GeoentryResult() {
    }

    protected GeoentryResult(Parcel in) {
        super(in);
        placeName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(placeName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoentryResult> CREATOR = new Creator<GeoentryResult>() {
        @Override
        public GeoentryResult createFromParcel(Parcel in) {
            return new GeoentryResult(in);
        }

        @Override
        public GeoentryResult[] newArray(int size) {
            return new GeoentryResult[size];
        }
    };
}
