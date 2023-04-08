package com.moh.hamadpulse.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.DepartmentsDataAdapter;
import com.moh.hamadpulse.adapters.DoctorsNameAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.CardviewDataModel;
import com.moh.hamadpulse.models.DoctorNameModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientTransfer extends Fragment {
    TextView txt_Trans_Date, txt_Trans_Time, txt_patientIn, txt_patientInDate, txt_patientInDep, txt_patientInTime, txt_patientDoctor, txt_patientOUTDep;
    ImageButton btn_pick_date, btn_time_picker;
    AutoCompleteTextView txt_patientOutDoctor, txt_Doc, txt_ConDoctor;
    DepartmentsDataAdapter MasterAdapter;
    Spinner sp_outdep, sp_dep;
    DepartmentsDataModel outDep, inDep;
    CardviewDataModel cardviewDataModel;
    ArrayList<DepartmentsDataModel> departmentsDataModels;
    String P_ADM_OUT_LOC_CD, P_ADM_ENTER_LOC_CD, DOCOUT, DOCIN, TRAN_ENTER_DATE, TRAN_TIME_DATE;
    MaterialButton btn_transfer;

    public PatientTransfer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_transfaer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        txt_Trans_Date = view.findViewById(R.id.txt_Trans_Date);
        btn_pick_date = view.findViewById(R.id.btn_pick_date);
        txt_Trans_Time = view.findViewById(R.id.txt_Trans_Time);
        btn_time_picker = view.findViewById(R.id.btn_time_picker);
        txt_patientOutDoctor = view.findViewById(R.id.txt_patientOutDoctor);

        cardviewDataModel = ((ActivityPatient) getActivity()).getmCardviewDataModel();
        txt_patientIn = view.findViewById(R.id.txt_patientIn);
        txt_patientIn.setText(cardviewDataModel.getAdmcd() + "");
        txt_patientInDate = view.findViewById(R.id.txt_patientInDate);
        txt_patientInDep = view.findViewById(R.id.txt_patientInDep);
        txt_patientInDep.setText(cardviewDataModel.getLoc_name_ar());
        txt_patientInTime = view.findViewById(R.id.txt_patientInTime);
        txt_patientDoctor = view.findViewById(R.id.txt_patientDoctor);
        // sp_outdep = view.findViewById(R.id.sp_outdep);
        txt_patientDoctor = view.findViewById(R.id.txt_patientDoctor);
        txt_patientOUTDep = view.findViewById(R.id.txt_patientOUTDep);
        txt_patientOUTDep.setText(cardviewDataModel.getLoc_name_ar());
        P_ADM_OUT_LOC_CD = cardviewDataModel.getLOC_CODE();
        txt_patientDoctor.setText(cardviewDataModel.getDocname());

        String date = cardviewDataModel.getIndate();
        String[] parts = date.split(" ");

        txt_patientInDate.setText(parts[0]);
        txt_patientInTime.setText(parts[1]);

        btn_transfer = view.findViewById(R.id.btn_transfer);

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trans_patient();
            }
        });


