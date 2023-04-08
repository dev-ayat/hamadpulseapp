package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstanceInfo {
    private final static long serialVersionUID = -6876754121043194113L;
    @SerializedName("basic_info")
    @Expose
    private List<BasicInfo> basicInfo = null;

    public List<BasicInfo> getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(List<BasicInfo> basicInfo) {
        this.basicInfo = basicInfo;
    }
}
