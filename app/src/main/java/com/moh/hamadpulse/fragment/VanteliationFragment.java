package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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
import com.moh.hamadpulse.models.GetVentilationForPatient;

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
public class VanteliationFragment extends Fragment {
    FloatingActionButton add_Vent;
    String user_id;
    ArrayList<GetVentilationForPatient> ventilationArray;
    LinearLayout ventresult, emptyresult, ventlblresult;
    MatrixTableAdapter2<GetVentilationForPatient> adapter;
    TableFixHeaders tableFixHeaders;

    public String fragment_cd = "14";

    public VanteliationFragment() {
        // Required empty public constructor
    }

    public VanteliationFragment(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient = mInterfacePatient;
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

        View view = inflater.inflate(R.layout.fragment_vanteliation, container, false);
        add_Vent = view.findViewById(R.id.btn_add_Vent);
        emptyresult = view.findViewById(R.id.emptyresult_linearLayout);
        ventilationArray = new ArrayList<>();
        user_id = Controller.pref.getString("USER_ID", "");
        PrepareGetVentilationForPatient();
        add_Vent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.pref.getString(USER_TYPE, "").equals("2") || Controller.pref.getString(USER_TYPE, "").equals("4"))
                    Toast.makeText(getContext(), "الإضافة من ضمن صلاحيات الدكتور ", Toast.LENGTH_SHORT).show();
                else
                    ShowAddVentilationForPatient();
            }
        });
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.table);
        return view;
    }


    public void PrepareGetVentilationForPatient() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "التنفس الصناعي");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_Ventilation_Patient_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("ADM_VENT_TYPE");
                    if (jsonArray.length() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GetVentilationForPatient>>() {
                        }.getType();
                        ventilationArray = gson.fromJson(jsonArray.toString(), type);
                        adapter = new MatrixTableAdapter2<>(getContext());
                        adapter.setData(ventilationArray);
                        tableFixHeaders.setRowSelectable(true);
                        tableFixHeaders.setAdapter(adapter);

                        tableFixHeaders.setOnItemClickListener(new TableFixHeaders.OnItemClickListener() {
                            @Override
                            public void onItemClick(TableFixHeaders parent, View view, int row, int column, long id) {
                                //   addtreatmentArray.add(ventilationArray.get(row));
                                //String serial = ventilationArray.get(row).getvSERIAL();
                                ShowDeleteMsg(ventilationArray.get(row));
                            }
                        });

                        tableFixHeaders.setOnItemLongClickListener(new TableFixHeaders.OnItemLongClickListener() {
                            @Override
                            public void onItemLongClick(TableFixHeaders parent, View view, int row, int column, long id) {


                            }
                        });


                        GetVentilationForPatient mGetVentilationForPatient = new GetVentilationForPatient();
                        mGetVentilationForPatient.setVENADMDATE("Date");
                        mGetVentilationForPatient.setVENADMFIO2("FiO2%");
                        mGetVentilationForPatient.setVENADMIE("I:E");
                        mGetVentilationForPatient.setVENADMPEEP("PEEP/CmH2O");
                        mGetVentilationForPatient.setVENADMRR("RR");
                        mGetVentilationForPatient.setVENADMVTCC("Vt/CC");
                        mGetVentilationForPatient.setVENTYPENAME("Mode Vent");
                        mGetVentilationForPatient.setvENADMOxgen("O2 Flow");
                        ventilationArray.add(0, mGetVentilationForPatient);
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

    public void ShowAddVentilationForPatient() {

        ((ActivityPatient)getActivity()).CallFragment(new AddVentelationFragment());

    }

    public void ShowDeleteMsg(GetVentilationForPatient mGetVentilationForPatient) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendDeletOrder(mGetVentilationForPatient);
                    }


                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void sendDeletOrder(GetVentilationForPatient mGetVentilationForPatient) {

        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("USER_ID", user_id);
        map.put("SERIAL", mGetVentilationForPatient.getvSERIAL());
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("map", map.toString());
        Log.e("ayat", "map");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.Delete_Ventilation_Patient_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        ventilationArray.remove(mGetVentilationForPatient);
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mInterfacePatient.showLoading(false);
                Controller.view_error(error, getContext());
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
    @Override
    public void onResume() {
        super.onResume();
        ((ActivityPatient) getActivity()).setTitle(" التنفس الصناعي");
    }
}
