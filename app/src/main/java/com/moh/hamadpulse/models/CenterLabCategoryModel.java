package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CenterLabCategoryModel {
    @SerializedName("GROUP_NAME_AR")
    @Expose
    private String groupNameAr;
    @SerializedName("CATEGORY_ID")
    @Expose
    private String categoryId;
    @SerializedName("CATEGORY_NAME")
    @Expose
    private String categoryName;
    @SerializedName("CAT_STATUS")
    @Expose
    private String categoryStatus;
    @SerializedName("LAB_ORDER_DETAILS_STATUS")
    @Expose
    private String categoryStatusNumber;
    @SerializedName("TESTS")
    @Expose
    private ArrayList<TestModel> tests = null;

    public ArrayList<TestModel> getTests() {
        return tests;
    }

    public void setTests(ArrayList<TestModel> tests) {
        this.tests = tests;
    }

    public String getCategoryStatusNumber() {
        return categoryStatusNumber;
    }

    public void setCategoryStatusNumber(String categoryStatusNumber) {
        this.categoryStatusNumber = categoryStatusNumber;
    }

    public String getGroupNameAr() {
        return groupNameAr;
    }

    public void setGroupNameAr(String groupNameAr) {
        this.groupNameAr = groupNameAr;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
