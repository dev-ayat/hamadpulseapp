package com.moh.hamadpulse.fragment;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.constants.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class PieChartFragment extends Fragment {
    public int APP, P_APP, ATT, ATT_N_APP, NOT_VISITE, ATT_W_APP, AVG_ATT;
    public int EMR_ALL, EMR_OUT, EMR_IN, EMR_IN_72H;
    public int AQSAOUT, ACCEDANTOUT;
    public int ADM_72H, ADM_6DAY, ADM_1WEEK;
    PieChart ClinicChart, EmrChart, Aqsa_Acc_Chart, Adm_Chart;
    ArrayList<PieEntry> Clinicyvalues, Emryvalues, Aqsa_Acc_yvalus, Adm_yvalus;
    PieData Clinicdata, Emrdata, AqsaAccdata, Admdata;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        ClinicChart = view.findViewById(R.id.ClinincChart);
        EmrChart = view.findViewById(R.id.EmrChart);
        Aqsa_Acc_Chart = view.findViewById(R.id.Aqsa_Acc_Chart);
        Adm_Chart = view.findViewById(R.id.Adm_Chart);

        AddValuesToClinicChart(APP, P_APP, ATT, ATT_N_APP, NOT_VISITE, ATT_W_APP, AVG_ATT);
        prepareClinicchartData();

        AddValuesToEMRChart(EMR_ALL, EMR_OUT, EMR_IN, EMR_IN_72H);
        prepareEmrchartData();

        AddValuesToAqsaAccChart(AQSAOUT, ACCEDANTOUT);
        prepareAqsaAccChartData();

        AddValuesToAdmChart(ADM_72H, ADM_6DAY, ADM_1WEEK);
        prepareAdmChartData();

        Timer timer = new Timer();
        timer.schedule(new ChartTimer(), 600000);

        ClinicChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
        return view;

    }


    ////// Clinic//////
    public void prepareClinicchartData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
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
                        AddValuesToClinicChart(APP, P_APP, ATT, ATT_N_APP, NOT_VISITE, ATT_W_APP, AVG_ATT);
                        ClinicChart.setData(Clinicdata);
                        ClinicChart.notifyDataSetChanged();
                        ClinicChart.invalidate();

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

        Clinicyvalues = new ArrayList<PieEntry>();
        Clinicyvalues.add(new PieEntry(APP, "الحجوزات الكلية"));
        //  Clinicyvalues.add(new PieEntry(P_APP, "الحجز بدون موعد"));
        Clinicyvalues.add(new PieEntry(ATT, "الحضور الكلي للمرضى"));
        Clinicyvalues.add(new PieEntry(ATT_N_APP, "الحضور  بموعد"));
        Clinicyvalues.add(new PieEntry(ATT_W_APP, "حضور بدون موعد"));
        Clinicyvalues.add(new PieEntry(NOT_VISITE, "لم يحضر"));
        //  Clinicyvalues.add(new PieEntry(AVG_ATT, "نسبة الحضور من الحجز %"));
        ClinicChart.getDescription().setText("إحصائية العيادات");
        PieDataSet dataSet = new PieDataSet(Clinicyvalues, "");
        Clinicdata = new PieData(dataSet);
        setChartProporetes(ClinicChart, Clinicdata, dataSet);
        SetlegandChart(ClinicChart);
    }

    ////// End of clinic//////

    ///// EMR STAT  ////
    public void prepareEmrchartData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
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
                        AddValuesToEMRChart(EMR_ALL, EMR_OUT, EMR_IN, EMR_IN_72H);
                        EmrChart.setData(Emrdata);
                        EmrChart.notifyDataSetChanged();
                        EmrChart.invalidate();

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

        Emryvalues = new ArrayList<PieEntry>();
        Emryvalues.add(new PieEntry(EMR_ALL, "الحضور الكلي "));
        Emryvalues.add(new PieEntry(EMR_IN, "الحضور الحالي"));
        Emryvalues.add(new PieEntry(EMR_OUT, "عدد الخروج"));
        Emryvalues.add(new PieEntry(EMR_IN_72H, "مكوث في الطوارئ لمدة 72 ساعة"));
        EmrChart.getDescription().setText("إحصائية الطوارئ");
        PieDataSet dataSet = new PieDataSet(Emryvalues, "");
        Emrdata = new PieData(dataSet);
        setChartProporetes(EmrChart, Emrdata, dataSet);
        SetlegandChart(EmrChart);
    }

