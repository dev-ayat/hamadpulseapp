package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.BloodTransferAdapter;
import com.moh.hamadpulse.models.GetBloodTransfer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodTransferFragment extends Fragment {
    RecyclerView blood_recycler_view;
    BloodTransferAdapter bloodTransferAdapter;
    ArrayList<GetBloodTransfer> bloodTransferArrayList;
    String fragment_cd = "23";
    public BloodTransferFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_transfer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        blood_recycler_view = view.findViewById(R.id.blood_recycler_view);
        bloodTransferArrayList = new ArrayList<>();
        bloodTransferAdapter = new BloodTransferAdapter(bloodTransferArrayList, getContext());
        blood_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        blood_recycler_view.setHasFixedSize(true);
        blood_recycler_view.setAdapter(bloodTransferAdapter);
        Map<String, String> map = new HashMap<>();
        map.put("PATREIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "نقل دم");
        Log.d("blood trans",map.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_TRANSFER_BlOOD_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("BLOOD_TRANS");
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetBloodTransfer>>() {}.getType();
                    bloodTransferArrayList = gson.fromJson(jsonArray.toString(), type);
                    bloodTransferAdapter.setBloodList(bloodTransferArrayList);
                    bloodTransferAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                   // Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("نقل الدم");
    }

}
