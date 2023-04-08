
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVentilationConst {

    @SerializedName("VEN_TYPE_CODE")
    @Expose
    private String vENTYPECODE;
    @SerializedName("VEN_TYPE_NAME")
    @Expose
    private String vENTYPENAME;

    /**
     * No args constructor for use in serialization
     */
    public GetVentilationConst() {
    }

    /**
     * @param vENTYPENAME
     * @param vENTYPECODE
     */
    public GetVentilationConst(String vENTYPECODE, String vENTYPENAME) {
        super();
        this.vENTYPECODE = vENTYPECODE;
        this.vENTYPENAME = vENTYPENAME;
    }

    public String getVENTYPECODE() {
        return vENTYPECODE;
    }

    public void setVENTYPECODE(String vENTYPECODE) {
        this.vENTYPECODE = vENTYPECODE;
    }

    public String getVENTYPENAME() {
        return vENTYPENAME;
    }

    public void setVENTYPENAME(String vENTYPENAME) {
        this.vENTYPENAME = vENTYPENAME;
    }

}
