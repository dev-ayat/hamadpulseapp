
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllargiesmodel {

    @SerializedName("ALLERGIES_PATIENTS_CD")
    @Expose
    private String allergiesPatientsCd;
    @SerializedName("PATREC_CD")
    @Expose
    private String patrecCd;
    @SerializedName("ALLERGY_CD")
    @Expose
    private String allergyCd;
    @SerializedName("ALLERGY_NAME")
    @Expose
    private String allergyName;
    @SerializedName("DOCTOR_ID")
    @Expose
    private Object doctorId;
    @SerializedName("CREATED_ON")
    @Expose
    private String createdOn;
    @SerializedName("ALLERGY_TYPE")
    @Expose
    private String allergyType;
    @SerializedName("ALLERGY_TYPE_NAME")
    @Expose
    private String allergyTypeName;
    @SerializedName("REMARK")
    @Expose
    private String remark;
    @SerializedName("ALL_DATE")
    @Expose
    private String allDate;
    @SerializedName("REACTION")
    @Expose
    private String reaction;
    @SerializedName("REACTION_NAME")
    @Expose
    private String reactionName;
    @SerializedName("SEVERITY")
    @Expose
    private String severity;
    @SerializedName("SEVERITY_NAME")
    @Expose
    private String severityName;
    @SerializedName("NOTE")
    @Expose
    private String note;

    public String getAllergiesPatientsCd() {
        return allergiesPatientsCd;
    }

    public void setAllergiesPatientsCd(String allergiesPatientsCd) {
        this.allergiesPatientsCd = allergiesPatientsCd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatrecCd() {
        return patrecCd;
    }

    public void setPatrecCd(String patrecCd) {
        this.patrecCd = patrecCd;
    }

    public String getAllergyCd() {
        return allergyCd;
    }

    public void setAllergyCd(String allergyCd) {
        this.allergyCd = allergyCd;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public Object getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Object doctorId) {
        this.doctorId = doctorId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    public String getAllergyTypeName() {
        return allergyTypeName;
    }

    public void setAllergyTypeName(String allergyTypeName) {
        this.allergyTypeName = allergyTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAllDate() {
        return allDate;
    }

    public void setAllDate(String allDate) {
        this.allDate = allDate;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getReactionName() {
        return reactionName;
    }

    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverityName() {
        return severityName;
    }

    public void setSeverityName(String severityName) {
        this.severityName = severityName;
    }

}
