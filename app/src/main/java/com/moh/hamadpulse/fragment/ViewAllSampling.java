package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.ViewSamplesAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.AllSampleModel;
import com.moh.hamadpulse.models.LabSampleModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewAllSampling extends Fragment {

    RecyclerView sampling_RV;
    FloatingActionButton btn_add_samples;
    AllSampleModel model;
    ViewSamplesAdapter adapter;
    ArrayList<LabSampleModel> models;

    public ViewAllSampling() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_sampling, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sampling_RV = view.findViewById(R.id.sampling_RV);
        btn_add_samples = view.findViewById(R.id.btn_add_samples);
        btn_add_samples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getItemCount() != 0)
                    update_allSamples();
                else
                    Toast.makeText(getContext(), "There is no sampling to update", Toast.LENGTH_SHORT).show();
            }
        });
        models = new ArrayList<>();
        adapter = new ViewSamplesAdapter(models);
        sampling_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        sampling_RV.setAdapter(adapter);
        getSamples();

    }

    private void update_allSamples() {
        Map<String, String> map = new HashMap<>();
        int size = model.getLabSample().size();
//        map.put("P_D_DETAILS_CD", 5955954 + "");
//        map.put("P_D_CATOGERY_CD", 263 + "");
//        map.put("P_D_SAMPLING_STATUS", 1 + "");
        for (int i = 0; i < size; i++) {
            LabSampleModel labSampleModel = model.getLabSample().get(i);
            map.put("items[" + i + "][P_D_DETAILS_CD]", labSampleModel.getDDetailsCd());
            map.put("items[" + i + "][P_D_CATOGERY_CD]", labSampleModel.getDCatogeryCd());
            map.put("items[" + i + "][P_D_SAMPLING_STATUS]", labSampleModel.getDSamplingStatus());
            map.put("items[" + i + "][P_D_SAMPLING_EMP_CODE]", Controller.pref.getString("USER_ID", ""));
        }
        map.put("TRANS_SCREEN_CD_IN", 44 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Update lab Sampling");
        Log.e("map", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.UPDATE_SAMPLE_STATUS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
//                        getActivity().onBackPressed();
                        getSamples();
                    } else {
                        Toast.makeText(getContext(), "لم يتم التحديث",
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


    private void getSamples() {
        Map<String, String> map = new HashMap<>();
//        map.put("P_VISIT_CD", ((ActivityPatient)
//                getActivity()).getmCardviewDataModel().getAdmcd() + "");
//        map.put("P_PATRIC_CD", ((ActivityPatient)
//                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_VISIT_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", 44 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View Services Statistics");
        MyRequest.makeRquest(getContext(), Controller.GET_LAB_SAMPLE_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("LAB_SAMPLE");
                            Gson gson = new Gson();
                            model = gson.fromJson(jsonObject.toString(),
                                    AllSampleModel.class);
                            adapter.setsamples(model.getLabSample());
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                    }
                });
    }
}