package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.EditTextDialogFragment;
import com.moh.hamadpulse.models.ExternalDrugsModel;
import com.moh.hamadpulse.models.ExternalMedicineModel;
import com.moh.hamadpulse.models.GetMedDoseConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DischargeDrugsAdapter extends RecyclerView.Adapter<DischargeDrugsAdapter.DischargeDrugsViewHolder> {
    private final Context context;
    ArrayList<ExternalDrugsModel> dischargeDrugsList;
    ArrayList<ExternalMedicineModel> externalMedicineModels;
    ArrayList<Object> getMedDoseConstArrayList;
    AdapterSpinner mAdapterSpinnerRepeat;
    Gson gson;
    ExternalDrugsModel externalDrugsModel;

    public DischargeDrugsAdapter(ArrayList<ExternalMedicineModel> externalMedicineModels, Context context) {
        this.externalMedicineModels = externalMedicineModels;
        this.context = context;
        getDose();
    }


    public ArrayList<ExternalMedicineModel> getExternalMedicineModels() {
        return externalMedicineModels;
    }

    public void setExternalMedicineModels(ArrayList<ExternalMedicineModel> externalMedicineModels) {
        this.externalMedicineModels = externalMedicineModels;
    }

    public ArrayList<ExternalDrugsModel> getdischargeDrugsList() {
        return dischargeDrugsList;
    }

    public void setdischargeDrugsList(ArrayList<ExternalDrugsModel> dischargeDrugsList) {
        this.dischargeDrugsList = dischargeDrugsList;
    }

    @NonNull
    @Override
    public DischargeDrugsViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DischargeDrugsViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.add_new_outing_drugs, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DischargeDrugsViewHolder holder, int position) {
        ExternalMedicineModel docModel = externalMedicineModels.get(position);
        holder.RepeatSpinner.setAdapter(mAdapterSpinnerRepeat);
        if (docModel.getOrderStatus().equals("1")) {
            holder.txti_Drug.setText(docModel.getItemName());
            holder.txti_Drug.setEnabled(false);
            holder.DosageFormTv.setText(docModel.getDragForm());
            holder.Repeat_TV.setVisibility(View.VISIBLE);
            holder.RepeatSpinner.setVisibility(View.GONE);
            holder.Repeat_TV.setText(docModel.getDoseName());
            holder.txti_drug_dose.setText(docModel.getRepeat());
            holder.txti_drug_dose.setEnabled(false);
            holder.txti_Duration.setText(docModel.getDuration());
            holder.txti_Duration.setEnabled(false);
            holder.txti_Quantity.setText(docModel.getTotal());
            holder.btn_delete_drug.setImageResource(R.drawable.available_medicine);
            holder.btn_delete_drug.setEnabled(false);
        } else if (docModel.getOrderStatus().equals("0")) {
            holder.txti_Drug.setText(docModel.getItemName());
            holder.txti_Drug.setEnabled(true);
            holder.DosageFormTv.setText(docModel.getDragForm());
            holder.Repeat_TV.setVisibility(View.GONE);
            holder.RepeatSpinner.setVisibility(View.VISIBLE);
            holder.RepeatSpinner.setSelection(
                    getMedDoseConstArrayList.indexOf(new GetMedDoseConst(docModel.getDosage(),
                            docModel.getDoseName())));
            holder.Repeat_TV.setText(docModel.getDoseName());
            holder.txti_drug_dose.setText(docModel.getDosage());
            holder.txti_drug_dose.setEnabled(true);
            holder.txti_Duration.setText(docModel.getDuration());
            holder.txti_Duration.setEnabled(true);
            holder.txti_Quantity.setText(docModel.getTotal());
            holder.btn_delete_drug.setImageResource(R.drawable.no_available_medicine);
            holder.btn_delete_drug.setEnabled(true);
            AutoCompleteTextView medTV = holder.txti_Drug;
            medTV.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 2) {
                        getMedicin(charSequence.toString().trim(), medTV);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            medTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    externalDrugsModel = (ExternalDrugsModel) parent.getItemAtPosition(position);
                    medTV.setText(externalDrugsModel.getItemName());
                    docModel.setItemCode(externalDrugsModel.getItemCode());
                    docModel.setItemName(externalDrugsModel.getItemName());
                    docModel.setDragCD(externalDrugsModel.getMedMCode());
                    docModel.setDragForm(externalDrugsModel.getUnitName());
                    holder.DosageFormTv.setText(externalDrugsModel.getUnitName());
                }
            });

            holder.RepeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    GetMedDoseConst mGetMedDoseConstSelected = (GetMedDoseConst)
                            getMedDoseConstArrayList.get(i);
                    if (i != 0) {
                        docModel.setDoseName(mGetMedDoseConstSelected.getDOSENAME());
                        docModel.setDosage(mGetMedDoseConstSelected.getDOSECODE());
                        Log.e("dose_value", mGetMedDoseConstSelected.getDOSENAME() + "");
                        CalcMedAmount(i, holder.txti_Duration.getText().toString().trim(),
                                holder.txti_drug_dose.getText().toString().trim(),
                                holder.txti_Quantity);
                    }
