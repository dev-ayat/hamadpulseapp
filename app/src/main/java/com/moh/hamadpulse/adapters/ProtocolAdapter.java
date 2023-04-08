package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetProtocolOncology;

import java.util.ArrayList;



public class ProtocolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<GetProtocolOncology> protocolList;
    private Context context;

    public ProtocolAdapter(ArrayList<GetProtocolOncology> protocolList,Context context){
        this.protocolList=protocolList;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
            View view=Inflater.inflate(R.layout.row_patient_protocol, parent, false);
            return new protcolViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder , int position) {

        ProtocolAdapter.protcolViewholder mprotcolViewholder = (ProtocolAdapter.protcolViewholder)holder;

        GetProtocolOncology data=protocolList.get(position);
        mprotcolViewholder.txtvisitdate.setText(data.getReportDate());
        mprotcolViewholder.txtpatientcd.setText(data.getPatientCd());
        mprotcolViewholder.txtprotcolname.setText(data.getProtocolName());
        mprotcolViewholder.txtvisitno.setText(data.getVisitedId());

        Log.e("protocol",data.getVisitedId()+"ayaaat");


        mprotcolViewholder.openfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");

                String Tumer_no = data.getTumorsOrderNo();
                String visited_id = data.getVisitedId();
                String mypdf = "http://pulse.moh.gov.ps/newehos/index.php/Diagnosis_cont_standard_pulse/getPatientProtocolReport/" + Tumer_no + "/" + visited_id;

                Log.e("urlpdf", mypdf);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mypdf));
                context.startActivity(browserIntent);
                //mDialogLoding.hideDialog();

            }
        });


    }

    @Override
    public int getItemCount() {
        return protocolList.size();
    }

    public void setProtocolList(ArrayList<GetProtocolOncology> protocolList) {
        this.protocolList = protocolList;
    }

    public  class protcolViewholder extends RecyclerView.ViewHolder{
      TextView txtvisitdate, txtvisitno, txtprotcolname, txtpatientcd;
        ImageButton openfile;

      public protcolViewholder(@NonNull View itemView) {
          super(itemView);

          this.txtvisitno = (TextView) itemView.findViewById(R.id.txtvisitno);
          this.txtvisitdate = (TextView) itemView.findViewById(R.id.txtvisitdate);
          this.txtprotcolname = (TextView) itemView.findViewById(R.id.txtprotcolname);
          this.txtpatientcd = (TextView) itemView.findViewById(R.id.txtpatientcd);
          this.openfile = (ImageButton) itemView.findViewById(R.id.lblopen);

      }
  }
}
