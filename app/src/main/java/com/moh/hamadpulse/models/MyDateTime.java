package com.moh.hamadpulse.models;

public class MyDateTime {
    String Days;
    String Hours;
    String Minutes;
    String Month;
    String Year;

    public MyDateTime() {
    }

    public MyDateTime(String days, String hours, String minutes) {
        Days = days;
        Hours = hours;
        Minutes = minutes;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }

    public String getMinutes() {
        return Minutes;
    }

    public void setMinutes(String minutes) {
        Minutes = minutes;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
