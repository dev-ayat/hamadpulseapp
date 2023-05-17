package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class newAddVitalSignsFragment extends DialogFragment {
    String patname, patid, admdate, patadmcd, user_id,
            V_txtiTEMP, V_txtiSaO2, V_txtiRRmin, V_txtiEtCO2mmHg,
            V_txtiHeartRate, V_txtiNIBP,V_txtiNIBP_lower, V_txtiUrine, V_txtiCVP, V_txtiIBP;
    int v_stool;
    TextInputEditText txtiTEMP, txtiSaO2, txtiRRmin, txtiEtCO2mmHg,txti_notes,
            txtiHeartRate, txtiNIBP,txti_NIBP_lower, txtiUrine, txtiCVP, txtiIBP;
    RadioGroup rgroub_stool;
    FloatingActionButton btn_add_vital;
    LinearLayout containerVitalSign;
    public String fragment_cd = "11";

    public newAddVitalSignsFragment() {
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

        View view = inflater.inflate(R.layout.new_fragment_add_vitalsign, container, false);
        containerVitalSign = view.findViewById(R.id.containerVitalSign);
        txtiTEMP = view.findViewById(R.id.txti_TEMP);
        txti_notes = view.findViewById(R.id.txti_notes);
        txti_notes.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (txti_notes.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });
        txtiSaO2 = view.findViewById(R.id.txti_SaO2);
        txtiSaO2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int val = Integer.parseInt(editable.toString().trim());
                    if (val > 100) {
                        editable.replace(0, editable.length(), "100", 0, 3);
                    } else if (val < 0) {
                        editable.replace(0, editable.length(), "0", 0, 1);
//                        txtiSaO2.setText("0");
                    }
                } catch (NumberFormatException ex) {
                    // Do something
                }
            }
        });
        txtiRRmin = view.findViewById(R.id.txti_RRmin);
        txtiEtCO2mmHg = view.findViewById(R.id.txti_EtCO2mmHg);
        txtiHeartRate = view.findViewById(R.id.txti_HeartRate);
        txtiNIBP = view.findViewById(R.id.txti_NIBP);
        txti_NIBP_lower = view.findViewById(R.id.txti_NIBP_lower);
        txtiUrine = view.findViewById(R.id.txti_Urine);
        txtiCVP = view.findViewById(R.id.txti_CVP);
        txtiIBP = view.findViewById(R.id.txti_IBP);
        btn_add_vital = view.findViewById(R.id.btn_add_vital);
        user_id = Controller.pref.getString("USER_ID", "");
        rgroub_stool = view.findViewById(R.id.rg_stool);
        rgroub_stool.check(R.id.rbtn_notpass);
        btn_add_vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                V_txtiTEMP = txtiTEMP.getText().toString();
                V_txtiSaO2 = txtiSaO2.getText().toString();
                V_txtiRRmin = txtiRRmin.getText().toString();
                V_txtiEtCO2mmHg = txtiEtCO2mmHg.getText().toString();
                V_txtiHeartRate = txtiHeartRate.getText().toString();
                V_txtiNIBP = txtiNIBP.getText().toString();
                V_txtiNIBP_lower = txti_NIBP_lower.getText().toString();
                V_txtiUrine = txtiUrine.getText().toString();
                V_txtiCVP = txtiCVP.getText().toString();
                V_txtiIBP = txtiIBP.getText().toString();
                v_stool = rgroub_stool.getCheckedRadioButtonId() == R.id.rbtn_notpass ? 0 : 1;

                ArrayList<View> mListViews = new ArrayList<>();
                mListViews = getAllChildren(containerVitalSign);
                int cnt = 0;
                for (int i = 0; i < mListViews.size(); i++) {
                    View mView = mListViews.get(i);
                    if (mView instanceof TextInputEditText) {
//                        if (mView.getTag() != null && ((TextInputEditText) mView).getText().toString().isEmpty())
//                            error += "\n Please Fill " + ((TextInputLayout)mView.getParent().getParent()).getHint().toString() + " Field";
                        if (!((TextInputEditText) mView).getText().toString().isEmpty())
                            ++cnt;
                    }
                }

                if (cnt>0)
                    AddvitalsignForPatient();
                else
                    Toast.makeText(getContext(), "الرجاء ادخال الملاحظة أو العلامات الحيوية على الأقل", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }


    public void AddvitalsignForPatient() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"");
        map.put("ADM_CD", ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"");
        map.put("USER_ID", user_id);
        map.put("TEMPC_VALUE", V_txtiTEMP);
        map.put("SAO2_VALUE", V_txtiSaO2);
        map.put("RRMIN_VALUE", V_txtiRRmin);
        map.put("ETCO2_VALUE", V_txtiEtCO2mmHg);
        map.put("HEART_RATE_VALUE", V_txtiHeartRate);
        map.put("URINE_OUT_VALUE", V_txtiUrine);
        map.put("NIBP_VALUE", V_txtiNIBP);
        map.put("NIBP_VALUE_LOWER", V_txtiNIBP_lower);
        map.put("CVP_VALUE", V_txtiCVP);
        map.put("IBP_VALUES", V_txtiIBP);
        map.put("NOTE", txti_notes.getText().toString());
        map.put("P_ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        map.put("P_STOOL", v_stool + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "العلامات الحيوية");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.NEW_INSERT_VITAL_SIGN_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        replaceAddDialogFragment();
                    } else
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                mInterfacePatient.showLoading(false);
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
        getActivity().onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
