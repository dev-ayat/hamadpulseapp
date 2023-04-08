package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NrsCmplnModel {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("TUMORS_ORDER_NO")
    @Expose
    private String tumorsOrderNo;
    @SerializedName("NURSING_NAME")
    @Expose
    private String nursingName;
    @SerializedName("CMPLN_OTHER")
    @Expose
    private Object cmplnOther;
    @SerializedName("COMPLICATIONS")
    @Expose
    private String complications;
    @SerializedName("NOTES")
    @Expose
    private String notes;
    @SerializedName("TIME_CMPLCT")
    @Expose
    private Object timeCmplct;
    @SerializedName("DRUG_NO")
    @Expose
    private Object drugNo;
    @SerializedName("CREATED_ON")
    @Expose
    private String createdOn;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("VISIT_ID")
    @Expose
    private String visitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTumorsOrderNo() {
        return tumorsOrderNo;
    }

    public void setTumorsOrderNo(String tumorsOrderNo) {
        this.tumorsOrderNo = tumorsOrderNo;
    }

    public String getNursingName() {
        return nursingName;
    }

    public void setNursingName(String nursingName) {
        this.nursingName = nursingName;
    }

    public Object getCmplnOther() {
        return cmplnOther;
    }

    public void setCmplnOther(Object cmplnOther) {
        this.cmplnOther = cmplnOther;
    }

    public String getComplications() {
        return complications;
    }

    public void setComplications(String complications) {
        this.complications = complications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Object getTimeCmplct() {
        return timeCmplct;
    }

    public void setTimeCmplct(Object timeCmplct) {
        this.timeCmplct = timeCmplct;
    }

    public Object getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(Object drugNo) {
        this.drugNo = drugNo;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

}
