package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class PatientInfoModel {
    @SerializedName("ORDER_CODE")
    private String orderCode;
    @SerializedName("ORDER_YEAR")
    private String orderYear;
    @SerializedName("MRP_ID")
    private String mrpId;
    @SerializedName("FULL_NAME")
    private String fullName;
    @SerializedName("ORDER_PATREC_CD")
    private String orderPatrecCd;
    @SerializedName("ORDER_RAD_DATE")
    private String orderRadDate;
    @SerializedName("DEPT_NAME_AR")
    private String deptNameAr;
    @SerializedName("LOC_NAME_AR")
    private String locNameAr;
    @SerializedName("EMP_FULL_NAME")
    private String empFullName;
    @SerializedName("AGE")
    private String age;
    @SerializedName("SEX_NAME_AR")
    private String sexNameAr;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(String orderYear) {
        this.orderYear = orderYear;
    }

    public String getMrpId() {
        return mrpId;
    }

    public void setMrpId(String mrpId) {
        this.mrpId = mrpId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrderPatrecCd() {
        return orderPatrecCd;
    }

    public void setOrderPatrecCd(String orderPatrecCd) {
        this.orderPatrecCd = orderPatrecCd;
    }

    public String getOrderRadDate() {
        return orderRadDate;
    }

    public void setOrderRadDate(String orderRadDate) {
        this.orderRadDate = orderRadDate;
    }

    public String getDeptNameAr() {
        return deptNameAr;
    }

    public void setDeptNameAr(String deptNameAr) {
        this.deptNameAr = deptNameAr;
    }

    public String getLocNameAr() {
        return locNameAr;
    }

    public void setLocNameAr(String locNameAr) {
        this.locNameAr = locNameAr;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSexNameAr() {
        return sexNameAr;
    }

    public void setSexNameAr(String sexNameAr) {
        this.sexNameAr = sexNameAr;
    }

}

