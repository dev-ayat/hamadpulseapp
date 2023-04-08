package com.moh.hamadpulse.activiteis;

import static com.moh.hamadpulse.constants.ConstShared.EMP_SERIAL;
import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.AppInfoModel;
import com.moh.hamadpulse.models.PrivMenu;
import com.moh.hamadpulse.models.Screen;
import com.moh.hamadpulse.test;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = Controller.class.getSimpleName();
    public static String foldername = "";
    Thread splashTread;
    AppInfoModel model;
    ArrayList<HashMap<String, String>> permissions;
    SharedPreferences pref;
    TextView msg;
    TextView btnlogin;
    ImageView settings;
    //private LoadingDialog loader_dialog;
    ArrayList<HashMap<String, String>> USER_LOCATION = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> USER_SCREEN = new ArrayList<HashMap<String, String>>();
    EditText username, password;
    CheckBox svpasschBox;
    Animation animation, animation2;
    TextView result;
    String user, pass;
    private Dialog pDialog;
    private TextView txtResponse;
    private Controller controller;
    private EditText txtvar;
    AVLoadingIndicatorView imgLoading;
    public String ipAddress, host_name;
    public String Screen_cd = "1";

    PackageManager manager;
    String version_str, appPackageName;


    // temporary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Controller.LOADER_DIALOG = new DialogLoding(this);
        Controller.Msg_DIALOG = new DialogMsg(this);
        Log.d("testttt","test");
//         String android_id = Settings.Secure.getString(getBaseContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//         Log.e("android_id",Build.SERIAL);
//        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_PHONE_STATE }, 1);
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            Log.e("error-msg", Build.getSerial());
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
//        }else{
//            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
//            Log.e("serial",Build.getSerial());
//            else
//                Log.e("serial",Build.SERIAL);
//        }
        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        txtvar = findViewById(R.id.txtvar);
        settings = findViewById(R.id.settings);
        imgLoading = findViewById(R.id.imgLoading);
        svpasschBox = findViewById(R.id.svpasschBox);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        Controller.editor.putString("SAVE_LOGIN", "0");
        if (Controller.pref.getString("SAVE_LOGIN", "0").equals("0")) {
            Log.e("save", Controller.pref.getString("SAVE_LOGIN", "0"));
            username.setText(Controller.pref.getString("username", ""));
            password.setText(Controller.pref.getString("password", ""));
            txtvar.setText(Controller.pref.getString("foldername", ""));
            svpasschBox.setChecked(true);
        }


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        Controller.editor.putString("IP_Address", ipAddress);
        Log.d("is connected",Controller.IsConnected()+"");
//        if (Controller.IsConnected())
//            getVersionNumber();
//        else
//            check_netwrok_connection();
//        InetAddress addr = null;
//        try {
//            addr = InetAddress.getByName("192.168.1.1");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//         host_name = addr.getHostName();
//        Controller.editor.putString("host_name",host_name);

  //410186266
        // أوروبي
//           username.setText("939892279");
//            username.setText("901689760");
        //password.setText("123456");
        //     username.setText("920617917");
        //     password.setText("6wU02i");
//        locale
// اسامة ابو جبل
//        username.setText("993517325");
//        password.setText("XXX60s");
////
            username.setText("ADMIN");
          password.setText("123456");
//  nurse
//        username.setText("912277142");
//        password.setText("8GC55i");
        // Shifaa
//        username.setText("993517325");
//       password.setText("993517325");

//        username.setText("801033259");
//        password.setText("a223607");

        // aqsa
//          username.setText("925057085");
//           password.setText("e123123");

        // Turkish

        /// doctor
//        username.setText("902730860");
//        password.setText("0JE72m");
// nurse
//           username.setText("903484418");
//          password.setText("q8R24m");

        ///doctor besan

//           username.setText("803479609");
//          password.setText("PsA53g");

//pharmacist
//         username.setText("949816888");
//          password.setText("l8M72v");
//        Toast.makeText(getApplicationContext(), isConnectedViaWifi()+"",
//                Toast.LENGTH_LONG).show();

        // nurse
////

//        username.setText("900754490");
//        password.setText("FsQ92m");
//

        // RANTISI
//           password.setText("JUN23i");
//         username.setText("903280139");

        // RANTISI
        //      password.setText("a123123");
        //      username.setText("923916944");
        // andonisi
//          username.setText("804314276");
//         password.setText("N2H84s");
//     naser
//        username.setText("924927122");
//        password.setText("a123456");

