package org.ligboy.mweather.model.real;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Precipitation implements Parcelable {
    @SerializedName("nearest")
    public NearestPrecipitation nearest;

    @SerializedName("local")
    public LocalPrecipitation local;

    public Precipitation() {
    }

    protected Precipitation(Parcel in) {
        local = in.readParcelable(LocalPrecipitation.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(local, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Precipitation> CREATOR = new Creator<Precipitation>() {
        @Override
        public Precipitation createFromParcel(Parcel in) {
            return new Precipitation(in);
        }

        @Override
        public Precipitation[] newArray(int size) {
            return new Precipitation[size];
        }
    };
}
