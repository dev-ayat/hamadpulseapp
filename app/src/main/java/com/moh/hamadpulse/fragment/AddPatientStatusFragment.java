package com.moh.hamadpulse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.adapters.AdmStatusConstAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.AdmPatientConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPatientStatusFragment extends DialogFragment implements AdmStatusConstAdapter.patientStatusInterface {
    String patname, patid, admdate, patadmcd, user_code;
    Button btnaddpatientstatus;
    ArrayList<AdmPatientConst> admPatientConstsArray;
    AdmStatusConstAdapter admStatusConstAdapter;
    RecyclerView status_const_recyclerview;
    String statuscd = "-1";
    private Context mContext;
    public String fragment_cd = "30";


    public AddPatientStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_patient_status, container, false);
        btnaddpatientstatus = view.findViewById(R.id.btn_add_patient_status);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        admdate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        user_code = String.valueOf(Controller.pref.getInt("USER_CODE", -1));
        status_const_recyclerview = view.findViewById(R.id.status_recycler_view);
        admPatientConstsArray = new ArrayList<>();
        status_const_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        // admStatusConstAdapter = new AdmStatusConstAdapter(getContext(), admPatientConstsArray, this);
        // status_const_recyclerview.setAdapter(admStatusConstAdapter);
        Log.e("patient info ", patid + "-----" + "" + patadmcd + "-----" + patname + "-----" + admdate);
        PrepareGetStatusConst();
        btnaddpatientstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.LOADER_DIALOG.showDialog("جاري إضافة حالة المريض");
                AddPatientStatus();
            }
        });
        return view;
    }

    public void PrepareGetStatusConst() {
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_PATIENT_STATUS_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("STATUS_CONSTATNS");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<AdmPatientConst>>() {
                    }.getType();
                    admPatientConstsArray = gson.fromJson(jsonArray.toString(), type);
                    Log.e("list", admPatientConstsArray.toString());
                    Log.e("size", admPatientConstsArray.size() + "");
                    adapterini();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
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

    public void adapterini() {
        admStatusConstAdapter = new AdmStatusConstAdapter(getContext(), admPatientConstsArray, this);
        status_const_recyclerview.setAdapter(admStatusConstAdapter);
        admStatusConstAdapter.notifyDataSetChanged();
    }

    public void AddPatientStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", patid);
        map.put("ADM_CD", patadmcd);
        map.put("USER_CODE", user_code);
        map.put("PATIENT_STATUS_CD", statuscd);
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "حالة المريض");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_PATIENT_STATUS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("VRESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تمت الإضافة بنجاح");
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم تتم الإضافة");
                            }
                        }, 3000);
                        Controller.Msg_DIALOG.hideDialog();
                    }
                    replaceAddDialogFragment();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {

                Controller.view_error(volleyError, getContext());
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

    private void replaceAddDialogFragment() {
        getDialog().dismiss();
        final Bundle args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("indate", admdate);
        args.putString("patadm", patadmcd);
        DoctorNurseNoteFragment doctorNurseNoteFragment = new DoctorNurseNoteFragment();
        doctorNurseNoteFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, doctorNurseNoteFragment, "Patient_Status_tag")
                .addToBackStack("add_Patient_Status_tag").commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("إضافة حالة المريض");
    }


    @Override
    public void onstatusClick(String statuscdselected) {

        statuscd = statuscdselected;

    }

}
