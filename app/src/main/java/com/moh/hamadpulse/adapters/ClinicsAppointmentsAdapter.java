package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ClinicAppointmentsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClinicsAppointmentsAdapter extends RecyclerView.Adapter<ClinicsAppointmentsAdapter.ClinicsAppointmentsViewHolder> {
    ArrayList<ClinicAppointmentsModel> list = new ArrayList<>();
    TextView appointmentsTV;
    RecyclerviewClickListener recyclerviewClickListener;
    boolean falg;

    public ClinicsAppointmentsAdapter(ArrayList<ClinicAppointmentsModel> list, RecyclerviewClickListener recyclerviewClickListener) {
        this.list = list;
        this.recyclerviewClickListener = recyclerviewClickListener;
    }

    public ClinicsAppointmentsAdapter(ArrayList<ClinicAppointmentsModel> list) {
        this.list = list;
        falg = true;
    }

    public TextView getAppointmentsTV() {
        return appointmentsTV;
    }

    public void setAppointmentsTV(TextView appointmentsTV) {
        this.appointmentsTV = appointmentsTV;
    }

    public ArrayList<ClinicAppointmentsModel> getList() {
        return list;
    }

    public void setList(ArrayList<ClinicAppointmentsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ClinicsAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClinicsAppointmentsAdapter.ClinicsAppointmentsViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_clincs_appointment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicsAppointmentsViewHolder holder, int position) {
        ClinicAppointmentsModel model = list.get(position);
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.clinc_name.setText(model.getClinic_name());
        holder.day.setText(getDay(model.getDate()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!falg) {
                    appointmentsTV.setText(model.getTime() + " " + model.getDate());
                    recyclerviewClickListener.recyclerviewClickListener(position);
                }
            }
        });
    }

    public String getDay(String input) {
        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date);
        switch (goal) {
            case "Saturday":
                return "السبت";
            case "Sunday":
                return "الأحد";
            case "Monday":
                return "الإثنين";
            case "Tuesday":
                return "الثلاثاء";
            case "Wednesday":
                return "الأربعاء";
            case "Thursday":
                return "الخميس";
            case "Friday":
                return "الجمعة";
            default:
                return "";


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ClinicsAppointmentsViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView time;
        private TextView day;
        private TextView clinc_name;
        private TextView numberOfApp;

        public ClinicsAppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_date);
            time = itemView.findViewById(R.id.tv_time);
            clinc_name = itemView.findViewById(R.id.tv_subClinic);
            day = itemView.findViewById(R.id.tv_day);
            numberOfApp = itemView.findViewById(R.id.numberOfApp);
            if (falg)
                numberOfApp.setVisibility(View.GONE);
        }

    }

    public interface RecyclerviewClickListener {
        void recyclerviewClickListener(int position);
    }
}

