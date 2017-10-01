package com.abhi.fabkutbusiness.crm.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class ResponseBasicInfoUpdate {
    private String MESSAGE;
    private String STATUS;

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

    public ArrayList<ResponseBasicInfoDataUpdate> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseBasicInfoDataUpdate> data) {
        this.data = data;
    }

    private ArrayList<ResponseBasicInfoDataUpdate> data;
}
