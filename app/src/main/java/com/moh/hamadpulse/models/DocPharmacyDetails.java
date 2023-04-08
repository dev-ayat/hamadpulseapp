package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DocPharmacyDetails {

    @SerializedName("DOSE_NAME")
    private String dOSENAME;

    @SerializedName("INP_ORDER_CD")
    private String iNPORDERCD;

    @SerializedName("INP_MED_CD")
    private String iNPMEDCD;

    @SerializedName("DOS_G_NAME")
    private String dOSGNAME;

    @SerializedName("UNIT_CODE")
    private String uNITCODE;

	@SerializedName("INP_DETAIL_CODE")
	private String iNPDETAILCODE;

	@SerializedName("HOS_NO")
	private String hOSNO;

	@SerializedName("ITEM_CODE")
	private String iTEMCODE;

	@SerializedName("INP_INTERVAL")
	private String iNPINTERVAL;

	@SerializedName("INP_UPDATED_ON")
	private Object iNPUPDATEDON;

	@SerializedName("ITEM_NAME")
	private String iTEMNAME;

	@SerializedName("INP_UPDATED_BY_CD")
	private Object iNPUPDATEDBYCD;

	@SerializedName("INP_STATUS_CD")
	private String iNPSTATUSCD;

	@SerializedName("INP_INSERT_ON")
	private Object iNPINSERTON;

	@SerializedName("UNIT_NAME")
	private String uNITNAME;

	@SerializedName("INP_INSERT_BY_CD")
	private Object iNPINSERTBYCD;

    @SerializedName("INP_DOSE_CD")
    private String iNPDOSECD;

    @SerializedName("INP_WANTED_AMOUNT")
    private String iNPWANTEDAMOUNT;

    @SerializedName("INP_DOSE_GIVING_CD")
    private String iNPDOSEGIVINGCD;

    @SerializedName("NOTE")
    private String iNPNOTE;


	@SerializedName("DISCHARGED_FROM_NURSING")
	@Expose
	private String dischargedFromNursing;
	@SerializedName("PHARM_ORDER_CD")
	@Expose
	private String pharmOrderCd;
	@SerializedName("INPATIENT_ORDER_DONE")
	@Expose
	private String inpatientOrderDone;
	private String dose_value;

	public String getDose_value() {
		return dose_value;
	}

	public void setDose_value(String dose_value) {
		this.dose_value = dose_value;
	}

	private Boolean isSelected = new Boolean(false);

	public Boolean isSelected() {
		return isSelected;
	}

	public void setSelected(Boolean selected) {
		isSelected = selected;
	}

	public String getPharmOrderCd() {
        return pharmOrderCd;
    }

    public void setPharmOrderCd(String pharmOrderCd) {
        this.pharmOrderCd = pharmOrderCd;
    }

    public String getInpatientOrderDone() {
        return inpatientOrderDone;
    }

    public void setInpatientOrderDone(String inpatientOrderDone) {
        this.inpatientOrderDone = inpatientOrderDone;
    }

    public String getDischargedFromNursing() {
        return dischargedFromNursing;
    }

    public void setDischargedFromNursing(String dischargedFromNursing) {
        this.dischargedFromNursing = dischargedFromNursing;
    }

    public boolean isFromPharmacy() {
        if (dischargedFromNursing == null || dischargedFromNursing.equals("0"))
            return inpatientOrderDone != null && inpatientOrderDone.equals("1")
                    &&
                    pharmOrderCd != null;
        else
            return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocPharmacyDetails)) return false;
        DocPharmacyDetails that = (DocPharmacyDetails) o;
        return Objects.equals(iNPMEDCD, that.iNPMEDCD);
    }


    public String getDOSENAME() {
        return dOSENAME;
    }

    public String getINPORDERCD() {
        return iNPORDERCD;
    }

    public String getINPMEDCD() {
        return iNPMEDCD;
    }

	public String getDOSGNAME(){
		return dOSGNAME;
	}

	public String getUNITCODE(){
		return uNITCODE;
	}

	public String getINPDETAILCODE(){
		return iNPDETAILCODE;
	}

	public String getHOSNO(){
		return hOSNO;
	}

	public String getITEMCODE(){
		return iTEMCODE;
	}

	public String getINPINTERVAL(){
		return iNPINTERVAL;
	}

	public Object getINPUPDATEDON(){
		return iNPUPDATEDON;
	}

	public String getITEMNAME(){
		return iTEMNAME;
	}

	public Object getINPUPDATEDBYCD(){
		return iNPUPDATEDBYCD;
	}

	public String getINPSTATUSCD(){
		return iNPSTATUSCD;
	}

	public Object getINPINSERTON(){
		return iNPINSERTON;
	}

	public String getUNITNAME(){
		return uNITNAME;
	}

	public Object getINPINSERTBYCD(){
		return iNPINSERTBYCD;
	}

	public String getINPDOSECD(){
		return iNPDOSECD;
	}

	public String getINPWANTEDAMOUNT(){
		return iNPWANTEDAMOUNT;
	}

	public String getINPDOSEGIVINGCD(){
		return iNPDOSEGIVINGCD;
	}

	public void setdOSENAME(String dOSENAME) {
		this.dOSENAME = dOSENAME;
	}

	public void setiNPORDERCD(String iNPORDERCD) {
		this.iNPORDERCD = iNPORDERCD;
	}

	public void setiNPMEDCD(String iNPMEDCD) {
		this.iNPMEDCD = iNPMEDCD;
	}

	public void setdOSGNAME(String dOSGNAME) {
		this.dOSGNAME = dOSGNAME;
	}

	public void setuNITCODE(String uNITCODE) {
		this.uNITCODE = uNITCODE;
	}

	public void setiNPDETAILCODE(String iNPDETAILCODE) {
		this.iNPDETAILCODE = iNPDETAILCODE;
	}

	public void sethOSNO(String hOSNO) {
		this.hOSNO = hOSNO;
	}

	public void setiTEMCODE(String iTEMCODE) {
		this.iTEMCODE = iTEMCODE;
	}

	public void setiNPINTERVAL(String iNPINTERVAL) {
		this.iNPINTERVAL = iNPINTERVAL;
	}

	public void setiNPUPDATEDON(Object iNPUPDATEDON) {
		this.iNPUPDATEDON = iNPUPDATEDON;
	}

	public void setiTEMNAME(String iTEMNAME) {
		this.iTEMNAME = iTEMNAME;
	}

	public void setiNPUPDATEDBYCD(Object iNPUPDATEDBYCD) {
		this.iNPUPDATEDBYCD = iNPUPDATEDBYCD;
	}

	public void setiNPSTATUSCD(String iNPSTATUSCD) {
		this.iNPSTATUSCD = iNPSTATUSCD;
	}

	public void setiNPINSERTON(Object iNPINSERTON) {
		this.iNPINSERTON = iNPINSERTON;
	}

	public void setuNITNAME(String uNITNAME) {
		this.uNITNAME = uNITNAME;
	}

	public void setiNPINSERTBYCD(Object iNPINSERTBYCD) {
		this.iNPINSERTBYCD = iNPINSERTBYCD;
	}

	public void setiNPDOSECD(String iNPDOSECD) {
        this.iNPDOSECD = iNPDOSECD;
    }

    public void setiNPWANTEDAMOUNT(String iNPWANTEDAMOUNT) {
        this.iNPWANTEDAMOUNT = iNPWANTEDAMOUNT;
    }

    public void setiNPDOSEGIVINGCD(String iNPDOSEGIVINGCD) {
        this.iNPDOSEGIVINGCD = iNPDOSEGIVINGCD;
    }

    public String getiNPNOTE() {
        return iNPNOTE;
    }

    public void setiNPNOTE(String iNPNOTE) {
        this.iNPNOTE = iNPNOTE;
    }
}