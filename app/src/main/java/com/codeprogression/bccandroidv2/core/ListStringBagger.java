package com.codeprogression.bccandroidv2.core;

import android.os.Parcel;

import com.hannesdorfmann.parcelableplease.ParcelBagger;

import java.util.ArrayList;
import java.util.List;

public class ListStringBagger implements ParcelBagger<List<String>> {
    @Override public void write(List<String> value, Parcel out, int flags) {
        out.writeStringList(value);
    }

    @Override public List<String> read(Parcel in) {
        List<String> list = new ArrayList<>();
        in.readStringList(list);
        return list;
    }
}