package com.moh.hamadpulse.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.Activity_Call;
import com.moh.hamadpulse.models.CardviewDataModel;
import com.moh.hamadpulse.models.Extra;

import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.MyViewHolder> {
    private ArrayList<CardviewDataModel> dataSet;
    private Context context;

    public CardviewAdapter(Context context, ArrayList<CardviewDataModel> dataSet) {
        this.dataSet = dataSet;

    }

    public ArrayList<CardviewDataModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<CardviewDataModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //  view.setOnClickListener(PatientFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    Context cx;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        int seq = listPosition + 1;
        cx=holder.itemView.getContext();
        CardviewDataModel Carddata = dataSet.get(listPosition);
        //

        if(Carddata.getADMISSION_STATUS()!=null&&
                Carddata.getADMISSION_STATUS().equals("1")) {
            holder.itemView.setBackgroundColor(cx.getResources().getColor(
                    R.color.colorPendingLight));
            holder.is_discharged_tf.setVisibility(View.VISIBLE);
        }else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            holder.is_discharged_tf.setVisibility(View.GONE);
        }
        holder.txtpatName.setText(Carddata.getPatname());
        holder.txtpatid.setText(String.valueOf(Carddata.getPatid()));
        holder.txtdate.setText(Carddata.getIndate());
        holder.txtdoc.setText(Carddata.getDocname());
        holder.txtroom.setText(Carddata.getRoomname());
        holder.txtadmcd.setText(String.valueOf(Carddata.getRoomname()));
        holder.txtbed.setText(Carddata.getBedname());
        holder.txtSeq.setText("#"+seq);
        holder.txtmrpid.setText(Carddata.getPtmrpid());
        holder.HOS_NO.setText(Carddata.getHOS_NO());
        holder.H_NAME_AR.setText(Carddata.getH_NAME_AR());
        holder.MRP_SEX_CD.setText(Carddata.getMRP_SEX_CD());
        holder.SEX_NAME_AR.setText(Carddata.getSEX_NAME_AR());
        holder.MRP_DOB.setText(Carddata.getMRP_DOB());
        holder.HOS_PERMISSION.setText(Carddata.getHOS_PERMISSION());
        holder.LOC_CODE.setText(Carddata.getLOC_CODE());
        holder.MRP_MOBILE_NO.setText(Carddata.getMRP_MOBILE_NO());
        holder.txtPatientStatus.setText(Carddata.getPATIENT_STATUS_NAME_EN());

        //holder.txtResultDate.setText(Carddata.getRESULT_DATE());
        //holder.txtResultStatus.setText(Carddata.getTEST_RESULT());
        switch (Carddata.getPATIENT_STATUS_NAME_EN().toUpperCase())
        {
            case "ASYMPTOMATIC":
                holder.txtPatientStatus.setTextColor(cx.getResources().getColor(R.color.status_pateint_1));
                break;
            case "MILD":
                holder.txtPatientStatus.setTextColor(cx.getResources().getColor(R.color.status_pateint_2));
                break;
            case "MODERATE":
                holder.txtPatientStatus.setTextColor(cx.getResources().getColor(R.color.status_pateint_3));
                break;
            case "SEVERE":
                holder.txtPatientStatus.setTextColor(cx.getResources().getColor(R.color.status_pateint_4));
                break;
            case "CRITICAL":
                holder.txtPatientStatus.setTextColor(cx.getResources().getColor(R.color.status_pateint_5));
                break;
        }
        holder.container_MRP_MOBILE_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cx)
                            .setTitle("هل تريد الإتصال بالمريض " + Carddata.getPatname())
                            .setIcon(R.drawable.phone)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent mIntent = new Intent(holder.container_MRP_MOBILE_NO.getContext(), Activity_Call.class);
                                    mIntent.putExtra("EXTRA_CALL_NUMBER", Carddata.getMRP_MOBILE_NO());
                                    cx.startActivity(mIntent);

                                }


                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                } catch (Exception e) {

                }
            }
        });
        LinearLayout PatientDetailsLayout = holder.PatientDetailsLayout;
        LinearLayout PatientstatusLayout = holder.PatientstatusLayout;
        ImageButton btn_show_details = holder.btn_show_details;
        btn_show_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (PatientDetailsLayout.getVisibility() == View.VISIBLE & PatientstatusLayout.getVisibility() == View.VISIBLE) {
                    PatientDetailsLayout.setVisibility(View.GONE);
                    PatientstatusLayout.setVisibility(View.GONE);
                    btn_show_details.setImageResource(R.drawable.arrow_down);
                } else {
                    PatientDetailsLayout.setVisibility(View.VISIBLE);
                    PatientstatusLayout.setVisibility(View.VISIBLE);
                    btn_show_details.setImageResource(R.drawable.arrow_up);
                }
            }
        });

