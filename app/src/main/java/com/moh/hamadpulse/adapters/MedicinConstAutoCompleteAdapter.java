//package com.moh.pulsegh.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.moh.pulsegh.models.GetMedicineConst;
//
//import java.util.ArrayList;
//
//public class MedicinConstAutoCompleteAdapter extends ArrayAdapter<GetMedicineConst> {
//
//    ArrayList<GetMedicineConst> getMedicineConstArrayList;
//    Context context;
//
//    public MedicinConstAutoCompleteAdapter(Context context, int resource, ArrayList<GetMedicineConst> getMedicineConstArrayList) {
//        super(context, resource, getMedicineConstArrayList);
//        this.context = context;
//        this.getMedicineConstArrayList = (ArrayList) getMedicineConstArrayList;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
//        TextView MedicinName = (TextView) view.findViewById(android.R.id.text1);
//        MedicinName.setText(getMedicineConstArrayList.get(position).getITEMNAME());
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
//        return getMedicineConstArrayList.size();
//    }
//}
