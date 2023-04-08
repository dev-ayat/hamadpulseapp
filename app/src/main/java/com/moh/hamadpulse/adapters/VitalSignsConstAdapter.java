package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetVitalSignsConst;

import java.util.ArrayList;


public class VitalSignsConstAdapter extends RecyclerView.Adapter<VitalSignsConstAdapter.ViewHolder> {
    public String statuscdselected;
    Context context;
    ArrayList<GetVitalSignsConst> vitalSignsConstArray;
    private int lastSelectedPosition = -1;


    public VitalSignsConstAdapter(Context context, ArrayList<GetVitalSignsConst> vitalSignsConstArray) {
        this.context = context;
        this.vitalSignsConstArray = vitalSignsConstArray;
        Log.e("signsarray", "size=" + vitalSignsConstArray.size());
    }


    @NonNull
    @Override
    public VitalSignsConstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vital_signs_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VitalSignsConstAdapter.ViewHolder holder, final int position) {
        holder.vitalsign_cd.setText(vitalSignsConstArray.get(position).getVSCODE());
        holder.vital_sign_name.setHint(vitalSignsConstArray.get(position).getVSNAME());
        holder.txti_vitalsign.setTag(position);
        holder.txti_vitalsign.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (holder.txti_vitalsign.getTag().equals(position))
                    vitalSignsConstArray.get(position).setvSValuePost(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return vitalSignsConstArray.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vitalsign_cd;
        TextInputLayout vital_sign_name;
        TextInputEditText txti_vitalsign;

        public ViewHolder(View itemView) {
            super(itemView);
            vitalsign_cd = (TextView) itemView.findViewById(R.id.txt_vitalsign_cd);
            vital_sign_name = itemView.findViewById(R.id.vital_sign_name);
            txti_vitalsign = itemView.findViewById(R.id.txti_vitalsign);


        }
    }

}
