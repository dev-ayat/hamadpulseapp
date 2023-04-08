
package com.moh.hamadpulse.models;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moh.hamadpulse.bean.AttributeEnumerable;

public class GetVitalForAdm implements AttributeEnumerable {

    @SerializedName("V_ADM_CODE")
    @Expose
    private String vADMCODE;
    @SerializedName("V_ADM_ADMISSION_CD")
    @Expose
    private String vADMADMISSIONCD;
    @SerializedName("V_ADM_PATREC_CD")
    @Expose
    private String vADMPATRECCD;
    @SerializedName("V_ADM_CREATED_BY_CD")
    @Expose
    private String vADMCREATEDBYCD;
    @SerializedName("V_ADM_DATE")
    @Expose
    private String vADMDATE;
    @SerializedName("TEMP_C")
    @Expose
    private String tEMPC;
    @SerializedName("SAO2")
    @Expose
    private String sAO2;
    @SerializedName("RR_MIN")
    @Expose
    private String rRMIN;
    @SerializedName("EtCO2")
    @Expose
    private String etCO2;
    @SerializedName("HeartRate")
    @Expose
    private String heartRate;
    @SerializedName("NIBP")
    @Expose
    private String nIBP;
    @SerializedName("NIBP_LOWER")
    @Expose
    private String NIBP_LOWER;
    @SerializedName("UrineOut")
    @Expose
    private Object urineOut;
    @SerializedName("CVP")
    @Expose
    private Object cVP;
    @SerializedName("IBP")
    @Expose
    private Object iBP;
    @SerializedName("VITAL_NOTE")
    @Expose
    private String VITAL_NOTE;
    @SerializedName("DIFF_MIN")
    @Expose
    private int DIFF_MIN;

    @SerializedName("DOC_NAME")
    @Expose
    private String DOC_NAME;

    @SerializedName("WEIGHT")
    @Expose
    private String WEIGHT;

    @SerializedName("HEIGHT")
    @Expose
    private String HEIGHT;

    @SerializedName("BMI")
    @Expose
    private String BMI;

    @SerializedName("HC")
    @Expose
    private String HC;

    /**
     * No args constructor for use in serialization
     */
    public GetVitalForAdm() {
    }

    /**
     * @param vADMCODE
     * @param vADMDATE
     * @param sAO2
     * @param vADMADMISSIONCD
     * @param tEMPC
     * @param etCO2
     * @param urineOut
     * @param heartRate
     * @param iBP
     * @param vADMCREATEDBYCD
     * @param nIBP
     * @param vADMPATRECCD
     * @param cVP
     * @param rRMIN
     * @param DOC_NAME
     */
    public GetVitalForAdm(String vADMCODE, String vADMADMISSIONCD, String vADMPATRECCD,
                          String vADMCREATEDBYCD, String vADMDATE,
                          String tEMPC, String sAO2, String rRMIN,
                          String etCO2, String heartRate, String nIBP, String DOC_NAME,
                          Object urineOut, Object cVP, Object iBP,
                          String WEIGHT, String HEIGHT, String BMI, String HC) {
        super();
        this.vADMCODE = vADMCODE;
        this.vADMADMISSIONCD = vADMADMISSIONCD;
        this.vADMPATRECCD = vADMPATRECCD;
        this.vADMCREATEDBYCD = vADMCREATEDBYCD;
        this.vADMDATE = vADMDATE;
        this.tEMPC = tEMPC;
        this.sAO2 = sAO2;
        this.rRMIN = rRMIN;
        this.etCO2 = etCO2;
        this.heartRate = heartRate;
        this.nIBP = nIBP;
        this.urineOut = urineOut;
        this.cVP = cVP;
        this.iBP = iBP;
        this.DOC_NAME = DOC_NAME;
        this.WEIGHT = WEIGHT;
        this.HEIGHT = HEIGHT;
        this.BMI = BMI;
        this.HC = HC;
    }

    public String getVADMCODE() {
        return vADMCODE;
    }

    public void setVADMCODE(String vADMCODE) {
        this.vADMCODE = vADMCODE;
    }

    public String getVADMADMISSIONCD() {
        return vADMADMISSIONCD;
    }

    public void setVADMADMISSIONCD(String vADMADMISSIONCD) {
        this.vADMADMISSIONCD = vADMADMISSIONCD;
    }

    public String getVADMPATRECCD() {
        return vADMPATRECCD;
    }

    public void setVADMPATRECCD(String vADMPATRECCD) {
        this.vADMPATRECCD = vADMPATRECCD;
    }

    public String getVADMCREATEDBYCD() {
        return vADMCREATEDBYCD;
    }

    public void setVADMCREATEDBYCD(String vADMCREATEDBYCD) {
        this.vADMCREATEDBYCD = vADMCREATEDBYCD;
    }

    public String getVADMDATE() {
        return vADMDATE;
    }

    public void setVADMDATE(String vADMDATE) {
        this.vADMDATE = vADMDATE;
    }

    public String getTEMPC() {
        return tEMPC;
    }

    public void setTEMPC(String tEMPC) {
        this.tEMPC = tEMPC;
    }

    public String getSAO2() {
        return sAO2;
    }

    public void setSAO2(String sAO2) {
        this.sAO2 = sAO2;
    }

    public String  getRRMIN() {
        if(rRMIN!=null)
            return rRMIN;
        else
            return "";
    }

    public void setRRMIN(String rRMIN) {
        this.rRMIN = rRMIN;
    }

    public String getEtCO2() {
        return etCO2;
    }

    public void setEtCO2(String etCO2) {
        this.etCO2 = etCO2;
    }

