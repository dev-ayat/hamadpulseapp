package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfo {

    private final static long serialVersionUID = 8291123096105508909L;
    @SerializedName("Instance")
    @Expose
    private String instance;
    @SerializedName("time_instance")
    @Expose
    private String timeInstance;
    @SerializedName("instance_timestamp")
    @Expose
    private String instanceTimestamp;
    @SerializedName("bodypartexamined")
    @Expose
    private String bodypartexamined;
    @SerializedName("modality")
    @Expose
    private String modality;
    @SerializedName("orthanc_web_viewer")
    @Expose
    private String orthancWebViewer;
    @SerializedName("image_base64")
    @Expose
    private String imageBase64;
    @SerializedName("image_link")
    @Expose
    private String imageLink;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getTimeInstance() {
        return timeInstance;
    }

    public void setTimeInstance(String timeInstance) {
        this.timeInstance = timeInstance;
    }

    public String getInstanceTimestamp() {
        return instanceTimestamp;
    }

    public void setInstanceTimestamp(String instanceTimestamp) {
        this.instanceTimestamp = instanceTimestamp;
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

    public String getOrthancWebViewer() {
        return orthancWebViewer;
    }

    public void setOrthancWebViewer(String orthancWebViewer) {
        this.orthancWebViewer = orthancWebViewer;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
