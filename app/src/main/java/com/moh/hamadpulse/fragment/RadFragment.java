package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;

public class RadFragment extends Fragment {
    CardView cvRadRequest, cvRadResult, cvRadReports;

    public RadFragment() {
        Log.e("test", RadFragment.class.getSimpleName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rad, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvRadRequest = view.findViewById(R.id.cvRadRequest);
        cvRadResult = view.findViewById(R.id.cvRadResult);
        cvRadReports = view.findViewById(R.id.cvRadReports);
        cvRadResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new newradresFragment((InterfacePatient) getContext()));
            }
        });
// طلب صورة أشعة
        cvRadRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Controller.pref.getString(USER_TYPE, "").equals("2") || Controller.pref.getString(USER_TYPE, "").equals("4"))
                    Toast.makeText(getContext(), "اضافة طلبات الأشعة من ضمن صلاحيات الدكتور ", Toast.LENGTH_SHORT).show();
                else
                    ((ActivityPatient) getActivity()).CallFragment(new RadiologyFragment());

            }
        });
        cvRadReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ActivityPatient) getActivity()).CallFragment(new View_Rad_Orders());

            }
        });

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
//
//    }

    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("الأشعة");
    }
}
