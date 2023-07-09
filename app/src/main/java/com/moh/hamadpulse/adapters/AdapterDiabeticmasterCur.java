package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.DiabeticCurModel;
import com.moh.hamadpulse.models.DiabeticMasterCurModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterDiabeticmasterCur extends RecyclerView.Adapter<AdapterDiabeticmasterCur.DiabeticCurViewHolder> {
    ArrayList<DiabeticMasterCurModel> arrrayList_DiabeticCur = new ArrayList<>();
    Context context;
    AdapterDiabeticCur adapterDiabeticCur;
    ArrayList<DiabeticCurModel> diabeticCurModelArrayList;

    public AdapterDiabeticmasterCur(Context context, ArrayList<DiabeticMasterCurModel> arrrayList_DiabeticCur) {
        this.arrrayList_DiabeticCur = arrrayList_DiabeticCur;
        this.context = context;
    }

    public ArrayList<DiabeticMasterCurModel> getArrrayList_DiabeticCur() {
        return arrrayList_DiabeticCur;
    }

    public void setArrrayList_DiabeticCur(ArrayList<DiabeticMasterCurModel> arrrayList_DiabeticCur) {
        this.arrrayList_DiabeticCur = arrrayList_DiabeticCur;
    }

    @NonNull
    @Override
    public DiabeticCurViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiabeticCurViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.sliding_master_row, parent, false));

    }

    //
    @Override
    public void onBindViewHolder(@NonNull DiabeticCurViewHolder holder, int position) {
        DiabeticMasterCurModel diabeticmastCurModel = arrrayList_DiabeticCur.get(position);

        holder.order_date.setText(diabeticmastCurModel.getOrder_date());
        holder.ORDER_COUNT.setText(diabeticmastCurModel.getORDER_COUNT());
        holder.btnShow_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.details_view.getVisibility() == View.GONE) {
                    holder.details_view.setVisibility(View.VISIBLE);
                } else {
                    holder.details_view.setVisibility(View.GONE);
                }
                RecyclerView diabetic_RecyclerView = holder.RV_slidingdetails;
                diabeticCurModelArrayList = new ArrayList<>();
                adapterDiabeticCur = new AdapterDiabeticCur(diabeticCurModelArrayList);
                diabetic_RecyclerView.setHasFixedSize(true);
                diabetic_RecyclerView.setAdapter(adapterDiabeticCur);
                diabetic_RecyclerView.setLayoutManager(new LinearLayoutManager(context));
                //  adapterDiabeticCur= new AdapterDiabeticCur();
                Map<String, String> map = new HashMap<>();
                map.put("P_ADM_CD", ((ActivityPatient) context).getmCardviewDataModel().getAdmcd() + "");
                map.put("P_PATRIC_CD", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
                map.put("P_ORDER_DATE", String.valueOf(diabeticmastCurModel.getOrder_date()));
                map.put("TRANS_SCREEN_CD_IN", "37");
                map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
                map.put("TRANS_ACTION_CD_IN", "2");
                map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
                map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
                map.put("TRANS_DESCRIPTION_IN", "View DiabeticCur");
                MyRequest.makeRquest(context, Controller.GET_INP_DIABETIC_CHART_URL,
                        map, new MyRequest.CallBack() {
                            @Override
                            public void Result(String response) {

                                JSONObject mJSONObject = null;
                                try {
                                    mJSONObject = new JSONObject(response);
                                    JSONArray jsonArray = mJSONObject.getJSONArray("DIABETIC_CUR");

                                    //////////////////
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<ArrayList<DiabeticCurModel>>() {
                                    }.getType();
                                    diabeticCurModelArrayList = gson.fromJson(jsonArray.toString(),
                                            type);

                                    adapterDiabeticCur.setArrrayList_DiabeticCur
                                            (diabeticCurModelArrayList);
                                    adapterDiabeticCur.notifyDataSetChanged();


                                    // getIcdConstArrayList.add(getIcdConstArrayList);
                                } catch (JSONException e) {

                                    e.printStackTrace();
                                    Toast.makeText(context, "Api Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void Error(VolleyError error) {
                                if (context != null)
                                    Controller.view_error(error, context);
                            }
                        });
            }
        });
        Log.e("orderdate", "onBindViewHolder: " + diabeticmastCurModel.getOrder_date());


    }

    @Override
    public int getItemCount() {
        return arrrayList_DiabeticCur.size();
    }

    class DiabeticCurViewHolder extends RecyclerView.ViewHolder {
        private TextView order_date;
        private TextView ORDER_COUNT;
        private RecyclerView RV_slidingdetails;
        private ImageButton btnShow_Details;
        private LinearLayout details_view;

        public DiabeticCurViewHolder(@NonNull View itemView) {
            super(itemView);
            order_date = itemView.findViewById(R.id.txtorderdate);
            ORDER_COUNT = itemView.findViewById(R.id.txtordercount);
            RV_slidingdetails = itemView.findViewById(R.id.RV_slidingdetails);
            btnShow_Details = itemView.findViewById(R.id.btnShow_Details);
            details_view = itemView.findViewById(R.id.details_view);

        }
    }
}