    public String getHeartRate() {
        if (heartRate != null)
            return heartRate;
        else
            return "";
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getNIBP() {
        if (nIBP != null)
            return nIBP;
        else
            return "";
    }

    public void setNIBP(String nIBP) {
        this.nIBP = nIBP;
    }

    public String getNIBP_LOWER() {
        if (NIBP_LOWER != null)
            return NIBP_LOWER;
        else
            return "";
    }

    public void setNIBP_LOWER(String NIBP_LOWER) {
        this.NIBP_LOWER = NIBP_LOWER;
    }

    public Object getUrineOut() {
        return urineOut;
    }

    public void setUrineOut(Object urineOut) {
        this.urineOut = urineOut;
    }

    public Object getCVP() {
        return cVP;
    }

    public void setCVP(Object cVP) {
        this.cVP = cVP;
    }

    public Object getIBP() {
        return iBP;
    }

    public void setIBP(Object iBP) {
        this.iBP = iBP;
    }

    public String getVITAL_NOTE() {
        return VITAL_NOTE;
    }

    public void setVITAL_NOTE(String VITAL_NOTE) {
        this.VITAL_NOTE = VITAL_NOTE;
    }

    public int getDIFF_MIN() {
        return DIFF_MIN;
    }

    public void setDIFF_MIN(int DIFF_MIN) {
        this.DIFF_MIN = DIFF_MIN;
    }

    public String getDOC_NAME() {
        return DOC_NAME;
    }

    public void setDOC_NAME(String DOC_NAME) {
        this.DOC_NAME = DOC_NAME;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(String HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getHC() {
        return HC;
    }

    public void setHC(String HC) {
        this.HC = HC;
    }

    @Override
    public int getAttributeCountable() {
        return 16;
    }

    public static class Iyad {
        String val;
        Integer color;

        public Iyad() {
        }

        public Iyad(String val, Integer color) {
            this.val = val;
            this.color = color;
        }

        public String getVal() {
            if (val != null)
                return val;
            else
                return "";
        }

        public void setVal(String val) {
            this.val = val;
        }

        public Integer getColor() {
            if(color!=null)
                return color;
            else
                return Color.BLACK;
        }

        public void setColor(Integer color) {
            this.color = color;
        }
    }

    Iyad mIyad;
    @Override
    public Object getAttribute(int i) {
        Object attribute;
        switch (i) {
            case 0:

                attribute = getVADMDATE() != null ? getVADMDATE() : "";
                break;
            case 1:

//                attribute = getVITAL_NOTE() != null ? getVITAL_NOTE() : "";
                attribute = getVITAL_NOTE() != null ?  getVITAL_NOTE().trim().equals("Note")
                        ?"Note":"Tap To View": "";
                break;
            case 2:
                mIyad = new Iyad();
                if(getTEMPC() != null) {
                    mIyad.setVal(getTEMPC());
                    try {
                        if (Integer.parseInt(getTEMPC()) > 40) {
                            mIyad.setColor(Color.RED);
                            Log.e("ERROR","RED");
                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("ERROR",e.getMessage());
                        e.printStackTrace();
                    }
                }
                attribute =mIyad;
                break;
            case 3:
                attribute = getSAO2() != null ? getSAO2() : "";
                break;
            case 4:
                mIyad = new Iyad();
                if(getRRMIN() != null) {
                    mIyad.setVal(getRRMIN());
                    try {
                        if (Integer.parseInt(getRRMIN()) > 30) {
                            mIyad.setColor(Color.RED);
                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("ERROR",e.getMessage());
                        e.printStackTrace();
                    }
                }
                attribute = mIyad;
                break;
            case 5:
                attribute = getEtCO2() != null ? getEtCO2() : "";
                break;
            case 6:
                mIyad = new Iyad();
                if(getHeartRate() != null) {
                    mIyad.setVal(getHeartRate());
                    try {
                        if (Integer.parseInt(getHeartRate()) > 100 || Integer.parseInt(getHeartRate()) < 60) {
                            mIyad.setColor(Color.RED);
                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("ERROR",e.getMessage());
                        e.printStackTrace();
                    }
                }
                attribute = mIyad;
                break;
            case 7:
                mIyad = new Iyad();
                if(getNIBP() != null) {
                    mIyad.setVal(getNIBP()+"/"+getNIBP_LOWER());
                    try {
                        if (Integer.parseInt(getNIBP()) > 150 || Integer.parseInt(getNIBP()) < 100) {
                            mIyad.setColor(Color.RED);
                        }
                        if (Integer.parseInt(getNIBP_LOWER()) > 90 || Integer.parseInt(getNIBP_LOWER()) < 50) {
                            mIyad.setColor(Color.RED);
                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("ERROR",e.getMessage());
                        e.printStackTrace();
                    }
                }
                attribute = mIyad;
                break;
            case 8:
                attribute = getUrineOut() != null ? getUrineOut() : "";
                break;
            case 9:
                attribute = getCVP() != null ? getCVP() : "";
                break;
            case 10:
                attribute = getIBP() != null ? getIBP() : "";
                break;
            case 11:
                attribute = getDOC_NAME() != null ? getDOC_NAME() : "";
                break;
            case 12:
                attribute = getWEIGHT() != null ? getWEIGHT() : "";
                break;
            case 13:
                attribute = getHEIGHT() != null ? getHEIGHT() : "";
                break;
            case 14:
                attribute = getBMI() != null ? getBMI() : "";
                break;
            case 15:
                attribute = getHC() != null ? getHC() : "";
                break;
            default:
                attribute = "";
                break;
        }
        return attribute;
    }
}
