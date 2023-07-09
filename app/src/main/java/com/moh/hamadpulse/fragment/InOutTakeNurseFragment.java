package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class InOutTakeNurseFragment extends DialogFragment {
    String Iv_Fluid, Oral, Drains, Ngt, Vomiting, Urine, Chest_Tube_Left, Chest_Tube_Right, Vol, Started, Finished, Gastro, Residual;
    TextInputLayout txti_Iv_Fluid, txti_Oral, txti_Drains, txti_Ngt, txti_Vomiting,
            txti_Urine, txti_Chest_Tube_Left, txti_Chest_Tube_Right, txti_in_out_note,
            txti_vol, txti_started, txti_finished, txti_gastro, txti_residual;
    FloatingActionButton btn_add_TakeInOut;
    LinearLayout containerVitalSign;
    Spinner Sp_in_take;
    InterfacePatient mInterfacePatient;

    public InOutTakeNurseFragment() {
        // Required empty public constructor
    }

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

        View view = inflater.inflate(R.layout.in_out_take, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_add_TakeInOut = view.findViewById(R.id.btn_add_newTakeInOut);
        txti_Iv_Fluid = view.findViewById(R.id.txti_Iv_Fluid);
        setListenerForInputTextLayout(txti_Iv_Fluid);
        txti_Oral = view.findViewById(R.id.txti_Oral);
        setListenerForInputTextLayout(txti_Oral);
//        txti_Drains = view.findViewById(R.id.txti_in_out_drains);
//        setListenerForInputTextLayout(txti_Drains);
        txti_Ngt = view.findViewById(R.id.txti_NGT);
        setListenerForInputTextLayout(txti_Ngt);
        txti_Vomiting = view.findViewById(R.id.txti_vomitting);
        setListenerForInputTextLayout(txti_Vomiting);
        txti_Urine = view.findViewById(R.id.txti_urine);
        setListenerForInputTextLayout(txti_Urine);
//        txti_Chest_Tube_Left = view.findViewById(R.id.txti_chestl);
//        setListenerForInputTextLayout(txti_Chest_Tube_Left);
//        txti_Chest_Tube_Right = view.findViewById(R.id.txti_chestR);
//        setListenerForInputTextLayout(txti_Chest_Tube_Right);
        txti_in_out_note = view.findViewById(R.id.txti_in_out_note);
        txti_vol = view.findViewById(R.id.txti_vol);
        setListenerForInputTextLayout(txti_vol);
//        txti_started = view.findViewById(R.id.txti_started);
//        setListenerForInputTextLayout(txti_started);
//        txti_finished = view.findViewById(R.id.txti_finished);
//        setListenerForInputTextLayout(txti_finished);
        txti_gastro = view.findViewById(R.id.txti_gastro);
        setListenerForInputTextLayout(txti_gastro);
        txti_residual = view.findViewById(R.id.txti_residual);
        setListenerForInputTextLayout(txti_residual);

        btn_add_TakeInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Iv_Fluid = txti_Iv_Fluid.getEditText().getText().toString().trim();
                Oral = txti_Oral.getEditText().getText().toString().trim();
//                Drains = txti_Drains.getEditText().getText().toString().trim();
                Ngt = txti_Ngt.getEditText().getText().toString().trim();
                Vomiting = txti_Vomiting.getEditText().getText().toString().trim();
                Urine = txti_Urine.getEditText().getText().toString().trim();
//             Chest_Tube_Left = txti_Chest_Tube_Left.getEditText().getText().toString().trim();

                Vol = txti_vol.getEditText().getText().toString().trim();
//                Started = txti_started.getEditText().getText().toString().trim();
//                Finished = txti_finished.getEditText().getText().toString().trim();
                Gastro = txti_gastro.getEditText().getText().toString().trim();
                Residual = txti_residual.getEditText().getText().toString().trim();

                if (validation()) {
                    AddTakeInOutForPatient();
                }

            }
        });

    }

    private boolean validation() {
        boolean flag = true;
        if (Iv_Fluid.isEmpty()) {
            txti_Iv_Fluid.setError("please fill the field");
            flag = false;
        }
        if (Oral.isEmpty()) {
            txti_Oral.setError("please fill the field");
            flag = false;
        }
//        if (Drains.isEmpty()) {
//            txti_Drains.setError("please fill the field");
//            flag = false;
//        }
        if (Ngt.isEmpty()) {
            txti_Ngt.setError("please fill the field");
            flag = false;
        }
        if (Vomiting.isEmpty()) {
            txti_Vomiting.setError("please fill the field");
            flag = false;
        }
        if (Urine.isEmpty()) {
            txti_Urine.setError("please fill the field");
            flag = false;
        }
//        if (Chest_Tube_Left.isEmpty()) {
//            txti_Chest_Tube_Left.setError("please fill the field");
//            flag = false;
//        }
//        if (Chest_Tube_Right.isEmpty()) {
//            txti_Chest_Tube_Right.setError("please fill the field");
//            flag = false;
//        }
        return flag;
    }

    public void AddTakeInOutForPatient() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_INP_IN_OUT_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_INP_IN_OUT_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
//        map.put("P_INP_IN_OUT_PATRIC_CD", 191211+"");
//        map.put("P_INP_IN_OUT_ADM_CD", 412565+"");
        map.put("P_INP_IN_OUT_IV_FLUID", Iv_Fluid);
        map.put("P_INP_IN_OUT_ORAL", Oral);
//        map.put("P_INP_IN_OUT_DRAINS", Drains);
        map.put("P_INP_IN_OUT_NGT", Ngt);
        map.put("P_INP_IN_OUT_VOMTTING", Vomiting);
        map.put("P_INP_IN_OUT_URINE", Urine);
//        map.put("P_INP_IN_OUT_CH_R", Chest_Tube_Right);
//        map.put("P_INP_IN_OUT_CH_L", Chest_Tube_Left);
        map.put("P_IN_OUT_NOTE", txti_in_out_note.getEditText().getText().toString().trim());
        map.put("P_INP_IN_OUT_VOL", Vol);
//        map.put("P_INP_IN_OUT_STARTED", Started);
//        map.put("P_INP_IN_OUT_FINISHED", Finished);
        map.put("P_INP_IN_OUT_GASTRO", Gastro);
        map.put("P_INP_IN_OUT_RESIDUAL", Residual);
        map.put("P_INP_IN_OUT_CREATED_BY", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", 35 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "insert take in out");
        Log.e("ERRORinout", "" + map.toString());


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_IN_OUT_TAKE_URL, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("P_RESULT");
                            if (res == 1) {
                                Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                                getActivity().onBackPressed();
                            } else if (res == 2) {
                                Toast.makeText(getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                                getActivity().onBackPressed();
                            } else
                                Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();

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

    private void setListenerForInputTextLayout(TextInputLayout til) {
        til.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
