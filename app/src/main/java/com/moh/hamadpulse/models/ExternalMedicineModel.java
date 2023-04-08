package com.moh.hamadpulse.models;

import com.google.gson.annotations.SerializedName;

public class ExternalMedicineModel {
    @SerializedName("ORDER_DATE")
    private String orderDate;
    @SerializedName("ORDER_ENTER_DOC_CD")
    private String orderEnterDocCD;
    @SerializedName("ORDER_CODE")
    private String orderCode;
    @SerializedName("DRAG_CD")
    private String dragCD;
    @SerializedName("DOSAGE")
    private String dosage;
    @SerializedName("DRAG_FORM")
    private String dragForm;
    @SerializedName("DURTION")
    private String duration;
    @SerializedName("REPEAT")
    private String repeat;
    @SerializedName("DOSE_NAME")
    private String doseName;
    @SerializedName("USER_FULL_NAME")
    private String userFullName;
    @SerializedName("ITEM_NAME")
    private String itemName;
    @SerializedName("ITEM_CODE")
    private String itemCode;
    @SerializedName("TOTAL")
    private String total;
    @SerializedName("DRAG_DESC")
    private String note;
    @SerializedName("ORDER_STATUS_CD")
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderEnterDocCD() {
        return orderEnterDocCD;
    }

    public void setOrderEnterDocCD(String orderEnterDocCD) {
        this.orderEnterDocCD = orderEnterDocCD;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDragCD() {
        return dragCD;
    }

    public void setDragCD(String dragCD) {
        this.dragCD = dragCD;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDragForm() {
        return dragForm;
    }

    public void setDragForm(String dragForm) {
        this.dragForm = dragForm;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemValid() {
//        to validate row if some filed is empty or not selected or filled the result is false otherwise result is true
        return !(dragCD == null || dragCD.isEmpty() || dosage == null || dosage.isEmpty() || repeat == null || repeat.isEmpty() ||
                total == null || total.isEmpty());
    }
}
