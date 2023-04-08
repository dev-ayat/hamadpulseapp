package com.moh.hamadpulse.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.adapters.HospitalsSinnerAdapter;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

public class ParChartFragment extends Fragment {
    public int APP, P_APP, ATT, ATT_N_APP, NOT_VISITE, ATT_W_APP, AVG_ATT;
    public int EMR_ALL, EMR_OUT, EMR_IN, EMR_IN_72H;
    public int AQSAOUT, ACCEDANTOUT;
    public int ADM_72H, ADM_6DAY, ADM_1WEEK;
    public int OP_BIG, OP_MEDIUM, OP_SMALL, OP_SKILL;
    Spinner statisticSpinner, hosspinner;
    Button btnshowstat;
    BarChart Chart;
    // ArrayList<PieEntry> Clinicyvalues,Emryvalues,Aqsa_Acc_yvalus,Adm_yvalus;
    BarDataSet barDataSet1, barDataSet2, barDataSet3, barDataSet4;
    ArrayList<String> Hospitals;
    String hos_no = "" + 1;
    int static_no;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_par_chart, container, false);
        statisticSpinner = view.findViewById(R.id.statname_spinner);
        hosspinner = view.findViewById(R.id.hosname_spinner);
        btnshowstat = view.findViewById(R.id.btnshowstat);
        Chart = view.findViewById(R.id.Chart);
        Chart.setNoDataText("أختر المستشفي والإحصائية لعرض البيانات");
        prepareHosSpinnerData();

        // String  HOS_NAME= Controller.pref.getString("LOC_NAME","");
//        ArrayAdapter<CharSequence> hosAdapter = ArrayAdapter
//                .createFromResource(getContext(), R.array.hos_name,
//                        android.R.layout.simple_spinner_item);

        //   ArrayAdapter hosAdapter =  new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,Hospitals);

        // Specify the layout to use when the list of choices appears
