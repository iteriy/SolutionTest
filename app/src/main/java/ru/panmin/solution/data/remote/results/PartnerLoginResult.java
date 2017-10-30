package ru.panmin.solution.data.remote.results;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import ru.panmin.solution.data.models.DeviceProperties;

public class PartnerLoginResult implements Parcelable {

    public static final Parcelable.Creator<PartnerLoginResult> CREATOR = new Parcelable.Creator<PartnerLoginResult>() {
        @Override
        public PartnerLoginResult createFromParcel(Parcel source) {
            return new PartnerLoginResult(source);
        }

        @Override
        public PartnerLoginResult[] newArray(int size) {
            return new PartnerLoginResult[size];
        }
    };

    @SerializedName("stationSkipLimit")
    private int stationSkipLimit;
    @SerializedName("partnerId")
    private String partnerId;
    @SerializedName("partnerAuthToken")
    private String partnerAuthToken;
    @SerializedName("syncTime")
    private String syncTime;
    @SerializedName("deviceProperties")
    private DeviceProperties deviceProperties;
    @SerializedName("stationSkipUnit")
    private String stationSkipUnit;

    public PartnerLoginResult() {
    }

    private PartnerLoginResult(Parcel in) {
        this.stationSkipLimit = in.readInt();
        this.partnerId = in.readString();
        this.partnerAuthToken = in.readString();
        this.syncTime = in.readString();
        this.deviceProperties = in.readParcelable(DeviceProperties.class.getClassLoader());
        this.stationSkipUnit = in.readString();
    }

    public int getStationSkipLimit() {
        return stationSkipLimit;
    }

    public void setStationSkipLimit(int stationSkipLimit) {
        this.stationSkipLimit = stationSkipLimit;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerAuthToken() {
        return partnerAuthToken;
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        this.partnerAuthToken = partnerAuthToken;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    public DeviceProperties getDeviceProperties() {
        return deviceProperties;
    }

    public void setDeviceProperties(DeviceProperties deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    public String getStationSkipUnit() {
        return stationSkipUnit;
    }

    public void setStationSkipUnit(String stationSkipUnit) {
        this.stationSkipUnit = stationSkipUnit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stationSkipLimit);
        dest.writeString(this.partnerId);
        dest.writeString(this.partnerAuthToken);
        dest.writeString(this.syncTime);
        dest.writeParcelable(this.deviceProperties, flags);
        dest.writeString(this.stationSkipUnit);
    }

}