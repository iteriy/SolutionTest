package ru.panmin.solution.data.remote.params;

import com.google.gson.annotations.SerializedName;

public class UserLoginParams {

    @SerializedName("loginType")
    private String loginType = "user";

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("partnerAuthToken")
    private String partnerAuthToken;

    @SerializedName("syncTime")
    private long syncTime;

    public UserLoginParams() {
    }

    public UserLoginParams(String email, String password) {
        username = email;
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
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

    public String getPartnerAuthToken() {
        return partnerAuthToken;
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        this.partnerAuthToken = partnerAuthToken;
    }

    public long getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(long syncTime) {
        this.syncTime = syncTime;
    }

}