
package com.moh.hamadpulse.models;


import com.google.gson.annotations.SerializedName;


public class DiabeticMasterCurModel {

    @SerializedName("ORDER_DATE")
    private String ORDER_DATE;
    @SerializedName("ORDER_COUNT")
    private String ORDER_COUNT;

    public String getOrder_date() {
        return ORDER_DATE;
    }

    public void setOrder_date(String order_date) {
        this.ORDER_DATE = order_date;
    }

    public String getORDER_COUNT() {
        return ORDER_COUNT;
    }

    public void setORDER_COUNT(String ORDER_COUNT) {
        this.ORDER_COUNT = ORDER_COUNT;
    }

}
