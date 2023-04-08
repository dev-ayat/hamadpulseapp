package com.moh.hamadpulse.models;

import java.util.ArrayList;

public class LaborderCardviewDataModel {

    private String laborderdate;
    private int laborderid, laborderyear, hos_no, patpatricID;
    private ArrayList<LabCategoryDataModel> mListCat;
    private boolean isClick;

    public LaborderCardviewDataModel(String laborderdate, int laborderid, int laborderyear, int hos_no, int patpatricID) {
        this.laborderdate = laborderdate;
        this.laborderid = laborderid;
        this.laborderyear = laborderyear;
        this.hos_no = hos_no;
        this.patpatricID = patpatricID;
        mListCat = new ArrayList<>();
        this.isClick = false;
    }
    public LaborderCardviewDataModel() {

        mListCat = new ArrayList<>();
    }
    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public ArrayList<LabCategoryDataModel> getmListCat() {
        return mListCat;
    }

    public void setmListCat(ArrayList<LabCategoryDataModel> mListCat) {
        this.mListCat = mListCat;
    }

    public int getPatpatricID() {
        return patpatricID;
    }

    public void setPatpatricID(int patpatricID) {
        this.patpatricID = patpatricID;
    }

    public String getLaborderdate() {
        return laborderdate;
    }

    public String getLaborderdateOnly() {
        return (laborderdate.split(" "))[0];
    }

    public void setLaborderdate(String laborderdate) {
        this.laborderdate = laborderdate;
    }

    public int getLaborderid() {
        return laborderid;
    }

    public void setLaborderid(int laborderid) {
        this.laborderid = laborderid;
    }

    public int getLaborderyear() {
        return laborderyear;
    }

    public void setLaborderyear(int laborderyear) {
        this.laborderyear = laborderyear;
    }

    public int getHos_no() {
        return hos_no;
    }

    public void setHos_no(int hos_no) {
        this.hos_no = hos_no;
    }
}