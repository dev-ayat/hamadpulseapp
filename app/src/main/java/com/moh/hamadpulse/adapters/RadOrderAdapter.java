package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetRadOrderForAdm;

import java.util.ArrayList;


public class RadOrderAdapter extends RecyclerView.Adapter<RadOrderAdapter.ViewHolder> {
    Context context;
    ArrayList<GetRadOrderForAdm> admPatientRadArray;
    RadInterface myRadInterface;


    public RadOrderAdapter(Context context, ArrayList<GetRadOrderForAdm> admPatientRadArray, RadInterface myRadInterface) {
        this.context = context;
        this.admPatientRadArray = admPatientRadArray;
        this.myRadInterface = myRadInterface;
        Log.e("signsarray", "size=" + admPatientRadArray.size());
    }

    @NonNull
    @Override
    public RadOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rad_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    Context cx;
    @Override
    public void onBindViewHolder(@NonNull RadOrderAdapter.ViewHolder holder, final int position) {
        String fullName = "";
        cx = holder.itemView.getContext();
        GetRadOrderForAdm PatientRad = admPatientRadArray.get(position);
        fullName = " " + PatientRad.getEMP_FIRST_NAME_AR();
//        fullName+=" "+PatientRad.getEMP_FATHER_NAME_AR();
//        fullName+=" "+PatientRad.getEMP_GRANDFATHER_NAME_AR();
        fullName += " " + PatientRad.getEMP_LAST_NAME_AR();
        Log.e("test", PatientRad.getORDERCODE());
        holder.txtdate.setText(PatientRad.getORDERCREATEDON());
        holder.txtradservice.setText(PatientRad.getSERVICENAMEEN());
        holder.txtmasterorgin.setText(PatientRad.getORGANNAMEAR());
        holder.txtdetailsorgan.setText(PatientRad.getORGANDNAMEAR());
        holder.txtposition.setText(PatientRad.getCRADPOSITIONNAMEEN());
        holder.txtRadserial.setText(PatientRad.getORDERCODE());
        holder.txtradDorder.setText(PatientRad.getoRDERDCODE());
        holder.txtreson.setText(PatientRad.getORDERD_ORGAN_NOTE());
        holder.txtprecautions.setText(PatientRad.getLOOKUP_DETAILS());
        holder.radDocName.setText(fullName);
        holder.delete_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRadInterface.onradClick(PatientRad);
            }
        });
//        holder.rowFG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String serial = PatientRad.getORDERCODE();
////                String radDserial = PatientRad.getoRDERDCODE();
//                myRadInterface.onradClick(PatientRad);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return admPatientRadArray.size();

    }

    public interface RadInterface {
        void onradClick(GetRadOrderForAdm mGetRadOrderForAdm);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtdate, txtradservice, txtmasterorgin, txtdetailsorgan, txtposition, txtRadserial, txtreson, txtradDorder, radDocName, txtprecautions;
        LinearLayout rowFG;
        ImageButton delete_img_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            txtdate = itemView.findViewById(R.id.txtdate);
            txtradservice = itemView.findViewById(R.id.txtradservice);
            txtmasterorgin = itemView.findViewById(R.id.txtmasterorgin);
            txtdetailsorgan = itemView.findViewById(R.id.txtdetailsorgan);
            txtposition = itemView.findViewById(R.id.txtposition);
            txtRadserial = itemView.findViewById(R.id.txt_radserial);
            txtreson = itemView.findViewById(R.id.txtreson);
            txtradDorder = itemView.findViewById(R.id.txt_radDorder);
            radDocName = itemView.findViewById(R.id.radDocName);
            rowFG = itemView.findViewById(R.id.rowFG);
            delete_img_btn = itemView.findViewById(R.id.delete_img_btn);
            txtprecautions = itemView.findViewById(R.id.txtprecautions);

        }
    }


}
