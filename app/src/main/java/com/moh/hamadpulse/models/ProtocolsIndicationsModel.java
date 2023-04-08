package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProtocolsIndicationsModel {
    @SerializedName("INDICATION")
    @Expose
    private AllIndicationsModel indication;
    @SerializedName("ALL_PROTOCOL")
    @Expose
    private AllProtocolsModel allProtocols;

    public AllIndicationsModel getIndication() {
        return indication;
    }

    public void setIndication(AllIndicationsModel indication) {
        this.indication = indication;
    }

    public AllProtocolsModel getAllProtocols() {
        return allProtocols;
    }

    public void setAllProtocols(AllProtocolsModel allProtocols) {
        this.allProtocols = allProtocols;
    }
}
