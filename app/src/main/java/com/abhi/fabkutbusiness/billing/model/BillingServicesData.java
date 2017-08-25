package com.abhi.fabkutbusiness.billing.model;

import java.io.Serializable;

/**
 * Created by abhi on 27/05/17.
 */

public class BillingServicesData implements Serializable {

    private String name;
    private String originalPrice;

    public BillingServicesData(String s1,String s2) {
        setName(s1);
        setOriginalPrice(s2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
}
