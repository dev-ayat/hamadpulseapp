
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadPositionConst {

    @SerializedName("C_RAD_POSITION_CODE")
    @Expose
    private String cRADPOSITIONCODE;
    @SerializedName("C_RAD_POSITION_NAME_EN")
    @Expose
    private String cRADPOSITIONNAMEEN;

    /**
     * No args constructor for use in serialization
     */
    public GetRadPositionConst() {
    }

    /**
     * @param cRADPOSITIONCODE
     * @param cRADPOSITIONNAMEEN
     */
    public GetRadPositionConst(String cRADPOSITIONCODE, String cRADPOSITIONNAMEEN) {
        super();
        this.cRADPOSITIONCODE = cRADPOSITIONCODE;
        this.cRADPOSITIONNAMEEN = cRADPOSITIONNAMEEN;
    }

    public String getCRADPOSITIONCODE() {
        return cRADPOSITIONCODE;
    }

    public void setCRADPOSITIONCODE(String cRADPOSITIONCODE) {
        this.cRADPOSITIONCODE = cRADPOSITIONCODE;
    }

    public String getCRADPOSITIONNAMEEN() {
        return cRADPOSITIONNAMEEN;
    }

    public void setCRADPOSITIONNAMEEN(String cRADPOSITIONNAMEEN) {
        this.cRADPOSITIONNAMEEN = cRADPOSITIONNAMEEN;
    }

}
