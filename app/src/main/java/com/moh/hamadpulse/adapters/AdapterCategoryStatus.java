package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.CategoryStatusTempModel;
import com.moh.hamadpulse.models.CenterLabCategoryModel;

import java.util.ArrayList;

public class AdapterCategoryStatus extends RecyclerView.Adapter<AdapterCategoryStatus.LabCategoryViewHoleder> {
    ArrayList<CategoryStatusTempModel> LabCategoryList = new ArrayList<>();
    ArrayList<CenterLabCategoryModel> centerLabCategoryModels = new ArrayList<>();
    Object model;
    boolean flag = false;
    LaborderCardviewAdapter.InterfaceLabAdapterClick mInterfaceLabAdapterClick;

    public AdapterCategoryStatus(ArrayList<CategoryStatusTempModel> LabCategoryList, Object model,
                                 LaborderCardviewAdapter.InterfaceLabAdapterClick mInterfaceLabAdapterClick) {
        this.LabCategoryList = LabCategoryList;
        this.mInterfaceLabAdapterClick = mInterfaceLabAdapterClick;
        flag = true;
        this.model = model;
        Log.d("test", LabCategoryList.size() + " in Adapter");
    }

    public AdapterCategoryStatus(Object model, LaborderCardviewAdapter.InterfaceLabAdapterClick mInterfaceLabAdapterClick) {
        this.model = model;
        this.mInterfaceLabAdapterClick = mInterfaceLabAdapterClick;
    }

    public ArrayList<CenterLabCategoryModel> getCenterLabCategoryModels() {
        return centerLabCategoryModels;
    }

    public void setLabCategoryList(ArrayList<CategoryStatusTempModel> LabCategoryList) {
        this.LabCategoryList = LabCategoryList;
        flag = true;
    }

    public void setCenterLabCategoryModels(ArrayList<CenterLabCategoryModel> centerLabCategoryModels) {
        this.centerLabCategoryModels = centerLabCategoryModels;
    }

    public ArrayList<CategoryStatusTempModel> getLabCategoryList() {
        return LabCategoryList;
    }


    @NonNull
    @Override
    public LabCategoryViewHoleder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabCategoryViewHoleder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_lab_result_master, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabCategoryViewHoleder holder, int position) {

        if (flag) {
            CategoryStatusTempModel model = LabCategoryList.get(position);
            TextView txt_status = holder.txt_status;
            Context cx = txt_status.getContext();
            String status = model.getStatus_number().trim();
            holder.txt_cat.setText(model.getCategory());
            txt_status.setText(model.getStatus().trim());
            txt_status.setBackgroundColor(
                    cx.getResources().getColor(
                            status.equals("3") ? R.color.colorReady :
                                    status.equals("0") ? R.color.colorWaiting :
                                            status.equals("4") ? R.color.colorCancel :
                                                    R.color.viewBg
                    ));
            if (!(status.equals("0") || status.equals("3") || status.equals("4"))) {
                txt_status.setTextColor(cx.getResources().getColor(R.color.black));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(status.equals("3"))
                    mInterfaceLabAdapterClick.onLabClick(model, position);
                }
            });
        } else {
            CenterLabCategoryModel model = centerLabCategoryModels.get(position);
            TextView txt_status = holder.txt_status;
            Context cx = txt_status.getContext();
            String status_number = model.getCategoryStatusNumber().trim();
            holder.txt_cat.setText(model.getCategoryName());
            txt_status.setText(model.getCategoryStatus().trim());
            txt_status.setBackgroundColor(
                    cx.getResources().getColor(
                            status_number.equals("2") ? R.color.colorReady :
                                    status_number.equals("1") ? R.color.colorWaiting :
                                            status_number.equals("0") ? R.color.colorCancel :
                                                    R.color.viewBg
                    ));
            if (!(status_number.equals("0") || status_number.equals("1") || status_number.equals("2"))) {
                txt_status.setTextColor(cx.getResources().getColor(R.color.black));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(status_number.equals("2"))
                        mInterfaceLabAdapterClick.onLabClick(model, position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return flag ? LabCategoryList.size() : centerLabCategoryModels.size();
    }

    class LabCategoryViewHoleder extends RecyclerView.ViewHolder {
        TextView txt_cat, txt_status;

        public LabCategoryViewHoleder(@NonNull View itemView) {
            super(itemView);
            txt_cat = itemView.findViewById(R.id.txtCategory);
            txt_status = itemView.findViewById(R.id.txtStatus);
        }
    }
}
