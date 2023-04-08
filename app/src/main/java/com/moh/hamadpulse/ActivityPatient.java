package com.moh.hamadpulse;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import static java.time.temporal.ChronoUnit.DAYS;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterPatientServices;
import com.moh.hamadpulse.fragment.AddDoctorNurseNoteFragment;
import com.moh.hamadpulse.fragment.AddNewProtocol;
import com.moh.hamadpulse.fragment.AddRadiologyOrderFragment;
import com.moh.hamadpulse.fragment.AddTreatmentPlanFragment;
import com.moh.hamadpulse.fragment.AddVentelationFragment;
import com.moh.hamadpulse.fragment.AdmissionDashboradFragment;
import com.moh.hamadpulse.fragment.AdmissionHistoryFragment;
import com.moh.hamadpulse.fragment.AllargiesFragment;
import com.moh.hamadpulse.fragment.ArchiveServicesFragment;
import com.moh.hamadpulse.fragment.BloodTransferFragment;
import com.moh.hamadpulse.fragment.DischargeFragment;
import com.moh.hamadpulse.fragment.DoctorNurseNoteFragment;
import com.moh.hamadpulse.fragment.EfilePhotoCopingFragment;
import com.moh.hamadpulse.fragment.Efile_Fragment;
import com.moh.hamadpulse.fragment.Fragment_View_Lab_Reports;
import com.moh.hamadpulse.fragment.LabFragment;
import com.moh.hamadpulse.fragment.Lbres_Fragment;
import com.moh.hamadpulse.fragment.NurseServicesFragment;
import com.moh.hamadpulse.fragment.ProtocolFragment;
import com.moh.hamadpulse.fragment.RadFragment;
import com.moh.hamadpulse.fragment.RadiologyFragment;
import com.moh.hamadpulse.fragment.TreatmentPlanFragment;
import com.moh.hamadpulse.fragment.VanteliationFragment;
import com.moh.hamadpulse.fragment.View_Rad_Orders;
import com.moh.hamadpulse.fragment.laborderFragment;
import com.moh.hamadpulse.fragment.newAddVitalSignsFragment;
import com.moh.hamadpulse.fragment.newVitalSignsFragment;
import com.moh.hamadpulse.fragment.radresFragment;
import com.moh.hamadpulse.models.CardviewDataModel;
import com.moh.hamadpulse.models.Extra;
import com.moh.hamadpulse.models.NewPatientServicesModel;
import com.moh.hamadpulse.models.PatientDiagnoseModel;
import com.moh.hamadpulse.models.PatientServices;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityPatient extends AppCompatActivity implements /*View.OnClickListener,*/InterfacePatient, AdapterPatientServices.ClickAdapterPatientServices, View.OnClickListener {
    CardviewDataModel mCardviewDataModel;
    private Toolbar toolbar;
    LinearLayout patient_data_layout,open_close_layout;
    MaterialCardView cardView_data_layout;
    RecyclerView rvPatientServices;
    AdapterPatientServices mAdapterPatientServices;
    ArrayList<PatientServices> mListPatientServices;
    LinearLayout primarysitelayout, histologylayout, diagnoselayout;
    private TextView txtPatientName,patient_patric,patient_in_date,patient_day_count,patient_dig;
    private TextView txtPatientID;
    private TextView txtPatientAdmDate;
    private TextView txtDayCount, txtPrimarySiteNumber, txtPrimarySiteDisc, txtHistologyNumber, txtHistologyDisc,txt_diagnosisDisc,txt_diagnosisNumber;
    private AVLoadingIndicatorView imgLoading;
    int width;

    ImageButton open_close_btn,btn_allergies,btn_is_discharge;
    NewPatientServicesModel newPatientServicesModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Controller.mInterfacePatient=this;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        setContentView(R.layout.activity_patient);

        btn_allergies = findViewById(R.id.btn_allergies);
        btn_is_discharge = findViewById(R.id.btn_is_discharge);
        btn_is_discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),
                        "المريض مرشح للخروج", Toast.LENGTH_LONG).show();
            }
        });

        //tooltip
        patient_patric=findViewById(R.id.patient_patric);
        patient_in_date=findViewById(R.id.patient_in_date);
        patient_day_count=findViewById(R.id.patient_day_count);
        patient_dig=findViewById(R.id.patient_dig);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            patient_patric.setTooltipText("الرقم الطبي");
            patient_in_date.setTooltipText("تاريخ الدخول");
            patient_day_count.setTooltipText("عدد أيام المكوث");
            patient_dig.setTooltipText("التشخيص");
            patient_dig.setTooltipText("المريض مرشح للخروج");
            btn_allergies.setTooltipText("الحساسية");
        }


        if (getIntent().getSerializableExtra(Extra.EXTRA_PATIENT_INFO) != null) { 
            mCardviewDataModel = (CardviewDataModel) getIntent().getSerializableExtra(Extra.EXTRA_PATIENT_INFO);
        }
        //إظهار أن المريض مرشح للخروج
        if(mCardviewDataModel.getADMISSION_STATUS()!=null
                &&mCardviewDataModel.getADMISSION_STATUS().equals("1"))
            btn_is_discharge.setVisibility(View.VISIBLE);
