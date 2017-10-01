package com.abhi.fabkutbusiness.crm.model;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class ResponsePersonalInfoData {

    int business_id;
    int EndUser_id;
    String enduser_name;
    String dob;
    String anidate;
    int m_um;
    int Profile_Comp_Personal;
    int Profile_Comp_total;

    public int getProfile_Comp_Personal() {
        return Profile_Comp_Personal;
    }

    public void setProfile_Comp_Personal(int profile_Comp_Personal) {
        Profile_Comp_Personal = profile_Comp_Personal;
    }

    public int getProfile_Comp_total() {
        return Profile_Comp_total;
    }

    public void setProfile_Comp_total(int profile_Comp_total) {
        Profile_Comp_total = profile_Comp_total;
    }

    public int getM_um() {
        return m_um;
    }

    public void setM_um(int m_um) {
        this.m_um = m_um;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getEndUser_id() {
        return EndUser_id;
    }

    public void setEndUser_id(int endUser_id) {
        EndUser_id = endUser_id;
    }

    public String getEnduser_name() {
        return enduser_name;
    }

    public void setEnduser_name(String enduser_name) {
        this.enduser_name = enduser_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAnidate() {
        return anidate;
    }

    public void setAnidate(String anidate) {
        this.anidate = anidate;
    }
}

