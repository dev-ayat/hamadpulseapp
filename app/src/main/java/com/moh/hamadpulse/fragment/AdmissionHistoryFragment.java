package com.moh.hamadpulse.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdmissionHistoryAdapter;
import com.moh.hamadpulse.models.AdmissionHistoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AdmissionHistoryFragment extends Fragment {


    RecyclerView adm_his_rv;
    public AdmissionHistoryFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admission_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adm_his_rv=view.findViewById(R.id.adm_his_rv);
        getAdmissionHistory();
    }

    private void getAdmissionHistory() {
        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATRIC_CD", "33747");
//        map.put("P_PATRIC_CD","191211");
//        map.put("P_ADM_CD","412565");
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View Admission History");
        MyRequest.makeRquest(getContext(), Controller.GET_PATIENT_ADM_HIS_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("ADMISSION_INFO");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<AdmissionHistoryModel>>() {
                            }.getType();
                            ArrayList<AdmissionHistoryModel> list = gson.fromJson(jsonArray.toString(),
                                    type);

                            AdmissionHistoryAdapter adapter=new AdmissionHistoryAdapter(list,
                                    (ActivityPatient) getActivity());
                            adm_his_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            adm_his_rv.setAdapter(adapter);



                            // getIcdConstArrayList.add(getIcdConstArrayList);
                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        if (getActivity() != null)
                            Controller.view_error(error, getContext());
                    }
                });
    }
}