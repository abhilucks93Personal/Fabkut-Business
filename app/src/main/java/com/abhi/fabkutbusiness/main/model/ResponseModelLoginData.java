package com.abhi.fabkutbusiness.main.model;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelLoginData {

    private String Status;
    private String Business_id;
    private String Business_name;
    private String Business_email_id;
    private String Contact_no;
    private String salon_password;
    private String opening_hours;
    private String Closing_hours;
    private String seats;
    private Double tax_percentage;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBusiness_id() {
        return Business_id;
    }

    public void setBusiness_id(String business_id) {
        Business_id = business_id;
    }

    public String getBusiness_name() {
        return Business_name;
    }

    public void setBusiness_name(String business_name) {
        Business_name = business_name;
    }

    public String getBusiness_email_id() {
        return Business_email_id;
    }

    public void setBusiness_email_id(String business_email_id) {
        Business_email_id = business_email_id;
    }

    public String getContact_no() {
        return Contact_no;
    }

    public void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }

    public String getSalon_password() {
        return salon_password;
    }

    public void setSalon_password(String salon_password) {
        this.salon_password = salon_password;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getClosing_hours() {
        return Closing_hours;
    }

    public void setClosing_hours(String closing_hours) {
        Closing_hours = closing_hours;
    }

    public String getSeats() {
        if (seats == null)
            return "2";
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Double getTax_percentage() {
        if (tax_percentage == null)
            return 18.0;
        return tax_percentage;
    }

    public void setTax_percentage(Double tax_percentage) {
        this.tax_percentage = tax_percentage;
    }


}