//        doctor
//          username.setText("924927122");
//         password.setText("a123456");
//        Mental
//        username.setText("801269960");
//        password.setText("kRE96d");

//        Dora
//        username.setText("900972589");
//        password.setText("iz0599352908");
//        ----------------------------------------------
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer visiblity = txtvar.getVisibility();
                if (visiblity == 8) {
                    txtvar.setVisibility(View.VISIBLE);
                } else {
                    txtvar.setVisibility(View.GONE);

                }
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLoading.setVisibility(View.VISIBLE);
//                CheckVersion();
                checkuserpass();
            }
        });
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void CheckVersion(String version_number) {
        manager = getApplicationContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            Log.e("Mariam_about_traccing", "4");
            version_str = info.versionName;

            appPackageName = info.packageName;
            Log.d("versionnnn",appPackageName);
            Log.e("Mariam_about_traccing", "5");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("first_excption",e.getMessage());
        }

        try {

            String mLatestVersionName = version_number;

            if (mLatestVersionName != null && !mLatestVersionName.isEmpty()) {

                if (Float.valueOf(version_str) < Float.valueOf(mLatestVersionName)) {
                    imgLoading.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("تحديث التطبيق")
                            .setCancelable(false)
                            .setMessage("التطبيق بحاجة للتحديث .. اخر نسخة " + mLatestVersionName)
                            .setPositiveButton("تحديث", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        Log.e("Mariam_about_traccing", "13");
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("market://details?id=" + appPackageName)));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                    }

                                }
                            })
                            .setNegativeButton("إلغاء الأمر", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });

                    builder.create();
                    builder.show();

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("first_excption",e.getMessage());
            imgLoading.setVisibility(View.GONE);
        }

    }

    private void getVersionNumber() {
        Map<String, String> map = new HashMap<>();
       /* MyRequest.makeRquest(this,
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
                        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });*/
        Log.d("version_number","test");
        MyRequest.makeRquest(this, Controller.APP_INFO_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    Log.e("response", response);
                    JSONObject object = new JSONObject(response);
                    Log.e("response",object.getString("app_info"));
                    Gson gson = new Gson();
                    model = gson.fromJson(object.getString("app_info"),
                            AppInfoModel.class);

                    Controller.DATABASE_V=model.getDatabase_version();
                    Log.d("version_number",model.getVersion());
                    Log.d("version_number",model.getDatabase_version());
                    CheckVersion(model.getVersion());
                } catch (JSONException e) {

                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getApplicationContext());
            }

        });
