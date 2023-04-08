package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProtocolsResultModel {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("PROTOCOL_NAME")
    @Expose
    private String protocolName;
    @SerializedName("PROTOCOL_TYPE")
    @Expose
    private String protocolType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }
}
