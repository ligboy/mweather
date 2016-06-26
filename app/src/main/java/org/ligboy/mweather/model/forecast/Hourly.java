package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Hourly implements Parcelable {

    @SerializedName("description")
    public String description;

    @SerializedName("skycon")
    public SkyCondition[] skyConditions;

    @SerializedName("cloudrate")
    public CloudRate[] cloudRates;

    @SerializedName("aqi")
    public Aqi[] airQualityIndexes;

    @SerializedName("humidity")
    public Humidity[] humiditis;

    @SerializedName("pm25")
    public Pm25[] pm25s;

    @SerializedName("precipitation")
    public Precipitation[] precipitations;

    @SerializedName("wind")
    public Wind[] winds;

    @SerializedName("temperature")
    public Temperature[] temperatures;

    public Hourly() {
    }

    protected Hourly(Parcel in) {
        description = in.readString();
        skyConditions = in.createTypedArray(SkyCondition.CREATOR);
        cloudRates = in.createTypedArray(CloudRate.CREATOR);
        airQualityIndexes = in.createTypedArray(Aqi.CREATOR);
        humiditis = in.createTypedArray(Humidity.CREATOR);
        pm25s = in.createTypedArray(Pm25.CREATOR);
        precipitations = in.createTypedArray(Precipitation.CREATOR);
        winds = in.createTypedArray(Wind.CREATOR);
        temperatures = in.createTypedArray(Temperature.CREATOR);
    }

    public static final Creator<Hourly> CREATOR = new Creator<Hourly>() {
        @Override
        public Hourly createFromParcel(Parcel in) {
            return new Hourly(in);
        }

        @Override
        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeTypedArray(skyConditions, i);
        parcel.writeTypedArray(cloudRates, i);
        parcel.writeTypedArray(airQualityIndexes, i);
        parcel.writeTypedArray(humiditis, i);
        parcel.writeTypedArray(pm25s, i);
        parcel.writeTypedArray(precipitations, i);
        parcel.writeTypedArray(winds, i);
        parcel.writeTypedArray(temperatures, i);
    }
}
