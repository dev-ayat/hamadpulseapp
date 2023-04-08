package com.moh.hamadpulse.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.FavMedModel;
import com.moh.hamadpulse.models.PharmMedModel;

import java.util.ArrayList;

public class Fav_Med_Adapter extends RecyclerView.Adapter<Fav_Med_Adapter.Fav_Med_View_Holder> {
    private ArrayList<PharmMedModel> list = new ArrayList<>();
    private ArrayList<FavMedModel> favList = new ArrayList<>();
    private ArrayList<PharmMedModel> newItem = new ArrayList<>();
    private ArrayList<PharmMedModel> removedItem = new ArrayList<>();

    public Fav_Med_Adapter(ArrayList<PharmMedModel> list, ArrayList<FavMedModel> favList) {
        this.list = list;
        this.favList = favList;
    }

    public ArrayList<PharmMedModel> getNewItem() {
        return newItem;
    }

    public void setNewItem(ArrayList<PharmMedModel> newItem) {
        this.newItem = newItem;
    }

    public ArrayList<PharmMedModel> getRemovedItem() {
        return removedItem;
    }

    public void setRemovedItem(ArrayList<PharmMedModel> removedItem) {
        this.removedItem = removedItem;
    }

    public ArrayList<PharmMedModel> getList() {
        return list;
    }

    public void setList(ArrayList<PharmMedModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Fav_Med_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Fav_Med_View_Holder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.fav_med_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Fav_Med_View_Holder holder, int position) {
        PharmMedModel pharmMedModel = list.get(position);
        holder.med_name_tv.setText(pharmMedModel.getItemName());
        ImageButton star_btn = holder.fav_med_btn;
        if (favList.contains(new FavMedModel(pharmMedModel.getMedMCode()))) {
            Log.d("fav_med", pharmMedModel.getMedMCode());
            star_btn.setImageResource(R.drawable.fav_med);
            pharmMedModel.setIs_fav(true);
        } else {
            star_btn.setImageResource(R.drawable.unfav_med);
        }
        star_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pharmMedModel.isIs_fav()) {
                    star_btn.setImageResource(R.drawable.unfav_med);
                    pharmMedModel.setIs_fav(false);
                    removedItem.add(pharmMedModel);
                    if (newItem.contains(pharmMedModel))
                        newItem.remove(newItem.indexOf(pharmMedModel));
                } else {
                    star_btn.setImageResource(R.drawable.fav_med);
                    pharmMedModel.setIs_fav(true);
                    newItem.add(pharmMedModel);
                    if (removedItem.contains(pharmMedModel))
                        removedItem.remove(removedItem.indexOf(pharmMedModel));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Fav_Med_View_Holder extends RecyclerView.ViewHolder {
        TextView med_name_tv;
        ImageButton fav_med_btn;

        public Fav_Med_View_Holder(@NonNull View itemView) {
            super(itemView);
            med_name_tv = itemView.findViewById(R.id.med_name_tv);
            fav_med_btn = itemView.findViewById(R.id.fav_med_btn);
        }
    }
}
