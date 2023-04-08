package com.moh.hamadpulse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.ChangePositionModel;

import java.util.ArrayList;

public class AdapterChangePosition extends RecyclerView.Adapter<AdapterChangePosition.ChangePositionViewHolder> {
    ArrayList<ChangePositionModel> changePositionList = new ArrayList<>();

    public AdapterChangePosition(ArrayList<ChangePositionModel> changePositionList) {
        this.changePositionList = changePositionList;
    }

    public ArrayList<ChangePositionModel> getchangePositionList() {
        return changePositionList;
    }

    public void setchangePositionList(ArrayList<ChangePositionModel> changePositionList) {
        this.changePositionList = changePositionList;
    }

    @NonNull
    @Override
    public ChangePositionViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChangePositionViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_change_position, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ChangePositionViewHolder holder, int position) {
        ChangePositionModel item = changePositionList.get(position);
//        Log.d("positions_list_",item.getAll_position_list().toString());
        holder.Rt_8_Am.setChecked(item.getInpChRt8am());
        holder.Lt_10_Am.setChecked(item.getInpChLt10am());
        holder.Bk_12_MD.setChecked(item.getInpChBack12md());
        holder.Rt_2_Pm.setChecked(item.getInpChRt2pm());
        holder.Lt_4_Pm.setChecked(item.getInpChLt4pm());
        holder.Bk_6_Pm.setChecked(item.getInpChBack6pm());
        holder.Rt_8_Pm.setChecked(item.getInpChRt8pm());
        holder.Lt_10_Pm.setChecked(item.getInpChLt10pm());
        holder.Bk_12_MN.setChecked(item.getInpChBack12mn());
        holder.Rt_2_Am.setChecked(item.getInpChRt2am());
        holder.Lt_4_Am.setChecked(item.getInpChLt4am());
        holder.Bk_6_Am.setChecked(item.getInpChBack6am());
        holder.date.setText(item.getInpChCreatedOn());
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                //sent object to be update
//                Bundle b = new Bundle();
//                b.putSerializable("item", item);
//                Add_Changing_Position add_changing_position = new Add_Changing_Position();
//                add_changing_position.setArguments(b);
//                ((ActivityPatient) holder.Bk_6_Am.getContext())
//                        .CallFragment(add_changing_position);
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return changePositionList.size();
    }

    class ChangePositionViewHolder extends RecyclerView.ViewHolder {
        CheckBox Rt_8_Am, Lt_10_Am, Bk_12_MD, Rt_2_Pm, Lt_4_Pm,
                Bk_6_Pm, Rt_8_Pm, Lt_10_Pm, Bk_12_MN,
                Rt_2_Am, Lt_4_Am, Bk_6_Am;
        TextView date;

        public ChangePositionViewHolder(@NonNull View itemView) {
            super(itemView);
            Rt_8_Am = itemView.findViewById(R.id.CB_Rt_8Am);
            Lt_10_Am = itemView.findViewById(R.id.CB_Lt_10Am);
            Bk_12_MD = itemView.findViewById(R.id.CB_BK_12MD);
            Rt_2_Pm = itemView.findViewById(R.id.CB_Rt_2Pm);
            Lt_4_Pm = itemView.findViewById(R.id.CB_Lt_4Pm);
            Bk_6_Pm = itemView.findViewById(R.id.CB_BK_6Pm);
            Rt_8_Pm = itemView.findViewById(R.id.CB_Rt_8Pm);
            Lt_10_Pm = itemView.findViewById(R.id.CB_Lt_10Pm);
            Bk_12_MN = itemView.findViewById(R.id.CB_BK_12MN);
            Rt_2_Am = itemView.findViewById(R.id.CB_RT_2AM);
            Lt_4_Am = itemView.findViewById(R.id.CB_LT_4AM);
            Bk_6_Am = itemView.findViewById(R.id.CB_BK_6AM);
            date = itemView.findViewById(R.id.txtDate);

        }
    }
}
