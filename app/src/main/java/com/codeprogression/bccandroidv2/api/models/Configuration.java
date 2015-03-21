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
public class Configuration implements Parcelable {
    @Expose @Getter Images images;
    @Expose @Getter @Bagger(ListStringBagger.class) List<String> changeKeys;

    public Configuration() {
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {

        ConfigurationParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Parcelable.Creator<Configuration> CREATOR = new Parcelable.Creator<Configuration>() {
        @Override
        public Configuration createFromParcel(Parcel source) {
            Configuration target = new Configuration();
            ConfigurationParcelablePlease.readFromParcel(target, source);
            return target;
        }

        @Override
        public Configuration[] newArray(int size) {
            return new Configuration[size];
        }
    };


}
