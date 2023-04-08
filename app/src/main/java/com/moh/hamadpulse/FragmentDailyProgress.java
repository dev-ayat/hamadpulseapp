package com.moh.hamadpulse;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.Util.MinMaxFilter;
import com.moh.hamadpulse.constants.Delivery;
import com.moh.hamadpulse.models.AdmPatientConst;
import com.moh.hamadpulse.models.ExamCnt;
import com.moh.hamadpulse.models.ExaminationAdm;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentDailyProgress extends Fragment {

    ArrayList<ExamCnt> mListExamCnt;
    ArrayList<AdmPatientConst> mListAdmPatientConst;
    RequestQueue mRequestQueue;
    String fragment_cd = "1";
    ExaminationAdm mExaminationAdm;
    LinearLayout containerDetailsO2;

    public FragmentDailyProgress(ExaminationAdm mExaminationAdm) {
        // Required empty public constructor
        this.mExaminationAdm = mExaminationAdm;
    }

    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_progress, container, false);
    }


    Spinner spinnerExam,spinnerAdmPatientConst,spinnerDelivery;
    private EditText txtRespiratory;
    private EditText txtABD;
    private EditText txtCVS;
    private EditText txtCNS;
    FloatingActionButton btnAddExam;
    EditText edProgrssNote;
    private EditText edMin;
    //    private RadioGroup rgO2;
