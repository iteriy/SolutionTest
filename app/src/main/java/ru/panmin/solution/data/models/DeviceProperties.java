package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DeviceProperties implements Parcelable {

    public static final Parcelable.Creator<DeviceProperties> CREATOR = new Parcelable.Creator<DeviceProperties>() {
        @Override
        public DeviceProperties createFromParcel(Parcel source) {
            return new DeviceProperties(source);
        }

        @Override
        public DeviceProperties[] newArray(int size) {
            return new DeviceProperties[size];
        }
    };

    @SerializedName("followOnAdRefreshInterval")
    private int followOnAdRefreshInterval;
    @SerializedName("ooyala")
    private Ooyala ooyala;
    @SerializedName("videoAdUniqueInterval")
    private int videoAdUniqueInterval;
    @SerializedName("videoAdStartInterval")
    private int videoAdStartInterval;
    @SerializedName("optionalFeatures")
    private OptionalFeatures optionalFeatures;
    @SerializedName("adRefreshInterval")
    private int adRefreshInterval;
    @SerializedName("videoAdRefreshInterval")
    private int videoAdRefreshInterval;

    public DeviceProperties() {
    }

    private DeviceProperties(Parcel in) {
        this.followOnAdRefreshInterval = in.readInt();
        this.ooyala = in.readParcelable(Ooyala.class.getClassLoader());
        this.videoAdUniqueInterval = in.readInt();
        this.videoAdStartInterval = in.readInt();
        this.optionalFeatures = in.readParcelable(OptionalFeatures.class.getClassLoader());
        this.adRefreshInterval = in.readInt();
        this.videoAdRefreshInterval = in.readInt();
    }

    public int getFollowOnAdRefreshInterval() {
        return followOnAdRefreshInterval;
    }

    public void setFollowOnAdRefreshInterval(int followOnAdRefreshInterval) {
        this.followOnAdRefreshInterval = followOnAdRefreshInterval;
    }

    public Ooyala getOoyala() {
        return ooyala;
    }

    public void setOoyala(Ooyala ooyala) {
        this.ooyala = ooyala;
    }

    public int getVideoAdUniqueInterval() {
        return videoAdUniqueInterval;
    }

    public void setVideoAdUniqueInterval(int videoAdUniqueInterval) {
        this.videoAdUniqueInterval = videoAdUniqueInterval;
    }

    public int getVideoAdStartInterval() {
        return videoAdStartInterval;
    }

    public void setVideoAdStartInterval(int videoAdStartInterval) {
        this.videoAdStartInterval = videoAdStartInterval;
    }

    public OptionalFeatures getOptionalFeatures() {
        return optionalFeatures;
    }

    public void setOptionalFeatures(OptionalFeatures optionalFeatures) {
        this.optionalFeatures = optionalFeatures;
    }

    public int getAdRefreshInterval() {
        return adRefreshInterval;
    }

    public void setAdRefreshInterval(int adRefreshInterval) {
        this.adRefreshInterval = adRefreshInterval;
    }

    public int getVideoAdRefreshInterval() {
        return videoAdRefreshInterval;
    }

    public void setVideoAdRefreshInterval(int videoAdRefreshInterval) {
        this.videoAdRefreshInterval = videoAdRefreshInterval;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.followOnAdRefreshInterval);
        dest.writeParcelable(this.ooyala, flags);
        dest.writeInt(this.videoAdUniqueInterval);
        dest.writeInt(this.videoAdStartInterval);
        dest.writeParcelable(this.optionalFeatures, flags);
        dest.writeInt(this.adRefreshInterval);
        dest.writeInt(this.videoAdRefreshInterval);
    }

}