//        sp_outdep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                outDep = (DepartmentsDataModel) adapterView.getItemAtPosition(i);
//                P_ADM_OUT_LOC_CD= outDep.getLocCode();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        sp_dep = view.findViewById(R.id.sp_dep);
        sp_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inDep = (DepartmentsDataModel) adapterView.getItemAtPosition(i);
                P_ADM_ENTER_LOC_CD = inDep.getLocCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txt_patientOutDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DoctorNameModel model = (DoctorNameModel) adapterView.getItemAtPosition(i);
                txt_patientOutDoctor.setText(model.getFull_name());
                DOCOUT = model.getEMP_ID();
            }
        });
        txt_patientOutDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 2)
                    setDoctorsNames(txt_patientOutDoctor);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /***********************************/
        txt_Doc = view.findViewById(R.id.txt_Doc);
        txt_Doc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DoctorNameModel model = (DoctorNameModel) adapterView.getItemAtPosition(i);
                txt_Doc.setText(model.getFull_name());
                DOCIN = model.getEMP_ID();
            }
        });
        txt_Doc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 2)
                    setDoctorsNames(txt_Doc);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /***********************************/
        txt_ConDoctor = view.findViewById(R.id.txt_ConDoctor);
        txt_ConDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DoctorNameModel model = (DoctorNameModel) adapterView.getItemAtPosition(i);
                txt_ConDoctor.setText(model.getFull_name());
            }
        });
        txt_ConDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 2)
                    setDoctorsNames(txt_ConDoctor);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /***********************************/
        btn_pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog date_dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txt_Trans_Date.setText((day < 10 ? "0" + day : day) + "/" + ((++month) < 10 ? "0" + month : month) + "/" + year);
                        TRAN_ENTER_DATE = txt_Trans_Date.getText().toString();
                    }
                }, year, month, day);
                date_dialog.show();
            }
        });
        btn_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int min) {
                        txt_Trans_Time.setText((hours < 10 ? ("0" + hours) : hours) + ":" + (min < 10 ? "0" + min : min));
                        TRAN_TIME_DATE = txt_Trans_Time.getText().toString();
                    }
                }, hours, min, true);
                dialog.show();
            }
        });
        getAllDepartments();

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (departmentsDataModels != null)
                    sp_outdep.setSelection(departmentsDataModels.indexOf(new DepartmentsDataModel()));

            }
        }, 500);
        sp_outdep.setEnabled(false);*/
    }

    void setDoctorsNames(AutoCompleteTextView autoCompleteTextView) {
        Map<String, String> map = new HashMap<>();
        map.put("P_NAME", autoCompleteTextView.getText().toString().trim());

        MyRequest.makeRquest(getContext(), Controller.GET_DOCTOR_BY_NAME_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {

                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("DOCTORS_CUR");

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<DoctorNameModel>>() {
                    }.getType();

                    ArrayList<DoctorNameModel> nameModels = gson.fromJson(jsonArray.toString(), type);
                    DoctorsNameAdapter adapter = new DoctorsNameAdapter(getContext(), 0, nameModels);
                    autoCompleteTextView.setAdapter(adapter);
                    autoCompleteTextView.setThreshold(0);
                    autoCompleteTextView.showDropDown();
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });
    }

    public void getAllDepartments() {
//        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        MyRequest.makeRquest(getContext(), Controller.GET_ALL_DEPARTMENTS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                Log.d("response", response.toString());
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    JSONObject jsonObject = mJSONObject.getJSONObject("DEPARTMENTS_CUR");
                    DepartmentsModel model = gson.fromJson(jsonObject.toString(),
                            DepartmentsModel.class);
                    DepartmentsDataModel departmentsDataModel = new DepartmentsDataModel();
                    departmentsDataModel.setLocNameAr("اختر القسم ..");

                    model.getDepartments().add(0, departmentsDataModel);
                    departmentsDataModels = model.getDepartments();
                    MasterAdapter = new DepartmentsDataAdapter(getContext(), 0, departmentsDataModels);
                    //    sp_outdep.setAdapter(MasterAdapter);
                    sp_dep.setAdapter(MasterAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());

            }
        });


    }



    public void trans_patient() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADMISSION_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_MRP_PATREC_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ADM_OUT_LOC_CD", P_ADM_OUT_LOC_CD + "");
        map.put("P_OUT_DOC_CD", DOCOUT + "");
        map.put("P_IN_DOC_CD", DOCIN + "");
        map.put("TRAN_ENTER_DATE", TRAN_ENTER_DATE + "");
        map.put("TRAN_TIME_DATE", TRAN_TIME_DATE + "");
        map.put("P_ADM_ENTER_LOC_CD", P_ADM_ENTER_LOC_CD + "");
        map.put("TRANS_SCREEN_CD_IN", 49 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "transfer patient");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("transmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.TRANSPORT_PATIENT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم نقل المريض", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else if(res==7){
                        Toast.makeText(getContext(), "للمريض إجراءات في القسم لم يتم الإنتهاء منها", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(), "لم تتم عملية النقل",
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
        ((ActivityPatient) getActivity()).setTitle("نقل المريض");
    }
}


