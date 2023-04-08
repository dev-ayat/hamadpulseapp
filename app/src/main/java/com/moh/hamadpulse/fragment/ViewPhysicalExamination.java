package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.mInterfacePatient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.PhysicalExaminationModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ViewPhysicalExamination extends Fragment {
    FloatingActionButton Add_Edit_ph_ex;
    ListView PH_Examination_rv;
    PhysicalExaminationModel model;
    public ViewPhysicalExamination() {
        // Required empty public constructor
    }


   void fillData(){
       mInterfacePatient.showLoading(true);
       Map<String, String> map = new HashMap<>();
       map.put("P_ADM_CD", ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"");
       map.put("TRANS_SCREEN_CD_IN", 48 + "");
       map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
       map.put("TRANS_ACTION_CD_IN", "2");
       map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
       map.put("TRANS_DESCRIPTION_IN", "View Physical Examination");
       MyRequest.makeRquest(getContext(), Controller.GET_PH_EXAM_DOC_URL,
               map, new MyRequest.CallBack() {
                   @Override
                   public void Result(String response) {
                       JSONObject mJSONObject = null;
                       try {
                           mJSONObject = new JSONObject(response);
                           JSONObject jsonObject = mJSONObject.getJSONObject("PH_EXAM");
                           Gson gson = new Gson();
                           model = gson.fromJson(jsonObject.toString(),
                                   PhysicalExaminationModel.class);
                           fillExaminations(new ArrayList<PhysicalExaminationModel>(Arrays.asList(model)));
                           mInterfacePatient.showLoading(false);
                       } catch (JSONException e) {
                           e.printStackTrace();
                           Log.e("api", e.getMessage());
                           Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                           mInterfacePatient.showLoading(false);

                       }

                   }

                   @Override
                   public void Error(VolleyError error) {
                       Controller.view_error(error, getContext());
                       mInterfacePatient.showLoading(false);
                   }
               });
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_physical_examination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Add_Edit_ph_ex=view.findViewById(R.id.Add_Edit_ph_ex);
        PH_Examination_rv=view.findViewById(R.id.PH_Examination_rv);
        Add_Edit_ph_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new PhysicalExamination(model==null?null:model.getPH_EXAM_NOTE()));
            }
        });

        fillData();
    }

    private void fillExaminations(ArrayList<PhysicalExaminationModel> admissionDiagnoseModels) {
        ArrayAdapter<PhysicalExaminationModel> arrayAdapter = new ArrayAdapter<PhysicalExaminationModel>(getContext(),
                R.layout.listview_textview, admissionDiagnoseModels);
        PH_Examination_rv.setAdapter(arrayAdapter);
    }
}