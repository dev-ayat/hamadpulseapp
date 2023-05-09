package com.moh.hamadpulse;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.moh.hamadpulse.CustemException.NoDepartmentFound;
import com.moh.hamadpulse.constants.ConstShared;
import com.moh.hamadpulse.constants.LruBitmapCache;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.PrivMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Controller extends Application {
    public static InterfacePatient mInterfacePatient;
    public static final String TAG = Controller.class.getSimpleName();
    public static ConnectivityManager connectivityManager;
    public static String ORDER_DEP_CD = "";
    public static String DATABASE_V = "0.0";

    // google play root pulse

    // public static String ROOT = "http://pulse.hamad.ps/ehosapi/index.php/Android_api/";
    public static String ROOT = "http://10.20.28.208/ehosapi/index.php/Android_api/";
    //  public static String ROOT = "http://192.168.2.32/ehosapi/index.php/Android_api/";

    //Turky
//    public static String ROOT = "http://10.30.60.20/newehosapi/index.php/Android_api/";

    public static String ROOT_PDF = "http://apps.moh.gov.ps/newwebarch/index.php/Androidhos_api/";
    public static String ROOT_RAD = "http://pulse.moh.gov.ps/newehos/index.php/RadViewer/api_mobile";
    public static String ROOT_RAD_python = "http://pacs.moh.gov.ps" + ":1103/api";
    public static String ROOT_RAD_python_img = "http://Pacs.hamad.ps" + ":1103/api_img";
    //public static String ROOT_RAD_python = "http://192.168.2.6" + ":1103/api";  // hamad
    //  public static String ROOT_RAD_python_img = "http://192.168.2.6" + ":1103/api_img"; // hamad


    public static String ROOT_RAD_base46 = "http://10.20.77.21/newehos/index.php/RadViewerNew/api_mobile";
    public static String ROOT_RAD_base46_TEST = "http://10.20.10.254:1103/api";
    public static String ROOT_RAD_base46_TEST_endo = "http://10.20.34.50:1103/api";
    public static String ROOT_RAD_base46_Click_endo = "http://10.20.34.50:1103/api_img";
    public static String PERSONAL_IMG = "http://apps.moh.gov.ps/newwebemp/AndroidAPI/getimage.php?no=";
    public static String AUTH_URL = ROOT + "auth_user";
    public static String USER_SPC_DEPT_URL = ROOT + "GET_USER_SPC_DEPARTMENTS";
    public static String USER_DEPT_URL = ROOT + "Get_User_Departments";
    public static String USER_ALL_MED_DEPT_URL = ROOT + "GET_USER_ALL_MED_DEPT";
    public static String DEPT_PAT_URL = ROOT + "GET_PATIENT_BY_DEPT";
    public static String GET_EMR_PATIENT = ROOT + "GET_EMR_QUERY";
    public static String GET_EXAM_CNT = ROOT + "GET_EXAM_CNT";
    public static String GET_SPIN_CNT = ROOT + "GET_ALL_SPIN";
    public static String ADD_EXAMINATION = ROOT + "ADD_EXAMINATION";
    public static String GET_PATIENT_COVID = ROOT + "GET_PATIENT_COVID";
    public static String DEL_EXAM = ROOT + "DEL_EXAM";
    public static String GET_EXAMINATION_ADM = ROOT + "GET_EXAMINATION_ADM";
    public static String LAB_ORDERS_URL = ROOT + "GET_LAB_ORDERS";
    public static String LAB_RESULT_URL = ROOT + "GET_LAB_CAT";
    public static String Efile_VISIT_URL = ROOT + "GET_PATEINT_VISIT";
    public static String Efile_URL = ROOT + "GET_FILES_BY_VISIT";
    public static String Efile_PDF_URL = ROOT_PDF + "GET_FILES_BY_VISIT_PDF";
    public static String LAB_ORDER_GROUPS_URL = ROOT + "GET_LAB_GROUPS";
    public static String INSERT_LAB_ORDER_URL = ROOT + "INSERT_LAB_ORDER";
    public static String INSERT_FAV_LAB_TEST_URL = ROOT + "ADD_TOFAVOURITE_TEST";
    public static String DELETE_FAV_LAB_TEST_URL = ROOT + "REMOVEFROM_FAV_TEST";//
    public static String GET_FAV_LAB_TEST_URL = ROOT + "GET_ALL_FAVOURITE_TEST";
    public static String GET_GET_PHOTO_RAD_URL = ROOT + "GET_PAINT_REPORT_RAD";
    public static String GET_CLINIC_STAT_URL = ROOT + "Get_Clinic_Statistics";
    public static String GET_EMR_STAT_URL = ROOT + "Get_Emr_Statistics";
    public static String GET_AQSA_ACCEDANT_STAT_URL = ROOT + "GET_AQSA_ACCEDANT_STAT";//
    public static String GET_AMDISSION_STAT_URL = ROOT + "GET_AMDISSION_STAT";
    public static String GET_OPERATION_STAT_URL = ROOT + "GET_OPERATION_STAT";
    public static String GET_HOSPITAL_INFO_URL = ROOT + "GET_HOSPITAL_INFO";
    public static String INSERT_VITAL_SIGN_URL = ROOT + "ADD_ADM_SIGNS";
    public static String GET_VITAL_SIGN_CONST_URL = ROOT + "GET_VITALS";/**/
    public static String GET_VITAL_SIGN_URL = ROOT + "GET_VITAL_FOR_ADMISSIONS";
    public static String DELETE_VITAL_SIGN_URL = ROOT + "DEL_ADM_VITAL";
    public static String INSERT_PATIENT_STATUS_URL = ROOT + "ADD_ADM_PATIENT_STATUS";
    public static String GET_NOTE_FOR_PATIENT_URL = ROOT + "GET_NOTE_FOR_ADMISSIONS";//
    public static String INSERT_NOTE_FOR_PATIENT_URL = ROOT + "ADD_ADM_DOC_NOTE";
    public static String Delete_NOTE_FOR_PATIENT_URL = ROOT + "DEL_DOC_NOTE";
    public static String GET_PATIENT_STATUS_CONSTATNS_URL = ROOT + "GET_PATIENT_STATUS_CONSTATNS";
    public static String GET_VENTILATION_CONSTATNS_URL = ROOT + "GET_VENT_TYPES";
    public static String GET_O2_DEVICE_URL = ROOT + "GET_O2_DEVICE";
    public static String GET_Ventilation_Patient_URL = ROOT + "GET_VENT_FOR_ADMISSIONS";
    public static String INSERT_Ventilation_Patient_URL = ROOT + "ADD_ADM_VENTILATOR";
    public static String Delete_Ventilation_Patient_URL = ROOT + "DEL_VENTILATOR_ADM";
    public static String GET_MED_DOSAGE_CONSTATNS_URL = ROOT + "GET_DOSES";
    public static String GET_MED_ROUTE_CONSTATNS_URL = ROOT + "GET_DOSES_TYPE";
    public static String GET_MEDICINS_CONSTATNS_URL = ROOT + "GET_MEDICINES";
    public static String INSERT_MEDICINS_URL = ROOT + "ADD_DOC_PHARMACY";
    public static String GET_TREATMENT_fOR_PATIENT_URL = ROOT + "GET_DOC_PHARMACY";
    public static String GET_NEW_DOC_PHARMACY = ROOT + "GET_NEW_DOC_PHARMACY";
    public static String GET_DOC_PHARMACY_DETAILS = ROOT + "GET_DOC_PHARMACY_DETAILS";
    public static String GET_DOC_PHARM_MEDICINE = ROOT + "GET_DOC_PHARM_MEDICINE";
    public static String ADD_NEW_MEDICIN = ROOT + "ADD_NEW_MEDICIN";
    public static String DELETE_TREATMENT_URL = ROOT + "DEL_INPATIENT_PHARM";
    public static String INSERT_PHOTOS_URL = ROOT + "add_photo_to_Folder";
    public static String GET_ARCHIVE_DOCUMENTS_TYPES_URL = ROOT + "GET_ARCHIVE_DOCUMENTS_TYPES";
    public static String INSERT_ARCHIVE_DOCUMENTS_URL = ROOT_PDF + "SAVE_FILE_API";
    public static String INSERT_PATIENT_DIAGNOSIS_URL = ROOT + "ADD_PATIENT_DIAGNOSIS";
    public static String GET_ICDS_URL = ROOT + "GET_ICDS";
    public static String INSERT_CORONA_URL = ROOT + "ADD_COVID_19";
    public static String GET_USER_TYPE_URL = ROOT + "GET_USER_TYPE";
    public static String GET_GET_DOCTOR_SPECALIST_URL = ROOT + "GET_DOCTOR_SPECALIST";

    public static String NEW_INSERT_VITAL_SIGN_URL = ROOT + "ADD_ADM_SIGNS";
    public static String NEW_GET_VITAL_SIGN_URL = ROOT + "GET_VITAL_FOR_ADMISSIONS";
    public static String NEW_DELETE_VITAL_SIGN_URL = ROOT + "DEL_ADM_VITAL";
    public static String GET_RAD_SERVICES_URL = ROOT + "get_rad_services";
    public static String GET_RAD_ORGAN_MASTER_URL = ROOT + "get_organ_master";
    public static String GET_RAD_ORGAN_DETAILS_URL = ROOT + "get_organ_detials";
    public static String GET_RAD_ORDER_URL = ROOT + "get_rad_order";
    public static String INSERT_RAD_ORDER_URL = ROOT + "insert_rad_order";
    public static String DEL_RAD_ORDER_URL = ROOT + "del_rad";
    public static String GET_RAD_POSITION_URL = ROOT + "get_position_organ";
    public static String GET_NURSE_DEPT_URL = ROOT + "GET_NURSING_DEPARTMENTS";
    public static String GET_NURSE_DEPT_USER_URL = ROOT + "GET_NURSE_DEPT_USER_URL";
    public static String UPDATE_MEDICINE_STATUS_URL = ROOT + "UPDATE_MEDICINE_STATUS";
    public static String LAB_ORDERS_Info_URL = ROOT + "GET_ORDERS_INFO";
    public static String GET_PROTOCOL_ONCOLOGY_URL = ROOT + "GET_PROTOCOL_ONCOLOGY";
    public static String GET_TRANSFER_BlOOD_URL = ROOT + "GET_TRANSFER_BlOOD";
    public static String GET_EXAMINATION_ONCOLOGY_URL = ROOT + "GET_PHYSICAL_ONCOLOGY";
    public static String UPDATE_BLOOD_ORDER_URL = ROOT + "UPDATE_BLOOD_ORDER";
    public static String GET_DailyCare_PATIENT_URL = ROOT + "GET_ALL_DAILYCARE_TODAY";
    public static String UPDATE_EXECUTE_MED_NURSE_URL = ROOT + "UPDATE_EXECUTE_MED_NURSE";
    public static String GET_MED_EXECUTION_URL = ROOT + "GET_MED_EXECUTION";
    public static String ADD_MED_EXECUTION_URL = ROOT + "ADD_MED_EXECUTION";
    public static String ADD_IN_OUT_TAKE_URL = ROOT + "ADD_IN_OUT_TAKE";
    public static String GET_INP_DIABETIC_CHART_URL = ROOT + "GET_INP_DIABETIC_CHART";
    public static String INSERT_INP_DIABETIC_CHART_URL = ROOT + "INSERT_INP_DIABETIC_CHART";
    public static String GET_IN_OUT_TAKE_URL = ROOT + "GET_IN_OUT_TAKE";
    public static String INSERT_INP_CHANGING_POSITION_URL = ROOT + "INSERT_INP_CHANGING_POSITION";
    public static String GET_INP_CHANGING_POSITION_URL = ROOT + "GET_INP_CHANGING_POSITION";
    public static String INSERT_IN_OUT_TAKE_URL = ROOT + "INSERT_IN_OUT_TAKE";
    public static String GET_RAD_ORDER_PATIENT_URL = ROOT + "GET_RAD_ORDER_PATIENT";
    public static String GET_RAD_REPORT_URL = ROOT + "GET_RAD_REPORT";
    public static String GET_SERVICES_STATISTICS_URL = ROOT + "GET_SERVICES_STATISTICS";
    public static String GET_P_PROTOCOL_REPORT_PR_URL = ROOT + "GET_P_PROTOCOL_REPORT_PR";
    public static String UPDATE_ORDER_STATUS_URL = ROOT + "UPDATE_ORDER_STATUS";
    public static String GET_PATENT_LAB_ORDERS_PR_URL = ROOT + "GET_PATENT_LAB_ORDERS_PR";
    public static String APP_INFO_URL = ROOT + "APP_INFO";
    public static String GET_PATIENT_DIAGNOSE_URL = ROOT + "GET_PATIENT_DIAGNOSE";
    public static String SAVE_NP_PROTOCOL_URL = ROOT + "SAVE_NP_PROTOCOL";
    public static String SAVE_NP_PROTOCOL_PR_DRUGS_URL = ROOT + "SAVE_NP_PROTOCOL_PR_DRUGS";
    public static String GET_PATIENT_ALLERGIES_URL = ROOT + "GET_ALLERGIES_PATIENTS";
    public static String INSERT_ALLERGIES_PATIENTS_URL = ROOT + "INSERT_ALLERGIES_PATIENTS";
    public static String GET_ALLERGIES_CONST_URL = ROOT + "GET_ALLERGIES_CONST";
    public static String INSERT_MED_RETURN_URL = ROOT + "INSERT_MED_RETURN";
    public static String GET_PROTOCOL_LAB_RESULT_URL = ROOT + "GET_PROTOCOL_LAB_RESULT";
    public static String GET_PROTOCOL_CONSTANT_URL = ROOT + "GET_PROTOCOL_CONSTANT";
    public static String GET_PROTOCOL_DATA_PR_URL = ROOT + "GET_PROTOCOL_DATA_PR";
    public static String GET_PROTOCOL_VSIGN_URL = ROOT + "GET_PROTOCOL_VSIGN";
    public static String UPDATE_SAMPLE_STATUS_URL = ROOT + "UPDATE_SAMPLE_STATUS";
    public static String GET_LAB_SAMPLE_URL = ROOT + "GET_LAB_SAMPLE";
    public static String SAVE_CHEMOTHROPY_PROTOCOL_PR_URL = ROOT + "SAVE_CHEMOTHROPY_PROTOCOL_PR";
    public static String GET_NP_O2_MODE_PR_URL = ROOT + "GET_NP_O2_MODE";
    public static String INSERT_NP_O2_MODE_URL = ROOT + "INSERT_NP_O2_MODE";
    public static String GET_EXTERNAL_PHARM_MED_URL = ROOT + "GET_EXTERNAL_PHARM_MED";
    public static String ADD_EXTERNAL_MEDICIN_URL = ROOT + "ADD_EXTERNAL_MEDICIN";
    public static String GET_EXTERNAL_MEDICIN_URL = ROOT + "GET_EXTERNAL_MEDICIN";
    public static String GET_MASTER_CLINICS_URL = ROOT + "GET_MASTER_CLINICS";
    public static String GET_SUB_CLINICS_URL = ROOT + "GET_SUB_CLINICS";
    public static String GET_CLINICS_APPOINTMENTS_URL = ROOT + "GET_CLINICS_APPOINTMENTS";
    public static String INSERT_PHYSICAL_THERAPY_URL = ROOT + "INSERT_PHYSICAL_THERAPY";
    public static String UPDATE_CLINICS_APPOINTMENTS_URL = ROOT + "UPDATE_CLINICS_APPOINTMENTS";
    public static String GET_PHYSICAL_THERAPY_URL = ROOT + "GET_PHYSICAL_THERAPY";
    public static String GET_PATIENT_DIAGNOSIS_COUNT_URL = ROOT + "GET_PATIENT_DIAGNOSIS_COUNT";
    public static String GET_P_CLINICS_APPOINTMENTS_URL = ROOT + "GET_P_CLINICS_APPOINTMENTS";
    public static String GET_ADMISSION_DIAGNOSIS_URL = ROOT + "GET_ADMISSION_DIAGNOSIS";
    public static String DELETE_ADMISSION_DIAGNOSIS_URL = ROOT + "DELETE_ADMISSION_DIAGNOSIS";
    public static String UPDATE_OUT_STATUS_URL = ROOT + "UPDATE_OUT_STATUS";
    public static String GENERAT_TOKEN_ON_LOGIN_URL = ROOT + "GENERAT_TOKEN_ON_LOGIN";
    public static String CLEAR_TOKEN_ON_LOGOUT_URL = ROOT + "CLEAR_TOKEN_ON_LOGOUT";
    public static String GET_ADMISSION_DATA_URL = ROOT + "GET_ADMISSION_DATA";
    public static String GET_ADMISSION_NOTES_URL = ROOT + "GET_ADMISSION_NOTES";
    public static String INSERT_PH_EXAM_DOC_URL = ROOT + "INSERT_PH_EXAM_DOC";
    public static String GET_DOCTOR_BY_NAME_URL = ROOT + "GET_DOCTOR_BY_NAME";
    public static String GET_ALL_DEPARTMENTS_URL = ROOT + "GET_ALL_DEPARTMENTS";
    public static String GET_FAV_MED_URL = ROOT + "GET_FAV_MED";
    public static String GET_ALL_PHARMS_MED_URL = ROOT + "GET_ALL_PHARMS_MED";
    public static String ADD_TO_MED_FAV_URL = ROOT + "ADD_TO_MED_FAV";
    public static String REMOVE_FROM_MED_FAV_URL = ROOT + "REMOVE_FROM_MED_FAV";
    public static String GET_USER_FAV_MED_URL = ROOT + "GET_USER_FAV_MED";
    public static String SEND_NOTIFICATION_URL = ROOT + "SEND_NOTIFICATION";
    public static String TRANSPORT_PATIENT_URL = ROOT + "TRANSPORT_PATIENT_PR";
    public static String GET_NEW_P_SERVICES_URL = ROOT + "GET_NEW_P_SERVICES";
    public static String GET_PATIENT_ADM_HIS_URL = ROOT + "GET_PATIENT_ADM_HIS";
    public static String DELETE_INP_DIABETIC_CHART_URL = ROOT + "DELETE_INP_DIABETIC_CHART";
    public static String GET_PH_EXAM_DOC_URL = ROOT + "GET_PH_EXAM_DOC";


    public static DialogLoding LOADER_DIALOG = null;
    public static DialogMsg Msg_DIALOG = null;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;


    public static ArrayList<PrivMenu> PrivMENUS = new ArrayList();
    //  public static ArrayList<JSONObject> SCREENS = new ArrayList();
    public static Context myContext;
    private static Controller mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized Controller getInstance() {
        return mInstance;
    }

//    public static ArrayList<Labordertestmodel> getLabordertestmodel()
//    {
//        Gson gson ;
//        gson=new Gson();
//        pref = myContext.getSharedPreferences("preferencename", 0);
//        String gsonLabordertestmodel = pref.getString(OrderList,"");
//        Type listType = new TypeToken<ArrayList<Labordertestmodel>>(){}.getType();
//        return gson.fromJson(gsonLabordertestmodel, listType);
//    }
//    public static void setArrayPrefs(ArrayList<Labordertestmodel> array) {
//
//        Gson gson = new Gson();
//        String gsonLabordertestmodel = gson.toJson(array);
//        pref = myContext.getSharedPreferences("preferencename", 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.remove(OrderList);
//        editor.commit();
//        editor.putString(OrderList,gsonLabordertestmodel);
//        editor.commit();
//    }

    public static boolean IsConnected() {
        connectivityManager = mInstance.getConnectionManager();

        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected())
            return true;
        else
            return false;
    }

    public static void check_netwrok_connection(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.wifi_disconnected, null);
        AlertDialog alertD = new AlertDialog.Builder(context).create();
        Button reconnect_btn = promptView.findViewById(R.id.reconnect_btn);
        Button cancel_btn = promptView.findViewById(R.id.cancel_btn);
        cancel_btn.setVisibility(View.GONE);
        reconnect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsConnected()) {
                    alertD.dismiss();
                } else {
                    check_netwrok_connection(context);
                }
            }
        });
