package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetMedicineConst;

import java.util.ArrayList;

public class UserFavMedAdapter extends RecyclerView.Adapter<UserFavMedAdapter.UserFavMedViewHolder>{

    ArrayList<GetMedicineConst> list=new ArrayList<>();

    public UserFavMedAdapter(ArrayList<GetMedicineConst> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserFavMedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          return new UserFavMedAdapter.UserFavMedViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_user_fav_med, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserFavMedViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getITEMNAME());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserFavMedViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public UserFavMedViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.med_name_tv);
        }
    }
}
