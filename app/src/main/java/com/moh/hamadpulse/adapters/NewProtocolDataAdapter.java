package com.moh.hamadpulse.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.EditTextDialogFragment;
import com.moh.hamadpulse.models.GetMedicineConst;
import com.moh.hamadpulse.models.NewChemotherapyModel;
import com.moh.hamadpulse.models.PostDrugsModel;
import com.moh.hamadpulse.models.PreDrugsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewProtocolDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<NewChemotherapyModel> chemotherapyModels;
    ArrayList<PreDrugsModel> preDrugsModels;
    ArrayList<PostDrugsModel> postDrugModels;
    NewProtocolDataAdapter adapter;
    Context context;

    public NewProtocolDataAdapter(ArrayList<NewChemotherapyModel> chemotherapyModels,
                                  ArrayList<PreDrugsModel> preDrugsModels,
                                  ArrayList<PostDrugsModel> postDrugModels) {//,int type
        this.chemotherapyModels = chemotherapyModels;
        this.preDrugsModels = preDrugsModels;
        this.postDrugModels = postDrugModels;
    }

    public ArrayList<NewChemotherapyModel> getChemotherapyModels() {
        return chemotherapyModels;
    }

    public ArrayList<PreDrugsModel> getPreDrugsModels() {
        return preDrugsModels;
    }

    public ArrayList<PostDrugsModel> getPostDrugModels() {
        return postDrugModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return viewType == 0 ? new NewProtocolDataViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_new_protocol_drugs, parent, false)) :
                new AddProtocolDataViewHolder(LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.row_new_drugs, parent, false));

    }

    @Override
    public int getItemViewType(int position) {
        int type = Integer.parseInt(chemotherapyModels != null ?
                chemotherapyModels.get(position).getType() :
                preDrugsModels != null ? preDrugsModels.get(position).getType() :
                        postDrugModels.get(position).getType());
        return type;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        adapter = this;
        context = viewholder.itemView.getContext();
        if (viewholder.getItemViewType() == 0) {
            NewProtocolDataViewHolder holder = (NewProtocolDataViewHolder) viewholder;
            if (chemotherapyModels != null) {
                NewChemotherapyModel item = chemotherapyModels.get(position);
                EditText txti_Drug = holder.txti_Drug;
                EditText txti_Dosing = holder.txti_Dosing;
                EditText txti_DoseMOD = holder.txti_DoseMOD;
                EditText txti_FinalDose = holder.txti_FinalDose;
                EditText txti_Route = holder.txti_Route;
                EditText txti_Frequency = holder.txti_Frequency;
                txti_Drug.setText(item.getDrug());
                txti_Dosing.setText(item.getDosing());
                txti_DoseMOD.setText("100");
                txti_FinalDose.setText(item.getFinalDose());
                txti_Route.setText(item.getRoute());
                txti_Frequency.setText(item.getFrequency());
                // store value when user edit edittext
                txti_Drug.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDrug(txti_Drug.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Dosing.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDosing(txti_Dosing.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_FinalDose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (!b) {
                            item.setFinalDose(txti_FinalDose.getText().toString().trim());
                        }
                    }
                });
                txti_Route.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setRoute(txti_Route.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Frequency.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setFrequency(txti_Frequency.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

//                txti_DoseMOD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View view, boolean b) {
//                        if(!b){
//                            item.setDosi(txti_DoseMOD.getText().toString().trim());
//                        }
//                    }
//                });
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
//              give text view colors when is there special instructions to view
                TextView special_instruction = holder.txti_SpecialInstructions;
                if (item.getSpecialInstructions() != null && !item.getSpecialInstructions().isEmpty()) {
                    special_instruction.setTextColor(context.getResources().getColor(R.color.white));
                    special_instruction.setBackground
                            (context.getResources().getDrawable(R.drawable.clickable_shape));
                } else {
                    special_instruction.setTextColor(Color.BLACK);
                }
                special_instruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(view.getContext()).setTitle("Warning message").
                                setMessage("Confirm delete operation").setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                chemotherapyModels.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                    }
                });
            } else if (preDrugsModels != null) {
                PreDrugsModel item = preDrugsModels.get(position);
                EditText txti_Drug = holder.txti_Drug;
                EditText txti_Dosing = holder.txti_Dosing;
                EditText txti_DoseMOD = holder.txti_DoseMOD;
                EditText txti_FinalDose = holder.txti_FinalDose;
                EditText txti_Route = holder.txti_Route;
                EditText txti_Frequency = holder.txti_Frequency;
                txti_Drug.setText(item.getDrug());
                txti_Dosing.setText(item.getDosing());
                txti_DoseMOD.setText("100");
                txti_FinalDose.setText(item.getFinalDose());
                txti_Route.setText(item.getRoute());
                txti_Frequency.setText(item.getFrequency());
                // store value when user edit edittext
                txti_Drug.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDrug(txti_Drug.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Dosing.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDosing(txti_Dosing.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_FinalDose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (!b) {
                            item.setFinalDose(txti_FinalDose.getText().toString().trim());
                        }
                    }
                });
                txti_Route.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setRoute(txti_Route.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Frequency.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setFrequency(txti_Frequency.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
//                give text view colors when is there special instructions to view
                TextView special_instruction = holder.txti_SpecialInstructions;
                if (item.getSpecialInstructions() != null || item.getSpecialInstructions().isEmpty()) {
                    special_instruction.setTextColor(context.getResources().getColor(R.color.white));
                    special_instruction.setBackground
                            (context.getResources().getDrawable(R.drawable.clickable_shape));
                } else {
                    special_instruction.setTextColor(Color.BLACK);
                }
                special_instruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(view.getContext()).setTitle("Warning message").
                                setMessage("Confirm delete operation").setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                preDrugsModels.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                    }
                });
            } else {
                PostDrugsModel item = postDrugModels.get(position);
                EditText txti_Drug = holder.txti_Drug;
                EditText txti_Dosing = holder.txti_Dosing;
                EditText txti_DoseMOD = holder.txti_DoseMOD;
                EditText txti_FinalDose = holder.txti_FinalDose;
                EditText txti_Route = holder.txti_Route;
                EditText txti_Frequency = holder.txti_Frequency;
                txti_Drug.setText(item.getDrug());
                txti_Dosing.setText(item.getDosing());
                txti_DoseMOD.setText("100");
                txti_FinalDose.setText(item.getFinalDose());
                txti_Route.setText(item.getRoute());
                txti_Frequency.setText(item.getFrequency());
                // store value when user edit edittext
                txti_Drug.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDrug(txti_Drug.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Dosing.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setDosing(txti_Dosing.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_FinalDose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (!b) {
                            item.setFinalDose(txti_FinalDose.getText().toString().trim());
                        }
                    }
                });
                txti_Route.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setRoute(txti_Route.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                txti_Frequency.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        item.setFrequency(txti_Frequency.getText().toString().trim());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
//                give text view colors when is there special instructions to view
                TextView special_instruction = holder.txti_SpecialInstructions;
                if (item.getSpecialInstructions() != null || item.getSpecialInstructions().isEmpty()) {
                    special_instruction.setTextColor(context.getResources().getColor(R.color.white));
                    special_instruction.setBackground
                            (context.getResources().getDrawable(R.drawable.clickable_shape));
                } else {
                    special_instruction.setTextColor(Color.BLACK);
                }
                holder.txti_SpecialInstructions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(view.getContext()).setTitle("Warning message").
                                setMessage("Confirm delete operation").setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                postDrugModels.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                    }
                });
            }
        } else {
            AddProtocolDataViewHolder holder = (AddProtocolDataViewHolder) viewholder;
            if (chemotherapyModels != null) {
                NewChemotherapyModel item = chemotherapyModels.get(position);
                AutoCompleteTextView drugs_tv = holder.txt_auto_drug;
                drugs_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GetMedicineConst medicinConstSelected = (GetMedicineConst) parent.getItemAtPosition(position);
                        drugs_tv.setText(medicinConstSelected.getITEMNAME());
                        item.setDrugNo(medicinConstSelected.getITEMCODE());
                    }
                });
                drugs_tv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().isEmpty())
                            getMedicin(charSequence.toString(), drugs_tv);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                holder.txti_SpecialInstructions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (drugs_tv.getText().toString().isEmpty() ||
                                holder.txti_Dosing.toString().isEmpty() ||
                                holder.txti_DoseMOD.toString().isEmpty() ||
                                holder.txti_FinalDose.toString().isEmpty() ||
                                holder.txti_Route.toString().isEmpty() ||
                                holder.txti_Frequency.toString().isEmpty()) {
                            Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show();
                            item.setDrug(drugs_tv.getText().toString());
                            item.setDosing(holder.txti_Dosing.getText().toString());
                            item.setFinalDose(holder.txti_FinalDose.getText().toString());
                            item.setRoute(holder.txti_Route.getText().toString());
                            item.setFrequency(holder.txti_Frequency.getText().toString());
                            item.setType("0");
                            holder.btn_delete_drug.setImageResource(R.drawable.no_available_medicine);
                            notifyDataSetChanged();
                        }
                    }
                });
            } else if (preDrugsModels != null) {
                PreDrugsModel item = preDrugsModels.get(position);
                AutoCompleteTextView drugs_tv = holder.txt_auto_drug;
                drugs_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GetMedicineConst medicinConstSelected = (GetMedicineConst) parent.getItemAtPosition(position);
                        drugs_tv.setText(medicinConstSelected.getITEMNAME());
                        item.setDrugNo(medicinConstSelected.getITEMCODE());
                    }
                });
                drugs_tv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        getMedicin(charSequence.toString(), drugs_tv);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