//    private RadioButton rbNo;
//    private RadioButton rbYes;
    Switch switchO2;
    ArrayAdapter<ExamCnt> mArrayAdapter;
    ArrayAdapter<AdmPatientConst> mAdapterAdmPatientConst;
    EditText edO2;
    ExamCnt ExamCntSelected;
    Delivery DeliverySelected;
    AdmPatientConst AdmPatientConstSelected;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerExam = view.findViewById(R.id.spinnerExam);
        spinnerAdmPatientConst = view.findViewById(R.id.spinnerAdmPatientConst);
        spinnerDelivery = view.findViewById(R.id.spinnerDelivery);
        txtRespiratory = view.findViewById(R.id.txtRespiratory);
        edProgrssNote = view.findViewById(R.id.edProgrssNote);
        txtABD = view.findViewById(R.id.txtABD);
        txtCVS = view.findViewById(R.id.txtCVS);
        txtCNS = view.findViewById(R.id.txtCNS);
        switchO2 = view.findViewById(R.id.switchO2);
        edO2 = view.findViewById(R.id.edO2);
        edMin = view.findViewById(R.id.edMin);
        containerDetailsO2 = view.findViewById(R.id.containerDetailsO2);
        edMin.setFilters(new InputFilter[]{new MinMaxFilter("1", "60")});
        switchO2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    containerDetailsO2.setVisibility(View.VISIBLE);
                else
                    containerDetailsO2.setVisibility(View.GONE);


                //Toast.makeText(getContext(), b+"rbYes", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddExam = view.findViewById(R.id.btnAddExam);
        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addExam();
                if (!isValid().isEmpty())
                    Toast.makeText(getContext(), "" + isValid().replaceFirst("\n", ""), Toast.LENGTH_SHORT).show();
                else
                    addExam();
            }
        });


        spinnerExam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ExamCntSelected = mListExamCnt.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DeliverySelected = mListDelivery.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAdmPatientConst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AdmPatientConstSelected = mListAdmPatientConst.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getTest();
        getAdmPatientConst();
        getDelivery();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mExaminationAdm != null) {
                    edProgrssNote.setText(mExaminationAdm.getEXAM_NOTE());
                    spinnerAdmPatientConst.setSelection(
                            mListAdmPatientConst.indexOf
                                    (new AdmPatientConst(mExaminationAdm.getPATIENT_STATUS_NAME_EN())));
                    spinnerExam.setSelection(
                            mListExamCnt.indexOf(
                                    new ExamCnt(mExaminationAdm.getEXAM_NAME())));
                    txtRespiratory.setText(mExaminationAdm.getEXAM_RESP());
                    txtABD.setText(mExaminationAdm.getEXAM_ABD());
                    txtCVS.setText(mExaminationAdm.getEXAM_CVS());
                    txtCNS.setText(mExaminationAdm.getEXAM_CNS());
                    edProgrssNote.setText(mExaminationAdm.getEXAM_NOTE());
                    if (mExaminationAdm.getIS_O2_NEED_CD().equals("1")) {
                        switchO2.setChecked(true);
                        spinnerDelivery.setSelection(mListDelivery.indexOf(new Delivery(mExaminationAdm.getTYPE_O2_THERAPY())));
                        edMin.setText(mExaminationAdm.getEXAM_O2_FLOW_RATE());
                    }

                }
            }
        }, 500);

    }

    public String isValid()
    {
        String error = "";
        if(txtABD.getText().toString().isEmpty())
            error+="\n Please Fill ABD";
        if (txtCNS.getText().toString().isEmpty())
            error += "\n Please Fill CNS";
        if (txtCVS.getText().toString().isEmpty())
            error += "\n Please Fill CVS";
        if (txtRespiratory.getText().toString().isEmpty())
            error += "\n Please Fill Respiratory";
        if (ExamCntSelected.getEXAM_CODE().equals("-1"))
            error += "\n Please Choose Consciousness level";
        if (AdmPatientConstSelected.getPATIENTSTATUSID().equals("-1"))
            error += "\n Please Choose Patient status";
//        if(switchO2.isChecked()) {
//            if(edO2.getText().toString().isEmpty())
//            error += "\n Please fill O2 thrapy";
//        }
        return error;
    }

    private void addExam() {

        String AdmCd = ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"";
        mInterfacePatient.showLoading(true);
        mRequestQueue = Volley.newRequestQueue(getContext());

        StringRequest sr = new StringRequest(Request.Method.POST,Controller.ADD_EXAMINATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mInterfacePatient.showLoading(false);
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    String res = mJSONObject.getString("RESULT");
                    if (res.equals("1")) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else if (res.equals("2")) {
                        Toast.makeText(getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم تتم العملية بنجاح", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("P_EXAM_SERIAL", mExaminationAdm != null ? mExaminationAdm.getEXAM_SERIAL() : "-1");//-1 for add case
                params.put("P_EXAM_CD", ExamCntSelected.getEXAM_CODE());
                params.put("P_RESP_CD",txtRespiratory.getText().toString());
                params.put("P_CVS_CODE",txtCVS.getText().toString());
                params.put("P_ABD_CD", txtABD.getText().toString());
                params.put("P_CNS_CD", txtCNS.getText().toString());
                params.put("P_IS_O2_NEED", switchO2.isChecked() ? "1" : "0");
                //params.put("P_TYPE_O2",switchO2.isChecked()?edO2.getText().toString():"");
                params.put("P_TYPE_O2", switchO2.isChecked() ? DeliverySelected.getDELIVERY_CODE() : "");
                params.put("P_FLOW_RATE", switchO2.isChecked() ? edMin.getText().toString() : "");
                params.put("P_USER_ID", Controller.pref.getString("USER_ID", ""));
                params.put("P_ADMISSION_CD", AdmCd);
                params.put("P_SPIN_CD", AdmPatientConstSelected.getPATIENTSTATUSID());
                params.put("P_EXAM_NOTE", edProgrssNote.getText().toString());

                params.put("TRANS_SCREEN_CD_IN", fragment_cd);
                params.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
                params.put("TRANS_ACTION_CD_IN", "1");
                params.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
                params.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
                params.put("TRANS_DESCRIPTION_IN", "DAILY PROG ");
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                params.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
                Log.e("test_map", "map=" + params.toString());
                return params;
            }

        };
        mRequestQueue.add(sr);
    }

    ArrayList<Delivery> mListDelivery;
    public void populateSpinnerDelivery(ArrayList<Delivery> mListDelivery)
    {
        mArrayAdapter = new ArrayAdapter(getContext(),  R.layout.my_spinner_textview, mListDelivery);
        //mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mArrayAdapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinnerDelivery.setAdapter(mArrayAdapter);
    }

    public void populateSpinnerExam(ArrayList<ExamCnt> mListExamCnt)
    {
        mArrayAdapter = new ArrayAdapter(getContext(),  R.layout.my_spinner_textview, mListExamCnt);
        //mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mArrayAdapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinnerExam.setAdapter(mArrayAdapter);
    }
    public void populateSpinnerPatientStatus(ArrayList<AdmPatientConst> mListAdmPatientConst)
    {
        mAdapterAdmPatientConst = new ArrayAdapter(getContext(),  R.layout.my_spinner_textview, mListAdmPatientConst);
        //mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAdapterAdmPatientConst.setDropDownViewResource(R.layout.my_spinner_textview);
        spinnerAdmPatientConst.setAdapter(mAdapterAdmPatientConst);
    }
    public void getTest()
    {
        mInterfacePatient.showLoading(true);
        mRequestQueue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.GET,Controller.GET_EXAM_CNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    mListExamCnt = gson.fromJson(mJSONObject.getString("EXAM_CNT"), new TypeToken<ArrayList<ExamCnt>>() {}.getType());
                    ExamCnt mExamCnt = new ExamCnt("-1","Choose Examination");
                    mListExamCnt.add(0,mExamCnt);
                    ExamCntSelected = mExamCnt;
                    populateSpinnerExam(mListExamCnt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                return params;
            }

        };
        mRequestQueue.add(sr);
    }
    public void getDelivery()
    {
        mInterfacePatient.showLoading(true);
        mRequestQueue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.GET,Controller.GET_O2_DEVICE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    mListDelivery = gson.fromJson(mJSONObject.getString("P_RESULT"), new TypeToken<ArrayList<Delivery>>() {}.getType());
                    Delivery mDelivery = new Delivery("-1","اختر","Choose Device");
                    mListDelivery.add(0,mDelivery);
                    DeliverySelected = mDelivery;
                    populateSpinnerDelivery(mListDelivery);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                return params;
            }

        };
        mRequestQueue.add(sr);
    }
    public void getAdmPatientConst()
    {
        mInterfacePatient.showLoading(true);
        mRequestQueue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.GET,Controller.GET_PATIENT_STATUS_CONSTATNS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    mListAdmPatientConst = gson.fromJson(mJSONObject.getString("STATUS_CONSTATNS"), new TypeToken<ArrayList<AdmPatientConst>>() {}.getType());
                    AdmPatientConst mAdmPatientConst = new AdmPatientConst("-1","Choose Patient status");
                    mListAdmPatientConst.add(0,mAdmPatientConst);
                    AdmPatientConstSelected = mAdmPatientConst;
                    populateSpinnerPatientStatus(mListAdmPatientConst);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                return params;
            }

        };
        mRequestQueue.add(sr);
    }

    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("Add Daily Progrees");
    }
}