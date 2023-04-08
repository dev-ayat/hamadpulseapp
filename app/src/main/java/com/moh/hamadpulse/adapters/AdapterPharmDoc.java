package com.moh.hamadpulse.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.DocPharmacy;

import java.util.ArrayList;

public class AdapterPharmDoc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<DocPharmacy> mListDocPharmacy;
    OnAdapterClick mOnAdapterClick;

    public AdapterPharmDoc(ArrayList<DocPharmacy> mListDocPharmacy) {
        this.mListDocPharmacy = mListDocPharmacy;
    }

    public ArrayList<DocPharmacy> getmListDocPharmacy() {
        return mListDocPharmacy;
    }

    public void setmListDocPharmacy(ArrayList<DocPharmacy> mListDocPharmacy) {
        this.mListDocPharmacy = mListDocPharmacy;
        notifyDataSetChanged();
    }

    public OnAdapterClick getmOnAdapterClick() {
        return mOnAdapterClick;
    }

    public void setmOnAdapterClick(OnAdapterClick mOnAdapterClick) {
        this.mOnAdapterClick = mOnAdapterClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pharm_doc, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.e("test", position + "");
        MyViewHolder mMyViewHolder = (MyViewHolder) holder;
        DocPharmacy model = mListDocPharmacy.get(position);
        mMyViewHolder.txtPharmDocument.setText(model.getiNPPHARMCODE());
        mMyViewHolder.txtPharmDate.setText(model.getFormattedDateTime());//Controller.getRemain()
        mMyViewHolder.txtPharmDoctor.setText(model.getdOCNAME());
        mMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAdapterClick.myClick(mListDocPharmacy.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListDocPharmacy.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPharmDocument,txtPharmDate,txtPharmDoctor;
        public MyViewHolder(View view) {
            super(view);
            txtPharmDocument =  view.findViewById(R.id.txtPharmDocument);
            txtPharmDate =  view.findViewById(R.id.txtPharmDate);
            txtPharmDoctor =  view.findViewById(R.id.txtPharmDoctor);
        }

    }
}
