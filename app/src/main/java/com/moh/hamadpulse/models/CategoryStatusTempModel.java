package com.moh.hamadpulse.models;

public class CategoryStatusTempModel {
    private String category, status, status_number;

    public CategoryStatusTempModel(String category, String status, String status_number) {
        this.category = category;
        this.status = status;
        this.status_number = status_number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus_number() {
        return status_number;
    }

    public void setStatus_number(String status_number) {
        this.status_number = status_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
