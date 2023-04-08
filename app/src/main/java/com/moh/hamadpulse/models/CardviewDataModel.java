package com.moh.hamadpulse.models;

import java.io.Serializable;

public class CardviewDataModel implements Serializable {
    // "HOS_NO": "1",
    //   "H_NAME_AR": "مجمع الشفاء الطبي"
    //   "MRP_DOB": "12/12/1974",
    // "MRP_SEX_CD": "1",
    //    "SEX_NAME_AR": "ذكر",
    private String patname, docname, roomname, bedname, ptmrpid
            , HOS_NO, H_NAME_AR, MRP_SEX_CD, SEX_NAME_AR, MRP_DOB, HOS_PERMISSION, LOC_CODE, MRP_MOBILE_NO;
//            ,ICD_CD,ICD_NAME_EN;
    private int patid, admcd;
    private String indate, LOC_NAME_AR, SUP_LOC_NAME;
    String EXAM_SPIN_CD, PATIENT_STATUS_NAME_EN, RESULT_DATE, TEST_RESULT,ADMISSION_STATUS;


    public CardviewDataModel() {
    }

    public CardviewDataModel(String patname, String docname, String roomname, String bedname, String ptmrpid,
                             int patid, int admcd, String indate, String HOS_NO,
                             String h_NAME_AR, String MRP_SEX_CD, String SEX_NAME_AR, String MRP_DOB, String HOS_PERMISSION, String LOC_CODE, String MRP_MOBILE_NO,
                             String LOC_NAME_AR) {
        this.patname = patname;
        this.docname = docname;
        this.roomname = roomname;
        this.bedname = bedname;
        this.ptmrpid = ptmrpid;
        this.HOS_NO = HOS_NO;
        this.H_NAME_AR = h_NAME_AR;
        this.MRP_SEX_CD = MRP_SEX_CD;
        this.SEX_NAME_AR = SEX_NAME_AR;
        this.MRP_DOB = MRP_DOB;
        this.patid = patid;
        this.admcd = admcd;
        this.indate = indate;
        this.HOS_PERMISSION = HOS_PERMISSION;
        this.LOC_CODE = LOC_CODE;
        this.MRP_MOBILE_NO = MRP_MOBILE_NO;
        this.LOC_NAME_AR = LOC_NAME_AR;
        //    this.SUP_LOC_NAME=SUP_LOC_NAME;
    }

    public String getADMISSION_STATUS() {
        return ADMISSION_STATUS;
    }

    public void setADMISSION_STATUS(String ADMISSION_STATUS) {
        this.ADMISSION_STATUS = ADMISSION_STATUS;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getBedname() {
        return bedname;
    }

    public void setBedname(String bedname) {
        this.bedname = bedname;
    }

    public int getPatid() {
        return patid;
    }

    public void setPatid(int patid) {
        this.patid = patid;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getPtmrpid() {
        return ptmrpid;
    }

    public void setPtmrpid(String ptmrpid) {
        this.ptmrpid = ptmrpid;
    }

    public int getAdmcd() {
        return admcd;
    }

    public void setAdmcd(int admcd) {
        this.admcd = admcd;
    }

    public String getHOS_NO() {
        return HOS_NO;
    }

    public void setHOS_NO(String HOS_NO) {
        this.HOS_NO = HOS_NO;
    }

    public String getH_NAME_AR() {
        return H_NAME_AR;
    }

    public void setH_NAME_AR(String h_NAME_AR) {
        H_NAME_AR = h_NAME_AR;
    }

    public String getMRP_SEX_CD() {
        return MRP_SEX_CD;
    }

    public void setMRP_SEX_CD(String MRP_SEX_CD) {
        this.MRP_SEX_CD = MRP_SEX_CD;
    }

    public String getSEX_NAME_AR() {
        return SEX_NAME_AR;
    }

    public void setSEX_NAME_AR(String SEX_NAME_AR) {
        this.SEX_NAME_AR = SEX_NAME_AR;
    }

    public String getMRP_DOB() {
        return MRP_DOB;
    }

    public void setMRP_DOB(String MRP_DOB) {
        this.MRP_DOB = MRP_DOB;
    }

    public String getHOS_PERMISSION() {
        return HOS_PERMISSION;
    }

    public void setHOS_PERMISSION(String HOS_PERMISSION) {
        this.HOS_PERMISSION = HOS_PERMISSION;
    }

    public String getLOC_CODE() {
        return LOC_CODE;
    }

    public void setLOC_CODE(String LOC_CODE) {
        this.LOC_CODE = LOC_CODE;
    }

    public String getMRP_MOBILE_NO() {
        return MRP_MOBILE_NO;
    }


    public void setMRP_MOBILE_NO(String MRP_MOBILE_NO) {
        this.MRP_MOBILE_NO = MRP_MOBILE_NO;
    }

    public String getEXAM_SPIN_CD() {
        if(EXAM_SPIN_CD!=null)
        return EXAM_SPIN_CD;
        else
        return "";
    }

    public void setEXAM_SPIN_CD(String EXAM_SPIN_CD) {
        this.EXAM_SPIN_CD = EXAM_SPIN_CD;
    }

    public String getPATIENT_STATUS_NAME_EN() {
        if(PATIENT_STATUS_NAME_EN!=null)
        return PATIENT_STATUS_NAME_EN;
        else
            return "";
    }

    public void setPATIENT_STATUS_NAME_EN(String PATIENT_STATUS_NAME_EN) {
        this.PATIENT_STATUS_NAME_EN = PATIENT_STATUS_NAME_EN;
    }

    public String getRESULT_DATE() {
        if(RESULT_DATE!=null)
        return RESULT_DATE;
        else
        return "";
    }

    public void setRESULT_DATE(String RESULT_DATE) {
        this.RESULT_DATE = RESULT_DATE;
    }

    public String getTEST_RESULT() {
        if (TEST_RESULT != null)
            return TEST_RESULT;
        else
            return "";
    }

    public void setTEST_RESULT(String TEST_RESULT) {
        this.TEST_RESULT = TEST_RESULT;
    }

    public String getLoc_name_ar() {
        return LOC_NAME_AR;
    }

    public void setLoc_name_ar(String LOC_NAME_AR) {
        this.LOC_NAME_AR = LOC_NAME_AR;
    }

//    public String getSup_loc_name() {
//        return SUP_LOC_NAME;
//    }
//
//    public void setSup_loc_name(String SUP_LOC_NAME) {
//        this.SUP_LOC_NAME = SUP_LOC_NAME;
//    }
}