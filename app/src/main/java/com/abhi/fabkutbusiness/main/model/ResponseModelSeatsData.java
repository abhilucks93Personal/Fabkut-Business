package com.abhi.fabkutbusiness.main.model;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class ResponseModelSeatsData {

    private String seatNum;

    private ResponseModelAppointmentsData bookingData;

    private ArrayList<String> slots;

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public ResponseModelAppointmentsData getBookingData() {
        return bookingData;
    }

    public void setBookingData(ResponseModelAppointmentsData bookingData) {
        this.bookingData = bookingData;
    }

    public ArrayList<String> getSlots() {
        if (slots == null)
            slots = new ArrayList<>();
        return slots;
    }

    public void setSlots(ArrayList<String> slots) {
        this.slots = slots;
    }
}