package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetRadPositionConst;

import java.util.ArrayList;
import java.util.List;

public class RadiologyPositionAdapter extends ArrayAdapter<GetRadPositionConst> {

    ArrayList<GetRadPositionConst> RadPositionList;
    Context context;

    public RadiologyPositionAdapter(Context context, int resource, List<GetRadPositionConst> RadPositionList) {
        super(context, resource, RadPositionList);
        this.context = context;
        this.RadPositionList = (ArrayList) RadPositionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView radpositionname = (CheckedTextView) view.findViewById(android.R.id.text1);
        radpositionname.setText(RadPositionList.get(position).getCRADPOSITIONNAMEEN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return RadPositionList.size();
    }
}
