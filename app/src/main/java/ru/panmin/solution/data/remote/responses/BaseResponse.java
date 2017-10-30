package ru.panmin.solution.data.remote.responses;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse<T> {

    public static final String STAT_OK = "ok";
    public static final String STAT_FAIL = "fail";

    @SerializedName("stat")
    private String stat;

    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private T result;

    public BaseResponse() {
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return TextUtils.equals(stat, STAT_OK);
    }

}