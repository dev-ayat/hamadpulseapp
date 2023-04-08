package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PharmMedModel {
    @SerializedName("MED_M_CODE")
    @Expose
    private String medMCode;
    @SerializedName("PHARMACY_NO")
    @Expose
    private String pharmacyNo;
    @SerializedName("ITEM_CODE")
    @Expose
    private String itemCode;
    @SerializedName("ITEM_NAME")
    @Expose
    private String itemName;
    private boolean is_fav;

    public boolean isIs_fav() {
        return is_fav;
    }

    public void setIs_fav(boolean is_fav) {
        this.is_fav = is_fav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmMedModel that = (PharmMedModel) o;
        return Objects.equals(medMCode, that.medMCode);
    }


    public String getMedMCode() {
        return medMCode;
    }

    public void setMedMCode(String medMCode) {
        this.medMCode = medMCode;
    }

    public String getPharmacyNo() {
        return pharmacyNo;
    }

    public void setPharmacyNo(String pharmacyNo) {
        this.pharmacyNo = pharmacyNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
