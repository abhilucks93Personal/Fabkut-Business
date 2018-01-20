package com.abhi.fabkutbusiness.retrofit;

import com.abhi.fabkutbusiness.main.model.ResponseModelLoginData;

import java.util.ArrayList;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModel {

    private String MESSAGE;

    private String STATUS;

    private Object data;

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


}
