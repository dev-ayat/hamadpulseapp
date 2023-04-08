package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TumerRepModel {

    @SerializedName("TUMORS_ORDER_NO")
    @Expose
    private String tumorsOrderNo;
    @SerializedName("REPORT_DATE")
    @Expose
    private String reportDate;
    @SerializedName("P_ID")
    @Expose
    private String pId;
    @SerializedName("PREMEDICATION")
    @Expose
    private String premedication;
    @SerializedName("NP_ACTION_ID")
    @Expose
    private String npActionId;
    @SerializedName("STATUS")
    @Expose
    private String status;
    @SerializedName("STATUS_NAME")
    @Expose
    private String statusName;

    public String getTumorsOrderNo() {
        return tumorsOrderNo;
    }

    public void setTumorsOrderNo(String tumorsOrderNo) {
        this.tumorsOrderNo = tumorsOrderNo;
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

    public String getPremedication() {
        return premedication;
    }

    public void setPremedication(String premedication) {
        this.premedication = premedication;
    }

    public String getNpActionId() {
        return npActionId;
    }

    public void setNpActionId(String npActionId) {
        this.npActionId = npActionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
