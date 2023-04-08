package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VentilationModeModel {
    @SerializedName("INP_NP_ID")
    @Expose
    private String inpNpId;
    @SerializedName("INP_NP_ADM_CD")
    @Expose
    private String inpNpAdmCd;
    @SerializedName("INP_NP_PATREIC_CD")
    @Expose
    private String inpNpPatreicCd;
    @SerializedName("INP_NP_O2_MODE")
    @Expose
    private String inpNpO2Mode;
    @SerializedName("INP_NP_NOTE")
    @Expose
    private String inpNpNote;
    @SerializedName("INP_NP_CREATED_ON")
    @Expose
    private String inpNpCreatedOn;
    @SerializedName("INP_NP_CREATED_BY")
    @Expose
    private String inpNpCreatedBy;
    @SerializedName("DELIVERY_NAME_EN")
    @Expose
    private String deliveryNameEn;
    @SerializedName("USER_FULL_NAME")
    @Expose
    private String userFullName;

    public String getInpNpId() {
        return inpNpId;
    }

    public void setInpNpId(String inpNpId) {
        this.inpNpId = inpNpId;
    }

    public String getInpNpAdmCd() {
        return inpNpAdmCd;
    }

    public void setInpNpAdmCd(String inpNpAdmCd) {
        this.inpNpAdmCd = inpNpAdmCd;
    }

    public String getInpNpPatreicCd() {
        return inpNpPatreicCd;
    }

    public void setInpNpPatreicCd(String inpNpPatreicCd) {
        this.inpNpPatreicCd = inpNpPatreicCd;
    }

    public String getInpNpO2Mode() {
        return inpNpO2Mode;
    }

    public void setInpNpO2Mode(String inpNpO2Mode) {
        this.inpNpO2Mode = inpNpO2Mode;
    }

    public String getInpNpNote() {
        return inpNpNote;
    }

    public void setInpNpNote(String inpNpNote) {
        this.inpNpNote = inpNpNote;
    }

    public String getInpNpCreatedOn() {
        return inpNpCreatedOn;
    }

    public void setInpNpCreatedOn(String inpNpCreatedOn) {
        this.inpNpCreatedOn = inpNpCreatedOn;
    }

    public String getInpNpCreatedBy() {
        return inpNpCreatedBy;
    }

    public void setInpNpCreatedBy(String inpNpCreatedBy) {
        this.inpNpCreatedBy = inpNpCreatedBy;
    }

    public String getDeliveryNameEn() {
        return deliveryNameEn;
    }

    public void setDeliveryNameEn(String deliveryNameEn) {
        this.deliveryNameEn = deliveryNameEn;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }


}
