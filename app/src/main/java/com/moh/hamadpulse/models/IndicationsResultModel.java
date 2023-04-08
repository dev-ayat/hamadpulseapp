package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndicationsResultModel {
    @SerializedName("IND_CODE")
    @Expose
    private String indCode;
    @SerializedName("IND_NAME_AR")
    @Expose
    private String indNameAr;
    @SerializedName("IND_NAME_EN")
    @Expose
    private String indNameEn;

    public String getIndCode() {
        return indCode;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public String getIndNameAr() {
        return indNameAr;
    }

    public void setIndNameAr(String indNameAr) {
        this.indNameAr = indNameAr;
    }

    public String getIndNameEn() {
        return indNameEn;
    }

    public void setIndNameEn(String indNameEn) {
        this.indNameEn = indNameEn;
    }
}
