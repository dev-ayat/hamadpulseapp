package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.DocPharmacyDetails;

import java.util.ArrayList;

public class AdmissionDrugsAdapter extends RecyclerView.Adapter<AdmissionDrugsAdapter.AdmissionDrugsViewHolder> {

    private ArrayList<DocPharmacyDetails> mListDocPharmacyDetails;

    public AdmissionDrugsAdapter(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails) {
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
    }

    public ArrayList<DocPharmacyDetails> getmListDocPharmacyDetails() {
        return mListDocPharmacyDetails;
    }

    public void setmListDocPharmacyDetails(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails) {
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
    }

    @NonNull
    @Override
    public AdmissionDrugsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdmissionDrugsAdapter.AdmissionDrugsViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_admission_drugs, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdmissionDrugsViewHolder holder, int position) {
        DocPharmacyDetails model = mListDocPharmacyDetails.get(position);
        holder.txti_Drug.setText(model.getITEMNAME());
        holder.Repeat_TV.setText(model.getDOSENAME());
        holder.txti_drug_dose.setText(model.getDose_value());
        holder.txti_Duration.setText(model.getINPINTERVAL());
        holder.txti_Quantity.setText(model.getINPWANTEDAMOUNT());
        holder.DosageFormTv.setText(model.getUNITNAME());
    }

    @Override
    public int getItemCount() {
        return mListDocPharmacyDetails.size();
    }


    class AdmissionDrugsViewHolder extends RecyclerView.ViewHolder {
        TextView txti_Drug, DosageFormTv, txti_show_notes, Repeat_TV, txti_Duration, txti_Quantity, txti_drug_dose;

        public AdmissionDrugsViewHolder(@NonNull View itemView) {
            super(itemView);
            txti_Drug = itemView.findViewById(R.id.txti_Drug);
            DosageFormTv = itemView.findViewById(R.id.DosageFormTv);
            txti_show_notes = itemView.findViewById(R.id.txti_show_notes);
            txti_Duration = itemView.findViewById(R.id.txti_Duration);
            txti_Quantity = itemView.findViewById(R.id.txti_Quantity);
            txti_drug_dose = itemView.findViewById(R.id.txti_drug_dose);
            Repeat_TV = itemView.findViewById(R.id.Repeat_TV);
        }
    }
}
