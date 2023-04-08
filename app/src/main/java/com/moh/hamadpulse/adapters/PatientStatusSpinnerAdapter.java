package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.AdmPatientConst;

import java.util.ArrayList;
import java.util.List;

public class PatientStatusSpinnerAdapter extends ArrayAdapter<AdmPatientConst> {

    ArrayList<AdmPatientConst> PatientStatusList;
    Context context;

    public PatientStatusSpinnerAdapter(Context context, int resource, List<AdmPatientConst> PatientStatusList) {
        super(context, resource, PatientStatusList);
        this.context = context;
        this.PatientStatusList = (ArrayList) PatientStatusList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView status = (CheckedTextView) view.findViewById(android.R.id.text1);
        status.setText(PatientStatusList.get(position).getPATIENTSTATUSNAMEEN());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return PatientStatusList.size();
    }
}
