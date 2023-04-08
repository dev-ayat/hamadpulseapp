package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterSpinner;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetMedDoseConst;
import com.moh.hamadpulse.models.GetMedRouteConst;
import com.moh.hamadpulse.models.GetMedicineConst;

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
public class RepeatTreatmentPlanFragment extends DialogFragment {
    String patname, patid, admdate, patadmcd, user_id, dosagecd, Med_NAME_String, Medicinecd, Routecd;
    String interval = "0";
    String dosagevalue = "0";
    Button btn_treatplan;
    ImageButton img_MedSearch;
    Spinner DosageSpinner, RouteSpinner;
    TextInputEditText txti_notes;
    ArrayList<Object> getMedDoseConstArrayList;
    ArrayList<GetMedicineConst> getMedicineConstArrayList;
    ArrayList<Object> getMedRouteConstArrayList;
    AdapterSpinner dosageSpinnerAdapter;
    AdapterSpinner medRouteConstSpinnerAdapter;
    AutoCompleteTextView auto_drugname;
    TextInputEditText txti_interval, txti_TotalAmount;
    /*MedicinConstAutoCompleteAdapter medicinConstAdapter;*/

    public RepeatTreatmentPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_treatment_plan, container, false);
        btn_treatplan = view.findViewById(R.id.btn_treatplan);
        auto_drugname = view.findViewById(R.id.auto_drugname);
        img_MedSearch = view.findViewById(R.id.img_MedSearch);
        DosageSpinner = view.findViewById(R.id.DosageSpinner);
        RouteSpinner = view.findViewById(R.id.RouteSpinner);
        txti_interval = view.findViewById(R.id.txti_interval);
        txti_TotalAmount = view.findViewById(R.id.txti_TotalAmount);
        txti_notes = view.findViewById(R.id.txti_notes);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        admdate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        user_id = Controller.pref.getString("USER_ID", "");

        getMedDoseConstArrayList = new ArrayList<>();
        dosageSpinnerAdapter = new AdapterSpinner(getContext(), 0, getMedDoseConstArrayList);
        DosageSpinner.setAdapter(dosageSpinnerAdapter);
        PreparegetMedDoseConst();
        DosageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dosagecd = ((GetMedDoseConst) DosageSpinner.getSelectedItem()).getDOSECODE();
                dosagevalue = ((GetMedDoseConst) DosageSpinner.getSelectedItem()).getDOSEVAUE();
                if (dosagecd.length() > 0) {
                    if (dosagevalue.length() > 0) {
                        CalcMedAmount();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getMedRouteConstArrayList = new ArrayList<>();
        medRouteConstSpinnerAdapter = new AdapterSpinner(getContext(), 0, getMedRouteConstArrayList);
        RouteSpinner.setAdapter(medRouteConstSpinnerAdapter);
        PreparegetMedRouteConst();
        RouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Routecd = ((GetMedRouteConst) RouteSpinner.getSelectedItem()).getDOSGCODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        auto_drugname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GetMedicineConst medicinConst = (GetMedicineConst) adapterView.getItemAtPosition(i);
                auto_drugname.setText(medicinConst.getITEMNAME());
                Medicinecd = medicinConst.getMEDMCODE();
            }
        });


        getMedicineConstArrayList = new ArrayList<>();
        img_MedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Med_NAME_String = auto_drugname.getText().toString();
                //PreparegetMedicin(Med_NAME_String);
            }
        });

        txti_interval.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                interval = txti_interval.getText().toString();
                if (interval.length() > 0) {
                    CalcMedAmount();
                }
            }
        });

        btn_treatplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dosagecd.equals("0")) {
                    Handler aHandler = new Handler();
                    aHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Controller.Msg_DIALOG.showDialog("الرجاء إدخال التكرار");
                        }
                    }, 1000);
                    Controller.Msg_DIALOG.hideDialog();
                } else if (Routecd.equals("0")) {
                    Handler aHandler = new Handler();
                    aHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Controller.Msg_DIALOG.showDialog("الرجاء إدخال طريقة إعطاء الجرعة");
                        }
                    }, 1000);
                    Controller.Msg_DIALOG.hideDialog();
                }
                {

                    String TotalInterval = txti_interval.getText().toString();
                    String TotalMedAmount = txti_TotalAmount.getText().toString();
                    AddMedicinForPatient(Medicinecd, dosagecd, Routecd, TotalInterval, TotalMedAmount);
                }
            }
        });

        return view;
    }

    public void PreparegetMedDoseConst() {
        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_MED_DOSAGE_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("ALL_DOSES");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetMedDoseConst>>() {
                    }.getType();

                    getMedDoseConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    getMedDoseConstArrayList.add(0, new GetMedDoseConst("0", "اختر التكرار ..", "0", ""));

                    dosageSpinnerAdapter = new AdapterSpinner(getContext(), 0, getMedDoseConstArrayList);
                    DosageSpinner.setAdapter(dosageSpinnerAdapter);
                    dosageSpinnerAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
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

    public void PreparegetMedRouteConst() {
        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_MED_ROUTE_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("ALL_DOSES");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetMedRouteConst>>() {
                    }.getType();

                    getMedRouteConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    getMedRouteConstArrayList.add(0, new GetMedRouteConst("0", "اختر طريقة إعطاء العلاج", ""));

                    medRouteConstSpinnerAdapter = new AdapterSpinner(getContext(), 0, getMedRouteConstArrayList);
                    RouteSpinner.setAdapter(medRouteConstSpinnerAdapter);
                    medRouteConstSpinnerAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
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
/*
    public void PreparegetMedicin(String Med_NAME_String) {
        Map<String, String> map = new HashMap<>();
        map.put("M_NAME", Med_NAME_String);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_MEDICINS_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("ALL_MED");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetMedicineConst>>() {
                    }.getType();

                    getMedicineConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    //  getMedicineConstArrayList.add(0, new GetMedicineConst("0","اختر الجرعة ..", "0",""));
                    medicinConstAdapter = new MedicinConstAutoCompleteAdapter(getContext(), 0, getMedicineConstArrayList);
                    auto_drugname.setAdapter(medicinConstAdapter);
                    auto_drugname.setThreshold(0);
                    auto_drugname.showDropDown();
                    medicinConstAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }*/

    public void AddMedicinForPatient(String Medicinecd, String dosagecd, String Routecd, String interval, String TotalAmount) {
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", patid);
        map.put("ADM_CD", patadmcd);
        map.put("USER_ID", user_id);
        map.put("MED_CD", Medicinecd);
        map.put("DOSE_CD", dosagecd);
        map.put("DOSE_TYPE", Routecd);
        map.put("INTERVAL", interval);
        map.put("WANT_AMOUNT", TotalAmount);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("map", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_MEDICINS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تمت الإضافة بنجاح");
                            }
                        }, 3000);
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
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
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
        TreatmentPlanFragment treatmentPlanFragment = new TreatmentPlanFragment();
        treatmentPlanFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, treatmentPlanFragment, "TreatmentPlan_tag")
                .addToBackStack("AddTreatmentPlan_tag").commit();

    }

    public void CalcMedAmount() {
        float count_dosagevalue = Float.parseFloat(dosagevalue);
        // String interval=txti_interval.getText().toString();
        float countinterval = Float.parseFloat(interval);
        float res = count_dosagevalue * countinterval;
        txti_TotalAmount.setText(String.valueOf(res));
    }
}
