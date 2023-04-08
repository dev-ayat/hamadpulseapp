package com.moh.hamadpulse.models;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class PatientServices {
    Fragment mFragment;
    Intent mIntent;
    String title;
    int image_id;

    public PatientServices(Fragment mFragment, Intent mIntent, String title) {
        this.mFragment = mFragment;
        this.mIntent = mIntent;
        this.title = title;
    }

    public PatientServices(Fragment mFragment, Intent mIntent, String title, int image_id) {
        this.mFragment = mFragment;
        this.mIntent = mIntent;
        this.title = title;
        this.image_id = image_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Intent getmIntent() {
        return mIntent;
    }

    public void setmIntent(Intent mIntent) {
        this.mIntent = mIntent;
    }

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
