package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesStatisticsModel {
    @SerializedName("LAB_ORDERS")
    @Expose
    private String labOrders;
    @SerializedName("RAD_ORDERS")
    @Expose
    private String radOrders;
    @SerializedName("PHARM_ORDERS")
    @Expose
    private String pharmOrders;
    @SerializedName("NURSING_PRO")
    @Expose
    private String nursingPro;
    @SerializedName("VITAL_SIGN")
    @Expose
    private String vitalSign;
    @SerializedName("DOCTOR_NOTES")
    @Expose
    private String doctorNotes;
    @SerializedName("VINTALATION")
    @Expose
    private String vintalation;
    @SerializedName("DAILY_PROG")
    @Expose
    private String dailyProg;

    public String getLabOrders() {
        return labOrders;
    }

    public void setLabOrders(String labOrders) {
        this.labOrders = labOrders;
    }

    public String getRadOrders() {
        return radOrders;
    }

    public void setRadOrders(String radOrders) {
        this.radOrders = radOrders;
    }

    public String getPharmOrders() {
        return pharmOrders;
    }

    public void setPharmOrders(String pharmOrders) {
        this.pharmOrders = pharmOrders;
    }

    public String getNursingPro() {
        return nursingPro;
    }

    public void setNursingPro(String nursingPro) {
        this.nursingPro = nursingPro;
    }

    public String getVitalSign() {
        return vitalSign;
    }

    public void setVitalSign(String vitalSign) {
        this.vitalSign = vitalSign;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getVintalation() {
        return vintalation;
    }

    public void setVintalation(String vintalation) {
        this.vintalation = vintalation;
    }

    public String getDailyProg() {
        return dailyProg;
    }

    public void setDailyProg(String dailyProg) {
        this.dailyProg = dailyProg;
    }
}
