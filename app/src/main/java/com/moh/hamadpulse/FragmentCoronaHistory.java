package com.moh.hamadpulse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterHistoryCorona;
import com.moh.hamadpulse.models.CoronaHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentCoronaHistory extends Fragment {


    private RecyclerView rvReportCoronaHistory;
    AdapterHistoryCorona mAdapterHistoryCorona;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<CoronaHistory> mListCoronaHistory;
    String fragment_cd = "26";
    public FragmentCoronaHistory() {
        // Required empty public constructor
    }
    public FragmentCoronaHistory(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient=mInterfacePatient;
    }

    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_corona_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvReportCoronaHistory = view.findViewById(R.id.rvReportCoronaHistory);
        mAdapterHistoryCorona = new AdapterHistoryCorona(new ArrayList<>());
        rvReportCoronaHistory.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvReportCoronaHistory.setLayoutManager(mLinearLayoutManager);
        rvReportCoronaHistory.setAdapter(mAdapterHistoryCorona);
        mInterfacePatient.showLoading(true);
        Map<String, String> mMap = new HashMap<>();
        mMap.put("patientId", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPtmrpid() + "");
        mMap.put("TRANS_SCREEN_CD_IN", fragment_cd);
        mMap.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        mMap.put("TRANS_ACTION_CD_IN", "2");
        mMap.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        mMap.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        mMap.put("TRANS_DESCRIPTION_IN", "المسحات");
        MyRequest.makeRquest(getContext(), "http://apps.moh.gov.ps/phc/api/covidlabtestpulsapi.php", mMap, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    if (mJSONObject.getString("status").equals("1")) {
                        JSONArray jsonArray = mJSONObject.getJSONArray("data");
                        Gson mGson = new Gson();
                        mListCoronaHistory = mGson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<CoronaHistory>>() {
                        }.getType());
                        mAdapterHistoryCorona.setmListCoronaHistory(mListCoronaHistory);
                        mAdapterHistoryCorona.notifyDataSetChanged();
                        setTitle();
                    }
                    else
                        Toast.makeText(getContext(), "لا توجد بيانات مدخلة", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                mInterfacePatient.showLoading(false);
                Controller.view_error(error, getContext());
            }
        });
    }

    public void setTitle()
    {
        ((ActivityPatient) getActivity()).setTitle("سجل المسحات"+" ("+mAdapterHistoryCorona.getmListCoronaHistory().size()+")");
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle();
    }
}