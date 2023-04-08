package com.moh.hamadpulse.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.Allergys_Name_Adapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.Allergy_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Add_New_Allergies extends Fragment {
    InterfacePatient mInterfacePatient;
    DatePickerDialog datePickerDialog;
    Spinner allergies_type_sp, allergies_sp, reaction_type_sp, severity_type_sp;
    TextInputLayout Remark_layout, Note_Layout;
    Button date_btn, btn_add_new_allergy;
    String dateFrom = "2022/2/2";
    boolean is_other = false;

    public Add_New_Allergies() {//InterfacePatient mInterfacePatient
        // Required empty public constructor
//        this.mInterfacePatient=mInterfacePatient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_allergies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allergies_type_sp = view.findViewById(R.id.allergies_type_sp);
        allergies_sp = view.findViewById(R.id.allergies_sp);
        reaction_type_sp = view.findViewById(R.id.reaction_type_sp);
        severity_type_sp = view.findViewById(R.id.severity_type_sp);
        btn_add_new_allergy = view.findViewById(R.id.btn_add_new_allergy);
        Remark_layout = view.findViewById(R.id.Remark_layout);
        Note_Layout = view.findViewById(R.id.Note_Layout);
        date_btn = view.findViewById(R.id.date_btn);
//      remove error from input text layout
//        Remark_layout.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Remark_layout.setError(null);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        Note_Layout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Note_Layout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        prepareDateDialog();
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        btn_add_new_allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              validate form
                if (validation())
                    add_new_allergy();
            }
        });
//      get constants depend on allergy type
        allergies_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                        setEnableFor(true);
                        getAllergysForTheType(i);
                        break;
             /*       case 3:
                        setEnableFor(false);
//                      change value to handle this condition in validation
                        is_other = true;
                        break;*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//      when user click in this spinner before the allergy type spinner
//        allergies_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(allergies_type_sp.getSelectedItemPosition()==0)
//                    Toast.makeText(getContext(), "please select " +
//                            "allergy type first", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void setEnableFor(boolean enable) {
        allergies_sp.setEnabled(enable);
        reaction_type_sp.setEnabled(enable);
        severity_type_sp.setEnabled(enable);
        Remark_layout.setEnabled(enable);
    }

    private void getAllergysForTheType(int type) {
        Map<String, String> map = new HashMap<>();
        map.put("P_ALLERGY_TYPE", type + "");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_ALLERGIES_CONST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("ALLERGIES");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Allergy_Model>>() {
                    }.getType();
                    ArrayList<Allergy_Model> allergy_models = gson.fromJson(jsonArray.toString(), type);
                    allergy_models.add(0, new Allergy_Model("0", "إختر الحساسية .."));
                    Allergys_Name_Adapter adapter = new Allergys_Name_Adapter(getContext(), 0, allergy_models);
                    allergies_sp.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
//                Controller.view_error(volleyError, getContext());
//                mInterfacePatient.showLoading(false);
                Toast.makeText(getContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void add_new_allergy() {
//
        Map<String, String> map = new HashMap<>();
        map.put("P_PATREC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ALLERGY_CD", allergies_sp.isEnabled() ? ((Allergy_Model) allergies_sp.getSelectedItem()).getAllergyCd() : "");
        map.put("P_ALL_DATE", date_btn.getText().toString());
        map.put("P_DOCTOR_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("P_REACTION", reaction_type_sp.isEnabled() ? reaction_type_sp.getSelectedItemPosition() + "" : "");
        map.put("P_SEVERITY", severity_type_sp.isEnabled() ? severity_type_sp.getSelectedItemPosition() + "" : "");
        map.put("P_REMARK", Remark_layout.getEditText().getText().toString());
        map.put("P_NOTE", Note_Layout.getEditText().getText().toString());
        map.put("TRANS_SCREEN_CD_IN", 41 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Add new Allergies");
        Log.e("map_insert", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_ALLERGIES_PATIENTS_URL, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response_insert", response.toString());
                        try {
                            int res = response.getInt("RESULT");
                            if (res == 1) {
                                Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                                getActivity().onBackPressed();
                            } else if (res == 2) {
                                Toast.makeText(getContext(), "تم الإضافة مسبقا", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("json", "ERROR");
                        }
//                mInterfacePatient.showLoading(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Controller.view_error(volleyError, getContext());
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


    private void prepareDateDialog() {
//      get current date
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateFrom = (i2 < 10 ? "0" + i2 : i2 + "") + "/" + ((++i1) < 10 ? "0" + i1 : i1 + "") + "/" + i;
                date_btn.setText(dateFrom);
            }
        };
        datePickerDialog = new DatePickerDialog(getContext(), style, onDateSetListener
                , year, month, day);
    }

    private boolean validation() {
        boolean flag = true;
        if (allergies_type_sp.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please Select Allergies Type", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if (allergies_sp.isEnabled() && allergies_sp.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please Select Allergy", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if (reaction_type_sp.isEnabled() && reaction_type_sp.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please Select Reaction", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if (reaction_type_sp.isEnabled() && severity_type_sp.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please Select Severity", Toast.LENGTH_LONG).show();
            flag = false;
        }
//        if(Remark_layout.getEditText().getText().toString().isEmpty()){
//            Remark_layout.setError("Please fill the field");
//            flag=false;
//        }
        if (allergies_type_sp.getSelectedItemPosition() == 3
                && Note_Layout.getEditText().getText()
                .toString().isEmpty()) {
            Note_Layout.setError("Please fill the field");
            flag = false;
        }
        return flag || is_other;
    }
}