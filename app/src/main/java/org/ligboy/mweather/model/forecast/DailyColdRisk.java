package org.ligboy.mweather.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ligboy.mweather.api.util.CyDate;

/**
 * @author Ligboy Liu ligboy@gmail.com
 */
public class DailyColdRisk implements Parcelable {

    @SerializedName("index")
    public int index;
    @SerializedName("desc")
    public String desc;
    @SerializedName("datetime")
    public CyDate datetime;

    public DailyColdRisk() {
    }

    protected DailyColdRisk(Parcel in) {
        index = in.readInt();
        desc = in.readString();
        long time = in.readLong();
        datetime = time != Long.MIN_VALUE ? new CyDate(time) : null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(desc);
        dest.writeLong(datetime != null ? datetime.getTime() : Long.MIN_VALUE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DailyColdRisk> CREATOR
            = new Creator<DailyColdRisk>() {
        @Override
        public DailyColdRisk createFromParcel(Parcel in) {
            return new DailyColdRisk(in);
        }

        @Override
        public DailyColdRisk[] newArray(int size) {
            return new DailyColdRisk[size];
        }
    };
}
