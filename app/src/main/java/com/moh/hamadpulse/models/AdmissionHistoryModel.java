package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdmissionHistoryModel {
    @SerializedName("ADMISSION_CODE")
    @Expose
    private String ADMISSION_CODE;
    @SerializedName("TIME_IN")
    @Expose
    private String TIME_IN;
    @SerializedName("TIME_OUT")
    @Expose
    private String TIME_OUT;
    @SerializedName("LOC_NAME")
    @Expose
    private String LOC_NAME;

    public String getADMISSION_CODE() {
        return ADMISSION_CODE;
    }

    public void setADMISSION_CODE(String ADMISSION_CODE) {
        this.ADMISSION_CODE = ADMISSION_CODE;
    }

    public String getTIME_IN() {
        return TIME_IN;
    }

    public void setTIME_IN(String TIME_IN) {
        this.TIME_IN = TIME_IN;
    }

    public String getTIME_OUT() {
        return TIME_OUT;
    }

    public void setTIME_OUT(String TIME_OUT) {
        this.TIME_OUT = TIME_OUT;
    }

    public String getLOC_NAME() {
        return LOC_NAME;
    }

    public void setLOC_NAME(String LOC_NAME) {
        this.LOC_NAME = LOC_NAME;
    }
}
