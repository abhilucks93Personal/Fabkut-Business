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
    private Integer previous_balance;
    private Long total_revenue;
    private Integer total_visits;

    public ResponseModelCustomerData(String business_id, String business_Name, String endUser_FirstName, String endUser_LastName, String gender, String email, String contact_no, String allergies) {
        this.customerId = "" + System.currentTimeMillis();
        this.business_id = business_id;
        this.business_Name = business_Name;
        this.EndUser_FirstName = endUser_FirstName;
        this.EndUser_LastName = endUser_LastName;
        this.gender = gender;
        this.email = email;
        this.contact_no = contact_no;
        this.allergies = allergies;
        this.previous_balance = 0;
        this.total_revenue = Long.valueOf(0);
        this.total_visits = 0;
    }

    public ResponseModelCustomerData() {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerProfileImage() {
        return customerProfileImage;
    }

    public void setCustomerProfileImage(String customerProfileImage) {
        this.customerProfileImage = customerProfileImage;
    }

    public String getCustomerProfilePercentage() {
        return customerProfilePercentage;
    }

    public void setCustomerProfilePercentage(String customerProfilePercentage) {
        this.customerProfilePercentage = customerProfilePercentage;
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
            allergies = "";
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Integer getPrevious_balance() {
        if (previous_balance == null)
            return 0;
        return previous_balance;
    }

    public void setPrevious_balance(Integer previous_balance) {
        this.previous_balance = previous_balance;
    }

    public Long getTotal_revenue() {
        if (total_revenue == null)
            return Long.valueOf(0);
        return total_revenue;
    }

    public void setTotal_revenue(Long total_revenue) {
        this.total_revenue = total_revenue;
    }

    public Integer getTotal_visits() {
        if (total_visits == null)
            return 0;
        return total_visits;
    }

    public void setTotal_visits(Integer total_visits) {
        this.total_visits = total_visits;
    }
}
