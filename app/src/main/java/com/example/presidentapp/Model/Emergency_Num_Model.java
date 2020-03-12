package com.example.presidentapp.Model;

public class Emergency_Num_Model {
    String emergencyNumberId;
    String emergencyName;
    String emergencyNumber;

    public Emergency_Num_Model () {}

    public Emergency_Num_Model(String emergencyNumberId, String emergencyName, String emergencyNumber) {
        this.emergencyNumberId = emergencyNumberId;
        this.emergencyName = emergencyName;
        this.emergencyNumber = emergencyNumber;
    }

    public String getEmergencyNumberId() {
        return emergencyNumberId;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }
}

