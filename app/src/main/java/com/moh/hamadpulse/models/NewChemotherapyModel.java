package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class NewChemotherapyModel {
    @SerializedName("ID")
    @Expose
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewChemotherapyModel)) return false;
        NewChemotherapyModel that = (NewChemotherapyModel) o;
        return Objects.equals(id, that.id) && Objects.equals(itemCode, that.itemCode) && Objects.equals(drug, that.drug) && Objects.equals(dosing, that.dosing) && Objects.equals(finalDose, that.finalDose) && Objects.equals(route, that.route) && Objects.equals(frequency, that.frequency) && Objects.equals(specialInstructions, that.specialInstructions) && Objects.equals(drugNo, that.drugNo);
    }


}
