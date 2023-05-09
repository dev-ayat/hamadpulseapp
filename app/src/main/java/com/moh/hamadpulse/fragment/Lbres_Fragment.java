package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.FragmentLabResult;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.LabCategoryAdapter;
import com.moh.hamadpulse.adapters.LaborderCardviewAdapter;
import com.moh.hamadpulse.models.AdmissionHistoryModel;
import com.moh.hamadpulse.models.LabCategoryDataModel;
import com.moh.hamadpulse.models.LabTestDataModel;
import com.moh.hamadpulse.models.LaborderCardviewDataModel;
import com.moh.hamadpulse.models.Labtestcultnotemodel;
import com.moh.hamadpulse.models.Labtestcultureantimodel;
import com.moh.hamadpulse.models.Labtestculturemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lbres_Fragment extends Fragment implements LaborderCardviewAdapter.InterfaceLabAdapterClick {
    private static LaborderCardviewAdapter laborderCardviewAdapter;
    private static LabCategoryAdapter labCategoryAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<Object> Carddata;
    private static ArrayList<LabCategoryDataModel> CatArraylist;
    String patname, patid, indate;
    ImageView card_arrow;
    private RecyclerView.LayoutManager layoutManager;
    LabCategoryDataModel mLabCategoryDataModel;
    Labtestcultnotemodel mLabtestcultnotemodel;
    LabTestDataModel mLabTestDataModel;
    Labtestculturemodel mLabtestculturemodel;
    Labtestcultureantimodel mLabtestcultureantimodel;
    String fragment_cd = "4";
    AdmissionHistoryModel model;
    public Lbres_Fragment() {

    }
    public Lbres_Fragment(AdmissionHistoryModel model) {
       this.model=model;
    }
    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_labres, container, false);
        Controller.LOADER_DIALOG.showDialog("جاري جلب بيانات الطلبات للمريض");
        recyclerView = view.findViewById(R.id.laborder_recycler_view);
        Carddata = new ArrayList<>();
        laborderCardviewAdapter = new LaborderCardviewAdapter(Carddata, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(laborderCardviewAdapter);

        preparedeptData();
        Controller.LOADER_DIALOG.hideDialog();
        card_arrow = view.findViewById(R.id.card_arrow);
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
    }


    private void preparedeptData() {
        patid = ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "";
        indate = ((ActivityPatient) getActivity()).getmCardviewDataModel().getIndate() + "";
        Map<String, String> map = new HashMap<>();
        map.put("patid", patid);

        if(model==null)
            map.put("indate",model==null? indate:model.getTIME_IN());
        else
            map.put("P_ADM_CD", model.getADMISSION_CODE());

        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", patid);
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "عرض نتيجة فحوصات المختبر");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        Log.d("map",map.toString());
        mInterfacePatient.showLoading(true);
        MyRequest.makeRquest(getContext(), Controller.LAB_ORDERS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray orders_arr = mJSONObject.getJSONArray("LAB_ORDERS");
                    if (orders_arr.length() > 0) {
                        for (int i = 0; i < orders_arr.length(); i++) {
                            JSONObject obj = orders_arr.getJSONObject(i);
                            if (obj != null) {
                                if (obj.getString("ORDER_CD") != null) {
                                    LaborderCardviewDataModel Card = new LaborderCardviewDataModel((obj.getString("ORDER_REQUEST_DATE")), Integer.parseInt(obj.getString("ORDER_CD")),
                                            Integer.parseInt(obj.getString("ORDER_YEAR")), Integer.parseInt(obj.getString("HOS_NO")), Integer.parseInt(obj.getString("ORDER_PATREC_CODE")));
                                    Carddata.add(Card);

                                }
                            }
                        }
                    } else {
                        LinearLayout emptyreslab = getView().findViewById(R.id.emptyreslab_layout);
                        emptyreslab.setVisibility(View.VISIBLE);
                    }
                    recyclerView.setAdapter(laborderCardviewAdapter);
                    laborderCardviewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_dept);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//
