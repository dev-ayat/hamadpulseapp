package com.moh.hamadpulse.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ProtocolLabResultModel;

import java.util.ArrayList;

public class ProtocolLabResultAdapter extends RecyclerView.Adapter<ProtocolLabResultAdapter.ProtocolLabResultViewHolder> {
    ArrayList<String> lab_tests_name = new ArrayList<>();
    ProtocolLabResultModel model;

    public ProtocolLabResultAdapter(ArrayList<String> lab_tests_name, ProtocolLabResultModel model) {
        this.lab_tests_name = lab_tests_name;
        this.model = model;
    }

    public ArrayList<String> getlab_tests_name() {
        return lab_tests_name;
    }

    public void setlab_tests_name(ArrayList<String> lab_tests_name) {
        this.lab_tests_name = lab_tests_name;
    }

    @NonNull
    @Override
    public ProtocolLabResultViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProtocolLabResultViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.protocol_lab_result_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProtocolLabResultViewHolder holder, int position) {
        if (position == 0) {
            holder.txt_first.setText(model.getWbcVal());
            holder.txt_second.setText(model.getGranVal());
            holder.txt_third.setText(model.getWbcVal());
            holder.txt_fourth.setText(model.getHbVal());
            holder.itemView.setBackgroundColor(Color.parseColor("#f6d2df"));
        } else if (position == 1) {
            holder.lab_order_card.getLayoutParams().width = 400;
            holder.txt_lab_name.setText("KIDNEY FUNCTION");
            holder.txt_first_title.setText("SERUM CREATININE");
            holder.txt_second_title.setVisibility(View.GONE);
            holder.txt_third_title.setVisibility(View.GONE);
            holder.txt_fourth_title.setVisibility(View.GONE);
            holder.txt_first.setText(model.getSerumCreatinineVal());
            holder.txt_second.setVisibility(View.GONE);
            holder.txt_third.setVisibility(View.GONE);
            holder.txt_fourth.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.parseColor("#c6eaf5"));
        } else {
            holder.txt_lab_name.setText("LFT");
            holder.txt_first_title.setText("BILIRUBIN");
            holder.txt_second_title.setText("ALP");
            holder.txt_third_title.setText("ALT");
            holder.txt_fourth_title.setText("AST");
            holder.txt_first.setText(model.getBilirubinVal());
            holder.txt_second.setText(model.getAlpVal() == null ? "" : model.getAlpVal());
            holder.txt_third.setText(model.getAltVal() == null ? "" : model.getAltVal());
            holder.txt_fourth.setText(model.getAstVal() == null ? "" : model.getAstVal());
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffd5"));
        }
    }

    @Override
    public int getItemCount() {
        return lab_tests_name.size();
    }

    class ProtocolLabResultViewHolder extends RecyclerView.ViewHolder {
        TextView txt_lab_name, txt_first_title, txt_second_title,
                txt_third_title, txt_fourth_title, txt_first, txt_second,
                txt_third, txt_fourth;
        MaterialCardView lab_order_card;

        public ProtocolLabResultViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_lab_name = itemView.findViewById(R.id.txt_lab_name);
            txt_first_title = itemView.findViewById(R.id.txt_first_title);
            txt_second_title = itemView.findViewById(R.id.txt_second_title);
            txt_third_title = itemView.findViewById(R.id.txt_third_title);
            txt_fourth_title = itemView.findViewById(R.id.txt_fourth_title);
            txt_first = itemView.findViewById(R.id.txt_first);
            txt_second = itemView.findViewById(R.id.txt_second);
            txt_third = itemView.findViewById(R.id.txt_third);
            txt_fourth = itemView.findViewById(R.id.txt_fourth);
            lab_order_card = itemView.findViewById(R.id.lab_order_card);
        }
    }
}