//        hosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        hosspinner.setAdapter(hosAdapter);
        HospitalsSinnerAdapter hosAdapter = new HospitalsSinnerAdapter(Hospitals);
        hosspinner.setAdapter(hosAdapter);
        hosspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.e("item", "" + parent.getItemAtPosition(position));
                hos_no = "" + (position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<CharSequence> StatnameAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.Statstic_name,
                        android.R.layout.simple_spinner_item);

        StatnameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statisticSpinner.setAdapter(StatnameAdapter);
        statisticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                static_no = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnshowstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler aHandler = new Handler();
                Log.e("stat", "hos=" + hos_no + "type" + static_no);
                switch (static_no) {
                    case 1:
                        prepareClinicchartData(hos_no);
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddValuesToClinicChart(APP, P_APP, ATT, ATT_N_APP, NOT_VISITE, ATT_W_APP, AVG_ATT);
                            }
                        }, 2000);
                        break;
                    case 2:
                        prepareEmrchartData(hos_no);
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddValuesToEMRChart(EMR_ALL, EMR_OUT, EMR_IN, EMR_IN_72H);
                            }
                        }, 2000);
                        break;
                    case 3:
                        prepareAqsaAccChartData(hos_no);
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddValuesToAqsaAccChart(AQSAOUT, ACCEDANTOUT);
                            }
                        }, 2000);

                        break;
                    case 4:
                        prepareAdmChartData(hos_no);
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddValuesToAdmChart(ADM_72H, ADM_6DAY, ADM_1WEEK);
                            }
                        }, 2000);
                        break;

                    case 5:
                        prepareOPerationChartData(hos_no);
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddValuesToOperChart(OP_BIG, OP_MEDIUM, OP_SMALL, OP_SKILL);
                            }
                        }, 2000);
                        break;


                }
            }
        });


        Chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
        return view;

    }

    public void prepareHosSpinnerData() {
        Hospitals = new ArrayList<String>();
        Map<String, String> map = new HashMap<>();
        String loc_cd = Controller.pref.getString("LOC_CD", "");
        map.put("LOC_CODE", loc_cd);
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("request", "" + map);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_HOSPITAL_INFO_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONObject HOSPITAL_INFO_OBJ = jsonObject.getJSONObject("HOSPITAL_INFO");
                    JSONArray LOC_ARR = HOSPITAL_INFO_OBJ.getJSONArray("RES_LOCATION");
                    Log.e("LOC_ARR", "" + LOC_ARR);
                    if (LOC_ARR.length() > 0) {
                        for (int x = 0; x < LOC_ARR.length(); x++) {
                            JSONObject LOC_obj = LOC_ARR.getJSONObject(x);
                            Log.e("LOC_obj", "" + LOC_ARR.getJSONObject(x));
                            Hospitals.add(LOC_obj.getString("H_NAME_AR"));
                        }
                        ///   Log.e("Hospitals",Hospitals);
                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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
    ////// Clinic//////


    public void prepareClinicchartData(String hos_no) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", hos_no);
        map.put("P_FROM_DATE", formatter.format(date));
        map.put("P_TO_DATE", formatter.format(date));
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("chartayat", "1");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_CLINIC_STAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("chartayat", "2");
                try {
                    Log.e("chartayat", "3");
                    Log.e("chartjson:", jsonObject.toString());
                    JSONObject CLINIC_OBJ = jsonObject.getJSONObject("CLINIC");

                    if (CLINIC_OBJ.length() > 0) {
                        Log.e("json", "" + CLINIC_OBJ.getString("P_APP"));
                        APP = CLINIC_OBJ.getInt("APP");
                        P_APP = CLINIC_OBJ.getInt("P_APP");
                        ATT = CLINIC_OBJ.getInt("ATT");
                        ATT_N_APP = CLINIC_OBJ.getInt("ATT_N_APP");
                        NOT_VISITE = CLINIC_OBJ.getInt("NOT_VISITE");
                        ATT_W_APP = CLINIC_OBJ.getInt("ATT_W_APP");
                        AVG_ATT = CLINIC_OBJ.getInt("AVG_ATT");
                        Chart.invalidate();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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

    public void AddValuesToClinicChart(int APP, int P_APP, int ATT, int ATT_N_APP, int NOT_VISITE,
                                       int ATT_W_APP, int AVG_ATT) {
        SetlegandBarChart(Chart);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, APP));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1f, ATT));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2f, ATT_N_APP));
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        entries4.add(new BarEntry(3f, ATT_W_APP));

        barDataSet1 = new BarDataSet(entries1, "الحجوزات الكلية");
        barDataSet1.setColor(Color.rgb(204, 0, 0));

        barDataSet2 = new BarDataSet(entries2, "الحضور الكلي للمرضى");
        barDataSet2.setColor(Color.rgb(255, 153, 51));

        barDataSet3 = new BarDataSet(entries3, "الحضور  بموعد");
        barDataSet3.setColor(Color.rgb(102, 204, 0));

        barDataSet4 = new BarDataSet(entries4, "حضور بدون موعد");
        barDataSet4.setColor(Color.rgb(0, 102, 204));

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3, barDataSet4);
        XAxis xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Chart.setData(barData);
        Chart.setFitBars(true);
        xAxis.setGranularity(1f);
        Chart.animateXY(5000, 5000);
        Chart.invalidate();
        barData.setValueTextSize(15f);
        // barData.setValueFormatter(new LargeValueFormatter());

        barData.setValueFormatter(new ValueFormatter());

    }


    ////// End of clinic//////

    ///// EMR STAT  ////
    public void prepareEmrchartData(String hos_no) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", hos_no);
        map.put("P_FROM_DATE", formatter.format(date));
        map.put("P_TO_DATE", formatter.format(date));
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("chartayat", "1");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_EMR_STAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("chartayat", "3");
                    Log.e("chartjson:", jsonObject.toString());
                    JSONObject EMR_OBJ = jsonObject.getJSONObject("EMR");
                    if (EMR_OBJ.length() > 0) {
                        Log.e("Emrُjson", "" + EMR_OBJ.getString("EMR_ALL"));
                        EMR_ALL = EMR_OBJ.getInt("EMR_ALL");
                        EMR_OUT = EMR_OBJ.getInt("EMR_OUT");
                        EMR_IN = EMR_OBJ.getInt("EMR_IN");
                        EMR_IN_72H = EMR_OBJ.getInt("EMR_IN_72H");
                        Chart.invalidate();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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


    public void AddValuesToEMRChart(int EMR_ALL, int EMR_OUT, int EMR_IN, int EMR_IN_72H) {

        SetlegandBarChart(Chart);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, EMR_ALL));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1f, EMR_IN));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2f, EMR_OUT));
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        entries4.add(new BarEntry(3f, EMR_IN_72H));

        barDataSet1 = new BarDataSet(entries1, "الحضور الكلي ");
        barDataSet1.setColor(Color.rgb(204, 0, 0));

        barDataSet2 = new BarDataSet(entries2, "الحضور الحالي");
        barDataSet2.setColor(Color.rgb(255, 153, 51));

        barDataSet3 = new BarDataSet(entries3, "عدد الخروج");
        barDataSet3.setColor(Color.rgb(102, 204, 0));

        barDataSet4 = new BarDataSet(entries4, "مكوث في الطوارئ لمدة 72 ساعة");
        barDataSet4.setColor(Color.rgb(0, 102, 204));

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3, barDataSet4);
        XAxis xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Chart.setData(barData);
        Chart.setFitBars(true);
        xAxis.setGranularity(1f);
        Chart.animateXY(5000, 5000);
        Chart.invalidate();
        barData.setValueTextSize(15f);
        barData.setValueFormatter(new ValueFormatter());
    }

