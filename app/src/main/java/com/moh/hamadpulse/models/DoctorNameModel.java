package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class DoctorNameModel {
    @SerializedName("FULL_NAME")
    private String full_name;

    @SerializedName("EMP_ID")
    private String EMP_ID;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(String EMP_ID) {
        this.EMP_ID = EMP_ID;
    }
}
