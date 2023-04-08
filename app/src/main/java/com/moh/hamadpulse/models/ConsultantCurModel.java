package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class ConsultantCurModel {
    @SerializedName("RADIOLOGIST")
    private String radiologist;
    @SerializedName("CONSULTANT_RADIOLOGIST")
    private String consultantRadiologist;

    public String getRadiologist() {
        return radiologist;
    }

    public void setRadiologist(String radiologist) {
        this.radiologist = radiologist;
    }

    public String getConsultantRadiologist() {
        return consultantRadiologist;
    }

    public void setConsultantRadiologist(String consultantRadiologist) {
        this.consultantRadiologist = consultantRadiologist;
    }
}
