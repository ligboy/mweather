package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Forecast implements Parcelable {

    @SerializedName("hourly")
    public Hourly hourly;
    @SerializedName("minutely")
    public Minutely minutely;
    @SerializedName("daily")
    public Daily daily;
    @SerializedName("primary")
    public int primary;

    public Forecast() {
    }

    protected Forecast(Parcel in) {
        hourly = in.readParcelable(Hourly.class.getClassLoader());
        minutely = in.readParcelable(Minutely.class.getClassLoader());
        daily = in.readParcelable(Daily.class.getClassLoader());
        primary = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(hourly, flags);
        dest.writeParcelable(minutely, flags);
        dest.writeParcelable(daily, flags);
        dest.writeInt(primary);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}
