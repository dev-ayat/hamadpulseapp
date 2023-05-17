package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.adapters.VitalSignsConstAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetVitalSignsConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddVitalSignsFragment extends DialogFragment {
    String patname, patid, admdate, patadmcd, USER_ID;
    TextInputEditText txti_Temperature, txti_prusser, txti_pulse, txti_Respiratory;
    Button btnaddsign;
    ArrayList<GetVitalSignsConst> VitalSignsConstArray;
    ArrayList<GetVitalSignsConst> MyOutputlist;
    RecyclerView rv_vitalsignsConst;
    VitalSignsConstAdapter vitalSignsConstAdapter;
    TextInputLayout prusserlabl;
  /// هدا الشغل قديم  وملغي
    public AddVitalSignsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_vital_signs, container, false);
        btnaddsign = view.findViewById(R.id.btn_add_sign);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        admdate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        USER_ID = Controller.pref.getString("USER_ID", "");
        rv_vitalsignsConst = view.findViewById(R.id.rv_vitalsignsConst);
        VitalSignsConstArray = new ArrayList<>();
        rv_vitalsignsConst.setLayoutManager(new LinearLayoutManager(getContext()));
        PrepareGetVitalSignsConst();
        MyOutputlist = new ArrayList<>();
        btnaddsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (GetVitalSignsConst mGetVitalSignsConst : VitalSignsConstArray) {
                    Log.e("arraylog", mGetVitalSignsConst.getVSCODE() + "----" + mGetVitalSignsConst.getvSValuePost());
                    if (mGetVitalSignsConst.getvSValuePost() != null && mGetVitalSignsConst.getvSValuePost().length() > 0) {
                        MyOutputlist.add(mGetVitalSignsConst);
                    } else {
                        MyOutputlist.remove(mGetVitalSignsConst);
                    }

                }
                Log.e("ERRORsize", "MyOutputlist.size()=" + MyOutputlist.size());

                //Controller.LOADER_DIALOG.showDialog("جاري إضافة العلامات الحيوية");
                AddVitalSign(MyOutputlist);
            }
        });
        return view;

    }

    public void PrepareGetVitalSignsConst() {
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_VITAL_SIGN_CONST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("SIGNS");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetVitalSignsConst>>() {
                    }.getType();
                    VitalSignsConstArray = gson.fromJson(jsonArray.toString(), type);
                    vitalSignsConstAdapter = new VitalSignsConstAdapter(getContext(), VitalSignsConstArray);
                    rv_vitalsignsConst.setAdapter(vitalSignsConstAdapter);
                    vitalSignsConstAdapter.notifyDataSetChanged();

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

    public void AddVitalSign(ArrayList<GetVitalSignsConst> MyOutputlist) {
        Log.e("ERRORsize", "MyOutputlist.size()=" + MyOutputlist.size());
        JSONArray VitalCDArray = new JSONArray();
        JSONArray VitalValueArray = new JSONArray();

        for (int i = 0; i < MyOutputlist.size(); i++) {
            Log.e("MyOutputlist", MyOutputlist.get(i).getvSValuePost());
            JSONObject VSCODEobj = new JSONObject();
            JSONObject vSValueobj = new JSONObject();
            try {
                VSCODEobj.put("VSCODE", String.valueOf(MyOutputlist.get(i).getVSCODE()));
                VSCODEobj.put("vSValue", String.valueOf(MyOutputlist.get(i).getvSValuePost()));
                VitalCDArray.put(VSCODEobj);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("ayat", e.getMessage());
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", patid);
        map.put("ADM_CD", patadmcd);
        map.put("USER_ID", USER_ID);
        map.put("VITAL_CD", VitalCDArray.toString());
        Log.e("info", patid + "-----" + "" + patadmcd + "-----" + USER_ID);
        Log.e("VITAL_CD", VitalCDArray.toString());
        Log.e("map", map.toString());
        ///  [{"VSCODE":"1","vSValue":"50"},{"VSCODE":"2","vSValue":"69"}]

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_VITAL_SIGN_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.e("response", "ayat resp" + response.toString());
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تمت الإضافة بنجاح");
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                        replaceAddDialogFragment();

                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم تتم الإضافة");
                            }
                        }, 3000);
                        Controller.Msg_DIALOG.hideDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
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

    private void replaceAddDialogFragment() {
        getDialog().dismiss();
        final Bundle args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("indate", admdate);
        args.putString("patadm", patadmcd);
        VitalSignsFragment vitalSignsFragment = new VitalSignsFragment();
        vitalSignsFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, vitalSignsFragment, "vital_signs_tag")
                .addToBackStack("add_vital_signs_tag").commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("إضافة علامات حيوية");
    }
}
