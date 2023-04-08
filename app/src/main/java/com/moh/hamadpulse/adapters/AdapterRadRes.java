package com.moh.hamadpulse.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.FragmentReportDialog;
import com.moh.hamadpulse.models.RadCurModel;

import java.util.ArrayList;

public class AdapterRadRes extends RecyclerView.Adapter<AdapterRadRes.RadResViewHolder> {
    ArrayList<RadCurModel> RadResList = new ArrayList<>();

    public AdapterRadRes(ArrayList<RadCurModel> RadResList) {
        this.RadResList = RadResList;
    }

    public ArrayList<RadCurModel> getRadResList() {
        return RadResList;
    }

    public void setRadResList(ArrayList<RadCurModel> RadResList) {
        this.RadResList = RadResList;
    }

    @NonNull
    @Override
    public RadResViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadResViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_rad_orders, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RadResViewHolder holder, int position) {
        RadCurModel item = RadResList.get(position);
//      split txt to tow line
        item.setServiceNameAr(item.getServiceNameAr().replace(" ", "\n"));
        item.setOrderStatusTypeNameAr(item.getOrderStatusTypeNameAr().
                replace(" ", "\n"));
        holder.txt_Date.setText(item.getOrderDate().trim());
        holder.txt_photo_type.setText(item.getServiceNameAr().trim());
        holder.txt_photo_status.setText(item.getOrderStatusTypeNameAr().trim());
        Log.d("flag",item.getOrderStatusCd());
        ImageButton btn = holder.btn_view_report;
        // set enabled depending on report status
        boolean flag = item.getOrderStatusCd().equals("3") || item.getOrderStatusCd().equals("2");
        btn.setImageResource(flag?R.drawable.view:R.drawable.no_view);
        btn.setEnabled(flag ? true : false);
//        Context context=btn.getContext();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentReportDialog fragmentReportDialog = new FragmentReportDialog(item.getOrderCode(), item.getOrderYear());
                FragmentTransaction ft = ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                fragmentReportDialog.show(ft, "Report Fragment");

            }
        });
    }

    @Override
    public int getItemCount() {
        return RadResList.size();
    }

    class RadResViewHolder extends RecyclerView.ViewHolder {
        TextView txt_Date, txt_photo_type, txt_photo_status;
        ImageButton btn_view_report;

        public RadResViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Date = itemView.findViewById(R.id.txt_Date_);
            txt_photo_type = itemView.findViewById(R.id.txt_photo_type);
            txt_photo_status = itemView.findViewById(R.id.txt_photo_status);
            btn_view_report = itemView.findViewById(R.id.btn_view_report);
        }
    }
}
