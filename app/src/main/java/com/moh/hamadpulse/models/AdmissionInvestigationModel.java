package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdmissionInvestigationModel {
    @SerializedName("INVES_CODE")
    @Expose
    private String INVES_CODE;
    @SerializedName("ADM_CD")
    @Expose
    private String ADM_CD;
    @SerializedName("LOOKUP_CD")
    @Expose
    private String LOOKUP_CD;
    @SerializedName("LOOKUP_DETAILS")
    @Expose
    private String LOOKUP_DETAILS;

    public String getINVES_CODE() {
        return INVES_CODE;
    }

    public void setINVES_CODE(String INVES_CODE) {
        this.INVES_CODE = INVES_CODE;
    }

    public String getADM_CD() {
        return ADM_CD;
    }

    public void setADM_CD(String ADM_CD) {
        this.ADM_CD = ADM_CD;
    }

    public String getLOOKUP_CD() {
        return LOOKUP_CD;
    }

    public void setLOOKUP_CD(String LOOKUP_CD) {
        this.LOOKUP_CD = LOOKUP_CD;
    }

    public String getLOOKUP_DETAILS() {
        return LOOKUP_DETAILS;
    }

    public void setLOOKUP_DETAILS(String LOOKUP_DETAILS) {
        this.LOOKUP_DETAILS = LOOKUP_DETAILS;
    }

    @Override
    public String toString() {
        return LOOKUP_DETAILS;
    }
}
