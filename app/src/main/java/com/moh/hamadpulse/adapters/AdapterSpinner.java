package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ExternalDrugsModel;
import com.moh.hamadpulse.models.GetMedDoseConst;
import com.moh.hamadpulse.models.GetMedRouteConst;
import com.moh.hamadpulse.models.GetMedicineConst;
import com.moh.hamadpulse.models.MasterClinicModel;
import com.moh.hamadpulse.models.SubClinicModel;

import java.util.ArrayList;

public class AdapterSpinner extends ArrayAdapter<Object> {

    ArrayList<Object> mListObject;
    Context context;

    public AdapterSpinner(Context context, int resource,  ArrayList<Object> mListObject) {
        super(context, resource, mListObject);
        this.context = context;
        this.mListObject = mListObject;
    }

    public ArrayList<Object> getmListObject() {
        return mListObject;
    }

    public void setmListObject(ArrayList<Object> mListObject) {
        this.mListObject = mListObject;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        CheckedTextView status;
        TextView txtList;
        Object item = mListObject.get(position);
        if (item instanceof GetMedRouteConst) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            status = view.findViewById(android.R.id.text1);
            status.setText(((GetMedRouteConst) item).getDOSGNAME());
        } else if (item instanceof GetMedDoseConst) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            status = view.findViewById(android.R.id.text1);
            status.setText(((GetMedDoseConst) item).getDOSENAME());
        } else if (item instanceof GetMedicineConst) {
            GetMedicineConst model = ((GetMedicineConst) item);
            view = LayoutInflater.from(context).inflate(R.layout.medicine_const_layout, parent, false);
            ImageView img_available_status = view.findViewById(R.id.img_available_status);
            if (model.getAVAILABLEBAL() != null)
                img_available_status.setImageResource(Integer.parseInt(model.getAVAILABLEBAL()) > 0 ? R.drawable.available_medicine :
                        R.drawable.no_available_medicine);
            txtList = view.findViewById(R.id.txt_medicine);
            txtList.setText(model.getITEMNAME());
        } else if (item instanceof ExternalDrugsModel) {
            ExternalDrugsModel model = ((ExternalDrugsModel) item);
            view = LayoutInflater.from(context).inflate(R.layout.medicine_const_layout, parent, false);
            ImageView img_available_status = view.findViewById(R.id.img_available_status);
            if (model.getAvailableBal() != null)
                img_available_status.setImageResource(Integer.parseInt(model.getAvailableBal()) > 0 ? R.drawable.available_medicine :
                        R.drawable.no_available_medicine);
            txtList = view.findViewById(R.id.txt_medicine);
            txtList.setText(model.getItemName());
        } else if (item instanceof MasterClinicModel) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            status = view.findViewById(android.R.id.text1);
            status.setText(((MasterClinicModel) item).getClinicName());
        } else if (item instanceof SubClinicModel) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            status = view.findViewById(android.R.id.text1);
            status.setText(((SubClinicModel) item).getlOC_NAME_AR());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return mListObject.size();
    }
}
