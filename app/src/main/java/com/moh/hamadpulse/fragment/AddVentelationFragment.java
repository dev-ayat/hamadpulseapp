package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
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
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.VentalationModeSpinnerAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetVentilationConst;

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
public class AddVentelationFragment extends DialogFragment {
    String  user_id, ventmod, vtcc_value, RR_value, FIO2_value, Peep_value, O2flow_value;
    FloatingActionButton add_vent;
    String IEvalue = "";
    Spinner ventModeSpinner;

    TextInputEditText vtcc, RR, Fio2, PEEP, O2flow;
    //RadioGroup IEradiogroup;
    RadioButton selectedradioButton;
    ArrayList<Object> VentilationModeList;
    VentalationModeSpinnerAdapter ventSpinnerAdapter;
    VentalationModeSpinnerAdapter mSpinnerAdapterDelivery;

    private Spinner spIEFirst;
    private Spinner spIEEnd;
    public String fragment_cd = "14";

    public AddVentelationFragment() {
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

        View view = inflater.inflate(R.layout.fragment_add_ventelation, container, false);
        add_vent = view.findViewById(R.id.btn_add_vent);
        ventModeSpinner = view.findViewById(R.id.ventModeSpinner);
        vtcc = view.findViewById(R.id.txti_vtcc);
        RR = view.findViewById(R.id.txti_RR);
        Fio2 = view.findViewById(R.id.txti_Fio2);
        PEEP = view.findViewById(R.id.txti_PEEP);
        O2flow = view.findViewById(R.id.txti_o2flow);
        spIEFirst = view.findViewById(R.id.spIEFirst);
        spIEEnd = view.findViewById(R.id.spIEEnd);
        //IEradiogroup = view.findViewById(R.id.IEradiogroup);
        user_id = Controller.pref.getString("USER_ID", "");
        VentilationModeList = new ArrayList<>();
        PrepareGetVentModeConst();

        ventModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ventmod = ((GetVentilationConst) ventModeSpinner.getSelectedItem()).getVENTYPECODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        IEradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int selectedId = IEradiogroup.getCheckedRadioButtonId();
//                selectedradioButton = (RadioButton) view.findViewById(selectedId);
//                IEvalue = (String) selectedradioButton.getText();
//
//            }
//        });
        add_vent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vtcc_value = vtcc.getText().toString();
                RR_value = RR.getText().toString();
                FIO2_value = Fio2.getText().toString();
                Peep_value = PEEP.getText().toString();
                O2flow_value = O2flow.getText().toString();
                if (ventmod.equals("0")) {
                    Toast.makeText(getContext(), "الرجاء اختيار نوع التنفس الصناعي", Toast.LENGTH_SHORT).show();
                } else
                    AddVentalationForPatient();
            }
        });
        return view;

    }

    public void PrepareGetVentModeConst() {
        Log.e("ERROR", "PrepareGetVentModeConst");
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_VENTILATION_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("V_TYPE");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetVentilationConst>>() {
                    }.getType();

                    VentilationModeList = gson.fromJson(jsonArray.toString(), type);
                    VentilationModeList.add(0, new GetVentilationConst("0", "Select ventilation Mode.."));
                    ventSpinnerAdapter = new VentalationModeSpinnerAdapter(getContext(), 0, VentilationModeList);
                    ventModeSpinner.setAdapter(ventSpinnerAdapter);
                    ventSpinnerAdapter.notifyDataSetChanged();

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
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    public void AddVentalationForPatient() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("USER_ID", user_id);
        map.put("VENT_TYPE", ventmod);
        map.put("RR_VALUE", RR_value);
        map.put("VTCC_VALUE", vtcc_value);
        map.put("FIO2_VALUE", FIO2_value);
        map.put("PEEP_VALUE", Peep_value);
        map.put("O2flow_VALUE", O2flow_value);
        map.put("IE_VALUE", spIEFirst.getSelectedItem().toString() + ":" + spIEEnd.getSelectedItem().toString());
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "التنفس الصناعي");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_Ventilation_Patient_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();

                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
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
