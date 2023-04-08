package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.ChemothropyModel;
import com.moh.hamadpulse.models.PostCurModel;
import com.moh.hamadpulse.models.PreCurModel;
import com.moh.hamadpulse.models.TumerRepModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class StutasMedicationConfirmDialog extends DialogFragment {
    Button btn_ok, btn_cancel;
    EditText ET_note;
    TumerRepModel model;
    int status_id, action_id;
    ChemothropyModel chemothropyModel;
    PreCurModel preCurModel;
    PostCurModel postCurModel;
    boolean flag = false, flagPrePostCur = false;

    public StutasMedicationConfirmDialog() {
        // Required empty public constructor
    }

    public StutasMedicationConfirmDialog(TumerRepModel model,
                                         int action_id, int status_id) {
        this.model = model;
        this.status_id = status_id;
        this.action_id = action_id;
    }

    public StutasMedicationConfirmDialog(ChemothropyModel chemothropyModel,
                                         int action_id, int status_id) {
        this.chemothropyModel = chemothropyModel;
        this.status_id = status_id;
        this.action_id = action_id;
        flag = true;
    }

    public StutasMedicationConfirmDialog(PreCurModel preCurModel,
                                         int action_id, int status_id) {
        this.preCurModel = preCurModel;
        this.status_id = status_id;
        this.action_id = action_id;
        flagPrePostCur = true;
    }

    public StutasMedicationConfirmDialog(PostCurModel postCurModel,
                                         int action_id, int status_id) {
        this.postCurModel = postCurModel;
        this.status_id = status_id;
        this.action_id = action_id;
        flagPrePostCur = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stutas_medication_confirm_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        ET_note = view.findViewById(R.id.ET_Status_Note);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagPrePostCur)
                    Save_Protocol_PR();
                else
                    Save_Protocol();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void Save_Protocol() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATREC_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATREC_CODE", "562581");
        map.put("P_TUMORS_ORDER_NO", flag ? chemothropyModel.getTumorsOrderNo() : model.getTumorsOrderNo());
        map.put("P_NP_ACTION_ID", action_id + "");
        map.put("P_STATUS", status_id + "");
        map.put("P_DRUG_NO", flag ? chemothropyModel.getDrugNo() : "");
        map.put("P_NOTES", ET_note.getText().toString().trim());
        map.put("P_CREATED_BY", Controller.pref.getString("USER_ID", ""));
//        map.put("P_ADM_CD", ((ActivityPatient)
//                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", 40 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "PROTOCOL_EXC");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.SAVE_NP_PROTOCOL_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int res = response.getInt("P_RESULT");
                    Log.d("response", res + "");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت إضافة الحالة بنجاح ", Toast.LENGTH_SHORT).show();
                        dismiss();
//
                    } else if (res == 2) {
                        Toast.makeText(getContext(), "تمت تحديث الحالة بنجاح ", Toast.LENGTH_SHORT).show();
                        dismiss();
//
                    } else {
                        Toast.makeText(getContext(), "لم يتم التحديث",
                                Toast.LENGTH_SHORT).show();
//
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//

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

    public void Save_Protocol_PR() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATREC_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATREC_CODE", "562581");
        map.put("P_TUMORS_ORDER_NO", action_id == 1 ? preCurModel.getTumorsOrderNo() : postCurModel.getTumorsOrderNo());
        map.put("P_NP_ACTION_ID", action_id + "");
        map.put("P_STATUS", status_id + "");
        map.put("P_DRUG_NO", action_id == 1 ? preCurModel.getDrugNo() : postCurModel.getDrugNo());
        map.put("P_NOTES", ET_note.getText().toString().trim());
        map.put("P_CREATED_BY", Controller.pref.getString("USER_ID", ""));
//        map.put("P_ADM_CD", ((ActivityPatient)
//                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", 40 + "");
        map.put("TRANS_USER_CODE_IN",
                (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "PROTOCOL_EXC_PrePostCur");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.SAVE_NP_PROTOCOL_PR_DRUGS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int res = response.getInt("P_RESULT");
                    Log.d("response", res + "");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت إضافة الحالة بنجاح ", Toast.LENGTH_SHORT).show();
                        dismiss();
//
                    } else if (res == 2) {
                        Toast.makeText(getContext(), "تمت تحديث الحالة بنجاح ", Toast.LENGTH_SHORT).show();
                        dismiss();
//
                    } else {
                        Toast.makeText(getContext(), "لم يتم التحديث",
                                Toast.LENGTH_SHORT).show();
//
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//

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

    public void UpdateMedicationStatus() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_VISIT_ID", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_ORDER_TYPE", model.getTumorsOrderNo());
        map.put("P_DEPT_TYPE", "");
        map.put("P_STATUS_ID", status_id + "");
        map.put("P_NOTE", ET_note.getText().toString().trim());
        map.put("P_CREATED_BY", Controller.pref.getString("USER_ID", ""));
        map.put("TRANS_SCREEN_CD_IN", 40 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "PROTOCOL_EXC");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.UPDATE_ORDER_STATUS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت تحديث الحالة بنجاح ", Toast.LENGTH_SHORT).show();
                        dismiss();
//
                    } else {
                        Toast.makeText(getContext(), "لم يتم التحديث",
                                Toast.LENGTH_SHORT).show();
//
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//

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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }
}