
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadServicesConst {

    @SerializedName("SERVICE_CODE")
    @Expose
    private String sERVICECODE;
    @SerializedName("SERVICE_NAME_AR")
    @Expose
    private String sERVICENAMEAR;
    @SerializedName("SERVICE_NAME_EN")
    @Expose
    private String sERVICENAMEEN;
    @SerializedName("SERVICE_DESCRIPTION")
    @Expose
    private Object sERVICEDESCRIPTION;

    /**
     * No args constructor for use in serialization
     */
    public GetRadServicesConst() {
    }

    /**
     * @param sERVICEDESCRIPTION
     * @param sERVICENAMEEN
     * @param sERVICENAMEAR
     * @param sERVICECODE
     */
    public GetRadServicesConst(String sERVICECODE, String sERVICENAMEAR, String sERVICENAMEEN, String sERVICEDESCRIPTION) {
        super();
        this.sERVICECODE = sERVICECODE;
        this.sERVICENAMEAR = sERVICENAMEAR;
        this.sERVICENAMEEN = sERVICENAMEEN;
        this.sERVICEDESCRIPTION = sERVICEDESCRIPTION;
    }

    public String getSERVICECODE() {
        return sERVICECODE;
    }

    public void setSERVICECODE(String sERVICECODE) {
        this.sERVICECODE = sERVICECODE;
    }

    public String getSERVICENAMEAR() {
        return sERVICENAMEAR;
    }

    public void setSERVICENAMEAR(String sERVICENAMEAR) {
        this.sERVICENAMEAR = sERVICENAMEAR;
    }

    public String getSERVICENAMEEN() {
        return sERVICENAMEEN;
    }

    public void setSERVICENAMEEN(String sERVICENAMEEN) {
        this.sERVICENAMEEN = sERVICENAMEEN;
    }

    public String getSERVICEDESCRIPTION() {
        return (String) sERVICEDESCRIPTION;
    }

    public void setSERVICEDESCRIPTION(String sERVICEDESCRIPTION) {
        this.sERVICEDESCRIPTION = sERVICEDESCRIPTION;
    }

}
