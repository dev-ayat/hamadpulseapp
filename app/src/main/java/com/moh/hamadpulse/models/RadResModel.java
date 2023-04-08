package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RadResModel {
    @SerializedName("RESULT_COUNT")
    private String resultCount;
    @SerializedName("RAD_CUR")
    private ArrayList<RadCurModel> radCur;

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<RadCurModel> getRadCur() {
        return radCur;
    }

    public void setRadCur(ArrayList<RadCurModel> radCur) {
        this.radCur = radCur;
    }
}
