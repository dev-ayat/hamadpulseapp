package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NrsPrtclModel {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("NP_SERIAL")
    @Expose
    private String npSerial;
    @SerializedName("MRP_PATREC_CODE")
    @Expose
    private String mrpPatrecCode;
    @SerializedName("TUMORS_ORDER_NO")
    @Expose
    private String tumorsOrderNo;
    @SerializedName("NP_ACTION_ID")
    @Expose
    private String npActionId;
    @SerializedName("DRUG_NO")
    @Expose
    private String drugNo;
    @SerializedName("NOTES_PROGRESS")
    @Expose
    private String notesProgress;
    @SerializedName("CREATED_PROGRESS_ON")
    @Expose
    private String createdProgressOn;
    @SerializedName("NURSING_CODE")
    @Expose
    private String nursingCode;
    @SerializedName("STATUS")
    @Expose
    private String status;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("CREATED_DONE_ON")
    @Expose
    private Object createdDoneOn;
    @SerializedName("NOTES_DONE")
    @Expose
    private Object notesDone;
    @SerializedName("P_ID")
    @Expose
    private String pId;
    @SerializedName("VISIT_ID")
    @Expose
    private String visitId;
    @SerializedName("NURSING_NAME")
    @Expose
    private String nursingName;
    @SerializedName("STATUS_ACTION")
    @Expose
    private String statusAction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNpSerial() {
        return npSerial;
    }

    public void setNpSerial(String npSerial) {
        this.npSerial = npSerial;
    }

    public String getMrpPatrecCode() {
        return mrpPatrecCode;
    }

    public void setMrpPatrecCode(String mrpPatrecCode) {
        this.mrpPatrecCode = mrpPatrecCode;
    }

    public String getTumorsOrderNo() {
        return tumorsOrderNo;
    }

    public void setTumorsOrderNo(String tumorsOrderNo) {
        this.tumorsOrderNo = tumorsOrderNo;
    }

    public String getNpActionId() {
        return npActionId;
    }

    public void setNpActionId(String npActionId) {
        this.npActionId = npActionId;
    }

    public String getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(String drugNo) {
        this.drugNo = drugNo;
    }

    public String getNotesProgress() {
        return notesProgress;
    }

    public void setNotesProgress(String notesProgress) {
        this.notesProgress = notesProgress;
    }

    public String getCreatedProgressOn() {
        return createdProgressOn;
    }

    public void setCreatedProgressOn(String createdProgressOn) {
        this.createdProgressOn = createdProgressOn;
    }

    public String getNursingCode() {
        return nursingCode;
    }

    public void setNursingCode(String nursingCode) {
        this.nursingCode = nursingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public Object getCreatedDoneOn() {
        return createdDoneOn;
    }

    public void setCreatedDoneOn(Object createdDoneOn) {
        this.createdDoneOn = createdDoneOn;
    }

    public Object getNotesDone() {
        return notesDone;
    }

    public void setNotesDone(Object notesDone) {
        this.notesDone = notesDone;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getNursingName() {
        return nursingName;
    }

    public void setNursingName(String nursingName) {
        this.nursingName = nursingName;
    }

    public String getStatusAction() {
        return statusAction;
    }

    public void setStatusAction(String statusAction) {
        this.statusAction = statusAction;
    }
}
