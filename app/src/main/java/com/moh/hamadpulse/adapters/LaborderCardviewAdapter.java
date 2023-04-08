package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.CategoryStatusTempModel;
import com.moh.hamadpulse.models.CentralLabOrderModel;
import com.moh.hamadpulse.models.LabCategoryDataModel;
import com.moh.hamadpulse.models.LabTestDataModel;
import com.moh.hamadpulse.models.LaborderCardviewDataModel;
import com.moh.hamadpulse.models.Labtestcultnotemodel;
import com.moh.hamadpulse.models.Labtestcultureantimodel;
import com.moh.hamadpulse.models.Labtestculturemodel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaborderCardviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context con;
    TextView ststus;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<LabCategoryDataModel> mListCat;
    LabCategoryDataModel mLabCategoryDataModel;
    ArrayList<Object> mListTest;
    LabTestDataModel mLabTestDataModel;
    Labtestculturemodel mLabtestculturemodel;
    Labtestcultureantimodel mLabtestcultureantimodel;
    Labtestcultnotemodel mLabtestcultnotemodel;
    ArrayList<Object> mantiList;
    LabCategoryAdapter labcat;
    //CulturantiAdapter anticult;
    Context context;
    private ArrayList<Object> dataSet;
//    private ArrayList<CategoryStatusTempModel> models
//            =new ArrayList<>();

    public final int ITEM_TYPE_REQUEST = 1;
    public final int ITEM_LAB_CULT_NOTES = 2;


    InterfaceLabAdapterClick mInterfaceLabAdapterClick;

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        cx = holder.itemView.getContext();
        switch (holder.getItemViewType()) {
            case ITEM_TYPE_REQUEST:
                ViewHolderRequest mViewHolderRequest = (ViewHolderRequest) holder;
                if (dataSet.get(position)
                        instanceof LaborderCardviewDataModel) {
                    final LaborderCardviewDataModel mLaborderCardviewDataModel =
                            (LaborderCardviewDataModel) dataSet.get(position);

                    mViewHolderRequest.txtorderdate.setText(mLaborderCardviewDataModel.
                            getLaborderdate());
                    mViewHolderRequest.txtorderid.setText(String.valueOf(mLaborderCardviewDataModel.getLaborderid()));
                    RecyclerView rv = mViewHolderRequest.rv_cat_status;
//                perparData(mLaborderCardviewDataModel);
//                Log.d("test",models.size()+" , befor adapter");
                    Map<String, String> map = new HashMap<>();
                    map.put("ORDER_CD", String.valueOf(mLaborderCardviewDataModel.getLaborderid()));
                    map.put("ORDER_YEAR", String.valueOf(mLaborderCardviewDataModel.getLaborderyear()));
                    map.put("PATRIC_CD", String.valueOf(mLaborderCardviewDataModel.getPatpatricID()));
                    map.put("ORDER_DATE", mLaborderCardviewDataModel.getLaborderdateOnly());
                    map.put("HOS_NO", String.valueOf(mLaborderCardviewDataModel.getHos_no()));
                    Log.e("cat_map", map.toString());
                    MyRequest.makeRquest(cx, Controller.LAB_RESULT_URL, map, new MyRequest.CallBack() {
                        @Override
                        public void Result(String response) {
                            JSONObject mJSONObject = null;
                            ArrayList<CategoryStatusTempModel> models
                                    = new ArrayList<>();
                            try {
                                mJSONObject = new JSONObject(response);
                                JSONArray cat_arr = (JSONArray) mJSONObject.getJSONArray("CAT");
                                if (cat_arr.length() > 0) {
                                    for (int i = 0; i < cat_arr.length(); i++) {
                                        JSONObject cat_obj = cat_arr.getJSONObject(i);
                                        models.add(new CategoryStatusTempModel(cat_obj.getString("CATEGORY_NAME"),
                                                cat_obj.getString("ORDER_STATUS_NAME_EN"), cat_obj.getString("D_ORDER_STATUS")));
                                        AdapterCategoryStatus adapter = new AdapterCategoryStatus(models, mLaborderCardviewDataModel
                                                , mInterfaceLabAdapterClick);
                                        rv.setAdapter(adapter);
                                        rv.setLayoutManager(new LinearLayoutManager(cx));
                                    }
                                }
                            } catch (Exception e) {
//                            Log.d("test",models.size()+" ,at exception");
                            }
                        }

                        @Override
                        public void Error(VolleyError error) {
                            Controller.view_error(error, cx);
//                        Log.d("test",models.size()+" ,at error");
                        }
                    });

//                mViewHolderRequest.txtlaborderyear.setText(String.valueOf(mLaborderCardviewDataModel.getLaborderyear()));
//                mViewHolderRequest.txtpatriceID.setText(String.valueOf(mLaborderCardviewDataModel.getPatpatricID()));
//                mViewHolderRequest.txthosno.setText(String.valueOf(mLaborderCardviewDataModel.getHos_no()));
                    mViewHolderRequest.itemView.setBackgroundColor(cx.getResources().getColor(R.color.viewBg));
                    if (mLaborderCardviewDataModel.isClick())
                        mViewHolderRequest.itemView.setBackgroundColor(cx.getResources().getColor(R.color.backgroundcolor));
                    mViewHolderRequest.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mLaborderCardviewDataModel.setClick(true);
                            notifyItemChanged(position);
                            mInterfaceLabAdapterClick.onLabClick(mLaborderCardviewDataModel, -1);
                        }
                    });
                } else {
                    final CentralLabOrderModel centralLabOrderModel =
                            (CentralLabOrderModel) dataSet.get(position);
                    mViewHolderRequest.txtorderdate.setText(centralLabOrderModel.
                            getRequestDate());
                    mViewHolderRequest.txtorderid.setText(String.valueOf(centralLabOrderModel.getLabOrderId()));
                    RecyclerView rv = mViewHolderRequest.rv_cat_status;
                    AdapterCategoryStatus adapter = new AdapterCategoryStatus(centralLabOrderModel, mInterfaceLabAdapterClick);
                    adapter.setCenterLabCategoryModels(centralLabOrderModel.getCat());
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(cx));
                    mViewHolderRequest.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            centralLabOrderModel.setClick(true);
                            notifyItemChanged(position);
                            mInterfaceLabAdapterClick.onLabClick(centralLabOrderModel, -1);
                        }
                    });
                }
                break;

            case ITEM_LAB_CULT_NOTES:
                Labtestcultnotemodel cultnotedata = (Labtestcultnotemodel) dataSet.get(position);
                ViewHolderLabCultNotes mViewHolderLabCultNotes = (ViewHolderLabCultNotes) holder;
                mViewHolderLabCultNotes.gramstain.setText(cultnotedata.getGramstain());
                mViewHolderLabCultNotes.acidfaststain.setText(cultnotedata.getAcidfaststain());
                mViewHolderLabCultNotes.KOH.setText(cultnotedata.getKOH());
                mViewHolderLabCultNotes.Fungi.setText(cultnotedata.getFungi());
                mViewHolderLabCultNotes.notes.setText(cultnotedata.getNotes());
                break;


        }

    }

    public LaborderCardviewAdapter(ArrayList<Object> dataSet, InterfaceLabAdapterClick mInterfaceLabAdapterClick) {
        this.dataSet = dataSet;
        this.mInterfaceLabAdapterClick = mInterfaceLabAdapterClick;

    }

    public ArrayList<Object> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<Object> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        Object object = dataSet.get(position);
        if (object instanceof LaborderCardviewDataModel || object instanceof CentralLabOrderModel)
            return ITEM_TYPE_REQUEST;
        else if (object instanceof Labtestcultnotemodel)
            return ITEM_LAB_CULT_NOTES;

