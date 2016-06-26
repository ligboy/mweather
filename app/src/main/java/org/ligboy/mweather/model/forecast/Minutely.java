package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Minutely implements Parcelable {

    @SerializedName("description")
    public String description;

    @SerializedName("probability")
    public float[] probability;

    @SerializedName("datasource")
    public String datasource;

    @SerializedName("precipitation")
    public float[] precipitation;

    @SerializedName("precipitation_2h")
    public float[] precipitation2h;

    public Minutely() {
    }

    protected Minutely(Parcel in) {
        description = in.readString();
        probability = in.createFloatArray();
        datasource = in.readString();
        precipitation = in.createFloatArray();
        precipitation2h = in.createFloatArray();
    }

    public static final Creator<Minutely> CREATOR = new Creator<Minutely>() {
        @Override
        public Minutely createFromParcel(Parcel in) {
            return new Minutely(in);
        }

        @Override
        public Minutely[] newArray(int size) {
            return new Minutely[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeFloatArray(probability);
        parcel.writeString(datasource);
        parcel.writeFloatArray(precipitation);
        parcel.writeFloatArray(precipitation2h);
    }
}
