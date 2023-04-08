package com.moh.hamadpulse.fragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.models.AppInfoModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class VersionInfoFragment extends Fragment {


    TextView version, desc, by, reserved, check_updates;
    String version_str, appPackageName;
    ImageView img_update;
    LinearLayout site_linear, store_linear, ios_linear;
    ImageButton copy_site, copy_store, copy_ios;

    Button update;

    ClipboardManager clipboard;
    PackageManager manager;
    AppInfoModel model;


    public VersionInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_versioninfo, container, false);

        ((HomeActivity) getActivity()).setActionBarTitle("معلومات التطبيق");

        version = (TextView) view.findViewById(R.id.version);
        desc = (TextView) view.findViewById(R.id.desc);
        by = (TextView) view.findViewById(R.id.by);
        reserved = (TextView) view.findViewById(R.id.reserved);
        site_linear = view.findViewById(R.id.site_linear);
        store_linear = view.findViewById(R.id.store_linear);
        img_update = view.findViewById(R.id.img_update);
        copy_store = view.findViewById(R.id.copy_store);
        check_updates = view.findViewById(R.id.check_updates);
        update = view.findViewById(R.id.update);
        getVersionNumber();

        clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        manager = getContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getContext().getPackageName(), 0);
            version_str = info.versionName;
            appPackageName = info.packageName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version.setText("الاصدار V " + version_str+"("+Controller.DATABASE_V+")");




        store_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getContext().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });



        copy_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText("store_link",
                        "https://play.google.com/store/apps/details?id=com.moh.hamadpulse");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "تم النسخ الي الحافظة", Toast.LENGTH_SHORT).show();


            }
        });


        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_info);
        item.setVisible(true);
        super.onPrepareOptionsMenu(menu);

    }


    private void getVersionNumber() {
        Map<String, String> map = new HashMap<>();
        MyRequest.makeRquest(getContext(),
                Controller.APP_INFO_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {
                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("app_info");
                            Gson gson = new Gson();
                            model = gson.fromJson(jsonObject.toString(),
                                    AppInfoModel.class);
                            CheckVersion(model.getVersion());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
//                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        Controller.view_error(error, getContext());
                    }
                });

    }

    public void CheckVersion(String version_number) {
        manager = getContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getContext().getPackageName(), 0);
            version_str = info.versionName;
            appPackageName = info.packageName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }

        try {

            String mLatestVersionName = version_number;

            if (mLatestVersionName != null && !mLatestVersionName.isEmpty()) {

                if (Float.valueOf(version_str) < Float.valueOf(mLatestVersionName)) {
                    //show dialog
                    check_updates.setText("التطبيق بحاجة للتحديث .. اخر نسخة " + mLatestVersionName);
                    img_update.setImageResource(R.drawable.software);
                    update.setVisibility(View.VISIBLE);
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        }
                    });
                } else {
                    check_updates.setText("لا يوجد تحديثات");
                    img_update.setImageResource(R.drawable.updated);
                    update.setVisibility(View.GONE);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
