package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AddDiabetic_Cur extends Fragment {
    TextInputLayout txti_layout_BS_DL, txti_layout_Insulen_Dose, txti_layout_DiabeticNote;
    EditText txt_BS_DL, txt_insulen_dose, txt_Note;
    Spinner spinner_insulen_type;
    FloatingActionButton btn_add_new_diabetic_cur;
    String bs_dl, insulen_dose, note;
    int insulen_type;

    public AddDiabetic_Cur() {
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
        return inflater.inflate(R.layout.fragment_add_diabetic_cur, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txti_layout_BS_DL = view.findViewById(R.id.texti_bs_dl);
        txt_BS_DL = txti_layout_BS_DL.getEditText();
        txti_layout_Insulen_Dose = view.findViewById(R.id.texti_insulen_dose);
        txt_insulen_dose = txti_layout_Insulen_Dose.getEditText();
        txti_layout_DiabeticNote = view.findViewById(R.id.texti_DiabeticNote);
        txt_Note = txti_layout_DiabeticNote.getEditText();
        spinner_insulen_type = view.findViewById(R.id.spinner_insulen_type);
        btn_add_new_diabetic_cur = view.findViewById(R.id.btn_add_new_diabetic_cur);
        spinner_insulen_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                insulen_type = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//      remove error after typing for ds_dl and insulin dose
        txt_BS_DL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txti_layout_BS_DL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txt_insulen_dose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txti_layout_Insulen_Dose.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txt_Note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txti_layout_DiabeticNote.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_add_new_diabetic_cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bs_dl = txt_BS_DL.getText().toString().trim();
                insulen_dose = txt_insulen_dose.getText().toString().trim();
                note = txt_Note.getText().toString().trim();
//              checking if the fields is empty or not
                boolean flag = true;
                if (bs_dl.isEmpty()) {
//                  show error massage
                    txti_layout_BS_DL.setError("can't be empty");
                    flag = false;
                }
                if (insulen_dose.isEmpty()) {
                    txti_layout_Insulen_Dose.setError("can't be empty");
                    flag = false;
                }
                if (note.isEmpty()) {
                    txti_layout_DiabeticNote.setError("can't be empty");
                    flag = false;
                }
//              check if the user select an insulin type or not
                if (insulen_type == 0) {
                    Toast.makeText(getContext(), "Please select " +
                            "insulen type", Toast.LENGTH_LONG).show();
                    flag = false;
                }
                if (flag) {
                    AddDiabeticForPatient();
                }
            }
        });

    }


    //send request to add diabetic for patient
    public void AddDiabeticForPatient() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_INP_DIABETIC_PATREC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_INP_DIABETIC_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_INP_DIABETIC_BS_DL", bs_dl);
        map.put("P_INP_DIABETIC_INSULIN_TYPE", insulen_type + "");
        map.put("P_INP_DIABETIC_INSULIN_DOSE", insulen_dose);
        map.put("P_INP_DIABETIC_CREATED_BY", Controller.pref.getString("USER_ID", ""));
        map.put("P_INP_DIABETIC_NOTE", note);
        map.put("TRANS_SCREEN_CD_IN", 37 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Add new Diabetic");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_INP_DIABETIC_CHART_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة",
                                Toast.LENGTH_SHORT).show();
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
                Controller.view_error(volleyError, getContext());
//                mInterfacePatient.showLoading(false);
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
        ((ActivityPatient) getActivity()).setTitle("فحص السكر");
    }
}