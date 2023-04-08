package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RadDetailModel {
    @SerializedName("ORDER_CODE_CD")
    @Expose
    private String orderCodeCd;
    @SerializedName("ORDER_YEAR_CD")
    @Expose
    private String orderYearCd;
    @SerializedName("ORDERD_RESULT")
    @Expose
    private String orderdResult;
    @SerializedName("ORGAN_NAME_AR")
    @Expose
    private String organNameAr;
    @SerializedName("ORGAN_D_NAME_AR")
    @Expose
    private String organDNameAr;
    @SerializedName("ORGAN_D_CODE")
    @Expose
    private String organDCode;
    @SerializedName("ORGAN_D_TECHNIQUE")
    @Expose
    private String organDTechnique;

    public String getOrderCodeCd() {
        return orderCodeCd;
    }

    public void setOrderCodeCd(String orderCodeCd) {
        this.orderCodeCd = orderCodeCd;
    }

    public String getOrderYearCd() {
        return orderYearCd;
    }

    public void setOrderYearCd(String orderYearCd) {
        this.orderYearCd = orderYearCd;
    }

    public String getOrderdResult() {
        return orderdResult;
    }

    public void setOrderdResult(String orderdResult) {
        this.orderdResult = orderdResult;
    }

    public String getOrganNameAr() {
        return organNameAr;
    }

    public void setOrganNameAr(String organNameAr) {
        this.organNameAr = organNameAr;
    }

    public String getOrganDNameAr() {
        return organDNameAr;
    }

    public void setOrganDNameAr(String organDNameAr) {
        this.organDNameAr = organDNameAr;
    }

    public String getOrganDCode() {
        return organDCode;
    }

    public void setOrganDCode(String organDCode) {
        this.organDCode = organDCode;
    }

    public String getOrganDTechnique() {
        return organDTechnique;
    }

    public void setOrganDTechnique(String organDTechnique) {
        this.organDTechnique = organDTechnique;
    }
}
