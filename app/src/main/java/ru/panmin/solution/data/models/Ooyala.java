package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ooyala implements Parcelable {

    public static final Parcelable.Creator<Ooyala> CREATOR = new Parcelable.Creator<Ooyala>() {
        @Override
        public Ooyala createFromParcel(Parcel source) {
            return new Ooyala(source);
        }

        @Override
        public Ooyala[] newArray(int size) {
            return new Ooyala[size];
        }
    };

    @SerializedName("streamingPercentage")
    private int streamingPercentage;
    @SerializedName("streamingWhitelist")
    private List<Long> streamingWhitelist = new ArrayList<>();
    @SerializedName("videoAdBufferRetryCount")
    private int videoAdBufferRetryCount;
    @SerializedName("videoAdLoadingTimeout")
    private int videoAdLoadingTimeout;
    @SerializedName("videoAdPlayTimeout")
    private int videoAdPlayTimeout;

    public Ooyala() {
    }

    private Ooyala(Parcel in) {
        this.streamingPercentage = in.readInt();
        this.streamingWhitelist = new ArrayList<Long>();
        in.readList(this.streamingWhitelist, Long.class.getClassLoader());
        this.videoAdBufferRetryCount = in.readInt();
        this.videoAdLoadingTimeout = in.readInt();
        this.videoAdPlayTimeout = in.readInt();
    }

    public int getStreamingPercentage() {
        return streamingPercentage;
    }

    public void setStreamingPercentage(int streamingPercentage) {
        this.streamingPercentage = streamingPercentage;
    }

    public List<Long> getStreamingWhitelist() {
        return streamingWhitelist;
    }

    public void setStreamingWhitelist(List<Long> streamingWhitelist) {
        this.streamingWhitelist = streamingWhitelist;
    }

    public int getVideoAdBufferRetryCount() {
        return videoAdBufferRetryCount;
    }

    public void setVideoAdBufferRetryCount(int videoAdBufferRetryCount) {
        this.videoAdBufferRetryCount = videoAdBufferRetryCount;
    }

    public int getVideoAdLoadingTimeout() {
        return videoAdLoadingTimeout;
    }

    public void setVideoAdLoadingTimeout(int videoAdLoadingTimeout) {
        this.videoAdLoadingTimeout = videoAdLoadingTimeout;
    }

    public int getVideoAdPlayTimeout() {
        return videoAdPlayTimeout;
    }

    public void setVideoAdPlayTimeout(int videoAdPlayTimeout) {
        this.videoAdPlayTimeout = videoAdPlayTimeout;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.streamingPercentage);
        dest.writeList(this.streamingWhitelist);
        dest.writeInt(this.videoAdBufferRetryCount);
        dest.writeInt(this.videoAdLoadingTimeout);
        dest.writeInt(this.videoAdPlayTimeout);
    }

}