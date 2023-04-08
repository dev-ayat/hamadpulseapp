package com.moh.hamadpulse.adapters;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

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

import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.ProtocolExecutionFragment;
import com.moh.hamadpulse.models.GetProtocolOncology;

import java.util.ArrayList;


public class ProtocoloncologyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GetProtocolOncology> physicalOncologiesList;
    private Context context;

    public ProtocoloncologyAdapter(ArrayList<GetProtocolOncology> physicalOncologiesList,
                                   Context context) {
        this.physicalOncologiesList = physicalOncologiesList;
        this.context = context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View view = Inflater.inflate(R.layout.row_patient_protocol, parent, false);
        return new protcolViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("protocol", "ayaaat");

        protcolViewholder mprotcolViewholder = (ProtocoloncologyAdapter.protcolViewholder) holder;
        GetProtocolOncology data = physicalOncologiesList.get(position);
        mprotcolViewholder.txtvisitdate.setText(data.getReportDate());
        mprotcolViewholder.txtpatientcd.setText(data.getPatientCd());
        mprotcolViewholder.txtprotcolname.setText(data.getProtocolName());
        mprotcolViewholder.txtvisitno.setText(data.getVisitedId());

        Log.e("protocol", data.getVisitedId() + "ayaaat");
        if(Controller.pref.getString(USER_TYPE, "").equals("5"))
        mprotcolViewholder.txtProtocolEexc.setImageResource(R.drawable.exp_2);

        mprotcolViewholder.txtProtocolEexc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) context).CallFragment(new ProtocolExecutionFragment
                        (data.getTumorsOrderNo()));
            }
        });
        mprotcolViewholder.openfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");

                String Tumer_no = data.getTumorsOrderNo();
                String visited_id = data.getVisitedId();
                String patric_id = data.getPatientCd();
                String mypdf = "http://pulse.moh.gov.ps/newehos/index.php/Diagnosis_cont_standard_pulse/getPatientProtocolReport/" + Tumer_no + "/" + visited_id + "/" + patric_id;

                Log.e("urlpdf", mypdf);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mypdf));
                context.startActivity(browserIntent);
                //mDialogLoding.hideDialog();

            }
        });

    }

    @Override
    public int getItemCount() {
        return physicalOncologiesList.size();
    }

    public void setProtocolList(ArrayList<GetProtocolOncology> physicalOncologiesList) {
        this.physicalOncologiesList = physicalOncologiesList;
        Log.e("ERROR", physicalOncologiesList.size() + "protocol");
    }

    public class protcolViewholder extends RecyclerView.ViewHolder {
        TextView txtvisitdate, txtvisitno, txtprotcolname, txtpatientcd;
        ImageButton txtProtocolEexc, openfile;

        public protcolViewholder(@NonNull View itemView) {
            super(itemView);

            this.txtvisitno = (TextView) itemView.findViewById(R.id.txtvisitno);
            this.txtvisitdate = (TextView) itemView.findViewById(R.id.txtvisitdate);
            this.txtprotcolname = (TextView) itemView.findViewById(R.id.txtprotcolname);
            this.txtpatientcd = (TextView) itemView.findViewById(R.id.txtpatientcd);
            this.txtProtocolEexc = itemView.findViewById(R.id.txtProtocolEexc);
            this.openfile = (ImageButton) itemView.findViewById(R.id.lblopen);


        }
  }
}
