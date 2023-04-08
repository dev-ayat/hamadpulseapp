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

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ExaminationAdm;

import java.util.ArrayList;

public class AdapterExaminationAdm extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ExaminationAdm> mListExaminationAdm;
    Context cx;
    boolean flag;
    public interface OnClickExaminationAdm
    {
        void onClick(ExaminationAdm mExaminationAdm);
    }
    OnClickExaminationAdm mOnClickExaminationAdm;

    public AdapterExaminationAdm(ArrayList<ExaminationAdm> mListExaminationAdm,OnClickExaminationAdm mOnClickExaminationAdm,boolean flag) {
        this.mListExaminationAdm = mListExaminationAdm;
        this.mOnClickExaminationAdm = mOnClickExaminationAdm;
        this.flag=flag;
    }

    public ArrayList<ExaminationAdm> getmListExaminationAdm() {
        return mListExaminationAdm;
    }

    public void setmListExaminationAdm(ArrayList<ExaminationAdm> mListExaminationAdm) {
        this.mListExaminationAdm = mListExaminationAdm;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_examination_adm, parent, false);
        return new HolderExaminationAdm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ExaminationAdm mExaminationAdm = mListExaminationAdm.get(position);
        Context cx = holder.itemView.getContext();


        HolderExaminationAdm mHolderExaminationAdm = (HolderExaminationAdm) holder;
        Log.e("is_true", !mExaminationAdm.getUSER_ID().equals((Controller.pref.getString("USER_ID", ""))) + "");
        if (!mExaminationAdm.getUSER_ID().equals((Controller.pref.getString("USER_ID", ""))))
            mHolderExaminationAdm.imgbtn_delete.setVisibility(View.GONE);
        mHolderExaminationAdm.txtExamName.setText(mExaminationAdm.getEXAM_NAME());
        mHolderExaminationAdm.txtPatientStatus.setText(mExaminationAdm.getPATIENT_STATUS_NAME_EN());
        mHolderExaminationAdm.txtRespiratory.setText(mExaminationAdm.getEXAM_RESP());
        mHolderExaminationAdm.txtABD.setText(mExaminationAdm.getEXAM_ABD());
        mHolderExaminationAdm.txtDoctorName.setText(mExaminationAdm.getDOC_NAME());
        mHolderExaminationAdm.txtCNS.setText(mExaminationAdm.getEXAM_CNS());
        mHolderExaminationAdm.txtCVS.setText(mExaminationAdm.getEXAM_CVS());
        mHolderExaminationAdm.txtNote.setText(mExaminationAdm.getEXAM_NOTE());
        mHolderExaminationAdm.txtExamDate.setText(mExaminationAdm.getEXAM_DATE());
//        mHolderExaminationAdm.containerO2.setVisibility(View.GONE);
        if (mExaminationAdm.getIS_O2_NEED_CD().equals("1")) {
            mHolderExaminationAdm.containerO2.setVisibility(View.VISIBLE);
            String O2=mExaminationAdm.getDELIVERY_NAME_EN();
            Log.d("O2_String",O2);
            mHolderExaminationAdm.txtO2.setText(O2); // remove \n
        }else{
            mHolderExaminationAdm.containerO2.setVisibility(View.GONE);
        }
//        mHolderExaminationAdm.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mOnClickExaminationAdm.onClick(mExaminationAdm);
//            }
//        });
        mHolderExaminationAdm.imgbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(cx, "click", Toast.LENGTH_SHORT).show();
                mOnClickExaminationAdm.onClick(mExaminationAdm);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListExaminationAdm.size();
    }

    public class HolderExaminationAdm extends RecyclerView.ViewHolder {

        //View subItem;
        TextView txtExamName,txtRespiratory;
        private TextView txtABD;
        private TextView txtCVS;
        private TextView txtCNS;
        private TextView txtExamDate;
        private TextView txtDoctorName;
        private LinearLayout containerO2;
        private TextView txtO2;
        private TextView txtPatientStatus;
        private TextView txtNote;
        private ImageButton imgbtn_delete;
        public HolderExaminationAdm(View itemView) {
            super(itemView);
            txtExamName=itemView.findViewById(R.id.txtExamName);
            txtDoctorName=itemView.findViewById(R.id.txtDoctorName);
            txtRespiratory=itemView.findViewById(R.id.txtRespiratory);
            txtABD = itemView.findViewById(R.id.txtABD);
            txtCVS = itemView.findViewById(R.id.txtCVS);
            txtCNS = itemView.findViewById(R.id.txtCNS);
            txtExamDate = itemView.findViewById(R.id.txtExamDate);
            containerO2 = itemView.findViewById(R.id.containerO2);
            txtO2 = itemView.findViewById(R.id.txtO2);
            txtPatientStatus = itemView.findViewById(R.id.txtPatientStatus);
            txtNote = itemView.findViewById(R.id.txtNote);
            imgbtn_delete = itemView.findViewById(R.id.imgbtn_delete);
            imgbtn_delete.setVisibility(flag?View.VISIBLE:View.GONE);


        }
    }
}
