package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InOutMasterModel {

    @SerializedName("TOTALS")

    private TotalsModel totals;
    @SerializedName("INP_IN_OUT_TAKE_CD")
    private String inpInOutTakeCd;
    @SerializedName("INP_IN_OUT_ADM_CD")
    private String inpInOutAdmCd;
    @SerializedName("INP_IN_OUT_PATRIC_CD")
    private String inpInOutPatricCd;
    @SerializedName("INP_IN_OUT_MASTER_CREATED_ON")
    private String inpInOutCreatedOn;
    @SerializedName("ORCER_SOURCE")
    private String orcerSource;
    @SerializedName("HOS_NO")
    private String hosNo;
    @SerializedName("IN_OUT_DETAILS_CUR")
    private ArrayList<InOutDetailsCurModel> inOutDetailsCur;
    public String getInpInOutTakeCd() {
        return inpInOutTakeCd;
    }

    public void setInpInOutTakeCd(String inpInOutTakeCd) {
        this.inpInOutTakeCd = inpInOutTakeCd;
    }

    public TotalsModel getTotals() {
        return totals;
    }

    public void setTotals(TotalsModel totals) {
        this.totals = totals;
    }

    public String getInpInOutAdmCd() {
        return inpInOutAdmCd;
    }

    public void setInpInOutAdmCd(String inpInOutAdmCd) {
        this.inpInOutAdmCd = inpInOutAdmCd;
    }

    public String getInpInOutPatricCd() {
        return inpInOutPatricCd;
    }

    public void setInpInOutPatricCd(String inpInOutPatricCd) {
        this.inpInOutPatricCd = inpInOutPatricCd;
    }

    public String getInpInOutCreatedOn() {
        return inpInOutCreatedOn;
    }

    public void setInpInOutCreatedOn(String inpInOutCreatedOn) {
        this.inpInOutCreatedOn = inpInOutCreatedOn;
    }

    public String getOrcerSource() {
        return orcerSource;
    }

    public void setOrcerSource(String orcerSource) {
        this.orcerSource = orcerSource;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public ArrayList<InOutDetailsCurModel> getInOutDetailsCur() {
        return inOutDetailsCur;
    }

    public void setInOutDetailsCur(ArrayList<InOutDetailsCurModel> inOutDetailsCur) {
        this.inOutDetailsCur = inOutDetailsCur;
    }

}

