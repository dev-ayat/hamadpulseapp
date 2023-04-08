package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModel {

    @SerializedName("LAB_ORDER_ID")
    @Expose
    private String labOrderId;
    @SerializedName("VISIT_ID")
    @Expose
    private String visitId;
    @SerializedName("LAB_ORDER_STATUS")
    @Expose
    private String labOrderStatus;
    @SerializedName("CANCELD_REASON")
    @Expose
    private String canceldReason;
    @SerializedName("CANCELD_DATE")
    @Expose
    private String canceldDate;
    @SerializedName("CANCELD_BY")
    @Expose
    private String canceldBy;
    @SerializedName("LAB_ORDER_DETAILS_ID")
    @Expose
    private String labOrderDetailsId;
    @SerializedName("CATOGERY_ID")
    @Expose
    private String catogeryId;
    @SerializedName("LAB_ORDER_DETAILS_STATUS")
    @Expose
    private String labOrderDetailsStatus;
    @SerializedName("IS_REJECTED_SAMPLE")
    @Expose
    private String isRejectedSample;
    @SerializedName("REJECTION_REASON")
    @Expose
    private String rejectionReason;
    @SerializedName("LAB_RESULT_ID")
    @Expose
    private String labResultId;
    @SerializedName("TEST_ID")
    @Expose
    private String testId;
    @SerializedName("VALUE")
    @Expose
    private String value;
    @SerializedName("CULTURE_STATE_ID")
    @Expose
    private String cultureStateId;
    @SerializedName("CULTURE_A_RESULT_ID")
    @Expose
    private String cultureAResultId;
    @SerializedName("CULTURE_B_RESULT_ID")
    @Expose
    private String cultureBResultId;
    @SerializedName("CULTURE_C_RESULT_ID")
    @Expose
    private String cultureCResultId;
    @SerializedName("ORGANISM_A_RESULT_ID")
    @Expose
    private String organismAResultId;
    @SerializedName("ORGANISM_B_RESULT_ID")
    @Expose
    private String organismBResultId;
    @SerializedName("ORGANISM_C_RESULT_ID")
    @Expose
    private String organismCResultId;
    @SerializedName("ACID_FAST_STAIN")
    @Expose
    private String acidFastStain;
    @SerializedName("GRAMSTAIN")
    @Expose
    private String gramstain;
    @SerializedName("FUNGI")
    @Expose
    private String fungi;
    @SerializedName("ORGANISM_COUNT_A")
    @Expose
    private String organismCountA;
    @SerializedName("ORGANISM_COUNT_B")
    @Expose
    private String organismCountB;
    @SerializedName("ORGANISM_COUNT_C")
    @Expose
    private String organismCountC;
    @SerializedName("SAMPLE_TYPE_ID")
    @Expose
    private String sampleTypeId;
    @SerializedName("NOTES")
    @Expose
    private String notes;
    @SerializedName("GROUP_NAME_AR")
    @Expose
    private String groupNameAr;
    @SerializedName("CATEGORY_ID")
    @Expose
    private String categoryId;
    @SerializedName("CATEGORY_NAME")
    @Expose
    private String categoryName;
    @SerializedName("C_TEST_ITEM_ID")
    @Expose
    private String cTestItemId;
    @SerializedName("TEST_ITEMS_NAME")
    @Expose
    private String testItemsName;
    @SerializedName("TEST_UNIT")
    @Expose
    private String testUnit;
    @SerializedName("C_ORDER")
    @Expose
    private String cOrder;
    @SerializedName("ITEMS_VALUE_NAME")
    @Expose
    private String itemsValueName;
    @SerializedName("REFERANCE_DISCRIPTION")
    @Expose
    private String referanceDiscription;

    public String getLabOrderId() {
        return labOrderId;
    }

    public void setLabOrderId(String labOrderId) {
        this.labOrderId = labOrderId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getLabOrderStatus() {
        return labOrderStatus;
    }

    public void setLabOrderStatus(String labOrderStatus) {
        this.labOrderStatus = labOrderStatus;
    }

    public String getCanceldReason() {
        return canceldReason;
    }

    public void setCanceldReason(String canceldReason) {
        this.canceldReason = canceldReason;
    }

    public String getCanceldDate() {
        return canceldDate;
    }

    public void setCanceldDate(String canceldDate) {
        this.canceldDate = canceldDate;
    }

    public String getCanceldBy() {
        return canceldBy;
    }

    public void setCanceldBy(String canceldBy) {
        this.canceldBy = canceldBy;
    }

    public String getLabOrderDetailsId() {
        return labOrderDetailsId;
    }

    public void setLabOrderDetailsId(String labOrderDetailsId) {
        this.labOrderDetailsId = labOrderDetailsId;
    }

    public String getCatogeryId() {
        return catogeryId;
    }

    public void setCatogeryId(String catogeryId) {
        this.catogeryId = catogeryId;
    }

    public String getLabOrderDetailsStatus() {
        return labOrderDetailsStatus;
    }

    public void setLabOrderDetailsStatus(String labOrderDetailsStatus) {
        this.labOrderDetailsStatus = labOrderDetailsStatus;
    }

    public String getIsRejectedSample() {
        return isRejectedSample;
    }

    public void setIsRejectedSample(String isRejectedSample) {
        this.isRejectedSample = isRejectedSample;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getLabResultId() {
        return labResultId;
    }

    public void setLabResultId(String labResultId) {
        this.labResultId = labResultId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCultureStateId() {
        return cultureStateId;
    }

    public void setCultureStateId(String cultureStateId) {
        this.cultureStateId = cultureStateId;
    }

    public String getCultureAResultId() {
        return cultureAResultId;
    }

    public void setCultureAResultId(String cultureAResultId) {
        this.cultureAResultId = cultureAResultId;
    }

    public String getCultureBResultId() {
        return cultureBResultId;
    }

    public void setCultureBResultId(String cultureBResultId) {
        this.cultureBResultId = cultureBResultId;
    }

    public String getCultureCResultId() {
        return cultureCResultId;
    }

    public void setCultureCResultId(String cultureCResultId) {
        this.cultureCResultId = cultureCResultId;
    }

    public String getOrganismAResultId() {
        return organismAResultId;
    }

    public void setOrganismAResultId(String organismAResultId) {
        this.organismAResultId = organismAResultId;
    }

    public String getOrganismBResultId() {
        return organismBResultId;
    }

    public void setOrganismBResultId(String organismBResultId) {
        this.organismBResultId = organismBResultId;
    }

    public String getOrganismCResultId() {
        return organismCResultId;
    }

    public void setOrganismCResultId(String organismCResultId) {
        this.organismCResultId = organismCResultId;
    }

    public String getAcidFastStain() {
        return acidFastStain;
    }

    public void setAcidFastStain(String acidFastStain) {
        this.acidFastStain = acidFastStain;
    }

    public String getGramstain() {
        return gramstain;
    }

    public void setGramstain(String gramstain) {
        this.gramstain = gramstain;
    }

    public String getFungi() {
        return fungi;
    }

    public void setFungi(String fungi) {
        this.fungi = fungi;
    }

    public String getOrganismCountA() {
        return organismCountA;
    }

    public void setOrganismCountA(String organismCountA) {
        this.organismCountA = organismCountA;
    }

    public String getOrganismCountB() {
        return organismCountB;
    }

    public void setOrganismCountB(String organismCountB) {
        this.organismCountB = organismCountB;
    }

    public String getOrganismCountC() {
        return organismCountC;
    }

    public void setOrganismCountC(String organismCountC) {
        this.organismCountC = organismCountC;
    }

    public String getSampleTypeId() {
        return sampleTypeId;
    }

    public void setSampleTypeId(String sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getcTestItemId() {
        return cTestItemId;
    }

    public void setcTestItemId(String cTestItemId) {
        this.cTestItemId = cTestItemId;
    }

    public String getTestItemsName() {
        return testItemsName;
    }

    public void setTestItemsName(String testItemsName) {
        this.testItemsName = testItemsName;
    }

    public String getTestUnit() {
        return testUnit;
    }

    public void setTestUnit(String testUnit) {
        this.testUnit = testUnit;
    }

    public String getcOrder() {
        return cOrder;
    }

    public void setcOrder(String cOrder) {
        this.cOrder = cOrder;
    }

    public String getItemsValueName() {
        return itemsValueName;
    }

    public void setItemsValueName(String itemsValueName) {
        this.itemsValueName = itemsValueName;
    }

    public String getReferanceDiscription() {
        return referanceDiscription;
    }

    public void setReferanceDiscription(String referanceDiscription) {
        this.referanceDiscription = referanceDiscription;
    }
}
