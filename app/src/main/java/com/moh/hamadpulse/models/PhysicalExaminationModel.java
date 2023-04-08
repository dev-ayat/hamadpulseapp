package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhysicalExaminationModel {
    @SerializedName("PH_EXAM_CD")
    @Expose
    private String PH_EXAM_CD;
    @SerializedName("PH_EXAM_NOTE")
    @Expose
    private String PH_EXAM_NOTE;
    @SerializedName("P_USER_ID")
    @Expose
    private String USER_ID;
    @SerializedName("P_CREATED_ON")
    @Expose
    private String P_CREATED_ON;

    public String getPH_EXAM_CD() {
        return PH_EXAM_CD;
    }

    public void setPH_EXAM_CD(String PH_EXAM_CD) {
        this.PH_EXAM_CD = PH_EXAM_CD;
    }

    public String getPH_EXAM_NOTE() {
        return PH_EXAM_NOTE;
    }

    public void setPH_EXAM_NOTE(String PH_EXAM_NOTE) {
        this.PH_EXAM_NOTE = PH_EXAM_NOTE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getP_CREATED_ON() {
        return P_CREATED_ON;
    }

    public void setP_CREATED_ON(String p_CREATED_ON) {
        P_CREATED_ON = p_CREATED_ON;
    }

    @Override
    public String toString() {
        return  PH_EXAM_NOTE;
    }
}
