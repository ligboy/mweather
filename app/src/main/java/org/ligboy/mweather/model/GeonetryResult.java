package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class GeonetryResult extends BaseResult implements Parcelable {

    @SerializedName("placeName")
    public String placeName;

    public GeonetryResult() {
    }

    protected GeonetryResult(Parcel in) {
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

    public static final Creator<GeonetryResult> CREATOR = new Creator<GeonetryResult>() {
        @Override
        public GeonetryResult createFromParcel(Parcel in) {
            return new GeonetryResult(in);
        }

        @Override
        public GeonetryResult[] newArray(int size) {
            return new GeonetryResult[size];
        }
    };
}
