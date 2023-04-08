package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ReferralPhysicalTherapyModel;

import java.util.ArrayList;

public class PhysicalTherapyReferralAdapter extends RecyclerView.Adapter<PhysicalTherapyReferralAdapter.PhysicalTherapyReferralViewHolder> {
    ArrayList<ReferralPhysicalTherapyModel> list;

    public PhysicalTherapyReferralAdapter(ArrayList<ReferralPhysicalTherapyModel> list) {
        this.list = list;
    }

    public ArrayList<ReferralPhysicalTherapyModel> getList() {
        return list;
    }

    public void setList(ArrayList<ReferralPhysicalTherapyModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PhysicalTherapyReferralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhysicalTherapyReferralAdapter.PhysicalTherapyReferralViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_referral_physical_therapy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhysicalTherapyReferralViewHolder holder, int position) {
        ReferralPhysicalTherapyModel model = list.get(position);
        holder.txtcd.setText(model.getReferral_cd());
        holder.txtdig.setText(model.getRM_DIAGNOSIS());
        holder.txtcoll.setText(model.getRM_DEAR_COLLEAGUE());
        holder.txtper.setText(model.getRM_PRECAUTIONS());
        holder.txtdate.setText(model.getRM_CREATED_ON());
        holder.txtname.setText(model.getFull_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PhysicalTherapyReferralViewHolder extends RecyclerView.ViewHolder {
        TextView txtcd, txtdig, txtcoll, txtper, txtdate, txtname;

        public PhysicalTherapyReferralViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcd = itemView.findViewById(R.id.txtcd);
            txtdig = itemView.findViewById(R.id.txtdig);
            txtcoll = itemView.findViewById(R.id.txtcoll);
            txtper = itemView.findViewById(R.id.txtper);
            txtdate = itemView.findViewById(R.id.txtdate);
            txtname = itemView.findViewById(R.id.txtname);
        }
    }
}
