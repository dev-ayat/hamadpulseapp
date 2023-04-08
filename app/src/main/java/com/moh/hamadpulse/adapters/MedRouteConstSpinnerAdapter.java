//package com.moh.pulsegh.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.CheckedTextView;
//
//import com.moh.pulsegh.models.GetMedRouteConst;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedRouteConstSpinnerAdapter extends ArrayAdapter<GetMedRouteConst> {
//
//    ArrayList<GetMedRouteConst> medRouteConsts;
//    Context context;
//
//    public MedRouteConstSpinnerAdapter(Context context, int resource, List<GetMedRouteConst> medRouteConsts) {
//        super(context, resource, medRouteConsts);
//        this.context = context;
//        this.medRouteConsts = (ArrayList) medRouteConsts;
//    }
//
//    public ArrayList<GetMedRouteConst> getMedRouteConsts() {
//        return medRouteConsts;
//    }
//
//    public void setMedRouteConsts(ArrayList<GetMedRouteConst> medRouteConsts) {
//        this.medRouteConsts = medRouteConsts;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
//        CheckedTextView status = (CheckedTextView) view.findViewById(android.R.id.text1);
//        status.setText(medRouteConsts.get(position).getDOSGNAME());
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
//        return medRouteConsts.size();
//    }
//}
