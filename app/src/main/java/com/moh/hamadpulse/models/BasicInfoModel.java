package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class BasicInfoModel {
    @SerializedName("Instance")
    private String Instance;
    @SerializedName("time_instance")
    private String time_instance;
    @SerializedName("modality")
    private String modality;
    @SerializedName("img_link")
    private String img_link;

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
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
