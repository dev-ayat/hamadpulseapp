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

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.VentilationModeAdapter;
import com.moh.hamadpulse.models.VentilationModeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewO2Mode extends Fragment {
    RecyclerView ventilation_recycler_view;
    FloatingActionButton btn_add_ventilation;


    public ViewO2Mode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_o2_mode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ventilation_recycler_view = view.findViewById(R.id.ventilation_recycler_view);
        btn_add_ventilation = view.findViewById(R.id.btn_add_ventilation);
        btn_add_ventilation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new AddO2Mode());
            }
        });
        getAllVentilationModes();
    }

    void getAllVentilationModes() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", 45 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View O2 Modes");
        MyRequest.makeRquest(getContext(), Controller.GET_NP_O2_MODE_PR_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {
                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("NP_O2_MODE");
                            Log.d("ventSize", jsonArray.length() + "");
                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<VentilationModeModel>>() {
                            }.getType();
                            ArrayList<VentilationModeModel> models = gson.fromJson(jsonArray.toString(),
                                    type);
                            VentilationModeAdapter adapter = new VentilationModeAdapter(getContext()
                                    , models);
                            Log.d("ventSize", models.size() + "");
                            ventilation_recycler_view.setAdapter(adapter);
                            ventilation_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
                            // getIcdConstArrayList.add(getIcdConstArrayList);
                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("O2 Mode");
    }
}