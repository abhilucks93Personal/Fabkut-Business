package com.abhi.fabkutbusiness.main.model;

import java.util.ArrayList;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelSeats {

    private String MESSAGE;

    private String STATUS;

    private ArrayList<ResponseModelSeatsData> data;

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

    public ArrayList<ResponseModelSeatsData> getData() {
        if (data == null)
            data = new ArrayList<>();

        return data;
    }

    public void setData(ArrayList<ResponseModelSeatsData> data) {
        this.data = data;
    }
}
