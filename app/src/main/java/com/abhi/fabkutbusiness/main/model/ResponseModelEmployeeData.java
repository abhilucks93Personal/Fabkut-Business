package com.abhi.fabkutbusiness.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelEmployeeData implements Parcelable {

    private String business_id;
    private String business_Name;
    private String emp_id;
    private String emp_name;
    private String emp_profile_image;
    private ArrayList<String> bookedSlots = new ArrayList<>();

    protected ResponseModelEmployeeData(Parcel in) {
        business_id = in.readString();
        business_Name = in.readString();
        emp_id = in.readString();
        emp_name = in.readString();
        emp_profile_image = in.readString();
        bookedSlots = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(business_id);
        dest.writeString(business_Name);
        dest.writeString(emp_id);
        dest.writeString(emp_name);
        dest.writeString(emp_profile_image);
        dest.writeStringList(bookedSlots);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseModelEmployeeData> CREATOR = new Creator<ResponseModelEmployeeData>() {
        @Override
        public ResponseModelEmployeeData createFromParcel(Parcel in) {
            return new ResponseModelEmployeeData(in);
        }

        @Override
        public ResponseModelEmployeeData[] newArray(int size) {
            return new ResponseModelEmployeeData[size];
        }
    };

    public ArrayList<String> getBookedSlots() {
        if (bookedSlots == null)
            bookedSlots = new ArrayList<>();
        return bookedSlots;
    }

    public void setBookedSlots(ArrayList<String> bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_Name() {
        return business_Name;
    }

    public void setBusiness_Name(String business_Name) {
        this.business_Name = business_Name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        if (emp_name == null)
            return "";
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_profile_image() {
        if (emp_profile_image == null)
            return "dummy_url";
        if (emp_profile_image.length() == 0)
            return "dummy_url";
        return emp_profile_image;
    }

    public void setEmp_profile_image(String emp_profile_image) {
        this.emp_profile_image = emp_profile_image;
    }


}
