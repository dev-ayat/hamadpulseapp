package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ReferralPhysicalTherapyFragment extends Fragment {

    EditText dearcolleague_et, Diagnosis_et, Precautions_et;
    MaterialButton btn_submit;
    String fragment_cd = "46";

    public ReferralPhysicalTherapyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_referral_physical_therapy_fragments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dearcolleague_et = view.findViewById(R.id.dearcolleague_et);
        Diagnosis_et = view.findViewById(R.id.Diagnosis_et);
        Precautions_et = view.findViewById(R.id.Precautions_et);
        btn_submit = view.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dearcolleague = dearcolleague_et.getText().toString().trim();
                String diagnosis = Diagnosis_et.getText().toString().trim();
                String precautions = Precautions_et.getText().toString().trim();
                if (dearcolleague.isEmpty() || diagnosis.isEmpty() || precautions.isEmpty()) {
                    Toast.makeText(getContext(), "All fields must be fill", Toast.LENGTH_LONG).show();
                } else {
                    refrral(dearcolleague, diagnosis, precautions);
                }
            }
        });

    }

    private void refrral(String dearcolleague, String diagnosis, String precautions) {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_PATIENT_ID", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPtmrpid() + "");
        map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("P_SUB_DEP", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getLOC_CODE() + "");

        String full_name[] = (((ActivityPatient) getActivity()).getmCardviewDataModel().getPatname()).split(" ");
        map.put("P_FNAME", full_name[0]);
        map.put("P_SNAME", full_name[1]);
        map.put("P_TNAME", full_name[2]);
        map.put("P_LNAME", full_name[3]);
        map.put("P_DIAGNOSIS", diagnosis);
        map.put("P_COLLEAGUE", dearcolleague);
        map.put("P_PRECAUTIONS", precautions);

        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Insert Referral to PHYSICAL_THERAPY ");
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_PHYSICAL_THERAPY_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت التحويل بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم يتم التحويل",
                                Toast.LENGTH_SHORT).show();
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
                Controller.view_error(volleyError, getContext());
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
}