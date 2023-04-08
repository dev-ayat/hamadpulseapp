//package com.moh.pulsegh.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.CheckedTextView;
//
//import com.moh.pulsegh.models.GetMedDoseConst;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DosageConstSpinnerAdapter extends ArrayAdapter<GetMedDoseConst> {
//
//    ArrayList<GetMedDoseConst> MedDoseList;
//    Context context;
//
//    public DosageConstSpinnerAdapter(Context context, int resource, List<GetMedDoseConst> MedDoseList) {
//        super(context, resource, MedDoseList);
//        this.context = context;
//        this.MedDoseList = (ArrayList) MedDoseList;
//    }
//
//    public ArrayList<GetMedDoseConst> getMedDoseList() {
//        return MedDoseList;
//    }
//
//    public void setMedDoseList(ArrayList<GetMedDoseConst> medDoseList) {
//        MedDoseList = medDoseList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
//        CheckedTextView status = (CheckedTextView) view.findViewById(android.R.id.text1);
//        status.setText(MedDoseList.get(position).getDOSENAME());
//
//        return view;
//    }
//
//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        return getView(position, convertView, parent);
//    }
//
//    @Override
//    public int getCount() {
//        return MedDoseList.size();
//    }
//}
