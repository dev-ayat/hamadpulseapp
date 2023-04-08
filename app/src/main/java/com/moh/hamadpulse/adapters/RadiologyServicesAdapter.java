package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetRadServicesConst;

import java.util.ArrayList;
import java.util.List;

public class RadiologyServicesAdapter extends ArrayAdapter<GetRadServicesConst> {

    ArrayList<GetRadServicesConst> RadServicesList;
    Context context;

    public RadiologyServicesAdapter(Context context, int resource, List<GetRadServicesConst> RadServicesList) {
        super(context, resource, RadServicesList);
        this.context = context;
        this.RadServicesList = (ArrayList) RadServicesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView radservicesname = (CheckedTextView) view.findViewById(android.R.id.text1);
        radservicesname.setText(RadServicesList.get(position).getSERVICENAMEEN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return RadServicesList.size();
    }
}
