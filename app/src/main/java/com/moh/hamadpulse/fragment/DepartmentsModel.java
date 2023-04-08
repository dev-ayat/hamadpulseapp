package com.moh.hamadpulse.fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DepartmentsModel {
    @SerializedName("RESULT")
    @Expose
    private ArrayList<DepartmentsDataModel> departments;


    public ArrayList<DepartmentsDataModel> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<DepartmentsDataModel> departments) {
        this.departments = departments;
    }
}
