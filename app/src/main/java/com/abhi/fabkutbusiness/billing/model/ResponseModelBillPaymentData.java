package com.abhi.fabkutbusiness.billing.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abhi on 23/07/17.
 */

public class ResponseModelBillPaymentData {

    private String businessId;
    private String billId;
    private String customerId;
    private String customerName;
    private String subtotal;
    private String tax;
    private String total;
    private String paid;
    private String unPaid;
    private String isWaivedOff;
    private String remark;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getUnPaid() {
        return unPaid;
    }

    public void setUnPaid(String unPaid) {
        this.unPaid = unPaid;
    }

    public String getIsWaivedOff() {
        return isWaivedOff;
    }

    public void setIsWaivedOff(String isWaivedOff) {
        this.isWaivedOff = isWaivedOff;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
