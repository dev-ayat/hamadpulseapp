package com.moh.hamadpulse.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterSpinner;
import com.moh.hamadpulse.adapters.ClinicsAppointmentsAdapter;
import com.moh.hamadpulse.adapters.DischargeDrugsAdapter;
import com.moh.hamadpulse.adapters.ICD10ConstAutoCompleteAdapter;
import com.moh.hamadpulse.adapters.Outing_Diagnosis_Adapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.AdmissionDiagnosisModel;
import com.moh.hamadpulse.models.ClinicAppointmentsModel;
import com.moh.hamadpulse.models.ExternalMedicineModel;
import com.moh.hamadpulse.models.GetICD10Const;
import com.moh.hamadpulse.models.MasterClinicModel;
import com.moh.hamadpulse.models.SubClinicModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DischargeFragment extends Fragment implements ClinicsAppointmentsAdapter.RecyclerviewClickListener, Outing_Diagnosis_Adapter.DischargeInterFace {
    String user_id;
    LinearLayout no_appointments, no_p_appointments;
    RecyclerView rv_appointments, rv_patient_appointments, diagnosis_rv;
    ImageButton view_appointment_btn;
    MaterialButton btn_save_appointment;
    DatePickerDialog from_datePickerDialog;
    AutoCompleteTextView auto_diag1, auto_diag2, auto_diag3;
    Spinner main_sp, sec_sp;
    Button img_diagsearch1, img_diagsearch2, img_diagsearch3, btn_add_diag, btn_discharge, btn_new_external_med, btn_save_new_Drug, from_date_btn;
    ICD10ConstAutoCompleteAdapter icd10ConstAdapter;
    ArrayList<GetICD10Const> getIcdConstArrayList;
    String ICD_String, ICD_CD;
    ArrayList MyOutputlist;
    String fragment_cd = "19";
    DischargeDrugsAdapter adapter;
    RecyclerView outing_drugs_rv;
    AdapterSpinner MasterAdapter, SubAdapter;
    MasterClinicModel masterClinicModel;
    SubClinicModel subClinicModel;
    String dateFrom = "2022/2/2";
    ArrayList<ClinicAppointmentsModel> aList;
    ClinicsAppointmentsAdapter appointmentsAdapter;
    TextView book_result;
    ClinicAppointmentsModel appointmentsModel;
    DialogMsg dialogMsg;
    boolean have_diagnosis = false;
    Outing_Diagnosis_Adapter outing_diagnosis_adapter;
    DischargeFragment dischargeFragment;

    public DischargeFragment(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient = mInterfacePatient;
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
        return inflater.inflate(R.layout.fragment_discharge, container, false);

    }

    private void initDatePickerDialog() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateFrom = (i2 < 10 ? "0" + i2 : i2 + "") + "/" + ((++i1) < 10 ? "0" + i1 : i1 + "") + "/" + i;
                from_date_btn.setText(dateFrom);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        from_datePickerDialog = new DatePickerDialog(getContext(), style, onDateSetListener
                , year, month, day + 1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dischargeFragment = this;
        dialogMsg = new DialogMsg(getContext());
        adapter = new DischargeDrugsAdapter(new ArrayList<ExternalMedicineModel>(), getContext());
        rv_appointments = view.findViewById(R.id.rv_appointments);
        btn_save_appointment = view.findViewById(R.id.btn_save_appointment);
        book_result = view.findViewById(R.id.book_result);
        auto_diag1 = view.findViewById(R.id.auto_diag1);
        auto_diag2 = view.findViewById(R.id.auto_diag2);
        auto_diag3 = view.findViewById(R.id.auto_diag3);
        img_diagsearch1 = view.findViewById(R.id.img_diagsearch1);
        img_diagsearch2 = view.findViewById(R.id.img_diagsearch2);
        img_diagsearch3 = view.findViewById(R.id.img_diagsearch3);
        btn_discharge = view.findViewById(R.id.btn_discharge);
        btn_add_diag = view.findViewById(R.id.btn_add_diag);
        from_date_btn = view.findViewById(R.id.from_date_btn);
        no_appointments = view.findViewById(R.id.no_appointments);
        no_p_appointments = view.findViewById(R.id.no_p_appointments);
        rv_patient_appointments = view.findViewById(R.id.rv_patient_appointments);
        diagnosis_rv = view.findViewById(R.id.diagnosis_rv);
        from_date_btn.setText(getCurrentDate());
        have_diagnosis();
        from_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from_datePickerDialog.show();
            }
        });

        view_appointment_btn = view.findViewById(R.id.view_appointment_btn);
        view_appointment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (have_diagnosis) {
                    if (main_sp.getSelectedItemPosition() != 0) {
                        getAllClincsAppointments();
                    }
                } else {
                    Toast.makeText(getContext(), "يجب ترشيح المريض للخروج أولاً", Toast.LENGTH_LONG).show();
                }

            }
        });
        main_sp = view.findViewById(R.id.main_sp);
        sec_sp = view.findViewById(R.id.sec_sp);
        initDatePickerDialog();
        getAllExternalMedicine();
        getAllMasterClinics(main_sp);
        outing_drugs_rv = view.findViewById(R.id.outing_drugs_rv);
        outing_drugs_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        outing_drugs_rv.setAdapter(adapter);
        btn_new_external_med = view.findViewById(R.id.btn_new_external_med);
        btn_new_external_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (have_diagnosis) {
                    ExternalMedicineModel model = new ExternalMedicineModel();
                    model.setOrderStatus("0");
                    adapter.getExternalMedicineModels().add(model);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "يجب ترشيح المريض للخروج أولاً", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_save_new_Drug = view.findViewById(R.id.btn_save_new_Drug);
        btn_save_new_Drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_new_external_medicine();
            }
        });
        user_id = Controller.pref.getString("USER_ID", "");
        getIcdConstArrayList = new ArrayList<>();
        MyOutputlist = new ArrayList<>();
        img_diagsearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICD_String = auto_diag1.getText().toString();
                PreparegetIcd(ICD_String, auto_diag1);
            }
        });
        auto_diag1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GetICD10Const icd10Const = (GetICD10Const) adapterView.getItemAtPosition(i);
                auto_diag1.setText(icd10Const.getICDNAMEEN());
                ICD_CD = icd10Const.getICDCODE();
                if (ICD_CD != null && ICD_CD.length() > 0) {
                    MyOutputlist.add(ICD_CD);
                } else {
                    MyOutputlist.remove(ICD_CD);
                }
            }
        });

        img_diagsearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICD_String = auto_diag2.getText().toString();
                PreparegetIcd(ICD_String, auto_diag2);
            }
        });
        auto_diag2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GetICD10Const icd10Const = (GetICD10Const) adapterView.getItemAtPosition(i);
                auto_diag2.setText(icd10Const.getICDNAMEEN());
                ICD_CD = icd10Const.getICDCODE();

                if (ICD_CD != null && ICD_CD.length() > 0) {
                    MyOutputlist.add(ICD_CD);
                } else {
                    MyOutputlist.remove(ICD_CD);
                }

            }
        });

        img_diagsearch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICD_String = auto_diag3.getText().toString();
                PreparegetIcd(ICD_String, auto_diag3);
            }
        });
        auto_diag3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GetICD10Const icd10Const = (GetICD10Const) adapterView.getItemAtPosition(i);
                auto_diag3.setText(icd10Const.getICDNAMEEN());
                ICD_CD = icd10Const.getICDCODE();
                if (ICD_CD != null && ICD_CD.length() > 0) {
                    MyOutputlist.add(ICD_CD);
                } else {
                    MyOutputlist.remove(ICD_CD);
                }
            }
        });
        btn_discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discharge(1);
            }
        });
        btn_add_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int size = MyOutputlist.size();
