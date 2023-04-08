package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.Adapter_Pre_Post_Chemothropy;
import com.moh.hamadpulse.models.ProtocolExecutionModel;
import com.moh.hamadpulse.models.TumerPostTxt_Model;
import com.moh.hamadpulse.models.TumerRepModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProtocolExecutionFragment extends Fragment {
    TextView txt_Reason, txt_Protocol, txt_Note, txt_Indication, txtPremedications, txtPostmedications;
    RecyclerView Chemothropy_RV, Post_Med_RV, Pre_Med_RV;
    Spinner PremedicationsStatus_SP, PostmedicationsStatus_SP, PatientComplain_SP;
    Button btn_save;
    ProtocolExecutionModel model;
    String order_id;
    boolean flag_pre = false, flag_post = false;

    public ProtocolExecutionFragment() {
        // Required empty public constructor
    }

    public ProtocolExecutionFragment(String order_id) {
        this.order_id = order_id;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_protocol_exection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_Reason = view.findViewById(R.id.txt_Reason);
        txt_Protocol = view.findViewById(R.id.txt_Protocol);
        txt_Indication = view.findViewById(R.id.txt_Indication);
        txtPremedications = view.findViewById(R.id.txtPremedications);
        txtPostmedications = view.findViewById(R.id.txtPostmedications);
        Chemothropy_RV = view.findViewById(R.id.RV_Chemothropy);
        Pre_Med_RV = view.findViewById(R.id.PremedicationRv);
        Post_Med_RV = view.findViewById(R.id.PostmedicationRV);
        PremedicationsStatus_SP = view.findViewById(R.id.PremedicationsStatus_SP);
        PostmedicationsStatus_SP = view.findViewById(R.id.PostmedicationsStatus_SP);
        PatientComplain_SP = view.findViewById(R.id.PatientComplain_SP);
        txt_Note = view.findViewById(R.id.txt_Note);
        btn_save = view.findViewById(R.id.btn_save);
        getAllData();

        if(Controller.pref.getString(USER_TYPE, "").equals("5")){
            PremedicationsStatus_SP.setEnabled(false);
            PostmedicationsStatus_SP.setEnabled(false);

        }

        //     Toast.makeText(getContext(), order_id, Toast.LENGTH_LONG).show();


        Handler aHandler = new Handler();
        aHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PremedicationsStatus_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
//                        Toast.makeText(getContext(), "Please select status"
//                                , Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                            case 2:
                            case 3:
                                if (flag_pre) {
                                    FragmentTransaction ft = ((AppCompatActivity) getContext()).
                                            getSupportFragmentManager().beginTransaction();
                                    new StutasMedicationConfirmDialog(model.getTumerRep().get(0), 1, i)
                                            .show(ft, "StutasMedicationConfirmDialog");

                                }
                            default:
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                PostmedicationsStatus_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
                                break;
                            case 1:
                            case 2:
                            case 3:
                                if (flag_post) {
                                    FragmentTransaction ft = ((AppCompatActivity) getContext()).
                                            getSupportFragmentManager().beginTransaction();
                                    new StutasMedicationConfirmDialog
                                            (model.getTumerRep().get(0), 3, i).
                                            show(ft, "StutasMedicationConfirmDialog");
                                }
                                break;
                            default:
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }, 500);


    }

    private void getAllData() {
        Map<String, String> map = new HashMap<>();
        map.put("P_PATIENT_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_ORDER_CD", order_id);
        map.put("TRANS_SCREEN_CD_IN", 40 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity())
                .getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN",
                (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "PROTOCOL_EXC");
        Log.d("map", map.toString());
        MyRequest.makeRquest(getContext(),
                Controller.GET_P_PROTOCOL_REPORT_PR_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("RESULT");

//                            Log.e("KOKO", jsonArray + "");
                            //////////////////
                            Gson gson = new Gson();
//                            Type type = new TypeToken<ArrayList<GetRadServicesConst>>() {
//                            }.getType();

                            model = gson.fromJson(jsonObject.toString(),
                                    ProtocolExecutionModel.class);
                            fillData(model);


                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT).show();


                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
//                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void fillData(ProtocolExecutionModel model) {
        this.model = model;
        TumerRepModel tumerRepModel = model.getTumerRep().get(0);
//        if (tumerRepModel.getReason() != null)
//            txt_Reason.setText(tumerRepModel.getReason().toString());
//        txt_Protocol.setText(tumerRepModel.getProtocolName());
//        if (tumerRepModel.getIndication() != null)
//            txt_Indication.setText(tumerRepModel.getIndication().toString());
        Adapter_Pre_Post_Chemothropy adapter = new Adapter_Pre_Post_Chemothropy(model.getChemothropy(), null,
                null, tumerRepModel);
        String premedication = tumerRepModel.getPremedication();
        if ((premedication == null) || premedication.trim().equals("")) {
            PremedicationsStatus_SP.setSelected(false);
        } else {
            txtPremedications.setText(premedication);
        }

        String postmedication = model.getTumerPostTxt().get(0).getPostmedication();
        if ((postmedication == null) || postmedication.trim().equals("")) {
            PostmedicationsStatus_SP.setSelected(false);
        } else {
            txtPostmedications.setText(postmedication);
        }
        Chemothropy_RV.setAdapter(adapter);
        Chemothropy_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter_Pre_Post_Chemothropy adapterPreMedication =
                new Adapter_Pre_Post_Chemothropy(null, model.getPreCur(),
                        null, tumerRepModel);
        Pre_Med_RV.setAdapter(adapterPreMedication);
        Pre_Med_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter_Pre_Post_Chemothropy adapterPostMedication =
                new Adapter_Pre_Post_Chemothropy(null, null,
                        model.getPostCur(), tumerRepModel);
        Post_Med_RV.setAdapter(adapterPostMedication);
        Post_Med_RV.setLayoutManager(new LinearLayoutManager(getContext()));

//        PremedicationsStatus_SP.setSelection(tumerRepModel.get);
        TumerRepModel tumerModel = model.getTumerRep().get(0);
        TumerPostTxt_Model tumerPostTxt_model = model.getTumerPostTxt().get(0);

        PremedicationsStatus_SP.setSelection(Integer.parseInt
                (tumerModel.getStatus() == null ? "0" : tumerModel.getStatus()));

        PostmedicationsStatus_SP.setSelection(Integer.parseInt
                (tumerPostTxt_model.getStatus() == null ? "0" : tumerPostTxt_model.getStatus()));
        if (PremedicationsStatus_SP.getSelectedItemPosition() == Integer.parseInt
                (tumerModel.getStatus() == null ? "0" : tumerModel.getStatus()))
            flag_pre = true;
//        if(PostmedicationsStatus_SP.getSelectedItemPosition()==Integer.parseInt
//                (tumerPostTxt_model.getStatus() == null ? "0" : tumerPostTxt_model.getStatus()))
        flag_post = true;

    }

    @Override
    public void onResume() {
        super.onResume();
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}