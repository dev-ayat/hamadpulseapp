package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class InOutDetailsCurModel {

    @SerializedName("INP_IN_OUT_D_CD")
    private String inpInOutDCd;
    @SerializedName("IN_OUT_TAKE_MASTER")
    private String inOutTakeMaster;
    @SerializedName("INP_IN_OUT_IV_FLUID")
    private String inpInOutIvFluid;
    @SerializedName("INP_IN_OUT_ORAL")
    private String inpInOutOral;
    @SerializedName("INP_IN_OUT_DRAINS")
    private String inpInOutDrains;
    @SerializedName("INP_IN_OUT_NGT")
    private String inpInOutNgt;
    @SerializedName("INP_IN_OUT_VOMTTING")
    private String inpInOutVomtting;
    @SerializedName("INP_IN_OUT_URINE")
    private String inpInOutUrine;
    @SerializedName("INP_IN_OUT_CH_R")
    private String inpInOutChR;
    @SerializedName("INP_IN_OUT_CH_L")
    private String inpInOutChL;
    @SerializedName("INP_IN_OUT_CREATED_BY")
    private String inpInOutCreatedBy;
    @SerializedName("USER_FULL_NAME")
    private String userFullName;
    @SerializedName("INP_IN_OUT_DETAILS_CREATED_ON")
    private String inpInOutCreatedOn;
    @SerializedName("ORDER_SOURCE")
    private String orderSource;
    @SerializedName("NOTE")
    private String NOTE;
    @SerializedName("HOS_NO")
    private String hosNo;

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getInpInOutDCd() {
        return inpInOutDCd;
    }

    public void setInpInOutDCd(String inpInOutDCd) {
        this.inpInOutDCd = inpInOutDCd;
    }
    public String getInOutTakeMaster() {
        return inOutTakeMaster;
    }
    public void setInOutTakeMaster(String inOutTakeMaster) {
        this.inOutTakeMaster = inOutTakeMaster;
    }
    public String getInpInOutIvFluid() {
        return inpInOutIvFluid;
    }
    public void setInpInOutIvFluid(String inpInOutIvFluid) {
        this.inpInOutIvFluid = inpInOutIvFluid;
    }

    public String getInpInOutOral() {
        return inpInOutOral;
    }

    public void setInpInOutOral(String inpInOutOral) {
        this.inpInOutOral = inpInOutOral;
    }

    public String getInpInOutDrains() {
        return inpInOutDrains;
    }

    public void setInpInOutDrains(String inpInOutDrains) {
        this.inpInOutDrains = inpInOutDrains;
    }

    public String getInpInOutNgt() {
        return inpInOutNgt;
    }

    public void setInpInOutNgt(String inpInOutNgt) {
        this.inpInOutNgt = inpInOutNgt;
    }

    public String getInpInOutVomtting() {
        return inpInOutVomtting;
    }

    public void setInpInOutVomtting(String inpInOutVomtting) {
        this.inpInOutVomtting = inpInOutVomtting;
    }

    public String getInpInOutUrine() {
        return inpInOutUrine;
    }

    public void setInpInOutUrine(String inpInOutUrine) {
        this.inpInOutUrine = inpInOutUrine;
    }

    public String getInpInOutChR() {
        return inpInOutChR;
    }

    public void setInpInOutChR(String inpInOutChR) {
        this.inpInOutChR = inpInOutChR;
    }

    public String getInpInOutChL() {
        return inpInOutChL;
    }

    public void setInpInOutChL(String inpInOutChL) {
        this.inpInOutChL = inpInOutChL;
    }

    public String getInpInOutCreatedBy() {
        return inpInOutCreatedBy;
    }

    public void setInpInOutCreatedBy(String inpInOutCreatedBy) {
        this.inpInOutCreatedBy = inpInOutCreatedBy;
    }

    public String getInpInOutCreatedOn() {
        return inpInOutCreatedOn;
    }

    public void setInpInOutCreatedOn(String inpInOutCreatedOn) {
        this.inpInOutCreatedOn = inpInOutCreatedOn;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}

