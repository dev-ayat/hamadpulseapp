
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDoctorsOrders {

    private final static long serialVersionUID = 5386157788415879022L;
    @SerializedName("DOC_ORDER_CODE")
    @Expose
    private String docOrderCode;
    @SerializedName("DOC_ORDER_ADM_CD")
    @Expose
    private String docOrderAdmCd;
    @SerializedName("DOC_ORDER_PATREC_CD")
    @Expose
    private String docOrderPatrecCd;
    @SerializedName("DOC_ORDER_NOTE")
    @Expose
    private String docOrderNote;
    @SerializedName("DOC_CREATED_BY_CD")
    @Expose
    private String docCreatedByCd;
    @SerializedName("USER_ID")
    @Expose
    private String userId;
    @SerializedName("DOC_CREATED_ON")
    @Expose
    private String docCreatedOn;
    @SerializedName("DOC_NAME")
    @Expose
    private String docName;

    /**
     * No args constructor for use in serialization
     */
    public GetDoctorsOrders() {
    }

    /**
     * @param docName
     * @param docOrderAdmCd
     * @param docOrderNote
     * @param docOrderCode
     * @param docCreatedByCd
     * @param docOrderPatrecCd
     * @param userId
     * @param docCreatedOn
     */
    public GetDoctorsOrders(String docOrderCode, String docOrderAdmCd, String docOrderPatrecCd, String docOrderNote, String docCreatedByCd, String userId, String docCreatedOn, String docName) {
        super();
        this.docOrderCode = docOrderCode;
        this.docOrderAdmCd = docOrderAdmCd;
        this.docOrderPatrecCd = docOrderPatrecCd;
        this.docOrderNote = docOrderNote;
        this.docCreatedByCd = docCreatedByCd;
        this.userId = userId;
        this.docCreatedOn = docCreatedOn;
        this.docName = docName;
    }

    public String getDocOrderCode() {
        return docOrderCode;
    }

    public void setDocOrderCode(String docOrderCode) {
        this.docOrderCode = docOrderCode;
    }

    public String getDocOrderAdmCd() {
        return docOrderAdmCd;
    }

    public void setDocOrderAdmCd(String docOrderAdmCd) {
        this.docOrderAdmCd = docOrderAdmCd;
    }

    public String getDocOrderPatrecCd() {
        return docOrderPatrecCd;
    }

    public void setDocOrderPatrecCd(String docOrderPatrecCd) {
        this.docOrderPatrecCd = docOrderPatrecCd;
    }

    public String getDocOrderNote() {
        return docOrderNote;
    }

    public void setDocOrderNote(String docOrderNote) {
        this.docOrderNote = docOrderNote;
    }

    public String getDocCreatedByCd() {
        return docCreatedByCd;
    }

    public void setDocCreatedByCd(String docCreatedByCd) {
        this.docCreatedByCd = docCreatedByCd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocCreatedOn() {
        return docCreatedOn;
    }

    public void setDocCreatedOn(String docCreatedOn) {
        this.docCreatedOn = docCreatedOn;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

}
