package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class P_VitalSignsModel {
    @SerializedName("NURSING_PROCEDURES_CODE")
    @Expose
    private String nursingProceduresCode;
    @SerializedName("PATREC_PATIENT_CD")
    @Expose
    private String patrecPatientCd;
    @SerializedName("PRESSURE")
    @Expose
    private String pressure;
    @SerializedName("DIA")
    @Expose
    private String dia;
    @SerializedName("BP_HAND")
    @Expose
    private String bpHand;
    @SerializedName("POSTION_HAND")
    @Expose
    private String postionHand;
    @SerializedName("O2_SAT")
    @Expose
    private String o2Sat;
    @SerializedName("HEAT")
    @Expose
    private String heat;
    @SerializedName("POSTION_HEAT")
    @Expose
    private String postionHeat;
    @SerializedName("PULSE")
    @Expose
    private String pulse;
    @SerializedName("PULSE_HAND")
    @Expose
    private String pulseHand;
    @SerializedName("RESPIRATORY_RATE")
    @Expose
    private String respiratoryRate;
    @SerializedName("WEIGHT")
    @Expose
    private String weight;
    @SerializedName("HEIGHT")
    @Expose
    private String height;
    @SerializedName("BMI")
    @Expose
    private String bmi;
    @SerializedName("HC")
    @Expose
    private String hc;
    @SerializedName("CREATED_BY")
    @Expose
    private String createdBy;
    @SerializedName("CREATED_ON")
    @Expose
    private String createdOn;
    @SerializedName("VISIT_CD")
    @Expose
    private String visitCd;
    @SerializedName("IS_FINISH")
    @Expose
    private String isFinish;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;

    public String getNursingProceduresCode() {
        return nursingProceduresCode;
    }

    public void setNursingProceduresCode(String nursingProceduresCode) {
        this.nursingProceduresCode = nursingProceduresCode;
    }

    public String getPatrecPatientCd() {
        return patrecPatientCd;
    }

    public void setPatrecPatientCd(String patrecPatientCd) {
        this.patrecPatientCd = patrecPatientCd;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getBpHand() {
        return bpHand;
    }

    public void setBpHand(String bpHand) {
        this.bpHand = bpHand;
    }

    public String getPostionHand() {
        return postionHand;
    }

    public void setPostionHand(String postionHand) {
        this.postionHand = postionHand;
    }

    public String getO2Sat() {
        return o2Sat;
    }

    public void setO2Sat(String o2Sat) {
        this.o2Sat = o2Sat;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getPostionHeat() {
        return postionHeat;
    }

    public void setPostionHeat(String postionHeat) {
        this.postionHeat = postionHeat;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getPulseHand() {
        return pulseHand;
    }

    public void setPulseHand(String pulseHand) {
        this.pulseHand = pulseHand;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getVisitCd() {
        return visitCd;
    }

    public void setVisitCd(String visitCd) {
        this.visitCd = visitCd;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }
}
