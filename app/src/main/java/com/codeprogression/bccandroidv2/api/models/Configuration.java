package com.codeprogression.bccandroidv2.api.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.Parcels;

import java.util.List;

import lombok.Getter;

@Parcel
public class Configuration {
    @Expose @Getter Images images;
    @Expose @Getter List<String> changeKeys;

    @ParcelConstructor
    public Configuration() {
    }

    public Parcelable toParcelable(){
        return Parcels.wrap(this);
    }

    public static Configuration fromParcelable(Parcelable parcelable){
        return Parcels.unwrap(parcelable);
    }
}
