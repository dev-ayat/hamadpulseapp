package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetAllargiesmodel;

import java.util.ArrayList;


public class AllergiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GetAllargiesmodel getAllargiesmodel;
    ArrayList<Object> mListObject;
    private ArrayList<GetAllargiesmodel> AllergiesList;
    private Context context;

    public AllergiesAdapter(ArrayList<GetAllargiesmodel> AllergiesList, Context context) {
        this.AllergiesList = AllergiesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View view = Inflater.inflate(R.layout.row_allargies, parent, false);
        return new protcolViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("protocol", "ayaaat");
        protcolViewholder viewholder = (AllergiesAdapter.protcolViewholder) holder;
        GetAllargiesmodel data = AllergiesList.get(position);
        viewholder.txt_allergy_name.setText(data.getAllergyName());
        viewholder.txt_allergy_type.setText(data.getAllergyTypeName());
        viewholder.txt_serverity.setText(data.getSeverityName());
        viewholder.txt_Reaction.setText(data.getReactionName());
        viewholder.txt_Note.setText(data.getNote() != null ? data.getNote() : "");


    }

    @Override
    public int getItemCount() {
        return AllergiesList.size();
    }

    public void setBloodList(ArrayList<GetAllargiesmodel> BloodList) {
        this.AllergiesList = BloodList;
    }

    public class protcolViewholder extends RecyclerView.ViewHolder {
        TextView txt_allergy_name, txt_allergy_type, txt_serverity, txt_Reaction, txt_Note;

        public protcolViewholder(@NonNull View itemView) {
            super(itemView);

            this.txt_allergy_name = (TextView) itemView.findViewById(R.id.txt_allergy_name);
            this.txt_allergy_type = (TextView) itemView.findViewById(R.id.txt_allergy_type);
            this.txt_serverity = (TextView) itemView.findViewById(R.id.txt_serverity);
            this.txt_Reaction = (TextView) itemView.findViewById(R.id.txt_Reaction);
            this.txt_Note = (TextView) itemView.findViewById(R.id.txt_Note);

        }
    }
}
