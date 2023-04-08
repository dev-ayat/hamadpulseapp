package com.moh.hamadpulse.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.moh.hamadpulse.adapters.NewProtocolDataAdapter;
import com.moh.hamadpulse.adapters.ProtocolLabResultAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.IndicationsResultModel;
import com.moh.hamadpulse.models.NewChemotherapyModel;
import com.moh.hamadpulse.models.P_VitalSignsModel;
import com.moh.hamadpulse.models.PostDrugsModel;
import com.moh.hamadpulse.models.PreDrugsModel;
import com.moh.hamadpulse.models.PrePostDrugTextModel;
import com.moh.hamadpulse.models.ProtocolDataModel;
import com.moh.hamadpulse.models.ProtocolLabResultModel;
import com.moh.hamadpulse.models.ProtocolVitalSignsModel;
import com.moh.hamadpulse.models.ProtocolsIndicationsModel;
import com.moh.hamadpulse.models.ProtocolsResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AddNewProtocol extends Fragment {
    EditText ET_Height, ET_Weight, ET_BSA, pre_ED, post_ED, ET_Reason;
    RecyclerView Lab_Result_RV, RV_Pre, RV_Chemo, RV_Post;
    Spinner Protocol_SP, Indication_SP, Cycle_SP, Day_SP, Repeat_SP;
    MaterialButton btn_save_protocol, btn_add_new_pre, btn_add_new_chemo, add_add_new_post;
    ArrayList<ProtocolsResultModel> allProtocolsModels;
    ArrayList<IndicationsResultModel> indicationsResultModels;
    ProtocolLabResultModel protocolLabResultModel;
    ProtocolVitalSignsModel model;
    NewProtocolDataAdapter chemoAdapter;
    NewProtocolDataAdapter preAdapter;
    NewProtocolDataAdapter postAdapter;
    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    public AddNewProtocol() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((ActivityPatient) getActivity()).show_hide_details(View.GONE);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_protocol, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ET_Height = view.findViewById(R.id.ET_Height);
        ET_Weight = view.findViewById(R.id.ET_Weight);
        ET_BSA = view.findViewById(R.id.ET_BSA);
        ET_Reason = view.findViewById(R.id.ET_Reason);
        pre_ED = view.findViewById(R.id.pre_ED);
        post_ED = view.findViewById(R.id.post_ED);
        Lab_Result_RV = view.findViewById(R.id.Lab_Result_RV);
        RV_Pre = view.findViewById(R.id.RV_Pre);
        RV_Post = view.findViewById(R.id.RV_Post);
        RV_Chemo = view.findViewById(R.id.RV_Chemo);
        Protocol_SP = view.findViewById(R.id.Protocol_SP);
        Indication_SP = view.findViewById(R.id.Indication_SP);
        Cycle_SP = view.findViewById(R.id.Cycle_SP);
        Day_SP = view.findViewById(R.id.Day_SP);
        Repeat_SP = view.findViewById(R.id.Repeat_SP);
        btn_save_protocol = view.findViewById(R.id.btn_save_protocol);
        btn_add_new_pre = view.findViewById(R.id.btn_add_new_pre);
        btn_add_new_chemo = view.findViewById(R.id.btn_add_new_chemo);
        add_add_new_post = view.findViewById(R.id.add_add_new_post);
        btn_save_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewProtocol();
            }
        });
        btn_add_new_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Protocol_SP.getSelectedItemPosition() != 0) {
                    PreDrugsModel preDrugsModel = new PreDrugsModel();
                    preDrugsModel.setType("1");
                    preAdapter.getPreDrugsModels().add(preDrugsModel);
                    preAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Select Protocol First", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_add_new_chemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Protocol_SP.getSelectedItemPosition() != 0) {
                    NewChemotherapyModel newChemotherapyModel = new NewChemotherapyModel();
                    newChemotherapyModel.setType("1");
                    chemoAdapter.getChemotherapyModels().add(newChemotherapyModel);
                    chemoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Select Protocol First", Toast.LENGTH_LONG).show();
                }
            }
        });
        add_add_new_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Protocol_SP.getSelectedItemPosition() != 0) {
                    PostDrugsModel postDrugsModel = new PostDrugsModel();
                    postDrugsModel.setType("1");
                    postAdapter.getPostDrugModels().add(postDrugsModel);
                    postAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Select Protocol First", Toast.LENGTH_LONG).show();
                }
            }
        });
