package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetPhysicalOncology;

import java.util.ArrayList;


public class ExaminationoncologyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<GetPhysicalOncology> physicalOncologiesList;
    private Context context;

    public ExaminationoncologyAdapter(ArrayList<GetPhysicalOncology> physicalOncologiesList, Context context){
        this.physicalOncologiesList=physicalOncologiesList;

        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
            View view=Inflater.inflate(R.layout.row_patient_notification, parent, false);
            return new protcolViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        protcolViewholder mprotcolViewholder = (ExaminationoncologyAdapter.protcolViewholder)holder;
        GetPhysicalOncology data=physicalOncologiesList.get(position);
        Log.e("ERROR",data.getCbName()+"d");
        mprotcolViewholder.txtvisitdate.setText(data.getDateDiag());
      //  holder.txtpatientcd.setText(data.get());
        mprotcolViewholder.txtnotificationname.setText(data.getCmIcdNameEn());
        mprotcolViewholder.txtvisitno.setText(data.getVisitId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");

                String Risk_ID = data.getRskId();
                String patric_cd = data.getPatric_cd();
                String mypdf = "http://pulse.moh.gov.ps/newehos/index.php/Cancer_physical_exam_cont_pulse/getCancerReport/" + Risk_ID + "/" + patric_cd;

                Log.e("urlpdf", mypdf);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mypdf));
                context.startActivity(browserIntent);
                //mDialogLoding.hideDialog();

            }
        });

    }

    @Override
    public int getItemCount() {
        return physicalOncologiesList.size();
    }

    public void setProtocolList(ArrayList<GetPhysicalOncology> physicalOncologiesList) {
        this.physicalOncologiesList = physicalOncologiesList;
        Log.e("ERROR",physicalOncologiesList.size()+"p");
    }

    public  class protcolViewholder extends RecyclerView.ViewHolder{
      TextView txtvisitdate, txtvisitno, txtnotificationname,txtpatientcd,lblopen;

      public protcolViewholder(@NonNull View itemView) {
          super(itemView);

          this.txtvisitno = (TextView) itemView.findViewById(R.id.txtvisitno);
          this.txtvisitdate = (TextView) itemView.findViewById(R.id.txtdiagnosedate);
          this.txtnotificationname = (TextView) itemView.findViewById(R.id.txtnotificationname);
         this.lblopen = (TextView) itemView.findViewById(R.id.lblopen);
         this.txtpatientcd = (TextView) itemView.findViewById(R.id.txtpatientcd);

      }
  }
}
