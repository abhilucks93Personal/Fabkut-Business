package com.abhi.fabkutbusiness.billing.model;

import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abhi on 25/09/17.
 */

public class ResponseModelBillingList {

    private String MESSAGE;

    private String STATUS;

    private HashMap<String, ArrayList<ResponseModelAppointmentsData>> DATA;

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

    public HashMap<String, ArrayList<ResponseModelAppointmentsData>> getDATA() {
        if (DATA == null)
            DATA = new HashMap<>();
        return DATA;
    }

    public void setDATA(HashMap<String, ArrayList<ResponseModelAppointmentsData>> DATA) {
        this.DATA = DATA;
    }
}