//      set entries for cycle and day spinners
//      ***********************************************
        String cycle_values[] = new String[26];
        for (int i = 0; i < 26; i++) {
            cycle_values[i] = i + "";
        }
        ArrayAdapter<String> cycles_adapter = new ArrayAdapter<>(getContext()
                , android.R.layout.simple_spinner_item, cycle_values);
        cycles_adapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        Cycle_SP.setAdapter(cycles_adapter);
        String days_values[] = new String[32];
        for (int i = 0; i < 32; i++) {
            days_values[i] = i + "";
        }
        ArrayAdapter<String> days_adapter = new ArrayAdapter<>(getContext()
                , android.R.layout.simple_spinner_item, days_values);
        days_adapter.setDropDownViewResource(android.R.layout
                .select_dialog_item);
        Day_SP.setAdapter(days_adapter);
//      ****************************************
        ET_Height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty() && !ET_Weight.getText().toString().trim().isEmpty()) {
                    calculate_BSA(charSequence.toString().trim(), ET_Weight.getText().toString().trim());
                } else {
                    ET_BSA.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ET_Weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty() && !ET_Height.getText().toString().trim().isEmpty()) {
                    calculate_BSA(charSequence.toString().trim(), ET_Height.getText().toString().trim());
                } else {
                    ET_BSA.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // fill Pre and Post Text depend on protocol
        Protocol_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    fillDate(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getVitalSigns();
        getLabResult();
        getProtocolConstance();
    }

    private void getVitalSigns() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_PATRIC_CD", 191211 + "");

        Log.e("map", map.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_PROTOCOL_VSIGN_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("PROTOCAL_VS");
                            Gson gson = new Gson();
                            model = gson.fromJson(jsonObject.toString(),
                                    ProtocolVitalSignsModel.class);
                            ArrayList<P_VitalSignsModel> pVitalSignsModels = model.getpVitalSignsModels();
                            ET_Height.setText(pVitalSignsModels.size() == 0 ? "" : pVitalSignsModels.get(0).getHeight());
                            ET_Weight.setText(pVitalSignsModels.size() == 0 ? "" : pVitalSignsModels.get(0).getWeight());
                            mInterfacePatient.showLoading(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mInterfacePatient.showLoading(false);
                            Log.e("api", e.getMessage());
//                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            showLoading(false);

                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                        mInterfacePatient.showLoading(false);
                    }
                });
    }

    private void fillDate(int i) {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PROTOCOL_ID", allProtocolsModels.get(i - 1).getId());
        Log.e("map", map.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_PROTOCOL_DATA_PR_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("PROTOCAL_DATA");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<ProtocolDataModel>>() {
                            }.getType();
                            ArrayList<ProtocolDataModel> models = gson.fromJson(jsonArray.toString(),
                                    type);
                            ProtocolDataModel model = models.get(0);
                            ArrayList<PrePostDrugTextModel> drugTextModels = model.getCurPrePost();
                            Log.e("prepost", drugTextModels.size() + "");
                            for (PrePostDrugTextModel item : drugTextModels) {
                                if (item.getPSerial().equals("1"))
                                    pre_ED.setText(item.getPDetails());
                                else
                                    post_ED.setText(item.getPDetails());
                            }
                            setChemoDrugs(model.getCurChemothorpy());
                            setPreDrugs(model.getPreDrugsModels());
                            setPostDrugs(model.getPostDrugsModels());
                            mInterfacePatient.showLoading(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("api", e.getMessage());
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

    private void setPostDrugs(ArrayList<PostDrugsModel> list) {
        postAdapter = new NewProtocolDataAdapter(null, null, list);
        RV_Post.setAdapter(postAdapter);
        RV_Post.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setPreDrugs(ArrayList<PreDrugsModel> list) {
        preAdapter = new NewProtocolDataAdapter(null, list, null);
        RV_Pre.setAdapter(preAdapter);
        RV_Pre.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setChemoDrugs(ArrayList<NewChemotherapyModel> list) {
        chemoAdapter = new NewProtocolDataAdapter(list, null, null);
        RV_Chemo.setAdapter(chemoAdapter);
        RV_Chemo.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getProtocolConstance() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        MyRequest.makeRquest(getContext(), Controller.GET_PROTOCOL_CONSTANT_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("CONSTANTS");
                            Gson gson = new Gson();
                            ProtocolsIndicationsModel model = gson.fromJson(jsonObject.toString(),
                                    ProtocolsIndicationsModel.class);
                            ArrayList<String> result = new ArrayList<>();
                            ArrayList<String> indications = new ArrayList<>();
                            allProtocolsModels = model.getAllProtocols().getResult();
                            indicationsResultModels = model.getIndication().getResult();
                            for (ProtocolsResultModel item : allProtocolsModels) {
                                result.add(item.getProtocolName());
                            }
                            for (IndicationsResultModel item : indicationsResultModels) {
                                indications.add(item.getIndNameEn());
                            }
                            result.add(0, "Select Protocol");
                            indications.add(0, "Select Indication");
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                    (getContext(), android.R.layout.simple_spinner_item, result); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                                    .simple_spinner_dropdown_item);
                            Protocol_SP.setAdapter(spinnerArrayAdapter);

                            ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<String>
                                    (getContext(), android.R.layout.simple_spinner_item, indications); //selected item will look like a spinner set from XML
                            ArrayAdapter.setDropDownViewResource(android.R.layout
                                    .simple_spinner_dropdown_item);
                            Indication_SP.setAdapter(ArrayAdapter);
                            mInterfacePatient.showLoading(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("api", e.getMessage());
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

    private void getLabResult() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

//        map.put("P_PATRIC_CD",  "191211");
        MyRequest.makeRquest(getContext(), Controller.GET_PROTOCOL_LAB_RESULT_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("LAB_RESULT");
                            Gson gson = new Gson();
                            protocolLabResultModel = gson.fromJson(jsonObject.toString(),
                                    ProtocolLabResultModel.class);
                            ArrayList<String> tests_name = new ArrayList<String>(Arrays.asList
                                    (new String[]{"CBC", "KIDNEY FUNCTION", "LFT"}));
                            ProtocolLabResultAdapter adapter = new ProtocolLabResultAdapter(tests_name, protocolLabResultModel);
                            Lab_Result_RV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                            Lab_Result_RV.setAdapter(adapter);
                            mInterfacePatient.showLoading(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            mInterfacePatient.showLoading(false);
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                        mInterfacePatient.showLoading(false);
                    }
                });
    }

    private void calculate_BSA(String h, String w) {
        double ht = Double.parseDouble(h);
        double wt = Double.parseDouble(w);
        ET_BSA.setText((Math.round((Math.sqrt(ht * wt / 3600) * 10.0)) / 10.0) + "");
    }

    private boolean validation() {
        if (ET_Height.getText().toString().isEmpty()
                || ET_Weight.getText().toString().isEmpty()
                || (pre_ED.getText().toString().isEmpty() && preAdapter.getItemCount() == 0)
                || (chemoAdapter.getItemCount() == 0) ||
                (post_ED.getText().toString().isEmpty()
                        && postAdapter.getItemCount() == 0)
                || Protocol_SP.getSelectedItemPosition() == 0
                || Indication_SP.getSelectedItemPosition() == 0 ||
                Repeat_SP.getSelectedItemPosition() == 0
        ) {
            return false;
        } else
            return true;
    }

    private void AddNewProtocol() {
        if (validation()) {
            mInterfacePatient.showLoading(true);
            Map<String, String> map = new HashMap<>();
            map.put("V_PATIENT_CD", ((ActivityPatient) getActivity())
                    .getmCardviewDataModel().getPatid() + "");
            map.put("PROTOCOL_ID", allProtocolsModels.get(Protocol_SP.getSelectedItemPosition() - 1).getId());
            map.put("USER_ID", (Controller.pref.getString("USER_ID", "")));
            map.put("TUMOR_ID", model.getpTumerModels().get(0).getId());
            map.put("V_CYCLE_NO", (String) Cycle_SP.getSelectedItem());
            map.put("V_REPEAT", Repeat_SP.getSelectedItemPosition() + "");
            map.put("V_HEIGHT", ET_Height.getText().toString());
            map.put("V_WEIGHT", ET_Weight.getText().toString());
            map.put("V_WBC", protocolLabResultModel.getWbcVal() == null ? "0" : protocolLabResultModel.getWbcVal());
            map.put("V_HBG", protocolLabResultModel.getHbVal() == null ? "0" : protocolLabResultModel.getHbVal());
            map.put("V_GRAN", protocolLabResultModel.getGranVal() == null ? "0" : protocolLabResultModel.getGranVal());
            map.put("V_PLT", protocolLabResultModel.getPltVal() == null ? "0" : protocolLabResultModel.getPltVal());
            map.put("V_SERUM", protocolLabResultModel.getSerumCreatinineVal() == null ? "0" : protocolLabResultModel.getSerumCreatinineVal());
            map.put("V_BILIRUBIN", protocolLabResultModel.getBilirubinVal() == null ? "0" : protocolLabResultModel.getBilirubinVal());
            map.put("V_ALP", protocolLabResultModel.getAlpVal() == null ? "0" : protocolLabResultModel.getAlpVal());
            map.put("V_ALT", protocolLabResultModel.getAltVal() == null ? "0" : protocolLabResultModel.getAltVal());
            map.put("V_AST", protocolLabResultModel.getAstVal() == null ? "0" : protocolLabResultModel.getAstVal());
            map.put("V_INDICATION", Indication_SP.getSelectedItemPosition() + "");
            map.put("V_REASON", ET_Reason.getText().toString());
            map.put("V_DAY", (String) Day_SP.getSelectedItem());
            map.put("V_VISITED_ID", ((ActivityPatient) getActivity())
                    .getmCardviewDataModel().getAdmcd() + "");
            map.put("V_PREMEDICATION", pre_ED.getText().toString());
            map.put("V_POSTMEDICATION", post_ED.getText().toString());
            map.put("P_ORDER_DEP_CD", "3");
            map.put("P_ORDER_LOC_CD", ((ActivityPatient) getActivity())
                    .getmCardviewDataModel().getLOC_CODE());
            map.put("P_ORDER_TYPE_CD", "3");
//              map.put("V_TUMORS_ORDER_NO","");
            if (chemoAdapter.getChemotherapyModels() != null) {
                ArrayList<NewChemotherapyModel> models = chemoAdapter.getChemotherapyModels();
                int size = models.size();
                for (int i = 0; i < size; i++) {
                    NewChemotherapyModel model = models.get(i);
                    map.put("items[" + i + "][V_DRUG_NO]", model.getDrugNo());
                    map.put("items[" + i + "][V_PROTOCOL_ID]", allProtocolsModels.get(Protocol_SP.getSelectedItemPosition() - 1).getId());
                    map.put("items[" + i + "][V_DRUG]", model.getDrug());
                    map.put("items[" + i + "][V_ITEM_CODE]", model.getItemCode());
                    map.put("items[" + i + "][V_DOSING_MOD]", 100 + "");
                    map.put("items[" + i + "][V_DOSING]", model.getDosing());
                    map.put("items[" + i + "][V_FINAL_DOSE]", model.getFinalDose() == null ? "" : model.getFinalDose());
                    map.put("items[" + i + "][V_ROUTE]", model.getRoute());
                    map.put("items[" + i + "][V_FREQUENCY]", model.getFrequency());
                    map.put("items[" + i + "][V_SPECIAL_INSTRUCTIONS]", model.getSpecialInstructions() == null ? "" : model.getSpecialInstructions());
                    map.put("items[" + i + "][V_DOSING_UNIT]", model.getDosingUnit());
                }
            }
            if (preAdapter.getPreDrugsModels() != null) {
                ArrayList<PreDrugsModel> models = preAdapter.getPreDrugsModels();
                int size = models.size();
                for (int i = 0; i < size; i++) {
                    PreDrugsModel model = models.get(i);
                    map.put("pre_items[" + i + "][VR_DRUG_NO]", model.getDrugNo());
                    map.put("pre_items[" + i + "][VR_PROTOCOL_ID]", allProtocolsModels.get(Protocol_SP.getSelectedItemPosition() - 1).getId());
                    map.put("pre_items[" + i + "][VR_DRUG]", model.getDrug());
                    map.put("pre_items[" + i + "][VR_ITEM_CODE]", model.getItemCode());
                    map.put("pre_items[" + i + "][VR_DOSING_MOD]", 100 + "");
                    map.put("pre_items[" + i + "][VR_DOSING]", model.getDosing());
                    map.put("pre_items[" + i + "][VR_FINAL_DOSE]", model.getFinalDose() == null
                            ? "" : model.getFinalDose());
                    map.put("pre_items[" + i + "][VR_ROUTE]", model.getRoute());
                    map.put("pre_items[" + i + "][VR_FREQUENCY]", model.getFrequency());
                    map.put("pre_items[" + i + "][VR_SPECIAL_INSTRUCTIONS]", model.getSpecialInstructions() == null ? "" : model.getSpecialInstructions());
                    map.put("pre_items[" + i + "][VR_DOSING_UNIT]", model.getDosingUnit());
                }
            }
            Log.e("isNull", (postAdapter.getPreDrugsModels() == null) + "");
            if (postAdapter.getPostDrugModels() != null) {
                ArrayList<PostDrugsModel> models = postAdapter.getPostDrugModels();
                int size = models.size();
                for (int i = 0; i < size; i++) {
                    PostDrugsModel model = models.get(i);
                    map.put("post_items[" + i + "][VO_DRUG_NO]", model.getDrugNo());
                    map.put("post_items[" + i + "][VO_PROTOCOL_ID]", allProtocolsModels.get(Protocol_SP.getSelectedItemPosition() - 1).getId());
                    map.put("post_items[" + i + "][VO_DRUG]", model.getDrug());
                    map.put("post_items[" + i + "][VO_ITEM_CODE]", model.getItemCode());
                    map.put("post_items[" + i + "][VO_DOSING_MOD]", 100 + "");
                    map.put("post_items[" + i + "][VO_DOSING]", model.getDosing());
                    map.put("post_items[" + i + "][VO_FINAL_DOSE]", model.getFinalDose() == null ? "" : model.getFinalDose());
                    map.put("post_items[" + i + "][VO_ROUTE]", model.getRoute());
                    map.put("post_items[" + i + "][VO_FREQUENCY]", model.getFrequency());
                    map.put("post_items[" + i + "][VO_SPECIAL_INSTRUCTIONS]", model.getSpecialInstructions() == null ? "" : model.getSpecialInstructions());
                    map.put("post_items[" + i + "][VO_DOSING_UNIT]", model.getDosingUnit());
                }
            }
            map.put("TRANS_SCREEN_CD_IN", 43 + "");
            map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
            map.put("TRANS_ACTION_CD_IN", "1");
            map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                    getActivity()).getmCardviewDataModel().getPatid() + "");
            map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
            map.put("TRANS_DESCRIPTION_IN", "Add New Protocol");
            Log.e("map", map.toString());
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.SAVE_CHEMOTHROPY_PROTOCOL_PR_URL
                    , map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("tumer_response", response.toString());
                    try {
                        int res = response.getInt("P_RESULT");
                        if (res == 1) {
                            Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                        } else {
                            Toast.makeText(getContext(), "لم تتم الإضافة",
                                    Toast.LENGTH_SHORT).show();
                        }
                        mInterfacePatient.showLoading(false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("json", "ERROR");
                        mInterfacePatient.showLoading(false);
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
            Toast.makeText(getContext(), "You must " +
                    "fill all field", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ActivityPatient) getActivity()).show_hide_details(View.VISIBLE);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
