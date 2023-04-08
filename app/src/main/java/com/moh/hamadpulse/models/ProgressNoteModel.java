package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgressNoteModel {
    @SerializedName("PROGRESS_NOTE")
    @Expose
    private String progress_note;

    public String getProgress_note() {
        return progress_note;
    }

    public void setProgress_note(String progress_note) {
        this.progress_note = progress_note;
    }
}
