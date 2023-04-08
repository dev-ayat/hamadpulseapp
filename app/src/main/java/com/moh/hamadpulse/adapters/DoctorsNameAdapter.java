package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moh.hamadpulse.models.DoctorNameModel;

import java.util.ArrayList;

public class DoctorsNameAdapter extends ArrayAdapter<DoctorNameModel> {

    ArrayList<DoctorNameModel> doctorNameModels;
    Context context;

    public DoctorsNameAdapter(Context context, int resource, ArrayList<DoctorNameModel> doctorNameModels) {
        super(context, resource, doctorNameModels);
        this.context = context;
        this.doctorNameModels = doctorNameModels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView name = view.findViewById(android.R.id.text1);
        name.setText(doctorNameModels.get(position).getFull_name());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return doctorNameModels.size();
    }
}

