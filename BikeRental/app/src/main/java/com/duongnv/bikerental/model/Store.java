package com.duongnv.bikerental.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Store implements ClusterItem {

    private final String nameStore;
    private final LatLng latLng;

    public Store(String nameStore, LatLng latLng) {
        this.nameStore = nameStore;
        this.latLng = latLng;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return nameStore;
    }

    @Override
    public String getSnippet() {
        return "";
    }
}


