package com.abhi.fabkutbusiness.crm.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class ResponseCrmList {
    private String MESSAGE;

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

    public ArrayList<ResponseCrmListData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseCrmListData> data) {
        this.data = data;
    }

    private String STATUS;
    private ArrayList<ResponseCrmListData> data;
}
