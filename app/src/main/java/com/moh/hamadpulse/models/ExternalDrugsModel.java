package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalDrugsModel {
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
    @SerializedName("UNIT_CODE")
    @Expose
    private String unitCode;
    @SerializedName("UNIT_NAME")
    @Expose
    private String unitName;
    @SerializedName("AVAILABLE_BAL")
    @Expose
    private String availableBal;
    @SerializedName("REAL_BAL")
    @Expose
    private String realBal;
    @SerializedName("ITEM_PRICE")
    @Expose
    private String itemPrice;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("SPICAL")
    @Expose
    private String spical;
    @SerializedName("DISCHARGED_FROM_NURSING")
    @Expose
    private String dischargedFromNursing;
    @SerializedName("HOS_PHARM_CODE")
    @Expose
    private String hosPharmCode;
    @SerializedName("HOS_CODE")
    @Expose
    private String hosCode;
    @SerializedName("HOS_PHARM_NAME")
    @Expose
    private String hosPharmName;
    @SerializedName("ID_NO")
    @Expose
    private String idNo;
    @SerializedName("FNAME")
    @Expose
    private String fname;
    @SerializedName("SNAME")
    @Expose
    private String sname;
    @SerializedName("LNAME")
    @Expose
    private String lname;
    @SerializedName("PHARM_ACTIVE")
    @Expose
    private String pharmActive;
    @SerializedName("HOS_PHARM_TYPE")
    @Expose
    private String hosPharmType;

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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAvailableBal() {
        return availableBal;
    }

    public void setAvailableBal(String availableBal) {
        this.availableBal = availableBal;
    }

    public String getRealBal() {
        return realBal;
    }

    public void setRealBal(String realBal) {
        this.realBal = realBal;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getSpical() {
        return spical;
    }

    public void setSpical(String spical) {
        this.spical = spical;
    }

    public String getDischargedFromNursing() {
        return dischargedFromNursing;
    }

    public void setDischargedFromNursing(String dischargedFromNursing) {
        this.dischargedFromNursing = dischargedFromNursing;
    }

    public String getHosPharmCode() {
        return hosPharmCode;
    }

    public void setHosPharmCode(String hosPharmCode) {
        this.hosPharmCode = hosPharmCode;
    }

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getHosPharmName() {
        return hosPharmName;
    }

    public void setHosPharmName(String hosPharmName) {
        this.hosPharmName = hosPharmName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPharmActive() {
        return pharmActive;
    }

    public void setPharmActive(String pharmActive) {
        this.pharmActive = pharmActive;
    }

    public String getHosPharmType() {
        return hosPharmType;
    }

    public void setHosPharmType(String hosPharmType) {
        this.hosPharmType = hosPharmType;
    }

}
