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
import com.moh.hamadpulse.models.GetNoteForPatient;

import java.util.ArrayList;


public class DoctorNurseNoteAdapter extends RecyclerView.Adapter<DoctorNurseNoteAdapter.ViewHolder> {
    Context context;
    ArrayList<GetNoteForPatient> admPatientNotesArray;
    NoteInterface mynoteInterface;


    public DoctorNurseNoteAdapter(Context context, ArrayList<GetNoteForPatient> admPatientNotesArray, NoteInterface mynoteInterface) {
        this.context = context;
        this.admPatientNotesArray = admPatientNotesArray;
        this.mynoteInterface = mynoteInterface;
        Log.e("signsarray", "size=" + admPatientNotesArray.size());
    }

    @NonNull
    @Override
    public DoctorNurseNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doctor_nurse_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorNurseNoteAdapter.ViewHolder holder, final int position) {
        GetNoteForPatient PatientNote = admPatientNotesArray.get(position);
        holder.note_serial.setText(PatientNote.getdOCNURSERIAL());
        holder.note_date.setText(PatientNote.getDOCCREATEDON());
        holder.patient_status.setText(PatientNote.getPATIENT_STATUS_NAME_EN());
        holder.note_body.setText(PatientNote.getDOCNURNOTE());
        holder.Doctor_name.setText(PatientNote.getDOC_NAME());
        ImageButton delete_btn= holder.delete_btn;
        Log.d("UserId",PatientNote.getUSER_ID());
        Log.d("UserId",Controller.pref.getString("USER_ID", ""));
        delete_btn.setVisibility(PatientNote.getUSER_ID().
                equals(Controller.pref.getString("USER_ID", ""))?View.VISIBLE:View.GONE);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mynoteInterface.onNoteClick(PatientNote);
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String serial = PatientNote.getdOCNURSERIAL();
//                Log.e("serial", "serial" + serial);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return admPatientNotesArray.size();

    }

    public interface NoteInterface {
        void onNoteClick(GetNoteForPatient mGetNoteForPatient);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_serial, note_date, patient_status, note_body, Doctor_name;
        ImageButton delete_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            note_date = (TextView) itemView.findViewById(R.id.note_date);
            patient_status = (TextView) itemView.findViewById(R.id.patient_status);
            note_body = (TextView) itemView.findViewById(R.id.note_body);
            note_serial = (TextView) itemView.findViewById(R.id.note_serial);
            Doctor_name = (TextView) itemView.findViewById(R.id.Doctor_name);
            delete_btn = itemView.findViewById(R.id.delete_btn);

        }
    }


}
