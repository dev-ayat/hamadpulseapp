package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.moh.hamadpulse.FragmentPharmAdd;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.Adapter_old_medicines;
import com.moh.hamadpulse.models.DocPharmacyDetails;

import java.util.ArrayList;


public class DialogFragment_View_Older_Medicines extends DialogFragment {
    RecyclerView rv_old_medicines;
    MaterialButton btn_save;
    ArrayList<DocPharmacyDetails> old_medicines_list;
    FragmentPharmAdd fragmentPharmAdd;
    LinearLayout layout;

    public DialogFragment_View_Older_Medicines(
            ArrayList<DocPharmacyDetails> old_medicines_list
            , FragmentPharmAdd fragmentPharmAdd) {
        this.old_medicines_list = old_medicines_list;
        this.fragmentPharmAdd = fragmentPharmAdd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog__view__older__medicen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_old_medicines = view.findViewById(R.id.rv_old_medicines);
        btn_save = view.findViewById(R.id.btn_save);
        layout = view.findViewById(R.id.noPreviousMed);
        if (old_medicines_list.size() > 0) {
            Adapter_old_medicines adapter = new Adapter_old_medicines(old_medicines_list);
            rv_old_medicines.setAdapter(adapter);
            rv_old_medicines.setLayoutManager(new LinearLayoutManager(getContext()));
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentPharmAdd.refresh(null);
                    dismiss();
                }
            });
        } else {
            layout.setVisibility(View.VISIBLE);
            rv_old_medicines.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }
}