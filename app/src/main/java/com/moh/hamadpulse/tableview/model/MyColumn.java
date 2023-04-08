package com.moh.hamadpulse.tableview.model;

public class MyColumn {
    String id;
    String myDate;
    String val;

    public MyColumn() {
    }

    public MyColumn(String id, String myDate, String val) {
        this.id = id;
        this.myDate = myDate;
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    public String getVal() {
        if (val != null) {
            return val;
        } else
            return "";
    }

    public void setVal(String val) {
        this.val = val;
    }
}
