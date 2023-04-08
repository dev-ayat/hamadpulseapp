package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabSampleModel {
    @SerializedName("ORDER_CD")
    @Expose
    private String orderCd;
    @SerializedName("ORDER_REQUEST_DATE")
    @Expose
    private String orderRequestDate;
    @SerializedName("D_DETAILS_CD")
    @Expose
    private String dDetailsCd;
    @SerializedName("D_CATOGERY_CD")
    @Expose
    private String dCatogeryCd;
    @SerializedName("D_ORDER_STATUS")
    @Expose
    private String dOrderStatus;
    @SerializedName("D_SAMPLING_STATUS")
    @Expose
    private String dSamplingStatus;
    @SerializedName("CATEGORY_ID")
    @Expose
    private String categoryId;
    @SerializedName("CATEGORY_NAME_AR")
    @Expose
    private String categoryNameAr;
    @SerializedName("CATEGORY_NAME")
    @Expose
    private String categoryName;
    @SerializedName("CATEGORY_GROUP_CD")
    @Expose
    private String categoryGroupCd;
    @SerializedName("CATEGORY_CODE")
    @Expose
    private String categoryCode;
    @SerializedName("CATEGORY_NOTES")
    @Expose
    private Object categoryNotes;
    @SerializedName("GROUP_CD")
    @Expose
    private String groupCd;
    @SerializedName("GROUP_NAME_AR")
    @Expose
    private String groupNameAr;
    @SerializedName("GROUP_NAME_EN")
    @Expose
    private String groupNameEn;
    @SerializedName("GROUP_CODE_NAME")
    @Expose
    private String groupCodeName;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("ORDER_STATUS_NAME_AR")
    @Expose
    private String orderStatusNameAr;
    @SerializedName("ORDER_STATUS_NAME_EN")
    @Expose
    private String orderStatusNameEn;

    public String getOrderCd() {
        return orderCd;
    }

    public void setOrderCd(String orderCd) {
        this.orderCd = orderCd;
    }

    public String getOrderRequestDate() {
        return orderRequestDate;
    }

    public void setOrderRequestDate(String orderRequestDate) {
        this.orderRequestDate = orderRequestDate;
    }

    public String getDDetailsCd() {
        return dDetailsCd;
    }

    public void setDDetailsCd(String dDetailsCd) {
        this.dDetailsCd = dDetailsCd;
    }

    public String getDCatogeryCd() {
        return dCatogeryCd;
    }

    public void setDCatogeryCd(String dCatogeryCd) {
        this.dCatogeryCd = dCatogeryCd;
    }

    public String getDOrderStatus() {
        return dOrderStatus;
    }

    public void setDOrderStatus(String dOrderStatus) {
        this.dOrderStatus = dOrderStatus;
    }

    public String getDSamplingStatus() {
        return dSamplingStatus;
    }

    public void setDSamplingStatus(String dSamplingStatus) {
        this.dSamplingStatus = dSamplingStatus;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNameAr() {
        return categoryNameAr;
    }

    public void setCategoryNameAr(String categoryNameAr) {
        this.categoryNameAr = categoryNameAr;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryGroupCd() {
        return categoryGroupCd;
    }

    public void setCategoryGroupCd(String categoryGroupCd) {
        this.categoryGroupCd = categoryGroupCd;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Object getCategoryNotes() {
        return categoryNotes;
    }

    public void setCategoryNotes(Object categoryNotes) {
        this.categoryNotes = categoryNotes;
    }

    public String getGroupCd() {
        return groupCd;
    }

    public void setGroupCd(String groupCd) {
        this.groupCd = groupCd;
    }

    public String getGroupNameAr() {
        return groupNameAr;
    }

    public void setGroupNameAr(String groupNameAr) {
        this.groupNameAr = groupNameAr;
    }

    public String getGroupNameEn() {
        return groupNameEn;
    }

    public void setGroupNameEn(String groupNameEn) {
        this.groupNameEn = groupNameEn;
    }

    public String getGroupCodeName() {
        return groupCodeName;
    }

    public void setGroupCodeName(String groupCodeName) {
        this.groupCodeName = groupCodeName;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getOrderStatusNameAr() {
        return orderStatusNameAr;
    }

    public void setOrderStatusNameAr(String orderStatusNameAr) {
        this.orderStatusNameAr = orderStatusNameAr;
    }

    public String getOrderStatusNameEn() {
        return orderStatusNameEn;
    }

    public void setOrderStatusNameEn(String orderStatusNameEn) {
        this.orderStatusNameEn = orderStatusNameEn;
    }

}
