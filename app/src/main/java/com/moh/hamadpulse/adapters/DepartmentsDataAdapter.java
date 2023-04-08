package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.moh.hamadpulse.fragment.DepartmentsDataModel;


import java.util.ArrayList;

public class DepartmentsDataAdapter extends ArrayAdapter<DepartmentsDataModel> {

    ArrayList<DepartmentsDataModel> mListObject;
    Context context;

    public DepartmentsDataAdapter(Context context, int resource, ArrayList<DepartmentsDataModel> mListObject) {
        super(context, resource, mListObject);
        this.context = context;
        this.mListObject = mListObject;
    }

    public ArrayList<DepartmentsDataModel> getmListObject() {
        return mListObject;
    }

    public void setmListObject(ArrayList<DepartmentsDataModel> mListObject) {
        this.mListObject = mListObject;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        CheckedTextView status;
        TextView txtList;
        DepartmentsDataModel item = mListObject.get(position);
        view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        status = view.findViewById(android.R.id.text1);
        status.setText(item.getLocNameAr());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getPosition(@Nullable DepartmentsDataModel item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return mListObject.size();
    }
}