//        hide and show patient data
        open_close_btn=findViewById(R.id.open_close_btn);
        open_close_layout=findViewById(R.id.open_close_layout);
        open_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(open_close_layout.getVisibility()==View.VISIBLE){
                    open_close_layout.setVisibility(View.GONE);
                    open_close_btn.setImageResource(R.drawable.arrow_down);
                }else{
                    open_close_layout.setVisibility(View.VISIBLE);
                    open_close_btn.setImageResource(R.drawable.arrow_up);
                }
            }
        });
        get_new_p_serv();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d("model",(newPatientServicesModel==null)+"");
                init(newPatientServicesModel);
                getPrimarySite_Histology();

                }

        }, 100);



        //CallFragment(new FragmentButtons());


    }
    public void get_new_p_serv(){

        Map<String, String> map = new HashMap<>();
        map.put("P_PATRIC_CD", "616549");
        map.put("P_ADM_CD", "412596");
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View New Patient Services");
        MyRequest.makeRquest(getBaseContext(), Controller.GET_NEW_P_SERVICES_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("NEW_P_SERVICES");
                            Gson gson = new Gson();
                            newPatientServicesModel=gson.fromJson(jsonObject.toString(),
                                    NewPatientServicesModel.class);
                            Log.d("rad", newPatientServicesModel.getRad_orders());
                            Log.d("lab", newPatientServicesModel.getLab_orders());


                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error,getBaseContext());

                    }
                });

    }
    public CardviewDataModel getmCardviewDataModel() {
        return mCardviewDataModel;
    }

    public void setmCardviewDataModel(CardviewDataModel mCardviewDataModel) {
        this.mCardviewDataModel = mCardviewDataModel;
    }

    public void setTitle(String mTitle) {
        toolbar.setTitle(mTitle);
    }

    public void init(NewPatientServicesModel newPatientServicesModel) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ملف المريض");
        imgLoading = findViewById(R.id.imgLoading);
        rvPatientServices = findViewById(R.id.rvPatientServices);
        mListPatientServices = new ArrayList<>();
        if (Controller.ORDER_DEP_CD.equals("3")) {
            if(Controller.pref.getString(USER_TYPE, "").equals("5")){
                mListPatientServices.add(new PatientServices(new FragmentDailyProgressDashboard(this), null, "Daily Progress", R.drawable.schedule));
                mListPatientServices.add(new PatientServices(new RadFragment(), null, "Radiology", R.drawable.x_ray_c));
                mListPatientServices.add(new PatientServices(new LabFragment(), null, "Laboratory", R.drawable.flask));


                //mListPatientServices.add(new PatientServices(new TreatmentPlanFragment(this), null, "الصيدلية"));
                mListPatientServices.add(new PatientServices(new FragmentPharm(this), null, "Pharmacy", R.drawable.drugs));
                mListPatientServices.add(new PatientServices(new newVitalSignsFragment(this), null, "Vital Signs", R.drawable.vital_sigins_vector));

                if (mCardviewDataModel.getHOS_NO().equals("2") || mCardviewDataModel.getHOS_NO().equals("18"))
                    mListPatientServices.add(new PatientServices(new ProtocolFragment(), null, "Protocol", R.drawable.health));
            }else {
                mListPatientServices.add(new PatientServices(new FragmentDailyProgressDashboard(this), null, "Daily Progress", R.drawable.schedule));
                mListPatientServices.add(new PatientServices(new AdmissionDashboradFragment(), null, "Admission Request & \nPhysical Examination", R.drawable.ic_admandph));
                mListPatientServices.add(new PatientServices(new RadFragment(), null, "Radiology", R.drawable.x_ray_c));
                mListPatientServices.add(new PatientServices(new LabFragment(), null, "Laboratory", R.drawable.flask));

                //mListPatientServices.add(new PatientServices(new TreatmentPlanFragment(this), null, "الصيدلية"));
                mListPatientServices.add(new PatientServices(new VanteliationFragment(this), null, "Ventilation", R.drawable.ventilator));
                mListPatientServices.add(new PatientServices(new FragmentPharm(this), null, "Pharmacy", R.drawable.drugs));
                // mListPatientServices.add(new PatientServices(new newVitalSignsFragment(this), null, "V-sighn N-notes"));
                mListPatientServices.add(new PatientServices(new ArchiveServicesFragment(), null, "Electronic file", R.drawable.medical_history));
                mListPatientServices.add(new PatientServices(new DoctorNurseNoteFragment(), null, "Doctor orders", R.drawable.prescription));

                String pathologyurl = "http://apps.moh.gov.ps/pathology/path_sys/index.php/login/get";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathologyurl));

                mListPatientServices.add(new PatientServices(new DischargeFragment(this), null, "Discharge", R.drawable.bed));
                String DEPT_SPEC_CODE = Controller.pref.getString("DEPT_SPEC_CODE", "");
                mListPatientServices.add(new PatientServices(null, browserIntent, "Pathology", R.drawable.microscope));
                Log.e("DEPT_SPEC_CODE", "ayattttt" + DEPT_SPEC_CODE);
                //  if (DEPT_SPEC_CODE.equals("22")) {
                if (mCardviewDataModel.getHOS_NO().equals("2") || mCardviewDataModel.getHOS_NO().equals("18"))
                    mListPatientServices.add(new PatientServices(new ProtocolFragment(), null, "Protocol", R.drawable.health));
                // mListPatientServices.add(new PatientServices(new ExaminationFragment(this), null, "Clinical examination", R.drawable.diagnosis));
                mListPatientServices.add(new PatientServices(new NurseServicesFragment(), null, "Nursing procedures", R.drawable.nurses));
                mListPatientServices.add(new PatientServices(new BloodTransferFragment(), null, "Blood Transfer", R.drawable.blood_transfusion));
                //   }

                mListPatientServices.add(new PatientServices(new FragmentCoronaHistory(), null, "Corona Tests", R.drawable.bacteria));
                mListPatientServices.add(new PatientServices(new AdmissionHistoryFragment(), null, "Admission History", R.drawable.history_test_icon));
                mListPatientServices.add(new PatientServices(new ReferralsFragment(), null, "Referrals ", R.drawable.ic_exchange_green));
                Intent i = new Intent(this, ActivitySummary.class);
                i.putExtra(Extra.EXTRA_PATIENT_INFO, mCardviewDataModel);
            }
            //     mListPatientServices.add(new PatientServices(null, i, "Summary", R.drawable.report));
        } else {
            mListPatientServices.add(new PatientServices(new RadFragment(), null, "Radiation", R.drawable.x_ray_c));
            mListPatientServices.add(new PatientServices(new LabFragment(), null, "Laboratory", R.drawable.flask));
            mListPatientServices.add(new PatientServices(new FragmentPharm(this), null, "Pharmacy", R.drawable.drugs));
            mListPatientServices.add(new PatientServices(new DoctorNurseNoteFragment(), null, "Doctor orders", R.drawable.prescription));
            mListPatientServices.add(new PatientServices(new ArchiveServicesFragment(), null, "Electronic file", R.drawable.medical_history));
            mListPatientServices.add(new PatientServices(new NurseServicesFragment(), null, "Nursing procedures", R.drawable.nurses));
            mListPatientServices.add(new PatientServices(new FragmentCoronaHistory(), null, "Corona Tests", R.drawable.bacteria));
        }
        mAdapterPatientServices = new AdapterPatientServices(mListPatientServices, this::myclick,newPatientServicesModel);
