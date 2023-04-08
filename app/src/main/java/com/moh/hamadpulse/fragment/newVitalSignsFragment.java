package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.NEW_GET_VITAL_SIGN_URL;
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
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.GetVitalForAdm;

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
public class newVitalSignsFragment extends Fragment {
    FloatingActionButton add_vital;
    String patname, patid, admdate, patadmcd, user_id;
    ArrayList<GetVitalForAdm> VitalArray;
    LinearLayout ventresult, emptyresult, ventlblresult;
    MatrixTableAdapter2<GetVitalForAdm> adapter;
    TableFixHeaders tableFixHeaders;
    public String fragment_cd = "10";
    String adm_cd;
    public newVitalSignsFragment() {
        // Required empty public constructor
    }

    public newVitalSignsFragment(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient=mInterfacePatient;
    }
    public newVitalSignsFragment(String adm_cd,InterfacePatient mInterfacePatient) {
        this.adm_cd=adm_cd;
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

        View view = inflater.inflate(R.layout.fragment_newvital, container, false);
        add_vital = view.findViewById(R.id.btn_add_vital);
        emptyresult = view.findViewById(R.id.emptyresult_linearLayout);
        VitalArray = new ArrayList<>();
        user_id = Controller.pref.getString("USER_ID", "");
        PrepareGetVitalForPatient();
        Controller.Msg_DIALOG = new DialogMsg(getContext());
        add_vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddVitalSigns();
            }
        });
        tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.table);
//      hide add btn if we have accessed the screen from admission history or the user is pharmacist
        add_vital.setVisibility(adm_cd!=null?View.GONE:
                Controller.pref.getString(USER_TYPE, "").equals("5")?View.GONE:View.VISIBLE);
        return view;
    }


    public void PrepareGetVitalForPatient() {
        if (mInterfacePatient!=null)
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("PATRIC_CD", "429613");
        map.put("ADM_CD", adm_cd==null?((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "":adm_cd);
//        map.put("ADM_CD", "9544");
        map.put("V_ADM_DATE_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getIndate() + "");
//        map.put("V_ADM_DATE_IN", "12/13/2022 11:08:47");
        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "العلامات الحيوية");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        Log.e("ERROR", "map=" + map.toString());
        Log.e("ERROR", "url=" + NEW_GET_VITAL_SIGN_URL);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, NEW_GET_VITAL_SIGN_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("ERROR", "response=" + response);
                try {
                    JSONArray jsonArray = response.getJSONArray("ADM_SIGNS");
                    if (jsonArray.length() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GetVitalForAdm>>() {
                        }.getType();
                        VitalArray = gson.fromJson(jsonArray.toString(), type);
                        adapter = new MatrixTableAdapter2<>(getContext());
                        adapter.setData(VitalArray);
                        tableFixHeaders.setRowSelectable(true);
                        tableFixHeaders.setAdapter(adapter);
                        tableFixHeaders.setOnItemClickListener(new TableFixHeaders.OnItemClickListener() {
                            @Override
                            public void onItemClick(TableFixHeaders parent, View view, int row, int column, long id) {
                                Log.e("ERROR","row="+row+" column="+column);
                                if(column==1) {
                                    String v_s[]=VitalArray.get(row).getVADMDATE().split(" ");
                                    //  Toast.makeText(getContext(), VitalArray.get(row).getVITAL_NOTE() + "", Toast.LENGTH_LONG).show();
                                    Controller.Msg_DIALOG.showDialog(VitalArray.get(row).getVITAL_NOTE() + ""
                                            ,v_s[0]+"\n"+v_s[1]);

                                } else if(column==0) {
                                    if(VitalArray.get(row).getDIFF_MIN()<5)
                                    ShowDeleteMsg(VitalArray.get(row));
                                }
                            }
                        });


                        GetVitalForAdm mgetVitalForAdm = new GetVitalForAdm();
                        mgetVitalForAdm.setVADMDATE("Date");
                        mgetVitalForAdm.setVITAL_NOTE("Note");
                        mgetVitalForAdm.setTEMPC("TEMP-C");
                        mgetVitalForAdm.setSAO2("SaO2%");
                        mgetVitalForAdm.setRRMIN("RR/min");
                        mgetVitalForAdm.setEtCO2("EtCO2 mmHg");
                        mgetVitalForAdm.setHeartRate("Heart Rate");
                        mgetVitalForAdm.setNIBP("NIBP_H");
                        mgetVitalForAdm.setNIBP_LOWER("NIBP_L");
                        mgetVitalForAdm.setUrineOut("Urine Out ml/hr");
                        mgetVitalForAdm.setCVP("CVP cmH2O");
                        mgetVitalForAdm.setIBP("IBP mmHg");
                        mgetVitalForAdm.setWEIGHT("WEIGHT");
                        mgetVitalForAdm.setHEIGHT("HEIGHT");
                        mgetVitalForAdm.setBMI("BMI");
                        mgetVitalForAdm.setHC("HC");
                        mgetVitalForAdm.setDOC_NAME("Created By");
                        VitalArray.add(0, mgetVitalForAdm);
                        adapter.notifyDataSetChanged();
                    } else {
                        emptyresult.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (mInterfacePatient!=null)
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (mInterfacePatient!=null)
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


    public void ShowAddVitalSigns() {
        ((ActivityPatient)getActivity()).CallFragment(new newAddVitalSignsFragment());
    }

    public void ShowDeleteMsg(GetVitalForAdm mGetVitalForAdm) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Controller.LOADER_DIALOG.showDialog("جاري الحذف");
                        sendDeletOrder(mGetVitalForAdm);

                    }

                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void sendDeletOrder(GetVitalForAdm mGetVitalForAdm) {

        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("USER_ID", user_id);
        map.put("SERIAL", mGetVitalForAdm.getVADMCODE());
        map.put("HOS_NO", "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "5");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "العلامات الحيوية");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        Log.e("map", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.NEW_DELETE_VITAL_SIGN_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        VitalArray.remove(mGetVitalForAdm);
                        adapter.notifyDataSetChanged();
                        if(VitalArray.size()==0)
                            emptyresult.setVisibility(View.VISIBLE);
                        //PrepareGetVitalForPatient();

                    } else
                        Toast.makeText(getContext(), "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
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
        ((ActivityPatient) getActivity()).setTitle("العلامات الحيوية");
    }
}
