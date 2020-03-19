package com.example.presidentapp.Model;

public class MyBuildingAddBankModel{

    String addbankId;
    String addbankname;
    String addbankUPIId;
    String addbankaccountname;
    String addbankaccountnumber;
    String addbankIFSCcode;
    String addbankaddress;

    public MyBuildingAddBankModel(){}

    public MyBuildingAddBankModel(String addbankId, String addbankname, String addbankUPIId, String addbankaccountname, String addbankaccountnumber, String addbankIFSCcode, String addbankaddress) {
        this.addbankId = addbankId;
        this.addbankname = addbankname;
        this.addbankUPIId = addbankUPIId;
        this.addbankaccountname = addbankaccountname;
        this.addbankaccountnumber = addbankaccountnumber;
        this.addbankIFSCcode = addbankIFSCcode;
        this.addbankaddress = addbankaddress;
    }

    public String getAddbankId() {
        return addbankId;
    }

    public String getAddbankname() {
        return addbankname;
    }

    public String getAddbankUPIId() {
        return addbankUPIId;
    }

    public String getAddbankaccountname() {
        return addbankaccountname;
    }

    public String getAddbankaccountnumber() {
        return addbankaccountnumber;
    }

    public String getAddbankIFSCcode() {
        return addbankIFSCcode;
    }

    public String getAddbankaddress() {
        return addbankaddress;
    }
}

