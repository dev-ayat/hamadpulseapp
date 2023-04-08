package com.moh.hamadpulse.models;

import java.util.Objects;

public class ExamCnt {

    private String EXAM_CODE;
    private String EXAM_NAME;

    public ExamCnt(String EXAM_CODE, String EXAM_NAME) {
        this.EXAM_CODE = EXAM_CODE;
        this.EXAM_NAME = EXAM_NAME;
    }

    public ExamCnt(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamCnt examCnt = (ExamCnt) o;
        return Objects.equals(EXAM_NAME, examCnt.EXAM_NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EXAM_NAME);
    }

    public String getEXAM_CODE() {
        return EXAM_CODE;
    }

    public void setEXAM_CODE(String EXAM_CODE) {
        this.EXAM_CODE = EXAM_CODE;
    }

    public String getEXAM_NAME() {
        return EXAM_NAME;
    }

    public void setEXAM_NAME(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
    }

    @Override
    public String toString() {
        return EXAM_NAME;
    }

}
