package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OptionalFeature implements Parcelable {

    public static final Parcelable.Creator<OptionalFeature> CREATOR = new Parcelable.Creator<OptionalFeature>() {
        @Override
        public OptionalFeature createFromParcel(Parcel source) {
            return new OptionalFeature(source);
        }

        @Override
        public OptionalFeature[] newArray(int size) {
            return new OptionalFeature[size];
        }
    };

    @SerializedName("feature")
    private String feature;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("platformVersionRange")
    private VersionRange platformVersionRange;
    @SerializedName("productVersionRange")
    private VersionRange productVersionRange;

    public OptionalFeature() {
    }

    private OptionalFeature(Parcel in) {
        this.feature = in.readString();
        this.enabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.platformVersionRange = in.readParcelable(VersionRange.class.getClassLoader());
        this.productVersionRange = in.readParcelable(VersionRange.class.getClassLoader());
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public VersionRange getPlatformVersionRange() {
        return platformVersionRange;
    }

    public void setPlatformVersionRange(VersionRange platformVersionRange) {
        this.platformVersionRange = platformVersionRange;
    }

    public VersionRange getProductVersionRange() {
        return productVersionRange;
    }

    public void setProductVersionRange(VersionRange productVersionRange) {
        this.productVersionRange = productVersionRange;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feature);
        dest.writeValue(this.enabled);
        dest.writeParcelable(this.platformVersionRange, flags);
        dest.writeParcelable(this.productVersionRange, flags);
    }

}