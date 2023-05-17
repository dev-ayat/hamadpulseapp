package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class BasicInfoModel {
    @SerializedName("Instance")
    private String Instance;
    @SerializedName("time_instance")
    private String time_instance;
    @SerializedName("modality")
    private String modality;
    @SerializedName("ohiv_link")
    private String ohiv_link;


    public String getOhiv_link() {
        return ohiv_link;
    }

    public void setOhiv_link(String ohiv_link) {
        this.ohiv_link = ohiv_link;
    }

    public String getTime_instance() {
        return time_instance;
    }

    public void setTime_instance(String time_instance) {
        this.time_instance = time_instance;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getInstance() {
        return Instance;
    }

    public void setInstance(String instance) {
        Instance = instance;
    }
}