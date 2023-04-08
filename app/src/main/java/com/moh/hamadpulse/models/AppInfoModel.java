package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppInfoModel {
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("database_version")
    @Expose
    private String database_version;

    public String getDatabase_version() {
        return database_version;
    }

    public void setDatabase_version(String database_version) {
        this.database_version = database_version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
