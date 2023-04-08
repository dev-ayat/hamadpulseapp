package com.moh.hamadpulse;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterSpinner;
import com.moh.hamadpulse.models.DocPharmacyDetails;
import com.moh.hamadpulse.models.GetMedDoseConst;
import com.moh.hamadpulse.models.GetMedRouteConst;
import com.moh.hamadpulse.models.GetMedicineConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DialogFragmentMedicin extends DialogFragment implements View.OnClickListener {
    DocPharmacyDetails mDocPharmacyDetails;
    ArrayList<Object> getMedDoseConstArrayList;
    ArrayList<Object> getMedRouteConstArrayList;
    ArrayList<Object> mListMedicine;
    AdapterSpinner mAdapterSpinnerDosage;
    AdapterSpinner mAdapterSpinnerRoute;
    AdapterSpinner mAdapterSpinnerMedicin;
    Gson gson;
    OnConfirmMedicin mOnConfirmMedicin;
    GetMedicineConst medicinConstSelected;
    GetMedRouteConst mGetMedRouteConstSelected;
    GetMedDoseConst mGetMedDoseConstSelected;
    RadioGroup group;
    RadioButton internal_phram, drug_phram, water_phram;
    int add_update;

    public interface OnConfirmMedicin {
        void confirm(DocPharmacyDetails mDocPharmacyDetails, int add_update);
    }

    public OnConfirmMedicin getmOnConfirmMedicin() {
        return mOnConfirmMedicin;
    }

    public void setmOnConfirmMedicin(OnConfirmMedicin mOnConfirmMedicin) {
        this.mOnConfirmMedicin = mOnConfirmMedicin;
    }

    public DialogFragmentMedicin(DocPharmacyDetails mDocPharmacyDetails
            , int add_update) {
        // Required empty public constructor
        this.mDocPharmacyDetails = mDocPharmacyDetails;
        this.add_update = add_update;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return inflater.inflate(R.layout.fragment_dialog_medicin, container, false);
    }

    private Spinner DosageSpinner;
    private Spinner RouteSpinner;
    private Button btnMedicinConfirm;
    private Button btnMedicinCancel;
    private AutoCompleteTextView autoDrugname;
    private ImageView imgMedSearch;
    TextInputEditText txti_interval, txti_TotalAmount, txti_notes;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DosageSpinner = view.findViewById(R.id.DosageSpinner);
        RouteSpinner = view.findViewById(R.id.RouteSpinner);
        txti_interval = view.findViewById(R.id.txti_interval);
        txti_TotalAmount = view.findViewById(R.id.txti_TotalAmount);
        txti_notes = view.findViewById(R.id.txti_notes);
        water_phram = view.findViewById(R.id.water_phram);
        drug_phram = view.findViewById(R.id.drug_phram);
        internal_phram = view.findViewById(R.id.internal_phram);
        group = view.findViewById(R.id.radio_group);
//      set defuel value of radio button groub
        group.check(R.id.internal_phram);
//      in case update set note
        if (add_update == 1)
            txti_notes.setText(mDocPharmacyDetails.getiNPNOTE());
        btnMedicinConfirm = view.findViewById(R.id.btnMedicinConfirm);
        btnMedicinConfirm.setOnClickListener(this);
        btnMedicinCancel = view.findViewById(R.id.btnMedicinCancel);
        btnMedicinCancel.setOnClickListener(this);
        autoDrugname = view.findViewById(R.id.auto_drugname);
        imgMedSearch = view.findViewById(R.id.img_MedSearch);
        imgMedSearch.setOnClickListener(this);
        if (mDocPharmacyDetails.getINPMEDCD() != null) {
            imgMedSearch.setVisibility(View.GONE);
            autoDrugname.setEnabled(false);
            autoDrugname.setText(mDocPharmacyDetails.getITEMNAME());
        }

        mAdapterSpinnerDosage = new AdapterSpinner(getContext(), 0, new ArrayList<>());
        DosageSpinner.setAdapter(mAdapterSpinnerDosage);
        DosageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGetMedDoseConstSelected = (GetMedDoseConst) getMedDoseConstArrayList.get(position);
                CalcMedAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAdapterSpinnerRoute = new AdapterSpinner(getContext(), 0, new ArrayList<>());
        RouteSpinner.setAdapter(mAdapterSpinnerRoute);
        RouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGetMedRouteConstSelected = (GetMedRouteConst) getMedRouteConstArrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        autoDrugname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medicinConstSelected = (GetMedicineConst) parent.getItemAtPosition(position);
                autoDrugname.setText(medicinConstSelected.getITEMNAME());
                mDocPharmacyDetails.setiTEMCODE(medicinConstSelected.getITEMCODE());
                mDocPharmacyDetails.setiTEMNAME(medicinConstSelected.getITEMNAME());
                mDocPharmacyDetails.setiNPMEDCD(medicinConstSelected.getMEDMCODE());
            }
        });

        txti_interval.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CalcMedAmount();
            }
        });
        if(mDocPharmacyDetails.getINPINTERVAL()!=null)
            txti_interval.setText(mDocPharmacyDetails.getINPINTERVAL());
        getDose();
        getRoute();
        autoDrugname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(group.getCheckedRadioButtonId()==R.id.fav_rbtn&&
                autoDrugname.getText().toString().trim().isEmpty()) {
                    autoDrugname.setText(" ");
                    getMedicin();
                }
            }
        });
        autoDrugname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(group.getCheckedRadioButtonId()==R.id.fav_rbtn&&
                        autoDrugname.getText().toString().trim().isEmpty()) {
                    autoDrugname.setText(" ");
                    getMedicin();
                }
            }
        });
        autoDrugname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(" "))
                    return;

                if(charSequence.toString().trim().length()>2)
                    getMedicin();
                else if(group.getCheckedRadioButtonId()==R.id.fav_rbtn){
                    getMedicin();
            }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void CalcMedAmount() {
        String txtInterval = txti_interval.getText().toString().trim();
        if (!txtInterval.isEmpty()) {
            try {
                float count_dosagevalue = 0;
                if (DosageSpinner.getSelectedItemPosition() != 0)
                    count_dosagevalue = Float.parseFloat(mGetMedDoseConstSelected.getDOSEVAUE());
                float countinterval = Float.parseFloat(txtInterval);
                float res = count_dosagevalue * countinterval;
                txti_TotalAmount.setText(String.valueOf(res));
            } catch (Exception e) {
                txti_TotalAmount.setText("");
            }
        }
        else
            txti_TotalAmount.setText("");
    }

    private void getDose() {
        Map<String, String> map = new HashMap<>();

        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        MyRequest.makeRquest(getContext(), Controller.GET_MED_DOSAGE_CONSTATNS_URL, map, new MyRequest.CallBack() {
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
                    mAdapterSpinnerDosage.setmListObject(getMedDoseConstArrayList);
                    if(mDocPharmacyDetails.getINPDOSECD()!=null)
                        for(int i=0;i<getMedDoseConstArrayList.size();i++)
                            if(mDocPharmacyDetails.getINPDOSECD().equals(((GetMedDoseConst)getMedDoseConstArrayList.get(i)).getDOSECODE()))
                                DosageSpinner.setSelection(i);
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

    private void getRoute() {
        Map<String, String> map = new HashMap<>();
        MyRequest.makeRquest(getContext(), Controller.GET_MED_ROUTE_CONSTATNS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    getMedRouteConstArrayList = gson.fromJson(mJSONObject.getString("ALL_DOSES"), new TypeToken<ArrayList<GetMedRouteConst>>() {}.getType());
                    GetMedRouteConst mGetMedRouteConst = new GetMedRouteConst();
                    mGetMedRouteConst.setDOSGNAME("اختر الجرعة ..");
                    getMedRouteConstArrayList.add(0,mGetMedRouteConst);
                    mAdapterSpinnerRoute.setmListObject(getMedRouteConstArrayList);
                    if(mDocPharmacyDetails.getINPDOSEGIVINGCD()!=null)
                        for(int i=0;i<getMedRouteConstArrayList.size();i++)
                            if(mDocPharmacyDetails.getINPDOSEGIVINGCD().equals(((GetMedRouteConst)getMedRouteConstArrayList.get(i)).getDOSGCODE()))
                                RouteSpinner.setSelection(i);
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

    public void getMedicin() {
        Map<String, String> map = new HashMap<>();
        boolean flag=group.getCheckedRadioButtonId()==R.id.fav_rbtn;
        if(flag){
            map.put("P_USER_ID",  (Controller.pref.getString("USER_ID", "")));
            map.put("P_MED_NAME", autoDrugname.getText().toString().trim());

        }else{
        map.put("P_NAME", autoDrugname.getText().toString().trim());
        map.put("P_PHARM_TYPE", group.getCheckedRadioButtonId() == R.id.internal_phram ? "0" :
                group.getCheckedRadioButtonId() == R.id.drug_phram ? "4" : "3");
        map.put("P_LOC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getLOC_CODE() + "");}
        Log.e("map", "getMedicin: " + map.toString());
        MyRequest.makeRquest(getContext(), flag?Controller.GET_USER_FAV_MED_URL:
                Controller.GET_MEDICINS_CONSTATNS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();

                    mListMedicine = gson.fromJson(mJSONObject.getString(flag?"USER_FAV_MED":"MED_CUR"),
                            new TypeToken<ArrayList<GetMedicineConst>>() {
                    }.getType());
                    mAdapterSpinnerMedicin = new AdapterSpinner(getContext(), 0, mListMedicine);
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
                Controller.view_error(error, getContext());
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnMedicinConfirm:
                Log.e("test", "ITEM=" + DosageSpinner.getSelectedItemPosition());
                if (mDocPharmacyDetails.getINPMEDCD() == null) {
                    Toast.makeText(getContext(), "الرجاء ادخال الدواء", Toast.LENGTH_SHORT).show();
                } else if (DosageSpinner.getSelectedItemPosition()==0) {
                    Toast.makeText(getContext(), "الرجاء إدخال التكرار", Toast.LENGTH_SHORT).show();
                } else if (RouteSpinner.getSelectedItemPosition() == 0)
                    Toast.makeText(getContext(), "الرجاء إدخال طريقة إعطاء الجرعة", Toast.LENGTH_SHORT).show();
                else if(txti_interval.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "الرجاء ادخال الجرعة", Toast.LENGTH_SHORT).show();
                else {
                    mDocPharmacyDetails.setdOSENAME(mGetMedDoseConstSelected.getDOSENAME());
                    mDocPharmacyDetails.setdOSGNAME(mGetMedRouteConstSelected.getDOSGNAME());
                    mDocPharmacyDetails.setiNPINTERVAL(txti_interval.getText().toString());
                    mDocPharmacyDetails.setiNPDOSECD(mGetMedDoseConstSelected.getDOSECODE());
                    mDocPharmacyDetails.setiNPDOSEGIVINGCD(mGetMedRouteConstSelected.getDOSGCODE());
                    mDocPharmacyDetails.setiNPWANTEDAMOUNT(txti_TotalAmount.getText().toString());
                    mDocPharmacyDetails.setiNPSTATUSCD("0");
                    mDocPharmacyDetails.setiNPNOTE(txti_notes.getText().toString());
                    mOnConfirmMedicin.confirm(mDocPharmacyDetails, add_update);
                    dismiss();
                }

                break;
            case R.id.btnMedicinCancel:
                // Toast.makeText(getContext(), ""+txti_notes.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.img_MedSearch:
//                getMedicin(autoDrugname.getText().toString().trim().isEmpty()?a);
                break;
        }
    }
}