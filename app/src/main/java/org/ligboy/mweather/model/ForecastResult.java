package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.model.forecast.Forecast;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class ForecastResult extends BaseResult implements Parcelable {
    @SerializedName("lang")
    public String language;
    @SerializedName("server_time")
    public Date serverTime;
    @SerializedName("api_status")
    public String apiStatus;
    @SerializedName("tzshift")
    public int tzShift;
    @SerializedName("version")
    public String version;
    @SerializedName("location")
    public double[] location;
    @SerializedName("unit")
    public String unit;
    @SerializedName("result")
    public Forecast result;

    protected ForecastResult(Parcel in) {
        super(in);
        language = in.readString();
        apiStatus = in.readString();
        tzShift = in.readInt();
        version = in.readString();
        location = in.createDoubleArray();
        unit = in.readString();
        result = in.readParcelable(Forecast.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(language);
        dest.writeString(apiStatus);
        dest.writeInt(tzShift);
        dest.writeString(version);
        dest.writeDoubleArray(location);
        dest.writeString(unit);
        dest.writeParcelable(result, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ForecastResult> CREATOR = new Creator<ForecastResult>() {
        @Override
        public ForecastResult createFromParcel(Parcel in) {
            return new ForecastResult(in);
        }

        @Override
        public ForecastResult[] newArray(int size) {
            return new ForecastResult[size];
        }
    };
}
