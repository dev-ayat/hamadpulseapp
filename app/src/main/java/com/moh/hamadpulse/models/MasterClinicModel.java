package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class MasterClinicModel {
    @SerializedName("LOC_MASTER_CD")
    String clinicCD;
    @SerializedName("LOC_MASTER_AR")
    String clinicName;
    //    @SerializedName("LOC_MASTER_DEPT_CD")
    @SerializedName("LOC_MASTER_DEPT_CD")
    String clinicDeptCD;
    @SerializedName("HOS_NO")
    String HOS_NO;
    @SerializedName("IS_EXIST")
    String Is_Exist;
    @SerializedName("LOC_WITHOUT_AGE_CON")
    String LOC_WITHOUT_AGE_CON;

    public String getClinicCD() {
        return clinicCD;
    }

    public void setClinicCD(String clinicCD) {
        this.clinicCD = clinicCD;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicDeptCD() {
        return clinicDeptCD;
    }

    public void setClinicDeptCD(String clinicDeptCD) {
        this.clinicDeptCD = clinicDeptCD;
    }

    public String getHOS_NO() {
        return HOS_NO;
    }

    public void setHOS_NO(String HOS_NO) {
        this.HOS_NO = HOS_NO;
    }

    public String getIs_Exist() {
        return Is_Exist;
    }

    public void setIs_Exist(String is_Exist) {
        Is_Exist = is_Exist;
    }

    public String getLOC_WITHOUT_AGE_CON() {
        return LOC_WITHOUT_AGE_CON;
    }

    public void setLOC_WITHOUT_AGE_CON(String LOC_WITHOUT_AGE_CON) {
        this.LOC_WITHOUT_AGE_CON = LOC_WITHOUT_AGE_CON;
    }
}
