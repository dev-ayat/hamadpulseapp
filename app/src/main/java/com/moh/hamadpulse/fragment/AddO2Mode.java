package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.VentalationModeSpinnerAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.constants.Delivery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddO2Mode extends Fragment {
    ArrayList<Object> VentilationModeList;
    String ventmod;
    VentalationModeSpinnerAdapter ventSpinnerAdapter;
    Spinner ventilationModeSpinner;
    EditText txti_ventilationNotes;
    Button btn_add_ventilationMode;

    public AddO2Mode() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_o2_mode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ventilationModeSpinner = view.findViewById(R.id.ventilationModeSpinner);
        txti_ventilationNotes = view.findViewById(R.id.txti_ventilationNotes);
        btn_add_ventilationMode = view.findViewById(R.id.btn_add_ventilationMode);
        PrepareGetVentModeConst();
        btn_add_ventilationMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ventilationModeSpinner.getSelectedItemPosition() == 0 ||
                        txti_ventilationNotes.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    addVentilationMode();
                }
            }
        });
    }

    private void addVentilationMode() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_NOTE", txti_ventilationNotes.getText().toString().trim());
        map.put("P_O2_MODE", ventilationModeSpinner.getSelectedItemPosition() + "");
        map.put("P_CREATED_BY", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", 45 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Add new Ventilation Mode");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_NP_O2_MODE_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
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

    public void PrepareGetVentModeConst() {
        Log.e("ERROR", "PrepareGetVentModeConst");
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_O2_DEVICE_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("P_RESULT");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Delivery>>() {
                    }.getType();

                    VentilationModeList = gson.fromJson(jsonArray.toString(), type);
                    VentilationModeList.add(0, new Delivery("0", "أختر نوع جهاز الأوكسجين ..", "Select o2 device.."));
                    ventSpinnerAdapter = new VentalationModeSpinnerAdapter(getContext(), 0, VentilationModeList);
                    ventilationModeSpinner.setAdapter(ventSpinnerAdapter);
                    ventSpinnerAdapter.notifyDataSetChanged();

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

    @Override
    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("O2 Mode");
    }
}