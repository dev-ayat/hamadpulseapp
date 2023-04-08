
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetNursingDepts implements Serializable
{

    private final static long serialVersionUID = -3834107241228857601L;
    @SerializedName("LOC_CODE")
    @Expose
    private String lOCCODE;
    @SerializedName("LOC_NAME_AR")
    @Expose
    private String lOCNAMEAR;
    @SerializedName("HOS_NO")
    @Expose
    private String hOSNO;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetNursingDepts() {
    }

    /**
     * 
     * @param lOCCODE
     * @param lOCNAMEAR
     * @param hOSNO
     */
    public GetNursingDepts(String lOCCODE, String lOCNAMEAR, String hOSNO) {
        super();
        this.lOCCODE = lOCCODE;
        this.lOCNAMEAR = lOCNAMEAR;
        this.hOSNO = hOSNO;
    }

    public String getLOCCODE() {
        return lOCCODE;
    }

    public void setLOCCODE(String lOCCODE) {
        this.lOCCODE = lOCCODE;
    }

    public String getLOCNAMEAR() {
        return lOCNAMEAR;
    }

    public void setLOCNAMEAR(String lOCNAMEAR) {
        this.lOCNAMEAR = lOCNAMEAR;
    }

    public String getHOSNO() {
        return hOSNO;
    }

    public void setHOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

}
