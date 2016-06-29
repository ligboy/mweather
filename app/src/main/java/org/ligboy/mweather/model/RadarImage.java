package org.ligboy.mweather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class RadarImage implements Parcelable {

    public String image;

    public long datetime;

    public double[] lonlats;

    public RadarImage() {
    }

    protected RadarImage(Parcel in) {
        image = in.readString();
        datetime = in.readLong();
        lonlats = in.createDoubleArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeLong(datetime);
        dest.writeDoubleArray(lonlats);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RadarImage> CREATOR = new Creator<RadarImage>() {
        @Override
        public RadarImage createFromParcel(Parcel in) {
            return new RadarImage(in);
        }

        @Override
        public RadarImage[] newArray(int size) {
            return new RadarImage[size];
        }
    };
}
