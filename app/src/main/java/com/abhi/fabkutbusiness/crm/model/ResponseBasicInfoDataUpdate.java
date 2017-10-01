package com.abhi.fabkutbusiness.crm.model;

/**
 * Created by SIDDHARTH on 9/12/2017.
 */

public class ResponseBasicInfoDataUpdate {

    String Status;
    String business_id;
    String EndUser_id;
    String enduser_name;
    String lname;
    String gender;
    String email;
    String contact_no;
    String alternetContact;
    String allergies;
    int Profile_Comp_Basic;
    int Profile_Comp_total;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getEndUser_id() {
        return EndUser_id;
    }

    public void setEndUser_id(String endUser_id) {
        EndUser_id = endUser_id;
    }

    public String getEnduser_name() {
        return enduser_name;
    }

    public void setEnduser_name(String enduser_name) {
        this.enduser_name = enduser_name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAlternetContact() {
        return alternetContact;
    }

    public void setAlternetContact(String alternetContact) {
        this.alternetContact = alternetContact;
    }

    public int getProfile_Comp_Basic() {
        return Profile_Comp_Basic;
    }

    public void setProfile_Comp_Basic(int profile_Comp_Basic) {
        Profile_Comp_Basic = profile_Comp_Basic;
    }

    public int getProfile_Comp_total() {
        return Profile_Comp_total;
    }

    public void setProfile_Comp_total(int profile_Comp_total) {
        Profile_Comp_total = profile_Comp_total;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

}
