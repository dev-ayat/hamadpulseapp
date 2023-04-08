package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.NusreDepatmentsAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetNursingDepts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NursingDeptFragment extends DialogFragment {
    public static Button btndeptlist;
    public RadioButton deptradio;
    public TextView deptcd;
    ArrayList<HashMap<String, String>> USER_Depts = new ArrayList<HashMap<String, String>>();
    private List<GetNursingDepts> deptsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NusreDepatmentsAdapter mAdapter;

    public NursingDeptFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("test", getClass().getSimpleName());
        View view = inflater.inflate(R.layout.fragment_nurse_dept, container, false);
        recyclerView = view.findViewById(R.id.nuser_dept_recycler_view);
        btndeptlist = view.findViewById(R.id.btn_nursedeptlist);
        deptcd = view.findViewById(R.id.txtnursedeptcd);
        deptradio = view.findViewById(R.id.nursedeptnameradio);
        mAdapter = new NusreDepatmentsAdapter(deptsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        getActivity().invalidateOptionsMenu();
        Log.e("test","Dept");
        PrepareGetNurseDept();
        btndeptlistListener();


        return view;

    }


    public void PrepareGetNurseDept() {
        String USER_TYPE = Controller.pref.getString("USER_TYPE", "");
        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        String url = "";
        if (USER_TYPE.equals('2')) {  //nurse
            String USER_ID = Controller.pref.getString("USER_ID", "");
            map.put("USER_ID", USER_ID);
            url = Controller.GET_NURSE_DEPT_USER_URL;
        } else {
            url = Controller.GET_NURSE_DEPT_URL;
        }
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("NURSEING_DEPT");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetNursingDepts>>() {
                    }.getType();
                    deptsList = gson.fromJson(jsonArray.toString(), type);
                    GetNursingDepts mGetNursingDepts = new GetNursingDepts();
                    mGetNursingDepts.setLOCNAMEAR("None");
                    deptsList.add(0,mGetNursingDepts);
                    Log.e("list", mAdapter.toString());
                    Log.e("size", deptsList.size() + "");
                    adapterini();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
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

    public void adapterini() {
        mAdapter = new NusreDepatmentsAdapter(deptsList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    public void btndeptlistListener() {
        btndeptlist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("testdeptsha", Controller.pref.getString("NURSE_DEPT_CD_SELEC", ""));
                replaceYourDialogFragment();
            }

        });

    }

    private void replaceYourDialogFragment() {
        getDialog().dismiss();
        PatientFragment PatientFragment = new PatientFragment(537);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, PatientFragment, "pateint_tag")
                .addToBackStack("Nurse_Dept_dialog_tag").commit();

    }

}


