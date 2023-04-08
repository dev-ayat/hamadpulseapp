package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.GET_NEW_DOC_PHARMACY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.FragmentDailyProgressDashboard;
import com.moh.hamadpulse.FragmentPharm;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdmissionDrugsAdapter;
import com.moh.hamadpulse.models.AdmissionDataModel;
import com.moh.hamadpulse.models.AdmissionDiagnoseModel;
import com.moh.hamadpulse.models.AdmissionHistoryModel;
import com.moh.hamadpulse.models.AdmissionSpecialDataModel;
import com.moh.hamadpulse.models.DocPharmacy;
import com.moh.hamadpulse.models.DocPharmacyDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AdmissionDataFragment extends DialogFragment {
    ListView diagnosis_rv;
    ArrayList<AdmissionDataModel> admissionDataModels;
    TextView txt_ReasonOfAdmission, txt_Remark, txt_note,inDateTF,inDaptTF;
    RecyclerView admission_drugs_rv;
    AdmissionHistoryModel model;
    MaterialCardView lab_result_cv,vital_sign_cv,pharm_cv,daily_progress_cv;
    ActivityPatient activityPatient;

    public AdmissionDataFragment(AdmissionHistoryModel model) {
        this.model=model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admission_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityPatient= (ActivityPatient) getActivity();
        diagnosis_rv = view.findViewById(R.id.diagnosis_rv_);
        txt_ReasonOfAdmission = view.findViewById(R.id.txt_ReasonOfAdmission);
        txt_Remark = view.findViewById(R.id.txt_Remark);
        txt_note = view.findViewById(R.id.txt_note);
        admission_drugs_rv = view.findViewById(R.id.admission_drugs_rv);
        lab_result_cv = view.findViewById(R.id.lab_result_cv);
        vital_sign_cv = view.findViewById(R.id.vital_sign_cv);
        daily_progress_cv = view.findViewById(R.id.daily_progress_cv);
        inDateTF = view.findViewById(R.id.inDateTF);
        inDaptTF = view.findViewById(R.id.inDaptTF);
        pharm_cv = view.findViewById(R.id.pharm_cv);
        inDaptTF.setText(model.getLOC_NAME());
        inDateTF.setText((model.getTIME_IN().split(" "))[0]);//first element of array (date without time)
        lab_result_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPatient.CallFragment(new Lbres_Fragment(model));
            }
        });
        pharm_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPatient.CallFragment(new FragmentPharm(activityPatient,model.getADMISSION_CODE()));
            }
        });
        vital_sign_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            activityPatient.CallFragment(new newVitalSignsFragment(model.getADMISSION_CODE(),activityPatient));
            }
        });
        daily_progress_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPatient.CallFragment(new FragmentDailyProgressDashboard(activityPatient,model.getADMISSION_CODE()));
            }
        });
        getAllAdmissionData();
//        getPharmDocs();

    }

    void getAllAdmissionData() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", model.getADMISSION_CODE());
