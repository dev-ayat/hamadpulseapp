package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterSetChangePosition;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.ChangePositionModel;
import com.moh.hamadpulse.models.SetChangePositionModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Add_Changing_Position extends Fragment {
    RadioGroup air_matrix_g, bed_sores_g, grade_g;
    RadioButton grade1, grade2, grade3, grade4;
    RecyclerView RV_SetChangePosition;
    ArrayList<SetChangePositionModel> setChangePositionModels;
    AdapterSetChangePosition adapter;
    ChangePositionModel changePositionModel;
    FloatingActionButton fbtn_add_update_change_position;
    int air_max, bed_sores, grade;
    ArrayList<Boolean> positions_list;

    public Add_Changing_Position(ChangePositionModel changePositionModel) {
        // Required empty public constructor]
        this.changePositionModel = changePositionModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_changing_position, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepateArrayOfSetChangePosition();
        //get object to update
        air_matrix_g = view.findViewById(R.id.rgroub_AirMatrix);
        grade1 = view.findViewById(R.id.rbtn_one);
        grade2 = view.findViewById(R.id.rbtn_two);
        grade3 = view.findViewById(R.id.rbtn_three);
        grade4 = view.findViewById(R.id.rbtn_four);
        //set check for default value
        air_matrix_g.check(R.id.rbtn_No);
        bed_sores_g = view.findViewById(R.id.rgroub_Bed_Sores);
        bed_sores_g.check(R.id.rbtn_Absent);
        grade_g = view.findViewById(R.id.rgroub_Grade);
        bed_sores_g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn_Absent) {
                    setEnableForCheckBoxes(false);
                } else {
                    setEnableForCheckBoxes(true);
                }
            }
        });
        setEnableForCheckBoxes(false);
        RV_SetChangePosition = view.findViewById(R.id.RV_SetChangePosition);
        fbtn_add_update_change_position = view.findViewById(R.id.btn_add_new_change_position);