//                holder.txti_Drug.setText(item.getDrug());
//                holder.txti_Dosing.setText(item.getDosing());
//                holder.txti_DoseMOD.setText("100");
//                holder.txti_FinalDose.setText(item.getFinalDose());
//                holder.txti_Route.setText(item.getRoute());
//                holder.txti_Frequency.setText(item.getFrequency());
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                holder.txti_SpecialInstructions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (drugs_tv.getText().toString().isEmpty() ||
                                holder.txti_Dosing.toString().isEmpty() ||
                                holder.txti_DoseMOD.toString().isEmpty() ||
                                holder.txti_FinalDose.toString().isEmpty() ||
                                holder.txti_Route.toString().isEmpty() ||
                                holder.txti_Frequency.toString().isEmpty()) {
                            Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show();
                            item.setDrug(drugs_tv.getText().toString());
                            item.setDosing(holder.txti_Dosing.getText().toString());
                            item.setFinalDose(holder.txti_FinalDose.getText().toString());
                            item.setRoute(holder.txti_Route.getText().toString());
                            item.setFrequency(holder.txti_Frequency.getText().toString());
                            item.setType("0");
                            holder.btn_delete_drug.setImageResource(R.drawable.no_available_medicine);
                            notifyDataSetChanged();
                        }
                    }
                });
            } else {
                PostDrugsModel item = postDrugModels.get(position);
                AutoCompleteTextView drugs_tv = holder.txt_auto_drug;
                drugs_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GetMedicineConst medicinConstSelected = (GetMedicineConst) parent.getItemAtPosition(position);
                        drugs_tv.setText(medicinConstSelected.getITEMNAME());
                        item.setDrugNo(medicinConstSelected.getITEMCODE());
                    }
                });
                drugs_tv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        getMedicin(charSequence.toString(), drugs_tv);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
