package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class ReferralPhysicalTherapyModel {
    @SerializedName("RM_MODEL_CODE")
    private String referral_cd;
    @SerializedName("RM_DIAGNOSIS")
    private String RM_DIAGNOSIS;
    @SerializedName("RM_DEAR_COLLEAGUE")
    private String RM_DEAR_COLLEAGUE;
    @SerializedName("RM_PRECAUTIONS")
    private String RM_PRECAUTIONS;
    @SerializedName("RM_CREATED_ON")
    private String RM_CREATED_ON;
    @SerializedName("USER_FULL_NAME")
    private String full_name;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getReferral_cd() {
        return referral_cd;
    }

    public void setReferral_cd(String referral_cd) {
        this.referral_cd = referral_cd;
    }

    public String getRM_DIAGNOSIS() {
        return RM_DIAGNOSIS;
    }

    public void setRM_DIAGNOSIS(String RM_DIAGNOSIS) {
        this.RM_DIAGNOSIS = RM_DIAGNOSIS;
    }

    public String getRM_DEAR_COLLEAGUE() {
        return RM_DEAR_COLLEAGUE;
    }

    public void setRM_DEAR_COLLEAGUE(String RM_DEAR_COLLEAGUE) {
        this.RM_DEAR_COLLEAGUE = RM_DEAR_COLLEAGUE;
    }

    public String getRM_PRECAUTIONS() {
        return RM_PRECAUTIONS;
    }

    public void setRM_PRECAUTIONS(String RM_PRECAUTIONS) {
        this.RM_PRECAUTIONS = RM_PRECAUTIONS;
    }

    public String getRM_CREATED_ON() {
        return RM_CREATED_ON;
    }

    public void setRM_CREATED_ON(String RM_CREATED_ON) {
        this.RM_CREATED_ON = RM_CREATED_ON;
    }
}