//    }

    public void onResume() {
        super.onResume();
        // Set title bar
//        ((HomeActivity) getActivity())
//                .setActionBarTitle("نتائج الفحوصات");
    }

    FragmentLabResult mfragmentLabResult;
    ArrayList<Object> mListObject;
    @Override
    public void onLabClick(Object object, int pos) {
//        Toast.makeText(getContext(), pos + "", Toast.LENGTH_LONG).show();
        if (object instanceof LaborderCardviewDataModel) {
            mfragmentLabResult = new FragmentLabResult((LaborderCardviewDataModel) object, mListObject, pos);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            mfragmentLabResult.show(ft, "FragmentLabResult");
        }
        /*
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("ORDER_CD", String.valueOf(mLaborderCardviewDataModel.getLaborderid()));
        map.put("ORDER_YEAR", String.valueOf(mLaborderCardviewDataModel.getLaborderyear()));
        map.put("PATRIC_CD", String.valueOf(mLaborderCardviewDataModel.getPatpatricID()));
        map.put("ORDER_DATE", mLaborderCardviewDataModel.getLaborderdate());
        map.put("HOS_NO", String.valueOf(mLaborderCardviewDataModel.getHos_no()));

//        map.put("ORDER_CD", "10113066");
//        map.put("ORDER_YEAR", "202008");
//        map.put("PATRIC_CD", "292562");
//        map.put("ORDER_DATE", "25/08/2020");
//        map.put("HOS_NO", "11");
        MyRequest.makeRquest(getContext(), Controller.LAB_RESULT_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {

                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray cat_arr = (JSONArray) mJSONObject.getJSONArray("CAT");
                    if (cat_arr.length() > 0) {
                        Log.e("MYTEST","FLAG=1");
                        ArrayList<Object> mListObject = new ArrayList<>();
                        for (int i = 0; i < cat_arr.length(); i++) {
                            JSONObject cat_obj = cat_arr.getJSONObject(i);
                            Log.e("json2:", cat_obj.toString());
                            Log.e("jsongroup:", cat_obj.toString());
                            mLabCategoryDataModel = new LabCategoryDataModel(cat_obj.getString("CATEGORY_NAME"), cat_obj.getString("ORDER_STATUS_NAME_EN"), Integer.parseInt(cat_obj.getString("D_ORDER_STATUS")),
                                    Integer.parseInt(cat_obj.getString("CATEGORY_ID")), cat_obj.getString("GROUP_NAME_EN"), cat_obj.getString("GROUP_CD"));
                            mListObject.add(mLabCategoryDataModel);
                            JSONObject tests = cat_arr.getJSONObject(i);
                            JSONArray test_arr = (JSONArray) tests.getJSONArray("TESTS");
                            if (test_arr.length() > 0) {

                                for (int y = 0; y < test_arr.length(); y++) {
                                    JSONObject test_obj = test_arr.getJSONObject(y);
                                    if (!cat_obj.getString("GROUP_CD").equals("7")) {
                                        Log.e("ZAIN","TRUE");
                                        if(y==0) {
                                            mLabTestDataModel = new LabTestDataModel();
                                            mListObject.add(mLabTestDataModel);
                                        }
                                        mLabTestDataModel = new LabTestDataModel(
                                                test_obj.getString("TEST_ITEMS_NAME"),
                                                test_obj.getString("TEST_UNIT"),
                                                test_obj.getString("RESULT_VALUE").equals("null") ? " " : test_obj.getString("RESULT_VALUE"),
                                                test_obj.getString("REFERANCE_NORMAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_NORMAL_VALUE"),
                                                test_obj.getString("REFERANCE_CRITICAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_CRITICAL_VALUE")
                                        );
                                        mListObject.add(mLabTestDataModel);

                                    } else {

                                        Log.e("ZAIN","FALSE");
                                        if (cat_obj.getString("CATEGORY_ID").equals("125") || cat_obj.getString("CATEGORY_ID").equals("126") || cat_obj.getString("CATEGORY_ID").equals("256") || cat_obj.getString("CATEGORY_ID").equals("15")) {
                                            mLabtestcultnotemodel = new Labtestcultnotemodel(
                                                    test_obj.getString("GRAMSTAIN").equals("null") ? " " : test_obj.getString("GRAMSTAIN"),
                                                    test_obj.getString("ACID_FAST_STAIN").equals("null") ? " " : test_obj.getString("ACID_FAST_STAIN"),
                                                    test_obj.getString("KOH").equals("null") ? " " : test_obj.getString("KOH"),
                                                    test_obj.getString("FUNGI").equals("null") ? " " : test_obj.getString("FUNGI"),
                                                    test_obj.getString("RESULT_NOTE").equals("null") ? " " : test_obj.getString("RESULT_NOTE")

                                            );
                                            mListObject.add(mLabtestcultnotemodel);

                                        } else {

                                            Log.e("MYERRROR", "mLabtestculturemodel" + y);
                                            mLabtestculturemodel = new Labtestculturemodel(
                                                    test_obj.getString("CATEGORY_NAME_AR"),
                                                    test_obj.getString("STATE_CULTURE_NAME"),
                                                    test_obj.getString("ORGANISM_NAME_A"),
                                                    test_obj.getString("ORGANISM_COUNT_A"),
                                                    test_obj.getString("ORGANISM_NAME_B"),
                                                    test_obj.getString("ORGANISM_COUNT_B"),
                                                    test_obj.getString("ORGANISM_NAME_C"),
                                                    test_obj.getString("ORGANISM_COUNT_C"),
                                                    test_obj.getString("STATE_CULTURE_CODE")

                                            );

                                            mListObject.add(mLabtestculturemodel);
                                            JSONObject cult_anti = test_arr.getJSONObject(y);

                                            try {
                                                JSONArray cultanti_arr = (JSONArray) cult_anti.getJSONArray("CULT");
                                                if (cultanti_arr.length() > 0) {

                                                    Labtestcultureantimodel mLabtestcultureantimodel = new Labtestcultureantimodel("antibiotic", "A", "B", "C");
                                                    mLabtestcultureantimodel.setHeader("1");
                                                    mListObject.add(mLabtestcultureantimodel);

                                                    Gson mGson = new Gson();
                                                    ArrayList<Labtestcultureantimodel> mListTicketType = mGson.fromJson(cultanti_arr.toString(), new TypeToken<ArrayList<Labtestcultureantimodel>>() {
                                                    }.getType());
                                                    mListObject.addAll(mListTicketType);

                                                }
                                            }
                                            catch (Exception c)
                                            {

                                            }

                                        }
                                    }
                                }
                            }
                        }
                        Log.e("MYTEST","FLAG=2");
                        Log.e("ZFT",mListObject.size()+"size");
                        if(mListObject.size()>0) {

                            laborderCardviewAdapter.getDataSet().addAll(pos + 1, mListObject);
                        }
                        laborderCardviewAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("MYTEST","FLAG=10"+" ERROR="+e.getMessage());
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                mInterfacePatient.showLoading(false);
                Log.e("MYTEST","FLAG=11");
            }
        });

         */
    }
}