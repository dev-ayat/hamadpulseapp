package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.LabSampleModel;

import java.util.ArrayList;

public class ViewSamplesAdapter extends RecyclerView.Adapter<ViewSamplesAdapter.ViewSamplesViewHolder> {
    ArrayList<LabSampleModel> samples = new ArrayList<>();

    public ViewSamplesAdapter(ArrayList<LabSampleModel> samples) {
        this.samples = samples;
    }

    public ArrayList<LabSampleModel> getsamples() {
        return samples;
    }

    public void setsamples(ArrayList<LabSampleModel> samples) {
        this.samples = samples;
    }

    @NonNull
    @Override
    public ViewSamplesViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewSamplesViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_view_sampling, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewSamplesViewHolder holder, int position) {
        LabSampleModel model = samples.get(position);
        holder.txtOrderNumber.setText(model.getOrderCd());
        holder.txtSamplingDate.setText(model.getOrderRequestDate());
        holder.txtCategory.setText(model.getCategoryName());
        holder.txtGroup.setText(model.getGroupNameEn());
        holder.status_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                model.setDSamplingStatus(i + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    class ViewSamplesViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderNumber, txtSamplingDate, txtCategory, txtGroup;
        Spinner status_sp;

        public ViewSamplesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderNumber = itemView.findViewById(R.id.txtOrderNumber);
            txtSamplingDate = itemView.findViewById(R.id.txtSamplingDate);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtGroup = itemView.findViewById(R.id.txtGroup);
            status_sp = itemView.findViewById(R.id.status_sp);
        }
    }
}