//                holder.txti_Drug.setText(item.getDrug());
//                holder.txti_Dosing.setText(item.getDosing());
//                holder.txti_DoseMOD.setText("100");
//                holder.txti_FinalDose.setText(item.getFinalDose());
//                holder.txti_Route.setText(item.getRoute());
//                holder.txti_Frequency.setText(item.getFrequency());
                holder.final_dose_sp.setOnItemSelectedListener
                        (new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 2)
                                    item.setDosingUnit(i + "");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                holder.txti_SpecialInstructions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).
                                getSupportFragmentManager().beginTransaction();
                        new EditTextDialogFragment(item, adapter)
                                .show(ft, "Special Instructions");
                    }
                });
                holder.btn_delete_drug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (drugs_tv.getText().toString().isEmpty() ||
                                holder.txti_Dosing.toString().isEmpty() ||
                                holder.txti_DoseMOD.toString().isEmpty() ||
                                holder.txti_FinalDose.toString().isEmpty() ||
                                holder.txti_Route.toString().isEmpty() ||
                                holder.txti_Frequency.toString().isEmpty()) {
                            Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show();
                            item.setDrug(drugs_tv.getText().toString());
                            item.setDosing(holder.txti_Dosing.getText().toString());
                            item.setFinalDose(holder.txti_FinalDose.getText().toString());
                            item.setRoute(holder.txti_Route.getText().toString());
                            item.setFrequency(holder.txti_Frequency.getText().toString());
                            item.setType("0");
                            holder.btn_delete_drug.setImageResource(R.drawable.no_available_medicine);
                            notifyDataSetChanged();
                        }


                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return chemotherapyModels != null ?
                chemotherapyModels.size() : preDrugsModels != null ? preDrugsModels.size() : postDrugModels.size();
    }

    class AddProtocolDataViewHolder extends RecyclerView.ViewHolder {
        EditText txti_Dosing, txti_DoseMOD, txti_FinalDose, txti_Route,
                txti_Frequency;
        TextView txti_SpecialInstructions;
        ImageButton btn_delete_drug;
        Spinner final_dose_sp;
        AutoCompleteTextView txt_auto_drug;

        public AddProtocolDataViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_auto_drug = itemView.findViewById(R.id.txti_Drug);
            txti_Dosing = itemView.findViewById(R.id.txti_Dosing);
            txti_DoseMOD = itemView.findViewById(R.id.txti_DoseMOD);
            txti_FinalDose = itemView.findViewById(R.id.txti_FinalDose);
            txti_Route = itemView.findViewById(R.id.txti_Route);
            txti_Frequency = itemView.findViewById(R.id.txti_Frequency);
            txti_SpecialInstructions = itemView.findViewById(R.id.txti_SpecialInstructions);
            btn_delete_drug = itemView.findViewById(R.id.btn_delete_drug);
            final_dose_sp = itemView.findViewById(R.id.final_dose_sp);
        }


    }

    class NewProtocolDataViewHolder extends RecyclerView.ViewHolder {
        EditText txti_Drug, txti_Dosing, txti_DoseMOD, txti_FinalDose, txti_Route,
                txti_Frequency;
        TextView txti_SpecialInstructions;
        ImageButton btn_delete_drug;
        Spinner final_dose_sp;
        AutoCompleteTextView txt_auto_drug;

        public NewProtocolDataViewHolder(@NonNull View itemView) {
            super(itemView);

            txti_Drug = itemView.findViewById(R.id.txti_Drug);
            txti_Dosing = itemView.findViewById(R.id.txti_Dosing);
            txti_DoseMOD = itemView.findViewById(R.id.txti_DoseMOD);
            txti_FinalDose = itemView.findViewById(R.id.txti_FinalDose);
            txti_Route = itemView.findViewById(R.id.txti_Route);
            txti_Frequency = itemView.findViewById(R.id.txti_Frequency);
            txti_SpecialInstructions = itemView.findViewById(R.id.txti_SpecialInstructions);
            btn_delete_drug = itemView.findViewById(R.id.btn_delete_drug);
            final_dose_sp = itemView.findViewById(R.id.final_dose_sp);
        }

    }

    public void getMedicin(String medicinName, AutoCompleteTextView autoDrugname) {
        Map<String, String> map = new HashMap<>();
        map.put("M_NAME", medicinName);
        map.put("P_LOC_CD", ((ActivityPatient) context)
                .getmCardviewDataModel().getLOC_CODE() + "");
        Log.e("map", "getMedicin: " + map.toString());
        MyRequest.makeRquest(context, Controller.GET_MEDICINS_CONSTATNS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    //  Toast.makeText(getContext(), ""+mJSONObject, Toast.LENGTH_SHORT).show();

                    ArrayList<Object> mListMedicine = gson.fromJson(mJSONObject.getString("ALL_MED"), new TypeToken<ArrayList<GetMedicineConst>>() {
                    }.getType());
                    AdapterSpinner mAdapterSpinnerMedicin = new AdapterSpinner(context, 0, mListMedicine);
                    autoDrugname.setAdapter(mAdapterSpinnerMedicin);
                    autoDrugname.setThreshold(0);
                    autoDrugname.showDropDown();
                    mAdapterSpinnerMedicin.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
            }
        });
    }

}
