package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.models.EfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfileAdapter extends RecyclerView.Adapter<EfileAdapter.MyViewHolder> {
    private ArrayList<EfileModel> dataSet;
    private Context context;
    private DialogLoding mDialogLoding;

    public EfileAdapter(ArrayList<EfileModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_file_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        mDialogLoding = new DialogLoding(context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        EfileModel Carddata = dataSet.get(listPosition);
        holder.txtvisitdate.setText(Carddata.getVISTE_DATE());
        holder.txthossid.setText(Carddata.getHOSSID());
        holder.txtmrpid.setText(Carddata.getIDNUMBER());
        final Map<String, String> map = new HashMap<>();
        map.put("VISIT_DATE", Carddata.getVISTE_DATE());
        map.put("MRP_ID", Carddata.getIDNUMBER());
        Log.e("test", map.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");
                EfileModel Carddata = dataSet.get(listPosition);
                String my_MRP_ID = Carddata.getIDNUMBER();
                String my_VISIT_DATE = Carddata.getVISTE_DATE();
                String mypdf = "http://apps.moh.gov.ps//newwebarch/index.php/Androidhos_api/GET_FILES_BY_VISIT_PDF/"
                        + my_MRP_ID + "/" + my_VISIT_DATE;
                Log.d("pdf_url", mypdf);
                mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");
//               The change
                Controller.OpenPdfFiles(mypdf, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtvisitdate, txthossid, txtmrpid;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtvisitdate = (TextView) itemView.findViewById(R.id.txtvisitdate);
            this.txthossid = (TextView) itemView.findViewById(R.id.txthossid);
            this.txtmrpid = (TextView) itemView.findViewById(R.id.txtmrpid);
        }
    }


}