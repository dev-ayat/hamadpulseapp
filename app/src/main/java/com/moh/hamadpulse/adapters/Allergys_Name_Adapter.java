package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.Allergy_Model;

import java.util.ArrayList;

public class Allergys_Name_Adapter extends ArrayAdapter<Allergy_Model> {

    ArrayList<Allergy_Model> allergy_models;
    Context context;

    public Allergys_Name_Adapter(Context context, int resource, ArrayList<Allergy_Model> allergy_models) {
        super(context, resource, allergy_models);
        this.context = context;
        this.allergy_models = allergy_models;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView radservicesname = (CheckedTextView) view.findViewById(android.R.id.text1);
        radservicesname.setText(allergy_models.get(position).getAllergyName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return allergy_models.size();
    }
}
