package ru.panmin.solution.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlayList implements Parcelable {

    public static final Parcelable.Creator<PlayList> CREATOR = new Parcelable.Creator<PlayList>() {
        @Override
        public PlayList createFromParcel(Parcel source) {
            return new PlayList(source);
        }

        @Override
        public PlayList[] newArray(int size) {
            return new PlayList[size];
        }
    };

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("tracks")
    private List<Track> tracks = new ArrayList<>();

    public PlayList() {
    }

    public PlayList(@NonNull Track track) {
        name = track.getName();
        tracks = new ArrayList<>();
        tracks.clear();
        tracks.add(track);
    }

    private PlayList(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.tracks = in.createTypedArrayList(Track.CREATOR);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.tracks);
    }

}