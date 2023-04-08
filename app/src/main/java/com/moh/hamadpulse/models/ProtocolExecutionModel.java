package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProtocolExecutionModel {
    @SerializedName("TUMER_REP")
    @Expose
    private ArrayList<TumerRepModel> tumerRep = null;
    @SerializedName("CHEMOTHROPY")
    @Expose
    private ArrayList<ChemothropyModel> chemothropy = null;
    @SerializedName("NRS_PRTCL")
    @Expose
    private ArrayList<NrsPrtclModel> nrsPrtcl = null;
    @SerializedName("NRS_CMPLN")
    @Expose
    private ArrayList<NrsCmplnModel> nrsCmpln = null;
    @SerializedName("NRS_NOTES")
    @Expose
    private ArrayList<Object> nrsNotes = null;
    @SerializedName("PRE_CUR")
    @Expose
    private ArrayList<PreCurModel> preCur = null;
    @SerializedName("POST_CUR")
    @Expose
    private ArrayList<PostCurModel> postCur = null;
    @SerializedName("TUMER_POST_TXT")
    @Expose
    private ArrayList<TumerPostTxt_Model> tumerPostTxt = null;

    public ArrayList<TumerPostTxt_Model> getTumerPostTxt() {
        return tumerPostTxt;
    }

    public void setTumerPostTxt(ArrayList<TumerPostTxt_Model> tumerPostTxt) {
        this.tumerPostTxt = tumerPostTxt;
    }

    public ArrayList<TumerRepModel> getTumerRep() {
        return tumerRep;
    }

    public void setTumerRep(ArrayList<TumerRepModel> tumerRep) {
        this.tumerRep = tumerRep;
    }

    public ArrayList<ChemothropyModel> getChemothropy() {
        return chemothropy;
    }

    public void setChemothropy(ArrayList<ChemothropyModel> chemothropy) {
        this.chemothropy = chemothropy;
    }

    public ArrayList<NrsPrtclModel> getNrsPrtcl() {
        return nrsPrtcl;
    }

    public void setNrsPrtcl(ArrayList<NrsPrtclModel> nrsPrtcl) {
        this.nrsPrtcl = nrsPrtcl;
    }

    public ArrayList<NrsCmplnModel> getNrsCmpln() {
        return nrsCmpln;
    }

    public void setNrsCmpln(ArrayList<NrsCmplnModel> nrsCmpln) {
        this.nrsCmpln = nrsCmpln;
    }

    public ArrayList<Object> getNrsNotes() {
        return nrsNotes;
    }

    public void setNrsNotes(ArrayList<Object> nrsNotes) {
        this.nrsNotes = nrsNotes;
    }

    public ArrayList<PreCurModel> getPreCur() {
        return preCur;
    }

    public void setPreCur(ArrayList<PreCurModel> preCur) {
        this.preCur = preCur;
    }

    public ArrayList<PostCurModel> getPostCur() {
        return postCur;
    }

    public void setPostCur(ArrayList<PostCurModel> postCur) {
        this.postCur = postCur;
    }
}
