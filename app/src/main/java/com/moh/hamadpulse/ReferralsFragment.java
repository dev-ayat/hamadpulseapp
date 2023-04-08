package com.moh.hamadpulse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moh.hamadpulse.fragment.GetReferralPhysicalTherapyFragment;
import com.moh.hamadpulse.fragment.PatientTransfer;

public class ReferralsFragment extends Fragment {

    //
    CardView CV_Physical_Therapy, cv_patient_transfer;

    public ReferralsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_referrals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CV_Physical_Therapy = view.findViewById(R.id.CV_Physical_Therapy);
        cv_patient_transfer = view.findViewById(R.id.cv_patient_transfer);
        CV_Physical_Therapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new GetReferralPhysicalTherapyFragment());
            }
        });
        cv_patient_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new PatientTransfer());
            }
        });
    }
    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("التحويلات");
    }

}