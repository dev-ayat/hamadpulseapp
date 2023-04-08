package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClinicAppointmentsModel {
    @SerializedName("VISIT_TM_SERIAL")
    @Expose
    private String visit_id;
    @SerializedName("LOC_NAME_AR")
    @Expose
    private String clinic_name;
    @SerializedName("VISIT_LOC_CD")
    @Expose
    private String clinic_id;
    @SerializedName("VISIT_DATE")
    @Expose
    private String date;
    @SerializedName("VISIT_TIME")
    @Expose
    private String time;

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public String getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(String clinic_id) {
        this.clinic_id = clinic_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
