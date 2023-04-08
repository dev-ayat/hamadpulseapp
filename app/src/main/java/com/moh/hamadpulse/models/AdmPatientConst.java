
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class AdmPatientConst {

    @SerializedName("PATIENT_STATUS_ID")
    @Expose
    private String pATIENTSTATUSID;
    @SerializedName("PATIENT_STATUS_NAME_EN")
    @Expose
    private String pATIENTSTATUSNAMEEN;
    @SerializedName("PATIENT_STATUS_NAME_AR")
    @Expose
    private String pATIENTSTATUSNAMEAR;
    @SerializedName("HOS_NO")
    @Expose
    private String hOSNO;

    public AdmPatientConst(String pATIENTSTATUSID, String pATIENTSTATUSNAMEEN) {
        super();
        this.pATIENTSTATUSID = pATIENTSTATUSID;
        this.pATIENTSTATUSNAMEEN = pATIENTSTATUSNAMEEN;
    }

    public AdmPatientConst(String pATIENTSTATUSNAMEEN) {
        this.pATIENTSTATUSNAMEEN = pATIENTSTATUSNAMEEN;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmPatientConst that = (AdmPatientConst) o;
        return Objects.equals(pATIENTSTATUSNAMEEN, that.pATIENTSTATUSNAMEEN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pATIENTSTATUSNAMEEN);
    }

    public String getPATIENTSTATUSID() {
        return pATIENTSTATUSID;
    }

    public void setPATIENTSTATUSID(String pATIENTSTATUSID) {
        this.pATIENTSTATUSID = pATIENTSTATUSID;
    }

    public String getPATIENTSTATUSNAMEEN() {
        return pATIENTSTATUSNAMEEN;
    }

    public void setPATIENTSTATUSNAMEEN(String pATIENTSTATUSNAMEEN) {
        this.pATIENTSTATUSNAMEEN = pATIENTSTATUSNAMEEN;
    }

    public String getPATIENTSTATUSNAMEAR() {
        return pATIENTSTATUSNAMEAR;
    }

    public void setPATIENTSTATUSNAMEAR(String pATIENTSTATUSNAMEAR) {
        this.pATIENTSTATUSNAMEAR = pATIENTSTATUSNAMEAR;
    }

    public String getHOSNO() {
        return hOSNO;
    }

    public void setHOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

    @Override
    public String toString() {
        return pATIENTSTATUSNAMEEN;
    }

}
