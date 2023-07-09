
package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRadprecautions {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("LOOKUP_DETAILS")
    @Expose
    private String lookupDetails;
    @SerializedName("ISACTIVE")
    @Expose
    private String isactive;
    @SerializedName("PARENT_ID")
    @Expose
    private String parentId;
    @SerializedName("DELETED_AT")
    @Expose
    private Object deletedAt;
    @SerializedName("CREATED_AT")
    @Expose
    private Object createdAt;
    @SerializedName("UPDATED_AT")
    @Expose
    private Object updatedAt;

    /**
     * No args constructor for use in serialization
     */
    public GetRadprecautions() {
    }

    /**
     * @param createdAt
     * @param deletedAt
     * @param isactive
     * @param id
     * @param lookupDetails
     * @param parentId
     * @param updatedAt
     */
    public GetRadprecautions(String id, String lookupDetails, String isactive, String parentId, Object deletedAt, Object createdAt, Object updatedAt) {
        super();
        this.id = id;
        this.lookupDetails = lookupDetails;
        this.isactive = isactive;
        this.parentId = parentId;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLookupDetails() {
        return lookupDetails;
    }

    public void setLookupDetails(String lookupDetails) {
        this.lookupDetails = lookupDetails;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}