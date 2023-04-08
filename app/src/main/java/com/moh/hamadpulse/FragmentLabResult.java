package com.moh.hamadpulse;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterResultLab;
import com.moh.hamadpulse.models.CenterLabCategoryModel;
import com.moh.hamadpulse.models.CentralLabOrderModel;
import com.moh.hamadpulse.models.LabCategoryDataModel;
import com.moh.hamadpulse.models.LabTestDataModel;
import com.moh.hamadpulse.models.LaborderCardviewDataModel;
import com.moh.hamadpulse.models.Labtestcultnotemodel;
import com.moh.hamadpulse.models.Labtestcultureantimodel;
import com.moh.hamadpulse.models.Labtestculturemodel;
import com.moh.hamadpulse.models.TestModel;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentLabResult extends DialogFragment {
    private TextView idOrderLab, idDateLab;
    private RecyclerView rvLabResult;
    LinearLayout llCloseLabResult;
    LaborderCardviewDataModel mLaborderCardviewDataModel;
    ArrayList<Object> mListObject;
    AdapterResultLab mAdapterResultLab;
    AVLoadingIndicatorView imgLoading;
    CentralLabOrderModel centralLabOrderModel;
    boolean flag;
    int position;

    public FragmentLabResult(LaborderCardviewDataModel mLaborderCardviewDataModel,
                             ArrayList<Object> mListObject, int position) {
        // Required empty public constructor
        this.mLaborderCardviewDataModel = mLaborderCardviewDataModel;
        this.mListObject = mListObject;
        flag = true;
        this.position = position;
    }

    public FragmentLabResult(CentralLabOrderModel centralLabOrderModel, ArrayList<Object> mListObject,
                             int position) {
        // Required empty public constructor
        this.centralLabOrderModel = centralLabOrderModel;
        this.mListObject = mListObject;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lab_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idOrderLab = view.findViewById(R.id.idOrderLab);
        idDateLab = view.findViewById(R.id.idDateLab);
        imgLoading = view.findViewById(R.id.imgLoading);
        rvLabResult = view.findViewById(R.id.rvLabResult);
        llCloseLabResult = view.findViewById(R.id.llCloseLabResult);
        if (flag) {
            idOrderLab.setText(mLaborderCardviewDataModel.getLaborderid() + "");
            idDateLab.setText(mLaborderCardviewDataModel.getLaborderdate() + "");
        } else {
            idOrderLab.setText(centralLabOrderModel.getLabOrderId());
            idDateLab.setText(centralLabOrderModel.getRequestDate() + "");
        }
        rvLabResult = view.findViewById(R.id.rvLabResult);
        mListObject = new ArrayList<>();
        mAdapterResultLab = new AdapterResultLab(mListObject);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvLabResult.setLayoutManager(mLayoutManager);
        rvLabResult.setAdapter(mAdapterResultLab);
        llCloseLabResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getLabResult();
    }

    LabCategoryDataModel mLabCategoryDataModel;
    LabTestDataModel mLabTestDataModel;
    Labtestcultnotemodel mLabtestcultnotemodel;
    Labtestculturemodel mLabtestculturemodel;
    Labtestcultureantimodel mLabtestcultureantimodel;

    private void getLabResult() {
        imgLoading.setVisibility(View.VISIBLE);
        if (flag) {
            Map<String, String> map = new HashMap<>();
            map.put("ORDER_CD", String.valueOf(mLaborderCardviewDataModel.getLaborderid()));
            map.put("ORDER_YEAR", String.valueOf(mLaborderCardviewDataModel.getLaborderyear()));
            map.put("PATRIC_CD", String.valueOf(mLaborderCardviewDataModel.getPatpatricID()));
            map.put("ORDER_DATE", mLaborderCardviewDataModel.getLaborderdateOnly());
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
                            for (int i = 0; i < cat_arr.length(); i++) {
                                JSONObject cat_obj = cat_arr.getJSONObject(i);
                                mLabCategoryDataModel = new LabCategoryDataModel
                                        (cat_obj.getString("CATEGORY_NAME")
                                                , cat_obj.getString("ORDER_STATUS_NAME_EN"),
                                                Integer.parseInt(cat_obj.getString("D_ORDER_STATUS")),
                                                Integer.parseInt(cat_obj.getString("CATEGORY_ID")),
                                                cat_obj.getString("GROUP_NAME_EN"),
                                                cat_obj.getString("GROUP_CD"));
                                mListObject.add(mLabCategoryDataModel);
                                JSONObject tests = cat_arr.getJSONObject(i);
                                JSONArray test_arr = (JSONArray) tests.getJSONArray("TESTS");
                                if (test_arr.length() > 0) {
                                    for (int y = 0; y < test_arr.length(); y++) {
                                        JSONObject test_obj = test_arr.getJSONObject(y);
                                        if (!cat_obj.getString("GROUP_CD").equals("7")) {

                                            if (y == 0) {
                                                mLabTestDataModel = new LabTestDataModel();
                                                mLabTestDataModel.setHeader("0");
                                                mListObject.add(mLabTestDataModel);
                                            }
                                            mLabTestDataModel = new LabTestDataModel(
                                                    test_obj.getString("TEST_ITEMS_NAME"),
                                                    test_obj.getString("TEST_UNIT"),
                                                    test_obj.getString("RESULT_VALUE").equals("null") ? " " : test_obj.getString("RESULT_VALUE"),
                                                    test_obj.getString("REFERANCE_NORMAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_NORMAL_VALUE"),
                                                    test_obj.getString("REFERANCE_CRITICAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_CRITICAL_VALUE")
                                            );
                                            mLabTestDataModel.setFrom_To(true);
                                            mListObject.add(mLabTestDataModel);

                                        } else {

                                            if (cat_obj.getString("CATEGORY_ID").equals("125") ||
                                                    cat_obj.getString("CATEGORY_ID").equals("126") ||
                                                    cat_obj.getString("CATEGORY_ID").equals("256") ||
                                                    cat_obj.getString("CATEGORY_ID").equals("15")) {
                                                mLabtestcultnotemodel = new Labtestcultnotemodel(test_obj.getString("GRAMSTAIN").equals("null") ? " " : test_obj.getString("GRAMSTAIN"),
                                                        test_obj.getString("ACID_FAST_STAIN").equals("null") ? " " : test_obj.getString("ACID_FAST_STAIN"),
                                                        test_obj.getString("KOH").equals("null") ? " " : test_obj.getString("KOH"),
                                                        test_obj.getString("FUNGI").equals("null") ? " " : test_obj.getString("FUNGI"),
                                                        test_obj.getString("RESULT_NOTE").equals("null") ? " " : test_obj.getString("RESULT_NOTE")

                                                );
                                                mListObject.add(mLabtestcultnotemodel);

                                            } else {
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
                                                } catch (Exception c) {

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    if(position!=-1&&position<mListObject.size())
//                    Collections.swap(mListObject,position,0);
//                    if(position!=-1&&position<mListObject.size()){
//                        mListObject.add(0,mListObject.get(position));
//                        mListObject.remove(position);
//                    }

//                        mListObject.add(0,mListObject.remove(position));
                    imgLoading.setVisibility(View.GONE);
                    mAdapterResultLab.notifyDataSetChanged();
//                    mAdapterResultLab.setHasStableIds(true);
                    if (position != -1) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                rvLabResult.getLayoutManager().moveView(position,0);
//                            }
//                        },1000);


//                        rvLabResult.getLayoutManager().scrollToPosition(position);
//                        RecyclerView.SmoothScroller smoothScroller = new
//                                LinearSmoothScroller(getContext()) {
//                                    @Override protected int getVerticalSnapPreference() {
//                                        return LinearSmoothScroller.SNAP_TO_END;
//                                    }
//                                };
//                        smoothScroller.setTargetPosition(position);
//                        rvLabResult.getLayoutManager().startSmoothScroll(smoothScroller);
                    }
//                    Toast.makeText(getContext(),mListObject.size()+"", Toast.LENGTH_SHORT).show();
//                    rvLabResult.getLayoutManager().getChildAt(position).requestFocus();

                }

                @Override
                public void Error(VolleyError error) {
                    imgLoading.setVisibility(View.GONE);
                    Controller.view_error(error, getContext());
                }
            });
        } else {
            ArrayList<CenterLabCategoryModel> list = centralLabOrderModel.getCat();
            for (CenterLabCategoryModel model : list) {
                ArrayList<TestModel> test_arr = model.getTests();
                int size = test_arr.size();
                if (size > 0) {
                    for (int y = 0; y < size; y++) {
                        TestModel cat_obj = test_arr.get(y);
                        if (!cat_obj.getCategoryId().equals("7")) {

                            if (y == 0) {
                                mLabTestDataModel = new LabTestDataModel();
                                mLabTestDataModel.setHeader("0");
                                mListObject.add(mLabTestDataModel);
                            }
                            mLabTestDataModel = new LabTestDataModel(
                                    cat_obj.getTestItemsName(),
                                    cat_obj.getTestUnit(),
                                    cat_obj.getValue() == null ? " " :
                                            cat_obj.getValue(),
                                    cat_obj.getReferanceDiscription() == null ? " " : cat_obj.getReferanceDiscription(),
//                                cat_obj.getString("REFERANCE_CRITICAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_CRITICAL_VALUE")
                                    ""
                            );
                            mListObject.add(mLabTestDataModel);

                        } else {

                            if (cat_obj.getCategoryId().equals("125") ||
                                    cat_obj.getCategoryId().equals("126") ||
                                    cat_obj.getCategoryId().equals("256") ||
                                    cat_obj.getCategoryId().equals("15")) {
                                mLabtestcultnotemodel = new Labtestcultnotemodel(cat_obj.getGramstain().equals("null") ? " " : cat_obj.getGramstain(),
                                        cat_obj.getAcidFastStain().equals("null") ? " " : cat_obj.getAcidFastStain(),
//                                    cat_obj.getString("KOH").equals("null") ? " " : cat_obj.getString("KOH")
                                        "",
                                        cat_obj.getFungi().equals("null") ? " " : cat_obj.getFungi(),
                                        cat_obj.getNotes().equals("null") ? " " : cat_obj.getNotes() //getString("RESULT_NOTE")

                                );
                                mListObject.add(mLabtestcultnotemodel);

                            } else {


                                mLabtestculturemodel = new Labtestculturemodel(
                                        cat_obj.getCategoryName(),
                                        cat_obj.getCategoryName(),//"STATE_CULTURE_NAME"
                                        cat_obj.getOrganismAResultId(),
                                        cat_obj.getOrganismCountA(),
                                        cat_obj.getOrganismBResultId(),
                                        cat_obj.getOrganismCountB(),
                                        cat_obj.getOrganismCResultId(),
                                        cat_obj.getOrganismCountC(),
                                        cat_obj.getCultureStateId()

                                );

                                mListObject.add(mLabtestculturemodel);

//                            JSONObject cult_anti = test_arr.getJSONObject(y);

//                            try {
//                                JSONArray cultanti_arr = (JSONArray) cult_anti.getJSONArray("CULT");
//                                if (cultanti_arr.length() > 0) {
//
//                                    Labtestcultureantimodel mLabtestcultureantimodel = new Labtestcultureantimodel("antibiotic", "A", "B", "C");
//                                    mLabtestcultureantimodel.setHeader("1");
//                                    mListObject.add(mLabtestcultureantimodel);
//
//                                    Gson mGson = new Gson();
//                                    ArrayList<Labtestcultureantimodel> mListTicketType = mGson.fromJson(cultanti_arr.toString(), new TypeToken<ArrayList<Labtestcultureantimodel>>() {
//                                    }.getType());
//                                    mListObject.addAll(mListTicketType);
//
//                                }
//                            } catch (Exception c) {
//
//                            }
                            }
                        }
                    }
                }
            }
            imgLoading.setVisibility(View.GONE);
            mAdapterResultLab.notifyDataSetChanged();
        }
    }
}