//        rvPatientServices.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        rvPatientServices.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        //mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //rvPatientServices.setLayoutManager(mLinearLayoutManager);
        rvPatientServices.setLayoutManager(new GridLayoutManager(this, width <= 780 ? 1 : 2));//
        rvPatientServices.setAdapter(mAdapterPatientServices);
        txtPatientName = findViewById(R.id.txtPatientName);
        txtPatientID = findViewById(R.id.txtPatientID);
        txtPatientAdmDate = findViewById(R.id.txtPatientAdmDate);
        txtDayCount = findViewById(R.id.txtDayCount);
        txtPatientName.setText(mCardviewDataModel.getPatname());
        txtPatientID.setText(mCardviewDataModel.getPatid() + "");
        txtPatientAdmDate.setText(mCardviewDataModel.getIndate());

        btn_allergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllargiesFragment allargiesFragment = new AllargiesFragment();
                CallFragment(allargiesFragment);

            }
        });
//        MyDateTime mMyDateTime = ToolApp.getDiffDate(ToolApp.getDateNow(), ToolApp.convertFormatDate(mCardviewDataModel.getIndate(), new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String start_day=mCardviewDataModel.getIndate().split(" ")[0].replace("/","-");
            String end_day=LocalDate.now().format(formatter).toString();
