package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class RadViewModel {
    @SerializedName("status")
    private String status;
    @SerializedName("instance_info")
    private InstanceInfoModel instance_info;

    public InstanceInfoModel getInstance_info() {
        return instance_info;
    }

    public void setInstance_info(InstanceInfoModel instance_info) {
        this.instance_info = instance_info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
