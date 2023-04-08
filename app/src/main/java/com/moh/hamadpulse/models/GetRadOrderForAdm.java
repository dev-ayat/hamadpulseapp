
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadOrderForAdm {

    @SerializedName("ORDER_CODE")
    @Expose
    private String oRDERCODE;
    @SerializedName("ORDER_DATE")
    @Expose
    private String oRDERDATE;
    @SerializedName("ORDER_PATREC_CD")
    @Expose
    private String oRDERPATRECCD;
    @SerializedName("ORDER_PATIENT_TYPE_CD")
    @Expose
    private String oRDERPATIENTTYPECD;
    @SerializedName("ORDER_STATUS_CD")
    @Expose
    private String oRDERSTATUSCD;
    @SerializedName("ORDER_DEP_CD")
    @Expose
    private String oRDERDEPCD;
    @SerializedName("ORDER_LOC_CD")
    @Expose
    private String oRDERLOCCD;
    @SerializedName("ORDER_ENTER_DOC_CD")
    @Expose
    private String oRDERENTERDOCCD;
    @SerializedName("ORDER_CREATED_BY")
    @Expose
    private String oRDERCREATEDBY;
    @SerializedName("ORDER_CREATED_ON")
    @Expose
    private String oRDERCREATEDON;
    @SerializedName("ORDER_SERVICE_CD")
    @Expose
    private String oRDERSERVICECD;
    @SerializedName("ORDER_IS_DONE")
    @Expose
    private String oRDERISDONE;
    @SerializedName("ORDER_RES_PERIOD")
    @Expose
    private Object oRDERRESPERIOD;
    @SerializedName("ORDER_DOCTOR_COMMENT")
    @Expose
    private Object oRDERDOCTORCOMMENT;
    @SerializedName("ORDER_RESON")
    @Expose
    private String oRDERRESON;
    @SerializedName("ORGAN_D_CODE")
    @Expose
    private String oRGANDCODE;
    @SerializedName("ORGAN_D_NAME_AR")
    @Expose
    private String oRGANDNAMEAR;
    @SerializedName("ORGAN_D_NAME_EN")
    @Expose
    private String oRGANDNAMEEN;
    @SerializedName("ORGAN_CD")
    @Expose
    private String oRGANCD;
    @SerializedName("SERVICE_CODE")
    @Expose
    private String sERVICECODE;
    @SerializedName("SERVICE_NAME_AR")
    @Expose
    private String sERVICENAMEAR;
    @SerializedName("SERVICE_NAME_EN")
    @Expose
    private String sERVICENAMEEN;
    @SerializedName("C_RAD_POSITION_CODE")
    @Expose
    private String cRADPOSITIONCODE;
    @SerializedName("C_RAD_POSITION_NAME_EN")
    @Expose
    private String cRADPOSITIONNAMEEN;
    @SerializedName("ORGAN_CODE")
    @Expose
    private String oRGANCODE;
    @SerializedName("ORGAN_NAME_AR")
    @Expose
    private String oRGANNAMEAR;
    @SerializedName("ORGAN_NAME_EN")
    @Expose
    private String oRGANNAMEEN;
    @SerializedName("ORDERD_CODE")
    @Expose
    private String oRDERDCODE;

    private String EMP_FIRST_NAME_AR;
    private String EMP_FATHER_NAME_AR;
    private String EMP_GRANDFATHER_NAME_AR;
    private String EMP_LAST_NAME_AR;
    private int DIFF_MIN;
    private String CREATED_BY;
    private String CREATED_ON;
    private String ORDERD_ORGAN_NOTE;

    /**
     * No args constructor for use in serialization
     */
    public GetRadOrderForAdm() {
    }

    /**
     * @param oRGANCODE
     * @param oRGANNAMEAR
     * @param oRDERRESPERIOD
     * @param oRDERPATRECCD
     * @param oRDERENTERDOCCD
     * @param cRADPOSITIONNAMEEN
     * @param oRGANNAMEEN
     * @param sERVICENAMEAR
     * @param oRDERSTATUSCD
     * @param oRDERLOCCD
     * @param oRGANDNAMEAR
     * @param sERVICENAMEEN
     * @param oRGANDNAMEEN
     * @param sERVICECODE
     * @param oRDERRESON
     * @param oRGANDCODE
     * @param oRDERCREATEDON
     * @param oRDERPATIENTTYPECD
     * @param oRDERSERVICECD
     * @param oRDERDOCTORCOMMENT
     * @param oRDERDATE
     * @param oRDERISDONE
     * @param oRDERCODE
     * @param oRDERCREATEDBY
     * @param oRDERDEPCD
     * @param cRADPOSITIONCODE
     * @param oRGANCD
     * @param oRDERDCODE
     */
    public GetRadOrderForAdm(String oRDERCODE, String oRDERDATE, String oRDERPATRECCD,
                             String oRDERPATIENTTYPECD, String oRDERSTATUSCD, String oRDERDEPCD,
                             String oRDERLOCCD, String oRDERENTERDOCCD, String oRDERCREATEDBY,
                             String oRDERCREATEDON, String oRDERSERVICECD, String oRDERISDONE,
                             Object oRDERRESPERIOD, Object oRDERDOCTORCOMMENT, String oRDERRESON,
                             String oRGANDCODE, String oRGANDNAMEAR, String oRGANDNAMEEN,
                             String oRGANCD, String sERVICECODE, String sERVICENAMEAR,
                             String sERVICENAMEEN, String cRADPOSITIONCODE, String cRADPOSITIONNAMEEN,
                             String oRGANCODE, String oRGANNAMEAR, String oRGANNAMEEN, String oRDERDCODE) {
        super();
        this.oRDERCODE = oRDERCODE;
        this.oRDERDATE = oRDERDATE;
        this.oRDERPATRECCD = oRDERPATRECCD;
        this.oRDERPATIENTTYPECD = oRDERPATIENTTYPECD;
        this.oRDERSTATUSCD = oRDERSTATUSCD;
        this.oRDERDEPCD = oRDERDEPCD;
        this.oRDERLOCCD = oRDERLOCCD;
        this.oRDERENTERDOCCD = oRDERENTERDOCCD;
        this.oRDERCREATEDBY = oRDERCREATEDBY;
        this.oRDERCREATEDON = oRDERCREATEDON;
        this.oRDERSERVICECD = oRDERSERVICECD;
        this.oRDERISDONE = oRDERISDONE;
        this.oRDERRESPERIOD = oRDERRESPERIOD;
        this.oRDERDOCTORCOMMENT = oRDERDOCTORCOMMENT;
        this.oRDERRESON = oRDERRESON;
        this.oRGANDCODE = oRGANDCODE;
        this.oRGANDNAMEAR = oRGANDNAMEAR;
        this.oRGANDNAMEEN = oRGANDNAMEEN;
        this.oRGANCD = oRGANCD;
        this.sERVICECODE = sERVICECODE;
        this.sERVICENAMEAR = sERVICENAMEAR;
        this.sERVICENAMEEN = sERVICENAMEEN;
        this.cRADPOSITIONCODE = cRADPOSITIONCODE;
        this.cRADPOSITIONNAMEEN = cRADPOSITIONNAMEEN;
        this.oRGANCODE = oRGANCODE;
        this.oRGANNAMEAR = oRGANNAMEAR;
        this.oRGANNAMEEN = oRGANNAMEEN;
        this.oRDERDCODE = oRDERDCODE;
    }

    public String getORDERCODE() {
        return oRDERCODE;
    }

    public void setORDERCODE(String oRDERCODE) {
        this.oRDERCODE = oRDERCODE;
    }

    public String getORDERDATE() {
        return oRDERDATE;
    }

    public void setORDERDATE(String oRDERDATE) {
        this.oRDERDATE = oRDERDATE;
    }

    public String getORDERPATRECCD() {
        return oRDERPATRECCD;
    }

    public void setORDERPATRECCD(String oRDERPATRECCD) {
        this.oRDERPATRECCD = oRDERPATRECCD;
    }

    public String getORDERPATIENTTYPECD() {
        return oRDERPATIENTTYPECD;
    }

    public void setORDERPATIENTTYPECD(String oRDERPATIENTTYPECD) {
        this.oRDERPATIENTTYPECD = oRDERPATIENTTYPECD;
    }

    public String getORDERSTATUSCD() {
        return oRDERSTATUSCD;
    }

    public void setORDERSTATUSCD(String oRDERSTATUSCD) {
        this.oRDERSTATUSCD = oRDERSTATUSCD;
    }

    public String getORDERDEPCD() {
        return oRDERDEPCD;
    }

    public void setORDERDEPCD(String oRDERDEPCD) {
        this.oRDERDEPCD = oRDERDEPCD;
    }

    public String getORDERLOCCD() {
        return oRDERLOCCD;
    }

    public void setORDERLOCCD(String oRDERLOCCD) {
        this.oRDERLOCCD = oRDERLOCCD;
    }

    public String getORDERENTERDOCCD() {
        return oRDERENTERDOCCD;
    }

    public void setORDERENTERDOCCD(String oRDERENTERDOCCD) {
        this.oRDERENTERDOCCD = oRDERENTERDOCCD;
    }

    public String getORDERCREATEDBY() {
        return oRDERCREATEDBY;
    }

    public void setORDERCREATEDBY(String oRDERCREATEDBY) {
        this.oRDERCREATEDBY = oRDERCREATEDBY;
    }

    public String getORDERCREATEDON() {
        return oRDERCREATEDON;
    }

    public void setORDERCREATEDON(String oRDERCREATEDON) {
        this.oRDERCREATEDON = oRDERCREATEDON;
    }

    public String getORDERSERVICECD() {
        return oRDERSERVICECD;
    }

    public void setORDERSERVICECD(String oRDERSERVICECD) {
        this.oRDERSERVICECD = oRDERSERVICECD;
    }

    public String getORDERISDONE() {
        return oRDERISDONE;
    }

    public void setORDERISDONE(String oRDERISDONE) {
        this.oRDERISDONE = oRDERISDONE;
    }

    public Object getORDERRESPERIOD() {
        return oRDERRESPERIOD;
    }

    public void setORDERRESPERIOD(Object oRDERRESPERIOD) {
        this.oRDERRESPERIOD = oRDERRESPERIOD;
    }

    public Object getORDERDOCTORCOMMENT() {
        return oRDERDOCTORCOMMENT;
    }

    public void setORDERDOCTORCOMMENT(Object oRDERDOCTORCOMMENT) {
        this.oRDERDOCTORCOMMENT = oRDERDOCTORCOMMENT;
    }

    public String getORDERRESON() {
        return oRDERRESON;
    }

    public void setORDERRESON(String oRDERRESON) {
        this.oRDERRESON = oRDERRESON;
    }

    public String getORGANDCODE() {
        return oRGANDCODE;
    }

    public void setORGANDCODE(String oRGANDCODE) {
        this.oRGANDCODE = oRGANDCODE;
    }

    public String getORGANDNAMEAR() {
        return oRGANDNAMEAR;
    }

    public void setORGANDNAMEAR(String oRGANDNAMEAR) {
        this.oRGANDNAMEAR = oRGANDNAMEAR;
    }

    public String getORGANDNAMEEN() {
        return oRGANDNAMEEN;
    }

    public void setORGANDNAMEEN(String oRGANDNAMEEN) {
        this.oRGANDNAMEEN = oRGANDNAMEEN;
    }

    public String getORGANCD() {
        return oRGANCD;
    }

    public void setORGANCD(String oRGANCD) {
        this.oRGANCD = oRGANCD;
    }

    public String getSERVICECODE() {
        return sERVICECODE;
    }

    public void setSERVICECODE(String sERVICECODE) {
        this.sERVICECODE = sERVICECODE;
    }

    public String getSERVICENAMEAR() {
        return sERVICENAMEAR;
    }

    public void setSERVICENAMEAR(String sERVICENAMEAR) {
        this.sERVICENAMEAR = sERVICENAMEAR;
    }

    public String getSERVICENAMEEN() {
        return sERVICENAMEEN;
    }

    public void setSERVICENAMEEN(String sERVICENAMEEN) {
        this.sERVICENAMEEN = sERVICENAMEEN;
    }

    public String getCRADPOSITIONCODE() {
        return cRADPOSITIONCODE;
    }

    public void setCRADPOSITIONCODE(String cRADPOSITIONCODE) {
        this.cRADPOSITIONCODE = cRADPOSITIONCODE;
    }

    public String getCRADPOSITIONNAMEEN() {
        return cRADPOSITIONNAMEEN;
    }

    public void setCRADPOSITIONNAMEEN(String cRADPOSITIONNAMEEN) {
        this.cRADPOSITIONNAMEEN = cRADPOSITIONNAMEEN;
    }

    public String getORGANCODE() {
        return oRGANCODE;
    }

    public void setORGANCODE(String oRGANCODE) {
        this.oRGANCODE = oRGANCODE;
    }

    public String getORGANNAMEAR() {
        return oRGANNAMEAR;
    }

    public void setORGANNAMEAR(String oRGANNAMEAR) {
        this.oRGANNAMEAR = oRGANNAMEAR;
    }

    public String getORGANNAMEEN() {
        return oRGANNAMEEN;
    }

    public void setORGANNAMEEN(String oRGANNAMEEN) {
        this.oRGANNAMEEN = oRGANNAMEEN;
    }

    public String getoRDERDCODE() {
        return oRDERDCODE;
    }

    public void setoRDERDCODE(String oRDERDCODE) {
        this.oRDERDCODE = oRDERDCODE;
    }

    public String getEMP_FIRST_NAME_AR() {
        return EMP_FIRST_NAME_AR;
    }

    public void setEMP_FIRST_NAME_AR(String EMP_FIRST_NAME_AR) {
        this.EMP_FIRST_NAME_AR = EMP_FIRST_NAME_AR;
    }

    public String getEMP_FATHER_NAME_AR() {
        return EMP_FATHER_NAME_AR;
    }

    public void setEMP_FATHER_NAME_AR(String EMP_FATHER_NAME_AR) {
        this.EMP_FATHER_NAME_AR = EMP_FATHER_NAME_AR;
    }

    public String getEMP_GRANDFATHER_NAME_AR() {
        return EMP_GRANDFATHER_NAME_AR;
    }

    public void setEMP_GRANDFATHER_NAME_AR(String EMP_GRANDFATHER_NAME_AR) {
        this.EMP_GRANDFATHER_NAME_AR = EMP_GRANDFATHER_NAME_AR;
    }

    public String getEMP_LAST_NAME_AR() {
        return EMP_LAST_NAME_AR;
    }

    public void setEMP_LAST_NAME_AR(String EMP_LAST_NAME_AR) {
        this.EMP_LAST_NAME_AR = EMP_LAST_NAME_AR;
    }

    public int getDIFF_MIN() {
        return DIFF_MIN;
    }

    public void setDIFF_MIN(int DIFF_MIN) {
        this.DIFF_MIN = DIFF_MIN;
    }

    public String getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(String CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public String getCREATED_ON() {
        return CREATED_ON;
    }

    public void setCREATED_ON(String CREATED_ON) {
        this.CREATED_ON = CREATED_ON;
    }

    public String getORDERD_ORGAN_NOTE() {
        return ORDERD_ORGAN_NOTE;
    }

    public void setORDERD_ORGAN_NOTE(String ORDERD_ORGAN_NOTE) {
        this.ORDERD_ORGAN_NOTE = ORDERD_ORGAN_NOTE;
    }
}