/////////  END EMR STAT   //////


    ///// AQSA_ACC STAT  ////
    public void prepareAqsaAccChartData(String hos_no) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", hos_no);
        map.put("P_FROM_DATE", formatter.format(date));
        map.put("P_TO_DATE", formatter.format(date));

        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_AQSA_ACCEDANT_STAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject Aqsa_Acc_OBJ = jsonObject.getJSONObject("Aqsa_Acc");
                    Log.e("Aqsa_Acc_OBJ", "" + Aqsa_Acc_OBJ);
                    if (Aqsa_Acc_OBJ.length() > 0) {
                        AQSAOUT = Aqsa_Acc_OBJ.getInt("AQSAOUT");
                        ACCEDANTOUT = Aqsa_Acc_OBJ.getInt("ACCEDANTOUT");
                        Chart.invalidate();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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

    public void prepareOPerationChartData(String hos_no) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", hos_no);
        map.put("P_FROM_DATE", formatter.format(date));
        map.put("P_TO_DATE", formatter.format(date));
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_OPERATION_STAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject Aqsa_Acc_OBJ = jsonObject.getJSONObject("OPERATION");
                    Log.e("OPERATION_OBJ", "" + Aqsa_Acc_OBJ);
                    if (Aqsa_Acc_OBJ.length() > 0) {
                        OP_BIG = Aqsa_Acc_OBJ.getInt("BIG");
                        OP_MEDIUM = Aqsa_Acc_OBJ.getInt("MEDIUM");
                        OP_SMALL = Aqsa_Acc_OBJ.getInt("SMALL");
                        OP_SKILL = Aqsa_Acc_OBJ.getInt("SKILL");
                        Chart.invalidate();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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


    public void AddValuesToAqsaAccChart(int AQSAOUT, int ACCEDANTOUT) {

        SetlegandBarChart(Chart);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, AQSAOUT));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1f, ACCEDANTOUT));

        barDataSet1 = new BarDataSet(entries1, "إدعاء بأحداث أقصى ");
        barDataSet1.setColor(Color.rgb(204, 0, 0));

        barDataSet2 = new BarDataSet(entries2, "إدعاء حوادث طرق ");
        barDataSet2.setColor(Color.rgb(255, 153, 51));

        BarData barData = new BarData(barDataSet1, barDataSet2);
        XAxis xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Chart.setData(barData);
        Chart.setFitBars(true);
        xAxis.setGranularity(1f);
        Chart.animateXY(5000, 5000);
        Chart.invalidate();
        barData.setValueTextSize(15f);
        barData.setValueFormatter(new ValueFormatter());
    }