//                if (size == 0) {
//                    Toast.makeText(getContext(), "الرجاء إدخال تشخيص واحد على الأقل", Toast.LENGTH_SHORT).show();
//                } else {
//                    DischagePatient(MyOutputlist);
//                }
                AdmissionDiagnosisModel model = new AdmissionDiagnosisModel();
                model.setType(1);
                outing_diagnosis_adapter.getList().add(model);
                outing_diagnosis_adapter.notifyDataSetChanged();
            }
        });


        main_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    masterClinicModel = (MasterClinicModel) main_sp.getItemAtPosition(i);
                    getAllSubClinics(masterClinicModel.getClinicCD());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sec_sp.setAdapter(SubAdapter);
        sec_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subClinicModel = (SubClinicModel) sec_sp.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        appointmentsAdapter = new ClinicsAppointmentsAdapter(new ArrayList<>(), this);
        appointmentsAdapter.setAppointmentsTV(book_result);
        // save appointment
        btn_save_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!book_result.getText().toString().isEmpty() && appointmentsModel != null) {
                    book_appointment();
                } else {
                    Toast.makeText(getContext(), "الرجاء اختيار موعد ليتم حجزه", Toast.LENGTH_LONG).show();
                }
            }
        });
        getDiagForPatient();
        getPatientClincsAppointments();
    }

    private void discharge(int status) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", ((ActivityPatient) getContext()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ADM_CD", ((ActivityPatient) getContext()).getmCardviewDataModel().getAdmcd() + "");
        map.put("PATIENT_STATUS", status + "");
        map.put("USER_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", "19");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "5");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getContext()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "تخريج المريض أو الغاء التخريج");
        Log.e("map_update", map.toString());
        MyRequest.makeRquest(getContext(), Controller.UPDATE_OUT_STATUS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت عملية ترشيح المريض للخروج بنجاح", Toast.LENGTH_SHORT).show();
                        have_diagnosis();
                    } else if (res == 2) {
                        Toast.makeText(getContext(), "تمت عملية إلغاء ترشيح المريض للخروج بنجاح", Toast.LENGTH_SHORT).show();
                        getPatientClincsAppointments();
                    } else if (res == 3) {
                        Toast.makeText(getContext(), "المريض مرشح للخروج بالفعل", Toast.LENGTH_SHORT).show();
                    } else if (res == 4) {
                        Toast.makeText(getContext(), "لم تتم عملية إلغاء الترشيح للخروج بنجاح", Toast.LENGTH_SHORT).show();
                    }
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                    Log.e("json", "ERROR");
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void book_appointment() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_VISIT_TM_SERIAL", appointmentsModel.getVisit_id());
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "update clinics appointments");
        MyRequest.makeRquest(getContext(), Controller.UPDATE_CLINICS_APPOINTMENTS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 2) {
//                        Toast.makeText(getContext(), "تمت حجز الموعد بنجاح", Toast.LENGTH_SHORT).show();
                        dialogMsg.showDialog("تم حجز الموعد بنجاح");
                        book_result.setText("");
                        getPatientClincsAppointments();
                        getAllClincsAppointments();
                    } else {
//                        Toast.makeText(getContext(), "لم يتم حجز الموعد", Toast.LENGTH_SHORT).show();
                        dialogMsg.showDialog("لم يتم حجز الموعد");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void getDiagForPatient() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        MyRequest.makeRquest(getContext(), Controller.GET_ADMISSION_DIAGNOSIS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<AdmissionDiagnosisModel> list = gson.fromJson(mJSONObject.getString("ADMISSION_DIAGNOSIS"),
                            new TypeToken<ArrayList<AdmissionDiagnosisModel>>() {
                            }.getType());
                    Log.e("list-size", list.size() + "");
                    if (list.size() > 0)
                        btn_discharge.setVisibility(View.VISIBLE);
                    else
                        btn_discharge.setVisibility(View.GONE);
//                    if (list.size() == 0) {
//                        no_p_appointments.setVisibility(View.VISIBLE);
//                        rv_patient_appointments.setVisibility(View.GONE);
//                    } else {
//                        no_p_appointments.setVisibility(View.GONE);
//                        rv_patient_appointments.setVisibility(View.VISIBLE);
                    outing_diagnosis_adapter = new Outing_Diagnosis_Adapter(getContext(), list, mInterfacePatient
                            , dischargeFragment);
                    diagnosis_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    diagnosis_rv.setAdapter(outing_diagnosis_adapter);
//                    }
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void getPatientClincsAppointments() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        MyRequest.makeRquest(getContext(), Controller.GET_P_CLINICS_APPOINTMENTS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<ClinicAppointmentsModel> list = gson.fromJson(mJSONObject.getString("P_CLINICS_APPOINTMENTS"),
                            new TypeToken<ArrayList<ClinicAppointmentsModel>>() {
                            }.getType());
                    if (list.size() == 0) {
                        no_p_appointments.setVisibility(View.VISIBLE);
                        rv_patient_appointments.setVisibility(View.GONE);
                    } else {
                        no_p_appointments.setVisibility(View.GONE);
                        rv_patient_appointments.setVisibility(View.VISIBLE);
                        ClinicsAppointmentsAdapter adapter = new ClinicsAppointmentsAdapter(list);
                        rv_patient_appointments.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_patient_appointments.setAdapter(adapter);
                    }
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void getAllClincsAppointments() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("MAIN_CLINIC_CD", masterClinicModel.getClinicCD());
        map.put("SUB_CLINIC_CD", sec_sp.getSelectedItemPosition() == 0 ? "" : subClinicModel.getlOC_CODE());
        map.put("APPOIMENTS_DATE", from_date_btn.getText().toString());
        MyRequest.makeRquest(getContext(), Controller.GET_CLINICS_APPOINTMENTS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    aList = gson.fromJson(mJSONObject.getString("CLINICS_APPOINTMENTS"),
                            new TypeToken<ArrayList<ClinicAppointmentsModel>>() {
                            }.getType());
                    if (aList.size() == 0) {
                        no_appointments.setVisibility(View.VISIBLE);
                        rv_appointments.setVisibility(View.GONE);
                    } else {
                        no_appointments.setVisibility(View.GONE);
                        rv_appointments.setVisibility(View.VISIBLE);
                        appointmentsAdapter.setList(aList);
                        rv_appointments.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_appointments.setAdapter(appointmentsAdapter);
                        appointmentsAdapter.notifyDataSetChanged();
                    }
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void getAllSubClinics(String clinicCD) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("C_LOC_MASTER_CD", clinicCD);
        MyRequest.makeRquest(getContext(), Controller.GET_SUB_CLINICS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<Object> list = gson.fromJson(mJSONObject.getString("SUB_CLINICS"),
                            new TypeToken<ArrayList<SubClinicModel>>() {
                            }.getType());
                    SubClinicModel model = new SubClinicModel();
                    model.setlOC_NAME_AR("اختر العيادة ..");
                    list.add(0, model);
                    SubAdapter = new AdapterSpinner(getContext(), 0, list);
                    sec_sp.setAdapter(SubAdapter);
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    public void PreparegetIcd(String ICD_String, AutoCompleteTextView auto_diag) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("D_NAME", ICD_String);

        MyRequest.makeRquest(getContext(), Controller.GET_ICDS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {

                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ALL_ICD");

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetICD10Const>>() {
                    }.getType();

                    getIcdConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    icd10ConstAdapter = new ICD10ConstAutoCompleteAdapter(getContext(), 0, getIcdConstArrayList);
                    auto_diag.setAdapter(icd10ConstAdapter);
                    auto_diag.setThreshold(0);
                    auto_diag.showDropDown();
                    icd10ConstAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });

    }

    public void have_diagnosis() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "ترشيح للخروج");
        MyRequest.makeRquest(getContext(), Controller.GET_PATIENT_DIAGNOSIS_COUNT_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    have_diagnosis = res > 0;
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    public void DischagePatient(ArrayList MyOutputlist) {

        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("USER_ID", user_id);
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "ترشيح للخروج");
        for (int i = 0; i < MyOutputlist.size(); i++)
            map.put("ICD_CODE[" + i + "]", MyOutputlist.get(i).toString());

        MyRequest.makeRquest(getContext(), Controller.INSERT_PATIENT_DIAGNOSIS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        have_diagnosis();
                    } else {
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });

    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("ترشيح المريض للخروج");
    }

    private void add_new_external_medicine() {
        mInterfacePatient.showLoading(true);
        ArrayList<ExternalMedicineModel> list = adapter.getExternalMedicineModels();
        int size = list.size();
        boolean flag = true;
//        ensure submit request without un valid data
        for (ExternalMedicineModel item : list) {
            if (!item.isItemValid()) {
                flag = false;
                break;
            }
        }
        if (flag) {

            Map<String, String> map = new HashMap<>();
            map.put("P_ADM_CD", ((ActivityPatient)
                    getActivity()).getmCardviewDataModel().getAdmcd() + "");
            map.put("P_PATREC_CD", ((ActivityPatient)
                    getActivity()).getmCardviewDataModel().getPatid() + "");
            map.put("P_CREATED_BY", Controller.pref.getString("USER_ID", ""));
            for (int i = 0; i < size; i++) {
                ExternalMedicineModel item = list.get(i);
                if (item.getOrderStatus().equals("0")) {
                    map.put("items[" + i + "][P_MED_CODE]", item.getDragCD());
                    map.put("items[" + i + "][P_DOSE_CD]", item.getDosage());
                    map.put("items[" + i + "][P_MED_FORM]", item.getDragForm());
                    map.put("items[" + i + "][P_REPEAT]", item.getRepeat());
                    map.put("items[" + i + "][P_DURTION_DAY]", item.getDuration());
                    map.put("items[" + i + "][P_WANT_AMOUNT]", item.getTotal());
                    map.put("items[" + i + "][P_NOTE]", item.getNote() == null ? "" : item.getNote());
                }
            }
            map.put("TRANS_SCREEN_CD_IN", "8");
            map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
            map.put("TRANS_ACTION_CD_IN", "1");
            map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                    getActivity()).getmCardviewDataModel().getPatid() + "");
            map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
            map.put("TRANS_DESCRIPTION_IN", "Add new External Medicine");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
            Log.e("ventmap", map.toString());

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.ADD_EXTERNAL_MEDICIN_URL, map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        int res = response.getInt("P_RESULT");
                        if (res == 1) {
                            Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                            getAllExternalMedicine();
                        } else if (res == 2) {
                            Toast.makeText(getContext(), "تمت التحديث بنجاح", Toast.LENGTH_SHORT).show();
                            getAllExternalMedicine();
                        } else {
                            Toast.makeText(getContext(), "لم تتم الإضافة",
                                    Toast.LENGTH_SHORT).show();
                        }

                        mInterfacePatient.showLoading(false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mInterfacePatient.showLoading(false);
                        Log.e("json", "ERROR");
                    }
//                mInterfacePatient.showLoading(false);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    Controller.view_error(volleyError, getContext());
                    mInterfacePatient.showLoading(false);
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
        } else {
            Toast.makeText(getContext(), "please select unselected item or fill the empty fields", Toast.LENGTH_LONG).show();
        }
    }

    public void getAllExternalMedicine() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) getContext())
                .getmCardviewDataModel().getAdmcd() + "");
        map.put("P_PATRIC_CD", ((ActivityPatient) getContext())
                .getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "get external medicine");
        Log.e("map", "getMedicin: " + map);
        MyRequest.makeRquest(getContext(), Controller.GET_EXTERNAL_MEDICIN_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<ExternalMedicineModel> list = gson.fromJson(mJSONObject.getString("EXTERNAL_MEDICIN"),
                            new TypeToken<ArrayList<ExternalMedicineModel>>() {
                            }.getType());
                    adapter.getExternalMedicineModels().clear();
                    adapter.setExternalMedicineModels(list);
                    adapter.notifyDataSetChanged();
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    public void getAllMasterClinics(Spinner spinner) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        MyRequest.makeRquest(getContext(), Controller.GET_MASTER_CLINICS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<Object> list = gson.fromJson(mJSONObject.getString("MASTER_CLINICS"),
                            new TypeToken<ArrayList<MasterClinicModel>>() {
                            }.getType());
                    MasterClinicModel model = new MasterClinicModel();
                    model.setClinicName("اختر العيادة ..");
                    list.add(0, model);
                    Log.e("list_size", list.size() + "");
                    MasterAdapter = new AdapterSpinner(getContext(), 0, list);
                    spinner.setAdapter(MasterAdapter);
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month++;
        int year = calendar.get(Calendar.YEAR);
        return (day + 1) + "/" + month + "/" + year;
    }

    @Override
    public void recyclerviewClickListener(int position) {
        if (aList != null) {
            appointmentsModel = aList.get(position);
        }
    }

    @Override
    public void updateDicharge(int size, int delete) {
        if (delete == 1 && size == 0) {
            btn_discharge.setVisibility(View.GONE);
            discharge(0);
        } else if (delete == 0 && size > 0) {
            btn_discharge.setVisibility(View.VISIBLE);
        }
    }
}
