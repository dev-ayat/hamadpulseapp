package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InstanceInfoModel {
    @SerializedName("basic_info")
    private ArrayList<BasicInfoModel> basic_info;

    public ArrayList<BasicInfoModel> getBasic_info() {
        return basic_info;
    }

    public void setBasic_info(ArrayList<BasicInfoModel> basic_info) {
        this.basic_info = basic_info;
    }
}
