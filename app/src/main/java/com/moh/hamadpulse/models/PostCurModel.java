package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostCurModel {
    @SerializedName("TUMORS_ORDER_NO")
    @Expose
    private String tumorsOrderNo;
    @SerializedName("PATIENT_CD")
    @Expose
    private String patientCd;
    @SerializedName("REPORT_DATE")
    @Expose
    private String reportDate;
    @SerializedName("P_ID")
    @Expose
    private String pId;
    @SerializedName("DRUG")
    @Expose
    private String drug;
    @SerializedName("DOSING")
    @Expose
    private String dosing;
    @SerializedName("FINAL_DOSE")
    @Expose
    private String finalDose;
    @SerializedName("ROUTE")
    @Expose
    private String route;
    @SerializedName("FREQUENCY")
    @Expose
    private String frequency;
    @SerializedName("SPECIAL_INSTRUCTIONS")
    @Expose
    private String specialInstructions;
    @SerializedName("DRUG_NO")
    @Expose
    private String drugNo;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("DOSE_MOD")
    @Expose
    private String doseMod;
    @SerializedName("VISITED_ID")
    @Expose
    private String visitedId;
    @SerializedName("ITEM_CODE")
    @Expose
    private String itemCode;
    @SerializedName("DOSING_UNIT")
    @Expose
    private String dosingUnit;
    @SerializedName("PRE_POST")
    @Expose
    private String prePost;
    @SerializedName("STATUS")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTumorsOrderNo() {
        return tumorsOrderNo;
    }

    public void setTumorsOrderNo(String tumorsOrderNo) {
        this.tumorsOrderNo = tumorsOrderNo;
    }

    public String getPatientCd() {
        return patientCd;
    }

    public void setPatientCd(String patientCd) {
        this.patientCd = patientCd;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDosing() {
        return dosing;
    }

    public void setDosing(String dosing) {
        this.dosing = dosing;
    }

    public String getFinalDose() {
        return finalDose;
    }

    public void setFinalDose(String finalDose) {
        this.finalDose = finalDose;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(String drugNo) {
        this.drugNo = drugNo;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getDoseMod() {
        return doseMod;
    }

    public void setDoseMod(String doseMod) {
        this.doseMod = doseMod;
    }

    public String getVisitedId() {
        return visitedId;
    }

    public void setVisitedId(String visitedId) {
        this.visitedId = visitedId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDosingUnit() {
        return dosingUnit;
    }

    public void setDosingUnit(String dosingUnit) {
        this.dosingUnit = dosingUnit;
    }

    public String getPrePost() {
        return prePost;
    }

    public void setPrePost(String prePost) {
        this.prePost = prePost;
    }
}
