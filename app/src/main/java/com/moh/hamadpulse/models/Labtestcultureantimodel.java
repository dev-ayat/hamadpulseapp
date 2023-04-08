package com.moh.hamadpulse.models;

public class Labtestcultureantimodel {
    //String antibiotic, antiA, antiB, antiC;
    String header;

    /**
     * RESULT_ORDER_DETAILS_CD : 719374
     * D_ORDER_CD : 10113066
     * TEST_NAME_EN : Cefuroxime
     * CULTURE_A_ABBR : R
     * CULTURE_B_ABBR : null
     * CULTURE_C_ABBR : null
     */

    private String RESULT_ORDER_DETAILS_CD;
    private String D_ORDER_CD;
    private String TEST_NAME_EN;
    private String CULTURE_A_ABBR;
    private String CULTURE_B_ABBR;
    private String CULTURE_C_ABBR;

    public Labtestcultureantimodel(String TEST_NAME_EN, String CULTURE_A_ABBR, String CULTURE_B_ABBR, String CULTURE_C_ABBR) {
        this.TEST_NAME_EN = TEST_NAME_EN;
        this.CULTURE_A_ABBR = CULTURE_A_ABBR;
        this.CULTURE_B_ABBR = CULTURE_B_ABBR;
        this.CULTURE_C_ABBR = CULTURE_C_ABBR;
    }

    public String getRESULT_ORDER_DETAILS_CD() {
        return RESULT_ORDER_DETAILS_CD;
    }

    public void setRESULT_ORDER_DETAILS_CD(String RESULT_ORDER_DETAILS_CD) {
        this.RESULT_ORDER_DETAILS_CD = RESULT_ORDER_DETAILS_CD;
    }

    public String getD_ORDER_CD() {
        return D_ORDER_CD;
    }

    public void setD_ORDER_CD(String D_ORDER_CD) {
        this.D_ORDER_CD = D_ORDER_CD;
    }

    public String getTEST_NAME_EN() {
        return TEST_NAME_EN;
    }

    public void setTEST_NAME_EN(String TEST_NAME_EN) {
        this.TEST_NAME_EN = TEST_NAME_EN;
    }

    public String getCULTURE_A_ABBR() {
        return CULTURE_A_ABBR;
    }

    public void setCULTURE_A_ABBR(String CULTURE_A_ABBR) {
        this.CULTURE_A_ABBR = CULTURE_A_ABBR;
    }

    public String getCULTURE_B_ABBR() {
        return CULTURE_B_ABBR;
    }

    public void setCULTURE_B_ABBR(String CULTURE_B_ABBR) {
        this.CULTURE_B_ABBR = CULTURE_B_ABBR;
    }

    public String getCULTURE_C_ABBR() {
        return CULTURE_C_ABBR;
    }

    public void setCULTURE_C_ABBR(String CULTURE_C_ABBR) {
        this.CULTURE_C_ABBR = CULTURE_C_ABBR;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
