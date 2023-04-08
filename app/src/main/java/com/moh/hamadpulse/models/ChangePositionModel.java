package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChangePositionModel implements Serializable {

    @SerializedName("INP_CH_CD")
    @Expose
    private String inpChCd;
    @SerializedName("INP_CH_ADM_CD")
    @Expose
    private String inpChAdmCd;
    @SerializedName("INP_CH_PATRIC_CD")
    @Expose
    private String inpChPatricCd;
    @SerializedName("INP_CH_RT_8AM")
    @Expose
    private String inpChRt8am;
    @SerializedName("INP_CH_LT_10AM")
    @Expose
    private String inpChLt10am;
    @SerializedName("INP_CH_BACK_12MD")
    @Expose
    private String inpChBack12md;
    @SerializedName("INP_CH_RT_2PM")
    @Expose
    private String inpChRt2pm;
    @SerializedName("INP_CH_LT_4PM")
    @Expose
    private String inpChLt4pm;
    @SerializedName("INP_CH_BACK_6PM")
    @Expose
    private String inpChBack6pm;
    @SerializedName("INP_CH_RT_8PM")
    @Expose
    private String inpChRt8pm;
    @SerializedName("INP_CH_LT_10PM")
    @Expose
    private String inpChLt10pm;
    @SerializedName("INP_CH_BACK_12MN")
    @Expose
    private String inpChBack12mn;
    @SerializedName("INP_CH_RT_2AM")
    @Expose
    private String inpChRt2am;
    @SerializedName("INP_CH_LT_4AM")
    @Expose
    private String inpChLt4am;
    @SerializedName("INP_CH_BACK_6AM")
    @Expose
    private String inpChBack6am;
    @SerializedName("INP_CH_CRATED_BY")
    @Expose
    private String inpChCratedBy;
    @SerializedName("INP_CH_CREATED_ON")
    @Expose
    private String inpChCreatedOn;
    @SerializedName("ORDER_SOURCE")
    @Expose
    private String orderSource;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("INP_CH_AIR_MATRIX")
    @Expose
    private String inpChAirMatrix;
    @SerializedName("INP_CH_BED_SOURCE")
    @Expose
    private String inpChBedSource;
    @SerializedName("INP_CH_BED_SOURCE_GRADE")
    @Expose
    private String inpChBedSourceGrade;
    private ArrayList<Boolean> all_position_list;
    public ChangePositionModel() {
    }

    public ArrayList<Boolean> getAll_position_list() {
        return all_position_list;
    }

    public String getInpChCd() {
        return inpChCd;
    }

    public void setInpChCd(String inpChCd) {
        this.inpChCd = inpChCd;
    }

    public String getInpChAdmCd() {
        return inpChAdmCd;
    }

    public void setInpChAdmCd(String inpChAdmCd) {
        this.inpChAdmCd = inpChAdmCd;
    }

    public String getInpChPatricCd() {
        return inpChPatricCd;
    }

    public void setInpChPatricCd(String inpChPatricCd) {
        this.inpChPatricCd = inpChPatricCd;
    }

    public Boolean getInpChRt8am() {
        return inpChRt8am.equals("1") ? true : false;
    }

    public void setInpChRt8am(String inpChRt8am) {
        this.inpChRt8am = inpChRt8am;
    }

    public Boolean getInpChLt10am() {
        return inpChLt10am.equals("1") ? true : false;
    }

    public void setInpChLt10am(String inpChLt10am) {
        this.inpChLt10am = inpChLt10am;
    }

    public Boolean getInpChBack12md() {
        return inpChBack12md.equals("1") ? true : false;
    }

    public void setInpChBack12md(String inpChBack12md) {
        this.inpChBack12md = inpChBack12md;
    }

    public Boolean getInpChRt2pm() {
        return inpChRt2pm.equals("1") ? true : false;
    }

    public void setInpChRt2pm(String inpChRt2pm) {
        this.inpChRt2pm = inpChRt2pm;
    }

    public Boolean getInpChLt4pm() {
        return inpChLt4pm.equals("1") ? true : false;
    }

    public void setInpChLt4pm(String inpChLt4pm) {
        this.inpChLt4pm = inpChLt4pm;
    }

    public Boolean getInpChBack6pm() {
        return inpChBack6pm.equals("1") ? true : false;
    }

    public void setInpChBack6pm(String inpChBack6pm) {
        this.inpChBack6pm = inpChBack6pm;
    }

    public Boolean getInpChRt8pm() {
        return inpChRt8pm.equals("1") ? true : false;
    }

    public void setInpChRt8pm(String inpChRt8pm) {
        this.inpChRt8pm = inpChRt8pm;
    }

    public Boolean getInpChLt10pm() {
        return inpChLt10pm.equals("1") ? true : false;
    }

    public void setInpChLt10pm(String inpChLt10pm) {
        this.inpChLt10pm = inpChLt10pm;
    }

    public Boolean getInpChBack12mn() {
        return inpChBack12mn.equals("1") ? true : false;
    }

    public void setInpChBack12mn(String inpChBack12mn) {
        this.inpChBack12mn = inpChBack12mn;
    }

    public Boolean getInpChRt2am() {
        return inpChRt2am.equals("1") ? true : false;
    }

    public void setInpChRt2am(String inpChRt2am) {
        this.inpChRt2am = inpChRt2am;
    }

    public Boolean getInpChLt4am() {
        return inpChLt4am.equals("1") ? true : false;
    }

    public void setInpChLt4am(String inpChLt4am) {
        this.inpChLt4am = inpChLt4am;
    }

    public Boolean getInpChBack6am() {
        return inpChBack6am.equals("1") ? true : false;
    }

    public void setInpChBack6am(String inpChBack6am) {
        this.inpChBack6am = inpChBack6am;
    }

    public String getInpChCratedBy() {
        return inpChCratedBy;
    }

    public void setInpChCratedBy(String inpChCratedBy) {
        this.inpChCratedBy = inpChCratedBy;
    }

    public String getInpChCreatedOn() {
        return inpChCreatedOn;
    }

    public void setInpChCreatedOn(String inpChCreatedOn) {
        this.inpChCreatedOn = inpChCreatedOn;
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

    public String getInpChAirMatrix() {
        return inpChAirMatrix;
    }

    public void setInpChAirMatrix(String inpChAirMatrix) {
        this.inpChAirMatrix = inpChAirMatrix;
    }

    public String getInpChBedSource() {
        return inpChBedSource;
    }

    public void setInpChBedSource(String inpChBedSource) {
        this.inpChBedSource = inpChBedSource;
    }

    public String getInpChBedSourceGrade() {
        return inpChBedSourceGrade;
    }

    public void setInpChBedSourceGrade(String inpChBedSourceGrade) {
        this.inpChBedSourceGrade = inpChBedSourceGrade;
    }

    public void setAll_position_list(ArrayList<Boolean> all_position_list) {
        this.all_position_list = all_position_list;
    }
}

