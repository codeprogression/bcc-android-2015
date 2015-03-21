package com.codeprogression.bccandroidv2.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.codeprogression.bccandroidv2.core.ListStringBagger;
import com.google.gson.annotations.Expose;
import com.hannesdorfmann.parcelableplease.annotation.Bagger;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Getter;

@ParcelablePlease
public class Images implements Parcelable {

    @Expose @Getter String baseUrl;
    @Expose @Getter String secureBaseUrl;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> backdropSizes;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> logoSizes;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> posterSizes;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> profileSizes;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> stillSizes;

    @Override public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ImagesParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            Images target = new Images();
            ImagesParcelablePlease.readFromParcel(target, source);
            return target;
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
