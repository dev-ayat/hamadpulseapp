package com.moh.hamadpulse.models;

public class LabTestDataModel {
    private String txttestname, lbtestunit;
    private String txttestvalue, lbtestminref, lbtestmaxref;
    private String header;
    private boolean isFrom_To;

    public LabTestDataModel() {
    }

    public boolean isFrom_To() {
        return isFrom_To;
    }

    public void setFrom_To(boolean from_To) {
        isFrom_To = from_To;
    }

    public LabTestDataModel(String txttestname, String lbtestunit, String txttestvalue, String lbtestminref, String lbtestmaxref) {
        this.txttestname = txttestname;
        this.lbtestunit = lbtestunit;
        this.txttestvalue = txttestvalue;
        this.lbtestminref = lbtestminref;
        this.lbtestmaxref = lbtestmaxref;
    }


    public String getTxttestname() {
        return txttestname;
    }

    public void setTxttestname(String txttestname) {
        this.txttestname = txttestname;
    }

    public String getLbtestunit() {
        return lbtestunit;
    }

    public void setLbtestunit(String lbtestunit) {
        this.lbtestunit = lbtestunit;
    }

    public String getTxttestvalue() {
        return txttestvalue;
    }

    public void setTxttestvalue(String txttestvalue) {
        this.txttestvalue = txttestvalue;
    }

    public String getLbtestminref() {
        return lbtestminref;
    }

    public void setLbtestminref(String lbtestminref) {
        this.lbtestminref = lbtestminref;
    }

    public String getLbtestmaxref() {
        return lbtestmaxref;
    }

    public void setLbtestmaxref(String lbtestmaxref) {
        this.lbtestmaxref = lbtestmaxref;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
