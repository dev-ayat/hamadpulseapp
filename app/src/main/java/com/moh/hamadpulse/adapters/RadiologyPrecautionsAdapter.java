package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetRadprecautions;

import java.util.ArrayList;
import java.util.List;

public class RadiologyPrecautionsAdapter extends ArrayAdapter<GetRadprecautions> {

    ArrayList<GetRadprecautions> radprecautionsArrayList;
    Context context;

    public RadiologyPrecautionsAdapter(Context context, int resource, List<GetRadprecautions> RadDetailsOrganList) {
        super(context, resource, RadDetailsOrganList);
        this.context = context;
        this.radprecautionsArrayList = (ArrayList) RadDetailsOrganList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView radprecautionname = (CheckedTextView) view.findViewById(android.R.id.text1);
        radprecautionname.setText(radprecautionsArrayList.get(position).getLookupDetails());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return radprecautionsArrayList.size();
    }
}
