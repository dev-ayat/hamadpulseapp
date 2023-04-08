package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadViewer {

    @SerializedName("instance_id")
    @Expose
    private String instanceId;
    @SerializedName("instance_timestamp")
    @Expose
    private String instanceTimestamp;
    @SerializedName("PatientID")
    @Expose
    private String patientID;
    @SerializedName("PatientName")
    @Expose
    private String patientName;
    @SerializedName("bodypartexamined")
    @Expose
    private String bodypartexamined;
    @SerializedName("modality")
    @Expose
    private String modality;
    @SerializedName("ohiv_link")
    @Expose
    private String ohivLink;
    @SerializedName("instance_image")
    @Expose
    private String instanceImage;

    @SerializedName("image_base64")
    @Expose
    private String image_base64;

    @SerializedName("osimis_link")
    @Expose
    private String osimis_link;


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceTimestamp() {
        return instanceTimestamp;
    }

    public void setInstanceTimestamp(String instanceTimestamp) {
        this.instanceTimestamp = instanceTimestamp;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBodypartexamined() {
        return bodypartexamined;
    }

    public void setBodypartexamined(String bodypartexamined) {
        this.bodypartexamined = bodypartexamined;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getOhivLink() {
        return ohivLink;
    }

    public void setOhivLink(String ohivLink) {
        this.ohivLink = ohivLink;
    }

    public String getInstanceImage() {
        return instanceImage;
    }

    public void setInstanceImage(String instanceImage) {
        this.instanceImage = instanceImage;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

    public String getOsimis_link() {
        return osimis_link;
    }

    public void setOsimis_link(String osimis_link) {
        this.osimis_link = osimis_link;
    }
}

