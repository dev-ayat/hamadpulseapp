package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.CoronaHistory;

import java.util.ArrayList;

public class AdapterHistoryCorona extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<CoronaHistory> mListCoronaHistory;

    public AdapterHistoryCorona(ArrayList<CoronaHistory> mListCoronaHistory) {
        this.mListCoronaHistory = mListCoronaHistory;
    }

    public ArrayList<CoronaHistory> getmListCoronaHistory() {
        return mListCoronaHistory;
    }

    public void setmListCoronaHistory(ArrayList<CoronaHistory> mListCoronaHistory) {
        this.mListCoronaHistory = mListCoronaHistory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_corona_history, parent, false);
        return new HolderCoronaHistory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int seq = position + 1;
        CoronaHistory mCoronaHistory = mListCoronaHistory.get(position);
        HolderCoronaHistory mHolderCoronaHistory = (HolderCoronaHistory)holder;
        ((HolderCoronaHistory) holder).txtSeq.setText("#"+seq);
        ((HolderCoronaHistory) holder).txtRequestDate.setText(mCoronaHistory.getREQUEST_DATE());
        ((HolderCoronaHistory) holder).txtResultDate.setText(mCoronaHistory.getRESULT_DATE());
        ((HolderCoronaHistory) holder).txtTestResult.setText(mCoronaHistory.getTEST_RESULT());
    }

    @Override
    public int getItemCount() {
        return mListCoronaHistory.size();
    }

    public class HolderCoronaHistory extends RecyclerView.ViewHolder {
        //View subItem;
        private TextView txtRequestDate;
        private TextView txtResultDate;
        private TextView txtTestResult;
        private TextView txtSeq;
        public HolderCoronaHistory(View itemView) {
            super(itemView);
            txtSeq = itemView.findViewById(R.id.txtSeq);
            txtRequestDate = itemView.findViewById(R.id.txtRequestDate);
            txtResultDate = itemView.findViewById(R.id.txtResultDate);
            txtTestResult = itemView.findViewById(R.id.txtTestResult);

        }
    }
}