//                    Log.e("dose value test",mGetMedDoseConstSelected.getDOSEVAUE());


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            holder.txti_drug_dose.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    docModel.setRepeat(charSequence.toString().trim());
                    CalcMedAmount(holder.RepeatSpinner.getSelectedItemPosition(), holder.txti_Duration.getText().toString().trim()
                            , charSequence.toString().trim(), holder.txti_Quantity);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    externalMedicineModels.remove(position);
                    notifyItemChanged(position);
                }
            });
            TextView duration_tv = holder.txti_Duration;
//     store value when duration textview lose focus and also calculate quantity
            duration_tv.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String duration = charSequence.toString().trim();
                    docModel.setDuration(duration);
                    CalcMedAmount(holder.RepeatSpinner.getSelectedItemPosition(), duration, holder.txti_drug_dose.getText().toString().trim()
                            , holder.txti_Quantity);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            holder.txti_Quantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    docModel.setTotal(charSequence.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            holder.txti_show_notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                            getSupportFragmentManager().beginTransaction();
                    new EditTextDialogFragment(docModel, null)
                            .show(ft, "External drugs note");
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return externalMedicineModels.size();
    }


    public void getMedicin(String medicinName, AutoCompleteTextView autoDrugname) {
        Map<String, String> map = new HashMap<>();
        map.put("P_NAME", medicinName);
        map.put("P_LOC_CODE_IN", ((ActivityPatient) context)
                .getmCardviewDataModel().getLOC_CODE() + "");
        Log.e("map", "getMedicin: " + map);
        MyRequest.makeRquest(context, Controller.GET_EXTERNAL_PHARM_MED_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();
                    ArrayList<Object> mListMedicine = gson.fromJson(mJSONObject.getString("EXTERNAL_MED"), new TypeToken<ArrayList<ExternalDrugsModel>>() {
                    }.getType());
                    AdapterSpinner mAdapterSpinnerMedicin = new AdapterSpinner(context, 0, mListMedicine);
                    autoDrugname.setAdapter(mAdapterSpinnerMedicin);
                    autoDrugname.setThreshold(0);
                    autoDrugname.showDropDown();
                    mAdapterSpinnerMedicin.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
            }
        });

    }

    private void getDose() {
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) context).getmCardviewDataModel().getHOS_NO() + "");
        MyRequest.makeRquest(context, Controller.GET_MED_DOSAGE_CONSTATNS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    getMedDoseConstArrayList = gson.fromJson(mJSONObject.getString("ALL_DOSES"), new TypeToken<ArrayList<GetMedDoseConst>>() {
                    }.getType());
                    GetMedDoseConst mGetMedDoseConst = new GetMedDoseConst();
                    mGetMedDoseConst.setDOSENAME("اختر التكرار ..");
                    getMedDoseConstArrayList.add(0, mGetMedDoseConst);
                    mAdapterSpinnerRepeat = new AdapterSpinner(context, 0, getMedDoseConstArrayList);
//                    mAdapterSpinnerDosage.setmListObject(getMedDoseConstArrayList);
//                    if(mDocPharmacyDetails.getINPDOSECD()!=null)
//                        for(int i=0;i<getMedDoseConstArrayList.size();i++)
//                            if(mDocPharmacyDetails.getINPDOSECD().equals(((GetMedDoseConst)getMedDoseConstArrayList.get(i)).getDOSECODE()))
//                                DosageSpinner.setSelection(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
            }
        });
    }

    public void CalcMedAmount(int index, String txtInterval, String dose_value, EditText txti_TotalAmount) {
        if (!txtInterval.isEmpty() && !dose_value.isEmpty() && index != 0) {
            try {
                float count_dosagevalue = 0;
                count_dosagevalue = Float.parseFloat(((GetMedDoseConst) getMedDoseConstArrayList.get(index)).getDOSEVAUE());
                float countinterval = Float.parseFloat(txtInterval);
                float dose = Float.parseFloat(dose_value);
                float res = count_dosagevalue * countinterval * dose;
                txti_TotalAmount.setText(String.valueOf(res));
            } catch (Exception e) {
                txti_TotalAmount.setText("");
            }
        } else
            txti_TotalAmount.setText("");
    }

    class DischargeDrugsViewHolder extends RecyclerView.ViewHolder {
        AutoCompleteTextView txti_Drug;
        TextView DosageFormTv, txti_show_notes, Repeat_TV;
        Spinner RepeatSpinner;
        EditText txti_Duration, txti_Quantity, txti_drug_dose;
        ImageButton btn_delete_drug;

        public DischargeDrugsViewHolder(@NonNull View itemView) {
            super(itemView);
            txti_Drug = itemView.findViewById(R.id.txti_Drug);
            DosageFormTv = itemView.findViewById(R.id.DosageFormTv);
            txti_show_notes = itemView.findViewById(R.id.txti_show_notes);
            RepeatSpinner = itemView.findViewById(R.id.RepeatSpinner);
            txti_Duration = itemView.findViewById(R.id.txti_Duration);
            txti_Quantity = itemView.findViewById(R.id.txti_Quantity);
            btn_delete_drug = itemView.findViewById(R.id.btn_delete_drug);
            txti_drug_dose = itemView.findViewById(R.id.txti_drug_dose);
            Repeat_TV = itemView.findViewById(R.id.Repeat_TV);
        }
    }
}
