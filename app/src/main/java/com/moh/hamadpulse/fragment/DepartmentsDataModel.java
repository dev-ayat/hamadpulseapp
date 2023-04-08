package com.moh.hamadpulse.fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DepartmentsDataModel {
    public DepartmentsDataModel(String locCode) {
        this.locCode = locCode;
    }

    public DepartmentsDataModel() {
    }

    @SerializedName("LOC_CODE")
    @Expose
    private String locCode;
    @SerializedName("LOC_NAME_AR")
    @Expose
    private String locNameAr;
    @SerializedName("LOC_NAME_EN")
    @Expose
    private String locNameEn;
    @SerializedName("LOC_DOC_CODE")
    @Expose
    private String locDocCode;
    @SerializedName("LOC_CLASS_CD")
    @Expose
    private String locClassCd;
    @SerializedName("LOC_PATTERN_CD")
    @Expose
    private String locPatternCd;
    @SerializedName("LOC_DEPT_CD")
    @Expose
    private String locDeptCd;
    @SerializedName("LOC_MASTER_CD")
    @Expose
    private String locMasterCd;
    @SerializedName("LOC_CLASSIFICATION_CD")
    @Expose
    private String locClassificationCd;
    @SerializedName("LOC_DAILY_CARE_CD")
    @Expose
    private String locDailyCareCd;
    @SerializedName("OP_CLINIC")
    @Expose
    private String opClinic;
    @SerializedName("LOC_EXTRA_VISITS")
    @Expose
    private String locExtraVisits;
    @SerializedName("LOC_INP_FLAG")
    @Expose
    private String locInpFlag;
    @SerializedName("LOC_FROM_LOC")
    @Expose
    private String locFromLoc;
    @SerializedName("LOC_SUBDEP_CD")
    @Expose
    private String locSubdepCd;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("LOC_IS_COMPUTERIZED")
    @Expose
    private String locIsComputerized;
    @SerializedName("LOC_IS_HIDDEN")
    @Expose
    private String locIsHidden;
    @SerializedName("LOC_RESPONSIBILITY_CENTER")
    @Expose
    private String locResponsibilityCenter;

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getLocNameAr() {
        return locNameAr;
    }

    public void setLocNameAr(String locNameAr) {
        this.locNameAr = locNameAr;
    }

    public String getLocNameEn() {
        return locNameEn;
    }

    public void setLocNameEn(String locNameEn) {
        this.locNameEn = locNameEn;
    }

    public String getLocDocCode() {
        return locDocCode;
    }

    public void setLocDocCode(String locDocCode) {
        this.locDocCode = locDocCode;
    }

    public String getLocClassCd() {
        return locClassCd;
    }

    public void setLocClassCd(String locClassCd) {
        this.locClassCd = locClassCd;
    }

    public String getLocPatternCd() {
        return locPatternCd;
    }

    public void setLocPatternCd(String locPatternCd) {
        this.locPatternCd = locPatternCd;
    }

    public String getLocDeptCd() {
        return locDeptCd;
    }

    public void setLocDeptCd(String locDeptCd) {
        this.locDeptCd = locDeptCd;
    }

    public String getLocMasterCd() {
        return locMasterCd;
    }

    public void setLocMasterCd(String locMasterCd) {
        this.locMasterCd = locMasterCd;
    }

    public String getLocClassificationCd() {
        return locClassificationCd;
    }

    public void setLocClassificationCd(String locClassificationCd) {
        this.locClassificationCd = locClassificationCd;
    }

    public String getLocDailyCareCd() {
        return locDailyCareCd;
    }

    public void setLocDailyCareCd(String locDailyCareCd) {
        this.locDailyCareCd = locDailyCareCd;
    }

    public String getOpClinic() {
        return opClinic;
    }

    public void setOpClinic(String opClinic) {
        this.opClinic = opClinic;
    }

    public String getLocExtraVisits() {
        return locExtraVisits;
    }

    public void setLocExtraVisits(String locExtraVisits) {
        this.locExtraVisits = locExtraVisits;
    }

    public String getLocInpFlag() {
        return locInpFlag;
    }

    public void setLocInpFlag(String locInpFlag) {
        this.locInpFlag = locInpFlag;
    }

    public String getLocFromLoc() {
        return locFromLoc;
    }

    public void setLocFromLoc(String locFromLoc) {
        this.locFromLoc = locFromLoc;
    }

    public String getLocSubdepCd() {
        return locSubdepCd;
    }

    public void setLocSubdepCd(String locSubdepCd) {
        this.locSubdepCd = locSubdepCd;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getLocIsComputerized() {
        return locIsComputerized;
    }

    public void setLocIsComputerized(String locIsComputerized) {
        this.locIsComputerized = locIsComputerized;
    }

    public String getLocIsHidden() {
        return locIsHidden;
    }

    public void setLocIsHidden(String locIsHidden) {
        this.locIsHidden = locIsHidden;
    }

    public String getLocResponsibilityCenter() {
        return locResponsibilityCenter;
    }

    public void setLocResponsibilityCenter(String locResponsibilityCenter) {
        this.locResponsibilityCenter = locResponsibilityCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentsDataModel that = (DepartmentsDataModel) o;
        return Objects.equals(locCode, that.locCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locCode);
    }
}
