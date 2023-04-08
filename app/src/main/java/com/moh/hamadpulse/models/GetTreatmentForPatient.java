
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moh.hamadpulse.bean.AttributeEnumerable;

import java.io.Serializable;

public class GetTreatmentForPatient implements AttributeEnumerable, Serializable, Cloneable {

    @SerializedName("INP_PHARM_CODE")
    @Expose
    private String iNPPHARMCODE;
    @SerializedName("INP_PHARM_ADM_CD")
    @Expose
    private String iNPPHARMADMCD;
    @SerializedName("INP_INTERVAL")
    @Expose
    private String iNPINTERVAL;
    @SerializedName("INP_WANTED_AMOUNT")
    @Expose
    private String iNPWANTEDAMOUNT;
    @SerializedName("INP_PHARM_CREATED_BY_CD")
    @Expose
    private String iNPPHARMCREATEDBYCD;
    @SerializedName("DOC_FULL_NAME")
    @Expose
    private String dOCFULLNAME;
    @SerializedName("INP_PHARM_CREATED_ON")
    @Expose
    private String iNPPHARMCREATEDON;
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
    @SerializedName("DOS_G_NAME")
    @Expose
    private String dOSGNAME;
    @SerializedName("INP_DOSE_GIVING_CD")
    @Expose
    private String DOSEGIVINGC;
    @SerializedName("DIFF_MIN")
    @Expose
    private int DIFF_MIN;

    /**
     * No args constructor for use in serialization
     */
    public GetTreatmentForPatient() {
    }

    /**
     * @param dOSEVAUE
     * @param iTEMCODE
     * @param dOCFULLNAME
     * @param iNPPHARMCREATEDON
     * @param iTEMPRICE
     * @param iNPINTERVAL
     * @param uNITNAME
     * @param aVAILABLEBAL
     * @param eXTERNALPHARMDOSEVALUE
     * @param pHARMACYNO
     * @param uNITCODE
     * @param iNPPHARMCODE
     * @param rEALBAL
     * @param iNPPHARMCREATEDBYCD
     * @param iTEMNAME
     * @param mEDMCODE
     * @param dOSECODE
     * @param dOSGNAME
     * @param dOSENAME
     * @param iNPWANTEDAMOUNT
     * @param iNPPHARMADMCD
     */
    public GetTreatmentForPatient(String iNPPHARMCODE, String iNPPHARMADMCD, String iNPINTERVAL, String iNPWANTEDAMOUNT, String iNPPHARMCREATEDBYCD, String dOCFULLNAME, String iNPPHARMCREATEDON, String mEDMCODE, String pHARMACYNO, String iTEMCODE, String iTEMNAME, String uNITCODE, String uNITNAME, String aVAILABLEBAL, String rEALBAL, String iTEMPRICE, String dOSECODE, String dOSENAME, String dOSEVAUE, String eXTERNALPHARMDOSEVALUE, String dOSGNAME, String DOSEGIVINGC) {
        super();
        this.iNPPHARMCODE = iNPPHARMCODE;
        this.iNPPHARMADMCD = iNPPHARMADMCD;
        this.iNPINTERVAL = iNPINTERVAL;
        this.iNPWANTEDAMOUNT = iNPWANTEDAMOUNT;
        this.iNPPHARMCREATEDBYCD = iNPPHARMCREATEDBYCD;
        this.dOCFULLNAME = dOCFULLNAME;
        this.iNPPHARMCREATEDON = iNPPHARMCREATEDON;
        this.mEDMCODE = mEDMCODE;
        this.pHARMACYNO = pHARMACYNO;
        this.iTEMCODE = iTEMCODE;
        this.iTEMNAME = iTEMNAME;
        this.uNITCODE = uNITCODE;
        this.uNITNAME = uNITNAME;
        this.aVAILABLEBAL = aVAILABLEBAL;
        this.rEALBAL = rEALBAL;
        this.iTEMPRICE = iTEMPRICE;
        this.dOSECODE = dOSECODE;
        this.dOSENAME = dOSENAME;
        this.dOSEVAUE = dOSEVAUE;
        this.eXTERNALPHARMDOSEVALUE = eXTERNALPHARMDOSEVALUE;
        this.dOSGNAME = dOSGNAME;
        this.DOSEGIVINGC = DOSEGIVINGC;
    }

    public String getINPPHARMCODE() {
        return iNPPHARMCODE;
    }

    public void setINPPHARMCODE(String iNPPHARMCODE) {
        this.iNPPHARMCODE = iNPPHARMCODE;
    }

    public String getINPPHARMADMCD() {
        return iNPPHARMADMCD;
    }

    public void setINPPHARMADMCD(String iNPPHARMADMCD) {
        this.iNPPHARMADMCD = iNPPHARMADMCD;
    }

    public String getINPINTERVAL() {
        return iNPINTERVAL;
    }

    public void setINPINTERVAL(String iNPINTERVAL) {
        this.iNPINTERVAL = iNPINTERVAL;
    }

    public String getINPWANTEDAMOUNT() {
        return iNPWANTEDAMOUNT;
    }

    public void setINPWANTEDAMOUNT(String iNPWANTEDAMOUNT) {
        this.iNPWANTEDAMOUNT = iNPWANTEDAMOUNT;
    }

    public String getINPPHARMCREATEDBYCD() {
        return iNPPHARMCREATEDBYCD;
    }

    public void setINPPHARMCREATEDBYCD(String iNPPHARMCREATEDBYCD) {
        this.iNPPHARMCREATEDBYCD = iNPPHARMCREATEDBYCD;
    }

    public String getDOCFULLNAME() {
        return dOCFULLNAME;
    }

    public void setDOCFULLNAME(String dOCFULLNAME) {
        this.dOCFULLNAME = dOCFULLNAME;
    }

    public String getINPPHARMCREATEDON() {
        return iNPPHARMCREATEDON;
    }

    public void setINPPHARMCREATEDON(String iNPPHARMCREATEDON) {
        this.iNPPHARMCREATEDON = iNPPHARMCREATEDON;
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

    public Object getDOSGNAME() {
        return dOSGNAME;
    }

    public void setDOSGNAME(String dOSGNAME) {
        this.dOSGNAME = dOSGNAME;
    }

    public String getDOSEGIVINGC() {
        return DOSEGIVINGC;
    }

    public void setDOSEGIVINGC(String DOSEGIVINGC) {
        this.DOSEGIVINGC = DOSEGIVINGC;
    }


    public int getDIFF_MIN() {
        return DIFF_MIN;
    }

    public void setDIFF_MIN(int DIFF_MIN) {
        this.DIFF_MIN = DIFF_MIN;
    }

    @Override
    public int getAttributeCountable() {
        return 7;
    }

    @Override
    public Object getAttribute(int i) {
        Object attribute;
        switch (i) {
            case 0:
                attribute = getINPPHARMCREATEDON();
                break;
            case 1:
                attribute = getITEMNAME();
                break;
            case 2:
                attribute = getINPINTERVAL();
                break;
            case 3:
                attribute = getDOSENAME();
                break;
            case 4:
                attribute = getDOSGNAME();
                break;
            case 5:
                attribute = getINPWANTEDAMOUNT();
                break;
            case 6:
                attribute = getDOCFULLNAME();
                break;
            default:
                attribute = "";
                break;
        }
        return attribute;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
