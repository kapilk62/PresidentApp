package com.example.presidentapp.Model;

public class AddAnnouncementModel{
    String annoucementId;
    String annoucementName;
    String annoucementDescrpition;
    String annoucementDate;
    String annoucementTime;
    String annoucementType;

    public AddAnnouncementModel(){}

    public AddAnnouncementModel(String annoucementId, String annoucementName, String annoucementDescrpition, String annoucementDate, String annoucementTime, String annoucementType) {
        this.annoucementId = annoucementId;
        this.annoucementName = annoucementName;
        this.annoucementDescrpition = annoucementDescrpition;
        this.annoucementDate = annoucementDate;
        this.annoucementTime = annoucementTime;
        this.annoucementType = annoucementType;
    }

    public String getAnnoucementId() {
        return annoucementId;
    }

    public String getAnnoucementName() {
        return annoucementName;
    }

    public String getAnnoucementDescrpition() {
        return annoucementDescrpition;
    }

    public String getAnnoucementDate() {
        return annoucementDate;
    }

    public String getAnnoucementTime() {
        return annoucementTime;
    }

    public String getAnnoucementType() {
        return annoucementType;
    }
}
