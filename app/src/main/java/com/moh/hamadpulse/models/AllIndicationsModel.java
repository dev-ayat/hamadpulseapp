package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllIndicationsModel {
    @SerializedName("RESULT")
    @Expose
    private ArrayList<IndicationsResultModel> result = null;

    public ArrayList<IndicationsResultModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<IndicationsResultModel> result) {
        this.result = result;
    }
}
