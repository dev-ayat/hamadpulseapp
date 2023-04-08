package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.InOutMasterModel;

import java.util.ArrayList;

public class AdapterTakeInOutMaster extends RecyclerView.Adapter<AdapterTakeInOutMaster.TakeInOutViewHolder> {
    ArrayList<InOutMasterModel> inOutMasterModelArrayList = new ArrayList<>();

    public AdapterTakeInOutMaster(ArrayList<InOutMasterModel> inOutMasterModelArrayList) {
        this.inOutMasterModelArrayList = inOutMasterModelArrayList;
    }

    @NonNull
    @Override
    public TakeInOutViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TakeInOutViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_take_in_out, parent, false));

    }

    public ArrayList<InOutMasterModel> getInOutMasterModelArrayList() {
        return inOutMasterModelArrayList;
    }

    public void setInOutMasterModelArrayList(ArrayList<InOutMasterModel> inOutMasterModelArrayList) {
        this.inOutMasterModelArrayList = inOutMasterModelArrayList;
    }


    @Override
    public void onBindViewHolder(@NonNull TakeInOutViewHolder holder, int position) {
        InOutMasterModel in = inOutMasterModelArrayList.get(position);
        RecyclerView details_RV = holder.detials_RV;
        LinearLayout details_layout = holder.details_layout;
        ImageButton btnShow_details = holder.btnShow_details;
        holder.txtDate_Time_details.setText(in.getInpInOutCreatedOn());
        holder.txtInTake.setText(in.getTotals().getInTotals());
        holder.txtOutPut.setText(in.getTotals().getOutTotals());
        //to show and hide details RV
        btnShow_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set visibility of layout
                int visibility = details_layout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                details_layout.setVisibility(visibility);
                //set arrow direction depending on visibility of layout
                btnShow_details.setBackgroundResource(visibility == 0 ?
                        R.drawable.arrow_up : R.drawable.arrow_down);
            }
        });
        //Prepare process of viewing data in details RV
        AdapterInOutDetails adapter = new AdapterInOutDetails
                (in.getInOutDetailsCur());
        details_RV.setHasFixedSize(true);
        details_RV.setAdapter(adapter);
//      get context from any component
        details_RV.setLayoutManager
                (new LinearLayoutManager(details_RV.getContext()));
    }

    @Override
    public int getItemCount() {
        return inOutMasterModelArrayList.size();
    }

    class TakeInOutViewHolder extends RecyclerView.ViewHolder {
        LinearLayout details_layout;
        RecyclerView detials_RV;
        TextView txtDate_Time_details, txtInTake, txtOutPut;
        ImageButton btnShow_details;

        public TakeInOutViewHolder(@NonNull View itemView) {
            super(itemView);
            detials_RV = itemView.findViewById(R.id.details_RV);
            txtDate_Time_details = itemView.findViewById
                    (R.id.txtDateTimeDetails);
            txtInTake = itemView.findViewById(R.id.txtInTake);
            txtOutPut = itemView.findViewById(R.id.txtOutPut);
            btnShow_details = itemView.findViewById(R.id.btnShow_Details);
            details_layout = itemView.findViewById(R.id.details_layout);

        }
    }
}
