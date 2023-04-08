package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetOrganDetailsConst;

import java.util.ArrayList;
import java.util.List;

public class RadiologyDetailsOrganAdapter extends ArrayAdapter<GetOrganDetailsConst> {

    ArrayList<GetOrganDetailsConst> RadDetailsOrganList;
    Context context;

    public RadiologyDetailsOrganAdapter(Context context, int resource, List<GetOrganDetailsConst> RadDetailsOrganList) {
        super(context, resource, RadDetailsOrganList);
        this.context = context;
        this.RadDetailsOrganList = (ArrayList) RadDetailsOrganList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView raddetailsorganname = (CheckedTextView) view.findViewById(android.R.id.text1);
        raddetailsorganname.setText(RadDetailsOrganList.get(position).getORGANDNAMEEN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return RadDetailsOrganList.size();
    }
}
