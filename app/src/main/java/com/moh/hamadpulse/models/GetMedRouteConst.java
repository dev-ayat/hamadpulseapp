
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMedRouteConst {

    @SerializedName("DOS_G_CODE")
    @Expose
    private String dOSGCODE;
    @SerializedName("DOS_G_NAME")
    @Expose
    private String dOSGNAME;
    @SerializedName("HOS_NO")
    @Expose
    private String hOSNO;

    /**
     * No args constructor for use in serialization
     */
    public GetMedRouteConst() {
    }

    /**
     * @param dOSGCODE
     * @param hOSNO
     * @param dOSGNAME
     */
    public GetMedRouteConst(String dOSGCODE, String dOSGNAME, String hOSNO) {
        super();
        this.dOSGCODE = dOSGCODE;
        this.dOSGNAME = dOSGNAME;
        this.hOSNO = hOSNO;
    }

    public String getDOSGCODE() {
        return dOSGCODE;
    }

    public void setDOSGCODE(String dOSGCODE) {
        this.dOSGCODE = dOSGCODE;
    }

    public String getDOSGNAME() {
        return dOSGNAME;
    }

    public void setDOSGNAME(String dOSGNAME) {
        this.dOSGNAME = dOSGNAME;
    }

    public String getHOSNO() {
        return hOSNO;
    }

    public void setHOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

}
