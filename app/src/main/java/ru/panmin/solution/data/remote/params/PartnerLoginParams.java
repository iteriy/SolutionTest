package ru.panmin.solution.data.remote.params;

import com.google.gson.annotations.SerializedName;

import ru.panmin.solution.utils.Constants;

public class PartnerLoginParams {

    @SerializedName("username")
    private String username = Constants.PARTNER_USERNAME;

    @SerializedName("password")
    private String password = Constants.PARTNER_PASSWORD;

    @SerializedName("deviceId")
    private String deviceId = Constants.DEVICE_ID;

    @SerializedName("decrpyt password")
    private String decrpyt = Constants.DECRYPT_PASSWORD;

    public PartnerLoginParams() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDecrpyt() {
        return decrpyt;
    }

    public void setDecrpyt(String decrpyt) {
        this.decrpyt = decrpyt;
    }

}