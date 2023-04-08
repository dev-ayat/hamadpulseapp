package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class FavMedModel {
    @SerializedName("F_MED_CD")
    @Expose
    private String fMedCd;
    @SerializedName("F_USER_ID")
    @Expose
    private String fUserId;
    @SerializedName("F_MED_CODE")
    @Expose
    private String fMedCode;
    @SerializedName("F_MED_NAME")
    @Expose
    private String fMedName;


    public FavMedModel(String fMedCode) {
        this.fMedCode = fMedCode;
    }

    public FavMedModel() {
    }

    public String getFMedCd() {
        return fMedCd;
    }

    public void setFMedCd(String fMedCd) {
        this.fMedCd = fMedCd;
    }

    public String getFUserId() {
        return fUserId;
    }

    public void setFUserId(String fUserId) {
        this.fUserId = fUserId;
    }

    public String getFMedCode() {
        return fMedCode;
    }

    public void setFMedCode(String fMedCode) {
        this.fMedCode = fMedCode;
    }

    public String getFMedName() {
        return fMedName;
    }

    public void setFMedName(String fMedName) {
        this.fMedName = fMedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavMedModel that = (FavMedModel) o;
        return Objects.equals(fMedCode, that.fMedCode);
    }


}
