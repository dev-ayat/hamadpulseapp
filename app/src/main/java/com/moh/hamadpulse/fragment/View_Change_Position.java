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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.AdapterChangePosition;
import com.moh.hamadpulse.models.ChangePositionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class View_Change_Position extends Fragment {
    AdapterChangePosition adapterChangePosition;
    RecyclerView RV_Change_Position;
    FloatingActionButton fbtn_add_new_changing_position;
    ArrayList<ChangePositionModel> changePositionList;
    RadioButton rbtn_yes, rbtn_No, rbtn_Absent, rbtn_Present, rbtn_one, rbtn_two, rbtn_three, rbtn_four;
    RadioGroup rgroub_AirMatrix, rgroub_Bed_Sores, rgroub_Grade;

    public View_Change_Position() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_change_position, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbtn_No = view.findViewById(R.id.rbtn_No);
        //we use it for display
        rbtn_No.setEnabled(false);
        rbtn_yes = view.findViewById(R.id.rbtn_yes);
        rbtn_yes.setEnabled(false);
        rbtn_Absent = view.findViewById(R.id.rbtn_Absent);
        rbtn_Absent.setEnabled(false);
        rbtn_Present = view.findViewById(R.id.rbtn_Present);
        rbtn_Present.setEnabled(false);
        rbtn_one = view.findViewById(R.id.rbtn_one);
        rbtn_one.setEnabled(false);
        rbtn_two = view.findViewById(R.id.rbtn_two);
        rbtn_two.setEnabled(false);
        rbtn_three = view.findViewById(R.id.rbtn_three);
        rbtn_three.setEnabled(false);
        rbtn_four = view.findViewById(R.id.rbtn_four);
        rbtn_four.setEnabled(false);

        rgroub_AirMatrix = view.findViewById(R.id.rgroub_AirMatrix);
        rgroub_Bed_Sores = view.findViewById(R.id.rgroub_Bed_Sores);
        rgroub_Grade = view.findViewById(R.id.rgroub_Grade);
        changePositionList = new ArrayList<>();
        RV_Change_Position = view.findViewById(R.id.RV_Change_Position);
        adapterChangePosition = new AdapterChangePosition(changePositionList);
        RV_Change_Position.setAdapter(adapterChangePosition);
        RV_Change_Position.setLayoutManager(new LinearLayoutManager(getContext()));
        fbtn_add_new_changing_position = view.findViewById(R.id.btn_add_newChangePosition);
        Map<String, String> map = new HashMap<>();
        map.put("P_INP_CH_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("P_INP_CH_PATRIC_CD", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("P_INP_CH_PATRIC_CD", "191211");
//        map.put("P_INP_CH_ADM_CD", "412565");
        map.put("TRANS_SCREEN_CD_IN", 36 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
//        map.put("TRANS_USER_CODE_IN", 801766338 + "");
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN", "191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "view change position");
        MyRequest.makeRquest(getContext(), Controller.GET_INP_CHANGING_POSITION_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("CH_POS_CUR");

                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<ChangePositionModel>>() {
                            }.getType();
                            changePositionList = gson.fromJson(jsonArray.toString(),
                                    type);

                            //set data for air_matrix,bed,grade
                            if (changePositionList.size() != 0) {
                                ChangePositionModel model = changePositionList.get(changePositionList.size() - 1);
                                rgroub_AirMatrix.check(model.getInpChAirMatrix().equals("1") ? R.id.rbtn_yes : R.id.rbtn_No);
                                rgroub_Bed_Sores.check(model.getInpChBedSource().equals("1") ? R.id.rbtn_Present :
                                        R.id.rbtn_Absent);
                                String grade = model.getInpChBedSourceGrade();
                                rgroub_Grade.check(grade.equals("1") ? R.id.rbtn_one : grade.equals("2") ? R.id.rbtn_two
                                        : grade.equals("3") ? R.id.rbtn_three : grade.equals("4") ? R.id.rbtn_four : -1);
                                adapterChangePosition.setchangePositionList(changePositionList);
                                adapterChangePosition.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                    }
                });
        fbtn_add_new_changing_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (changePositionList.size() != 0) {
                    ChangePositionModel model = changePositionList.get(changePositionList.size() - 1);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String date_ = formatter.format(date);
                    Log.d("date",date_);
                    Log.d("date",model.getInpChCreatedOn());
                    Log.d("date",date_.compareTo(model.getInpChCreatedOn())+"");

                    if (date_.compareTo(model.getInpChCreatedOn()) > 0)
                        ((ActivityPatient) getActivity()).CallFragment(new Add_Changing_Position(null));
                    else
                        ((ActivityPatient) getActivity()).CallFragment(new Add_Changing_Position(model));
                } else {
                    ((ActivityPatient) getActivity()).CallFragment(new Add_Changing_Position(null));
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}