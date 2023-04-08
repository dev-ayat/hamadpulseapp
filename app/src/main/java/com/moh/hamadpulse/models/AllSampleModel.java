package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllSampleModel {

    @SerializedName("LAB_SAMPLE")
    @Expose
    private ArrayList<LabSampleModel> labSample = null;

    public ArrayList<LabSampleModel> getLabSample() {
        return labSample;
    }

    public void setLabSample(ArrayList<LabSampleModel> labSample) {
        this.labSample = labSample;
    }
}
