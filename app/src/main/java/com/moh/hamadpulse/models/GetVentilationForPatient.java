
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moh.hamadpulse.bean.AttributeEnumerable;

public class GetVentilationForPatient implements AttributeEnumerable {

    @SerializedName("VEN_ADM_CODE")
    @Expose
    private String vSERIAL;

    @SerializedName("VEN_TYPE_NAME")
    @Expose
    private String vENTYPENAME;
    @SerializedName("VEN_ADM_RR")
    @Expose
    private String vENADMRR;
    @SerializedName("VEN_ADM_VTCC")
    @Expose
    private String vENADMVTCC;
    @SerializedName("VEN_ADM_IE")
    @Expose
    private String vENADMIE;
    @SerializedName("VEN_ADM_FIO2")
    @Expose
    private String vENADMFIO2;
    @SerializedName("VEN_ADM_PEEP")
    @Expose
    private String vENADMPEEP;
    @SerializedName("VEN_MASK_OXYGEN")
    @Expose
    private String vENADMOxgen;
    @SerializedName("VEN_ADM_DATE")
    @Expose
    private String vENADMDATE;

    /**
     * No args constructor for use in serialization
     */
    public GetVentilationForPatient() {
    }

    /**
     * @param vSERIAL
     *  @param vENADMIE
     * @param vENADMVTCC
     * @param vENADMPEEP
     * @param vENTYPENAME
     * @param vENADMFIO2
     * @param vENADMDATE
     * @param vENADMRR
     * @param vENADMOxgen
     */
    public GetVentilationForPatient(String vSERIAL, String vENTYPENAME, String vENADMRR, String vENADMVTCC,
                                    String vENADMIE, String vENADMFIO2, String vENADMPEEP, String vENADMOxgen, String vENADMDATE) {
        super();
        this.vSERIAL = vSERIAL;
        this.vENTYPENAME = vENTYPENAME;
        this.vENADMRR = vENADMRR;
        this.vENADMVTCC = vENADMVTCC;
        this.vENADMIE = vENADMIE;
        this.vENADMFIO2 = vENADMFIO2;
        this.vENADMPEEP = vENADMPEEP;
        this.vENADMDATE = vENADMDATE;
        this.vENADMOxgen = vENADMOxgen;
    }

    public String getvSERIAL() {
        return vSERIAL;
    }

    public void setvSERIAL(String vSERIAL) {
        this.vSERIAL = vSERIAL;
    }

    public String getVENTYPENAME() {
        return vENTYPENAME;
    }

    public void setVENTYPENAME(String vENTYPENAME) {
        this.vENTYPENAME = vENTYPENAME;
    }

    public String getVENADMRR() {
        return vENADMRR;
    }

    public void setVENADMRR(String vENADMRR) {
        this.vENADMRR = vENADMRR;
    }

    public String getVENADMVTCC() {
        return vENADMVTCC;
    }

    public void setVENADMVTCC(String vENADMVTCC) {
        this.vENADMVTCC = vENADMVTCC;
    }

    public String getVENADMIE() {
        return vENADMIE;
    }

    public void setVENADMIE(String vENADMIE) {
        this.vENADMIE = vENADMIE;
    }

    public String getVENADMFIO2() {
        return vENADMFIO2;
    }

    public void setVENADMFIO2(String vENADMFIO2) {
        this.vENADMFIO2 = vENADMFIO2;
    }

    public String getVENADMPEEP() {
        return vENADMPEEP;
    }

    public void setVENADMPEEP(String vENADMPEEP) {
        this.vENADMPEEP = vENADMPEEP;
    }

    public String getvENADMOxgen() {
        return vENADMOxgen;
    }

    public void setvENADMOxgen(String vENADMOxgen) {
        this.vENADMOxgen = vENADMOxgen;
    }

    public String getVENADMDATE() {
        return vENADMDATE;
    }

    public void setVENADMDATE(String vENADMDATE) {
        this.vENADMDATE = vENADMDATE;
    }

    @Override
    public int getAttributeCountable() {
        return 8;
    }

    @Override
    public Object getAttribute(int i) {
        Object attribute;
        switch (i) {
            case 0:

                attribute = getVENADMDATE() != null ? getVENADMDATE() : "";
                break;
            case 1:
                attribute = getVENTYPENAME() != null ? getVENTYPENAME() : "";
                break;
            case 2:
                attribute = getVENADMVTCC() != null ? getVENADMVTCC() : "";
                break;
            case 3:
                attribute = getVENADMRR() != null ? getVENADMRR() : "";
                break;
            case 4:
                attribute = getVENADMFIO2() != null ? getVENADMFIO2() : "";
                break;
            case 5:
                attribute = getVENADMIE() != null ? getVENADMIE() : "";
                break;
            case 6:
                attribute = getVENADMPEEP() != null ? getVENADMPEEP() : "";
                break;
            case 7:
                attribute = getvENADMOxgen() != null ? getvENADMOxgen() : "";
                break;
            default:
                attribute = "";
                break;
        }
        return attribute;
    }
}
