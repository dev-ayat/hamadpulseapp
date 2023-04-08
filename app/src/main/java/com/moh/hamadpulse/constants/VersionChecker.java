package com.moh.hamadpulse.constants;

import android.os.AsyncTask;
import android.util.Log;

import com.moh.hamadpulse.BuildConfig;

import org.jsoup.Jsoup;

import java.io.IOException;

public class VersionChecker extends AsyncTask<String, String, String> {

    private String newVersion;

    @Override
    protected String doInBackground(String... params) {
        Log.e("Mariam_about_traccing", "8");

        try {
            newVersion =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=en")
                            .timeout(30000)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .get()
                            .select(".hAyfc .htlgb")
                            .get(7)
                            .ownText();
            Log.e("Mariam_about_traccing", "9");
        } catch (IOException e) {
//           Log.d("login_exception",e.toString());
        }
        return newVersion;
    }

    @Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
//        String currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);
//        if (onlineVersion != null && !onlineVersion.isEmpty()) {
//            if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
//                //show dialog
//            }
    }
}
