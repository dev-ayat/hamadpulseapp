package com.moh.hamadpulse.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
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

import com.android.volley.NetworkError;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterTakeInOutMaster;
import com.moh.hamadpulse.models.InOutMasterModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class View_Take_In_Out extends Fragment {
    RecyclerView RV_In_Out;
    AdapterTakeInOutMaster adapterTakeInOutMaster;
    ArrayList<InOutMasterModel> inOutMasterArrayList;
    FloatingActionButton btn_add_Take_In_Out;
    String fragment_cd = "35";


    public View_Take_In_Out() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((ActivityPatient) getActivity()).show_hide_details(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((ActivityPatient) getActivity()).show_hide_details(View.VISIBLE);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_take_in_out,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        RV_In_Out = view.findViewById(R.id.RV_In_Out);
        btn_add_Take_In_Out = view.findViewById(R.id.fbtn_add_In_Out);
        inOutMasterArrayList = new ArrayList<>();
        adapterTakeInOutMaster = new AdapterTakeInOutMaster(inOutMasterArrayList);

        RV_In_Out.setHasFixedSize(true);
        RV_In_Out.setAdapter(adapterTakeInOutMaster);
        RV_In_Out.setLayoutManager(new LinearLayoutManager(getContext()));
//        RV_In_Out.addItemDecoration(new DividerItemDecoration(RV_In_Out.getContext(), DividerItemDecoration.VERTICAL));

        Map<String, String> map = new HashMap<>();
        map.put("P_INP_IN_OUT_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_INP_IN_OUT_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_INP_IN_OUT_ADM_CD","412565");
//        map.put("P_INP_IN_OUT_PATRIC_CD","191211");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View Take In Out");
        MyRequest.makeRquest(getContext(), Controller.GET_IN_OUT_TAKE_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("IN_OUT_MASTER");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<InOutMasterModel>>() {
                            }.getType();
                            inOutMasterArrayList = gson.fromJson(jsonArray.toString(),
                                    type);

                            adapterTakeInOutMaster.
                                    setInOutMasterModelArrayList(inOutMasterArrayList);
                            adapterTakeInOutMaster.notifyDataSetChanged();


                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
//                        Controller.view_error(error,getContext());
                        if (error instanceof NetworkError) {
                            Log.e("login", "NetworkError");
                            Toast.makeText(getContext(), "يرجى التحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();

                        } else {
                            Log.e("login", "ServerError");
                            Toast.makeText(getContext(), "لا يوجد اتصال على سيرفر المستشفى طرفكم الرجاء مراجعة المسؤول", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        btn_add_Take_In_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new InOutTakeNurseFragment());
            }
        });

    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("فحص السوائل");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}