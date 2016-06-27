package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class RadarImageResult implements Parcelable {

    @SerializedName("status")
    public String status;

    @SerializedName("radar_img")
    public RadarImage[] radarImages;

    protected RadarImageResult(Parcel in) {
        status = in.readString();
        radarImages = in.createTypedArray(RadarImage.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedArray(radarImages, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RadarImageResult> CREATOR = new Creator<RadarImageResult>() {
        @Override
        public RadarImageResult createFromParcel(Parcel in) {
            return new RadarImageResult(in);
        }

        @Override
        public RadarImageResult[] newArray(int size) {
            return new RadarImageResult[size];
        }
    };
}
