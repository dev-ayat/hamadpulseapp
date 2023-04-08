package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.moh.hamadpulse.adapters.AllergiesAdapter;
import com.moh.hamadpulse.models.GetAllargiesmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllargiesFragment extends Fragment {
    RecyclerView allergies_rv;
    LinearLayout empty_allergyes_layout, allergies_layout_list, allergy_layout_header;
    AllergiesAdapter allergiesAdapter;
    ArrayList<GetAllargiesmodel> GetAllargieslist;
    FloatingActionButton btn_add_new_allergy, btn_back_main;

    public AllargiesFragment() {
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
        return inflater.inflate(R.layout.fragment_allargies, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allergies_rv = view.findViewById(R.id.allergies_recycler_view);
        empty_allergyes_layout = view.findViewById(R.id.empty_allergyes_layout);
        allergies_layout_list = view.findViewById(R.id.allergies_layout_list);
        allergy_layout_header = view.findViewById(R.id.allergy_layout_header);
        btn_add_new_allergy = view.findViewById(R.id.btn_add_new_allergy);
        btn_back_main = view.findViewById(R.id.btn_back_main);
        btn_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        btn_add_new_allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new Add_New_Allergies());
            }
        });
        getAllergies();
    }

    public void getAllergies() {
        GetAllargieslist = new ArrayList<>();
        allergiesAdapter = new AllergiesAdapter(GetAllargieslist, getContext());
        allergies_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        allergies_rv.setHasFixedSize(true);
        allergies_rv.setAdapter(allergiesAdapter);
        Map<String, String> map = new HashMap<>();
        //  map.put("P_PATREC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_PATREC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");

        Log.e("allariey", map.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_PATIENT_ALLERGIES_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Log.e("ayat", response);
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ALLERGIES_LIST");

                    Log.e("ayat", jsonArray + "");
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetAllargiesmodel>>() {
                    }.getType();
                    GetAllargieslist = gson.fromJson(jsonArray.toString(), type);
                    int listsize = GetAllargieslist.size();
                    if (listsize == 0) {
                        allergies_layout_list.setVisibility(View.GONE);
                        allergy_layout_header.setVisibility(View.GONE);
                        empty_allergyes_layout.setVisibility(View.VISIBLE);

                    }
                    allergiesAdapter.setBloodList(GetAllargieslist);
                    allergiesAdapter.notifyDataSetChanged();
                    Log.e("ayat", "ayaaat" + GetAllargieslist);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //     Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });
    }


}