//        if (getArguments() != null && getArguments().getSerializable("item") != null) {
//            Bundle itemBundle = getArguments();
//            changePositionModel = (ChangePositionModel) itemBundle.getSerializable("item");
//            fbtn_add_update_change_position.setImageResource(R.drawable.edit);
//        }
        //check where is the process is update or add
        if (changePositionModel != null) {
            // get data from model and view it on check boxes
            positions_list = new ArrayList<>(Arrays.asList(new Boolean[]{
                    changePositionModel.getInpChRt8am(), changePositionModel.getInpChLt10am()
                    , changePositionModel.getInpChBack12md(), changePositionModel.getInpChRt2pm(),
                    changePositionModel.getInpChLt4pm(), changePositionModel.getInpChBack6pm(),
                    changePositionModel.getInpChRt8pm(), changePositionModel.getInpChLt10pm(),
                    changePositionModel.getInpChBack12mn(), changePositionModel.getInpChRt2am(),
                    changePositionModel.getInpChLt4am(), changePositionModel.getInpChBack6am()
            }));

            adapter = new AdapterSetChangePosition(setChangePositionModels,
                    positions_list);

            air_matrix_g.check(changePositionModel.getInpChAirMatrix().equals("1") ? R.id.rbtn_yes : R.id.rbtn_No);
            bed_sores_g.check(changePositionModel.getInpChBedSource().equals("1") ? R.id.rbtn_Present :
                    R.id.rbtn_Absent);
            String grade = changePositionModel.getInpChBedSourceGrade();
            grade_g.check(grade.equals("1") ? R.id.rbtn_one : grade.equals("2") ? R.id.rbtn_two
                    : grade.equals("3") ? R.id.rbtn_three : grade.equals("4") ? R.id.rbtn_four : -1);

        } else {
            prepateArrayOfSetChangePosition();

            adapter = new AdapterSetChangePosition(setChangePositionModels,
                    new ArrayList<Boolean>());
        }
        RecyclerView.LayoutManager gridLayout = new GridLayoutManager(getContext()
                , 3);
        RV_SetChangePosition.setAdapter(adapter);
        RV_SetChangePosition.setLayoutManager(gridLayout);
        fbtn_add_update_change_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bed_sores_g.getCheckedRadioButtonId() == R.id.rbtn_Present
                        && grade_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(),
                            "please check the grade",
                            Toast.LENGTH_LONG).show();
                } else {
                    AddChangingPositionForPatient();
                }
            }

        });

    }

    private void prepateArrayOfSetChangePosition() {
        setChangePositionModels = new ArrayList<>();
        setChangePositionModels.add(new SetChangePositionModel("Rt",
                " 8Am", false));
        setChangePositionModels.add(new SetChangePositionModel("Lt",
                "10Am", false));
        setChangePositionModels.add(new SetChangePositionModel("B",
                "12MD", false));
        setChangePositionModels.add(new SetChangePositionModel("Rt",
                "2 Pm", false));
        setChangePositionModels.add(new SetChangePositionModel("Lt",
                "4 Pm", false));
        setChangePositionModels.add(new SetChangePositionModel("B",
                "6 Pm", false));
        setChangePositionModels.add(new SetChangePositionModel("Rt",
                "8 Pm", false));
        setChangePositionModels.add(new SetChangePositionModel("Lt",
                "10Pm", false));
        setChangePositionModels.add(new SetChangePositionModel("B",
                "12MN", false));
        setChangePositionModels.add(new SetChangePositionModel("Rt",
                "2 Am", false));
        setChangePositionModels.add(new SetChangePositionModel("Lt",
                "4 Am", false));
        setChangePositionModels.add(new SetChangePositionModel("B",
                "6 Am", false));
    }

    public void AddChangingPositionForPatient() {
//        mInterfacePatient.showLoading(true);

        air_max = air_matrix_g.getCheckedRadioButtonId() == R.id.rbtn_No ? 0 : 1;
        bed_sores = bed_sores_g.getCheckedRadioButtonId() == R.id.rbtn_Absent ? 0 : 1;
        int rbId = grade_g.getCheckedRadioButtonId();
        grade = rbId == -1 ? 0 : rbId == R.id.rbtn_one ? 1 : rbId == R.id.rbtn_two ? 2 : rbId == R.id.rbtn_three ? 3 : 4;
        Map<String, String> map = new HashMap<>();
        map.put("INP_CH_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("INP_CH_PATRIC_CD", 412565 + "");
//        map.put("INP_CH_ADM_CD", 191211+ "");
        map.put("INP_CH_ADM_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("INP_CH_AIR_MATRIX", air_max + "");
        map.put("INP_CH_BED_SOURCE", bed_sores + "");
        map.put("INP_CH_BED_SOURCE_GRADE", grade + "");
        map.put("INP_CH_RT_8AM", convertCheckBoxToValue(0) + "");
        map.put("INP_CH_LT_10AM", convertCheckBoxToValue(1) + "");
        map.put("INP_CH_BACK_12MD", convertCheckBoxToValue(2) + "");
        map.put("INP_CH_RT_2PM", convertCheckBoxToValue(3) + "");
        map.put("INP_CH_LT_4PM", convertCheckBoxToValue(4) + "");
        map.put("INP_CH_BACK_6PM", convertCheckBoxToValue(5) + "");
        map.put("INP_CH_RT_8PM", convertCheckBoxToValue(6) + "");
        map.put("INP_CH_LT_10PM", convertCheckBoxToValue(7) + "");
        map.put("INP_CH_BACK_12MN", convertCheckBoxToValue(8) + "");
        map.put("INP_CH_RT_2AM", convertCheckBoxToValue(9) + "");
        map.put("INP_CH_LT_4AM", convertCheckBoxToValue(10) + "");
        map.put("INP_CH_BACK_6AM", convertCheckBoxToValue(11) + "");
        map.put("INP_CH_CRATED_BY", Controller.pref.getString("USER_ID", ""));
        map.put("TRANS_SCREEN_CD_IN", 36 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "insert or update changing position");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST,
                Controller.INSERT_INP_CHANGING_POSITION_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else if (res == 2) {
                        Toast.makeText(getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "لم تتم العملية",
                                Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
//                mInterfacePatient.showLoading(false);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
//                mInterfacePatient.showLoading(false);
            }
        });
        jsObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                // Here goes the new timeout 3 minutes
                return 3 * 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                // The max number of attempts
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);

    }

    //    to convert checked CheckBoxes to 1 and the unchecked to 0
    private int convertCheckBoxToValue(int index) {
        if (changePositionModel != null)
            return positions_list.get(index) ? 1 : 0;
        else
            return setChangePositionModels.get(index).getCheckable() ? 1 : 0;
    }

    private void setEnableForCheckBoxes(boolean status) {
        if (!status) {
            grade_g.check(-1);
        }
        grade1.setEnabled(status);
        grade2.setEnabled(status);
        grade3.setEnabled(status);
        grade4.setEnabled(status);
    }
}