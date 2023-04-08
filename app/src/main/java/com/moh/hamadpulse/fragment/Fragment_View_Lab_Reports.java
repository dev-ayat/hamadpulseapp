package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.FragmentLabResult;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.LaborderCardviewAdapter;
import com.moh.hamadpulse.models.CentralLabOrderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Fragment_View_Lab_Reports extends Fragment implements LaborderCardviewAdapter.InterfaceLabAdapterClick {

    private static LaborderCardviewAdapter laborderCardviewAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<Object> Carddata;
    String fragment_cd = "4";
    InterfacePatient mInterfacePatient;

    public Fragment_View_Lab_Reports() {
        // Required empty public constructor
    }

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__view__lab__reports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.RV_Central_Lab_orders);
        Carddata = new ArrayList<>();
        laborderCardviewAdapter = new LaborderCardviewAdapter(Carddata, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(laborderCardviewAdapter);
        preparedeptData();
    }

    private void preparedeptData() {

        Map<String, String> map = new HashMap<>();
        map.put("P_PATENT_ID", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", 4 + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "CENTRAL_LAB");
        mInterfacePatient.showLoading(true);
        MyRequest.makeRquest(getContext(), Controller.GET_PATENT_LAB_ORDERS_PR_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {
                        try {
                            JSONObject mJSONObject = new JSONObject(response.toString());
                            JSONArray orders_arr = mJSONObject.getJSONArray("LAB_ORDERS");
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<CentralLabOrderModel>>() {
                            }.getType();
                            Carddata = gson.fromJson(orders_arr.toString(),
                                    type);

                            if (Carddata.size() == 0) {
                                LinearLayout emptyreslab = getView().findViewById(R.id.emptyreslab_layout);
                                emptyreslab.setVisibility(View.VISIBLE);
                            } else {
                                laborderCardviewAdapter.setDataSet(Carddata);
                                recyclerView.setAdapter(laborderCardviewAdapter);
                                laborderCardviewAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                            mInterfacePatient.showLoading(false);
                        }
                        mInterfacePatient.showLoading(false);
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Log.e("onErrorResponse", "error : " + error.getMessage());
                        mInterfacePatient.showLoading(false);
                        Controller.view_error(error, getContext());
                    }
                });
//            @Override
//
//                public void onErrorResponse(final VolleyError volleyError) {
//                    Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                    Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
//                    mInterfacePatient.showLoading(false);
//                }
//
//        });
//        jsObjRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                // Here goes the new timeout 3 minutes
//                return 3 * 60 * 1000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                // The max number of attempts
//                return 5;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//        MyRequest.makeRquest(getContext(), Controller.GET_PATENT_LAB_ORDERS_PR_URL, map, new MyRequest.CallBack() {
//            @Override
//            public void Result(String response) {
//                try {
//                    JSONObject mJSONObject = new JSONObject(response);
//                    JSONArray orders_arr = mJSONObject.getJSONArray("LAB_ORDERS");
//                    Gson gson = new Gson();
//                    Type type = new TypeToken<ArrayList<CentralLabOrderModel>>() {
//                    }.getType();
//                    Carddata = gson.fromJson(orders_arr.toString(),
//                            type);
//
//                    if (Carddata.size() == 0) {
//                        LinearLayout emptyreslab = getView().findViewById(R.id.emptyreslab_layout);
//                        emptyreslab.setVisibility(View.VISIBLE);
//                    } else {
//                        laborderCardviewAdapter.setDataSet(Carddata);
//                        recyclerView.setAdapter(laborderCardviewAdapter);
//                        laborderCardviewAdapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mInterfacePatient.showLoading(false);
//            }
//
//            @Override
//            public void Error(VolleyError error) {
//                mInterfacePatient.showLoading(false);
//            }
//        });


    }

    FragmentLabResult mfragmentLabResult;
    ArrayList<Object> mListObject;

    @Override
    public void onLabClick(Object object, int pos) {
        if (object instanceof CentralLabOrderModel) {
            mfragmentLabResult = new FragmentLabResult((CentralLabOrderModel) object, mListObject, pos);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            mfragmentLabResult.show(ft, "FragmentLabResult");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInterfacePatient.showLoading(false);
    }
}