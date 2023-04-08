
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrganDetailsConst {

    @SerializedName("ORGAN_D_CODE")
    @Expose
    private String oRGANDCODE;
    @SerializedName("ORGAN_D_NAME_AR")
    @Expose
    private String oRGANDNAMEAR;
    @SerializedName("ORGAN_D_NAME_EN")
    @Expose
    private String oRGANDNAMEEN;
    @SerializedName("ORGAN_CD")
    @Expose
    private String oRGANCD;

    /**
     * No args constructor for use in serialization
     */
    public GetOrganDetailsConst() {
    }

    /**
     * @param oRGANDCODE
     * @param oRGANDNAMEAR
     * @param oRGANCD
     * @param oRGANDNAMEEN
     */
    public GetOrganDetailsConst(String oRGANDCODE, String oRGANDNAMEAR, String oRGANDNAMEEN, String oRGANCD) {
        super();
        this.oRGANDCODE = oRGANDCODE;
        this.oRGANDNAMEAR = oRGANDNAMEAR;
        this.oRGANDNAMEEN = oRGANDNAMEEN;
        this.oRGANCD = oRGANCD;
    }

    public String getORGANDCODE() {
        return oRGANDCODE;
    }

    public void setORGANDCODE(String oRGANDCODE) {
        this.oRGANDCODE = oRGANDCODE;
    }

    public String getORGANDNAMEAR() {
        return oRGANDNAMEAR;
    }

    public void setORGANDNAMEAR(String oRGANDNAMEAR) {
        this.oRGANDNAMEAR = oRGANDNAMEAR;
    }

    public String getORGANDNAMEEN() {
        return oRGANDNAMEEN;
    }

    public void setORGANDNAMEEN(String oRGANDNAMEEN) {
        this.oRGANDNAMEEN = oRGANDNAMEEN;
    }

    public String getORGANCD() {
        return oRGANCD;
    }

    public void setORGANCD(String oRGANCD) {
        this.oRGANCD = oRGANCD;
    }


}
