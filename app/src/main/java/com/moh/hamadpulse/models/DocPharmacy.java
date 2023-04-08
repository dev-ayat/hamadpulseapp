package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class DocPharmacy {
    @SerializedName("DOC_NAME")
    private String dOCNAME;

    @SerializedName("INP_PHARM_CODE")
    private String iNPPHARMCODE;

    @SerializedName("INP_PHARM_ADM_CD")
    private String iNPPHARMADMCD;

    @SerializedName("HOS_NO")
    private String hOSNO;

    @SerializedName("INP_PHARM_CREATED")
    private String iNPPHARMCREATEDON;

    @SerializedName("INP_PHARM_CREATED_BY_CD")
    private String iNPPHARMCREATEDBYCD;

    public DocPharmacy() {
    }

    public DocPharmacy(String dOCNAME, String iNPPHARMCODE, String iNPPHARMADMCD, String hOSNO, String iNPPHARMCREATEDON, String iNPPHARMCREATEDBYCD) {
        this.dOCNAME = dOCNAME;
        this.iNPPHARMCODE = iNPPHARMCODE;
        this.iNPPHARMADMCD = iNPPHARMADMCD;
        this.hOSNO = hOSNO;
        this.iNPPHARMCREATEDON = iNPPHARMCREATEDON;
        this.iNPPHARMCREATEDBYCD = iNPPHARMCREATEDBYCD;
    }

    public String getdOCNAME() {
        return dOCNAME;
    }

    public void setdOCNAME(String dOCNAME) {
        this.dOCNAME = dOCNAME;
    }

    public String getiNPPHARMCODE() {
        return iNPPHARMCODE;
    }

    public void setiNPPHARMCODE(String iNPPHARMCODE) {
        this.iNPPHARMCODE = iNPPHARMCODE;
    }

    public String getiNPPHARMADMCD() {
        return iNPPHARMADMCD;
    }

    public void setiNPPHARMADMCD(String iNPPHARMADMCD) {
        this.iNPPHARMADMCD = iNPPHARMADMCD;
    }

    public String gethOSNO() {
        return hOSNO;
    }

    public void sethOSNO(String hOSNO) {
        this.hOSNO = hOSNO;
    }

    public String getiNPPHARMCREATEDON() {
        return iNPPHARMCREATEDON;
    }

    public String getFormattedDateTime() {
        return iNPPHARMCREATEDON.replace(" ", "\n");
    }

    public void setiNPPHARMCREATEDON(String iNPPHARMCREATEDON) {
        this.iNPPHARMCREATEDON = iNPPHARMCREATEDON;
    }

    public String getiNPPHARMCREATEDBYCD() {
        return iNPPHARMCREATEDBYCD;
    }

    public void setiNPPHARMCREATEDBYCD(String iNPPHARMCREATEDBYCD) {
        this.iNPPHARMCREATEDBYCD = iNPPHARMCREATEDBYCD;
    }
}
