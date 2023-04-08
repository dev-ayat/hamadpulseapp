package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.NewProtocolDataAdapter;
import com.moh.hamadpulse.models.ExternalMedicineModel;
import com.moh.hamadpulse.models.NewChemotherapyModel;
import com.moh.hamadpulse.models.PostDrugsModel;
import com.moh.hamadpulse.models.PreDrugsModel;


public class EditTextDialogFragment extends DialogFragment {

    Object item;
    NewProtocolDataAdapter adapter;

    public EditTextDialogFragment(Object item, NewProtocolDataAdapter adapter) {
        this.item = item;
        this.adapter = adapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_text_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.txt_view_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (item instanceof PreDrugsModel)
                    ((PreDrugsModel) item).setSpecialInstructions(charSequence + "");
                else if (item instanceof PostDrugsModel)
                    ((PostDrugsModel) item).setSpecialInstructions(charSequence + "");
                else if (item instanceof NewChemotherapyModel)
                    ((NewChemotherapyModel) item).setSpecialInstructions(charSequence + "");
                else if ((item instanceof ExternalMedicineModel))
                    ((ExternalMedicineModel) item).setNote(charSequence + "");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (item instanceof PreDrugsModel)
            editText.setText(((PreDrugsModel) item).getSpecialInstructions());
        else if (item instanceof PostDrugsModel)
            editText.setText(((PostDrugsModel) item).getSpecialInstructions());

        else if ((item instanceof NewChemotherapyModel))
            editText.setText(((NewChemotherapyModel) item).getSpecialInstructions());
        else if ((item instanceof ExternalMedicineModel))
            editText.setText(((ExternalMedicineModel) item).getNote());
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

    @Override
    public void onDetach() {
        super.onDetach();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}