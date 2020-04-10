package com.example.presidentapp.Model;

import com.example.presidentapp.GateKeeper.GateKeeper_gatekeeper_add;

public class GateKeeper_AddGateKeeperModel{
    String gatekeeperId;
    String gatekeeperfirstname;
    String gatekeeperlastname;
    String gatekeeperemail;
    String gatekeepermobilenumber;

    public GateKeeper_AddGateKeeperModel(){}

    public GateKeeper_AddGateKeeperModel(String gatekeeperId, String gatekeeperfirstname, String gatekeeperlastname, String gatekeeperemail, String gatekeepermobilenumber) {
        this.gatekeeperId = gatekeeperId;
        this.gatekeeperfirstname = gatekeeperfirstname;
        this.gatekeeperlastname = gatekeeperlastname;
        this.gatekeeperemail = gatekeeperemail;
        this.gatekeepermobilenumber = gatekeepermobilenumber;
    }

    public String getGatekeeperId() {
        return gatekeeperId;
    }

    public String getGatekeeperfirstname() {
        return gatekeeperfirstname;
    }

    public String getGatekeeperlastname() {
        return gatekeeperlastname;
    }

    public String getGatekeeperemail() {
        return gatekeeperemail;
    }

    public String getGatekeepermobilenumber() {
        return gatekeepermobilenumber;
    }
}
