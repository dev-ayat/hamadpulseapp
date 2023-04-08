
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VitalSigns {

    @SerializedName("NURSING_PROCEDURES_CODE")
    @Expose
    private String nURSINGPROCEDURESCODE;
    @SerializedName("PATREC_PATIENT_CD")
    @Expose
    private String pATRECPATIENTCD;
    @SerializedName("PRESSURE")
    @Expose
    private String pRESSURE;
    @SerializedName("PULSE")
    @Expose
    private String pULSE;
    @SerializedName("HEAT")
    @Expose
    private String hEAT;
    @SerializedName("RESPIRATORY_RATE")
    @Expose
    private String rESPIRATORYRATE;
    @SerializedName("CREATED_BY")
    @Expose
    private String cREATEDBY;
    @SerializedName("CREATED_DATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("VISIT_CD")
    @Expose
    private String vISITCD;
    @SerializedName("HOS_NO")
    @Expose
    private String hOSNO;

    public String getNURSINGPROCEDURESCODE() {
        return nURSINGPROCEDURESCODE;
    }

    public void setNURSINGPROCEDURESCODE(String nURSINGPROCEDURESCODE) {
        this.nURSINGPROCEDURESCODE = nURSINGPROCEDURESCODE;
    }

    public String getPATRECPATIENTCD() {
        return pATRECPATIENTCD;
    }

    public void setPATRECPATIENTCD(String pATRECPATIENTCD) {
        this.pATRECPATIENTCD = pATRECPATIENTCD;
    }

    public String getPRESSURE() {
        return pRESSURE;
    }

    public void setPRESSURE(String pRESSURE) {
        this.pRESSURE = pRESSURE;
    }

    public String getPULSE() {
        return pULSE;
    }

    public void setPULSE(String pULSE) {
        this.pULSE = pULSE;
    }

    public String getHEAT() {
        return hEAT;
    }

    public void setHEAT(String hEAT) {
        this.hEAT = hEAT;
    }

    public String getRESPIRATORYRATE() {
        return rESPIRATORYRATE;
    }

    public void setRESPIRATORYRATE(String rESPIRATORYRATE) {
        this.rESPIRATORYRATE = rESPIRATORYRATE;
    }

    public String getCREATEDBY() {
        return cREATEDBY;
    }

    public void setCREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(String cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public String getVISITCD() {
        return vISITCD;
    }

    public void setVISITCD(String vISITCD) {
        this.vISITCD = vISITCD;
    }

    public String getHOSNO() {
        return hOSNO;
    }

    public void setHOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

}
