package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.constants.Delivery;
import com.moh.hamadpulse.models.GetVentilationConst;

import java.util.ArrayList;

public class VentalationModeSpinnerAdapter extends ArrayAdapter<Object> {

    ArrayList<Object> VentilationModeList;
    Context context;



    public VentalationModeSpinnerAdapter(Context context, int resource, ArrayList<Object> VentilationModeList) {
        super(context, resource, VentilationModeList);
        this.context = context;
        this.VentilationModeList =  VentilationModeList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView VentMode = (CheckedTextView) view.findViewById(android.R.id.text1);
        if(VentilationModeList.get(position) instanceof GetVentilationConst)
            VentMode.setText(((GetVentilationConst) VentilationModeList.get(position)).getVENTYPENAME());
        if(VentilationModeList.get(position) instanceof Delivery)
            VentMode.setText(((Delivery) VentilationModeList.get(position)).getDELIVERY_NAME_EN());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return VentilationModeList.size();
    }
}