//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        alertD.setCancelable(false);
        alertD.setView(promptView);
        alertD.show();
    }

    public static void view_error(VolleyError error, Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.wifi_disconnected, null);
        AlertDialog alertD = new AlertDialog.Builder(context).create();
        Button reconnect_btn = promptView.findViewById(R.id.reconnect_btn);
        reconnect_btn.setVisibility(View.GONE);
        Button cancel_btn = promptView.findViewById(R.id.cancel_btn);
        TextView txt_message = promptView.findViewById(R.id.txt_message);
        ImageView status_image = promptView.findViewById(R.id.status_image);
//        cancel_btn.setVisibility(View.GONE);
//        reconnect_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertD.dismiss();
            }
        });

        if (error instanceof NetworkError) {
            Log.e("login", "NetworkError");
            txt_message.setText("يرجى التحقق من الإتصال بالإنترنت");

//            Controller.Msg_DIALOG.showDialog("يرجى التحقق من الإتصال بالإنترنت");

        } else if (error instanceof ServerError) {
            Log.e("login", "ServerError");
            txt_message.setText("لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول");
            status_image.setImageResource(R.drawable.server_error);
//            Controller.Msg_DIALOG.showDialog("لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول");
        } else if (error instanceof AuthFailureError) {
            Log.e("login", "AuthFailureError");
            txt_message.setText("لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول");
            status_image.setImageResource(R.drawable.error_c);
        } else if (error instanceof ParseError) {
            Log.e("login", "ParseError");
            txt_message.setText("الرجاء مراجعة مسؤول النظام");
            status_image.setImageResource(R.drawable.error_w);
        } else if (error instanceof NoConnectionError) {
            txt_message.setText("خطأ في الإتصال يرجى التأكد من اتصالكم");
        } else if (error instanceof TimeoutError) {
            Log.e("login", "TimeoutError");
            txt_message.setText("تعذر الإتصال بالشبكة حالياً يرجى المحاولة فيما بعد");
            status_image.setImageResource(R.drawable.ic_error_time_out);
//            Controller.Msg_DIALOG.showDialog("تعذر الإتصال بالشبكة حالياً يرجى المحاولة فيما بعد");
        } else if (error instanceof NoDepartmentFound) {
            txt_message.setText(error.getMessage());
            status_image.setImageResource(R.drawable.ic_confused);
        } else {
            txt_message.setText("حدث خطأ ما يرجى المحاولة فيما بعد");
            status_image.setImageResource(R.drawable.error_d);
        }
        alertD.setCancelable(false);
        alertD.setView(promptView);
        alertD.show();
    }

    public static void setRoot(String FOLDER_NAME) {
        //    ROOT = "http://apps.moh.gov.ps/"+FOLDER_NAME+"/ehos/index.php/Android_api/";
        // ROOT = "http://pulse.moh.gov.ps/" + FOLDER_NAME + "/newehosapi/index.php/Android_api/";
        ROOT = "http://10.20.41.6/" + FOLDER_NAME + "/newehosapi/index.php/Android_api/";
        ROOT_PDF = "http://apps.moh.gov.ps/newwebarch/index.php/Androidhos_api/";
        ROOT_RAD_base46 = "http://10.20.41.6/" + FOLDER_NAME + "/newehos/index.php/RadViewerNew/api_mobile";
//        ROOT_RAD_base46 = "http://pulse.moh.gov.ps/" + FOLDER_NAME + "/newehos/index.php/RadViewerNew/api_mobile";
        //   ROOT_RAD_python = "http://pacs.moh.gov.ps" + ":1103/" + "/api";
        //   ROOT_RAD_python_img = "http://pacs.moh.gov.ps" + ":1103/" + "/api_img";

        ROOT_RAD_python = "http://10.20.41.171" + ":1103/" + "/api";
        ROOT_RAD_python_img = "http://10.20.41.171" + ":1103/" + "/api_img";

        PERSONAL_IMG = "http://apps.moh.gov.ps/newwebemp/AndroidAPI/getimage.php?no=";
        AUTH_URL = ROOT + "auth_user";
        USER_SPC_DEPT_URL = ROOT + "GET_USER_SPC_DEPARTMENTS";
        USER_DEPT_URL = ROOT + "Get_User_Departments";
        USER_ALL_MED_DEPT_URL = ROOT + "GET_USER_ALL_MED_DEPT";
        DEPT_PAT_URL = ROOT + "GET_PATIENT_BY_DEPT";
        GET_EMR_PATIENT = ROOT + "GET_EMR_QUERY";
        GET_EXAM_CNT = ROOT + "GET_EXAM_CNT";
        GET_SPIN_CNT = ROOT + "GET_ALL_SPIN";
        ADD_EXAMINATION = ROOT + "ADD_EXAMINATION";
        GET_PATIENT_COVID = ROOT + "GET_PATIENT_COVID";
        DEL_EXAM = ROOT + "DEL_EXAM";
        GET_EXAMINATION_ADM = ROOT + "GET_EXAMINATION_ADM";
        LAB_ORDERS_URL = ROOT + "GET_LAB_ORDERS";
        LAB_RESULT_URL = ROOT + "GET_LAB_CAT";
        Efile_VISIT_URL = ROOT + "GET_PATEINT_VISIT";
        Efile_URL = ROOT + "GET_FILES_BY_VISIT";
        Efile_PDF_URL = ROOT_PDF + "GET_FILES_BY_VISIT_PDF";
        LAB_ORDER_GROUPS_URL = ROOT + "GET_LAB_GROUPS";
        INSERT_LAB_ORDER_URL = ROOT + "INSERT_LAB_ORDER";
        INSERT_FAV_LAB_TEST_URL = ROOT + "ADD_TOFAVOURITE_TEST";
        DELETE_FAV_LAB_TEST_URL = ROOT + "REMOVEFROM_FAV_TEST";
        GET_FAV_LAB_TEST_URL = ROOT + "GET_ALL_FAVOURITE_TEST";
        GET_GET_PHOTO_RAD_URL = ROOT + "GET_PAINT_REPORT_RAD";
        GET_CLINIC_STAT_URL = ROOT + "Get_Clinic_Statistics";
        GET_EMR_STAT_URL = ROOT + "Get_Emr_Statistics";
        GET_AQSA_ACCEDANT_STAT_URL = ROOT + "GET_AQSA_ACCEDANT_STAT";
        GET_AMDISSION_STAT_URL = ROOT + "GET_AMDISSION_STAT";
        GET_OPERATION_STAT_URL = ROOT + "GET_OPERATION_STAT";
        GET_HOSPITAL_INFO_URL = ROOT + "GET_HOSPITAL_INFO";
        INSERT_VITAL_SIGN_URL = ROOT + "ADD_ADM_SIGNS";
        GET_VITAL_SIGN_CONST_URL = ROOT + "GET_VITALS";
        GET_VITAL_SIGN_URL = ROOT + "GET_VITAL_FOR_ADMISSIONS";
        DELETE_VITAL_SIGN_URL = ROOT + "DEL_ADM_VITAL";
        INSERT_PATIENT_STATUS_URL = ROOT + "ADD_ADM_PATIENT_STATUS";
        GET_NOTE_FOR_PATIENT_URL = ROOT + "GET_NOTE_FOR_ADMISSIONS";
        INSERT_NOTE_FOR_PATIENT_URL = ROOT + "ADD_ADM_DOC_NOTE";
        Delete_NOTE_FOR_PATIENT_URL = ROOT + "DEL_DOC_NOTE";
        GET_PATIENT_STATUS_CONSTATNS_URL = ROOT + "GET_PATIENT_STATUS_CONSTATNS";
        GET_VENTILATION_CONSTATNS_URL = ROOT + "GET_VENT_TYPES";
        GET_O2_DEVICE_URL = ROOT + "GET_O2_DEVICE";
        GET_Ventilation_Patient_URL = ROOT + "GET_VENT_FOR_ADMISSIONS";
        INSERT_Ventilation_Patient_URL = ROOT + "ADD_ADM_VENTILATOR";
        Delete_Ventilation_Patient_URL = ROOT + "DEL_VENTILATOR_ADM";
        GET_MED_DOSAGE_CONSTATNS_URL = ROOT + "GET_DOSES";
        GET_MED_ROUTE_CONSTATNS_URL = ROOT + "GET_DOSES_TYPE";
        GET_MEDICINS_CONSTATNS_URL = ROOT + "GET_MEDICINES";
        INSERT_MEDICINS_URL = ROOT + "ADD_DOC_PHARMACY";
        GET_TREATMENT_fOR_PATIENT_URL = ROOT + "GET_DOC_PHARMACY";
        GET_NEW_DOC_PHARMACY = ROOT + "GET_NEW_DOC_PHARMACY";
        GET_DOC_PHARMACY_DETAILS = ROOT + "GET_DOC_PHARMACY_DETAILS";
        GET_DOC_PHARM_MEDICINE = ROOT + "GET_DOC_PHARM_MEDICINE";
        ADD_NEW_MEDICIN = ROOT + "ADD_NEW_MEDICIN";
        DELETE_TREATMENT_URL = ROOT + "DEL_INPATIENT_PHARM";
        INSERT_PHOTOS_URL = ROOT + "add_photo_to_Folder";
        GET_ARCHIVE_DOCUMENTS_TYPES_URL = ROOT + "GET_ARCHIVE_DOCUMENTS_TYPES";
        INSERT_ARCHIVE_DOCUMENTS_URL = ROOT_PDF + "SAVE_FILE_API";
        INSERT_PATIENT_DIAGNOSIS_URL = ROOT + "ADD_PATIENT_DIAGNOSIS";
        GET_ICDS_URL = ROOT + "GET_ICDS";
        INSERT_CORONA_URL = ROOT + "ADD_COVID_19";
        GET_USER_TYPE_URL = ROOT + "GET_USER_TYPE";
        GET_GET_DOCTOR_SPECALIST_URL = ROOT + "GET_DOCTOR_SPECALIST";

        NEW_INSERT_VITAL_SIGN_URL = ROOT + "ADD_ADM_SIGNS";
        NEW_GET_VITAL_SIGN_URL = ROOT + "GET_VITAL_FOR_ADMISSIONS";
        NEW_DELETE_VITAL_SIGN_URL = ROOT + "DEL_ADM_VITAL";
        GET_RAD_SERVICES_URL = ROOT + "get_rad_services";
        GET_RAD_ORGAN_MASTER_URL = ROOT + "get_organ_master";
        GET_RAD_ORGAN_DETAILS_URL = ROOT + "get_organ_detials";
        GET_RAD_ORDER_URL = ROOT + "get_rad_order";
        INSERT_RAD_ORDER_URL = ROOT + "insert_rad_order";
        DEL_RAD_ORDER_URL = ROOT + "del_rad";
        GET_RAD_POSITION_URL = ROOT + "get_position_organ";
        GET_NURSE_DEPT_URL = ROOT + "GET_NURSING_DEPARTMENTS";
        GET_NURSE_DEPT_USER_URL = ROOT + "GET_NURSE_DEPT_USER_URL";
        UPDATE_MEDICINE_STATUS_URL = ROOT + "UPDATE_MEDICINE_STATUS";
        LAB_ORDERS_Info_URL = ROOT + "GET_ORDERS_INFO";
        GET_PROTOCOL_ONCOLOGY_URL = ROOT + "GET_PROTOCOL_ONCOLOGY";
        GET_TRANSFER_BlOOD_URL = ROOT + "GET_TRANSFER_BlOOD";
        GET_EXAMINATION_ONCOLOGY_URL = ROOT + "GET_PHYSICAL_ONCOLOGY";
        UPDATE_BLOOD_ORDER_URL = ROOT + "UPDATE_BLOOD_ORDER";
        GET_DailyCare_PATIENT_URL = ROOT + "GET_ALL_DAILYCARE_TODAY";
        UPDATE_EXECUTE_MED_NURSE_URL = ROOT + "UPDATE_EXECUTE_MED_NURSE";
        GET_MED_EXECUTION_URL = ROOT + "GET_MED_EXECUTION";
        ADD_MED_EXECUTION_URL = ROOT + "ADD_MED_EXECUTION";
        ADD_IN_OUT_TAKE_URL = ROOT + "ADD_IN_OUT_TAKE";
        GET_INP_DIABETIC_CHART_URL = ROOT + "GET_INP_DIABETIC_CHART";
        INSERT_INP_DIABETIC_CHART_URL = ROOT + "INSERT_INP_DIABETIC_CHART";
        GET_IN_OUT_TAKE_URL = ROOT + "GET_IN_OUT_TAKE";
        INSERT_INP_CHANGING_POSITION_URL = ROOT + "INSERT_INP_CHANGING_POSITION";
        GET_INP_CHANGING_POSITION_URL = ROOT + "GET_INP_CHANGING_POSITION";
        INSERT_IN_OUT_TAKE_URL = ROOT + "INSERT_IN_OUT_TAKE";
        GET_RAD_ORDER_PATIENT_URL = ROOT + "GET_RAD_ORDER_PATIENT";
        GET_RAD_REPORT_URL = ROOT + "GET_RAD_REPORT";
        GET_SERVICES_STATISTICS_URL = ROOT + "GET_SERVICES_STATISTICS";
        GET_P_PROTOCOL_REPORT_PR_URL = ROOT + "GET_P_PROTOCOL_REPORT_PR";
        GET_PATENT_LAB_ORDERS_PR_URL = ROOT + "GET_PATENT_LAB_ORDERS_PR";
        APP_INFO_URL = ROOT + "APP_INFO";
        GET_PATIENT_DIAGNOSE_URL = ROOT + "GET_PATIENT_DIAGNOSE";
        SAVE_NP_PROTOCOL_URL = ROOT + "SAVE_NP_PROTOCOL";
        SAVE_NP_PROTOCOL_PR_DRUGS_URL = ROOT + "SAVE_NP_PROTOCOL_PR_DRUGS";
        GET_PATIENT_ALLERGIES_URL = ROOT + "GET_ALLERGIES_PATIENTS";
        INSERT_ALLERGIES_PATIENTS_URL = ROOT + "INSERT_ALLERGIES_PATIENTS";
        GET_ALLERGIES_CONST_URL = ROOT + "GET_ALLERGIES_CONST";
        INSERT_MED_RETURN_URL = ROOT + "INSERT_MED_RETURN";
        GET_PROTOCOL_LAB_RESULT_URL = ROOT + "GET_PROTOCOL_LAB_RESULT";
        GET_PROTOCOL_CONSTANT_URL = ROOT + "GET_PROTOCOL_CONSTANT";
        GET_PROTOCOL_DATA_PR_URL = ROOT + "GET_PROTOCOL_DATA_PR";
        GET_PROTOCOL_VSIGN_URL = ROOT + "GET_PROTOCOL_VSIGN";
        UPDATE_SAMPLE_STATUS_URL = ROOT + "UPDATE_SAMPLE_STATUS";
        GET_LAB_SAMPLE_URL = ROOT + "GET_LAB_SAMPLE";
        SAVE_CHEMOTHROPY_PROTOCOL_PR_URL = ROOT + "SAVE_CHEMOTHROPY_PROTOCOL_PR";
        GET_NP_O2_MODE_PR_URL = ROOT + "GET_NP_O2_MODE";
        INSERT_NP_O2_MODE_URL = ROOT + "INSERT_NP_O2_MODE";
        GET_EXTERNAL_PHARM_MED_URL = ROOT + "GET_EXTERNAL_PHARM_MED";
        ADD_EXTERNAL_MEDICIN_URL = ROOT + "ADD_EXTERNAL_MEDICIN";
        GET_EXTERNAL_MEDICIN_URL = ROOT + "GET_EXTERNAL_MEDICIN";
        GET_MASTER_CLINICS_URL = ROOT + "GET_MASTER_CLINICS";
        GET_SUB_CLINICS_URL = ROOT + "GET_SUB_CLINICS";
        INSERT_PHYSICAL_THERAPY_URL = ROOT + "INSERT_PHYSICAL_THERAPY";
        UPDATE_CLINICS_APPOINTMENTS_URL = ROOT + "UPDATE_CLINICS_APPOINTMENTS";
        GET_PHYSICAL_THERAPY_URL = ROOT + "GET_PHYSICAL_THERAPY";
        GET_PATIENT_DIAGNOSIS_COUNT_URL = ROOT + "GET_PATIENT_DIAGNOSIS_COUNT";
        GET_P_CLINICS_APPOINTMENTS_URL = ROOT + "GET_P_CLINICS_APPOINTMENTS";
        GET_ADMISSION_DIAGNOSIS_URL = ROOT + "GET_ADMISSION_DIAGNOSIS";
        DELETE_ADMISSION_DIAGNOSIS_URL = ROOT + "DELETE_ADMISSION_DIAGNOSIS";
        UPDATE_OUT_STATUS_URL = ROOT + "UPDATE_OUT_STATUS";
        GENERAT_TOKEN_ON_LOGIN_URL = ROOT + "GENERAT_TOKEN_ON_LOGIN";
        CLEAR_TOKEN_ON_LOGOUT_URL = ROOT + "CLEAR_TOKEN_ON_LOGOUT";
        GET_ADMISSION_DATA_URL = ROOT + "GET_ADMISSION_DATA";
        GET_ADMISSION_NOTES_URL = ROOT + "GET_ADMISSION_NOTES";
        INSERT_PH_EXAM_DOC_URL = ROOT + "INSERT_PH_EXAM_DOC";
        GET_DOCTOR_BY_NAME_URL = ROOT + "GET_DOCTOR_BY_NAME";
        GET_ALL_DEPARTMENTS_URL = ROOT + "GET_ALL_DEPARTMENTS";
        GET_FAV_MED_URL = ROOT + "GET_FAV_MED";
        GET_ALL_PHARMS_MED_URL = ROOT + "GET_ALL_PHARMS_MED";
        ADD_TO_MED_FAV_URL = ROOT + "ADD_TO_MED_FAV";
        REMOVE_FROM_MED_FAV_URL = ROOT + "REMOVE_FROM_MED_FAV";
        GET_USER_FAV_MED_URL = ROOT + "GET_USER_FAV_MED";
        SEND_NOTIFICATION_URL = ROOT + "SEND_NOTIFICATION";
        TRANSPORT_PATIENT_URL = ROOT + "TRANSPORT_PATIENT_PR";
        GET_NEW_P_SERVICES_URL = ROOT + "GET_NEW_P_SERVICES";
        GET_PATIENT_ADM_HIS_URL = ROOT + "GET_PATIENT_ADM_HIS";
        DELETE_INP_DIABETIC_CHART_URL = ROOT + "DELETE_INP_DIABETIC_CHART";

        Log.e("ROOT", ROOT);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.myContext = getApplicationContext();
        pref = getSharedPreferences("userProfile", MODE_PRIVATE);
        editor = pref.edit();
        initShared();


    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(ConstShared.IS_FIRST_TIME_LAUNCH, true);

    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(ConstShared.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void initShared() {
        editor.putInt(ConstShared.USER_CODE, -1);
        editor.putString(ConstShared.USER_NAME, "");
        editor.putString(ConstShared.USER_ID, "");
        editor.putString(ConstShared.USER_MOBILE, "");
        editor.putString(ConstShared.USER_EMAIL, "");
        editor.putString(ConstShared.LOGIN_MODE, "0");
        editor.putString(ConstShared.DEPT_CODE, "");
        editor.putString(ConstShared.DEPT_NAME_AR, "");
        editor.putString(ConstShared.DEPT_CD_SELEC, "");
        editor.putString(ConstShared.NURSE_DEPT_CD_SELEC, "");
        editor.putString(ConstShared.LOC_CD, "");
        editor.putString(ConstShared.LOC_NAME, "");
        editor.putString(ConstShared.LOC_NAME, "");
        editor.commit();
    }

    public void initlogininfo() {
        editor.putString(ConstShared.SAVE_LOGIN, "");
        editor.putString(ConstShared.username, "");
        editor.putString(ConstShared.password, "");
        editor.putString(ConstShared.FLODER_NAME, "");
        editor.commit();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public ConnectivityManager getConnectionManager() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }

    public static String pattern_date = "dd/MM/yyyy HH:mm:ss";
    public static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(pattern_date, Locale.ENGLISH);

    public static String getDateNow() {
        return mSimpleDateFormat.format(new Date());
    }

    public static String getRemain(String mDateCreate) {

        Date d1;
        Date d2 = null;
        long TotalSecond;
        String disTime = "";


        try {

            d1 = mSimpleDateFormat.parse(getDateNow());
            d2 = mSimpleDateFormat.parse(mDateCreate);

            //for months
            Calendar calObj = Calendar.getInstance();
            calObj.setTime(d2);
            int m = calObj.get(Calendar.MONTH);

            Calendar calObjNow = Calendar.getInstance();
            calObjNow.setTime(d1);
            int mNow = calObjNow.get(Calendar.MONTH);

            //TotalMili = d1.getTime() - d2.getTime();
            //long diff=(d1.getTime()-postTime.getTime())/1000;
            TotalSecond = (d1.getTime() - d2.getTime()) / 1000;
            Log.e("ZAIN", mDateCreate + " " + getDateNow());
            Log.e("ZAIN", mNow + " " + m);
            Log.e("ZAIN", "TotalSecond=" + TotalSecond);


            if (TotalSecond < 15) {
                disTime = "Just Now";
            } else if (TotalSecond < 60) {
                disTime = "" + TotalSecond + " seconds ago";
            } else if (TotalSecond < 3600) // until 1 hr
            {
                long temp = TotalSecond / 60;
                if (temp == 1)
                    disTime = "" + temp + " min ago";
                else
                    disTime = "" + temp + " mins ago";
            } else if (TotalSecond < (24 * 3600)) // until 24 hrs
            {
                long temp = TotalSecond / 3600;
                if (temp == 1)
                    disTime = "" + temp + " hr ago";
                else
                    disTime = "" + temp + " hrs ago";
            } else if (TotalSecond < (24 * 3600 * 7)) //until 7 days
            {
                long temp = TotalSecond / (3600 * 24);
                if (temp == 1)
                    disTime = "yesterday";
                else
                    disTime = "" + temp + " days ago";
            } else if (TotalSecond < ((24 * 3600 * 365))) // no. of  months.. until 1 yr
            {
                long temp = TotalSecond / (24 * 3600 * 30);

                if (temp <= 1) {
                    if (temp < 1) {
                        long weeks = TotalSecond / (24 * 3600 * 7);
                        if (weeks < 1)
                            disTime = "last week";
                        else
                            disTime = weeks + " weeks ago";
                    } else
                        disTime = "1 month ago";
                } else {
                    //int diffMonth=mNow-m;
                    disTime = temp + " months ago";
                }

            } else {
                disTime = "" + mDateCreate;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return disTime;

    }

    public static void OpenPdfFiles(String pdf, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(pdf), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (Exception e) {
//                  show dialog that tell the user that no application in his device
//                    can open pdf files
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.wifi_disconnected, null);
            AlertDialog alertD = new AlertDialog.Builder(context).create();
            Button reconnect_btn = promptView.findViewById(R.id.reconnect_btn);
            reconnect_btn.setText("google drive تنزيل");
            Button cancel_btn = promptView.findViewById(R.id.cancel_btn);
            TextView txt_message = promptView.findViewById(R.id.txt_message);
            txt_message.setText("لا يوجد تطبيق على جهازك قادر على أن يشغل" +
                    "\n" + "هذه الصغية من الملفات ,بإمكانك تنزيل" + "\n" +
                    "...,pdf reader,google drive");
            txt_message.setGravity(Gravity.CENTER);
            ImageView status_image = promptView.findViewById(R.id.status_image);
            status_image.setImageResource(R.drawable.no_app_found);
            reconnect_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/search?q=google%20drive&c=apps"));
                    context.startActivity(myIntent);
                }
            });
            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertD.dismiss();
                }
            });


            alertD.setCancelable(false);
            alertD.setView(promptView);
            alertD.show();
        }
    }


}