package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.VentilationModeModel;

import java.util.ArrayList;

public class VentilationModeAdapter extends RecyclerView.Adapter<VentilationModeAdapter.ViewHolder> {
    Context context;
    ArrayList<VentilationModeModel> models;

    public VentilationModeAdapter(Context context, ArrayList<VentilationModeModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.o2_mode_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        VentilationModeModel model = models.get(position);
        holder.delete_btn.setVisibility(View.GONE);
//            holder.note_serial.setText(PatientNote.getdOCNURSERIAL());
        holder.note_date.setText(model.getInpNpCreatedOn());
        holder.patient_status.setText(model.getDeliveryNameEn());
        holder.note_body.setText(model.getInpNpNote());
        holder.Doctor_name.setText(model.getUserFullName());
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String serial = PatientNote.getdOCNURSERIAL();
//                Log.e("serial", "serial" + serial);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return models.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_serial, note_date, patient_status, note_body, Doctor_name;
        ImageButton delete_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            note_date = (TextView) itemView.findViewById(R.id.note_date);
            patient_status = (TextView) itemView.findViewById(R.id.patient_status);
            note_body = (TextView) itemView.findViewById(R.id.note_body);
            note_serial = (TextView) itemView.findViewById(R.id.note_serial);
            Doctor_name = (TextView) itemView.findViewById(R.id.Doctor_name);
            delete_btn = itemView.findViewById(R.id.delete_btn);

        }
    }


}


