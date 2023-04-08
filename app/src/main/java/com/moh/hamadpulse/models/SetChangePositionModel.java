package com.moh.hamadpulse.models;

public class SetChangePositionModel {
    private String position, time;
    private Boolean checkable;

    public SetChangePositionModel(String position, String time, Boolean checkable) {
        this.position = position;
        this.time = time;
        this.checkable = checkable;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(Boolean checkable) {
        this.checkable = checkable;
    }
}
