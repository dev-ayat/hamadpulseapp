package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.INSERT_RAD_ORDER_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MultiSpinner;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.RadiologyDetailsOrganAdapter;
import com.moh.hamadpulse.adapters.RadiologyMasterOrganAdapter;
import com.moh.hamadpulse.adapters.RadiologyPositionAdapter;
import com.moh.hamadpulse.adapters.RadiologyPrecautionsAdapter;
import com.moh.hamadpulse.adapters.RadiologyServicesAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetOrganDetailsConst;
import com.moh.hamadpulse.models.GetRadMasterOrganConst;
import com.moh.hamadpulse.models.GetRadPositionConst;
import com.moh.hamadpulse.models.GetRadServicesConst;
import com.moh.hamadpulse.models.GetRadprecautions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRadiologyOrderFragment extends DialogFragment {
    String user_id, radservicesCD, radprecautions,
            radmasterCD, raddetailsCD, radpositionCD, notes, doctor_spc, LOC_CD;
    FloatingActionButton btn_add_rad;
    Spinner photoTypeSpinner, organMasterSpinner, organDetailsSpinner, organPositionSpinner, precautionspinner;
    TextInputEditText txti_notes, txti_preparation;
    ArrayList<GetRadServicesConst> radServicesConstslist;
    ArrayList<GetRadMasterOrganConst> radMasterOrganlist;
    ArrayList<GetOrganDetailsConst> RadDetailsOrganList;
    ArrayList<GetRadPositionConst> RadPositionList;
    ArrayList<GetRadprecautions> RadPrecautionsList;
    RadiologyServicesAdapter radiologyServicesAdapter;
    RadiologyMasterOrganAdapter radiologyMasterOrganAdapter;
    RadiologyDetailsOrganAdapter radiologyDetailsOrganAdapter;
    RadiologyPositionAdapter radiologyPositionAdapter;
    RadiologyPrecautionsAdapter radiologyPrecautionsAdapter;
    MultiSpinner multiSpinner;
    public String fragment_cd = "5";


    public AddRadiologyOrderFragment() {
        Log.e("test", "AddRadiologyOrderFragment");
        // Required empty public constructor
    }

    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_radiology, container, false);
        btn_add_rad = view.findViewById(R.id.btn_add_rad);
        photoTypeSpinner = view.findViewById(R.id.photoTypeSpinner);
        organMasterSpinner = view.findViewById(R.id.organMasterSpinner);
        organDetailsSpinner = view.findViewById(R.id.organDetailsSpinner);
        organPositionSpinner = view.findViewById(R.id.organPositionSpinner);
        precautionspinner = view.findViewById(R.id.PrecautionsSpinner);

        //multiSpinner = view.findViewById(R.id.multispinner);
        txti_notes = view.findViewById(R.id.txti_notes);
        txti_preparation = view.findViewById(R.id.txti_preparation);
        user_id = Controller.pref.getString("USER_ID", "");
        doctor_spc = Controller.pref.getString("Doctor_spc", "");
        radServicesConstslist = new ArrayList<>();
        radMasterOrganlist = new ArrayList<>();
        RadDetailsOrganList = new ArrayList<>();
        RadPositionList = new ArrayList<>();
        RadPrecautionsList = new ArrayList<>();
        PrepareGetServicesConst();
        PrepareprecautionsConst();
        photoTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                radservicesCD = ((GetRadServicesConst) photoTypeSpinner.getSelectedItem()).getSERVICECODE();
                PrepareGetMasterOrganConst(radservicesCD);
                if (radservicesCD.equals("1")) {
                    Toast.makeText(getContext(), "service xray", Toast.LENGTH_SHORT);
                    organPositionSpinner.setVisibility(View.VISIBLE);
                    PrepareGetPositionConst();

                } else {
                    organPositionSpinner.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        organMasterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                radmasterCD = ((GetRadMasterOrganConst) organMasterSpinner.getSelectedItem()).getORGANCODE();
                PrepareGetDetailsOrganConst(radmasterCD);
                PrepareGetOrganNote(radservicesCD, radmasterCD);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        organDetailsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                raddetailsCD = ((GetOrganDetailsConst) organDetailsSpinner.getSelectedItem()).getORGANDCODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        organPositionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                radpositionCD = ((GetRadPositionConst) organPositionSpinner.getSelectedItem()).getCRADPOSITIONCODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        precautionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                radprecautions = ((GetRadprecautions) precautionspinner.getSelectedItem()).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
       /* multiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                radpositionCD = ((GetRadPositionConst) organPositionSpinner.getSelectedItem()).getCRADPOSITIONCODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        btn_add_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = txti_notes.getText().toString();

                AddRadiologyForPatient();

            }
        });
        return view;

    }


    public void PrepareGetServicesConst() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID", ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_RAD_SERVICES_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("services");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetRadServicesConst>>() {
                    }.getType();
                    radServicesConstslist = gson.fromJson(jsonArray.toString(), type);
                    radServicesConstslist.add(0, new GetRadServicesConst("0", "إختر نوع الصورة ..", "Select rad exam ..", "0"));
                    radiologyServicesAdapter = new RadiologyServicesAdapter(getContext(), 0, radServicesConstslist);
                    photoTypeSpinner.setAdapter(radiologyServicesAdapter);
                    radiologyServicesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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


    public void PrepareGetPositionConst() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_RAD_POSITION_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("ORGAN_POSITIONS");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetRadPositionConst>>() {
                    }.getType();


                    RadPositionList = gson.fromJson(jsonArray.toString(), type);
                    RadPositionList.add(0, new GetRadPositionConst("0", "Select organ position.. "));
                    radiologyPositionAdapter = new RadiologyPositionAdapter(getContext(), 0, RadPositionList);
                    organPositionSpinner.setAdapter(radiologyPositionAdapter);
                    radiologyPositionAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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


    public void PrepareGetMasterOrganConst(String radservicesCD) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
//        map.put("P_USER_ID", ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"");
        map.put("ddl_services", radservicesCD);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_RAD_ORGAN_MASTER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("master_organ");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetRadMasterOrganConst>>() {
                    }.getType();

                    radMasterOrganlist = gson.fromJson(jsonArray.toString(), type);
                    radMasterOrganlist.add(0, new GetRadMasterOrganConst("0", "إختر العضو الرئيسي ..", "Select main organ .."));
                    radiologyMasterOrganAdapter = new RadiologyMasterOrganAdapter(getContext(), 0, radMasterOrganlist);
                    organMasterSpinner.setAdapter(radiologyMasterOrganAdapter);
                    radiologyMasterOrganAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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

    public void PrepareGetDetailsOrganConst(String radmasterCD) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("organ_master", radmasterCD);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_RAD_ORGAN_DETAILS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("organ_details");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetOrganDetailsConst>>() {
                    }.getType();
                    RadDetailsOrganList = gson.fromJson(jsonArray.toString(), type);
                    RadDetailsOrganList.add(0, new GetOrganDetailsConst("0", "", "Select organ details ... ", "0"));
                    radiologyDetailsOrganAdapter = new RadiologyDetailsOrganAdapter(getContext(), 0, RadDetailsOrganList);
                    organDetailsSpinner.setAdapter(radiologyDetailsOrganAdapter);
                    radiologyDetailsOrganAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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

    public void PrepareprecautionsConst() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.get_rad_Precutions_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("rad_Precutions");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetRadprecautions>>() {
                    }.getType();
                    RadPrecautionsList = gson.fromJson(jsonArray.toString(), type);
                    RadPrecautionsList.add(0, new GetRadprecautions("0", "Select precautions...", "", "", "", "", ""));
                    radiologyPrecautionsAdapter = new RadiologyPrecautionsAdapter(getContext(), 0, RadPrecautionsList);
                    precautionspinner.setAdapter(radiologyPrecautionsAdapter);
                    radiologyPrecautionsAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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

    public void PrepareGetOrganNote(String radservicesCD, String radmasterCD) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_SERVICE_CODE", radservicesCD);
        map.put("P_ORGAN_CODE", radmasterCD);

        Log.e("test", "map=" + map.toString());

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        MyRequest.makeRquest(getContext(), Controller.GET_RAD_ORGAN_NOTES_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {
                        Log.e("test1", "response" + response.toString());
                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("ORGAN_NOTE");
                            Log.e("test2", "response" + jsonObject.get("NOTE"));
                            String note = jsonObject.get("NOTE") + "";
                            Gson gson = new Gson();
                            txti_preparation.setText(note);


                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            mInterfacePatient.showLoading(false);


                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                        mInterfacePatient.showLoading(false);
                    }
                });
    }

    public void AddRadiologyForPatient() {

        if (radservicesCD == null || radservicesCD.equals("0")) {
            Toast.makeText(getContext(), "please select service..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (radmasterCD == null || radmasterCD.equals("0")) {
            Toast.makeText(getContext(), "please select main organ..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (radprecautions == null || radprecautions.equals("0")) {
            Toast.makeText(getContext(), "please select precuations..", Toast.LENGTH_SHORT).show();
            return;
        }

        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("patrec_id", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("visit_id", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("user_id", user_id);
        map.put("P_ORDER_RESON", notes);
        map.put("ddl_services", radservicesCD);
        map.put("master_organ", radmasterCD);
        map.put("details_organ", raddetailsCD == null ? "" : raddetailsCD);
        map.put("radprecautions", radprecautions == null ? "" : radprecautions);
        map.put("ddl_position_organ", radpositionCD == null ? "" : radpositionCD);
        map.put("visit_loc_cd", ((ActivityPatient) getActivity()).getmCardviewDataModel().getLOC_CODE());

        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "أشعة");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("test", "map=" + map.toString());
        Log.e("test", "url=" + INSERT_RAD_ORDER_URL);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, INSERT_RAD_ORDER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("test", "ERROR=" + e.getMessage());
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
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


    @Override
    public void onResume() {
        super.onResume();
    }
}
