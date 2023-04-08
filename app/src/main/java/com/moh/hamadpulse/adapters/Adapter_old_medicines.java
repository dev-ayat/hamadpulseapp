package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.DocPharmacyDetails;

import java.util.ArrayList;

public class Adapter_old_medicines extends RecyclerView.Adapter<Adapter_old_medicines.OldMedicineViewHolder> {
    ArrayList<DocPharmacyDetails> old_medicines_list;

    public Adapter_old_medicines(ArrayList<DocPharmacyDetails> old_medicines_list) {
        this.old_medicines_list = old_medicines_list;
    }

    public ArrayList<DocPharmacyDetails> getOld_medicines_list() {
        return old_medicines_list;
    }

    public void setOld_medicines_list(ArrayList<DocPharmacyDetails> old_medicines_list) {
        this.old_medicines_list = old_medicines_list;
    }

    @NonNull
    @Override
    public OldMedicineViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OldMedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_old_medicine, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull OldMedicineViewHolder holder, int position) {
        DocPharmacyDetails item = old_medicines_list.get(position);
        holder.txtItemName.setText(item.getITEMNAME());
        holder.txtInterval.setText(item.getINPINTERVAL());
        holder.txtRoute.setText(item.getDOSGNAME());
        holder.txtDosage.setText(item.getDOSENAME());
        holder.txtTotal.setText(item.getINPWANTEDAMOUNT());
//        holder.txtNote.setText(item.get);
        holder.select_CB.setChecked(item.isSelected());
        holder.select_CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setSelected(new Boolean(b));
            }
        });
    }

    @Override
    public int getItemCount() {
        return old_medicines_list.size();
    }

    class OldMedicineViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemName, txtInterval, txtDosage, txtRoute, txtTotal, txtNote;
        private CheckBox select_CB;

        public OldMedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtInterval = itemView.findViewById(R.id.txtInterval);
            txtDosage = itemView.findViewById(R.id.txtDosage);
            txtRoute = itemView.findViewById(R.id.txtRoute);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtNote = itemView.findViewById(R.id.txtNote);
            select_CB = itemView.findViewById(R.id.check_box_select);
        }
    }
}
