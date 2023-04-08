package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.AdmissionDataFragment;
import com.moh.hamadpulse.models.AdmissionHistoryModel;

import java.util.ArrayList;

public class AdmissionHistoryAdapter extends RecyclerView.Adapter<AdmissionHistoryAdapter.AdmissionHistoryViewHolder> {

    ArrayList<AdmissionHistoryModel> list=new ArrayList<>();
    ActivityPatient ap;
    public AdmissionHistoryAdapter(ArrayList<AdmissionHistoryModel> list, ActivityPatient ap) {
        this.list = list;
        this.ap=ap;
    }

    public ArrayList<AdmissionHistoryModel> getList() {
        return list;
    }

    public void setList(ArrayList<AdmissionHistoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdmissionHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdmissionHistoryViewHolder((LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_admssion_history, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull AdmissionHistoryViewHolder holder, int position) {
          AdmissionHistoryModel model=list.get(position);
          holder.adm_cd_tv.setText(model.getADMISSION_CODE());
          holder.loc_name_tv.setText(model.getLOC_NAME());
          holder.time_in_tv.setText(model.getTIME_IN());
          holder.time_out_tv.setText(model.getTIME_OUT());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
//                  AdmissionDataFragment dialog=new AdmissionDataFragment(model);
//                  FragmentTransaction ft = ((AppCompatActivity)holder.itemView.getContext()).getSupportFragmentManager().beginTransaction();
//                  dialog.show(ft, "FragmentPharmDetails");
                   ap.CallFragment(new AdmissionDataFragment(model));
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AdmissionHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView time_out_tv,time_in_tv,loc_name_tv,adm_cd_tv;
        public AdmissionHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            time_out_tv=itemView.findViewById(R.id.time_out_tv);
            time_in_tv=itemView.findViewById(R.id.time_in_tv);
            loc_name_tv=itemView.findViewById(R.id.loc_name_tv);
            adm_cd_tv=itemView.findViewById(R.id.adm_cd_tv);
        }
    }
}
