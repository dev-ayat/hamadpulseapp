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
import com.moh.hamadpulse.models.VitalSigns;

import java.util.ArrayList;


public class VitalSignsAdapter extends RecyclerView.Adapter<VitalSignsAdapter.ViewHolder> {
    Context context;
    ArrayList<VitalSigns> vitalsignsArrayList;

    public VitalSignsAdapter(Context context, ArrayList<VitalSigns> vitalsignsArrayList) {
        this.context = context;
        this.vitalsignsArrayList = vitalsignsArrayList;
        Log.e("signsarray", "size=" + vitalsignsArrayList.size());
    }

    @NonNull
    @Override
    public VitalSignsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vital_signs_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VitalSignsAdapter.ViewHolder holder, final int position) {
        holder.vital_sign_serial.setText(vitalsignsArrayList.get(position).getNURSINGPROCEDURESCODE());
        holder.Temperature.setText(vitalsignsArrayList.get(position).getHEAT());
        holder.prusser.setText(vitalsignsArrayList.get(position).getPRESSURE());
        holder.pulse.setText(vitalsignsArrayList.get(position).getPULSE());
        holder.Respiratory.setText(vitalsignsArrayList.get(position).getRESPIRATORYRATE());
        holder.resultdate.setText(vitalsignsArrayList.get(position).getCREATEDDATE());

    }

    @Override
    public int getItemCount() {
        return vitalsignsArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vital_sign_serial, Temperature, prusser, pulse, Respiratory, resultdate;
        //ImageView deletevitalsigns;

        public ViewHolder(View itemView) {
            super(itemView);
//            vital_sign_serial = (TextView) itemView.findViewById(R.id.txt_vitalsignserial);
//            Temperature = (TextView) itemView.findViewById(R.id.txt_Temperature);
//            prusser = (TextView) itemView.findViewById(R.id.txt_prusser);
//            pulse = (TextView) itemView.findViewById(R.id.txt_pulse);
//            Respiratory = (TextView) itemView.findViewById(R.id.txt_Respiratory);
//            resultdate = (TextView) itemView.findViewById(R.id.txt_resultdate);
//            //  deletevitalsigns = (ImageView) itemView.findViewById(R.id.deletevitalsigns);

        }
    }
}
