package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ReportCurModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FragmentReportDialog extends DialogFragment {
    ImageButton imgBTN_Close;
    TextView txt_title, txt_HospitalName, txt_OrganName, txt_OrganTechnique, txt_Result;
    ReportCurModel model = new ReportCurModel();
    String code, year;

    public FragmentReportDialog() {
        // Required empty public constructor
    }

    public FragmentReportDialog(String code, String year) {
        this.code = code;
        this.year = year;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_title = view.findViewById(R.id.txt_title);
        txt_HospitalName = view.findViewById(R.id.txt_HospitalName);
        txt_OrganName = view.findViewById(R.id.txt_OrganName);
        txt_OrganTechnique = view.findViewById(R.id.txt_OrganTechnique);
        txt_Result = view.findViewById(R.id.txt_Result);
        imgBTN_Close = view.findViewById(R.id.imgBTN_Close);
        imgBTN_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getReportResult();

    }

    private void getReportResult() {
        Map<String, String> map = new HashMap<>();
//        map.put("P_ORDER_CODE","82161");
        map.put("P_ORDER_CODE", code);
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_ORDER_YEAR",2016+"");
        map.put("P_ORDER_YEAR", year);
        map.put("TRANS_SCREEN_CD_IN", 38 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
//        map.put("TRANS_USER_CODE_IN", 801766338+"");
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "RADIOLOGY_Reports");
        MyRequest.makeRquest(getContext(), Controller.GET_RAD_REPORT_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("REPORT_CUR");
                            Gson gson = new Gson();
                            model = gson.fromJson(jsonObject.toString(), ReportCurModel.class);
                            fillFields();

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                    }
                });
    }

    private void fillFields() {
        txt_title.setText(model.getTitle());
        txt_HospitalName.setText(model.getHosNameEn());
        txt_OrganName.setText(model.getRadDetails().get(0).getOrganDNameAr());
        txt_OrganTechnique.setText(model.getRadDetails().get(0).getOrganDTechnique());
        txt_Result.setText(model.getRadDetails().get(0).getOrderdResult());

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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}