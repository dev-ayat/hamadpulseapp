
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVitalSignsForPatient {

    @SerializedName("VS_NAME")
    @Expose
    private String vSNAME;
    @SerializedName("V_ADM_VALUE")
    @Expose
    private String vADMVALUE;
    @SerializedName("V_ADM_DATE")
    @Expose
    private String vADMDATE;

    /**
     * No args constructor for use in serialization
     */
    public GetVitalSignsForPatient() {
    }

    /**
     * @param vADMVALUE
     * @param vADMDATE
     * @param vSNAME
     */
    public GetVitalSignsForPatient(String vSNAME, String vADMVALUE, String vADMDATE) {
        super();
        this.vSNAME = vSNAME;
        this.vADMVALUE = vADMVALUE;
        this.vADMDATE = vADMDATE;
    }

    public String getVSNAME() {
        return vSNAME;
    }

    public void setVSNAME(String vSNAME) {
        this.vSNAME = vSNAME;
    }

    public String getVADMVALUE() {
        return vADMVALUE;
    }

    public void setVADMVALUE(String vADMVALUE) {
        this.vADMVALUE = vADMVALUE;
    }

    public String getVADMDATE() {
        return vADMDATE;
    }

    public void setVADMDATE(String vADMDATE) {
        this.vADMDATE = vADMDATE;
    }

}
