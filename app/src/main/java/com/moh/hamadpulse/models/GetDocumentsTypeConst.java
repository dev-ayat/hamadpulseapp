
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDocumentsTypeConst {

    @SerializedName("SERIAL_ID")
    @Expose
    private String sERIALID;
    @SerializedName("FILE_TYPE_SID")
    @Expose
    private String fILETYPESID;
    @SerializedName("DOC_TYPE_NAME")
    @Expose
    private String dOCTYPENAME;
    @SerializedName("DOC_TYPE_CD")
    @Expose
    private String dOCTYPECD;

    /**
     * No args constructor for use in serialization
     */
    public GetDocumentsTypeConst() {
    }

    /**
     * @param dOCTYPENAME
     * @param dOCTYPECD
     */
    public GetDocumentsTypeConst(String dOCTYPENAME, String dOCTYPECD) {
        super();
        this.dOCTYPENAME = dOCTYPENAME;
        this.dOCTYPECD = dOCTYPECD;
    }

    public String getSERIALID() {
        return sERIALID;
    }

    public void setSERIALID(String sERIALID) {
        this.sERIALID = sERIALID;
    }

    public String getFILETYPESID() {
        return fILETYPESID;
    }

    public void setFILETYPESID(String fILETYPESID) {
        this.fILETYPESID = fILETYPESID;
    }

    public String getDOCTYPENAME() {
        return dOCTYPENAME;
    }

    public void setDOCTYPENAME(String dOCTYPENAME) {
        this.dOCTYPENAME = dOCTYPENAME;
    }

    public String getDOCTYPECD() {
        return dOCTYPECD;
    }

    public void setDOCTYPECD(String dOCTYPECD) {
        this.dOCTYPECD = dOCTYPECD;
    }

}
