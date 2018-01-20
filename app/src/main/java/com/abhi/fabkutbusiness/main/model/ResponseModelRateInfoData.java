package com.abhi.fabkutbusiness.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelRateInfoData implements Parcelable {

    @SerializedName("sub_service_id")
    private String id;
    private String business_id;
    private String business_Name;
    private String sub_service_name;
    private String employee_name;
    private String employee_id;
    private String rate;
    private String eta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSub_service_name() {
        return sub_service_name;
    }

    public void setSub_service_name(String sub_service_name) {
        this.sub_service_name = sub_service_name;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getRate() {
        if (rate == null)
            return "0";
        if (rate.equals(""))
            return "0";
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    protected ResponseModelRateInfoData(Parcel in) {
        id = in.readString();
        business_id = in.readString();
        business_Name = in.readString();
        sub_service_name = in.readString();
        employee_name = in.readString();
        employee_id = in.readString();
        rate = in.readString();
        eta = in.readString();
    }

    public static final Creator<ResponseModelRateInfoData> CREATOR = new Creator<ResponseModelRateInfoData>() {
        @Override
        public ResponseModelRateInfoData createFromParcel(Parcel in) {
            return new ResponseModelRateInfoData(in);
        }

        @Override
        public ResponseModelRateInfoData[] newArray(int size) {
            return new ResponseModelRateInfoData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(business_id);
        dest.writeString(business_Name);
        dest.writeString(sub_service_name);
        dest.writeString(employee_name);
        dest.writeString(employee_id);
        dest.writeString(rate);
        dest.writeString(eta);
    }
}
