package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProtocolDataModel {
    @SerializedName("CUR_PRE_POST")
    @Expose
    private ArrayList<PrePostDrugTextModel> curPrePost = null;
    @SerializedName("CUR_CHEMOTHORPY")
    @Expose
    private ArrayList<NewChemotherapyModel> curChemothorpy = null;
    @SerializedName("CUR_PRE_DRUGS")
    @Expose
    private ArrayList<PreDrugsModel> preDrugsModels = null;
    @SerializedName("CUR_POST_DRUGS")
    @Expose
    private ArrayList<PostDrugsModel> postDrugsModels = null;

    public ArrayList<PreDrugsModel> getPreDrugsModels() {
        return preDrugsModels;
    }

    public void setPreDrugsModels(ArrayList<PreDrugsModel> preDrugsModels) {
        this.preDrugsModels = preDrugsModels;
    }

    public ArrayList<PostDrugsModel> getPostDrugsModels() {
        return postDrugsModels;
    }

    public void setPostDrugsModels(ArrayList<PostDrugsModel> postDrugsModels) {
        this.postDrugsModels = postDrugsModels;
    }

    public ArrayList<PrePostDrugTextModel> getCurPrePost() {
        return curPrePost;
    }

    public void setCurPrePost(ArrayList<PrePostDrugTextModel> curPrePost) {
        this.curPrePost = curPrePost;
    }

    public ArrayList<NewChemotherapyModel> getCurChemothorpy() {
        return curChemothorpy;
    }

    public void setCurChemothorpy(ArrayList<NewChemotherapyModel> curChemothorpy) {
        this.curChemothorpy = curChemothorpy;
    }
}
