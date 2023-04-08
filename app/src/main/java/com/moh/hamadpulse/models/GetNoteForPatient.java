
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNoteForPatient {


    @SerializedName("DOC_NUR_CODE")
    @Expose
    private String dOCNURSERIAL;
    @SerializedName("DOC_NUR_ADM_CD")
    @Expose
    private String dOCNURADMCD;
    @SerializedName("DOC_NUR_PATREC_CD")
    @Expose
    private String dOCNURPATRECCD;
    @SerializedName("DOC_NUR_NOTE")
    @Expose
    private String dOCNURNOTE;
    @SerializedName("PATIENT_STATUS_NAME_AR")
    @Expose
    private String pATIENTSTATUSNAMEAR;

    @SerializedName("PATIENT_STATUS_NAME_EN")
    @Expose
    private String PATIENT_STATUS_NAME_EN;
    @SerializedName("DOC_CREATED_ON")
    @Expose
    private String dOCCREATEDON;
    @SerializedName("DOC_NAME")
    @Expose
    private String DOC_NAME;
    @SerializedName("USER_ID")
    @Expose
    private String USER_ID;

    /**
     * No args constructor for use in serialization
     */
    public GetNoteForPatient() {
    }

    public String getPATIENT_STATUS_NAME_EN() {
        return PATIENT_STATUS_NAME_EN;
    }

    public void setPATIENT_STATUS_NAME_EN(String PATIENT_STATUS_NAME_EN) {
        this.PATIENT_STATUS_NAME_EN = PATIENT_STATUS_NAME_EN;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    /**
     * @param dOCNURSERIAL
     * @param dOCNURNOTE
     * @param dOCNURPATRECCD
     * @param pATIENTSTATUSNAMEAR
     * @param dOCCREATEDON
     * @param dOCNURADMCD
     */
    public GetNoteForPatient(String dOCNURSERIAL, String dOCNURADMCD, String dOCNURPATRECCD, String dOCNURNOTE, String pATIENTSTATUSNAMEAR, String dOCCREATEDON) {
        super();
        this.dOCNURSERIAL = dOCNURSERIAL;
        this.dOCNURADMCD = dOCNURADMCD;
        this.dOCNURPATRECCD = dOCNURPATRECCD;
        this.dOCNURNOTE = dOCNURNOTE;
        this.pATIENTSTATUSNAMEAR = pATIENTSTATUSNAMEAR;
        this.dOCCREATEDON = dOCCREATEDON;
    }

    public String getdOCNURSERIAL() {
        return dOCNURSERIAL;
    }

    public void setdOCNURSERIAL(String dOCNURSERIAL) {
        this.dOCNURSERIAL = dOCNURSERIAL;
    }

    public String getDOCNURADMCD() {
        return dOCNURADMCD;
    }

    public void setDOCNURADMCD(String dOCNURADMCD) {
        this.dOCNURADMCD = dOCNURADMCD;
    }

    public String getDOCNURPATRECCD() {
        return dOCNURPATRECCD;
    }

    public void setDOCNURPATRECCD(String dOCNURPATRECCD) {
        this.dOCNURPATRECCD = dOCNURPATRECCD;
    }

    public String getDOCNURNOTE() {
        return dOCNURNOTE;
    }

    public void setDOCNURNOTE(String dOCNURNOTE) {
        this.dOCNURNOTE = dOCNURNOTE;
    }

    public String getPATIENTSTATUSNAMEAR() {
        return pATIENTSTATUSNAMEAR;
    }

    public void setPATIENTSTATUSNAMEAR(String pATIENTSTATUSNAMEAR) {
        this.pATIENTSTATUSNAMEAR = pATIENTSTATUSNAMEAR;
    }

    public String getDOCCREATEDON() {
        return dOCCREATEDON;
    }

    public void setDOCCREATEDON(String dOCCREATEDON) {
        this.dOCCREATEDON = dOCCREATEDON;
    }

    public String getDOC_NAME() {
        return DOC_NAME;
    }

    public void setDOC_NAME(String DOC_NAME) {
        this.DOC_NAME = DOC_NAME;
    }
}
