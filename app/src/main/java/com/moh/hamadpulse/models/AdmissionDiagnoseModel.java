package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdmissionDiagnoseModel {
    @SerializedName("ICD_NAME_EN")
    @Expose
    private String ICD_NAME_EN;
    @SerializedName("DIAGNOSIS_CODE")
    @Expose
    private String DIAGNOSIS_CODE;
    @SerializedName("DIAGNOSIS_ICD_CD")
    @Expose
    private String DIAGNOSIS_ICD_CD;
    @SerializedName("DIAGNOSIS_STATUS_CD")
    @Expose
    private String DIAGNOSIS_STATUS_CD;

    public String getICD_NAME_EN() {
        return ICD_NAME_EN;
    }

    public void setICD_NAME_EN(String ICD_NAME_EN) {
        this.ICD_NAME_EN = ICD_NAME_EN;
    }

    public String getDIAGNOSIS_CODE() {
        return DIAGNOSIS_CODE;
    }

    public void setDIAGNOSIS_CODE(String DIAGNOSIS_CODE) {
        this.DIAGNOSIS_CODE = DIAGNOSIS_CODE;
    }

    public String getDIAGNOSIS_ICD_CD() {
        return DIAGNOSIS_ICD_CD;
    }

    public void setDIAGNOSIS_ICD_CD(String DIAGNOSIS_ICD_CD) {
        this.DIAGNOSIS_ICD_CD = DIAGNOSIS_ICD_CD;
    }

    public String getDIAGNOSIS_STATUS_CD() {
        return DIAGNOSIS_STATUS_CD;
    }

    public void setDIAGNOSIS_STATUS_CD(String DIAGNOSIS_STATUS_CD) {
        this.DIAGNOSIS_STATUS_CD = DIAGNOSIS_STATUS_CD;
    }

    @Override
    public String toString() {
        return ICD_NAME_EN;
    }
}
