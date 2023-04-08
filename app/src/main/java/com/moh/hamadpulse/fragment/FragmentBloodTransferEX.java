package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.UPDATE_BLOOD_ORDER_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetBloodTransfer;
import com.moh.hamadpulse.models.SpinCnt;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentBloodTransferEX extends DialogFragment {
    LinearLayout llCloseLabResult;
    GetBloodTransfer getBloodTransfer;
    ArrayList<Object> mListObject;
    AVLoadingIndicatorView imgLoading;
    Spinner Procedurespinner;
    TextInputEditText txti_notes;
    String spinnerval,NURSING_NOTE;
    Button btn_add_note;
    InterfacePatient mInterfacePatient;
    ImageView close;
    private TextView idOrderLab, idDateLab;
    String fragment_cd = "33";

    public FragmentBloodTransferEX (GetBloodTransfer getBloodTransfer, ArrayList<Object> mListObject) {
        // Required empty public constructor
        this.getBloodTransfer = getBloodTransfer;
        this.mListObject = mListObject;
    }

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_execution, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idOrderLab = view.findViewById(R.id.idOrderLab);
        idDateLab = view.findViewById(R.id.idDateLab);
        imgLoading = view.findViewById(R.id.imgLoading);
        llCloseLabResult = view.findViewById(R.id.llCloseLabResult);
        txti_notes = view.findViewById(R.id.txti_notes);
        btn_add_note = view.findViewById(R.id.btn_add_note);
        close = view.findViewById(R.id.close);
        Procedurespinner = (Spinner) view.findViewById(R.id.ProcedureSpinner);
        mListObject = new ArrayList<>();
        idOrderLab.setText(getBloodTransfer.getOrdermCode()+ "");
        idDateLab.setText(getBloodTransfer.getOrdermReceiveDate()+ "");

        Log.e("logblood","5050"+getBloodTransfer);

        initializespinner();

        llCloseLabResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNursingPro();
            }
        });

        Procedurespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerval = ((SpinCnt) Procedurespinner.getSelectedItem()).getSPIN_CODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private void initializespinner() {

        ArrayList<SpinCnt> proc = new ArrayList<>();

            proc.add(new SpinCnt("0","اختر الإجراء المناسب" ));
            proc.add(new SpinCnt("1","منتظر التنفيذ" ));
            proc.add(new SpinCnt("2", "تحت العلاج" ));
            proc.add(new SpinCnt("3", "تم"));

        ArrayAdapter<SpinCnt> adapter = new ArrayAdapter<SpinCnt>(getContext(),  android.R.layout.simple_spinner_dropdown_item, proc);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        Procedurespinner.setAdapter(adapter);

    }


    public void AddNursingPro() {
        if(spinnerval == null || spinnerval.equals("0"))
        {
            Toast.makeText(getContext(), "الرجاء اختيار الإجراء المناسب", Toast.LENGTH_SHORT).show();
            return;
        }
        NURSING_NOTE = txti_notes.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("PATREC_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TUMORS_ORDER_NO", getBloodTransfer.getOrdermCode());
        map.put("STATUS_NURSING", spinnerval);
        map.put("NURSING_NOTE", NURSING_NOTE);
        map.put("NURSING_BY", Controller.pref.getString("USER_ID", ""));

        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "تنفيذ نقل الدم ");
        Log.d("b_t",map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, UPDATE_BLOOD_ORDER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String  res = response.getString("RESULT");
                    if (res .equals("1")) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                       // getActivity().onBackPressed();
                        replaceAddDialogFragment();
                    } else
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("test", "ERROR=" + e.getMessage());
                }
          //      mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
                Controller.view_error(volleyError, getContext());
            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);

    }

    private void replaceAddDialogFragment() {
        getDialog().dismiss();
        BloodTransferFragment Fragment = new BloodTransferFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, Fragment, "bloodfrag")
                .addToBackStack("bloodEX").commit();

    }
}