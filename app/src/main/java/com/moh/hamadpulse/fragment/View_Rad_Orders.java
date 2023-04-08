package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterRadRes;
import com.moh.hamadpulse.adapters.RadiologyServicesAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetRadServicesConst;
import com.moh.hamadpulse.models.RadCurModel;
import com.moh.hamadpulse.models.RadResModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class View_Rad_Orders extends Fragment {
    Spinner spinner_photo_type;
    RadiologyServicesAdapter radiologyServicesAdapter;
    ArrayList<GetRadServicesConst> radServicesConstslist;
    ArrayList<RadCurModel> models = new ArrayList<>();
    EditText et_number_of_records;
    ImageButton imgbtn_search;
    RadResModel radResModels;
    RecyclerView RadResRV;
    AdapterRadRes adapterRadRes;
    String radservicesCD;
    LinearLayout no_result_layout;
    InterfacePatient mInterfacePatient;

    public View_Rad_Orders() {
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
        return inflater.inflate(R.layout.fragment_view_rad_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner_photo_type = view.findViewById(R.id.spinner_photo_type);
        PrepareGetServicesConst();
        no_result_layout = view.findViewById(R.id.emptyEfile_layout);
        spinner_photo_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                radservicesCD = ((GetRadServicesConst) spinner_photo_type.getSelectedItem()).getSERVICECODE();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        et_number_of_records = view.findViewById(R.id.et_number_of_records);
        imgbtn_search = view.findViewById(R.id.imgbtn_search);
        RadResRV = view.findViewById(R.id.RV_red_orders);
        adapterRadRes = new AdapterRadRes(models);
        RadResRV.setAdapter(adapterRadRes);
        RadResRV.setLayoutManager(new LinearLayoutManager(getContext()));
//      get data when click search
        imgbtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//           validation
                if (validation()) {
                    String limit = et_number_of_records.getText().toString();
                    prepareRadRes(radservicesCD, limit);
                } else {
                    Toast.makeText(getContext(), "الرجاء إختيار نوع الصورة وتحديد عدد السجلات", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    //        get data to view it in the RV
    private void prepareRadRes(String photoType, String limit) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_SERVICE_TYPE", photoType);
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATRIC_CD",47179+"");
        map.put("P_START", "");
        map.put("P_LIMIT", limit);
        map.put("P_ORDER_STATUS_CD", "");
        map.put("TRANS_SCREEN_CD_IN", 38 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","47179");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View_RADIOLOGY_ORDERS");
        MyRequest.makeRquest(getContext(), Controller.GET_RAD_ORDER_PATIENT_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("RAD_RES");

                            Gson gson = new Gson();
                            radResModels = gson.fromJson(jsonObject.toString(),
                                    RadResModel.class);
                            if (radResModels.getRadCur().size() == 0) {
                                no_result_layout.setVisibility(View.VISIBLE);
                                RadResRV.setVisibility(View.GONE);

                            } else {
                                RadResRV.setVisibility(View.VISIBLE);
                                no_result_layout.setVisibility(View.GONE);
                                adapterRadRes.setRadResList(radResModels.getRadCur());
//                                RadResRV.setVisibility(View.VISIBLE);
                                adapterRadRes.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        mInterfacePatient.showLoading(false);
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                        mInterfacePatient.showLoading(false);
                    }
                });
    }

    private boolean validation() {
        if (spinner_photo_type.getSelectedItemId() == 0 || et_number_of_records.getText()
                .toString().trim().isEmpty())
            return false;
        else
            return true;
    }

    //get data to fill spinner
    public void PrepareGetServicesConst() {

        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_RAD_SERVICES_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("services");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetRadServicesConst>>() {
                    }.getType();
                    radServicesConstslist = gson.fromJson(jsonArray.toString(), type);
                    radServicesConstslist.add(0, new GetRadServicesConst("0", "إختر نوع الصورة ..", "إختر نوع الصورة ..", "0"));
                    radiologyServicesAdapter = new RadiologyServicesAdapter(getContext(), 0, radServicesConstslist);
                    spinner_photo_type.setAdapter(radiologyServicesAdapter);
                    radiologyServicesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
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
}