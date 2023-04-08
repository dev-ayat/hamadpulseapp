package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.GetNurseMedExecution;

import java.util.ArrayList;


public class NuserMedEXecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    String Status;
    int Serial;
    private ArrayList<GetNurseMedExecution> NurseMedExecutionList;
    private Context context;
    private Controller mcontroller;
    public NuserMedEXecAdapter(ArrayList<GetNurseMedExecution> NurseMedExecutionList, Context context){
        this.NurseMedExecutionList=NurseMedExecutionList;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
            View view=Inflater.inflate(R.layout.row_nuse_med_exec, parent, false);
            return new NurseViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("protocol", "ayaaat");

        NurseViewholder mNurseMedViewholder = (NuserMedEXecAdapter.NurseViewholder) holder;
        GetNurseMedExecution data = NurseMedExecutionList.get(position);
        mNurseMedViewholder.txtdatetime.setText(data.getInmedGivenDate());
        mNurseMedViewholder.txtnusename.setText(data.getUserFullName());
        mNurseMedViewholder.txtDose.setText(data.getInmedDose());
        mNurseMedViewholder.txtnote.setText("عرض الملاحظات");
        mcontroller.Msg_DIALOG = new DialogMsg(context);
        mNurseMedViewholder.txtnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcontroller.Msg_DIALOG.showDialog(data.getINMEDNOTE() + "");

            }
        });
        //(Integer) (data.getInmedDose().equals("null") ? "" :data.getInmedDose())
        // for (int x = 1; x <= Serial; x++)
        mNurseMedViewholder.txtserial.setText(String.valueOf(position + 1));
        Serial = Integer.parseInt(data.getInmedIsGiven());
//
        switch (Serial) {
            case 1:
                Status = "منتظر التنفيذ";
                break;
            case 2:
                Status= "تحت العلاج";

                break;
            case 3:
                Status= "تم";

                break;
            case 4:
                Status= "المريض رفض العلاج";

                break;

        }
        mNurseMedViewholder.txtStatus.setText(Status);


        Log.e("protocol",data.getUserFullName()+"ayaaat");

    }

    @Override
    public int getItemCount() {
        return NurseMedExecutionList.size();
    }

    public void setNurseMedExecutionList(ArrayList<GetNurseMedExecution> nurseMedExecutionList) {
        NurseMedExecutionList = nurseMedExecutionList;
    }

    public class NurseViewholder extends RecyclerView.ViewHolder {
        TextView txtserial, txtdatetime, txtStatus, txtnusename, txtDose, txtnote;

        public NurseViewholder(@NonNull View itemView) {
            super(itemView);
            this.txtserial = (TextView) itemView.findViewById(R.id.txtserial);
            this.txtdatetime = (TextView) itemView.findViewById(R.id.txtdatetime);
            this.txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            this.txtDose = (TextView) itemView.findViewById(R.id.txtDose);
            this.txtnusename = (TextView) itemView.findViewById(R.id.txtnusename);
            this.txtnote = (TextView) itemView.findViewById(R.id.txtnote);
        }
  }
}
