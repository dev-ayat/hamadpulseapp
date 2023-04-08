package com.moh.hamadpulse.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.R;


public class AdmissionDashboradFragment extends Fragment {
    CardView cvAdm, cv_ph_ex;

    public AdmissionDashboradFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admission_dashborad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cv_ph_ex = view.findViewById(R.id.cv_ph_ex);
        cvAdm = view.findViewById(R.id.cvAdm);
        cv_ph_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new ViewPhysicalExamination());
            }
        });
        cvAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new AdmissionRequestFragment());
            }
        });
    }
}