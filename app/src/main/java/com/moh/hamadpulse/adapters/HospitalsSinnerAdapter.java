package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moh.hamadpulse.R;

import java.util.ArrayList;

public class HospitalsSinnerAdapter extends BaseAdapter {
    ArrayList<String> Hospitals;

    public HospitalsSinnerAdapter(ArrayList<String> hospitals) {
        Hospitals = hospitals;
    }

    @Override
    public int getCount() {
        return Hospitals.size();
    }

    @Override
    public Object getItem(int i) {
        return Hospitals.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflateview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hospital_spinner, viewGroup, false);
        TextView hosspinner = inflateview.findViewById(R.id.hos_spinner);
        hosspinner.setText(Hospitals.get(i));
        return inflateview;
    }
}
