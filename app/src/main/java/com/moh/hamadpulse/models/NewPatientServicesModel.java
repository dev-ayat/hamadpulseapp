package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewPatientServicesModel {
    @SerializedName("LAB_ORDERS")
    @Expose
    private String lab_orders;
    @SerializedName("RAD_ORDERS")
    @Expose
    private String rad_orders;

    public String getLab_orders() {
        return lab_orders;
    }

    public void setLab_orders(String lab_orders) {
        this.lab_orders = lab_orders;
    }

    public String getRad_orders() {
        return rad_orders;
    }

    public void setRad_orders(String rad_orders) {
        this.rad_orders = rad_orders;
    }

    @Override
    public String toString() {
        return "NewPatientServicesModel{" +
                "lab_orders='" + lab_orders + '\'' +
                ", rad_orders='" + rad_orders + '\'' +
                '}';
    }
}
