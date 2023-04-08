package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetRadMasterOrganConst;

import java.util.ArrayList;
import java.util.List;

public class RadiologyMasterOrganAdapter extends ArrayAdapter<GetRadMasterOrganConst> {

    ArrayList<GetRadMasterOrganConst> RadMasterOrganList;
    Context context;

    public RadiologyMasterOrganAdapter(Context context, int resource, List<GetRadMasterOrganConst> RadMasterOrganList) {
        super(context, resource, RadMasterOrganList);
        this.context = context;
        this.RadMasterOrganList = (ArrayList) RadMasterOrganList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView radorganname = (CheckedTextView) view.findViewById(android.R.id.text1);
        radorganname.setText(RadMasterOrganList.get(position).getORGANNAMEEN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return RadMasterOrganList.size();
    }
}
