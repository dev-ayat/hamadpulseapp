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
import com.moh.hamadpulse.models.GetVentilationForPatient;

import java.util.ArrayList;


public class VentilationPatientAdapter extends RecyclerView.Adapter<VentilationPatientAdapter.ViewHolder> {
    Context context;
    ArrayList<GetVentilationForPatient> ventilationArray;

    public VentilationPatientAdapter(Context context, ArrayList<GetVentilationForPatient> ventilationArray) {
        this.context = context;
        this.ventilationArray = ventilationArray;
        Log.e("signsarray", "size=" + ventilationArray.size());
    }

    @NonNull
    @Override
    public VentilationPatientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_vantelation_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VentilationPatientAdapter.ViewHolder holder, final int position) {
        final GetVentilationForPatient Ventilation = ventilationArray.get(position);
        //holder.ventserial.setText(Ventilation.getvENTSERIAL());
        holder.date.setText(Ventilation.getVENADMDATE());
        holder.ventType.setText(Ventilation.getVENTYPENAME());
        holder.VtCC.setText(Ventilation.getVENADMVTCC());
        holder.RR.setText(Ventilation.getVENADMRR());
        holder.Fio2.setText(Ventilation.getVENADMFIO2());
        holder.IE.setText(Ventilation.getVENADMIE());
        holder.PEEP.setText(Ventilation.getVENADMPEEP());

    }

    @Override
    public int getItemCount() {
        return ventilationArray.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ventserial, date, ventType, VtCC, RR, Fio2, IE, PEEP;

        public ViewHolder(View itemView) {
            super(itemView);
            ventserial = (TextView) itemView.findViewById(R.id.txt_ventserial);
            date = (TextView) itemView.findViewById(R.id.txt_date);
            ventType = (TextView) itemView.findViewById(R.id.txt_ventType);
            VtCC = (TextView) itemView.findViewById(R.id.txt_VtCC);
            RR = (TextView) itemView.findViewById(R.id.txt_RR);
            Fio2 = (TextView) itemView.findViewById(R.id.txt_Fio2);
            IE = (TextView) itemView.findViewById(R.id.txt_IE);
            PEEP = (TextView) itemView.findViewById(R.id.txt_PEEP);
        }
    }
}
