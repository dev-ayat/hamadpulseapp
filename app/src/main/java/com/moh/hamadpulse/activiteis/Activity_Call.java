package com.moh.hamadpulse.activiteis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.moh.hamadpulse.constants.Extra;
import com.moh.hamadpulse.R;


public class Activity_Call extends AppCompatActivity {
    String CallNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        CallNumber = getIntent().getStringExtra(com.moh.hamadpulse.constants.Extra.EXTRA_CALL_NUMBER);
        makeCall(CallNumber);
    }

    public void makeCall(String mobileNo)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        callIntent.setData(Uri.parse("tel:" + mobileNo));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, Extra.REQUEST_PHONE_CALL);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, Extra.REQUEST_PHONE_CALL);
            }
        } else {
            startActivity(callIntent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Extra.REQUEST_PHONE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall(CallNumber);
                }
                else
                {
                    finish();
                }
                break;
        }
    }
}
