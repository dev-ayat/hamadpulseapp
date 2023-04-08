package com.moh.hamadpulse.adapters;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.GetNursingDepts;

import java.util.List;

public class NusreDepatmentsAdapter extends RecyclerView.Adapter<NusreDepatmentsAdapter.MyViewHolder> {

    public String deptcdselected="";
    private List<GetNursingDepts> deptsList;
    private int lastSelectedPosition = -1;


    public NusreDepatmentsAdapter(List<GetNursingDepts> deptsList) {
        this.deptsList = deptsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_nurse_dept_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetNursingDepts dept = deptsList.get(position);
        holder.deptnameradio.setText(dept.getLOCNAMEAR());
        holder.deptcd.setText(dept.getLOCCODE());
        holder.deptnameradio.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return deptsList.size();
    }

    public void updateDeptsdata(List<GetNursingDepts> newdeptsList) {
        deptsList.clear();
        deptsList.addAll(newdeptsList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RadioButton deptnameradio;
        public TextView deptcd;
        public Button btndeptlist;

        public MyViewHolder(View view) {
            super(view);
            deptnameradio = (RadioButton) view.findViewById(R.id.nursedeptnameradio);
            deptcd = (TextView) view.findViewById(R.id.txtnursedeptcd);
          //  btndeptlist = (Button) view.findViewById(R.id.btn_nursedeptlist);
            deptnameradio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    Log.d("API123", "selected :" + deptcd.getText());
                    deptcdselected = (String) deptcd.getText();
                    Log.d("var", "id:" + deptcdselected);
                    if (Controller.pref.getString(USER_TYPE, "").equals("3")) {  // doctor
                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", deptcdselected);
                        Controller.editor.putString("DEPT_CD_SELEC", "");
                        Controller.editor.commit();
                    }else{
                        Controller.editor.putString("NURSE_DEPT_CD_SELEC", deptcdselected);
                        Controller.editor.commit();
                    }

                    Log.d("ayat2", "shared :" + Controller.pref.getString("NURSE_DEPT_CD_SELEC", ""));


                }
            });
        }
    }


}