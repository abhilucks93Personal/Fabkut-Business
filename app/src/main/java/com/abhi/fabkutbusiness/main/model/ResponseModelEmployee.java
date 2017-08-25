package com.abhi.fabkutbusiness.main.model;

import java.util.ArrayList;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelEmployee {

    private String MESSAGE;

    private String STATUS;

    private ArrayList<ResponseModelEmployeeData> data;

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

    public ArrayList<ResponseModelEmployeeData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseModelEmployeeData> data) {
        this.data = data;
    }
}
