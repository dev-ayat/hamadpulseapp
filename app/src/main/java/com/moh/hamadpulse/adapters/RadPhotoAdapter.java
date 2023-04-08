package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.RadphotoModel;

import java.util.ArrayList;


public class RadPhotoAdapter extends RecyclerView.Adapter<RadPhotoAdapter.MyViewHolder> {


    private ArrayList<RadphotoModel> dataSet;
    private Context context;
    OnAdapterClick mOnAdapterClick;

    public RadPhotoAdapter(ArrayList<RadphotoModel> dataSet, Context context,OnAdapterClick mOnAdapterClick) {
        this.dataSet = dataSet;
        this.context = context;
        this.mOnAdapterClick = mOnAdapterClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rad_photo_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        RadphotoModel Carddata = dataSet.get(listPosition);
        holder.txtresdate.setText(Carddata.getORDERD_RESULT_DATE());
        holder.txtorganid.setText(Carddata.getORGAN_CODE());
        holder.txtorganname.setText(Carddata.getORGAN_NAME_AR());
        holder.txtservid.setText(Carddata.getORGAN_SERVICE_CD());
        holder.txtservname.setText(Carddata.getSERVICE_NAME_AR());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterClick.myClick(Carddata);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtresdate, txtorganid, txtorganname, txtservid, txtservname;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtresdate = (TextView) itemView.findViewById(R.id.txtresdate);
            this.txtorganid = (TextView) itemView.findViewById(R.id.txtorganid);
            this.txtorganname = (TextView) itemView.findViewById(R.id.txtorganname);
            this.txtservid = (TextView) itemView.findViewById(R.id.txtservid);
            this.txtservname = (TextView) itemView.findViewById(R.id.txtservname);
        }
    }

}