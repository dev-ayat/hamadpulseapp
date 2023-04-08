package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;

public class NurseServicesFragment extends Fragment implements View.OnClickListener {
    TextView lblscname, lbordername;
    CardView CV_in_out_take, CV_Diabetic, CV_Changing_Position, CV_V_Sighn_Notes, CV_View_O2;

    public NurseServicesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nurse_services_fragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CV_in_out_take = view.findViewById(R.id.CV_in_out_take);
        CV_Diabetic = view.findViewById(R.id.CV_Diabetic);
        CV_Changing_Position = view.findViewById(R.id.CV_Changing_Position);
        CV_V_Sighn_Notes = view.findViewById(R.id.CV_V_sighn);
        CV_View_O2 = view.findViewById(R.id.CV_View_O2);
        if (!Controller.ORDER_DEP_CD.equals("2")) {
            CV_in_out_take.setOnClickListener(this);
            CV_Diabetic.setOnClickListener(this);
            CV_Changing_Position.setOnClickListener(this);
            CV_V_Sighn_Notes.setOnClickListener(this);
            CV_View_O2.setOnClickListener(this);
        } else {
            CV_V_Sighn_Notes.setOnClickListener(this);
            CV_in_out_take.setVisibility(View.GONE);
            CV_Diabetic.setVisibility(View.GONE);
            CV_Changing_Position.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("الإجراءات التمريضية");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // in out take
            case R.id.CV_in_out_take:
                ((ActivityPatient) getActivity()).CallFragment(new View_Take_In_Out());
                break;
            // Diabetic
            case R.id.CV_Diabetic:
                ((ActivityPatient) getActivity()).CallFragment(new ViewDiabeticCur());
                break;
            case R.id.CV_Changing_Position:
//                ((ActivityPatient) getActivity()).CallFragment(new Add_Changing_Position());
                ((ActivityPatient) getActivity()).CallFragment(new View_Change_Position());
                break;
            case R.id.CV_V_sighn:
                ((ActivityPatient) getActivity()).CallFragment(new newVitalSignsFragment((ActivityPatient) getActivity()));
                break;
            case R.id.CV_View_O2:
                ((ActivityPatient) getActivity()).CallFragment(new ViewO2Mode());

        }
    }
}
