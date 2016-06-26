package org.ligboy.mweather.model.real;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class NearestPrecipitation implements Parcelable {

    @SerializedName("intensity")
    public float intensity;

    @SerializedName("distance")
    public float distance;

    public NearestPrecipitation() {
    }

    protected NearestPrecipitation(Parcel in) {
        intensity = in.readFloat();
        distance = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(intensity);
        dest.writeFloat(distance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NearestPrecipitation> CREATOR = new Creator<NearestPrecipitation>() {
        @Override
        public NearestPrecipitation createFromParcel(Parcel in) {
            return new NearestPrecipitation(in);
        }

        @Override
        public NearestPrecipitation[] newArray(int size) {
            return new NearestPrecipitation[size];
        }
    };
}
