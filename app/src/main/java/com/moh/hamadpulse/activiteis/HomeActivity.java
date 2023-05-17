package com.moh.hamadpulse.activiteis;

import static com.moh.hamadpulse.constants.ConstShared.DEPT_CODE;
import static com.moh.hamadpulse.constants.ConstShared.NURSE_DEPT_CD_SELEC;
import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.ExpandableListAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.fragment.Fragment_View_All_Requests;
import com.moh.hamadpulse.fragment.HomeFragment;
import com.moh.hamadpulse.fragment.NursingDeptFragment;
import com.moh.hamadpulse.fragment.ParChartFragment;
import com.moh.hamadpulse.fragment.PatientFragment;
import com.moh.hamadpulse.fragment.PieChartFragment;
import com.moh.hamadpulse.fragment.VersionInfoFragment;
import com.moh.hamadpulse.fragment.newradresFragment;
import com.moh.hamadpulse.models.MenuModel;
import com.moh.hamadpulse.models.PrivMenu;
import com.moh.hamadpulse.models.Screen;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, InterfacePatient {
    TextView txth_username, txtdept;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    AlertDialog alertDialog;
    public static final int REQUEST_CODE_PHONE_STATE_READ = 100;
    private int checkedPermission = PackageManager.PERMISSION_DENIED;
    TelephonyManager manager;
    InterfacePatient mInterfacePatient;
    private AVLoadingIndicatorView imgLoading;

    public void setOrientation() {


        switch (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                Log.e("test", "SCREENLAYOUT_SIZE_XLARGE");
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                Log.e("test", "SCREENLAYOUT_SIZE_LARGE");
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                Log.e("test", "SCREENLAYOUT_SIZE_NORMAL");
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                Log.e("test", "SCREENLAYOUT_SIZE_SMALL");
                break;
            default:
                Log.e("test", "default");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void requestPermission() {
        Toast.makeText(HomeActivity.this, "Requesting permission", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_PHONE_STATE_READ);
        } else {
            showDeviceInfo();
        }
    }

    public void showDeviceInfo() {
        manager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        AlertDialog.Builder dBuilder = new AlertDialog.Builder(this);
        StringBuilder stringBuilder = new StringBuilder();
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            String serial = Build.getRadioVersion();
            dBuilder.setTitle("Device Info");
            stringBuilder.append("SERIAL : " + serial.hashCode() + "\n");
        } else {
            dBuilder.setTitle("Permission denied");
            stringBuilder.append("Can't access device info !");
        }
        dBuilder.setMessage(stringBuilder);
        dBuilder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PHONE_STATE_READ:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkedPermission = PackageManager.PERMISSION_GRANTED;
                }
                break;
        }
    }

    void register_token(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")) + "");
        map.put("P_FCM_TOKEN", token);
        map.put("P_USER_TYPE", Controller.pref.getString(USER_TYPE, ""));


        if ((Controller.pref.getString(USER_TYPE, "").equals("1") ||
                Controller.pref.getString(USER_TYPE, "").equals("3"))) {
            map.put("P_USER_DEPT_CD", Controller.pref.getString(DEPT_CODE, ""));

        } else if ((Controller.pref.getString(USER_TYPE, "").equals("2") ||
                Controller.pref.getString(USER_TYPE, "").equals("4"))) {
            map.put("P_USER_DEPT_CD", Controller.pref.getString(NURSE_DEPT_CD_SELEC, ""));

        }

//        map.put("TRANS_SCREEN_CD_IN", 37 + "");
//        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
//        map.put("TRANS_ACTION_CD_IN", "1");
//        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
//                getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
//        map.put("TRANS_DESCRIPTION_IN", "Add new Diabetic");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("map", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GENERAT_TOKEN_ON_LOGIN_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1 || res == 2) {
                        Toast.makeText(getBaseContext(), "تمت تفعيل خاصية الإشعارت لهذا المستخدم", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "لم يتم تفعيل خاصية الإشعارت لهذا المستخدم", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
//                Controller.view_error(volleyError,getco);
                Log.e("volley error", volleyError.toString());
//                mInterfacePatient.showLoading(false);
            }
        });
        jsObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                // Here goes the new timeout 3 minutes
                return 3 * 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                // The max number of attempts
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mInterfacePatient = this;
//        requestPermission();