//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int width = displayMetrics.widthPixels;
//        ViewGroup.LayoutParams layoutParams = holder.rowFG.getLayoutParams();
//        layoutParams.width = width;
//        holder.rowFG.setLayoutParams(layoutParams);
//        holder.hscroll.postDelayed(new Runnable() {
//            public void run() {
//                //holder.s.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
//                int x, y;
//                x = holder.rowFG.getLeft();
//                y = holder.rowFG.getTop();
//                holder.hscroll.scrollTo(x, y);
//            }
//        }, 1);
        final String patname = Carddata.getPatname();
        String indate = Carddata.getIndate();
        final String patid = String.valueOf(Carddata.getPatid());
        String patmrp = Carddata.getPtmrpid();
        String patadm = String.valueOf(Carddata.getAdmcd());
        String pat_HOS_NO = Carddata.getHOS_NO();
        String pat_HOS_NAME = Carddata.getH_NAME_AR();
        String pat_SEX_CD = Carddata.getMRP_SEX_CD();
        String pat_SEX_NAME = Carddata.getSEX_NAME_AR();
        String pat_DOB = Carddata.getMRP_DOB();
        String HOS_PERMISSION = Carddata.getHOS_PERMISSION();
        String LOC_CODE= Carddata.getLOC_CODE();
        String MRP_MOBILE_NO= Carddata.getMRP_MOBILE_NO();

        final Bundle args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("patmrp", patmrp);
        args.putString("indate", indate);
        args.putString("patadm", patadm);
        args.putString("pat_HOS_NO", pat_HOS_NO);
        args.putString("pat_HOS_NAME", pat_HOS_NAME);
        args.putString("pat_SEX_CD", pat_SEX_CD);
        args.putString("pat_SEX_NAME", pat_SEX_NAME);
        args.putString("pat_DOB", pat_DOB);
        args.putString("HOS_PERMISSION", HOS_PERMISSION);
        args.putString("LOC_CODE", LOC_CODE);
        args.putString("MRP_MOBILE_NO", MRP_MOBILE_NO);
        Log.e("args", args.toString());

        holder.rowFG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText((AppCompatActivity) view.getContext(), "clicked", Toast.LENGTH_SHORT).show();
//                PatientProfileFragment patientProfileFragment = new PatientProfileFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                patientProfileFragment.setArguments(args);
//                ft.replace(R.id.content_frame, patientProfileFragment);
//                ft.addToBackStack(null);
//                ft.commit();

                Intent i = new Intent(cx, ActivityPatient.class);
                i.putExtra(Extra.EXTRA_PATIENT_INFO,Carddata);
                cx.startActivity(i);
            }
        });

