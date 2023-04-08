package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allergy_Model {

    @SerializedName("ALLERGY_CD")
    @Expose
    private String allergyCd;
    @SerializedName("ALLERGY_NAME")
    @Expose
    private String allergyName;
    @SerializedName("ALLERGY_TYPE")
    @Expose
    private String allergyType;

    public Allergy_Model(String allergyCd, String allergyName) {
        this.allergyCd = allergyCd;
        this.allergyName = allergyName;
    }


    public String getAllergyCd() {
        return allergyCd;
    }

    public void setAllergyCd(String allergyCd) {
        this.allergyCd = allergyCd;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }
}