//        else
//            return ITEM_TYPE_RESULT;
        return 0;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Log.e("test", "viewType=" + viewType);
        switch (viewType) {
//            case ITEM_TYPE_REQUEST:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_order_cardview, parent, false);
//                return new ViewHolderRequest(view);

            case ITEM_LAB_CULT_NOTES:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.culture_res_notes, parent, false);
                return new ViewHolderLabCultNotes(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_order_cardview, parent, false);
                return new ViewHolderRequest(view);
        }
    }

    Context cx;

    public interface InterfaceLabAdapterClick {
        void onLabClick(Object object, int pos);
    }

    private void perparData(LaborderCardviewDataModel mLaborderCardviewDataModel) {
        Map<String, String> map = new HashMap<>();
        map.put("ORDER_CD", String.valueOf(mLaborderCardviewDataModel.getLaborderid()));
        map.put("ORDER_YEAR", String.valueOf(mLaborderCardviewDataModel.getLaborderyear()));
        map.put("PATRIC_CD", String.valueOf(mLaborderCardviewDataModel.getPatpatricID()));
        map.put("ORDER_DATE", mLaborderCardviewDataModel.getLaborderdate());
        map.put("HOS_NO", String.valueOf(mLaborderCardviewDataModel.getHos_no()));
        MyRequest.makeRquest(cx, Controller.LAB_RESULT_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray cat_arr = (JSONArray) mJSONObject.getJSONArray("CAT");
                    if (cat_arr.length() > 0) {
                        for (int i = 0; i < cat_arr.length(); i++) {
                            JSONObject cat_obj = cat_arr.getJSONObject(i);
//                           models.add(new CategoryStatusTempModel(cat_obj.getString("CATEGORY_NAME"),
//                                    cat_obj.getString("ORDER_STATUS_NAME_EN")));
//                           Log.d("test",models.size()+" , in method");
                        }
                    }
                } catch (Exception e) {
//                    Log.d("test",models.size()+" ,at exception");
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, cx);
//                Log.d("test",models.size()+" ,at error");
            }
        });
