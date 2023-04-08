
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMedDoseConst {

    @SerializedName("DOSE_CODE")
    @Expose
    private String dOSECODE;
    @SerializedName("DOSE_NAME")
    @Expose
    private String dOSENAME;
    @SerializedName("DOSE_VAUE")
    @Expose
    private String dOSEVAUE;
    @SerializedName("EXTERNAL_PHARM_DOSE_VALUE")
    @Expose
    private String eXTERNALPHARMDOSEVALUE;

    /**
     * No args constructor for use in serialization
     */
    public GetMedDoseConst() {
    }

    /**
     * @param dOSEVAUE
     * @param eXTERNALPHARMDOSEVALUE
     * @param dOSECODE
     * @param dOSENAME
     */
    public GetMedDoseConst(String dOSECODE, String dOSENAME, String dOSEVAUE, String eXTERNALPHARMDOSEVALUE) {
        super();
        this.dOSECODE = dOSECODE;
        this.dOSENAME = dOSENAME;
        this.dOSEVAUE = dOSEVAUE;
        this.eXTERNALPHARMDOSEVALUE = eXTERNALPHARMDOSEVALUE;
    }

    public GetMedDoseConst(String dOSECODE, String dOSENAME) {
        this.dOSECODE = dOSECODE;
        this.dOSENAME = dOSENAME;
    }

    public String getDOSECODE() {
        return dOSECODE;
    }

    public void setDOSECODE(String dOSECODE) {
        this.dOSECODE = dOSECODE;
    }

    public String getDOSENAME() {
        return dOSENAME;
    }

    public void setDOSENAME(String dOSENAME) {
        this.dOSENAME = dOSENAME;
    }

    public String getDOSEVAUE() {
        return dOSEVAUE;
    }

    public void setDOSEVAUE(String dOSEVAUE) {
        this.dOSEVAUE = dOSEVAUE;
    }

    public String getEXTERNALPHARMDOSEVALUE() {
        return eXTERNALPHARMDOSEVALUE;
    }

    public void setEXTERNALPHARMDOSEVALUE(String eXTERNALPHARMDOSEVALUE) {
        this.eXTERNALPHARMDOSEVALUE = eXTERNALPHARMDOSEVALUE;
    }

}
