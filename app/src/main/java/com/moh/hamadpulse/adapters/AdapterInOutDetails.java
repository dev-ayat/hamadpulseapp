package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.InOutDetailsCurModel;

import java.util.ArrayList;

public class AdapterInOutDetails extends RecyclerView.Adapter<AdapterInOutDetails.InOutDetailsViewHolder> {
    ArrayList<InOutDetailsCurModel> al = new ArrayList<>();

    public AdapterInOutDetails(ArrayList<InOutDetailsCurModel> al) {
        this.al = al;
    }

    @NonNull
    @Override
    public InOutDetailsViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InOutDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_take_in_out_details, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InOutDetailsViewHolder holder, int position) {
        InOutDetailsCurModel item = al.get(position);
        holder.txtIv_Fluid.setText(item.getInpInOutIvFluid());
        holder.txtOral.setText(item.getInpInOutOral());
        // holder.txtDrains.setText(item.getInpInOutDrains());
        holder.txtNGT.setText(item.getInpInOutNgt());
        holder.txtVomiting.setText(item.getInpInOutVomtting());
        holder.txtUrine.setText(item.getInpInOutUrine());
        // holder.txtChestTube_R.setText(item.getInpInOutChR());
        //  holder.txtChestTube_L.setText(item.getInpInOutChL());
        holder.txtDate_time.setText(item.getInpInOutCreatedOn());
        holder.txtSign.setText(item.getUserFullName());
        holder.in_out_note.setText(item.getNOTE());
        holder.txt_vol.setText(item.getINP_IN_OUT_VOL());
//       holder.txtstarted.setText(item.getINP_IN_OUT_STARTED());
//      holder.txtfinished.setText(item.getINP_IN_OUT_FINISHED());
        holder.txti_gastro.setText(item.getINP_IN_OUT_GASTRO());
        holder.txtresedual.setText(item.getINP_IN_OUT_RESIDUAL());

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    class InOutDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView txtIv_Fluid, txtOral, txtDrains, txtNGT, txtVomiting, txtUrine, txtChestTube_R, txtChestTube_L,
                txtDate_time, txtSign, in_out_note, txt_vol, txtstarted, txtfinished, txti_gastro, txtresedual;

        public InOutDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIv_Fluid = itemView.findViewById(R.id.txtIv_Fluid);
            txtOral = itemView.findViewById(R.id.txtOral);
            // txtDrains = itemView.findViewById(R.id.txtDrains);
            txtNGT = itemView.findViewById(R.id.txtNGT);
            txtVomiting = itemView.findViewById(R.id.txtVomiting);
            txtUrine = itemView.findViewById(R.id.txtUrine);
            // txtChestTube_R = itemView.findViewById(R.id.txtChestTube_R);
            //  txtChestTube_L = itemView.findViewById(R.id.txtChestTube_L);
            txtSign = itemView.findViewById(R.id.txtSign);
            txtDate_time = itemView.findViewById(R.id.txtDateTime_Details);
            in_out_note = itemView.findViewById(R.id.in_out_note);
            txt_vol = itemView.findViewById(R.id.txtvol);
//            txtstarted = itemView.findViewById(R.id.txt_started);
//            txtfinished = itemView.findViewById(R.id.txt_finished);
            txti_gastro = itemView.findViewById(R.id.txt_gastro);
            txtresedual = itemView.findViewById(R.id.txt_residual);

        }
    }
}
