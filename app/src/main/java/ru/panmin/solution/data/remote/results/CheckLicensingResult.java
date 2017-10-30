package ru.panmin.solution.data.remote.results;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CheckLicensingResult implements Parcelable {

    public static final Parcelable.Creator<CheckLicensingResult> CREATOR = new Parcelable.Creator<CheckLicensingResult>() {
        @Override
        public CheckLicensingResult createFromParcel(Parcel source) {
            return new CheckLicensingResult(source);
        }

        @Override
        public CheckLicensingResult[] newArray(int size) {
            return new CheckLicensingResult[size];
        }
    };

    @SerializedName("isAllowed")
    private boolean allowed;

    public CheckLicensingResult() {
    }

    private CheckLicensingResult(Parcel in) {
        this.allowed = in.readByte() != 0;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.allowed ? (byte) 1 : (byte) 0);
    }

}