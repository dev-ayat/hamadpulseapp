package com.moh.hamadpulse.models;

import android.graphics.Bitmap;

public class photoBitmapModel {
    private Bitmap bitmap;

    public photoBitmapModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
