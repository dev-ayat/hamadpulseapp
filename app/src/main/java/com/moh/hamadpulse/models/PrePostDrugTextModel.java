package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrePostDrugTextModel {
    @SerializedName("P_HEADER")
    @Expose
    private String pHeader;
    @SerializedName("P_DETAILS")
    @Expose
    private String pDetails;
    @SerializedName("P_SERIAL")
    @Expose
    private String pSerial;
    @SerializedName("ID")
    @Expose
    private String id;

    public String getPHeader() {
        return pHeader;
    }

    public void setPHeader(String pHeader) {
        this.pHeader = pHeader;
    }

    public String getPDetails() {
        return pDetails;
    }

    public void setPDetails(String pDetails) {
        this.pDetails = pDetails;
    }

    public String getPSerial() {
        return pSerial;
    }

    public void setPSerial(String pSerial) {
        this.pSerial = pSerial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