//        Log.d("test",models.size()+" ,at the end of method");
    }

//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
//        final LaborderCardviewDataModel Carddata = dataSet.get(listPosition);
//
//        holder.txtorderdate.setText(Carddata.getLaborderdate());
//        holder.txtorderid.setText(String.valueOf(Carddata.getLaborderid()));
//        holder.txtlaborderyear.setText(String.valueOf(Carddata.getLaborderyear()));
//        holder.txtpatriceID.setText(String.valueOf(Carddata.getPatpatricID()));
//        holder.txthosno.setText(String.valueOf(Carddata.getHos_no()));
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayout card_container = (LinearLayout) holder.itemView.findViewById(R.id.card_container);
//                Integer visiblity = card_container.getVisibility();
//                if (visiblity == 8) {
//                    card_container.setVisibility(View.VISIBLE);
//                    if (Carddata.getmListCat().size() == 0) {
//                        Map<String, String> map = new HashMap<>();
//                        map.put("ORDER_CD", String.valueOf(Carddata.getLaborderid()));
//                        map.put("ORDER_YEAR", String.valueOf(Carddata.getLaborderyear()));
//                        map.put("PATRIC_CD", String.valueOf(Carddata.getPatpatricID()));
//                        map.put("ORDER_DATE", Carddata.getLaborderdate());
//                        map.put("HOS_NO", String.valueOf(Carddata.getHos_no()));
//                        Log.e("test", map.toString());
//                        Log.e("test", Controller.LAB_RESULT_URL);
//                        //       Toast.makeText(holder.itemView.getContext(), "Request Cat", Toast.LENGTH_SHORT).show();
//                        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.LAB_RESULT_URL, map, new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject jsonObject) {
//
//                                try {
//                                    Log.e("json:", jsonObject.toString());
//                                    JSONArray cat_arr = (JSONArray) jsonObject.getJSONArray("CAT");
//                                    if (cat_arr.length() > 0) {
//
//                                        for (int i = 0; i < cat_arr.length(); i++) {
//                                            JSONObject cat_obj = cat_arr.getJSONObject(i);
//                                            Log.e("json2:", cat_obj.toString());
//                                            Log.e("jsongroup:", cat_obj.toString());
//                                            mLabCategoryDataModel = new LabCategoryDataModel(cat_obj.getString("CATEGORY_NAME"), cat_obj.getString("ORDER_STATUS_NAME_EN"), Integer.parseInt(cat_obj.getString("D_ORDER_STATUS")),
//                                                    Integer.parseInt(cat_obj.getString("CATEGORY_ID")), cat_obj.getString("GROUP_NAME_EN"), cat_obj.getString("GROUP_CD"));
//                                            Carddata.getmListCat().add(mLabCategoryDataModel);
//                                            JSONObject tests = cat_arr.getJSONObject(i);
//                                            JSONArray test_arr = (JSONArray) tests.getJSONArray("TESTS");
//                                            if (test_arr.length() > 0) {
//                                                Log.e("json3:", test_arr.toString());
//
//                                                for (int y = 0; y < test_arr.length(); y++) {
//                                                    JSONObject test_obj = test_arr.getJSONObject(y);
//                                                    if (!cat_obj.getString("GROUP_CD").equals("7")) {
//                                                        Log.e("MYERRROR", "mLabTestDataModel=" + y);
//                                                        mLabTestDataModel = new LabTestDataModel(
//                                                                test_obj.getString("TEST_ITEMS_NAME"),
//                                                                test_obj.getString("TEST_UNIT"),
//                                                                test_obj.getString("RESULT_VALUE").equals("null") ? " " : test_obj.getString("RESULT_VALUE"),
//                                                                test_obj.getString("REFERANCE_NORMAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_NORMAL_VALUE"),
//                                                                test_obj.getString("REFERANCE_CRITICAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_CRITICAL_VALUE")
//                                                        );
//                                                        mLabCategoryDataModel.getmListTest().add(mLabTestDataModel);
//
//                                                    } else {
//                                                        if (cat_obj.getString("CATEGORY_ID").equals("125") || cat_obj.getString("CATEGORY_ID").equals("126") || cat_obj.getString("CATEGORY_ID").equals("256") || cat_obj.getString("CATEGORY_ID").equals("15")) {
//                                                            Log.e("MYERRROR", "mLabtestcultnotemodel" + y);
//                                                            mLabtestcultnotemodel = new Labtestcultnotemodel(
//                                                                    test_obj.getString("GRAMSTAIN").equals("null") ? " " : test_obj.getString("GRAMSTAIN"),
//                                                                    test_obj.getString("ACID_FAST_STAIN").equals("null") ? " " : test_obj.getString("ACID_FAST_STAIN"),
//                                                                    test_obj.getString("KOH").equals("null") ? " " : test_obj.getString("KOH"),
//                                                                    test_obj.getString("FUNGI").equals("null") ? " " : test_obj.getString("FUNGI"),
//                                                                    test_obj.getString("RESULT_NOTE").equals("null") ? " " : test_obj.getString("RESULT_NOTE")
//
//                                                            );
//                                                            mLabCategoryDataModel.getmListTest().add(mLabtestcultnotemodel);
//
//                                                        } else {
//
//                                                            Log.e("MYERRROR", "mLabtestculturemodel" + y);
//                                                            mLabtestculturemodel = new Labtestculturemodel(
//                                                                    test_obj.getString("CATEGORY_NAME_AR"),
//                                                                    test_obj.getString("STATE_CULTURE_NAME"),
//                                                                    test_obj.getString("ORGANISM_NAME_A"),
//                                                                    test_obj.getString("ORGANISM_COUNT_A"),
//                                                                    test_obj.getString("ORGANISM_NAME_B"),
//                                                                    test_obj.getString("ORGANISM_COUNT_B"),
//                                                                    test_obj.getString("ORGANISM_NAME_C"),
//                                                                    test_obj.getString("ORGANISM_COUNT_C"),
//                                                                    test_obj.getString("STATE_CULTURE_CODE")
//
//                                                            );
//                                                            mLabCategoryDataModel.getmListTest().add(mLabtestculturemodel);
//                                                            JSONObject cult_anti = test_arr.getJSONObject(y);
//                                                            JSONArray cultanti_arr = (JSONArray) cult_anti.getJSONArray("CULT");
//                                                            if (cultanti_arr.length() > 0) {
//                                                                Log.e("cult_anti:", cultanti_arr.toString());
//
//                                                                for (int H = 0; H < cultanti_arr.length(); H++) {
//                                                                    JSONObject cultanti_obj = cultanti_arr.getJSONObject(H);
//                                                                    Log.e("MYanti", "mLabtestcultureantimodel" + H);
//                                                                    mLabtestcultureantimodel = new Labtestcultureantimodel(
//                                                                            cultanti_obj.getString("TEST_NAME_EN"),
//                                                                            cultanti_obj.getString("CULTURE_A_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_A_ABBR"),
//                                                                            cultanti_obj.getString("CULTURE_B_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_B_ABBR"),
//                                                                            cultanti_obj.getString("CULTURE_C_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_C_ABBR")
//                                                                    );
//                                                                    mLabtestculturemodel.getMantiList().add(mLabtestcultureantimodel);
//                                                                }
//                                                                Log.e("cult_antifor:", cultanti_arr.toString());
//
//                                                            }
//                                                        }
//
//                                                    }
//
//                                                }
//                                            }
//                                        }
//                                        notifyDataSetChanged();
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                    Log.e("json", "ERROR" + e.getMessage());
//                                    Toast.makeText(holder.itemView.getContext(), "No orders", Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(final VolleyError volleyError) {
//                                android.os.Handler mHandler = new android.os.Handler();
//                                if (volleyError instanceof NetworkError) {
//                                } else if (volleyError instanceof ServerError) {
//                                } else if (volleyError instanceof AuthFailureError) {
//                                } else if (volleyError instanceof ParseError) {
//                                } else if (volleyError instanceof NoConnectionError) {
//                                } else if (volleyError instanceof TimeoutError) {
//                                    Log.e("error", volleyError.getClass().getName());
//
//                                }
//                            }
//                        });
//
//                        Controller.getInstance().addToRequestQueue(jsObjRequest);
//
//                    }
//                } else {
//                    card_container.setVisibility(View.GONE);
//                }
//            }
//        });
//        labcat = new LabCategoryAdapter(Carddata.getmListCat(), context);
//        mLinearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
//        holder.catRecyclerview.setLayoutManager(mLinearLayoutManager);
//        holder.catRecyclerview.setAdapter(labcat);
//
//    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderRequest extends RecyclerView.ViewHolder {

        TextView txtorderdate, txtorderid;
        RecyclerView rv_cat_status;
        public ViewHolderRequest(View itemView) {
            super(itemView);
            this.txtorderdate = (TextView) itemView.findViewById(R.id.txtorderdate);
            this.txtorderid = (TextView) itemView.findViewById(R.id.txtorderid);
            this.rv_cat_status = itemView.findViewById(R.id.RV_CategoryStatus);
        }
    }

    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }






    public static class ViewHolderLabCultNotes extends RecyclerView.ViewHolder {

        TextView gramstain, acidfaststain, KOH, Fungi, notes;

        public ViewHolderLabCultNotes(View itemView) {
            super(itemView);
            this.gramstain = (TextView) itemView.findViewById(R.id.txt_gramstain);
            this.acidfaststain = (TextView) itemView.findViewById(R.id.txt_acidfaststain);
            this.KOH = (TextView) itemView.findViewById(R.id.txt_KOH);
            this.Fungi = (TextView) itemView.findViewById(R.id.txt_Fungi);
            this.notes = (TextView) itemView.findViewById(R.id.txt_notes);

        }
    }


}