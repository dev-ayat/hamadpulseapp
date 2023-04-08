package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.moh.hamadpulse.models.GetDocumentsTypeConst;

import java.util.ArrayList;

public class DocTyesConstSpinnerAdapter extends ArrayAdapter<GetDocumentsTypeConst> {

    ArrayList<GetDocumentsTypeConst> DocumentsTypeConstArrayList;
    Context context;

    public DocTyesConstSpinnerAdapter(Context context, int resource, ArrayList<GetDocumentsTypeConst> DocumentsTypeConstArrayList) {
        super(context, resource, DocumentsTypeConstArrayList);
        this.context = context;
        this.DocumentsTypeConstArrayList = (ArrayList) DocumentsTypeConstArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView VentMode = (CheckedTextView) view.findViewById(android.R.id.text1);
        VentMode.setText(DocumentsTypeConstArrayList.get(position).getDOCTYPENAME());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return DocumentsTypeConstArrayList.size();
    }
}
