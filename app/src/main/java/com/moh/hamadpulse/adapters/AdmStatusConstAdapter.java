package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.AdmPatientConst;

import java.util.ArrayList;


public class AdmStatusConstAdapter extends RecyclerView.Adapter<AdmStatusConstAdapter.ViewHolder> {
    public String statuscdselected;
    Context context;
    ArrayList<AdmPatientConst> admPatientConstsArray;
    patientStatusInterface myStatusInterface;
    private int lastSelectedPosition = -1;


    public AdmStatusConstAdapter(Context context, ArrayList<AdmPatientConst> admPatientConstsArray, patientStatusInterface myStatusInterface) {
        this.context = context;
        this.admPatientConstsArray = admPatientConstsArray;
        this.myStatusInterface = myStatusInterface;
        Log.e("signsarray", "size=" + admPatientConstsArray.size());
    }

    public AdmStatusConstAdapter(Context context, ArrayList<AdmPatientConst> admPatientConstsArray) {
        this.context = context;
        this.admPatientConstsArray = admPatientConstsArray;
        Log.e("signsarray", "size=" + admPatientConstsArray.size());
    }

    @NonNull
    @Override
    public AdmStatusConstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_status_list_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdmStatusConstAdapter.ViewHolder holder, final int position) {
        holder.statuscd.setText(admPatientConstsArray.get(position).getPATIENTSTATUSID());
        holder.statusnameradio.setText(admPatientConstsArray.get(position).getPATIENTSTATUSNAMEEN());
        holder.statusnameradio.setChecked(true);
        statuscdselected = admPatientConstsArray.get(position).getPATIENTSTATUSID();
        holder.statusnameradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                myStatusInterface.onstatusClick(statuscdselected);
            }
        });




    }

    @Override
    public int getItemCount() {
        return admPatientConstsArray.size();

    }

    public interface patientStatusInterface {
        void onstatusClick(String statuscdselected);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView statuscd;
        RadioButton statusnameradio;

        public ViewHolder(View itemView) {
            super(itemView);
            statuscd = (TextView) itemView.findViewById(R.id.txtstatuscd);
            statusnameradio = itemView.findViewById(R.id.statusnameradio);


        }
    }

}
