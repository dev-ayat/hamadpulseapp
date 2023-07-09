package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetDoctorsOrders;

import java.util.ArrayList;


public class DoctorOrdersAdapter extends RecyclerView.Adapter<DoctorOrdersAdapter.ViewHolder> {
    Context context;
    ArrayList<GetDoctorsOrders> admPatientNotesArray;
    NoteInterface mynoteInterface;


    public DoctorOrdersAdapter(Context context, ArrayList<GetDoctorsOrders> admPatientNotesArray, NoteInterface mynoteInterface) {
        this.context = context;
        this.admPatientNotesArray = admPatientNotesArray;
        this.mynoteInterface = mynoteInterface;
        Log.e("signsarray", "size=" + admPatientNotesArray.size());
    }

    @NonNull
    @Override
    public DoctorOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doctor_order_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOrdersAdapter.ViewHolder holder, final int position) {
        GetDoctorsOrders Patientorder = admPatientNotesArray.get(position);
        holder.order_serial.setText(Patientorder.getDocOrderCode());
        holder.order_date.setText(Patientorder.getDocCreatedOn());
        holder.order_body.setText(Patientorder.getDocOrderNote());
        holder.Doctor_name.setText(Patientorder.getDocName());
        ImageButton delete_btn = holder.delete_btn;
        Log.d("UserId", Patientorder.getDocOrderNote());
        Log.d("UserId", Controller.pref.getString("USER_ID", ""));
        delete_btn.setVisibility(Patientorder.getUserId().
                equals(Controller.pref.getString("USER_ID", "")) ? View.VISIBLE : View.GONE);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mynoteInterface.onNoteClick(Patientorder);
            }
        });


    }

    @Override
    public int getItemCount() {
        return admPatientNotesArray.size();

    }

    public interface NoteInterface {
        void onNoteClick(GetDoctorsOrders mGetDoctorOrder);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_serial, order_date, order_body, Doctor_name;
        ImageButton delete_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            order_date = (TextView) itemView.findViewById(R.id.order_date);
            order_body = (TextView) itemView.findViewById(R.id.order_body);
            order_serial = (TextView) itemView.findViewById(R.id.order_serial);
            Doctor_name = (TextView) itemView.findViewById(R.id.Doctor_name);
            delete_btn = itemView.findViewById(R.id.delete_btn);

        }
    }


}
