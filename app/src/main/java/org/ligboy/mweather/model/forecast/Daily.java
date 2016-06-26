package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Daily implements Parcelable {

    @SerializedName("coldRisk")
    public DailyColdRisk[] coldRisks;
    @SerializedName("skycon")
    public SkyCondition[] skyConditions;
    @SerializedName("cloudrate")
    public DailyCloudRate[] clondRates;
    @SerializedName("aqi")
    public DailyAqi[] aqis;
    @SerializedName("humidity")
    public DailyHumidity[] humidities;
    @SerializedName("pm25")
    public DailyPm25[] pm25s;
    @SerializedName("wind")
    public DailyWind[] winds;
    @SerializedName("temperature")
    public DailyTemperature[] temperatures;
    @SerializedName("astro")
    public Astro[] astros;
    @SerializedName("ultraviolet")
    public Ultraviolet[] ultraviolets;
    @SerializedName("dressing")
    public Dressing[] dressings;
    @SerializedName("carWashing")
    public CarWashing[] carWashings;
    @SerializedName("precipitation")
    public DailyPrecipitation[] precipitations;

    public Daily() {
    }

    protected Daily(Parcel in) {
        coldRisks = in.createTypedArray(DailyColdRisk.CREATOR);
        skyConditions = in.createTypedArray(SkyCondition.CREATOR);
        clondRates = in.createTypedArray(DailyCloudRate.CREATOR);
        aqis = in.createTypedArray(DailyAqi.CREATOR);
        humidities = in.createTypedArray(DailyHumidity.CREATOR);
        pm25s = in.createTypedArray(DailyPm25.CREATOR);
        winds = in.createTypedArray(DailyWind.CREATOR);
        temperatures = in.createTypedArray(DailyTemperature.CREATOR);
        astros = in.createTypedArray(Astro.CREATOR);
        ultraviolets = in.createTypedArray(Ultraviolet.CREATOR);
        dressings = in.createTypedArray(Dressing.CREATOR);
        carWashings = in.createTypedArray(CarWashing.CREATOR);
        precipitations = in.createTypedArray(DailyPrecipitation.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(coldRisks, flags);
        dest.writeTypedArray(skyConditions, flags);
        dest.writeTypedArray(clondRates, flags);
        dest.writeTypedArray(aqis, flags);
        dest.writeTypedArray(humidities, flags);
        dest.writeTypedArray(pm25s, flags);
        dest.writeTypedArray(winds, flags);
        dest.writeTypedArray(temperatures, flags);
        dest.writeTypedArray(astros, flags);
        dest.writeTypedArray(ultraviolets, flags);
        dest.writeTypedArray(dressings, flags);
        dest.writeTypedArray(carWashings, flags);
        dest.writeTypedArray(precipitations, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };
}