//        holder.img_lab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LabFragment labFragment = new LabFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                labFragment.setArguments(args);
//                ft.replace(R.id.content_frame, labFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//        holder.img_files.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArchiveServicesFragment archiveServicesFragment = new ArchiveServicesFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                archiveServicesFragment.setArguments(args);
//                ft.replace(R.id.content_frame, archiveServicesFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//
//            }
//        });
//
//        holder.img_rad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RadFragment radFragment = new RadFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                radFragment.setArguments(args);
//                ft.replace(R.id.content_frame, radFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//        holder.img_vital_signs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                VitalSignsFragment vitalSignsFragment = new VitalSignsFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                vitalSignsFragment.setArguments(args);
//                ft.replace(R.id.content_frame, vitalSignsFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//
//        holder.img_diagnose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DoctorNurseNoteFragment diagnoseFragment = new DoctorNurseNoteFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                diagnoseFragment.setArguments(args);
//                ft.replace(R.id.content_frame, diagnoseFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//        holder.img_pharm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TreatmentPlanFragment treatmentPlanFragment = new TreatmentPlanFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                treatmentPlanFragment.setArguments(args);
//                ft.replace(R.id.content_frame, treatmentPlanFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//        holder.img_ventailor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                VanteliationFragment vanteliationFragment = new VanteliationFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                vanteliationFragment.setArguments(args);
//                ft.replace(R.id.content_frame, vanteliationFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//        holder.img_pathology.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pathologyurl = "http://apps.moh.gov.ps/pathology/path_sys/index.php/login/get";
//                Log.e("pathologyurl", pathologyurl);
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathologyurl));
//                holder.itemView.getContext().startActivity(browserIntent);
//            }
//        });
//
//        holder.img_discharge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DischargeFragment dischargeFragment = new DischargeFragment();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//                dischargeFragment.setArguments(args);
//                ft.replace(R.id.content_frame, dischargeFragment);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtpatName, txtpatid, txtdate, txtdoc, txtroom, txtbed, txtmrpid, txtSeq,is_discharged_tf,
                HOS_NO, HOS_PERMISSION, H_NAME_AR, MRP_SEX_CD, SEX_NAME_AR, MRP_DOB, txtadmcd, LOC_CODE, MRP_MOBILE_NO, txtPatientStatus;
        LinearLayout rowFG, PatientDetailsLayout, PatientstatusLayout;
        ImageButton btn_show_details;
        //HorizontalScrollView hscroll;
        ImageView img_lab, img_files, img_rad, img_pharm, img_vital_signs,
                img_diagnose, img_ventailor, img_pathology, img_discharge;
        LinearLayout container_MRP_MOBILE_NO;

        //private TextView txtResultDate;
        //private TextView txtResultStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.rowFG = itemView.findViewById(R.id.rowFG);
            this.txtpatName = itemView.findViewById(R.id.txtpatName);
            this.txtpatid = itemView.findViewById(R.id.txtpatid);
            this.txtdate = itemView.findViewById(R.id.txtdate);
            this.txtdoc = itemView.findViewById(R.id.txtdoc);
            this.txtroom = itemView.findViewById(R.id.txtroom);
            this.txtbed = itemView.findViewById(R.id.txtbed);
            this.txtmrpid = itemView.findViewById(R.id.txtmrpid);
            this.txtadmcd = itemView.findViewById(R.id.txtadmcd);
            this.MRP_MOBILE_NO = itemView.findViewById(R.id.MRP_MOBILE_NO);
            this.container_MRP_MOBILE_NO = itemView.findViewById(R.id.container_MRP_MOBILE_NO);
            //this.hscroll = itemView.findViewById(R.id.hscroll);
//            this.img_lab = itemView.findViewById(R.id.img_lab);
//            this.img_files = itemView.findViewById(R.id.img_files);
//            this.img_rad = itemView.findViewById(R.id.img_rad);
//            this.img_pharm = itemView.findViewById(R.id.img_pharm);
//            this.img_vital_signs = itemView.findViewById(R.id.img_vital_signs);
//            this.img_diagnose = itemView.findViewById(R.id.img_diagnose);
//            this.img_ventailor = itemView.findViewById(R.id.img_ventailor);
//            this.img_pathology = itemView.findViewById(R.id.img_pathology);
//            this.img_discharge = itemView.findViewById(R.id.img_discharge);
            this.txtSeq = itemView.findViewById(R.id.txtSeq);
            this.HOS_NO = itemView.findViewById(R.id.txt_HOS_NO);
            this.H_NAME_AR = itemView.findViewById(R.id.txt_H_NAME_AR);
            this.MRP_SEX_CD = itemView.findViewById(R.id.txt_MRP_SEX_CD);
            this.SEX_NAME_AR = itemView.findViewById(R.id.txt_SEX_NAME_AR);
            this.MRP_DOB = itemView.findViewById(R.id.txt_MRP_DOB);
            this.HOS_PERMISSION = itemView.findViewById(R.id.txt_HOS_PERMISSION);
            this.LOC_CODE = itemView.findViewById(R.id.txt_LOC_CODE);
            this.txtPatientStatus = itemView.findViewById(R.id.txtPatientStatus);
            this.btn_show_details = itemView.findViewById(R.id.btn_show_details);
            this.PatientDetailsLayout = itemView.findViewById(R.id.PatientDetailsLayout);
            this.PatientstatusLayout = itemView.findViewById(R.id.PatientstatusLayout);
            this.is_discharged_tf = itemView.findViewById(R.id.is_discharged_tf);

            //txtResultDate = itemView.findViewById(R.id.txtResultDate);
            //txtResultStatus = itemView.findViewById(R.id.txtResultStatus);


        }
    }

}