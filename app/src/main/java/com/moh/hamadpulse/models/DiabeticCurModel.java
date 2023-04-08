
package com.moh.hamadpulse.models;


import com.google.gson.annotations.SerializedName;


public class DiabeticCurModel {

    @SerializedName("INP_DIABETIC_CDM")
    private String inpDiabeticCd;
    @SerializedName("INP_DIABETIC_ADM_CD")
    private String inpDiabeticAdmCd;
    @SerializedName("INP_DIABETIC_PATREC_CD")
    private String inpDiabeticPatrecCd;
    @SerializedName("INP_DIABETIC_BS_DL")
    private String inpDiabeticBsDl;
    @SerializedName("INP_DIABETIC_INSULIN_TYPE")
    private String inpDiabeticInsulinType;
    @SerializedName("INP_DIABETIC_INSULIN_TYPE_NAME")
    private String inpDiabeticInsulinTypeName;
    @SerializedName("INP_DIABETIC_INSULIN_DOSE")
    private String inpDiabeticInsulinDose;
    @SerializedName("INP_DIABETIC_CREATED_BY")
    private String inpDiabeticCreatedBy;
    @SerializedName("INP_DIABETIC_CREATED_ON")
    private String inpDiabeticCreatedOn;
    @SerializedName("USER_FULL_NAME")
    private String userFullName;
    @SerializedName("INP_DIABETIC_NOTE")
    private String inpDiabeticNote;
    @SerializedName("USER_ID")
    private String USER_ID;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getInpDiabeticNote() {
        return inpDiabeticNote;
    }
    public void setInpDiabeticNote(String inpDiabeticNote) {
        this.inpDiabeticNote = inpDiabeticNote;
    }
    public String getInpDiabeticCd() {
        return inpDiabeticCd;
    }

    public void setInpDiabeticCd(String inpDiabeticCd) {
        this.inpDiabeticCd = inpDiabeticCd;
    }

    public String getInpDiabeticAdmCd() {
        return inpDiabeticAdmCd;
    }

    public void setInpDiabeticAdmCd(String inpDiabeticAdmCd) {
        this.inpDiabeticAdmCd = inpDiabeticAdmCd;
    }

    public String getInpDiabeticPatrecCd() {
        return inpDiabeticPatrecCd;
    }

    public void setInpDiabeticPatrecCd(String inpDiabeticPatrecCd) {
        this.inpDiabeticPatrecCd = inpDiabeticPatrecCd;
    }

    public String getInpDiabeticBsDl() {
        return inpDiabeticBsDl;
    }

    public void setInpDiabeticBsDl(String inpDiabeticBsDl) {
        this.inpDiabeticBsDl = inpDiabeticBsDl;
    }

    public String getInpDiabeticInsulinType() {
        return inpDiabeticInsulinType;
    }

    public void setInpDiabeticInsulinType(String inpDiabeticInsulinType) {
        this.inpDiabeticInsulinType = inpDiabeticInsulinType;
    }
    public String getInpDiabeticInsulinTypeName() {
        return inpDiabeticInsulinTypeName;
    }

    public void setInpDiabeticInsulinTypeName(String inpDiabeticInsulinTypeName) {
        this.inpDiabeticInsulinTypeName = inpDiabeticInsulinTypeName;
    }
    public String getInpDiabeticInsulinDose() {
        return inpDiabeticInsulinDose;
    }

    public void setInpDiabeticInsulinDose(String inpDiabeticInsulinDose) {
        this.inpDiabeticInsulinDose = inpDiabeticInsulinDose;
    }

    public String getInpDiabeticCreatedBy() {
        return inpDiabeticCreatedBy;
    }

    public void setInpDiabeticCreatedBy(String inpDiabeticCreatedBy) {
        this.inpDiabeticCreatedBy = inpDiabeticCreatedBy;
    }

    public String getInpDiabeticCreatedOn() {
        return inpDiabeticCreatedOn;
    }

    public void setInpDiabeticCreatedOn(String inpDiabeticCreatedOn) {
        this.inpDiabeticCreatedOn = inpDiabeticCreatedOn;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

}
