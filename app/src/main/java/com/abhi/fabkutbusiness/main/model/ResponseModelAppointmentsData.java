package com.abhi.fabkutbusiness.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class ResponseModelAppointmentsData implements Parcelable {

    private long bookingId;

    private String businessId;

    private String customerId;

    private String customerMobile;

    private String customerProfileImage;

    private String customerProfilePercentage;

    private String customerEndUser_FirstName;

    private String customerEndUser_LastName;

    private String customerGender;

    private String customerEmail;

    private String customerAllergies;

    private int bookingType;

    private int bookingStatus;

    private String cancelReason = "";

    private String bookingDate;

    private String bookingTime;

    private String totalAmount;

    private String totalTime;

    private String seatNumber;

    private ArrayList<ResponseModelRateInfoData> services;

    private ArrayList<String> slots;

    private ResponseModelEmployeeData employee;

    private ResponseModelBillPaymentData billPayment;

    private ArrayList<String> serviceTrackRecord;

    public ResponseModelAppointmentsData() {
    }


    protected ResponseModelAppointmentsData(Parcel in) {
        bookingId = in.readLong();
        businessId = in.readString();
        customerId = in.readString();
        customerMobile = in.readString();
        customerProfileImage = in.readString();
        customerProfilePercentage = in.readString();
        customerEndUser_FirstName = in.readString();
        customerEndUser_LastName = in.readString();
        customerGender = in.readString();
        customerEmail = in.readString();
        customerAllergies = in.readString();
        bookingType = in.readInt();
        bookingStatus = in.readInt();
        cancelReason = in.readString();
        bookingDate = in.readString();
        bookingTime = in.readString();
        totalAmount = in.readString();
        totalTime = in.readString();
        seatNumber = in.readString();
        services = in.createTypedArrayList(ResponseModelRateInfoData.CREATOR);
        slots = in.createStringArrayList();
        serviceTrackRecord = in.createStringArrayList();
        employee = in.readParcelable(ResponseModelEmployeeData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bookingId);
        dest.writeString(businessId);
        dest.writeString(customerId);
        dest.writeString(customerMobile);
        dest.writeString(customerProfileImage);
        dest.writeString(customerProfilePercentage);
        dest.writeString(customerEndUser_FirstName);
        dest.writeString(customerEndUser_LastName);
        dest.writeString(customerGender);
        dest.writeString(customerEmail);
        dest.writeString(customerAllergies);
        dest.writeInt(bookingType);
        dest.writeInt(bookingStatus);
        dest.writeString(cancelReason);
        dest.writeString(bookingDate);
        dest.writeString(bookingTime);
        dest.writeString(totalAmount);
        dest.writeString(totalTime);
        dest.writeString(seatNumber);
        dest.writeTypedList(services);
        dest.writeStringList(slots);
        dest.writeStringList(serviceTrackRecord);
        dest.writeParcelable(employee, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseModelAppointmentsData> CREATOR = new Creator<ResponseModelAppointmentsData>() {
        @Override
        public ResponseModelAppointmentsData createFromParcel(Parcel in) {
            return new ResponseModelAppointmentsData(in);
        }

        @Override
        public ResponseModelAppointmentsData[] newArray(int size) {
            return new ResponseModelAppointmentsData[size];
        }
    };

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerProfileImage() {
        return customerProfileImage;
    }

    public void setCustomerProfileImage(String customerProfileImage) {
        this.customerProfileImage = customerProfileImage;
    }

    public String getCustomerProfilePercentage() {
        return customerProfilePercentage;
    }

    public void setCustomerProfilePercentage(String customerProfilePercentage) {
        this.customerProfilePercentage = customerProfilePercentage;
    }

    public String getCustomerEndUser_FirstName() {
        return customerEndUser_FirstName;
    }

    public void setCustomerEndUser_FirstName(String customerEndUser_FirstName) {
        this.customerEndUser_FirstName = customerEndUser_FirstName;
    }

    public String getCustomerEndUser_LastName() {
        return customerEndUser_LastName;
    }

    public void setCustomerEndUser_LastName(String customerEndUser_LastName) {
        this.customerEndUser_LastName = customerEndUser_LastName;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAllergies() {
        return customerAllergies;
    }

    public void setCustomerAllergies(String customerAllergies) {
        this.customerAllergies = customerAllergies;
    }

    public int getBookingType() {
        return bookingType;
    }

    public void setBookingType(int bookingType) {
        this.bookingType = bookingType;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getTotalAmount() {
        if (totalAmount == null)
            return "0";
        if (totalAmount.equals(""))
            return "0";
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public ArrayList<ResponseModelRateInfoData> getServices() {
        return services;
    }

    public void setServices(ArrayList<ResponseModelRateInfoData> services) {
        this.services = services;
    }

    public ArrayList<String> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<String> slots) {
        this.slots = slots;
    }

    public ResponseModelEmployeeData getEmployee() {
        return employee;
    }

    public void setEmployee(ResponseModelEmployeeData employee) {
        this.employee = employee;
    }

    public ResponseModelBillPaymentData getBillPayment() {
        return billPayment;
    }

    public void setBillPayment(ResponseModelBillPaymentData billPayment) {
        this.billPayment = billPayment;
    }

    public ArrayList<String> getServiceTrackRecord() {
        if (serviceTrackRecord == null)
            serviceTrackRecord = new ArrayList<>();
        return serviceTrackRecord;
    }

    public void setServiceTrackRecord(ArrayList<String> serviceTrackRecord) {
        this.serviceTrackRecord = serviceTrackRecord;
    }
}