//            Log.d("my_date",start_day);
//            Log.d("my_date",end_day);
//            Log.d("my_date",DAYS.between(LocalDate.parse(start_day,formatter),
//                    LocalDate.parse(end_day,formatter))+"");
            txtDayCount.setText(DAYS.between(LocalDate.parse(start_day,formatter),
                    LocalDate.parse(end_day,formatter))+"");
        }

        patient_data_layout = findViewById(R.id.patient_data_layout);
        cardView_data_layout = findViewById(R.id.card_data_layout);
//        btnCoronaHistory = (Button) findViewById(R.id.btnCoronaHistory);
//        btnCoronaHistory.setOnClickListener(this);
//        btnLab = findViewById(R.id.btn_lab);
//        btnLab.setOnClickListener(this);
//        btnRad = findViewById(R.id.btn_rad);
//        btnRad.setOnClickListener(this);
//        btnPharm = findViewById(R.id.btn_pharm);
//        btnPharm.setOnClickListener(this);
//        btnVital = findViewById(R.id.btn_vital);
//        btnVital.setOnClickListener(this);
//        btnVentailor = findViewById(R.id.btn_ventailor);
//        btnVentailor.setOnClickListener(this);
//        btnDocnotes = findViewById(R.id.btn_docnotes);
//        btnDocnotes.setOnClickListener(this);
//        btnArchive = findViewById(R.id.btn_archive);
//        btnArchive.setOnClickListener(this);
//        btnPathology = findViewById(R.id.btn_pathology);
//        btnPathology.setOnClickListener(this);
//        btnDischarge = findViewById(R.id.btn_discharge);
//        btnDischarge.setOnClickListener(this);
//        btnDailyProgrees = findViewById(R.id.btn_daily_progrees);
//        btnDailyProgrees.setOnClickListener(this);
//        btnSummary = findViewById(R.id.btnSummary);
//        btnSummary.setOnClickListener(this);
        primarysitelayout = findViewById(R.id.primarysitelayout);
        histologylayout = findViewById(R.id.histologylayout);
        diagnoselayout = findViewById(R.id.diagnoselayout);
        txtPrimarySiteNumber = findViewById(R.id.txtPrimarySiteNumber);
        txtPrimarySiteDisc = findViewById(R.id.txtPrimarySiteDisc);
        txtHistologyNumber = findViewById(R.id.txtHistologyNumber);
        txtHistologyDisc = findViewById(R.id.txtHistologyDisc);
        txt_diagnosisDisc = findViewById(R.id.txt_diagnosisDisc);
        txt_diagnosisNumber = findViewById(R.id.txt_diagnosisNumber);
