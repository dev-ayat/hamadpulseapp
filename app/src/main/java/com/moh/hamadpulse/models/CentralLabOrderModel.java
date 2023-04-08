package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CentralLabOrderModel {
    @SerializedName("VISIT_ID")
    @Expose
    private String visitId;
    @SerializedName("LAB_ORDER_ID")
    @Expose
    private String labOrderId;
    @SerializedName("REQUEST_DATE")
    @Expose
    private String requestDate;
    @SerializedName("CAT")
    @Expose
    private ArrayList<CenterLabCategoryModel> cat = null;
    private boolean isClick;
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getLabOrderId() {
        return labOrderId;
    }

    public void setLabOrderId(String labOrderId) {
        this.labOrderId = labOrderId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public ArrayList<CenterLabCategoryModel> getCat() {
        return cat;
    }

    public void setCat(ArrayList<CenterLabCategoryModel> cat) {
        this.cat = cat;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