//        Log.e("serial",Device.getSerialNumber()+"");
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.e("token_err", "no token");
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.e("token", token);
//                        Log.e("user_id", (Controller.pref.getString("USER_ID", "")));
//                        Controller.editor.putString("FCM_TOKEN", token);
////                        String user_Id= Controller.editor.putString("FCM_TOKEN", token);
////
//                        register_token(token);
//                        // Log and toast
//                        Log.e("token", token);
//                    }
//                });
        imgLoading = findViewById(R.id.imgLoading);
        setOrientation();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));
        Controller.LOADER_DIALOG = new DialogLoding(this);
        Controller.Msg_DIALOG = new DialogMsg(this);
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txth_username = headerView.findViewById(R.id.txth_username);
        txtdept = headerView.findViewById(R.id.txtdept);
        txth_username.setText(Controller.pref.getString("USER_NAME", ""));
        txtdept.setText(Controller.pref.getString("DEPT_NAME_AR", ""));
        ImageView pernavimg = (ImageView) headerView.findViewById(R.id.pernavimg);
        Picasso.with(this)
                .load(Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""))
                .into(pernavimg);
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, homeFragment);
        HomeActivity homeActivity = new HomeActivity();
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else
                super.onBackPressed();
        }


    }

    public void close_app() {

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("هل تريد تسجيل الخروج ؟");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "لا", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "نعم", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                headerList.clear();
                clear_Token();
                finish();
                Controller.editor.putString("LOGIN_MODE", "0");
                Controller.editor.commit();


            }
        });
        alertDialog.show();
    }

    private void clear_Token() {
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")) + "");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.CLEAR_TOKEN_ON_LOGOUT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getBaseContext(), "تمت إلغاء تفعيل خاصية الإشعارت لهذا المستخدم", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "لم يتم إلغاء تفعيل خاصية الإشعارت لهذا المستخدم", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
//                Controller.view_error(volleyError,getco);
                Log.e("volley error", volleyError.toString());
//                mInterfacePatient.showLoading(false);
            }
        });
        jsObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                // Here goes the new timeout 3 minutes
                return 3 * 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                // The max number of attempts
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    public void open_icd10() {
        startActivity(new Intent(this, icd10Activity.class));
    }

    public void show_onboarding() {
        startActivity(new Intent(this, boardingActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem item = menu.findItem(R.id.action_dept);
        MenuItem nursedept = menu.findItem(R.id.nurse_dept);
        MenuItem infoversion = menu.findItem(R.id.action_info);
        MenuItem adminNurse = menu.findItem(R.id.admin_nurse_dept);

//        item.setVisible(false);
        nursedept.setVisible(false);
        adminNurse.setVisible(false);
        infoversion.setVisible(true);

        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        Log.e("TEST",id +" "+R.id.action_dept+" "+R.id.nurse_dept);
//        if (id == R.id.action_dept) {
//            DepartmentsFragment departmentsFragment = new DepartmentsFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            departmentsFragment.show(ft, "tag");
//        }

        if (id == R.id.nurse_dept) {
            NursingDeptFragment nursingDeptFragment = new NursingDeptFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            nursingDeptFragment.show(ft, "tag");

        }

        if (id == R.id.admin_nurse_dept) {
            NursingDeptFragment nursingDeptFragment = new NursingDeptFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            nursingDeptFragment.show(ft, "tag");
        }

        if (id == R.id.action_info) {
            VersionInfoFragment mversionInfoFragment = new VersionInfoFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, mversionInfoFragment);
                ft.addToBackStack(null);
                ft.commit();

        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //make this method blank
        return true;
    }


    private void prepareMenuData() {
        headerList.clear();
        MenuModel menuModel;
//        menuModel = new MenuModel("الرئيسية", true, false, 0); //PrivMenu of Android Tutorial. No sub menus
//        headerList.add(menuModel);
        for (int i = 0; i < Controller.PrivMENUS.size(); i++) {

            String name = Controller.PrivMENUS.get(i).getName();
            int menuid = Controller.PrivMENUS.get(i).getId();
            menuModel = new MenuModel(name, true, true, menuid); //PrivMenu of Java Tutorials
            headerList.add(menuModel);

            if (!menuModel.hasChildren) {
                childList.put(menuModel, null);
            }

            List<MenuModel> childModelsList = new ArrayList<>();
            for (int j = 0; j < Controller.PrivMENUS.get(i).getScreens().size(); j++) {

                String screen = Controller.PrivMENUS.get(i).getScreens().get(j).getName();
                int screenid = Controller.PrivMENUS.get(i).getScreens().get(j).getId();

                MenuModel childModel = new MenuModel(screen, false, false, screenid);
                childModelsList.add(childModel);
            }

            if (menuModel.hasChildren) {
                Log.d("API123", "here");
                childList.put(menuModel, childModelsList);
            }

        }
        menuModel = new MenuModel("الاستعلام عن icd10", true, false, 1); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("حول التطبيق", true, false, 2); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("تسجيل الخروج", true, false, 3); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);


    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        FrameLayout fragment = findViewById(R.id.content_frame);
                        int menuid = headerList.get(groupPosition).getMenuid();
                        Fragment myfragment = null;
                        Fragment homefragment = new HomeFragment();
                        switch (menuid) {
                            case 1:
                                open_icd10();
                                break;
                            case 2:
                                show_onboarding();
                                break;
                            case 3:
                                close_app();
                                break;
                        }
                        if (myfragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, homefragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {

                    PrivMenu menu = Controller.PrivMENUS.get(groupPosition);
                    Screen screen = menu.getScreens().get(childPosition);
                    // Toast.makeText(HomeActivity.this, "screen id:"+screen.getId(), Toast.LENGTH_SHORT).show();
                    // Log.e( "onChildClick: ","screen id"+screen.getId() );
                    FrameLayout fragment = findViewById(R.id.content_frame);
                    int screenid = screen.getId();
                    Fragment myfragment = null;
                    switch (screenid) {

                        case 537:
                        case 554:
                            ///spc_dept
                            ///end spc dept
                            myfragment = new PatientFragment(screenid);
                            break;
                        //rad
                        case 2587:
                            myfragment = new newradresFragment(mInterfacePatient, true);
                            break;
                        case 765:
                            myfragment = new HomeFragment();
                            break;
                        case 770:
                            myfragment = new PieChartFragment();
                            break;
                        case 779:
                            myfragment = new ParChartFragment();
                            break;
                        case 1332:
                            myfragment = new PatientFragment(screenid);
                            break;
                        case 1689:
                            myfragment = new Fragment_View_All_Requests();
                            break;
                    }

                    if (myfragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, myfragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    onBackPressed();
                }
                return false;
            }
        });
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public void showLoading(boolean b) {
        if (b)
            imgLoading.setVisibility(View.VISIBLE);
        else
            imgLoading.setVisibility(View.GONE);
    }


}
