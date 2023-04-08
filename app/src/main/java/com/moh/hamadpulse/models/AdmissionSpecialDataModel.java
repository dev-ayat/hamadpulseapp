package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdmissionSpecialDataModel {

    @SerializedName("ADMISSION_ENTER_DOC_NOTE")
    @Expose
    private String ADMISSION_ENTER_DOC_NOTE;
    @SerializedName("REASON_OF_ADMISSION")
    @Expose
    private String REASON_OF_ADMISSION;
    @SerializedName("ADDMISSION_REMARK")
    @Expose
    private String ADDMISSION_REMARK;
    @SerializedName("ADDMISSION_DIET")
    @Expose
    private String ADDMISSION_DIET;
    @SerializedName("ADDMISSION_INSTRUCTIONS")
    @Expose
    private String ADDMISSION_INSTRUCTIONS;

    public String getADMISSION_ENTER_DOC_NOTE() {
        return ADMISSION_ENTER_DOC_NOTE;
    }

    public void setADMISSION_ENTER_DOC_NOTE(String ADMISSION_ENTER_DOC_NOTE) {
        this.ADMISSION_ENTER_DOC_NOTE = ADMISSION_ENTER_DOC_NOTE;
    }

    public String getREASON_OF_ADMISSION() {
        return REASON_OF_ADMISSION;
    }

    public void setREASON_OF_ADMISSION(String REASON_OF_ADMISSION) {
        this.REASON_OF_ADMISSION = REASON_OF_ADMISSION;
    }

    public String getADDMISSION_REMARK() {
        return ADDMISSION_REMARK;
    }

    public void setADDMISSION_REMARK(String ADDMISSION_REMARK) {
        this.ADDMISSION_REMARK = ADDMISSION_REMARK;
    }

    public String getADDMISSION_DIET() {
        return ADDMISSION_DIET;
    }

    public void setADDMISSION_DIET(String ADDMISSION_DIET) {
        this.ADDMISSION_DIET = ADDMISSION_DIET;
    }

    public String getADDMISSION_INSTRUCTIONS() {
        return ADDMISSION_INSTRUCTIONS;
    }

    public void setADDMISSION_INSTRUCTIONS(String ADDMISSION_INSTRUCTIONS) {
        this.ADDMISSION_INSTRUCTIONS = ADDMISSION_INSTRUCTIONS;
    }
}
