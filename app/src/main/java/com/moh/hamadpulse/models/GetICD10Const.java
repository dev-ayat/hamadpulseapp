
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetICD10Const {

    @SerializedName("ICD_CODE")
    @Expose
    private String iCDCODE;
    @SerializedName("ICD_NAME_EN")
    @Expose
    private String iCDNAMEEN;
    @SerializedName("ICD_CD")
    @Expose
    private String iCDCD;

    /**
     * No args constructor for use in serialization
     */
    public GetICD10Const() {
    }

    /**
     * @param iCDNAMEEN
     * @param iCDCODE
     * @param iCDCD
     */
    public GetICD10Const(String iCDCODE, String iCDNAMEEN, String iCDCD) {
        super();
        this.iCDCODE = iCDCODE;
        this.iCDNAMEEN = iCDNAMEEN;
        this.iCDCD = iCDCD;
    }

    public String getICDCODE() {
        return iCDCODE;
    }

    public void setICDCODE(String iCDCODE) {
        this.iCDCODE = iCDCODE;
    }

    public String getICDNAMEEN() {
        return iCDNAMEEN;
    }

    public void setICDNAMEEN(String iCDNAMEEN) {
        this.iCDNAMEEN = iCDNAMEEN;
    }

    public String getICDCD() {
        return iCDCD;
    }

    public void setICDCD(String iCDCD) {
        this.iCDCD = iCDCD;
    }

}
