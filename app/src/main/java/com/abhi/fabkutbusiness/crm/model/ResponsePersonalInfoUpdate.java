package com.abhi.fabkutbusiness.crm.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/15/2017.
 */

public class ResponsePersonalInfoUpdate {

    private String MESSAGE;
    private String STATUS;
    private ArrayList<ResponsePersonalInfoUpdatedata> data;


    public void setData(ArrayList<ResponsePersonalInfoUpdatedata> data) {
        this.data = data;
    }


    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public ArrayList<ResponsePersonalInfoUpdatedata> getData() {
        return data;
    }



}
