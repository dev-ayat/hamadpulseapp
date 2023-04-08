package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class TotalsModel {
    @SerializedName("IN_TOTALS")
    private String inTotals;
    @SerializedName("OUT_TOTALS")
    private String outTotals;

    public String getInTotals() {
        return inTotals;
    }

    public void setInTotals(String inTotals) {
        this.inTotals = inTotals;
    }

    public String getOutTotals() {
        return outTotals;
    }

    public void setOutTotals(String outTotals) {
        this.outTotals = outTotals;
    }
}
