package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Astro implements Parcelable {
    @SerializedName("date")
    public Date date;
    @SerializedName("sunset")
    public SunTime sunset;
    @SerializedName("sunrise")
    public SunTime sunrise;

    public Astro() {
    }

    protected Astro(Parcel in) {
        long date = in.readLong();
        this.date = date != Long.MIN_VALUE ? new Date(date) : null;
        sunset = in.readParcelable(SunTime.class.getClassLoader());
        sunrise = in.readParcelable(SunTime.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date != null ? date.getTime() : Long.MIN_VALUE);
        dest.writeParcelable(sunset, flags);
        dest.writeParcelable(sunrise, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Astro> CREATOR = new Creator<Astro>() {
        @Override
        public Astro createFromParcel(Parcel in) {
            return new Astro(in);
        }

        @Override
        public Astro[] newArray(int size) {
            return new Astro[size];
        }
    };
}
