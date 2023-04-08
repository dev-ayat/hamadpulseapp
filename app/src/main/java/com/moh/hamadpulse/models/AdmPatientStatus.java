
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdmPatientStatus {

    @SerializedName("ADM_PATIENT_ID")
    @Expose
    private String aDMPATIENTID;
    @SerializedName("ADM_PATIENT_CD")
    @Expose
    private String aDMPATIENTCD;
    @SerializedName("ADM_PATIENT_STATUS_CD")
    @Expose
    private String aDMPATIENTSTATUSCD;
    @SerializedName("CREATED_DATE")
    @Expose
    private String aDMPATIENTCREATEDON;
    @SerializedName("ADM_PATIENT_CREATED_BY")
    @Expose
    private String aDMPATIENTCREATEDBY;
    @SerializedName("ADM_PATIENT_PATREC_CD")
    @Expose
    private String aDMPATIENTPATRECCD;
    @SerializedName("PATIENT_STATUS_NAME_EN")
    @Expose
    private String pATIENTSTATUSNAMEEN;
    @SerializedName("HOS_NO")
    @Expose
    private String hOSNO;

    public String getADMPATIENTID() {
        return aDMPATIENTID;
    }

    public void setADMPATIENTID(String aDMPATIENTID) {
        this.aDMPATIENTID = aDMPATIENTID;
    }

    public String getADMPATIENTCD() {
        return aDMPATIENTCD;
    }

    public void setADMPATIENTCD(String aDMPATIENTCD) {
        this.aDMPATIENTCD = aDMPATIENTCD;
    }

    public String getADMPATIENTSTATUSCD() {
        return aDMPATIENTSTATUSCD;
    }

    public void setADMPATIENTSTATUSCD(String aDMPATIENTSTATUSCD) {
        this.aDMPATIENTSTATUSCD = aDMPATIENTSTATUSCD;
    }

    public String getADMPATIENTCREATEDON() {
        return aDMPATIENTCREATEDON;
    }

    public void setADMPATIENTCREATEDON(String aDMPATIENTCREATEDON) {
        this.aDMPATIENTCREATEDON = aDMPATIENTCREATEDON;
    }

    public String getADMPATIENTCREATEDBY() {
        return aDMPATIENTCREATEDBY;
    }

    public void setADMPATIENTCREATEDBY(String aDMPATIENTCREATEDBY) {
        this.aDMPATIENTCREATEDBY = aDMPATIENTCREATEDBY;
    }

    public String getADMPATIENTPATRECCD() {
        return aDMPATIENTPATRECCD;
    }

    public void setADMPATIENTPATRECCD(String aDMPATIENTPATRECCD) {
        this.aDMPATIENTPATRECCD = aDMPATIENTPATRECCD;
    }

    public String getPATIENTSTATUSNAMEEN() {
        return pATIENTSTATUSNAMEEN;
    }

    public void setPATIENTSTATUSNAMEEN(String pATIENTSTATUSNAMEEN) {
        this.pATIENTSTATUSNAMEEN = pATIENTSTATUSNAMEEN;
    }

    public String getHOSNO() {
        return hOSNO;
    }

    public void setHOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

}
