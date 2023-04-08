package com.moh.hamadpulse;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.adapters.AdapterReportCorona;
import com.moh.hamadpulse.models.CardviewDataModel;
import com.moh.hamadpulse.models.Extra;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ActivitySummary extends AppCompatActivity {

    CardviewDataModel mCardviewDataModel;
    private TextView txtSummaryName;
    private TextView txtSummaryStatus;
    private TextView txtSummaryOccupation;
    private TextView txtSummaryAge;
    private TextView txtSummaryAddress;
    private TextView txtSummaryDoctorName;
    private RecyclerView rvReportCoronaG1;
    private RecyclerView rvReportCoronaG2;
    private RecyclerView rvReportCoronaPhysical;
    private RecyclerView rvReportCoronaSeverity;
    private RecyclerView rvReportCoronaModerate;
    private RecyclerView rvReportCoronaSevere;
    private RecyclerView rvReportCoronaARDS;
    private RecyclerView rvReportCoronaDisease;
    private RecyclerView rvReportCoronaCritical;
    LinearLayoutManager mLinearLayoutManager;
    TextView lblLabratory,txtLabratory;
    private TextView lblCXR;
    private TextView txtCXR;
    private TextView lblECG;
    private TextView txtECG;
    private TextView lblSummaryPCR;
    private TextView txtSummaryPCR;
    private TextView txtSummaryDate;
    private TextView lblSummaryDate;
    int SpanCnt;
    private AVLoadingIndicatorView imgLoading;
    private LinearLayout containerReport;
    AdapterReportCorona mAdapterReportCoronaG1,mAdapterReportCoronaG2,mAdapterReportPhysical,mAdapterReportSeverity,mAdapterReportModerate,mAdapterReportSevere,mAdapterReportCritical;
    AdapterReportCorona mAdapterReportCoronaARDS,mAdapterReportCoronaDisease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        containerReport = findViewById(R.id.containerReport);
        imgLoading = findViewById(R.id.imgLoading);
        if(getIntent().getSerializableExtra(Extra.EXTRA_PATIENT_INFO)!=null) {
            mCardviewDataModel = (CardviewDataModel) getIntent().getSerializableExtra(Extra.EXTRA_PATIENT_INFO);
        }

        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            SpanCnt = 3;
        else
            SpanCnt = 2;
        lblSummaryPCR = findViewById(R.id.lblSummaryPCR);
        lblSummaryDate = findViewById(R.id.lblSummaryDate);
        txtSummaryPCR = findViewById(R.id.txtSummaryPCR);
        txtSummaryDate = findViewById(R.id.txtSummaryDate);
        lblCXR = findViewById(R.id.lblCXR);
        txtCXR = findViewById(R.id.txtCXR);
        lblECG = findViewById(R.id.lblECG);
        txtECG = findViewById(R.id.txtECG);
        lblLabratory = findViewById(R.id.lblLabratory);
        txtLabratory = findViewById(R.id.txtLabratory);
        rvReportCoronaG1 = findViewById(R.id.rvReportCoronaG1);
        rvReportCoronaG2 = findViewById(R.id.rvReportCoronaG2);
        rvReportCoronaPhysical = findViewById(R.id.rvReportCoronaPhysical);
        rvReportCoronaSeverity = findViewById(R.id.rvReportCoronaSeverity);
        rvReportCoronaModerate = findViewById(R.id.rvReportCoronaModerate);
        rvReportCoronaSevere = findViewById(R.id.rvReportCoronaSevere);
        rvReportCoronaARDS = findViewById(R.id.rvReportCoronaARDS);
        rvReportCoronaCritical = findViewById(R.id.rvReportCoronaCritical);
        rvReportCoronaDisease = findViewById(R.id.rvReportCoronaDisease);

        mAdapterReportCoronaG1 = new AdapterReportCorona(new ArrayList<>(),false);
        rvReportCoronaG1.setLayoutManager(new GridLayoutManager(this,1));
        rvReportCoronaG1.setAdapter(mAdapterReportCoronaG1);


        mAdapterReportPhysical = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaPhysical.setLayoutManager(new GridLayoutManager(this,SpanCnt));
        rvReportCoronaPhysical.setAdapter(mAdapterReportPhysical);

        mAdapterReportSeverity = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaSeverity.setLayoutManager(new GridLayoutManager(this,SpanCnt));
        rvReportCoronaSeverity.setAdapter(mAdapterReportSeverity);

        mAdapterReportModerate = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaModerate.setLayoutManager(new GridLayoutManager(this,SpanCnt));
        rvReportCoronaModerate.setAdapter(mAdapterReportModerate);

        mAdapterReportSevere = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaSevere.setLayoutManager(new GridLayoutManager(this,1));
        rvReportCoronaSevere.setAdapter(mAdapterReportSevere);

        mAdapterReportCritical = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaCritical.setLayoutManager(new GridLayoutManager(this,SpanCnt));
        rvReportCoronaCritical.setAdapter(mAdapterReportCritical);

        mAdapterReportCoronaARDS = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaARDS.setLayoutManager(new GridLayoutManager(this,1));
        rvReportCoronaARDS.setAdapter(mAdapterReportCoronaARDS);

        mAdapterReportCoronaDisease = new AdapterReportCorona(new ArrayList<>(),true);
        rvReportCoronaDisease.setLayoutManager(new GridLayoutManager(this,SpanCnt));
        rvReportCoronaDisease.setAdapter(mAdapterReportCoronaDisease);

        mAdapterReportCoronaG2 = new AdapterReportCorona(new ArrayList<>(),false);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvReportCoronaG2.setLayoutManager(mLinearLayoutManager);
        rvReportCoronaG2.setAdapter(mAdapterReportCoronaG2);

        txtSummaryName = findViewById(R.id.txtSummaryName);
        txtSummaryStatus = findViewById(R.id.txtSummaryStatus);
        txtSummaryOccupation = findViewById(R.id.txtSummaryOccupation);
        txtSummaryAge = findViewById(R.id.txtSummaryAge);
        txtSummaryAddress = findViewById(R.id.txtSummaryAddress);
        txtSummaryDoctorName = findViewById(R.id.txtSummaryDoctorName);
        txtSummaryName.setText(mCardviewDataModel.getPatname());
        txtSummaryStatus.setText(mCardviewDataModel.getSEX_NAME_AR());
        imgLoading.setVisibility(View.VISIBLE);

        Map<String, String> mMap = new HashMap<>();
        mMap.put("P_COV_ID", mCardviewDataModel.getPtmrpid() + "");
//        mMap.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        //mMap.put("P_COV_ID","901915835");
        MyRequest.makeRquest(this, Controller.GET_PATIENT_COVID, mMap, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {

                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    if (!mJSONObject.getString("result").equals("0")) {
                        Gson mGson = new Gson();
                        test mtest = mGson.fromJson(mJSONObject.getString("data"), test.class);
                        txtSummaryOccupation.setText(mtest.getOCCUPATION().getValue());
                        mAdapterReportCoronaG2.setmListGROUP(mtest.getMY_GROUP2());
                        mAdapterReportCoronaG2.notifyDataSetChanged();
                        mAdapterReportCoronaG1.setmListGROUP(mtest.getMY_GROUP1());
                        mAdapterReportCoronaG1.notifyDataSetChanged();
                        mAdapterReportPhysical.setmListGROUP(mtest.getPHYSICAL_EXAMINATION());
                        mAdapterReportPhysical.notifyDataSetChanged();
                        mAdapterReportSeverity.setmListGROUP(mtest.getSEVERITY());
                        mAdapterReportSeverity.notifyDataSetChanged();
                        mAdapterReportModerate.setmListGROUP(mtest.getMODERATE_SYMPTOM());
                        mAdapterReportModerate.notifyDataSetChanged();
                        mAdapterReportSevere.setmListGROUP(mtest.getSEVERE());
                        mAdapterReportSevere.notifyDataSetChanged();
                        mAdapterReportCritical.setmListGROUP(mtest.getCRITICAL());
                        mAdapterReportCritical.notifyDataSetChanged();
                        mAdapterReportCoronaARDS.setmListGROUP(mtest.getARDS());
                        mAdapterReportCoronaARDS.notifyDataSetChanged();
                        mAdapterReportCoronaDisease.setmListGROUP(mtest.getCRITICAL_DISEASE());
                        mAdapterReportCoronaDisease.notifyDataSetChanged();
                        txtLabratory.setText(mtest.getCOV_CH_LABORATORY_FINDINGS().getValue());
                        lblLabratory.setText(mtest.getCOV_CH_LABORATORY_FINDINGS().getLbl());
                        txtCXR.setText(mtest.getCOV_CH_CXR_C_T_FINDINGS().getValue());
                        lblCXR.setText(mtest.getCOV_CH_CXR_C_T_FINDINGS().getLbl());
                        txtECG.setText(mtest.getCOV_CH_ECG_FINDINGS().getValue());
                        lblECG.setText(mtest.getCOV_CH_ECG_FINDINGS().getLbl());
                        txtSummaryPCR.setText(mtest.getCOV_CH_PCR_RESULT().getValue().equals("1") ? "Positive" : "Negative");
                        lblSummaryPCR.setText(mtest.getCOV_CH_PCR_RESULT().getLbl());
                        lblSummaryDate.setText(mtest.getCOV_CH_PCR_RESULT_DATE().getLbl());
                        txtSummaryDate.setText(mtest.getCOV_CH_PCR_RESULT_DATE().getValue());
                        txtSummaryDoctorName.setText(mtest.getUSER_NAME().getValue());
                        txtSummaryAge.setText(mtest.getEMP_AGE().getValue());
                        txtSummaryAddress.setText(mtest.getV_ADDRESS().getValue());
                        containerReport.setVisibility(View.VISIBLE);
                    }
                    else
                        Toast.makeText(ActivitySummary.this, "لا توجد بيانات مدخلة", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivitySummary.this, "Api Error", Toast.LENGTH_SHORT).show();
                    imgLoading.setVisibility(View.GONE);
                }
                imgLoading.setVisibility(View.GONE);


            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getBaseContext());
            }
        });
    }


}