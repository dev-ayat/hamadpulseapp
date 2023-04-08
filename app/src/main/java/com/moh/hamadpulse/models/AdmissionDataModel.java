package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AdmissionDataModel {
    @SerializedName("SPECIAL_DATA")
    @Expose
    private ArrayList<AdmissionSpecialDataModel> admissionSpecialDataModels;
    @SerializedName("DIAGNOSE_CUR")
    @Expose
    private ArrayList<AdmissionDiagnoseModel> admissionDiagnoseModels;
    @SerializedName("INVESTIGATION_CUR")
    @Expose
    private ArrayList<AdmissionInvestigationModel> admissionInvestigationModels;
    @SerializedName("ORDERS_CUR")
    @Expose
    private ArrayList<AdmissionOrdersModel> admissionOrdersModels;

    public ArrayList<AdmissionSpecialDataModel> getAdmissionSpecialDataModels() {
        return admissionSpecialDataModels;
    }

    public void setAdmissionSpecialDataModels(ArrayList<AdmissionSpecialDataModel> admissionSpecialDataModels) {
        this.admissionSpecialDataModels = admissionSpecialDataModels;
    }

    public ArrayList<AdmissionDiagnoseModel> getAdmissionDiagnoseModels() {
        return admissionDiagnoseModels;
    }

    public void setAdmissionDiagnoseModels(ArrayList<AdmissionDiagnoseModel> admissionDiagnoseModels) {
        this.admissionDiagnoseModels = admissionDiagnoseModels;
    }

    public ArrayList<AdmissionInvestigationModel> getAdmissionInvestigationModels() {
        return admissionInvestigationModels;
    }

    public void setAdmissionInvestigationModels(ArrayList<AdmissionInvestigationModel> admissionInvestigationModels) {
        this.admissionInvestigationModels = admissionInvestigationModels;
    }

    public ArrayList<AdmissionOrdersModel> getAdmissionOrdersModels() {
        return admissionOrdersModels;
    }

    public void setAdmissionOrdersModels(ArrayList<AdmissionOrdersModel> admissionOrdersModels) {
        this.admissionOrdersModels = admissionOrdersModels;
    }
}
