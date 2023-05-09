package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;

public class LabFragment extends Fragment implements View.OnClickListener {
    TextView lblscname, lbordername;
    CardView cvLabResult, cvLabRequest, cvCentralLab, cvSampling;

    public LabFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lab, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lblscname = view.findViewById(R.id.lblscname);
        lbordername = view.findViewById(R.id.lbordername);
        cvLabResult = view.findViewById(R.id.cvLabResult);
        cvLabRequest = view.findViewById(R.id.cvLabRequest);
      //  cvCentralLab = view.findViewById(R.id.cvCentralLab);
        cvSampling = view.findViewById(R.id.cvSampling);
        lbordername.setOnClickListener(this);
        cvLabResult.setOnClickListener(this);
        cvLabRequest.setOnClickListener(this);
//        cvCentralLab.setOnClickListener(this);
        cvSampling.setOnClickListener(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_dept);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("المختبر");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // نتائج طلبات المختبر
            case R.id.cvLabResult:
                ((ActivityPatient) getActivity()).CallFragment(new Lbres_Fragment());
                break;
            //إضافة طلب مختبر
            case R.id.cvLabRequest:
                if (!(Controller.pref.getString(USER_TYPE, "").equals("1") || Controller.pref.getString(USER_TYPE, "").equals("3")))
                    Toast.makeText(getContext(), "الإضافة من ضمن صلاحيات الدكتور ", Toast.LENGTH_SHORT).show();
                else
                    ((ActivityPatient) getActivity()).CallFragment(new laborderFragment());

                break;
//            case R.id.cvCentralLab:
//                ((ActivityPatient) getActivity()).CallFragment(new Fragment_View_Lab_Reports());
//                break;
            case R.id.cvSampling:
                if (Controller.pref.getString(USER_TYPE, "").equals("5"))
                    Toast.makeText(getContext(), "ليس من ضمن صلاحيات المستخدم", Toast.LENGTH_SHORT).show();
                else
                    ((ActivityPatient) getActivity()).CallFragment(new ViewAllSampling());

        }
    }
}
