package com.moh.hamadpulse.adapters;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.StutasMedicationConfirmDialog;
import com.moh.hamadpulse.fragment.TextViewFragment;
import com.moh.hamadpulse.models.ChemothropyModel;
import com.moh.hamadpulse.models.PostCurModel;
import com.moh.hamadpulse.models.PreCurModel;
import com.moh.hamadpulse.models.TumerRepModel;

import java.util.ArrayList;

public class Adapter_Pre_Post_Chemothropy extends RecyclerView.Adapter<Adapter_Pre_Post_Chemothropy.PrePostChemothropyViewHolder> {
    ArrayList<ChemothropyModel> ChemothropyList = new ArrayList<>();
    ArrayList<PreCurModel> PreCurList = new ArrayList<>();
    ArrayList<PostCurModel> PostCurList = new ArrayList<>();
    TumerRepModel model;

    public Adapter_Pre_Post_Chemothropy(ArrayList<ChemothropyModel> ChemothropyList
            , ArrayList<PreCurModel> PreCurList, ArrayList<PostCurModel> PostCurList, TumerRepModel model) {
        this.ChemothropyList = ChemothropyList;
        this.PreCurList = PreCurList;
        this.PostCurList = PostCurList;
        this.model = model;
    }

    public ArrayList<ChemothropyModel> getChemothropyList() {
        return ChemothropyList;
    }

    public void setChemothropyList(ArrayList<ChemothropyModel> chemothropyList) {
        ChemothropyList = chemothropyList;
    }

    public ArrayList<PreCurModel> getPreCurList() {
        return PreCurList;
    }

    public void setPreCurList(ArrayList<PreCurModel> preCurList) {
        PreCurList = preCurList;
    }

    public ArrayList<PostCurModel> getPostCurList() {
        return PostCurList;
    }

    public void setPostCurList(ArrayList<PostCurModel> postCurList) {
        PostCurList = postCurList;
    }

    @NonNull
    @Override
    public PrePostChemothropyViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrePostChemothropyViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_chemothropy, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PrePostChemothropyViewHolder holder, int position) {
        if(Controller.pref.getString(USER_TYPE, "").equals("5"))
            holder.action_SP.setEnabled(false);
        if (ChemothropyList != null) {

            ChemothropyModel item = ChemothropyList.get(position);
            holder.txti_Drug.setText(item.getDrug());
            holder.txti_Dosing.setText(item.getDosing());
            holder.txti_DoseMOD.setText(item.getDoseMod());
            holder.txti_FinalDose.setText(item.getFinalDose());
            holder.txti_Route.setText(item.getRoute());
            holder.txti_Frequency.setText(item.getFrequency());
            holder.txti_SpecialInstructions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                            getSupportFragmentManager().beginTransaction();
                    new TextViewFragment(item.getSpecialInstructions())
                            .show(ft, "Special Instructions");
                }
            });
            holder.action_SP.setSelection(Integer.parseInt
                    (item.getStatus() == null ? "0" : item.getStatus()));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.action_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) {
                                FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                        getSupportFragmentManager().beginTransaction();
                                new StutasMedicationConfirmDialog(item, 2, i).show(ft, "StutasMedicationConfirmDialog");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }, 500);
        } else if (PreCurList != null) {
            PreCurModel item = PreCurList.get(position);
            holder.txti_Drug.setText(item.getDrug());
            holder.txti_Dosing.setText(item.getDosing());
            holder.txti_DoseMOD.setText(item.getDoseMod());
            holder.txti_FinalDose.setText(item.getFinalDose());
            holder.txti_Route.setText(item.getRoute());
            holder.txti_Frequency.setText(item.getFrequency());
            holder.txti_SpecialInstructions.setText(item.getSpecialInstructions());
            holder.action_SP.setSelection(Integer.parseInt
                    (item.getStatus() == null ? "0" : item.getStatus()));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.action_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) {
                                FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                        getSupportFragmentManager().beginTransaction();
                                new StutasMedicationConfirmDialog(item, 1, i).show(ft, "StutasMedicationConfirmDialog");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }, 500);


        } else {
            PostCurModel item = PostCurList.get(position);
            holder.txti_Drug.setText(item.getDrug());
            holder.txti_Dosing.setText(item.getDosing());
            holder.txti_DoseMOD.setText(item.getDoseMod());
            holder.txti_FinalDose.setText(item.getFinalDose());
            holder.txti_Route.setText(item.getRoute());
            holder.txti_Frequency.setText(item.getFrequency());
            holder.txti_SpecialInstructions.setText(item.getSpecialInstructions());
//            if(item.getStatus()!=null)
//                holder.action_SP.setSelection(Integer.parseInt(item.getStatus()));
            holder.action_SP.setSelection(Integer.parseInt
                    (item.getStatus() == null ? "0" : item.getStatus()));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.action_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) {
                                FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                        getSupportFragmentManager().beginTransaction();
                                new StutasMedicationConfirmDialog(item, 3, i).show(ft, "StutasMedicationConfirmDialog");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }, 500);


        }


    }

    @Override
    public int getItemCount() {
        return ChemothropyList != null ?
                ChemothropyList.size() : PreCurList != null ? PreCurList.size() : PostCurList.size();
    }

    class PrePostChemothropyViewHolder extends RecyclerView.ViewHolder {
        TextView txti_Drug, txti_Dosing, txti_DoseMOD, txti_FinalDose, txti_Route,
                txti_Frequency, txti_SpecialInstructions;
        Spinner action_SP;

        public PrePostChemothropyViewHolder(@NonNull View itemView) {
            super(itemView);
            txti_Drug = itemView.findViewById(R.id.txti_Drug);
            txti_Dosing = itemView.findViewById(R.id.txti_Dosing);
            txti_DoseMOD = itemView.findViewById(R.id.txti_DoseMOD);
            txti_FinalDose = itemView.findViewById(R.id.txti_FinalDose);
            txti_Route = itemView.findViewById(R.id.txti_Route);
            txti_Frequency = itemView.findViewById(R.id.txti_Frequency);
            txti_SpecialInstructions = itemView.findViewById(R.id.txti_SpecialInstructions);
            action_SP = itemView.findViewById(R.id.PremedicationsAction_SP);
        }
    }
}
