package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.test;

import java.util.List;

public class AdapterReportCorona extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<test.ReportVal> mListGROUP;
    Context cx;
    boolean isCheckBox;

    public AdapterReportCorona(List<test.ReportVal> mListGROUP,boolean isCheckBox) {
        this.mListGROUP = mListGROUP;
        this.isCheckBox = isCheckBox;
    }

    public List<test.ReportVal> getmListGROUP() {
        return mListGROUP;
    }

    public void setmListGROUP(List<test.ReportVal> mListGROUP) {
        this.mListGROUP = mListGROUP;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(isCheckBox) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_corona_ch, parent, false);
            return new HolderReportCoronaCh(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_corona, parent, false);
            return new HolderReportCorona(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final test.ReportVal mReportVal = mListGROUP.get(position);
        cx = holder.itemView.getContext();
        if (isCheckBox)
        {
            HolderReportCoronaCh mHolderReportCoronaCh = (HolderReportCoronaCh) holder;
            mHolderReportCoronaCh.chReportSummay.setChecked(mReportVal.getValue().equals("0")?false:true);
            mHolderReportCoronaCh.chReportSummay.setText(mReportVal.getLbl());
            //mHolderReportCoronaCh.chReportSummay.setTextColor(mReportVal.getValue().equals("0")?Color.BLACK:Color.RED);

        }
        else {
            HolderReportCorona mHolderReportCorona = (HolderReportCorona) holder;
            mHolderReportCorona.txtLBL.setText(mReportVal.getLbl());
            mHolderReportCorona.txtValue.setText(mReportVal.getValue().equals("0") ? "No" : "Yes");
        }
    }

    @Override
    public int getItemCount() {
        return mListGROUP.size();
    }

    public class HolderReportCorona extends RecyclerView.ViewHolder {
        //View subItem;
        TextView txtLBL;
        TextView txtValue;
        public HolderReportCorona(View itemView) {
            super(itemView);
            txtLBL=itemView.findViewById(R.id.txtLBL);
            txtValue=itemView.findViewById(R.id.txtValue);
        }
    }
    public class HolderReportCoronaCh extends RecyclerView.ViewHolder {
        //View subItem;
        CheckBox chReportSummay;
        public HolderReportCoronaCh(View itemView) {
            super(itemView);
            chReportSummay=itemView.findViewById(R.id.chReportSummay);
        }
    }
}