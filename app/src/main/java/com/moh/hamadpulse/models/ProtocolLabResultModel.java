package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProtocolLabResultModel {
    @SerializedName("191211")
    @Expose
    private String _191211;
    @SerializedName("CBC_RESULT_DATE")
    @Expose
    private String cbcResultDate;
    @SerializedName("WBC_VAL")
    @Expose
    private String wbcVal;
    @SerializedName("GRAN_VAL")
    @Expose
    private String granVal;
    @SerializedName("PLT_VAL")
    @Expose
    private String pltVal;
    @SerializedName("HB_VAL")
    @Expose
    private String hbVal;
    @SerializedName("HB_RESULT_DATE")
    @Expose
    private String hbResultDate;
    @SerializedName("SERUM_CREATININE_VAL")
    @Expose
    private String serumCreatinineVal;
    @SerializedName("SERUM_RESULT_DATE")
    @Expose
    private String serumResultDate;
    @SerializedName("BILIRUBIN_VAL")
    @Expose
    private String bilirubinVal;
    @SerializedName("ALP_VAL")
    @Expose
    private String alpVal;
    @SerializedName("ALT_VAL")
    @Expose
    private String altVal;
    @SerializedName("AST_VAL")
    @Expose
    private String astVal;
    @SerializedName("LFT_DATE")
    @Expose
    private String lftDate;

    public String get_191211() {
        return _191211;
    }

    public void set_191211(String _191211) {
        this._191211 = _191211;
    }

    public String getCbcResultDate() {
        return cbcResultDate;
    }

    public void setCbcResultDate(String cbcResultDate) {
        this.cbcResultDate = cbcResultDate;
    }

    public String getWbcVal() {
        return wbcVal;
    }

    public void setWbcVal(String wbcVal) {
        this.wbcVal = wbcVal;
    }

    public String getGranVal() {
        return granVal;
    }

    public void setGranVal(String granVal) {
        this.granVal = granVal;
    }

    public String getPltVal() {
        return pltVal;
    }

    public void setPltVal(String pltVal) {
        this.pltVal = pltVal;
    }

    public String getHbVal() {
        return hbVal;
    }

    public void setHbVal(String hbVal) {
        this.hbVal = hbVal;
    }

    public String getHbResultDate() {
        return hbResultDate;
    }

    public void setHbResultDate(String hbResultDate) {
        this.hbResultDate = hbResultDate;
    }

    public String getSerumCreatinineVal() {
        return serumCreatinineVal;
    }

    public void setSerumCreatinineVal(String serumCreatinineVal) {
        this.serumCreatinineVal = serumCreatinineVal;
    }

    public String getSerumResultDate() {
        return serumResultDate;
    }

    public void setSerumResultDate(String serumResultDate) {
        this.serumResultDate = serumResultDate;
    }

    public String getBilirubinVal() {
        return bilirubinVal;
    }

    public void setBilirubinVal(String bilirubinVal) {
        this.bilirubinVal = bilirubinVal;
    }

    public String getAlpVal() {
        return alpVal;
    }

    public void setAlpVal(String alpVal) {
        this.alpVal = alpVal;
    }

    public String getAltVal() {
        return altVal;
    }

    public void setAltVal(String altVal) {
        this.altVal = altVal;
    }

    public String getAstVal() {
        return astVal;
    }

    public void setAstVal(String astVal) {
        this.astVal = astVal;
    }

    public String getLftDate() {
        return lftDate;
    }

    public void setLftDate(String lftDate) {
        this.lftDate = lftDate;
    }
}
