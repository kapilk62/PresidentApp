package com.example.presidentapp.Model;

public class GateKeeper_AddGatesModel{
    String gatesId;
    String gatesName;

    public GateKeeper_AddGatesModel(){}

    public GateKeeper_AddGatesModel(String gatesId, String gatesName) {
        this.gatesId = gatesId;
        this.gatesName = gatesName;
    }

    public String getGatesId() {
        return gatesId;
    }

    public String getGatesName() {
        return gatesName;
    }
}
