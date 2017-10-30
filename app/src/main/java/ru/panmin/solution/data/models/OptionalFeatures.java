package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OptionalFeatures implements Parcelable {

    public static final Parcelable.Creator<OptionalFeatures> CREATOR = new Parcelable.Creator<OptionalFeatures>() {
        @Override
        public OptionalFeatures createFromParcel(Parcel source) {
            return new OptionalFeatures(source);
        }

        @Override
        public OptionalFeatures[] newArray(int size) {
            return new OptionalFeatures[size];
        }
    };

    @SerializedName("optionalFeature")
    private List<OptionalFeature> optionalFeature = new ArrayList<>();

    public OptionalFeatures() {
    }

    private OptionalFeatures(Parcel in) {
        this.optionalFeature = in.createTypedArrayList(OptionalFeature.CREATOR);
    }

    public List<OptionalFeature> getOptionalFeature() {
        return optionalFeature;
    }

    public void setOptionalFeature(List<OptionalFeature> optionalFeature) {
        this.optionalFeature = optionalFeature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.optionalFeature);
    }

}