//        CV_Radiation = findViewById(R.id.CV_Radiation);
//        CV_Laboratory = findViewById(R.id.CV_Laboratory);
//        CV_Ventilation = findViewById(R.id.CV_Ventilation);
//        CV_Pharmacy = findViewById(R.id.CV_Pharmacy);
//        CV_Discharge = findViewById(R.id.CV_Discharge);
//        CV_Pathology = findViewById(R.id.CV_Pathology);
//        CV_Protocol = findViewById(R.id.CV_Protocol);
//        CV_Clinical_examination = findViewById(R.id.CV_Clinical_examination);
//        CV_Nursing_Procedures = findViewById(R.id.CV_Nursing_Procedures);
//        CV_Blood_Transfer = findViewById(R.id.CV_Blood_Transfer);
//        CV_Daily_Progress = findViewById(R.id.CV_Daily_Progress);
//        CV_Corona_Tests = findViewById(R.id.CV_Corona_Tests);
//        CV_Summary = findViewById(R.id.CV_Summary);
//        CV_Doctor_orders = findViewById(R.id.CV_Doctor_orders);
//        CV_Electronic_file = findViewById(R.id.CV_Electronic_file);
//        ArrayList<CardView> cardViews = new ArrayList<CardView>(Arrays.asList(new CardView[]{CV_Radiation
//                , CV_Laboratory, CV_Ventilation, CV_Pharmacy, CV_Discharge, CV_Pathology, CV_Protocol, CV_Clinical_examination, CV_Nursing_Procedures
//                , CV_Blood_Transfer, CV_Daily_Progress, CV_Corona_Tests, CV_Summary, CV_Doctor_orders, CV_Electronic_file}));
//        for (CardView view : cardViews) {
//            view.setOnClickListener(this);
//        }
//        rvPatientServices=findViewById(R.id.patient_services_rv);
        if (!(mCardviewDataModel.getHOS_NO().equals("2") || mCardviewDataModel.getHOS_NO().equals("18"))) {
            primarysitelayout.setVisibility(View.GONE);
            histologylayout.setVisibility(View.GONE);
            diagnoselayout.setVisibility(View.VISIBLE);
        }else
            diagnoselayout.setVisibility(View.GONE);
        isPatietHaveAllergies();
    }

    private void isPatietHaveAllergies() {
        final boolean[] flag = {false};
        Map<String, String> map = new HashMap<>();
        //  map.put("P_PATREC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_PATREC_CD", getmCardviewDataModel().getPatid() + "");
        Log.e("allariey", map.toString());
        MyRequest.makeRquest(getBaseContext(), Controller.GET_PATIENT_ALLERGIES_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Log.e("ayat", response);
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ALLERGIES_LIST");
                    if (jsonArray.length() > 0) {
                        AllargiesFragment allargiesFragment = new AllargiesFragment();
                        CallFragment(allargiesFragment);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //     Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getBaseContext());
            }
        });

    }

    void getPrimarySite_Histology() {

        Map<String, String> map = new HashMap<>();
//        Log.d("pid",)
        map.put("P_PATRIC_CD", mCardviewDataModel.getPatid() + "");
//        map.put("P_PATRIC_CD", "120");
        MyRequest.makeRquest(getBaseContext(), Controller.GET_PATIENT_DIAGNOSE_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray
                            ("DIAGNOSE_CURE");
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<PatientDiagnoseModel>>() {
                    }.getType();
                    ArrayList<PatientDiagnoseModel> list = gson.fromJson(jsonArray.toString(),
                            type);
                    if (list.size() > 0) {
                        PatientDiagnoseModel model = list.get(0);
                        if(histologylayout.getVisibility()!=View.VISIBLE) {
                            txt_diagnosisNumber.setText(model.getCmIcdCode().isEmpty()?"":model.getCmIcdCode());
                            if(model.getCmIcdNameEn().isEmpty()){
                                txt_diagnosisDisc.setText("التشخيص غير مدخل");
                                txt_diagnosisDisc.setGravity(Gravity.CENTER);
                                txt_diagnosisDisc.setTextColor(Color.RED);
                            }else
                            txt_diagnosisDisc.setText(model.getCmIcdNameEn());
                        }else{
                            txtPrimarySiteNumber.setText(model.getTopologyId());
                            txtPrimarySiteDisc.setText(model.getCtIcdNameEn());
                            txtHistologyNumber.setText(model.getCmIcdCode());
                            txtHistologyDisc.setText(model.getCmIcdNameEn());
                        }

                    } else {

                            txtPrimarySiteDisc.setText("التشخيص غير مدخل");
                            txtPrimarySiteDisc.setGravity(Gravity.CENTER);
                            txtPrimarySiteDisc.setTextColor(Color.RED);
                            txtHistologyDisc.setText("التشخيص غير مدخل");
                            txtHistologyDisc.setGravity(Gravity.CENTER);
                            txtHistologyDisc.setTextColor(Color.RED);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getApplicationContext());
            }
        });
    }

    public void CallFragment(Fragment mFragment) {
        if (mFragment instanceof FragmentDailyProgress)
            ((FragmentDailyProgress) mFragment).setmInterfacePatient(this);
//        if(mFragment instanceof FragmentDailyProgressDashboard) {
//            toolbar.setTitle("Daily Progress");
//            ((FragmentDailyProgressDashboard) mFragment).setmInterfacePatient(this);
//        }
//        if(mFragment instanceof LabFragment)
//            toolbar.setTitle("المختبر");
        if (mFragment instanceof laborderFragment) {
            toolbar.setTitle("طلب فحص مخبري");
            ((laborderFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof Lbres_Fragment) {
            toolbar.setTitle("نتائج الفحوصات المخبرية");
            ((Lbres_Fragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof AddNewProtocol) {
//            toolbar.setTitle("نتائج الفحوصات المخبرية");
            ((AddNewProtocol) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof DoctorNurseNoteFragment) {
            ((DoctorNurseNoteFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof AddDoctorNurseNoteFragment) {
            ((AddDoctorNurseNoteFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof TreatmentPlanFragment) {
            ((TreatmentPlanFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof FragmentPharm) {
            ((FragmentPharm) mFragment).setmInterfacePatient(this);
        }
        if(mFragment instanceof AddTreatmentPlanFragment) {
            ((AddTreatmentPlanFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof RadiologyFragment) {
            ((RadiologyFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof AddRadiologyOrderFragment) {
            ((AddRadiologyOrderFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof radresFragment) {
            ((radresFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof Efile_Fragment) {
            ((Efile_Fragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof EfilePhotoCopingFragment) {
            ((EfilePhotoCopingFragment) mFragment).setmInterfacePatient(this);
        }
//        if (mFragment instanceof newVitalSignsFragment) {
//            ((newVitalSignsFragment) mFragment).setmInterfacePatient(this);
//        }
        if (mFragment instanceof newAddVitalSignsFragment) {
            ((newAddVitalSignsFragment) mFragment).setmInterfacePatient(this);
        }
//        if (mFragment instanceof VanteliationFragment) {
//            ((VanteliationFragment) mFragment).setmInterfacePatient(this);
//        }
        if (mFragment instanceof AddVentelationFragment) {
            ((AddVentelationFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof DischargeFragment) {
            ((DischargeFragment) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof FragmentCoronaHistory) {
            ((FragmentCoronaHistory) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof View_Rad_Orders) {
            ((View_Rad_Orders) mFragment).setmInterfacePatient(this);
        }
        if (mFragment instanceof Fragment_View_Lab_Reports) {
            ((Fragment_View_Lab_Reports) mFragment).setmInterfacePatient(this);
        }


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framePatient, mFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_daily_progrees:
//                CallFragment(new FragmentDailyProgressDashboard());
//                break;
//            case R.id.btnCoronaHistory:
//                CallFragment(new FragmentCoronaHistory());
//                break;
//            case R.id.btn_lab:
//                CallFragment(new LabFragment());
//                break;
//            case R.id.btn_docnotes:
//                CallFragment(new DoctorNurseNoteFragment());
//                break;
//            case R.id.btn_pharm:
//                CallFragment(new TreatmentPlanFragment());
//                break;
//            case R.id.btn_rad:
//                CallFragment(new RadFragment());
//                break;
//            case R.id.btn_pathology:
//                String pathologyurl = "http://apps.moh.gov.ps/pathology/path_sys/index.php/login/get";
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathologyurl));
//                startActivity(browserIntent);
//                break;
//            case R.id.btn_archive:
//                CallFragment(new ArchiveServicesFragment());
//                break;
//            case R.id.btn_vital:
//                CallFragment(new newVitalSignsFragment());
//                break;
//            case R.id.btn_ventailor:
//                CallFragment(new VanteliationFragment());
//                break;
//            case R.id.btn_discharge:
//                CallFragment(new DischargeFragment());
//                break;
//            case R.id.btnSummary:
//                Intent i = new Intent(this, ActivitySummary.class);
//                i.putExtra(Extra.EXTRA_PATIENT_INFO,mCardviewDataModel);
//                startActivity(i);
//                break;
//        }
//    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            toolbar.setTitle("ملف المريض");
        }


    }

    @Override
    public void showLoading(boolean b) {
        if (b)
            imgLoading.setVisibility(View.VISIBLE);
        else
            imgLoading.setVisibility(View.GONE);
    }

    @Override
    public void myclick(PatientServices mPatientServices) {
        if (mPatientServices.getmFragment() != null) {
            if (mPatientServices.getmFragment() instanceof DischargeFragment &&
                    !(Controller.pref.getString(USER_TYPE, "").equals("1") ||
                            Controller.pref.getString(USER_TYPE, "").equals("3"))
            )
                Toast.makeText(this, "ترشيح المريض للخروج ليس من صلاحياتك", Toast.LENGTH_LONG).show();
            else
                CallFragment(mPatientServices.getmFragment());

        } else
            startActivity(mPatientServices.getmIntent());
    }

    @Override
    public void onClick(View view) {
        Log.d("test_click", (view.getId() == R.id.CV_Radiation) + "");
        switch (view.getId()) {
            case R.id.CV_Radiation:
                myclick(mListPatientServices.get(0));
                break;
            case R.id.CV_Laboratory:
                myclick(mListPatientServices.get(1));
                break;
            case R.id.CV_Ventilation:
                myclick(mListPatientServices.get(2));
                break;
            case R.id.CV_Pharmacy:
                myclick(mListPatientServices.get(3));
                break;
            case R.id.CV_Electronic_file:
                myclick(mListPatientServices.get(4));
                break;
            case R.id.CV_Doctor_orders:
                myclick(mListPatientServices.get(5));
                break;
            case R.id.CV_Discharge:
                myclick(mListPatientServices.get(6));
                break;
            case R.id.CV_Pathology:
                myclick(mListPatientServices.get(7));
                break;
            case R.id.CV_Protocol:
                myclick(mListPatientServices.get(8));
                break;
            case R.id.CV_Clinical_examination:
                myclick(mListPatientServices.get(9));
                break;
            case R.id.CV_Nursing_Procedures:
                myclick(mListPatientServices.get(10));
                break;
            case R.id.CV_Blood_Transfer:
                myclick(mListPatientServices.get(11));
                break;
            case R.id.CV_Daily_Progress:
                myclick(mListPatientServices.get(12));
                break;
            case R.id.CV_Corona_Tests:
                myclick(mListPatientServices.get(13));
                break;
            case R.id.CV_Summary:
                myclick(mListPatientServices.get(14));
                break;


        }
    }

    public void show_hide_details(int v) {
        patient_data_layout.setVisibility(v);
        cardView_data_layout.setVisibility(v);
    }
//    @Override
//    public void myClick(PatientServices mPatientServices) {
//        CallFragment(mPatientServices.getmFragment());
//    }
}