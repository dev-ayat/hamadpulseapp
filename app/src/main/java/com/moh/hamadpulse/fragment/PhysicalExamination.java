package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PhysicalExamination extends Fragment {

    EditText txt_ph_ex;
    FloatingActionButton add_ph_ex;
    String note;
    public PhysicalExamination(String note) {
        this.note=note;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_physical_examination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_ph_ex = view.findViewById(R.id.txt_ph_ex);
        txt_ph_ex.setText(note==null?"":note);
        txt_ph_ex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.equals(""))
                    txt_ph_ex.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        add_ph_ex = view.findViewById(R.id.add_ph_ex);
        add_ph_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = txt_ph_ex.getText().toString().trim();
                if (txt.isEmpty()) {
                    txt_ph_ex.setError("Can't be empty");
                } else {
                    savePhEx(txt);
                }
            }
        });

    }

    private void savePhEx(String txt) {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PH_EXAM_NOTES", txt + "");
        map.put("P_CREATED_BY", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", 48 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Add Physical Examination");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_PH_EXAM_DOC_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }else  if (res == 2) {
                        Toast.makeText(getContext(), "تمت التعديل بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة",
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