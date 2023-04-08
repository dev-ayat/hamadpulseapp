package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.FragmentBloodTransferEX;
import com.moh.hamadpulse.models.GetBloodTransfer;

import java.util.ArrayList;


public class BloodTransferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    GetBloodTransfer getBloodTransfer;
    ArrayList<Object>   mListObject;
    private ArrayList<GetBloodTransfer> BloodList;
    private Context context;
    public BloodTransferAdapter(ArrayList<GetBloodTransfer> BloodList, Context context){
        this.BloodList=BloodList;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
            View view=Inflater.inflate(R.layout.row_blood_trans, parent, false);
            return new protcolViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("protocol", "ayaaat");

        protcolViewholder viewholder = (BloodTransferAdapter.protcolViewholder) holder;
        GetBloodTransfer data = BloodList.get(position);
        viewholder.txtrecivedate.setText(data.getOrdermReceiveDate());
        viewholder.txtDocname.setText(data.getFullName());
        viewholder.txtBloodGroup.setText(data.getBloodGroupNameAr());
        viewholder.txtRservername.setText(data.getReserveNameAr());
        viewholder.txtunitType.setText(data.getUNIT_TYPE());
        viewholder.txtunitNo.setText(data.getUNIT_NO());

        Log.e("protocol", data.getFullName() + "ayaaat");

        viewholder.card_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order_no = data.getOrdermCode();

                //String mypdf = "http://pulse.moh.gov.ps/newehos/index.php/Blood_transfer_cont_pulse/Doc_blood_trans_pdf?id=" + order_no;
                String mypdf = "http://10.30.60.20/newehos/index.php/Blood_transfer_cont_pulse/Doc_blood_trans_pdf?id=" + order_no;

                Log.e("urlpdf", mypdf);
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mypdf));
//                context.startActivity(browserIntent);
                Controller.OpenPdfFiles(mypdf, context);
            }
        });

        viewholder.Ex_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBloodTransferEX fragmentBloodTransferEX=new FragmentBloodTransferEX(data,mListObject);
                FragmentTransaction ft = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentBloodTransferEX.show(ft, "fragmentBloodTransferEX");

            }
        });
    }

    @Override
    public int getItemCount() {
        return BloodList.size();
    }

    public void setBloodList(ArrayList<GetBloodTransfer> BloodList) {
        this.BloodList = BloodList;
    }

    public class protcolViewholder extends RecyclerView.ViewHolder {
        TextView txtrecivedate, txtDocname, txtBloodGroup, txtRservername, txtpatientcd, txtmrpid, txtunitType, txtunitNo;
        ImageButton card_arrow, Ex_protocol;

        public protcolViewholder(@NonNull View itemView) {
            super(itemView);

            this.txtrecivedate = (TextView) itemView.findViewById(R.id.txtOrderrecivedate);
            this.txtDocname = (TextView) itemView.findViewById(R.id.txtdocname);
            this.txtBloodGroup = (TextView) itemView.findViewById(R.id.txtbloodgroup);
            this.txtRservername = (TextView) itemView.findViewById(R.id.txtrevicername);
            this.card_arrow = (ImageButton) itemView.findViewById(R.id.card_arrow);
            this.Ex_protocol = (ImageButton) itemView.findViewById(R.id.Ex_protocol);
            this.txtunitType = (TextView) itemView.findViewById(R.id.txtunitType);
            this.txtunitNo = (TextView) itemView.findViewById(R.id.txtunitNo);
        }
  }
}
