package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNurseMedExecution {

    @SerializedName("INMED_CODE")
    @Expose
    private String inmedCode;
    @SerializedName("INMED_TREAT_CD")
    @Expose
    private String inmedTreatCd;
    @SerializedName("INMED_INPATIENT_CD")
    @Expose
    private String inmedInpatientCd;
    @SerializedName("INMED_CREATED_ON")
    @Expose
    private String inmedCreatedOn;
    @SerializedName("INMED_CREATEDBY_CD")
    @Expose
    private String inmedCreatedbyCd;
    @SerializedName("INMED_DOSE")
    @Expose
    private String inmedDose;
    @SerializedName("INMED_NURSE_CD")
    @Expose
    private String inmedNurseCd;
    @SerializedName("INMED_GIVEN_DATE")
    @Expose
    private String inmedGivenDate;
    @SerializedName("INMED_IS_GIVEN")
    @Expose
    private String inmedIsGiven;
    @SerializedName("USER_FULL_NAME")
    @Expose
    private String userFullName;


    @SerializedName("INMED_NOTE")
    @Expose
    private String INMEDNOTE;


    public String getInmedCode() {
        return inmedCode;
    }

    public void setInmedCode(String inmedCode) {
        this.inmedCode = inmedCode;
    }

    public String getInmedTreatCd() {
        return inmedTreatCd;
    }

    public void setInmedTreatCd(String inmedTreatCd) {
        this.inmedTreatCd = inmedTreatCd;
    }

    public String getInmedInpatientCd() {
        return inmedInpatientCd;
    }

    public void setInmedInpatientCd(String inmedInpatientCd) {
        this.inmedInpatientCd = inmedInpatientCd;
    }

    public String getInmedCreatedOn() {
        return inmedCreatedOn;
    }

    public void setInmedCreatedOn(String inmedCreatedOn) {
        this.inmedCreatedOn = inmedCreatedOn;
    }

    public String getInmedCreatedbyCd() {
        return inmedCreatedbyCd;
    }

    public void setInmedCreatedbyCd(String inmedCreatedbyCd) {
        this.inmedCreatedbyCd = inmedCreatedbyCd;
    }

    public String getInmedDose() {
        return inmedDose;
    }

    public void setInmedDose(String inmedDose) {
        this.inmedDose = inmedDose;
    }

    public String getInmedNurseCd() {
        return inmedNurseCd;
    }

    public void setInmedNurseCd(String inmedNurseCd) {
        this.inmedNurseCd = inmedNurseCd;
    }

    public String getInmedGivenDate() {
        return inmedGivenDate;
    }

    public void setInmedGivenDate(String inmedGivenDate) {
        this.inmedGivenDate = inmedGivenDate;
    }

    public String getInmedIsGiven() {
        return inmedIsGiven;
    }

    public void setInmedIsGiven(String inmedIsGiven) {
        this.inmedIsGiven = inmedIsGiven;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getINMEDNOTE() {
        return INMEDNOTE;
    }

    public void setINMEDNOTE(String INMEDNOTE) {
        this.INMEDNOTE = INMEDNOTE;
    }
}