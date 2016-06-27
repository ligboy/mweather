package org.ligboy.mweather.model.realtime;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class LocalPrecipitation implements Parcelable {

    @SerializedName("intensity")
    public float intensity;
    @SerializedName("datasource")
    public String datasource;

    public LocalPrecipitation() {
    }

    protected LocalPrecipitation(Parcel in) {
        intensity = in.readFloat();
        datasource = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(intensity);
        dest.writeString(datasource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LocalPrecipitation> CREATOR = new Creator<LocalPrecipitation>() {
        @Override
        public LocalPrecipitation createFromParcel(Parcel in) {
            return new LocalPrecipitation(in);
        }

        @Override
        public LocalPrecipitation[] newArray(int size) {
            return new LocalPrecipitation[size];
        }
    };
}
