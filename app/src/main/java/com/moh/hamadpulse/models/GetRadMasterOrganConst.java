
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadMasterOrganConst {

    @SerializedName("ORGAN_CODE")
    @Expose
    private String oRGANCODE;
    @SerializedName("ORGAN_NAME_AR")
    @Expose
    private String oRGANNAMEAR;
    @SerializedName("ORGAN_NAME_EN")
    @Expose
    private String oRGANNAMEEN;

    /**
     * No args constructor for use in serialization
     */
    public GetRadMasterOrganConst() {
    }

    /**
     * @param oRGANCODE
     * @param oRGANNAMEAR
     * @param oRGANNAMEEN
     */
    public GetRadMasterOrganConst(String oRGANCODE, String oRGANNAMEAR, String oRGANNAMEEN) {
        super();
        this.oRGANCODE = oRGANCODE;
        this.oRGANNAMEAR = oRGANNAMEAR;
        this.oRGANNAMEEN = oRGANNAMEEN;
    }

    public String getORGANCODE() {
        return oRGANCODE;
    }

    public void setORGANCODE(String oRGANCODE) {
        this.oRGANCODE = oRGANCODE;
    }

    public String getORGANNAMEAR() {
        return oRGANNAMEAR;
    }

    public void setORGANNAMEAR(String oRGANNAMEAR) {
        this.oRGANNAMEAR = oRGANNAMEAR;
    }

    public String getORGANNAMEEN() {
        return oRGANNAMEEN;
    }

    public void setORGANNAMEEN(String oRGANNAMEEN) {
        this.oRGANNAMEEN = oRGANNAMEEN;
    }

}
