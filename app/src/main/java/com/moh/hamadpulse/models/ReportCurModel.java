package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReportCurModel {

    @SerializedName("TITLE")
    @Expose
    private String title;
    @SerializedName("TITLE_EN")
    @Expose
    private String titleEn;
    @SerializedName("HOS_NAME_AR")
    @Expose
    private String hosNameAr;
    @SerializedName("HOS_NAME_EN")
    @Expose
    private String hosNameEn;
    @SerializedName("CONSULTANT_CUR")
    @Expose
    private ArrayList<ConsultantCurModel> consultantCur;
    @SerializedName("PATIENT_INFO")
    @Expose
    private ArrayList<PatientInfoModel> patientInfo;
    @SerializedName("RAD_DETAILS")
    @Expose
    private ArrayList<RadDetailModel> radDetails;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getHosNameAr() {
        return hosNameAr;
    }

    public void setHosNameAr(String hosNameAr) {
        this.hosNameAr = hosNameAr;
    }

    public String getHosNameEn() {
        return hosNameEn;
    }

    public void setHosNameEn(String hosNameEn) {
        this.hosNameEn = hosNameEn;
    }

    public ArrayList<ConsultantCurModel> getConsultantCur() {
        return consultantCur;
    }

    public void setConsultantCur(ArrayList<ConsultantCurModel> consultantCur) {
        this.consultantCur = consultantCur;
    }

    public ArrayList<PatientInfoModel> getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(ArrayList<PatientInfoModel> patientInfo) {
        this.patientInfo = patientInfo;
    }

    public ArrayList<RadDetailModel> getRadDetails() {
        return radDetails;
    }

    public void setRadDetails(ArrayList<RadDetailModel> radDetails) {
        this.radDetails = radDetails;
    }
}
