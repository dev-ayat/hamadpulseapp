package com.moh.hamadpulse.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.models.ServicesStatisticsModel;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Fragment_View_All_Requests extends Fragment {
    DatePickerDialog from_datePickerDialog, to_datePickerDialog;
    Button starting_date_btn, ending_date_btn;
    ImageButton cv_send;
    ServicesStatisticsModel model;
    String dateFrom = "2022/2/2";
    String dateTo = "2022/2/2";
    TextView txt_lab_orders, txt_rad_orders, txt_Pharmacy_Orders, txt_Nursing_Procedures, txt_Vital_Signs, txt_Doctor_Notes, txt_Ventilation, txt_Daily_Progress;
    private AVLoadingIndicatorView imgLoading;
    public Fragment_View_All_Requests() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__view__all__requests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        starting_date_btn = view.findViewById(R.id.starting_date_btn);
        ending_date_btn = view.findViewById(R.id.ending_date_btn);
        txt_lab_orders = view.findViewById(R.id.txt_lab_orders);
        txt_rad_orders = view.findViewById(R.id.txt_rad_orders);
        txt_Pharmacy_Orders = view.findViewById(R.id.txt_Pharmacy_Orders);
        txt_Nursing_Procedures = view.findViewById(R.id.txt_Nursing_Procedures);
        txt_Vital_Signs = view.findViewById(R.id.txt_Vital_Signs);
        txt_Doctor_Notes = view.findViewById(R.id.txt_Doctor_Notes);
        txt_Ventilation = view.findViewById(R.id.txt_Ventilation);
        txt_Daily_Progress = view.findViewById(R.id.txt_Daily_Progress);
        cv_send = view.findViewById(R.id.sendRequest_cv);
        imgLoading = view.findViewById(R.id.imgLoading);
        starting_date_btn.setText(getCurrentDate());
        ending_date_btn.setText(getCurrentDate());

        initDatePickerDialog();
        starting_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from_datePickerDialog.show();
            }
        });
        ending_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_datePickerDialog.show();
            }
        });
        cv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSERVICES_STATISTICS();
            }
        });


    }

    private void getSERVICES_STATISTICS() {
        showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("P_DATE_FROM", dateFrom);
        map.put("P_DATE_TO", dateTo);
        map.put("TRANS_SCREEN_CD_IN", 39 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View Services Statistics");
        MyRequest.makeRquest(getContext(), Controller.GET_SERVICES_STATISTICS_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("SERV_STAT");
                            Gson gson = new Gson();
                            model = gson.fromJson(jsonObject.toString(),
                                    ServicesStatisticsModel.class);
                            fillData(model);

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            showLoading(false);

                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                        showLoading(false);
                    }
                });
    }

    private void fillData(ServicesStatisticsModel model) {
        txt_lab_orders.setText(model.getLabOrders());
        txt_rad_orders.setText(model.getRadOrders());
        txt_Nursing_Procedures.setText(model.getNursingPro());
        txt_Daily_Progress.setText(model.getDailyProg());
        txt_Pharmacy_Orders.setText(model.getPharmOrders());
        txt_Ventilation.setText(model.getVintalation());
        txt_Vital_Signs.setText(model.getVitalSign());
        txt_Doctor_Notes.setText(model.getDoctorNotes());
    }

    private void initDatePickerDialog() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateFrom = (i2 < 10 ? "0" + i2 : i2 + "") + "/" + ((++i1) < 10 ? "0" + i1 : i1 + "") + "/" + i;
                starting_date_btn.setText(dateFrom);
            }
        };
        DatePickerDialog.OnDateSetListener onDateSetListener_ = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateTo = (i2 < 10 ? "0" + i2 : i2 + "") + "/" + ((++i1) < 10 ? "0" + i1 : i1 + "") + "/" + i;
                ending_date_btn.setText(dateTo);
            }

        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        from_datePickerDialog = new DatePickerDialog(getContext(), style, onDateSetListener
                , year, month, day);
        to_datePickerDialog = new DatePickerDialog(getContext(), style, onDateSetListener_
                , year, month, day);


    }

    String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month++;
        int year = calendar.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setTitle("الإحصائيات الداخلية");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((HomeActivity) getActivity()).setTitle("الشاشة الرئيسية");
    }

    public void showLoading(boolean b) {
        if (b)
            imgLoading.setVisibility(View.VISIBLE);
        else
            imgLoading.setVisibility(View.GONE);
    }
}