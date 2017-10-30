package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class VersionRange implements Parcelable {

    public static final Parcelable.Creator<VersionRange> CREATOR = new Parcelable.Creator<VersionRange>() {
        @Override
        public VersionRange createFromParcel(Parcel source) {
            return new VersionRange(source);
        }

        @Override
        public VersionRange[] newArray(int size) {
            return new VersionRange[size];
        }
    };

    @SerializedName("low")
    private String low;
    @SerializedName("high")
    private String high;

    public VersionRange() {
    }

    private VersionRange(Parcel in) {
        this.low = in.readString();
        this.high = in.readString();
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.low);
        dest.writeString(this.high);
    }

}