package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProtocolVitalSignsModel {
    @SerializedName("TUMER_CUR")
    @Expose
    private ArrayList<P_TumerModel> pTumerModels = null;
    @SerializedName("VSIGN_CUR")
    @Expose
    private ArrayList<P_VitalSignsModel> pVitalSignsModels = null;

    public ArrayList<P_TumerModel> getpTumerModels() {
        return pTumerModels;
    }

    public void setpTumerModels(ArrayList<P_TumerModel> pTumerModels) {
        this.pTumerModels = pTumerModels;
    }

    public ArrayList<P_VitalSignsModel> getpVitalSignsModels() {
        return pVitalSignsModels;
    }

    public void setpVitalSignsModels(ArrayList<P_VitalSignsModel> pVitalSignsModels) {
        this.pVitalSignsModels = pVitalSignsModels;
    }
}
