
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMedicineConst {

    @SerializedName("MED_M_CODE")
    @Expose
    private String mEDMCODE;
    @SerializedName("PHARMACY_NO")
    @Expose
    private String pHARMACYNO;
    @SerializedName("ITEM_CODE")
    @Expose
    private String iTEMCODE;
    @SerializedName("ITEM_NAME")
    @Expose
    private String iTEMNAME;
    @SerializedName("UNIT_CODE")
    @Expose
    private String uNITCODE;
    @SerializedName("UNIT_NAME")
    @Expose
    private String uNITNAME;
    @SerializedName("AVAILABLE_BAL")
    @Expose
    private String aVAILABLEBAL;
    @SerializedName("REAL_BAL")
    @Expose
    private String rEALBAL;
    @SerializedName("ITEM_PRICE")
    @Expose
    private String iTEMPRICE;

    /**
     * No args constructor for use in serialization
     */
    public GetMedicineConst() {
    }

    /**
     * @param pHARMACYNO
     * @param iTEMCODE
     * @param uNITCODE
     * @param rEALBAL
     * @param iTEMPRICE
     * @param iTEMNAME
     * @param mEDMCODE
     * @param uNITNAME
     * @param aVAILABLEBAL
     */
    public GetMedicineConst(String mEDMCODE, String pHARMACYNO, String iTEMCODE, String iTEMNAME, String uNITCODE, String uNITNAME, String aVAILABLEBAL, String rEALBAL, String iTEMPRICE) {
        super();
        this.mEDMCODE = mEDMCODE;
        this.pHARMACYNO = pHARMACYNO;
        this.iTEMCODE = iTEMCODE;
        this.iTEMNAME = iTEMNAME;
        this.uNITCODE = uNITCODE;
        this.uNITNAME = uNITNAME;
        this.aVAILABLEBAL = aVAILABLEBAL;
        this.rEALBAL = rEALBAL;
        this.iTEMPRICE = iTEMPRICE;
    }

    public String getMEDMCODE() {
        return mEDMCODE;
    }

    public void setMEDMCODE(String mEDMCODE) {
        this.mEDMCODE = mEDMCODE;
    }

    public String getPHARMACYNO() {
        return pHARMACYNO;
    }

    public void setPHARMACYNO(String pHARMACYNO) {
        this.pHARMACYNO = pHARMACYNO;
    }

    public String getITEMCODE() {
        return iTEMCODE;
    }

    public void setITEMCODE(String iTEMCODE) {
        this.iTEMCODE = iTEMCODE;
    }

    public String getITEMNAME() {
        return iTEMNAME;
    }

    public void setITEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public String getUNITCODE() {
        return uNITCODE;
    }

    public void setUNITCODE(String uNITCODE) {
        this.uNITCODE = uNITCODE;
    }

    public String getUNITNAME() {
        return uNITNAME;
    }

    public void setUNITNAME(String uNITNAME) {
        this.uNITNAME = uNITNAME;
    }

    public String getAVAILABLEBAL() {
        return aVAILABLEBAL;
    }

    public void setAVAILABLEBAL(String aVAILABLEBAL) {
        this.aVAILABLEBAL = aVAILABLEBAL;
    }

    public String getREALBAL() {
        return rEALBAL;
    }

    public void setREALBAL(String rEALBAL) {
        this.rEALBAL = rEALBAL;
    }

    public String getITEMPRICE() {
        return iTEMPRICE;
    }

    public void setITEMPRICE(String iTEMPRICE) {
        this.iTEMPRICE = iTEMPRICE;
    }

}