///////  END EMR STAT   //////


    ///// AQSA_ACC STAT  ////
    public void prepareAqsaAccChartData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", "0");
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
                        AddValuesToAqsaAccChart(AQSAOUT, ACCEDANTOUT);
                        Aqsa_Acc_Chart.setData(AqsaAccdata);
                        Aqsa_Acc_Chart.notifyDataSetChanged();
                        Aqsa_Acc_Chart.invalidate();

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

        Aqsa_Acc_yvalus = new ArrayList<PieEntry>();
        Aqsa_Acc_yvalus.add(new PieEntry(AQSAOUT, "إدعاء بأحداث أقصى "));
        Aqsa_Acc_yvalus.add(new PieEntry(ACCEDANTOUT, "إدعاء حوادث طرق "));
        Aqsa_Acc_Chart.getDescription().setText("إحصائية الأحداث");
        PieDataSet dataSet = new PieDataSet(Aqsa_Acc_yvalus, "");
        AqsaAccdata = new PieData(dataSet);
        setChartProporetes(Aqsa_Acc_Chart, AqsaAccdata, dataSet);
        SetlegandChart(Aqsa_Acc_Chart);
    }

///////  END AQSA_ACC STAT   //////


    ///// Admission STAT  ////
    public void prepareAdmChartData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map<String, String> map = new HashMap<>();
        map.put("HOS_NO", "0");
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
                        AddValuesToAdmChart(ADM_72H, ADM_6DAY, ADM_1WEEK);
                        Adm_Chart.setData(Admdata);
                        Adm_Chart.notifyDataSetChanged();
                        Adm_Chart.invalidate();

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

    public void AddValuesToAdmChart(int ADM_72H, int ADM_6DAY, int ADM_1WEEK) {

        Adm_yvalus = new ArrayList<PieEntry>();
        Adm_yvalus.add(new PieEntry(ADM_72H, "مرضى منومين أكثر من 72 ساعة "));
        Adm_yvalus.add(new PieEntry(ADM_6DAY, "مرضى منومين أكثر من 6أيام "));
        Adm_yvalus.add(new PieEntry(ADM_1WEEK, "مرضى منومين أكثر من 7 أيام "));
        Adm_Chart.getDescription().setText("إحصائية الدخول للمستشفى");
        PieDataSet dataSet = new PieDataSet(Adm_yvalus, "");
        Admdata = new PieData(dataSet);
        setChartProporetes(Adm_Chart, Admdata, dataSet);
        SetlegandChart(Adm_Chart);
    }


    ///////  END Admission STAT   //////
    public void setChartProporetes(PieChart Chart, PieData piedata, PieDataSet dataSet) {
        Chart.getDescription().setTextSize(20f); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
        Chart.getDescription().setTextAlign(Paint.Align.CENTER); //Sets the text alignment of the description text. Default RIGHT
        Chart.setDrawHoleEnabled(true);
        Chart.setTransparentCircleRadius(40f);
        Chart.setHoleRadius(40f);
        Chart.animateXY(1400, 1400);
        piedata.setValueTextSize(15f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataSet.setValueTextSize(20f);
        dataSet.setValueFormatter(new CustomPercentFormatter());//// to remove zero values
        piedata.setValueTextColor(Color.DKGRAY);


        // In Percentage term
        // Clinicdata.setValueFormatter(new PercentFormatter());
        // Default value
        // Clinicdata.setValueFormatter(new DefaultValueFormatter(0));


//        int [] color={ Color.rgb(100,221,23), Color.rgb(128,0,128), Color.rgb(255,136,0),
//                Color.rgb(255,0,0), Color.rgb(255,127,80), Color.rgb(47,95,255)
//        };
        // dataSet.setColors(color);


    }

    ///// legand for all charts
    public void SetlegandChart(PieChart Chart) {

        Chart.setDrawSliceText(false); // To remove slice text
        Chart.setDrawMarkers(false); // To remove markers when click
        Chart.setDrawEntryLabels(false); // To remove labels from piece of pie
        Chart.getDescription().setEnabled(true); // To remove description of pie

        Legend l = Chart.getLegend(); // get legend of pie
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // set vertical alignment for legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // set horizontal alignment for legend
        l.setOrientation(Legend.LegendOrientation.VERTICAL); // set orientation for legend
        l.setDrawInside(false);
        l.setTextSize(15f);// set if legend should be drawn inside or not
    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("الإحصائيات الإدارية");
    }

    public class CustomPercentFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public CustomPercentFormatter() {
            mFormat = new DecimalFormat("########");
        }

        public CustomPercentFormatter(DecimalFormat format) {
            this.mFormat = format;
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if (value == 0.0f)
                return "";
            //  return mFormat.format(value) + " %";
            return mFormat.format(value);
        }
    }

    //Timer class
    class ChartTimer extends TimerTask {
        @Override
        public void run() {

            prepareClinicchartData();

            prepareEmrchartData();

            prepareAqsaAccChartData();

            prepareAdmChartData();

        }

    }

}