//        map.put("P_ADM_CD", "9624");
//        map.put("P_ADM_CD", "412627");
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATRIC_CD", "427827");
//        map.put("P_PATRIC_CD", "221538");
//        map.put("P_PATRIC_CD","191211");
//        map.put("P_ADM_CD","412565");
        map.put("TRANS_SCREEN_CD_IN", "47");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Admission Request");
        Log.e("admission", map + "");

        MyRequest.makeRquest(getContext(), Controller.GET_ADMISSION_DATA_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("ADMISSION_DATA");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<AdmissionDataModel>>() {
                            }.getType();
                            admissionDataModels = gson.fromJson(jsonArray.toString(),
                                    type);
                            if (admissionDataModels.size() > 0) {
                                AdmissionDataModel model = admissionDataModels.get(0);
                                fillDiagnoses(model.getAdmissionDiagnoseModels());
                                fillSpecialData(model.getAdmissionSpecialDataModels());
//                                fillInvestigation(model.getAdmissionInvestigationModels());
//                                fillOrder(model.getAdmissionOrdersModels());
                            }
                            getPharmDocs();
                            // getIcdConstArrayList.add(getIcdConstArrayList);
                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        if (getActivity() != null)
                            Controller.view_error(error, getContext());
                    }
                });
    }

    public void getPharmDocs() {
//        mInterfacePatient.showLoading(true);
        HashMap<String, String> h = new HashMap<>();
//        for(int i=0;i<10;i++) {
//            h.put("items[" + i + "][item_code]", "item_code" + i);
//        }
        //h.put("ADM_CD","1140451");
        h.put("TRANS_SCREEN_CD_IN", "47");
        h.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        h.put("TRANS_ACTION_CD_IN", "2");
        h.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        h.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        h.put("TRANS_DESCRIPTION_IN", "صيدلية");
        h.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        MyRequest.makeRquest(getActivity(), GET_NEW_DOC_PHARMACY, h, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Log.e("LOGIN", response);
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    ArrayList<DocPharmacy> mListDocPharmacy = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacy>>() {
                    }.getType());
                    if (mListDocPharmacy.size() > 0) {
                        getAllMed(mListDocPharmacy.get(0));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
//                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void getAllMed(DocPharmacy docPharmacy) {

        String frag_cd = "47";
//            imgLoading.setVisibility(View.VISIBLE);
        HashMap<String, String> h = new HashMap<>();
        h.put("ORDER_CD", docPharmacy.getiNPPHARMCODE());
        h.put("TRANS_SCREEN_CD_IN", frag_cd);
        h.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        h.put("TRANS_ACTION_CD_IN", "2");
        h.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        h.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        h.put("TRANS_DESCRIPTION_IN", "صيدلية");

        Log.e("maph", h.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_DOC_PHARMACY_DETAILS, h, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    Log.e("json", mJSONObject.toString());
                    ArrayList<DocPharmacyDetails> mListDocPharmacyDetails = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacyDetails>>() {
                    }.getType());
                    Log.e("details", mListDocPharmacyDetails.size() + "");
                    AdmissionDrugsAdapter adapter = new AdmissionDrugsAdapter(mListDocPharmacyDetails);
                    admission_drugs_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    admission_drugs_rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    imgLoading.setVisibility(View.GONE);
            }

            @Override
            public void Error(VolleyError error) {
//                    imgLoading.setVisibility(View.GONE);
                Controller.view_error(error, getContext());
            }
        });

    }

//    private void fillOrder(ArrayList<AdmissionOrdersModel> admissionOrdersModels) {
//        if (admissionOrdersModels.size() > 0) {
//            ArrayAdapter<AdmissionOrdersModel> arrayAdapter = new ArrayAdapter<AdmissionOrdersModel>(getContext(),
//                    R.layout.listview_textview, admissionOrdersModels);
//            rv_Orders.setAdapter(arrayAdapter);
//        }
//    }
//
//    private void fillInvestigation(ArrayList<AdmissionInvestigationModel> admissionInvestigationModels) {
//        if (admissionInvestigationModels.size() > 0) {
//            Log.e("inv_size", admissionInvestigationModels.size() + "");
//            ArrayAdapter<AdmissionInvestigationModel> arrayAdapter = new ArrayAdapter<AdmissionInvestigationModel>(getContext(),
//                    R.layout.listview_textview, admissionInvestigationModels);
//            rv_Investigation.setAdapter(arrayAdapter);
//        }
//    }

    private void fillSpecialData(ArrayList<AdmissionSpecialDataModel> admissionSpecialDataModels) {
        if (admissionSpecialDataModels.size() > 0) {
            AdmissionSpecialDataModel model = admissionSpecialDataModels.get(0);
            txt_ReasonOfAdmission.setText(model.getREASON_OF_ADMISSION());
            txt_Remark.setText(model.getADDMISSION_REMARK());
//            txt_Diet.setText(model.getADDMISSION_DIET());
//            txt_SpecialInstruction.setText(model.getADDMISSION_INSTRUCTIONS());
            txt_note.setText(model.getADMISSION_ENTER_DOC_NOTE());
        }
    }

    private void fillDiagnoses(ArrayList<AdmissionDiagnoseModel> admissionDiagnoseModels) {
        ArrayAdapter<AdmissionDiagnoseModel> arrayAdapter = new ArrayAdapter<AdmissionDiagnoseModel>(getContext(),
                R.layout.listview_textview, admissionDiagnoseModels);
        diagnosis_rv.setAdapter(arrayAdapter);
    }
}