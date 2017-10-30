package ru.panmin.solution.ui.progress;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

public class EmptyBundle implements Parcelable {

    public static final Parcelable.Creator<EmptyBundle> CREATOR = new Parcelable.Creator<EmptyBundle>() {
        @Override
        public EmptyBundle createFromParcel(Parcel source) {
            return new EmptyBundle(source);
        }

        @Override
        public EmptyBundle[] newArray(int size) {
            return new EmptyBundle[size];
        }
    };

    @Nullable @DrawableRes private Integer image = null;
    @Nullable private String textTitle = null;
    @Nullable private String textDescription = null;
    @Nullable private String textButton = null;

    public EmptyBundle() {
    }

    public EmptyBundle(@Nullable @DrawableRes Integer image, @Nullable String textTitle,
                       @Nullable String textDescription, @Nullable String textButton) {
        this.image = image;
        this.textTitle = textTitle;
        this.textDescription = textDescription;
        this.textButton = textButton;
    }

    private EmptyBundle(Parcel in) {
        image = (Integer) in.readValue(Integer.class.getClassLoader());
        textTitle = in.readString();
        textDescription = in.readString();
        textButton = in.readString();
    }

    @Nullable
    public Integer getImage() {
        return image;
    }

    public void setImage(@Nullable Integer image) {
        this.image = image;
    }

    @Nullable
    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(@Nullable String textTitle) {
        this.textTitle = textTitle;
    }

    @Nullable
    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(@Nullable String textDescription) {
        this.textDescription = textDescription;
    }

    @Nullable
    public String getTextButton() {
        return textButton;
    }

    public void setTextButton(@Nullable String textButton) {
        this.textButton = textButton;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
        dest.writeString(textTitle);
        dest.writeString(textDescription);
        dest.writeString(textButton);
    }

}