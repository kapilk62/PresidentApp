package com.example.presidentapp.Model;

public class Vendor_Num_Model {
    String vendorNumberid;
    String vendorName;
    String vendorNumber;
    String vendorAddress;
    String vendorCategory;

    public Vendor_Num_Model(){};

    public Vendor_Num_Model(String vendorNumberid, String vendorName, String vendorNumber,String vendorAddress,String vendorCategory)
    {
        this.vendorAddress = vendorAddress;
        this.vendorName = vendorName;
        this.vendorNumber = vendorNumber;
        this.vendorNumberid=vendorNumberid;
        this.vendorCategory=vendorCategory;

    };

    public String getVendorNumberid() {
        return vendorNumberid;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public String getVendorCategory() {
        return vendorCategory;
    }



}
