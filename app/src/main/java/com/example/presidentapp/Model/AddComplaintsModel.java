package com.example.presidentapp.Model;

public class AddComplaintsModel{
    String complaintId;
    String complaintname;
    String complaintdescription;
    String complaintcategory;
    String complaintpriority;
    String complaintstatus;

    public AddComplaintsModel(){}

    public AddComplaintsModel(String complaintId, String complaintname, String complaintdescription, String complaintcategory, String complaintpriority, String complaintstatus) {
        this.complaintId = complaintId;
        this.complaintname = complaintname;
        this.complaintdescription = complaintdescription;
        this.complaintcategory = complaintcategory;
        this.complaintpriority = complaintpriority;
        this.complaintstatus = complaintstatus;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public String getComplaintname() {
        return complaintname;
    }

    public String getComplaintdescription() {
        return complaintdescription;
    }

    public String getComplaintcategory() {
        return complaintcategory;
    }

    public String getComplaintpriority() {
        return complaintpriority;
    }

    public String getComplaintstatus() {
        return complaintstatus;
    }
}
