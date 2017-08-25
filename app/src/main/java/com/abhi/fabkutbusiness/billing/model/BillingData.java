package com.abhi.fabkutbusiness.billing.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class BillingData implements Serializable {


    public BillingData(String s, String s2, String s3) {
        setName(s);
        ArrayList<BillingServicesData> str = new ArrayList<>();
        str.add(new BillingServicesData("Party mehndi",s2));
        str.add(new BillingServicesData("Package for Gents",s3));
        setBillingServicesDatas(str);

    }

    private String Name;

    private ArrayList<BillingServicesData> billingServicesDatas;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<BillingServicesData> getBillingServicesDatas() {
        return billingServicesDatas;
    }

    public void setBillingServicesDatas(ArrayList<BillingServicesData> billingServicesDatas) {
        this.billingServicesDatas = billingServicesDatas;
    }
}
