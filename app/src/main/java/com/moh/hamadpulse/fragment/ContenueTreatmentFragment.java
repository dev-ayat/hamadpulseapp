package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.EMP_SERIAL;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.moh.hamadpulse.models.CardviewDataModel;
import com.moh.hamadpulse.models.GetMedDoseConst;
import com.moh.hamadpulse.models.GetTreatmentForPatient;
import com.wang.avi.AVLoadingIndicatorView;

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
public class ContenueTreatmentFragment extends DialogFragment {
    String patname, patid, admdate, patadmcd, user_id,
            dosagecd, Med_NAME_String, Medicinecd, Routecd, interval, dosagevalue, doasename, inputdosage, wantedamount, serial;
    Button btn_treatplan, btn_delete;
    TextInputEditText txti_interval, txti_TotalAmount;
    TextView txtDrug, txtdoasename;
    Spinner DosageSpinner;
    ArrayList<Object> getMedDoseConstArrayList;
    AdapterSpinner dosageSpinnerAdapter;
    GetTreatmentForPatient mGetTreatmentForPatient;
    CardviewDataModel mCardviewDataModel;
    AVLoadingIndicatorView imgLoading;

    public interface hideDialog
    {
        void hide(GetTreatmentForPatient mGetTreatmentForPatient,String action);
    }
    hideDialog mhideDialog;

    public ContenueTreatmentFragment(GetTreatmentForPatient mGetTreatmentForPatient,CardviewDataModel mCardviewDataModel,hideDialog mhideDialog) {
        // Required empty public constructor
        this.mGetTreatmentForPatient= mGetTreatmentForPatient;
        this.mCardviewDataModel= mCardviewDataModel;
        this.mhideDialog=mhideDialog;
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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contenue_treatment, container, false);
        imgLoading = view.findViewById(R.id.imgLoading);
        btn_treatplan = view.findViewById(R.id.btn_treatplan);
        btn_delete = view.findViewById(R.id.btn_delete);
        txti_interval = view.findViewById(R.id.txti_interval);
        txti_TotalAmount = view.findViewById(R.id.txti_TotalAmount);
        DosageSpinner = view.findViewById(R.id.DosageSpinner);
        txtDrug = view.findViewById(R.id.txtDrug);
        txtdoasename = view.findViewById(R.id.txtdosename);
        user_id = Controller.pref.getString("USER_ID", "");

        serial = mGetTreatmentForPatient.getINPPHARMCODE();
        dosagecd = mGetTreatmentForPatient.getDOSECODE();
        dosagevalue = mGetTreatmentForPatient.getDOSEVAUE();
        interval = mGetTreatmentForPatient.getINPINTERVAL();
        doasename = mGetTreatmentForPatient.getDOSENAME();
        Routecd = mGetTreatmentForPatient.getDOSEGIVINGC();
        Med_NAME_String = mGetTreatmentForPatient.getITEMNAME();
        Medicinecd = mGetTreatmentForPatient.getMEDMCODE();
        wantedamount = mGetTreatmentForPatient.getINPWANTEDAMOUNT();
        txtDrug.setText(Med_NAME_String);
        txtdoasename.setText(doasename);
        txti_interval.setText(interval);
        txti_TotalAmount.setText(wantedamount);


        txti_interval.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                inputdosage = txti_interval.getText().toString();
                if (inputdosage.length() > 0) {
                    CalcMedAmount();
                }
            }
        });

        getMedDoseConstArrayList = new ArrayList<>();
        dosageSpinnerAdapter = new AdapterSpinner(getContext(), 0, getMedDoseConstArrayList);
        DosageSpinner.setAdapter(dosageSpinnerAdapter);
        DosageSpinner.setSelection(getIndex(DosageSpinner, doasename));

        PreparegetMedDoseConst();
        DosageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dosagecd = ((GetMedDoseConst) DosageSpinner.getSelectedItem()).getDOSECODE();
                dosagevalue = ((GetMedDoseConst) DosageSpinner.getSelectedItem()).getDOSEVAUE();
                if (dosagecd.length() > 0) {
                    if (dosagevalue.length() > 0) {
                        //   CalcMedAmount();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_treatplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TotalInterval = txti_interval.getText().toString();
                String TotalMedAmount = txti_TotalAmount.getText().toString();

                Handler aHandler = new Handler();
                aHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Controller.LOADER_DIALOG.showDialog("جاري إضافة العلاج");
                    }
                }, 3000);
                AddMedicinForPatient(Medicinecd, dosagecd, Routecd, TotalInterval, TotalMedAmount);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDeleteMsg();
            }
        });
        Log.e("MY_TEST","VISIT"+" "+mGetTreatmentForPatient.getDIFF_MIN()+" "+mGetTreatmentForPatient.getINPPHARMCREATEDBYCD());
        if (mGetTreatmentForPatient.getDIFF_MIN() < 5 && Controller.pref.getString(EMP_SERIAL, "").equals(mGetTreatmentForPatient.getINPPHARMCREATEDBYCD()))
            btn_delete.setVisibility(View.VISIBLE);
        else
            btn_delete.setVisibility(View.GONE);
        return view;
    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    public void CalcMedAmount() {
        float count_dosagevalue = Float.parseFloat(dosagevalue);
        // String interval=txti_interval.getText().toString();
        float countinterval = Float.parseFloat(inputdosage);
        float res = count_dosagevalue * countinterval;
        txti_TotalAmount.setText(String.valueOf(res));
    }

    public void AddMedicinForPatient(String Medicinecd, String dosagecd, String Routecd, String interval, String TotalAmount) {
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", mCardviewDataModel.getPatid()+"");
        map.put("ADM_CD", mCardviewDataModel.getAdmcd()+"");
        map.put("USER_ID", user_id);
        map.put("MED_CD", Medicinecd);
        map.put("DOSE_CD", dosagecd);
        map.put("DOSE_TYPE", Routecd);
        map.put("INTERVAL", interval);
        map.put("WANT_AMOUNT", TotalAmount);
        imgLoading.setVisibility(View.VISIBLE);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_MEDICINS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", "response=" + response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        try {
                            GetTreatmentForPatient mNewGetTreatmentForPatient = (GetTreatmentForPatient)mGetTreatmentForPatient.clone();
                            mNewGetTreatmentForPatient.setINPINTERVAL(interval);
                            mNewGetTreatmentForPatient.setINPWANTEDAMOUNT(TotalAmount);
                            mhideDialog.hide(mNewGetTreatmentForPatient,"1");
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        //replaceAddDialogFragment();
                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                imgLoading.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                imgLoading.setVisibility(View.GONE);
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

    public void PreparegetMedDoseConst() {
        imgLoading.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<>();
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_MED_DOSAGE_CONSTATNS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("ALL_DOSES");
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
                imgLoading.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                imgLoading.setVisibility(View.GONE);
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
    }


    public void ShowDeleteMsg() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendDeletOrder();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void sendDeletOrder() {

        imgLoading.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", mCardviewDataModel.getPatid()+"");
        map.put("ADM_CD", mCardviewDataModel.getAdmcd()+"");
        map.put("USER_ID", user_id);
        map.put("SERIAL", mGetTreatmentForPatient.getINPPHARMCODE());
        map.put("HOS_NO", "");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DELETE_TREATMENT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", "response=" + response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        //replaceAddDialogFragment();
                        mhideDialog.hide(mGetTreatmentForPatient,"-1");
                    } else {
                        Toast.makeText(getContext(), "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                imgLoading.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                imgLoading.setVisibility(View.GONE);
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