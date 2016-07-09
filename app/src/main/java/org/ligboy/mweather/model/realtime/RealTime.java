package org.ligboy.mweather.model.realtime;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class RealTime implements Parcelable {
    @SerializedName("temperature")
    public int temperature;

    @SerializedName("skycon")
    public String skyCondition;

    @SerializedName("cloudrate")
    public float cloudRate;

    @SerializedName("aqi")
    public int airQualityIndex;

    @SerializedName("humidity")
    public float humidity;

    @SerializedName("pm25")
    public int pm25;

    @SerializedName("precipitation")
    public Precipitation precipitation;

    @SerializedName("wind")
    public Wind wind;

    protected RealTime(Parcel in) {
        temperature = in.readInt();
        skyCondition = in.readString();
        cloudRate = in.readFloat();
        airQualityIndex = in.readInt();
        humidity = in.readFloat();
        pm25 = in.readInt();
        precipitation = in.readParcelable(Precipitation.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(temperature);
        dest.writeString(skyCondition);
        dest.writeFloat(cloudRate);
        dest.writeInt(airQualityIndex);
        dest.writeFloat(humidity);
        dest.writeInt(pm25);
        dest.writeParcelable(precipitation, flags);
        dest.writeParcelable(wind, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RealTime> CREATOR = new Creator<RealTime>() {
        @Override
        public RealTime createFromParcel(Parcel in) {
            return new RealTime(in);
        }

        @Override
        public RealTime[] newArray(int size) {
            return new RealTime[size];
        }
    };
}
