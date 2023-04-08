package com.moh.hamadpulse.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RadCurModel {

    @SerializedName("ORDER_CODE")
    private String orderCode;
    @SerializedName("ORDER_YEAR")
    private String orderYear;
    @SerializedName("ORDER_DATE")
    private String orderDate;
    @SerializedName("MRP_ID")
    private String mrpId;
    @SerializedName("ORDER_PATREC_CD")
    private String orderPatrecCd;
    @SerializedName("ORDER_PATIENT_TYPE_CD")
    private String orderPatientTypeCd;
    @SerializedName("MRP_ID_TYPE_CD")
    private String mrpIdTypeCd;
    @SerializedName("ORDER_TYPE_NAME_AR")
    private String orderTypeNameAr;
    @SerializedName("MRP_FIRST_NAME_F")
    private String mrpFirstNameF;
    @SerializedName("MRP_FATHER_NAME_F")
    private String mrpFatherNameF;
    @SerializedName("MRP_GRANDFATHER_NAME_F")
    private String mrpGrandfatherNameF;
    @SerializedName("MRP_LAST_NAME_F")
    private String mrpLastNameF;
    @SerializedName("FULL_NAME_AR")
    private String fullNameAr;
    @SerializedName("ORDER_STATUS_CD")
    private String orderStatusCd;
    @SerializedName("ORDER_STATUS_TYPE_NAME_AR")
    private String orderStatusTypeNameAr;
    @SerializedName("SERVICE_CODE")
    private String serviceCode;
    @SerializedName("SERVICE_NAME_AR")
    private String serviceNameAr;
    @SerializedName("ORDERD_AUDIT_DATE")
    private String orderdAuditDate;
    @SerializedName("ORDERD_RESULT_DATE")
    private String orderdResultDate;
    @SerializedName("ORDER_RECEIVER_NAME")
    private String orderReceiverName;
    @SerializedName("ORDER_ENTER_DOC_CD")
    private String orderEnterDocCd;
    @SerializedName("ORDER_IS_RECIVED")
    private String orderIsRecived;
    @SerializedName("TYPE_CODE")
    private String typeCode;
    @SerializedName("TYPE_NAME_AR")
    private String typeNameAr;
    @SerializedName("ORDER_DEP_CD")
    private String orderDepCd;
    @SerializedName("ORDER_LOC_CD")
    private String orderLocCd;
    @SerializedName("DEPT_NAME_AR")
    private String deptNameAr;
    @SerializedName("LOC_NAME_AR")
    private String locNameAr;
    @SerializedName("FULLEMP_NAME")
    private String fullempName;
    @SerializedName("RAD_ORDER_MCH_CD")
    private String radOrderMchCd;
    @SerializedName("ORDER_VOUCHER_NUMBER")
    private String orderVoucherNumber;
    @SerializedName("ORDERD_RESULT_FINAL")
    private String orderdResultFinal;
    @SerializedName("ORDER_NOTES")
    private String orderNotes;
    @SerializedName("RAD_IS_NIGHT")
    @Expose
    private String radIsNight;
    @SerializedName("PATEINT_AGE")
    @Expose
    private String pateintAge;

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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMrpId() {
        return mrpId;
    }

    public void setMrpId(String mrpId) {
        this.mrpId = mrpId;
    }

    public String getOrderPatrecCd() {
        return orderPatrecCd;
    }

    public void setOrderPatrecCd(String orderPatrecCd) {
        this.orderPatrecCd = orderPatrecCd;
    }

    public String getOrderPatientTypeCd() {
        return orderPatientTypeCd;
    }

    public void setOrderPatientTypeCd(String orderPatientTypeCd) {
        this.orderPatientTypeCd = orderPatientTypeCd;
    }

    public String getMrpIdTypeCd() {
        return mrpIdTypeCd;
    }

    public void setMrpIdTypeCd(String mrpIdTypeCd) {
        this.mrpIdTypeCd = mrpIdTypeCd;
    }

    public String getOrderTypeNameAr() {
        return orderTypeNameAr;
    }

    public void setOrderTypeNameAr(String orderTypeNameAr) {
        this.orderTypeNameAr = orderTypeNameAr;
    }

    public String getMrpFirstNameF() {
        return mrpFirstNameF;
    }

    public void setMrpFirstNameF(String mrpFirstNameF) {
        this.mrpFirstNameF = mrpFirstNameF;
    }

    public String getMrpFatherNameF() {
        return mrpFatherNameF;
    }

    public void setMrpFatherNameF(String mrpFatherNameF) {
        this.mrpFatherNameF = mrpFatherNameF;
    }

    public String getMrpGrandfatherNameF() {
        return mrpGrandfatherNameF;
    }

    public void setMrpGrandfatherNameF(String mrpGrandfatherNameF) {
        this.mrpGrandfatherNameF = mrpGrandfatherNameF;
    }

    public String getMrpLastNameF() {
        return mrpLastNameF;
    }

    public void setMrpLastNameF(String mrpLastNameF) {
        this.mrpLastNameF = mrpLastNameF;
    }

    public String getFullNameAr() {
        return fullNameAr;
    }

    public void setFullNameAr(String fullNameAr) {
        this.fullNameAr = fullNameAr;
    }

    public String getOrderStatusCd() {
        return orderStatusCd;
    }

    public void setOrderStatusCd(String orderStatusCd) {
        this.orderStatusCd = orderStatusCd;
    }

    public String getOrderStatusTypeNameAr() {
        return orderStatusTypeNameAr;
    }

    public void setOrderStatusTypeNameAr(String orderStatusTypeNameAr) {
        this.orderStatusTypeNameAr = orderStatusTypeNameAr;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceNameAr() {
        return serviceNameAr;
    }

    public void setServiceNameAr(String serviceNameAr) {
        this.serviceNameAr = serviceNameAr;
    }

    public String getOrderdAuditDate() {
        return orderdAuditDate;
    }

    public void setOrderdAuditDate(String orderdAuditDate) {
        this.orderdAuditDate = orderdAuditDate;
    }

    public String getOrderdResultDate() {
        return orderdResultDate;
    }

    public void setOrderdResultDate(String orderdResultDate) {
        this.orderdResultDate = orderdResultDate;
    }

    public String getOrderReceiverName() {
        return orderReceiverName;
    }

    public void setOrderReceiverName(String orderReceiverName) {
        this.orderReceiverName = orderReceiverName;
    }

    public String getOrderEnterDocCd() {
        return orderEnterDocCd;
    }

    public void setOrderEnterDocCd(String orderEnterDocCd) {
        this.orderEnterDocCd = orderEnterDocCd;
    }

    public String getOrderIsRecived() {
        return orderIsRecived;
    }

    public void setOrderIsRecived(String orderIsRecived) {
        this.orderIsRecived = orderIsRecived;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeNameAr() {
        return typeNameAr;
    }

    public void setTypeNameAr(String typeNameAr) {
        this.typeNameAr = typeNameAr;
    }

    public String getOrderDepCd() {
        return orderDepCd;
    }

    public void setOrderDepCd(String orderDepCd) {
        this.orderDepCd = orderDepCd;
    }

    public String getOrderLocCd() {
        return orderLocCd;
    }

    public void setOrderLocCd(String orderLocCd) {
        this.orderLocCd = orderLocCd;
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

    public String getFullempName() {
        return fullempName;
    }

    public void setFullempName(String fullempName) {
        this.fullempName = fullempName;
    }

    public String getRadOrderMchCd() {
        return radOrderMchCd;
    }

    public void setRadOrderMchCd(String radOrderMchCd) {
        this.radOrderMchCd = radOrderMchCd;
    }

    public String getOrderVoucherNumber() {
        return orderVoucherNumber;
    }

    public void setOrderVoucherNumber(String orderVoucherNumber) {
        this.orderVoucherNumber = orderVoucherNumber;
    }

    public String getOrderdResultFinal() {
        return orderdResultFinal;
    }

    public void setOrderdResultFinal(String orderdResultFinal) {
        this.orderdResultFinal = orderdResultFinal;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getRadIsNight() {
        return radIsNight;
    }

    public void setRadIsNight(String radIsNight) {
        this.radIsNight = radIsNight;
    }

    public String getPateintAge() {
        return pateintAge;
    }

    public void setPateintAge(String pateintAge) {
        this.pateintAge = pateintAge;
    }

}
