package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBloodTransfer {

    @SerializedName("ORDERM_CODE")
    @Expose
    private String ordermCode;
    @SerializedName("ORDERM_RECEIVE_DATE")
    @Expose
    private String ordermReceiveDate;
    @SerializedName("BLOOD_GROUP_NAME_AR")
    @Expose
    private String bloodGroupNameAr;
    @SerializedName("FULL_NAME")
    @Expose
    private String fullName;
    @SerializedName("RESERVE_NAME_AR")
    @Expose
    private String reserveNameAr;
    @SerializedName("UNIT_TYPE")
    @Expose
    private String UNIT_TYPE;
    @SerializedName("UNIT_NO")
    @Expose
    private String UNIT_NO;


    /**
     * No args constructor for use in serialization
     */
    public GetBloodTransfer() {
    }

    /**
     * @param ordermReceiveDate
     * @param ordermCode
     * @param fullName
     * @param reserveNameAr
     * @param bloodGroupNameAr
     */
    public GetBloodTransfer(String ordermCode, String ordermReceiveDate, String bloodGroupNameAr,
                            String fullName, String reserveNameAr, String UNIT_TYPE, String UNIT_NO) {
        super();
        this.ordermCode = ordermCode;
        this.ordermReceiveDate = ordermReceiveDate;
        this.bloodGroupNameAr = bloodGroupNameAr;
        this.fullName = fullName;
        this.reserveNameAr = reserveNameAr;
        this.UNIT_TYPE = UNIT_TYPE;
        this.UNIT_NO = UNIT_NO;
    }

    public String getOrdermCode() {
        return ordermCode;
    }

    public void setOrdermCode(String ordermCode) {
        this.ordermCode = ordermCode;
    }

    public String getOrdermReceiveDate() {
        return ordermReceiveDate;
    }

    public void setOrdermReceiveDate(String ordermReceiveDate) {
        this.ordermReceiveDate = ordermReceiveDate;
    }

    public String getBloodGroupNameAr() {
        return bloodGroupNameAr;
    }

    public void setBloodGroupNameAr(String bloodGroupNameAr) {
        this.bloodGroupNameAr = bloodGroupNameAr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getReserveNameAr() {
        return reserveNameAr;
    }

    public void setReserveNameAr(String reserveNameAr) {
        this.reserveNameAr = reserveNameAr;
    }

    public String getUNIT_TYPE() {
        return UNIT_TYPE;
    }

    public void setUNIT_TYPE(String UNIT_TYPE) {
        this.UNIT_TYPE = UNIT_TYPE;
    }

    public String getUNIT_NO() {
        return UNIT_NO;
    }

    public void setUNIT_NO(String UNIT_NO) {
        this.UNIT_NO = UNIT_NO;
    }
}
