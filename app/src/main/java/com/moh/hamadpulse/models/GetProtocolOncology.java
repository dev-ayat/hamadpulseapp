package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProtocolOncology {

    @SerializedName("TUMORS_ORDER_NO")
    @Expose
    private String tumorsOrderNo;
    @SerializedName("PATIENT_CD")
    @Expose
    private String patientCd;
    @SerializedName("REPORT_DATE")
    @Expose
    private String reportDate;
    @SerializedName("P_ID")
    @Expose
    private String pId;
    @SerializedName("VISITED_ID")
    @Expose
    private String visitedId;
    @SerializedName("PROTOCOL_NAME")
    @Expose
    private String protocolName;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetProtocolOncology() {
    }

    /**
     *
     * @param patientCd
     * @param reportDate
     * @param protocolName
     * @param tumorsOrderNo
     * @param visitedId
     * @param pId
     */
    public GetProtocolOncology(String tumorsOrderNo, String patientCd,
                               String reportDate, String pId, String visitedId,
                               String protocolName) {
        super();
        this.tumorsOrderNo = tumorsOrderNo;
        this.patientCd = patientCd;
        this.reportDate = reportDate;
        this.pId = pId;
        this.visitedId = visitedId;
        this.protocolName = protocolName;
    }

    public String getTumorsOrderNo() {
        return tumorsOrderNo;
    }

    public void setTumorsOrderNo(String tumorsOrderNo) {
        this.tumorsOrderNo = tumorsOrderNo;
    }

    public String getPatientCd() {
        return patientCd;
    }

    public void setPatientCd(String patientCd) {
        this.patientCd = patientCd;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getVisitedId() {
        return visitedId;
    }

    public void setVisitedId(String visitedId) {
        this.visitedId = visitedId;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

}
