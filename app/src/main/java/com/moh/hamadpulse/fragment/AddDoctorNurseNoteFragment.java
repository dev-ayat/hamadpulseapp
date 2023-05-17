package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.PatientStatusSpinnerAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDoctorNurseNoteFragment extends DialogFragment {
    String patid, patadmcd, user_id, patientstatus, notes;
    Button btn_add_note;
    Spinner PatientStatusSpinner;
    TextInputEditText txti_notes;
    ArrayList<AdmPatientConst> patientStatusArrayList;
    PatientStatusSpinnerAdapter patientStatusSpinnerAdapter;
    public String fragment_cd = "29";
    InterfacePatient mInterfacePatient;
    int type;

    public AddDoctorNurseNoteFragment() {
        // Required empty public constructor
    }

    public AddDoctorNurseNoteFragment(InterfacePatient mInterfacePatient, int type) {
        this.mInterfacePatient = mInterfacePatient;
        this.type = type;

    }

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_doctor_nurse_note, container, false);
        btn_add_note = view.findViewById(R.id.btn_add_note);
        PatientStatusSpinner = view.findViewById(R.id.PatientStatusSpinner);
        txti_notes = view.findViewById(R.id.txti_notes);
        patientStatusArrayList = new ArrayList<>();
        PrepareGetStatusConst();
        PatientStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                patientstatus = ((AdmPatientConst) PatientStatusSpinner.getSelectedItem()).getPATIENTSTATUSID();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = txti_notes.getText().toString();
                if (notes.isEmpty())
                    Toast.makeText(getContext(), "الرجاء ادخال الملاحظة", Toast.LENGTH_SHORT).show();
//                else if (patientstatus.equals("0"))
//                    Toast.makeText(getContext(), "الرجاء اختيار حالة المريض", Toast.LENGTH_SHORT).show();
                else
                    AddNotesForPatient();
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patid = ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"";
        patadmcd = ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"";
        user_id = Controller.pref.getString("USER_ID", "");
    }

    public void PrepareGetStatusConst() {
        mInterfacePatient.showLoading(true);
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

                    patientStatusArrayList = gson.fromJson(jsonArray.toString(), type);
                    patientStatusArrayList.add(0, new AdmPatientConst("0", "اختر حالة المريض..."));
                    patientstatus = "0";
                    patientStatusSpinnerAdapter = new PatientStatusSpinnerAdapter(getContext(), 0, patientStatusArrayList);
                    PatientStatusSpinner.setAdapter(patientStatusSpinnerAdapter);
                    patientStatusSpinnerAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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

    public void AddNotesForPatient() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", patid);
        map.put("ADM_CD", patadmcd);
        map.put("USER_ID", user_id);
        map.put("ADM_STATUS", patientstatus);
        map.put("ADM_NOTE", notes);
        map.put("NOTE_TYPE", type + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "ملاحظات التمريض");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("map", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_NOTE_FOR_PATIENT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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


    @Override
    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("إضافة الأوامر الطبية");
        if (type == 1) {
            ((ActivityPatient) getActivity()).setTitle("إضافة الملاحظات الطبية");
        } else {
            ((ActivityPatient) getActivity()).setTitle("إضافة الملاحظات التمريضية ");
        }
    }
}
