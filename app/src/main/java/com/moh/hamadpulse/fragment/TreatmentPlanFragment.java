package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.celerysoft.tablefixheaders.TableFixHeaders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.MatrixTableAdapter2;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetTreatmentForPatient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
// TODO: الصيدلية
public class TreatmentPlanFragment extends Fragment implements
        AddTreatmentPlanFragment.ListenerAddTreatmentPlanFragment,ContenueTreatmentFragment.hideDialog {

    FloatingActionButton btn_treatmentplan_sign;
    TextView txtpatid, txtpatName, txtadmdate, txtdaycount;
    String patname, patid, admdate, patadmcd;
    LinearLayout emptyresult;
    ArrayList<GetTreatmentForPatient> treatmentArray;
    ArrayList<GetTreatmentForPatient> addtreatmentArray;
    MatrixTableAdapter2<GetTreatmentForPatient> adapter;
    TableFixHeaders tableFixHeaders;

    public TreatmentPlanFragment() {
        // Required empty public constructor
    }
    public TreatmentPlanFragment(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient=mInterfacePatient;
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
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_treatment_plan, container, false);
        btn_treatmentplan_sign = layout.findViewById(R.id.btn_treatmentplan_sign);
        tableFixHeaders = (TableFixHeaders) layout.findViewById(R.id.table);
        emptyresult = layout.findViewById(R.id.emptyresult_linearLayout);

        PrepareGetTreatmentForPatient();

        addtreatmentArray = new ArrayList<GetTreatmentForPatient>();
        btn_treatmentplan_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddTreatmentPlan();
            }
        });

        btn_treatmentplan_sign.setVisibility(View.VISIBLE);

        return layout;
    }

    public void PrepareGetTreatmentForPatient() {
        Map<String, String> map = new HashMap<>();
        patid = ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "";
        patadmcd = ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "";
        map.put("PATRIC_CD", patid);
        map.put("ADM_CD", patadmcd);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_TREATMENT_fOR_PATIENT_URL,
                map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", response.toString());
                try {
                    Log.e("response2", response.toString());
                    JSONArray jsonArray = response.getJSONArray("ADM_PHARM");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());
                    if (jsonArray.length() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GetTreatmentForPatient>>() {
                        }.getType();
                        treatmentArray = gson.fromJson(jsonArray.toString(), type);
                        adapter = new MatrixTableAdapter2<>(getContext());
                        adapter.setData(treatmentArray);
                        tableFixHeaders.setRowSelectable(true);
                        tableFixHeaders.setAdapter(adapter);
                        tableFixHeaders.setOnItemClickListener(new TableFixHeaders.OnItemClickListener() {
                            @Override
                            public void onItemClick(TableFixHeaders parent, View view, int row, int column, long id) {
                                //  Toast.makeText(getContext(), adapter.getItem(row).getAttribute(column).toString(), Toast.LENGTH_SHORT).show();
                                //    Toast.makeText(getContext(), adapter.getItem(row).getAttribute(column).toString(), Toast.LENGTH_SHORT).show();
                                //addtreatmentArray.add(treatmentArray.get(row));
                                if(column==0) {
                                    // ** HERE
                                    //if (Controller.pref.getString(USER_TYPE, "").equals("1"))
                                        ShowContinueTreatmentPlan(treatmentArray.get(row));
                                }


                            }
                        });

//                        tableFixHeaders.setOnItemLongClickListener(new TableFixHeaders.OnItemLongClickListener() {
//                            @Override
//                            public void onItemLongClick(TableFixHeaders parent, View view, int row, int column, long id) {
//                                addtreatmentArray.add(treatmentArray.get(row));
//                            }
//                        });

                        GetTreatmentForPatient mgetTreatmentForPatient = new GetTreatmentForPatient();
                        mgetTreatmentForPatient.setINPPHARMCREATEDON("Date");
                        mgetTreatmentForPatient.setITEMNAME("Drug name");
                        mgetTreatmentForPatient.setINPINTERVAL("Interval");
                        mgetTreatmentForPatient.setDOSENAME("Dosage");
                        mgetTreatmentForPatient.setDOSGNAME("Route");
                        mgetTreatmentForPatient.setINPWANTEDAMOUNT("Total");
                        mgetTreatmentForPatient.setDOCFULLNAME("Doctor name");
                        treatmentArray.add(0, mgetTreatmentForPatient);
                        adapter.notifyDataSetChanged();
                    } else {

                        emptyresult.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
                mInterfacePatient.showLoading(false);
                Controller.view_error(volleyError, getContext());
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

    AddTreatmentPlanFragment addTreatmentPlanFragment;
    public void ShowAddTreatmentPlan() {
        ((ActivityPatient)getActivity()).CallFragment(new AddTreatmentPlanFragment());
    }

    ContenueTreatmentFragment mcontenueTreatmentFragment;
    public void ShowContinueTreatmentPlan(GetTreatmentForPatient mGetTreatmentForPatient) {
        mcontenueTreatmentFragment = new ContenueTreatmentFragment(mGetTreatmentForPatient,((ActivityPatient)getActivity()).getmCardviewDataModel(),this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mcontenueTreatmentFragment.show(ft, "ContinueTreatmentPlan_tag");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle("الخطة العلاجية للمريض");

    }

    @Override
    public void closed() {
        addTreatmentPlanFragment.dismiss();
        PrepareGetTreatmentForPatient();
    }

    @Override
    public void hide(GetTreatmentForPatient mGetTreatmentForPatient, String action) {
        mcontenueTreatmentFragment.dismiss();
        switch (action)
        {
            // Delete
            case "-1":
                treatmentArray.remove(mGetTreatmentForPatient);
                break;
            case "1":
                treatmentArray.add(mGetTreatmentForPatient);
        }
        adapter.notifyDataSetChanged();
    }
}
