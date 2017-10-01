package com.abhi.fabkutbusiness.crm.model;

/**
 * Created by SIDDHARTH on 9/15/217.
 */

public class ResponsePersonalInfoUpdatedata {

    private String Status;
    private int business_id;
    private int EndUser_id;
    private String enduser_name;
    private String dob;
    private int m_um;
    private String anidate;
    private int Profile_Comp_Personal;
    private int Profile_Comp_total;

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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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

