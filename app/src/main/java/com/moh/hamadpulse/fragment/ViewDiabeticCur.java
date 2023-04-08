package com.moh.hamadpulse.fragment;

import android.os.Bundle;
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
import com.moh.hamadpulse.adapters.AdapterDiabeticCur;
import com.moh.hamadpulse.models.DiabeticCurModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewDiabeticCur extends Fragment {

    RecyclerView diabetic_RecyclerView;
    AdapterDiabeticCur adapterDiabeticCur;
    ArrayList<DiabeticCurModel> diabeticCurModelArrayList;
    FloatingActionButton btn_add_diabetic_cur;
    String fragment_cd = "37";

    public ViewDiabeticCur() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_diabetic_cur, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        diabetic_RecyclerView = view.findViewById(R.id.RV_In_Out);
        btn_add_diabetic_cur = view.findViewById(R.id.fbtn_add_diabetic_cur);
        diabeticCurModelArrayList = new ArrayList<>();
        adapterDiabeticCur = new AdapterDiabeticCur(diabeticCurModelArrayList);
        diabetic_RecyclerView.setHasFixedSize(true);
        diabetic_RecyclerView.setAdapter(adapterDiabeticCur);
        diabetic_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATRIC_CD","191211");
//        map.put("P_ADM_CD","412565");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View DiabeticCur");
        MyRequest.makeRquest(getContext(), Controller.GET_INP_DIABETIC_CHART_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("DIABETIC_CUR");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<DiabeticCurModel>>() {
                            }.getType();
                            diabeticCurModelArrayList = gson.fromJson(jsonArray.toString(),
                                    type);

                            adapterDiabeticCur.setArrrayList_DiabeticCur
                                    (diabeticCurModelArrayList);
                            adapterDiabeticCur.notifyDataSetChanged();


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
        btn_add_diabetic_cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new AddDiabetic_Cur());
            }
        });
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("فحص السكر");
    }
}
