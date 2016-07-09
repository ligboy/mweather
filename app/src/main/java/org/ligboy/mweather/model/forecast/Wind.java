package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * WindLevel
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Wind implements Parcelable {
    @SerializedName("direction")
    public float direction;
    @SerializedName("speed")
    public float speed;
    @SerializedName("datetime")
    public CyDate datetime;


    public Wind() {
    }

    public Wind(float direction, float speed) {
        this.direction = direction;
        this.speed = speed;
    }

    protected Wind(Parcel in) {
        direction = in.readFloat();
        speed = in.readFloat();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new CyDate(time) : null;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(direction);
        parcel.writeFloat(speed);
        parcel.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }
}
