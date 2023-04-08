package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.AdmissionDiagnosisModel;
import com.moh.hamadpulse.models.GetICD10Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Outing_Diagnosis_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    InterfacePatient mInterfacePatient;
    ArrayList<AdmissionDiagnosisModel> list;
    DischargeInterFace dischargeInterFace;

    //    ArrayList<>
    public Outing_Diagnosis_Adapter(Context context, ArrayList<AdmissionDiagnosisModel> list,
                                    InterfacePatient mInterfacePatient,
                                    DischargeInterFace dischargeInterFace) {
        this.context = context;
        this.list = list;
        this.mInterfacePatient = mInterfacePatient;
        this.dischargeInterFace = dischargeInterFace;

    }

    public ArrayList<AdmissionDiagnosisModel> getList() {
        return list;
    }

    public void setList(ArrayList<AdmissionDiagnosisModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == 1 ? new Outing_Diagnosis_Adapter.AddingOutingViewHolder
                (LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.row_add_diagnosis, parent, false)) :
                new Outing_Diagnosis_Adapter.OutingViewHolder(LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.row_out_diagnosis, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//      hide or show discharge button in discharge fragment plus change status of patient if the user
//      delete all of diagnosis
        dischargeInterFace.updateDicharge(getPatientDiagCount(), 0);
        AdmissionDiagnosisModel model = list.get(position);
        if (model.getType() == 1) {
            AddingOutingViewHolder myHolder = (AddingOutingViewHolder) holder;
            AutoCompleteTextView auto_diag = myHolder.auto_diag;

            auto_diag.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() >= 3)
                        PreparegetIcd(charSequence.toString(), auto_diag);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            GetICD10Const[] icd10Const = new GetICD10Const[1];
            auto_diag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    icd10Const[0] = (GetICD10Const) adapterView.getItemAtPosition(i);
                    auto_diag.setText(icd10Const[0].getICDNAMEEN());
                    model.setICD_NAME_EN(icd10Const[0].getICDNAMEEN());
                    model.setDIAGNOSIS_ICD_CD(icd10Const[0].getICDCODE());
                }
            });
            myHolder.add_new_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (auto_diag.getText().toString().isEmpty())
                        Toast.makeText(context, "يجب اختيار التشخيص أولاً", Toast.LENGTH_SHORT).show();
                    else
                        add_new_diag(icd10Const[0], position, myHolder.auto_diag);
                }
            });
        } else {
            OutingViewHolder myHolder = (OutingViewHolder) holder;
            myHolder.txt_diagnosis.setText(model.getICD_NAME_EN());
            Log.d("code", model.getDIAGNOSIS_ICD_CD());
            myHolder.d_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context)
                            .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                            .setIcon(R.drawable.testicon)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    delete_diag(model.getDIAGNOSIS_ICD_CD(), position);
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
            });
        }
    }

    private void delete_diag(String diag_cd, int position) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) context).getmCardviewDataModel().getAdmcd() + "");
        map.put("DIAGNOSIS_CD", diag_cd);
        map.put("USER_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", "19");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "5");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "حذف التشخبص");
        Log.e("map", map.toString());
        MyRequest.makeRquest(context, Controller.DELETE_ADMISSION_DIAGNOSIS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(context, "تمت عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        notifyDataSetChanged();
                        dischargeInterFace.updateDicharge(getPatientDiagCount(), 1);
                        mInterfacePatient.showLoading(false);
                    } else {
                        mInterfacePatient.showLoading(false);
                        Toast.makeText(context, "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                    Log.e("json", "ERROR");
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
                mInterfacePatient.showLoading(false);
            }
        });
    }

    private void add_new_diag(GetICD10Const getICD10Const, int position, AutoCompleteTextView autoCompleteTextView) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) context).getmCardviewDataModel().getAdmcd() + "");
        map.put("USER_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", "19");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "ترشيح للخروج");
        map.put("ICD_CODE", getICD10Const.getICDCODE());
        MyRequest.makeRquest(context, Controller.INSERT_PATIENT_DIAGNOSIS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(context, "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        list.get(position).setType(0);
                        autoCompleteTextView.setText("");
                        notifyDataSetChanged();
                    } else if (res == 2) {
                        Toast.makeText(context, "تم إضافة هذا التشخيص مسبقا للمريض", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "لم تتم الإضافة", Toast.LENGTH_SHORT).show();
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
                Controller.view_error(error, context);
                mInterfacePatient.showLoading(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void PreparegetIcd(String ICD_String, AutoCompleteTextView auto_diag) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("D_NAME", ICD_String);
        MyRequest.makeRquest(context, Controller.GET_ICDS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ALL_ICD");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetICD10Const>>() {
                    }.getType();
                    ArrayList<GetICD10Const> getIcdConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    ICD10ConstAutoCompleteAdapter icd10ConstAdapter = new
                            ICD10ConstAutoCompleteAdapter(context, 0, getIcdConstArrayList);
                    auto_diag.setAdapter(icd10ConstAdapter);
                    auto_diag.setThreshold(0);
                    auto_diag.showDropDown();
                    icd10ConstAdapter.notifyDataSetChanged();
                    mInterfacePatient.showLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mInterfacePatient.showLoading(false);
                }

            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
                mInterfacePatient.showLoading(false);
            }
        });

    }
    class AddingOutingViewHolder extends RecyclerView.ViewHolder {
        AutoCompleteTextView auto_diag;
        ImageButton add_new_btn;

        public AddingOutingViewHolder(@NonNull View itemView) {
            super(itemView);
            auto_diag = itemView.findViewById(R.id.auto_diag);
            add_new_btn = itemView.findViewById(R.id.add_new_btn);
        }
    }

    class OutingViewHolder extends RecyclerView.ViewHolder {
        TextView txt_diagnosis;
        ImageButton d_delete_btn;

        public OutingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_diagnosis = itemView.findViewById(R.id.txt_diagnosis);
            d_delete_btn = itemView.findViewById(R.id.d_delete_btn);
        }
    }

    public interface DischargeInterFace {
        //      1 if process is delete
        void updateDicharge(int size, int delete);
    }

    public int getPatientDiagCount() {
        int size = 0;
        for (AdmissionDiagnosisModel item : list) {
            if (item.getType() == 0) {
                size = 1;
                break;
            }
        }
        return size;
    }
}

