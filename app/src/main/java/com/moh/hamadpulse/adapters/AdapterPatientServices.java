package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.NewPatientServicesModel;
import com.moh.hamadpulse.models.PatientServices;

import java.util.ArrayList;

public class AdapterPatientServices extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<PatientServices> mListPatientServices;
    NewPatientServicesModel model;

    public void setModel(NewPatientServicesModel model) {
        this.model = model;
    }

    public interface ClickAdapterPatientServices {
        void myclick(PatientServices mPatientServices);
    }

    ClickAdapterPatientServices mClickAdapterPatientServices;

    public AdapterPatientServices(ArrayList<PatientServices> mListPatientServices,
                                  ClickAdapterPatientServices mClickAdapterPatientServices,
                                  NewPatientServicesModel model
                                  ) {
        this.mListPatientServices = mListPatientServices;
        this.mClickAdapterPatientServices = mClickAdapterPatientServices;
        this.model=model;

    }

    public AdapterPatientServices(ArrayList<PatientServices> mListPatientServices) {
        this.mListPatientServices = mListPatientServices;
    }

    public ArrayList<PatientServices> getmListPatientServices() {
        return mListPatientServices;
    }

    public void setmListPatientServices(ArrayList<PatientServices> mListPatientServices) {
        this.mListPatientServices = mListPatientServices;
    }

    public ClickAdapterPatientServices getmClickAdapterPatientServices() {
        return mClickAdapterPatientServices;
    }

    public void setmClickAdapterPatientServices(ClickAdapterPatientServices mClickAdapterPatientServices) {
        this.mClickAdapterPatientServices = mClickAdapterPatientServices;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient_services, parent, false);
        return new HolderPatientServices(view);
    }


    Context cx;
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PatientServices mPatientServices = mListPatientServices.get(position);
        HolderPatientServices mHolderPatientServices = (HolderPatientServices)holder;
        if(position==2){//rad and lab
            Log.d("null_model",(model==null)+"");
            if(model!=null)
                mHolderPatientServices.newservicestf.setVisibility(model.getRad_orders().equals("0")?View.GONE:View.VISIBLE);
            }else if(position==3){
            if(model!=null)
                mHolderPatientServices.newservicestf.setVisibility(model.getLab_orders().equals("0")?View.GONE:View.VISIBLE);
            }


        cx = holder.itemView.getContext();
        mHolderPatientServices.txtPatientServicesTitle.setText(mPatientServices.getTitle());
        mHolderPatientServices.imgPatientServices.setImageResource(mPatientServices.getImage_id());
        mHolderPatientServices.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("ERROR","position="+position+" title="+)
                mClickAdapterPatientServices.myclick(mListPatientServices.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListPatientServices.size();
    }

    public class HolderPatientServices extends RecyclerView.ViewHolder {
        private TextView txtPatientServicesTitle;
        private ImageView imgPatientServices,newservicestf;
        public HolderPatientServices(View itemView) {
            super(itemView);
            txtPatientServicesTitle = itemView.findViewById(R.id.txtPatientServicesTitle);
            newservicestf = itemView.findViewById(R.id.newservicestf);
            imgPatientServices = itemView.findViewById(R.id.imgPatientServices);
        }
    }
}
