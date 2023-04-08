package com.moh.hamadpulse.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.FragmentDailyProgressDashboard;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.models.CardviewDataModel;

public class PatientProfileFragment extends Fragment {
    String patname, patid, admdate, patadmcd, hos_no, hos_name, sex_cd, sex_name,
            patDOB, USER_CODE, USER_ID, LOC_CD, patmrp, DocTypecd, HOS_PERMISSION;
    TextView txtpatid, txtpatName, txtadmdate, txtdaycount;
    Button btn_lab, btn_rad, btn_pharm, btn_vital, btn_ventailor, btn_docnotes, btn_archive, btn_pathology, btn_discharge,btn_daily_progrees;
    Bundle args;
    CardviewDataModel mCardviewDataModel;

    public PatientProfileFragment() {
        Log.e("test", "PatientProfileFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        txtadmdate = view.findViewById(R.id.txtadmdate);
        Log.e("photoargs2", getArguments().toString());
        btn_lab = view.findViewById(R.id.btn_lab);
        btn_rad = view.findViewById(R.id.btn_rad);
        btn_pharm = view.findViewById(R.id.btn_pharm);
        btn_vital = view.findViewById(R.id.btn_vital);
        btn_ventailor = view.findViewById(R.id.btn_ventailor);
        btn_docnotes = view.findViewById(R.id.btn_docnotes);
        btn_archive = view.findViewById(R.id.btn_archive);
        btn_pathology = view.findViewById(R.id.btn_pathology);
        btn_discharge = view.findViewById(R.id.btn_discharge);
        btn_daily_progrees = view.findViewById(R.id.btn_daily_progrees);

        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        patmrp = getArguments().getString("patmrp");
        admdate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        hos_no = getArguments().getString("pat_HOS_NO");
        hos_name = getArguments().getString("pat_HOS_NAME");
        sex_cd = getArguments().getString("pat_SEX_CD");
        sex_name = getArguments().getString("pat_SEX_NAME");
        patDOB = getArguments().getString("pat_DOB");
        HOS_PERMISSION = getArguments().getString("HOS_PERMISSION");
        LOC_CD = getArguments().getString("LOC_CODE");
        Log.e("args", "aaa   " + getArguments());
        USER_CODE = String.valueOf(Controller.pref.getInt("USER_CODE", -1));
        USER_ID = Controller.pref.getString("USER_ID", "");
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        txtadmdate.setText(admdate);
        args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("patmrp", patmrp);
        args.putString("indate", admdate);
        args.putString("patadm", patadmcd);
        args.putString("pat_HOS_NO", hos_no);
        args.putString("pat_HOS_NAME", hos_name);
        args.putString("pat_SEX_CD", sex_cd);
        args.putString("pat_SEX_NAME", sex_name);
        args.putString("pat_DOB", patDOB);
        args.putString("HOS_PERMISSION", HOS_PERMISSION);
        args.putString("LOC_CD", LOC_CD);
        Log.e("args", args.toString());
        btn_lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLab();
            }
        });

        btn_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToRad();
            }
        });

        btn_pharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToPharm();
            }
        });

        btn_daily_progrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToTest(new FragmentDailyProgressDashboard());
            }
        });

        btn_vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToVital();
            }
        });

        btn_ventailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToVentailor();
            }
        });

        btn_docnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToDocnotes();
            }
        });

        btn_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToArchive();
            }
        });

        btn_pathology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToPathology();
            }
        });

        btn_discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToDischarge();
            }
        });
        return view;

    }

    public void GoToTest(Fragment mFragment)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mFragment.setArguments(args);
        ft.replace(R.id.content_frame, mFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToLab() {
        LabFragment labFragment = new LabFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        labFragment.setArguments(args);
        ft.replace(R.id.content_frame, labFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToRad() {
        RadFragment radFragment = new RadFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        radFragment.setArguments(args);
        ft.replace(R.id.content_frame, radFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToPharm() {
        TreatmentPlanFragment treatmentPlanFragment = new TreatmentPlanFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        treatmentPlanFragment.setArguments(args);
        ft.replace(R.id.content_frame, treatmentPlanFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToVital() {
        newVitalSignsFragment vitalSignsFragment = new newVitalSignsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        vitalSignsFragment.setArguments(args);
        ft.replace(R.id.content_frame, vitalSignsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToVentailor() {
        VanteliationFragment vanteliationFragment = new VanteliationFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        vanteliationFragment.setArguments(args);
        ft.replace(R.id.content_frame, vanteliationFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToDocnotes() {
        DoctorNurseNoteFragment diagnoseFragment = new DoctorNurseNoteFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        diagnoseFragment.setArguments(args);
        ft.replace(R.id.content_frame, diagnoseFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToArchive() {
        ArchiveServicesFragment archiveServicesFragment = new ArchiveServicesFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        archiveServicesFragment.setArguments(args);
        ft.replace(R.id.content_frame, archiveServicesFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void GoToPathology() {
        String pathologyurl = "http://apps.moh.gov.ps/pathology/path_sys/index.php/login/get";
        Log.e("pathologyurl", pathologyurl);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathologyurl));
        startActivity(browserIntent);
    }

    public void GoToDischarge() {
//        DischargeFragment dischargeFragment = new DischargeFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        dischargeFragment.setArguments(args);
//        ft.replace(R.id.content_frame, dischargeFragment);
//        ft.addToBackStack(null);
//        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("اللملف الطبي للمريض");
    }

}
