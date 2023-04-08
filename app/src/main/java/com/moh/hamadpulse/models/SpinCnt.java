package com.moh.hamadpulse.models;

public class SpinCnt {

    public SpinCnt(String SPIN_CODE, String SPIN_NAME) {
        this.SPIN_CODE = SPIN_CODE;
        this.SPIN_NAME = SPIN_NAME;
    }

    /**
     * SPIN_CODE : 1
     * SPIN_NAME : stable
     */


    private String SPIN_CODE;
    private String SPIN_NAME;

    public String getSPIN_CODE() {
        return SPIN_CODE;
    }

    public void setSPIN_CODE(String SPIN_CODE) {
        this.SPIN_CODE = SPIN_CODE;
    }

    public String getSPIN_NAME() {
        return SPIN_NAME;
    }

    public void setSPIN_NAME(String SPIN_NAME) {
        this.SPIN_NAME = SPIN_NAME;
    }

    @Override
    public String toString() {
        return SPIN_NAME;
    }
}
