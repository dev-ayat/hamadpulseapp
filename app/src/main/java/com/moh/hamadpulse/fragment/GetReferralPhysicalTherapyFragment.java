package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.PhysicalTherapyReferralAdapter;
import com.moh.hamadpulse.models.ReferralPhysicalTherapyModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GetReferralPhysicalTherapyFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fbtn_add_referral;
    String fragment_cd = "46";

    public GetReferralPhysicalTherapyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_referral_physical_therapy_fragments, container, false);
    }
    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("التحويل للعلاج الطبيعي");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        getPhysicalTherapy();
        fbtn_add_referral = view.findViewById(R.id.fbtn_add_referral);
        if (Controller.pref.getString(USER_TYPE, "").equals("2") || Controller.pref.getString(USER_TYPE, "").equals("4"))
            fbtn_add_referral.setVisibility(View.GONE);
        fbtn_add_referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new ReferralPhysicalTherapyFragment());
            }
        });
    }

    void getPhysicalTherapy() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getContext())
                .getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient) getContext())
                .getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "get physical therapy");
        Log.e("map", "getMedicin: " + map);
        MyRequest.makeRquest(getContext(), Controller.GET_PHYSICAL_THERAPY_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<ReferralPhysicalTherapyModel> list = gson.fromJson(mJSONObject.getString("PHYSICAL_THERAPY"),
                            new TypeToken<ArrayList<ReferralPhysicalTherapyModel>>() {
                            }.getType());
                    PhysicalTherapyReferralAdapter adapter = new PhysicalTherapyReferralAdapter(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });
    }
}