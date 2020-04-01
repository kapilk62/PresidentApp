package com.example.presidentapp;

import android.app.Application;

public class GlobalClass extends Application {
    private String buildingId;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
