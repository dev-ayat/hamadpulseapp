package com.moh.hamadpulse.models;

public class CoronaHistory {


    /**
     * NO : 1
     * PATIENT_ID : 800677262
     * PATIENT_NAME : خلود جمال علي حمد
     * REQUEST_DATE : 26-SEP-20
     * RESULT_DATE : 28-SEP-20
     * TEST_RESULT : Positive
     */

    private int NO;
    private String PATIENT_ID;
    private String PATIENT_NAME;
    private String REQUEST_DATE;
    private String RESULT_DATE;
    private String TEST_RESULT;

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public String getPATIENT_ID() {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(String PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getPATIENT_NAME() {
        return PATIENT_NAME;
    }

    public void setPATIENT_NAME(String PATIENT_NAME) {
        this.PATIENT_NAME = PATIENT_NAME;
    }

    public String getREQUEST_DATE() {
        return REQUEST_DATE;
    }

    public void setREQUEST_DATE(String REQUEST_DATE) {
        this.REQUEST_DATE = REQUEST_DATE;
    }

    public String getRESULT_DATE() {
        return RESULT_DATE;
    }

    public void setRESULT_DATE(String RESULT_DATE) {
        this.RESULT_DATE = RESULT_DATE;
    }

    public String getTEST_RESULT() {
        return TEST_RESULT;
    }

    public void setTEST_RESULT(String TEST_RESULT) {
        this.TEST_RESULT = TEST_RESULT;
    }
}
