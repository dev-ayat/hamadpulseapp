package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.NuserMedEXecAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.DocPharmacyDetails;
import com.moh.hamadpulse.models.GetNurseMedExecution;
import com.moh.hamadpulse.models.SpinCnt;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentMedecineEX extends DialogFragment {
    DocPharmacyDetails mDocPharmacyDetails;
    AVLoadingIndicatorView imgLoading;
    Spinner Procedurespinner;
    TextInputEditText txti_notes, txti_dose;
    String spinnerval, NURSING_NOTE;
    FloatingActionButton btn_add_note;
    InterfacePatient mInterfacePatient;
    TextInputLayout dose_layout;
    TextView txtItemName_val, txtDosage_val, txtRoute_val, txtInter_val, txtTotal_val, txtNote_val, txt_done;

    RecyclerView nurse_med_recyclerview;
    ArrayList<GetNurseMedExecution> getnurseArrayList;
    NuserMedEXecAdapter nuserMedEXecAdapter;
    boolean isReturned;
    int remaining_dose;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }


    public FragmentMedecineEX(DocPharmacyDetails mDocPharmacyDetails) {
        // Required empty public constructor
        this.mDocPharmacyDetails = mDocPharmacyDetails;
    }
    public FragmentMedecineEX( ) {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medcine_execution, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgLoading = view.findViewById(R.id.imgLoading);
        txt_done = view.findViewById(R.id.txt_done);
        txti_notes = view.findViewById(R.id.txti_notes);
        txti_dose=view.findViewById(R.id.txti_dose);
        btn_add_note = view.findViewById(R.id.btn_add_note);
        dose_layout = view.findViewById(R.id.dose_layout);
        Procedurespinner = (Spinner) view.findViewById(R.id.ProcedureSpinner);

        txtItemName_val=view.findViewById(R.id.txtItemName_val);
        txtDosage_val=view.findViewById(R.id.txtDosage_val);
        txtRoute_val=view.findViewById(R.id.txtRoute_val);
        txtInter_val=view.findViewById(R.id.txtInter_val);
        txtTotal_val = view.findViewById(R.id.txtTotal_val);
        txtNote_val = view.findViewById(R.id.txtNote_val);
        nurse_med_recyclerview = view.findViewById(R.id.nurse_med_recycler_view);
        getnurseArrayList = new ArrayList<>();
        nuserMedEXecAdapter=new NuserMedEXecAdapter(getnurseArrayList,getContext());
        nurse_med_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        nurse_med_recyclerview.setHasFixedSize(true);
        nurse_med_recyclerview.setAdapter(nuserMedEXecAdapter);


        txtItemName_val.setText(mDocPharmacyDetails.getITEMNAME() + "");
        txtDosage_val.setText(mDocPharmacyDetails.getDOSENAME() + "");
        txtRoute_val.setText(mDocPharmacyDetails.getDOSGNAME() + "");
        txtInter_val.setText(mDocPharmacyDetails.getINPINTERVAL() + "");
        txtTotal_val.setText(mDocPharmacyDetails.getINPWANTEDAMOUNT() + "");
        txtNote_val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDocPharmacyDetails.getiNPNOTE() != null) {

                    Controller.Msg_DIALOG.showDialog("" + mDocPharmacyDetails.getiNPNOTE());
                } else {
                    Controller.Msg_DIALOG.showDialog("لا يوجد ملاحظات");

                }
            }
        });

        initializespinner();
        GET_EXECUTE_MED_NURSE();


        Procedurespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < 5) {
                    spinnerval = ((SpinCnt) Procedurespinner.getSelectedItem()).getSPIN_CODE();
                    dose_layout.setHint("الجرعة");
                    btn_add_note.setImageResource(android.R.drawable.ic_menu_add);
                    isReturned = false;
                } else {
                    dose_layout.setHint("الكمية المرجعة");
                    isReturned = true;
                    btn_add_note.setImageResource(R.drawable.return_arrow);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String P_INMED_TREAT_CD =mDocPharmacyDetails.getINPMEDCD();
                String P_INMED_ADM_CD =((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"";
                String P_INMED_NURSE_CD = Controller.pref.getString("USER_ID","");
                String P_INMED_DOSE_IN = String.valueOf(txti_dose.getText());
                String P_INMED_NOTE_IN = String.valueOf(txti_notes.getText());
                String P_INMED_IS_GIVEN = spinnerval;
                String P_INMED_ORDER_CD = mDocPharmacyDetails.getINPORDERCD();
                AddNursingPro(P_INMED_TREAT_CD, P_INMED_ADM_CD, P_INMED_NURSE_CD,
                        P_INMED_DOSE_IN, P_INMED_NOTE_IN, P_INMED_IS_GIVEN, P_INMED_ORDER_CD);
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();

    }
    private void initializespinner() {

        ArrayList<SpinCnt> proc = new ArrayList<>();

        proc.add(new SpinCnt("0", "اختر الإجراء المناسب"));
        proc.add(new SpinCnt("1", "منتظر التنفيذ"));
        proc.add(new SpinCnt("2", "تحت العلاج"));
        proc.add(new SpinCnt("3", "تم"));
        proc.add(new SpinCnt("4", "المريض رفض العلاج"));
        proc.add(new SpinCnt("5", "ارجاع العلاج"));

        ArrayAdapter<SpinCnt> adapter = new ArrayAdapter<SpinCnt>(getContext(), android.R.layout.simple_spinner_dropdown_item, proc);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Procedurespinner.setAdapter(adapter);

    }


    public void AddNursingPro(String P_INMED_TREAT_CD, String P_INMED_ADM_CD, String P_INMED_NURSE_CD,
                              String P_INMED_DOSE_IN, String P_INMED_NOTE_IN, String P_INMED_IS_GIVEN, String P_INMED_ORDER_CD) {
        Toast.makeText(getContext(), isReturned + "", Toast.LENGTH_LONG);
        Log.e("return", (Integer.parseInt(P_INMED_DOSE_IN) != remaining_dose) + "");
        Log.e("isReturned", isReturned + "" + "");
        if (isReturned) {
            if (P_INMED_ADM_CD.isEmpty()) {
                Toast.makeText(getContext(), "الرجاء إدخال الكمية", Toast.LENGTH_LONG).show();
                return;
            }
            if (Integer.parseInt(P_INMED_DOSE_IN) != remaining_dose) {
                Toast.makeText(getContext(), "الكمية المرجعة لا تساوي الكمية المتبقية", Toast.LENGTH_LONG).show();
            } else {
                NURSING_NOTE = txti_notes.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("P_RETURN_ORDER_CD", mDocPharmacyDetails.getINPORDERCD());
                map.put("P_RETURN_MED_CD", P_INMED_TREAT_CD);
                map.put("P_RETURN_DOSE", P_INMED_DOSE_IN);
                map.put("P_RETURN_NOTE", P_INMED_NOTE_IN);
                map.put("P_RETURN_CREATED_BY", P_INMED_NURSE_CD);
                map.put("TRANS_SCREEN_CD_IN", 42 + "");
                map.put("TRANS_USER_CODE_IN", P_INMED_NURSE_CD);
                map.put("TRANS_ACTION_CD_IN", "1");
                map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                        getActivity()).getmCardviewDataModel().getPatid() + "");
                map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
                map.put("TRANS_DESCRIPTION_IN", P_INMED_ORDER_CD);
                Log.e("map", map.toString());
                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_MED_RETURN_URL, map, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("P_RESULT");
                            if (res == 1) {
                                Toast.makeText(getContext(), "تمت الإرجاع بنجاح", Toast.LENGTH_SHORT).show();
                                Procedurespinner.setSelection(0);
                                txti_dose.setText("");
                                txti_notes.setText("");
//                                GET_EXECUTE_MED_NURSE();
                                // getActivity().onBackPressed();
                            } else
                                Toast.makeText(getContext(), "لم يتم الإضافة", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //      mInterfacePatient.showLoading(false);

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
        } else {
            if (spinnerval == null || spinnerval.equals("0")) {
                Toast.makeText(getContext(), "الرجاء اختيار الإجراء المناسب", Toast.LENGTH_SHORT).show();
                return;
            }
            NURSING_NOTE = txti_notes.getText().toString();
            Map<String, String> map = new HashMap<>();
            map.put("P_INMED_TREAT_CD", P_INMED_TREAT_CD);
            map.put("P_INMED_ADM_CD", P_INMED_ADM_CD);
            map.put("P_INMED_NURSE_CD", P_INMED_NURSE_CD);
            map.put("P_INMED_DOSE", P_INMED_DOSE_IN);
            map.put("P_INMED_NOTE_IN", P_INMED_NOTE_IN);
            map.put("P_INMED_IS_GIVEN", P_INMED_IS_GIVEN);
            map.put("P_INMED_ORDER_CD", P_INMED_ORDER_CD);

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.ADD_MED_EXECUTION_URL, map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        int res = response.getInt("P_RESULT");
                        if (res == 1) {
                            Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                            Procedurespinner.setSelection(0);
                            txti_dose.setText("");
                            txti_notes.setText("");
                            GET_EXECUTE_MED_NURSE();
                            // getActivity().onBackPressed();
                        } else
                            Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //      mInterfacePatient.showLoading(false);

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

    }

    public void GET_EXECUTE_MED_NURSE() {

        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_TREAT_CD", mDocPharmacyDetails.getINPMEDCD() + "");
        map.put("P_INMED_ORDER_CD", mDocPharmacyDetails.getINPORDERCD() + "");
        Log.e("ayat55", map.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_MED_EXECUTION_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("MED_EXE_CUR");
                    //////////////////
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetNurseMedExecution>>() {
                    }.getType();
                    getnurseArrayList = gson.fromJson(jsonArray.toString(), type);
                    int sum = 0;
                    if (getnurseArrayList.size() > 0) {

                        for (GetNurseMedExecution item : getnurseArrayList) {
                            if (item.getInmedDose() != null)
                                sum += Integer.parseInt(item.getInmedDose());
                        }
                        if (sum >= ((int) Double.parseDouble(mDocPharmacyDetails.getINPWANTEDAMOUNT()))) {
                            btn_add_note.setVisibility(View.GONE);
                            txt_done.setVisibility(View.VISIBLE);
                            txti_dose.setEnabled(false);
                            Procedurespinner.setEnabled(false);

                        }
                    }
                    remaining_dose = ((int) Double.parseDouble(mDocPharmacyDetails.getINPWANTEDAMOUNT())) - sum;
                    nuserMedEXecAdapter.setNurseMedExecutionList(getnurseArrayList);
                    nuserMedEXecAdapter.notifyDataSetChanged();


                    // getIcdConstArrayList.add(getIcdConstArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());

            }
        });
    }

}