///////  END AQSA_ACC STAT   //////


    ///// Admission STAT  ////
    public void prepareAdmChartData(String hos_no) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", hos_no);
        map.put("P_FROM_DATE", formatter.format(date));
        map.put("P_TO_DATE", formatter.format(date));
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_AMDISSION_STAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject Adm_OBJ = jsonObject.getJSONObject("Admission");
                    if (Adm_OBJ.length() > 0) {
                        ADM_72H = Adm_OBJ.getInt("ADM_72H");
                        ADM_6DAY = Adm_OBJ.getInt("ADM_6DAY");
                        ADM_1WEEK = Adm_OBJ.getInt("ADM_1WEEK");
                        Chart.invalidate();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
            }
        });

        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    public void AddValuesToAdmChart(int ADM_72H, int ADM_6DAY, int ADM_1WEEK) {

        SetlegandBarChart(Chart);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, ADM_72H));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1f, ADM_6DAY));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2f, ADM_1WEEK));

        barDataSet1 = new BarDataSet(entries1, "مرضى منومين أكثر من 72 ساعة");
        barDataSet1.setColor(Color.rgb(204, 0, 0));

        barDataSet2 = new BarDataSet(entries2, "مرضى منومين أكثر من 6أيام");
        barDataSet2.setColor(Color.rgb(255, 153, 51));

        barDataSet3 = new BarDataSet(entries3, "مرضى منومين أكثر من 7 أيام");
        barDataSet3.setColor(Color.rgb(102, 204, 0));


        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3);
        XAxis xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Chart.setData(barData);
        Chart.setFitBars(true);
        xAxis.setGranularity(1f);
        Chart.animateXY(5000, 5000);
        Chart.invalidate();
        barData.setValueTextSize(15f);
        barData.setValueFormatter(new ValueFormatter());

    }

    public void AddValuesToOperChart(int OP_BIG, int OP_MEDIUM, int OP_SMALL, int OP_SKILL) {

        SetlegandBarChart(Chart);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, OP_BIG));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1f, OP_MEDIUM));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2f, OP_SMALL));
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        entries4.add(new BarEntry(2f, OP_SKILL));

        barDataSet1 = new BarDataSet(entries1, "عمليات كبرى");
        barDataSet1.setColor(Color.rgb(204, 0, 0));

        barDataSet2 = new BarDataSet(entries2, "عمليات متوسطة");
        barDataSet2.setColor(Color.rgb(255, 153, 51));

        barDataSet3 = new BarDataSet(entries3, "عمليات صغرى");
        barDataSet3.setColor(Color.rgb(102, 204, 0));

        barDataSet4 = new BarDataSet(entries4, "عمليات ذات مهارة");
        barDataSet4.setColor(Color.rgb(0, 102, 204));

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3, barDataSet4);
        XAxis xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Chart.setData(barData);
        Chart.setFitBars(true);
        xAxis.setGranularity(1f);
        Chart.animateXY(5000, 5000);
        Chart.invalidate();
        barData.setValueTextSize(15f);
        barData.setValueFormatter(new ValueFormatter());

    }

    public void SetlegandBarChart(BarChart Chart) {

        Chart.setDrawMarkers(false); // To remove markers when click
        Chart.getDescription().setEnabled(false); // To remove description of pie
        Chart.setNoDataText("أختر المستشفي والإحصائية لعرض البيانات");

        Legend l = Chart.getLegend(); // get legend of pie
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTextSize(15f);// set if legend should be drawn inside or not
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(10f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(10f);
        l.setWordWrapEnabled(true);

    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("الإحصائيات الإدارية");
    }

    public class ValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public ValueFormatter() {
            mFormat = new DecimalFormat("########");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if (value > 0) {
                return value + "";
            } else {
                return "0";
            }
        }
    }

}
