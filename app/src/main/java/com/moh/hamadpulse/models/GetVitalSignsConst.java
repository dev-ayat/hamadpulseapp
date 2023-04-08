
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVitalSignsConst {

    @SerializedName("VS_CODE")
    @Expose
    private String vSCODE;
    @SerializedName("VS_NAME")
    @Expose
    private String vSNAME;
    private String vSValuePost;

    /**
     * No args constructor for use in serialization
     */
    public GetVitalSignsConst() {
    }

    /**
     * @param vSCODE
     * @param vSNAME
     */
    public GetVitalSignsConst(String vSCODE, String vSNAME) {
        super();
        this.vSCODE = vSCODE;
        this.vSNAME = vSNAME;
    }

    public String getvSValuePost() {
        return vSValuePost;
    }

    public void setvSValuePost(String vSValuePost) {
        this.vSValuePost = vSValuePost;
    }

    public String getVSCODE() {
        return vSCODE;
    }

    public void setVSCODE(String vSCODE) {
        this.vSCODE = vSCODE;
    }

    public String getVSNAME() {
        return vSNAME;
    }

    public void setVSNAME(String vSNAME) {
        this.vSNAME = vSNAME;
    }

}
