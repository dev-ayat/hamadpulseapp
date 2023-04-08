package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllProtocolsModel {
    @SerializedName("RESULT")
    @Expose
    private ArrayList<ProtocolsResultModel> result = null;


    public ArrayList<ProtocolsResultModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<ProtocolsResultModel> result) {
        this.result = result;
    }

}
