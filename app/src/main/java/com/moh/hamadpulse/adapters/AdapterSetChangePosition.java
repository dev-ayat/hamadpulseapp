package com.moh.hamadpulse.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ChangePositionModel;
import com.moh.hamadpulse.models.SetChangePositionModel;

import java.util.ArrayList;

public class AdapterSetChangePosition extends RecyclerView.Adapter<AdapterSetChangePosition.setChangePositionViewHolder> {
    ArrayList<SetChangePositionModel> setChangePositionArrayList = new ArrayList<>();
    ArrayList<Boolean> positions = new ArrayList<>();
    ChangePositionModel _model;

    public AdapterSetChangePosition(
            ArrayList<SetChangePositionModel> setChangePositionArrayList,
            ArrayList<Boolean> positions) {
        this.setChangePositionArrayList = setChangePositionArrayList;
        this.positions = positions;
        Log.d("positions_list", positions.toString());
    }
//    public AdapterSetChangePosition(
//            ArrayList<SetChangePositionModel> setChangePositionArrayList,
//            ChangePositionModel _model){
//        this.setChangePositionArrayList = setChangePositionArrayList;
//        this._model=_model;
//    }


    public ArrayList<SetChangePositionModel> getsetChangePositionArrayList() {
        return setChangePositionArrayList;
    }

    public void setsetChangePositionArrayList(ArrayList<SetChangePositionModel> setChangePositionArrayList) {
        this.setChangePositionArrayList = setChangePositionArrayList;
    }

    public ArrayList<Boolean> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Boolean> positions) {
        this.positions = positions;
    }

    @NonNull
    @Override
    public setChangePositionViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new setChangePositionViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_set_change_position, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull setChangePositionViewHolder holder, int position) {
        SetChangePositionModel model = setChangePositionArrayList.get(position);
        holder.position.setText(model.getPosition());
        holder.time.setText(model.getTime());
        if (positions.size() != 0)
            holder.isChanged.setChecked(positions.get(position));
        holder.isChanged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                model.setCheckable(b);
                if (positions.size() != 0)
                    positions.set(position, b);
//              switch (position){
//                  case 0:
//                      _model.setRt_8_Am(b);
//                      break;
//                  case 1:
//                      _model.setLt_10_Am(b);
//                      break;
//                  case 2:
//                      _model.setBk_12_MD(b);
//                      break;
//                  case 3:
//                      _model.setRt_2_Pm(b);
//                      break;
//                  case 4:
//                      _model.setLt_4_Pm(b);
//                      break;
//                  case 5:
//                      _model.setBk_6_Pm(b);
//                      break;
//                  case 6:
//                      break;

//              }


            }

        });
        Log.d("positions_list", positions.toString());

    }

    @Override
    public int getItemCount() {
        return setChangePositionArrayList.size();
    }

    class setChangePositionViewHolder extends RecyclerView.ViewHolder {
        TextView position, time;
        CheckBox isChanged;

        public setChangePositionViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position_TV);
            time = itemView.findViewById(R.id.time_TV);
            isChanged = itemView.findViewById(R.id.CB_Position);
        }
    }
}
