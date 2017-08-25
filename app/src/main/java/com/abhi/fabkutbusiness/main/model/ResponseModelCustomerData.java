package com.abhi.fabkutbusiness.main.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelCustomerData implements Serializable {

    private String business_id;
    private String business_Name;
    private String customerId;
    @SerializedName("EndUser_name")
    private String EndUser_FirstName;
    private String EndUser_LastName;
    private String gender;
    private String email;
    private String contact_no;
    private String allergies;
    private String customerProfileImage;
    private String customerProfilePercentage;


    public ResponseModelCustomerData(String business_id, String business_Name, String endUser_FirstName, String endUser_LastName, String gender, String email, String contact_no, String allergies) {
        this.business_id = business_id;
        this.business_Name = business_Name;
        EndUser_FirstName = endUser_FirstName;
        EndUser_LastName = endUser_LastName;
        this.gender = gender;
        this.email = email;
        this.contact_no = contact_no;
        this.allergies = allergies;
    }

    public ResponseModelCustomerData() {

    }

    public String getBusiness_id() {
        if (business_id == null)
            return "-1";
        if (business_id.equals(""))
            return "-1";
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_Name() {
        if (business_Name == null)
            return "";
        return business_Name;
    }

    public void setBusiness_Name(String business_Name) {
        this.business_Name = business_Name;
    }

    public String getEndUser_FirstName() {
        if (EndUser_FirstName == null)
            return "";
        return EndUser_FirstName;
    }

    public void setEndUser_FirstName(String endUser_FirstName) {
        EndUser_FirstName = endUser_FirstName;
    }

    public String getEndUser_LastName() {
        if (EndUser_LastName == null)
            return "";
        return EndUser_LastName;
    }

    public void setEndUser_LastName(String endUser_LastName) {
        EndUser_LastName = endUser_LastName;
    }

    public String getGender() {
        if (gender == null)
            return "";
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        if (email == null)
            return "";
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        if (contact_no == null)
            return "";
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAllergies() {
        if (allergies == null)
            return "";
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
