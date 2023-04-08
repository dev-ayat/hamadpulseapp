package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moh.hamadpulse.models.GetICD10Const;

import java.util.ArrayList;

public class ICD10ConstAutoCompleteAdapter extends ArrayAdapter<GetICD10Const> {

    ArrayList<GetICD10Const> getIcdConstArrayList;
    Context context;

    public ICD10ConstAutoCompleteAdapter(Context context, int resource, ArrayList<GetICD10Const> getIcdConstArrayList) {
        super(context, resource, getIcdConstArrayList);
        this.context = context;
        this.getIcdConstArrayList = (ArrayList) getIcdConstArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView MedicinName = (TextView) view.findViewById(android.R.id.text1);
        MedicinName.setText(getIcdConstArrayList.get(position).getICDNAMEEN());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {

        return getIcdConstArrayList.size();
    }
}
