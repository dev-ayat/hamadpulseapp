package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreDrugsModel {
    @SerializedName("PROTOCOL_ID")
    @Expose
    private String protocolId;
    @SerializedName("ITEM_CODE")
    @Expose
    private String itemCode;
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
    @SerializedName("PRE_POST")
    @Expose
    private String prePost;
    @SerializedName("DOSING_CHILD")
    @Expose
    private String dosingChild;
    private String dosingUnit;
    private String type = "0";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDosingUnit() {
        return dosingUnit;
    }

    public void setDosingUnit(String dosingUnit) {
        this.dosingUnit = dosingUnit;
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getPrePost() {
        return prePost;
    }

    public void setPrePost(String prePost) {
        this.prePost = prePost;
    }

    public String getDosingChild() {
        return dosingChild;
    }

    public void setDosingChild(String dosingChild) {
        this.dosingChild = dosingChild;
    }
}
