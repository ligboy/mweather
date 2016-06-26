package org.ligboy.mweather.model.real;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.model.real.Precipitation;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Realtime implements Parcelable {
    @SerializedName("temperature")
    public float temperature;

    @SerializedName("skycon")
    public float skyCondition;

    @SerializedName("cloudrate")
    public float cloudRate;

    @SerializedName("aqi")
    public float airQualityIndex;

    @SerializedName("humidity")
    public float humidity;

    @SerializedName("pm25")
    public float pm25;

    @SerializedName("precipitation")
    public Precipitation precipitation;

    protected Realtime(Parcel in) {
        temperature = in.readFloat();
        skyCondition = in.readFloat();
        cloudRate = in.readFloat();
        airQualityIndex = in.readFloat();
        humidity = in.readFloat();
        pm25 = in.readFloat();
        precipitation = in.readParcelable(Precipitation.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(temperature);
        dest.writeFloat(skyCondition);
        dest.writeFloat(cloudRate);
        dest.writeFloat(airQualityIndex);
        dest.writeFloat(humidity);
        dest.writeFloat(pm25);
        dest.writeParcelable(precipitation, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Realtime> CREATOR = new Creator<Realtime>() {
        @Override
        public Realtime createFromParcel(Parcel in) {
            return new Realtime(in);
        }

        @Override
        public Realtime[] newArray(int size) {
            return new Realtime[size];
        }
    };
}
