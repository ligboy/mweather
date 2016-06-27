package org.ligboy.mweather.model.realtime;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Wind
 * @author Ligboy Liu ligboy@gmail.com
 */
public class Wind implements Parcelable {
    @SerializedName("direction")
    public float direction;
    @SerializedName("speed")
    public float speed;


    public Wind() {
    }

    public Wind(float direction, float speed) {
        this.direction = direction;
        this.speed = speed;
    }

    protected Wind(Parcel in) {
        direction = in.readFloat();
        speed = in.readFloat();
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
    }
}