//        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.APP_INFO_URL, map, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("response",response.toString());
//                try {
//
//                    JSONObject jsonObject = response.getJSONObject("app_info");
//                    Gson gson = new Gson();
//                    model = gson.fromJson(jsonObject.toString(),
//                            AppInfoModel.class);
//                    Log.d("version_number",model.getVersion());
//                    CheckVersion(model.getVersion());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.d("version_number","test err");
//                    Log.e("api", e.getMessage());
//                }
//                Log.d("version_number","test err");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(final VolleyError volleyError) {
//                Controller.view_error(volleyError, getApplicationContext());
//                Log.d("version_number","test err");
////                mInterfacePatient.showLoading(false);
//            }
//        });
//
//        jsObjRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                // Here goes the new timeout 3 minutes
//                return 3 * 60 * 1000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                // The max number of attempts
//                return 5;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//                Log.d("version_number","test err");
//            }
//        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getBaseContext(), Build.getSerial() + "", Toast.LENGTH_SHORT).show();
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void checkuserpass() {

        user = username.getText().toString().trim();
        pass = password.getText().toString().trim();
        foldername = txtvar.getText().toString().trim();

        //    Log.e( "foldername: ",foldername);


        if (svpasschBox.isChecked()) {
            Controller.editor.putBoolean("saveLogin", true);
            Controller.editor.putString("username", user);
            Controller.editor.putString("password", pass);
            Controller.editor.putString("foldername", foldername);
            Controller.editor.commit();
        } else {
            Controller.editor.putString("SAVE_LOGIN", "");
            Controller.editor.putString("username", "");
            Controller.editor.putString("password", "");
            Controller.editor.putString("foldername", "");
            Controller.editor.commit();
        }

        if (user.length() < 1 | pass.length() < 1) {
            Toast.makeText(LoginActivity.this, "user name or password is incorrect", Toast.LENGTH_LONG).show();
            imgLoading.setVisibility(View.GONE);
        } else {

            doLogin(user, pass);
        }
    }

    public void doLogin(String username, String password) {


        test mtest = new test();
        btnlogin.setVisibility(View.GONE);
//        imgLoading.setVisibility(View.VISIBLE);
        Log.e("folder", foldername);
        if (foldername.length() > 0) {
            Controller.setRoot(foldername);
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        map.put("TRANS_SCREEN_CD_IN", Screen_cd);
        map.put("TRANS_USER_CODE_IN", username);
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", username);
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("HOS_NO", "");
        map.put("TRANS_DESCRIPTION_IN", "تسجيل دخول");

        // map.put("TRANS_PC_NAME_IN",(Controller.pref.getString("host_name","")));
        //  map.put("HOS_NO_IN",);

        //  Toast.makeText(LoginActivity.this, "Your Device IP Address:" + ipAddress, Toast.LENGTH_SHORT).show();

        Log.e("response", Controller.AUTH_URL);
        Log.e("response", map.toString());

        MyRequest.makeRquest(this, Controller.AUTH_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    // Toast.makeText(LoginActivity.this, "response"+response, Toast.LENGTH_SHORT).show();
                    Log.e("response", response);
                    JSONObject jobj = new JSONObject(response);
                    JSONObject check_auth_obj = (JSONObject) jobj.getJSONObject("check_auth");
                    JSONArray auth_arr = (JSONArray) check_auth_obj.getJSONArray("auth");
                    JSONObject status_obj = auth_arr.getJSONObject(0);
                    Log.e("st auth:", status_obj.getString("status"));
                    int status = status_obj.isNull("status") ? 0 : Integer.parseInt(status_obj.getString("status"));
                    int priv = status_obj.isNull("priv") ? 0 : Integer.parseInt(status_obj.getString("priv"));

                    if (status == 0) {
                        Toast.makeText(LoginActivity.this, "خطأ في اسم المستخدم أو كلمة المرور", Toast.LENGTH_SHORT).show();
                        btnlogin.setVisibility(View.VISIBLE);
                        imgLoading.setVisibility(View.GONE);
                    } else {

                        if (priv == 1) {
                            JSONArray users_arr = (JSONArray) jobj.getJSONArray("USERS");
                            JSONArray priv_arr = (JSONArray) jobj.getJSONArray("PRIVS");
                            JSONObject dept_obj = (JSONObject) jobj.getJSONObject("DEPARTMENT");
                            //JSONObject user_type_obj = (JSONObject) jobj.getJSONObject("USER_TYPE");

                            if (dept_obj != null) {
                                JSONArray Spc_dept_arr = (JSONArray) dept_obj.getJSONArray("SPC_DEPARTMENTS");
                                if (Spc_dept_arr.length() > 0) {
                                    JSONObject spc_obj = Spc_dept_arr.getJSONObject(0);
                                    Controller.editor.putString("DEPT_CODE", spc_obj.getString("DEPT_CODE").equals("null") ? "" : spc_obj.getString("DEPT_CODE"));
                                    Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NAME_AR"));
                                    Controller.editor.putString("DEPT_SPEC_CODE", spc_obj.getString("DEPT_SPEC_CODE"));
                                    Controller.editor.putString("DEPT_CD_SELEC", spc_obj.getString("DEPT_CODE"));
                                    Controller.editor.putString(USER_TYPE, spc_obj.getString("USER_TYPE"));
                                    //  Controller.editor.putString(USER_TYPE, spc_obj.isNull("NURS_LOC_CD")?"1":spc_obj.getString("NURS_LOC_CD"));
                                    Controller.editor.putString(EMP_SERIAL, spc_obj.isNull("EMP_SERIAL") ? "" : spc_obj.getString("EMP_SERIAL"));
                                    Controller.editor.commit();

                                    if (Controller.pref.getString(USER_TYPE, "").equals("2") || Controller.pref.getString(USER_TYPE, "").equals("4")) {  //2- nurse 4- admin nurse
                                        Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NURSE_NAME_AR"));
                                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", spc_obj.getString("NURS_DEPT_CD").equals("null") ? "" : spc_obj.getString("NURS_DEPT_CD"));
                                        Controller.editor.putString("DEPT_CD_SELEC", "");
                                        Controller.editor.commit();
                                    }
                                    if (Controller.pref.getString(USER_TYPE, "").equals("1")
                                            || Controller.pref.getString(USER_TYPE, "").equals("3")) {  // doctor
                                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", "");
                                        Controller.editor.commit();
                                    }
                                    Log.e("DEPT_CODE", "ayat" + Controller.pref.getString("DEPT_CODE", ""));
                                    Log.e("DEPT_CD_SELEC", "ayat" + Controller.pref.getString("DEPT_CD_SELEC", ""));
                                    Log.e("NURSE_DEPT_CD_SELEC", "ayat" + Controller.pref.getString("NURSE_DEPT_CD_SELEC", ""));
                                }
                            }

                            if (users_arr != null) {

                                if (users_arr.length() > 0) {
                                    JSONObject obj = users_arr.getJSONObject(0);

                                    if (obj != null) {


                                        if (obj.getString("USER_CODE") != null) {

                                            Controller.editor.putInt("USER_CODE", Integer.parseInt(obj.getString("USER_CODE")));
                                            Controller.editor.putString("USER_NAME", obj.getString("USER_FULL_NAME"));
                                            Controller.editor.putString("USER_ID", obj.getString("USER_ID"));
                                            Controller.editor.putString("USER_MOBILE", obj.getString("USER_MOBILE"));
                                            Controller.editor.putString("USER_EMAIL", obj.getString("USER_EMAIL"));
                                            Controller.editor.putString("LOGIN_MODE", "1");
                                            Controller.editor.commit();


                                            if (priv_arr != null) {
                                                Controller.PrivMENUS.clear();
                                                if (priv_arr.length() > 0) {
                                                    JSONObject menus = priv_arr.getJSONObject(0);

                                                    Controller.editor.putInt("LOC_TYPE", Integer.parseInt(menus.getString("LOC_TYPE")));
                                                    Controller.editor.putString("LOC_CD", menus.getString("LOC_CD"));
                                                    Controller.editor.putString("LOC_NAME", menus.getString("LOC_NAME"));
                                                    Controller.editor.commit();

                                                    JSONArray menu_arr = (JSONArray) menus.getJSONArray("menu");
                                                    for (int x = 0; x < menu_arr.length(); x++) {
                                                        JSONObject menu_obj = menu_arr.getJSONObject(x);
                                                        PrivMenu privMenu = new PrivMenu();
                                                        privMenu.setId(Integer.parseInt(menu_obj.getString("MENU_CODE")));
                                                        privMenu.setName(menu_obj.getString("MENU_NAME"));

                                                        JSONObject screens = menu_arr.getJSONObject(x);
                                                        JSONArray screen_arr = (JSONArray) screens.getJSONArray("screen");
                                                        for (int y = 0; y < screen_arr.length(); y++) {
                                                            JSONObject screen_obj = screen_arr.getJSONObject(y);
                                                            Screen screen = new Screen();
                                                            screen.setId(Integer.parseInt(screen_obj.getString("SCREEN_CODE")));
                                                            screen.setName(screen_obj.getString("SCREEN_NAME"));
                                                            privMenu.getScreens().add(screen);
                                                            //  Controller.SCREENS.add(screen);
                                                        }
                                                        Controller.PrivMENUS.add(privMenu);
                                                    }
                                                }
                                            }

                                            Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));
                                            ////////
                                            // Checking for first time launch for boarding screen
                                            Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                                            controller = new Controller();


                                            if (!controller.isFirstTimeLaunch()) {
                                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                Intent i = new Intent(LoginActivity.this, boardingActivity.class);
                                                controller.setFirstTimeLaunch(false);
                                                startActivity(i);
                                                finish();
                                            }

                                        } else {


                                        }
                                    }
                                }
                            }

                        } else {

                            Toast.makeText(LoginActivity.this, "لا يوجد صلاحية للمستخدم للدخول على النظام", Toast.LENGTH_SHORT).show();
                            btnlogin.setVisibility(View.VISIBLE);
                            imgLoading.setVisibility(View.GONE);
                        }
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    Log.e("json", "ERROR" + e.getMessage());
                    Toast.makeText(LoginActivity.this, getString(R.string.json_error), Toast.LENGTH_SHORT).show();

                    Controller.Msg_DIALOG.showDialog(" لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول");

                    btnlogin.setVisibility(View.VISIBLE);
                    imgLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void Error(VolleyError error) {
                btnlogin.setVisibility(View.VISIBLE);
                imgLoading.setVisibility(View.GONE);
//                if (error instanceof NetworkError) {
//                    Log.e("login", "NetworkError");
//
//                    Controller.Msg_DIALOG.showDialog("يرجى التحقق من الإتصال بالإنترنت");
//
//                } else if (error instanceof ServerError) {
//                    Log.e("login", "ServerError");
//                    Controller.Msg_DIALOG.showDialog("لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول");
//                } else if (error instanceof AuthFailureError) {
//                    Log.e("login", "AuthFailureError");
//                } else if (error instanceof ParseError) {
//                    Log.e("login", "ParseError");
//                } else if (error instanceof NoConnectionError) {
//                    Log.e("login", "NoConnectionError");
//                } else if (error instanceof TimeoutError) {
//                    Log.e("login", "TimeoutError");
//
//                    Controller.Msg_DIALOG.showDialog("تعذر الإتصال بالشبكة حالياً يرجى المحاولة فيما بعد");


//                }


            }

        });


    }
