package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.mInterfacePatient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.R;

public class DoctorFragment extends Fragment implements View.OnClickListener {
    TextView lblscname, lbordername;
    CardView cvdoctor_orders, cvdoctor_notes;

    public DoctorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lbordername = view.findViewById(R.id.lbordername);
        cvdoctor_orders = view.findViewById(R.id.cvdoctor_orders);
        cvdoctor_notes = view.findViewById(R.id.cvdoctor_notes);
        lbordername.setOnClickListener(this);
        cvdoctor_orders.setOnClickListener(this);
        cvdoctor_notes.setOnClickListener(this);

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
        ((ActivityPatient) getActivity()).setTitle("أوامر وملاحظات الطبيب");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // نتائج طلبات المختبر
            case R.id.cvdoctor_notes:

                ((ActivityPatient) getActivity()).CallFragment(new DoctorNurseNoteFragment(mInterfacePatient, 1));
                break;
            //إضافة طلب مختبر
//            case R.id.cvLabRequest:
//                if (!(Controller.pref.getString(USER_TYPE, "").equals("1") || Controller.pref.getString(USER_TYPE, "").equals("3")))
//                    Toast.makeText(getContext(), "الإضافة من ضمن صلاحيات الدكتور ", Toast.LENGTH_SHORT).show();
//                else
//                    ((ActivityPatient) getActivity()).CallFragment(new laborderFragment());
//
//                break;
            case R.id.cvdoctor_orders:
                ((ActivityPatient) getActivity()).CallFragment(new DoctorOrdersFragment());
                break;


        }
    }
}