//    public void doLogin(String username, String password) {
//
//
//        test mtest = new test();
//            btnlogin.setVisibility(View.GONE);
//            imgLoading.setVisibility(View.VISIBLE);
//            Log.e("folder", foldername);
//            if (foldername.length() > 0) {
//                Controller.setRoot(foldername);
//            }
//            Map<String, String> map = new HashMap<>();
//            map.put("username", username);
//            map.put("password", password);
//        Log.e("response",Controller.AUTH_URL);
//        Log.e("response",map.toString());
//
//        MyRequest.makeRquest(this, Controller.AUTH_URL, map, new MyRequest.CallBack() {
//                @Override
//                public void Result(String response) {
//                    try {
//                       // Toast.makeText(LoginActivity.this, "response"+response, Toast.LENGTH_SHORT).show();
//                        Log.e("response",response);
//                        JSONObject jobj = new JSONObject(response);
//                        JSONObject check_auth_obj = (JSONObject) jobj.getJSONObject("check_auth");
//                        JSONArray auth_arr = (JSONArray) check_auth_obj.getJSONArray("auth");
//                        JSONObject status_obj = auth_arr.getJSONObject(0);
//                        Log.e("st auth:", status_obj.getString("status"));
//                        int status = status_obj.isNull("status") ? 0 : Integer.parseInt(status_obj.getString("status"));
//
//                        if (status == 0) {
//                            Toast.makeText(LoginActivity.this, "خطأ في اسم المستخدم أو كلمة المرور", Toast.LENGTH_SHORT).show();
//                            btnlogin.setVisibility(View.VISIBLE);
//                            imgLoading.setVisibility(View.GONE);
//                        }else{
//
//                            JSONArray users_arr = (JSONArray) jobj.getJSONArray("USERS");
//                            JSONArray priv_arr = (JSONArray) jobj.getJSONArray("PRIVS");
//                            JSONObject dept_obj = (JSONObject) jobj.getJSONObject("DEPARTMENT");
//                            //JSONObject user_type_obj = (JSONObject) jobj.getJSONObject("USER_TYPE");
//
//                            if (dept_obj != null) {
//                                JSONArray Spc_dept_arr = (JSONArray) dept_obj.getJSONArray("SPC_DEPARTMENTS");
//                                if (Spc_dept_arr.length() > 0) {
//                                    JSONObject spc_obj = Spc_dept_arr.getJSONObject(0);
//                                    Controller.editor.putString("DEPT_CODE", spc_obj.getString("DEPT_CODE").equals("null") ? "" : spc_obj.getString("DEPT_CODE"));
//                                    Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NAME_AR"));
//                                    Controller.editor.putString("DEPT_CD_SELEC", spc_obj.getString("DEPT_CODE"));
//                                    Controller.editor.putString(USER_TYPE, spc_obj.getString("USER_TYPE"));
//                                    //  Controller.editor.putString(USER_TYPE, spc_obj.isNull("NURS_LOC_CD")?"1":spc_obj.getString("NURS_LOC_CD"));
//                                    Controller.editor.putString(EMP_SERIAL, spc_obj.isNull("EMP_SERIAL") ? "" : spc_obj.getString("EMP_SERIAL"));
//                                    Controller.editor.commit();
//
//                                    if (Controller.pref.getString(USER_TYPE, "").equals("2")) {  // nurse
//                                        Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NURSE_NAME_AR"));
//                                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", spc_obj.getString("NURS_DEPT_CD").equals("null") ? "" : spc_obj.getString("NURS_DEPT_CD"));
//                                        Controller.editor.putString("DEPT_CD_SELEC", "");
//                                        Controller.editor.commit();
//                                    }
//                                    if (Controller.pref.getString(USER_TYPE, "").equals("1")
//                                            || Controller.pref.getString(USER_TYPE, "").equals("3")) {  // doctor
//                                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", "");
//                                        Controller.editor.commit();
//                                    }
//                                    Log.e("DEPT_CODE", "ayat" + Controller.pref.getString("DEPT_CODE", ""));
//                                    Log.e("DEPT_CD_SELEC", "ayat" + Controller.pref.getString("DEPT_CD_SELEC", ""));
//                                    Log.e("NURSE_DEPT_CD_SELEC", "ayat" + Controller.pref.getString("NURSE_DEPT_CD_SELEC", ""));
//                                }
//                            }
//
//                            if (users_arr != null) {
//
//                                if (users_arr.length() > 0) {
//                                    JSONObject obj = users_arr.getJSONObject(0);
//
//                                    if (obj != null) {
//
//
//                                        if (obj.getString("USER_CODE") != null) {
//
//                                            Controller.editor.putInt("USER_CODE", Integer.parseInt(obj.getString("USER_CODE")));
//                                            Controller.editor.putString("USER_NAME", obj.getString("USER_FULL_NAME"));
//                                            Controller.editor.putString("USER_ID", obj.getString("USER_ID"));
//                                            Controller.editor.putString("USER_MOBILE", obj.getString("USER_MOBILE"));
//                                            Controller.editor.putString("USER_EMAIL", obj.getString("USER_EMAIL"));
//                                            Controller.editor.putString("LOGIN_MODE", "1");
//                                            Controller.editor.commit();
//
//
//                                            if (priv_arr != null) {
//
//                                                if (priv_arr.length() > 0) {
//                                                    JSONObject menus = priv_arr.getJSONObject(0);
//
//                                                    Controller.editor.putInt("LOC_TYPE", Integer.parseInt(menus.getString("LOC_TYPE")));
//                                                    Controller.editor.putString("LOC_CD", menus.getString("LOC_CD"));
//                                                    Controller.editor.putString("LOC_NAME", menus.getString("LOC_NAME"));
//                                                    Controller.editor.commit();
//
//                                                    JSONArray menu_arr = (JSONArray) menus.getJSONArray("menu");
//                                                    for (int x = 0; x < menu_arr.length(); x++) {
//                                                        JSONObject menu_obj = menu_arr.getJSONObject(x);
//                                                        PrivMenu privMenu = new PrivMenu();
//                                                        privMenu.setId(Integer.parseInt(menu_obj.getString("MENU_CODE")));
//                                                        privMenu.setName(menu_obj.getString("MENU_NAME"));
//
//                                                        JSONObject screens = menu_arr.getJSONObject(x);
//                                                        JSONArray screen_arr = (JSONArray) screens.getJSONArray("screen");
//                                                        for (int y = 0; y < screen_arr.length(); y++) {
//                                                            JSONObject screen_obj = screen_arr.getJSONObject(y);
//                                                            Screen screen = new Screen();
//                                                            screen.setId(Integer.parseInt(screen_obj.getString("SCREEN_CODE")));
//                                                            screen.setName(screen_obj.getString("SCREEN_NAME"));
//                                                            privMenu.getScreens().add(screen);
//                                                            //  Controller.SCREENS.add(screen);
//                                                        }
//                                                        Controller.PrivMENUS.add(privMenu);
//                                                    }
//                                                }
//                                            }
//
//                                            Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));
//                                            ////////
//                                            // Checking for first time launch for boarding screen
//                                            Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
//                                            controller = new Controller();
//
//
//                                            if (!controller.isFirstTimeLaunch()) {
//                                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                                                startActivity(i);
//                                                finish();
//                                            } else {
//                                                Intent i = new Intent(LoginActivity.this, boardingActivity.class);
//                                                controller.setFirstTimeLaunch(false);
//                                                startActivity(i);
//                                                finish();
//                                            }
//
//                                        } else {
//
//
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    } catch (JSONException e) {
//
//                        e.printStackTrace();
//                        Log.e("json", "ERROR"+e.getMessage());
//                        Toast.makeText(LoginActivity.this,getString(R.string.json_error), Toast.LENGTH_SHORT).show();
//                        btnlogin.setVisibility(View.VISIBLE);
//                        imgLoading.setVisibility(View.GONE);
//                    }
//                }
//
//                @Override
//                public void Error(VolleyError error) {
//                    btnlogin.setVisibility(View.VISIBLE);
//                    imgLoading.setVisibility(View.GONE);
//                }
//            });
//
//    }


//    public void doLogin(String username, String password) {
//
//
//        test mtest = new test();
//        btnlogin.setVisibility(View.GONE);
//        imgLoading.setVisibility(View.VISIBLE);
//        Log.e("folder", foldername);
//        if (foldername.length() > 0) {
//            Controller.setRoot(foldername);
//        }
//
//
//         RequestQueue mRequestQueue;
//         StringRequest mStringRequest;
//
//        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        //String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, Controller.AUTH_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("response",response.toString());
//
//                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.i(TAG,"Error :" + error.toString());
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> map = new HashMap<>();
//                map.put("username", username);
//                map.put("password", password);
//                return map;
//            }
//
//        };
//
//        mRequestQueue.add(mStringRequest);
//    }


//        MyRequest.makeRquest(this, Controller.AUTH_URL, map, new MyRequest.CallBack() {
//            @Override
//            public void Result(String response) {
//                try {
//                    // Toast.makeText(LoginActivity.this, "response"+response, Toast.LENGTH_SHORT).show();
//                    Log.e("response",response);
//                    JSONObject jobj = new JSONObject(response);
//                    JSONObject check_auth_obj = (JSONObject) jobj.getJSONObject("check_auth");
//                    JSONArray auth_arr = (JSONArray) check_auth_obj.getJSONArray("auth");
//                    JSONObject status_obj = auth_arr.getJSONObject(0);
//                    Log.e("st auth:", status_obj.getString("status"));
//                    int status = status_obj.isNull("status") ? 0 : Integer.parseInt(status_obj.getString("status"));
//                    if (status == 0) {
//                        Toast.makeText(LoginActivity.this, "خطأ في اسم المستخدم أو كلمة المرور", Toast.LENGTH_SHORT).show();
//                        btnlogin.setVisibility(View.VISIBLE);
//                        imgLoading.setVisibility(View.GONE);
//                    } else {
//
//                        JSONArray users_arr = (JSONArray) jobj.getJSONArray("USERS");
//                        JSONArray priv_arr = (JSONArray) jobj.getJSONArray("PRIVS");
//                        JSONObject dept_obj = (JSONObject) jobj.getJSONObject("DEPARTMENT");
//                        //JSONObject user_type_obj = (JSONObject) jobj.getJSONObject("USER_TYPE");
//
//
//                        if (dept_obj != null) {
//                            JSONArray Spc_dept_arr = (JSONArray) dept_obj.getJSONArray("SPC_DEPARTMENTS");
//                            if (Spc_dept_arr.length() > 0) {
//                                JSONObject spc_obj = Spc_dept_arr.getJSONObject(0);
//                                Controller.editor.putString("DEPT_CODE", spc_obj.getString("DEPT_CODE").equals("null") ? "" : spc_obj.getString("DEPT_CODE"));
//                                Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NAME_AR"));
//                                Controller.editor.putString("DEPT_CD_SELEC", spc_obj.getString("DEPT_CODE"));
//                                Controller.editor.putString(USER_TYPE, spc_obj.getString("USER_TYPE"));
//                                //  Controller.editor.putString(USER_TYPE, spc_obj.isNull("NURS_LOC_CD")?"1":spc_obj.getString("NURS_LOC_CD"));
//                                Controller.editor.putString(EMP_SERIAL, spc_obj.isNull("EMP_SERIAL")?"":spc_obj.getString("EMP_SERIAL"));
//                                Controller.editor.commit();
//
//
//                                if (Controller.pref.getString(USER_TYPE, "").equals("2")) {  // nurse
//                                    Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NURSE_NAME_AR"));
//                                    Controller.editor.putString("NURSE_DEPT_CD_SELEC", spc_obj.getString("NURS_DEPT_CD").equals("null") ? "" : spc_obj.getString("NURS_DEPT_CD"));
//                                    Controller.editor.putString("DEPT_CD_SELEC", "");
//                                    Controller.editor.commit();
//                                }
//                                if (Controller.pref.getString(USER_TYPE, "").equals("1")
//                                        ||Controller.pref.getString(USER_TYPE, "").equals("3")) {  // doctor
//                                    Controller.editor.putString("NURSE_DEPT_CD_SELEC", "");
//                                    Controller.editor.commit();
//                                }
//
//                                Log.e("DEPT_CODE","ayat"+Controller.pref.getString("DEPT_CODE",""));
//                                Log.e("DEPT_CD_SELEC","ayat"+Controller.pref.getString("DEPT_CD_SELEC",""));
//                                Log.e("NURSE_DEPT_CD_SELEC","ayat"+Controller.pref.getString("NURSE_DEPT_CD_SELEC",""));
//
//                            }
//                        }
//
//
//                        if (users_arr != null) {
//
//                            if (users_arr.length() > 0) {
//                                JSONObject obj = users_arr.getJSONObject(0);
//
//                                if (obj != null) {
//
//
//                                    if (obj.getString("USER_CODE") != null) {
//
//                                        Controller.editor.putInt("USER_CODE", Integer.parseInt(obj.getString("USER_CODE")));
//                                        Controller.editor.putString("USER_NAME", obj.getString("USER_FULL_NAME"));
//                                        Controller.editor.putString("USER_ID", obj.getString("USER_ID"));
//                                        Controller.editor.putString("USER_MOBILE", obj.getString("USER_MOBILE"));
//                                        Controller.editor.putString("USER_EMAIL", obj.getString("USER_EMAIL"));
//                                        Controller.editor.putString("LOGIN_MODE", "1");
//                                        Controller.editor.commit();
//
//
//                                        if (priv_arr != null) {
//
//                                            if (priv_arr.length() > 0) {
//                                                JSONObject menus = priv_arr.getJSONObject(0);
//
//                                                Controller.editor.putInt("LOC_TYPE", Integer.parseInt(menus.getString("LOC_TYPE")));
//                                                Controller.editor.putString("LOC_CD", menus.getString("LOC_CD"));
//                                                Controller.editor.putString("LOC_NAME", menus.getString("LOC_NAME"));
//                                                Controller.editor.commit();
//
//                                                JSONArray menu_arr = (JSONArray) menus.getJSONArray("menu");
//                                                for (int x = 0; x < menu_arr.length(); x++) {
//                                                    JSONObject menu_obj = menu_arr.getJSONObject(x);
//                                                    PrivMenu privMenu = new PrivMenu();
//                                                    privMenu.setId(Integer.parseInt(menu_obj.getString("MENU_CODE")));
//                                                    privMenu.setName(menu_obj.getString("MENU_NAME"));
//
//                                                    JSONObject screens = menu_arr.getJSONObject(x);
//                                                    JSONArray screen_arr = (JSONArray) screens.getJSONArray("screen");
//                                                    for (int y = 0; y < screen_arr.length(); y++) {
//                                                        JSONObject screen_obj = screen_arr.getJSONObject(y);
//                                                        Screen screen = new Screen();
//                                                        screen.setId(Integer.parseInt(screen_obj.getString("SCREEN_CODE")));
//                                                        screen.setName(screen_obj.getString("SCREEN_NAME"));
//                                                        privMenu.getScreens().add(screen);
//                                                        //  Controller.SCREENS.add(screen);
//                                                    }
//                                                    Controller.PrivMENUS.add(privMenu);
//                                                }
//                                            }
//                                        }
//
//                                        Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));
//                                        ////////
//                                        // Checking for first time launch for boarding screen
//                                        Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
//                                        controller = new Controller();
//
//
//                                        if (!controller.isFirstTimeLaunch()) {
//                                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                                            startActivity(i);
//                                            finish();
//                                        } else {
//                                            Intent i = new Intent(LoginActivity.this, boardingActivity.class);
//                                            controller.setFirstTimeLaunch(false);
//                                            startActivity(i);
//                                            finish();
//                                        }
//
//                                    } else {
//
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//
//                    e.printStackTrace();
//                    Log.e("json", "ERROR"+e.getMessage());
//                    Toast.makeText(LoginActivity.this,getString(R.string.json_error), Toast.LENGTH_SHORT).show();
//                    btnlogin.setVisibility(View.VISIBLE);
//                    imgLoading.setVisibility(View.GONE);
//                }


//
//            @Override
//            public void Error(VolleyError error) {
//                btnlogin.setVisibility(View.VISIBLE);
//                imgLoading.setVisibility(View.GONE);
//            }
//        });

//    }

    public void showicd10(View view) {
        startActivity(new Intent(this, icd10Activity.class));

    }


    public void check_netwrok_connection() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.wifi_disconnected, null);
        AlertDialog alertD = new AlertDialog.Builder(this).create();
        Button reconnect_btn = promptView.findViewById(R.id.reconnect_btn);
        Button cancel_btn = promptView.findViewById(R.id.cancel_btn);
        reconnect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Controller.IsConnected()) {
                    alertD.dismiss();
                    getVersionNumber();

                } else {
                    check_netwrok_connection();
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        alertD.setCancelable(false);
        alertD.setView(promptView);
        alertD.show();
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (Controller.IsConnected())
            getVersionNumber();
        else
            check_netwrok_connection();
    }
}
