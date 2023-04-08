package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.Controller.GET_RAD_ORDER_URL;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.RadOrderAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetRadOrderForAdm;

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
public class RadiologyFragment extends Fragment implements RadOrderAdapter.RadInterface {

    FloatingActionButton add_rad;

    String patname, patid, admdate, patadmcd, user_id,LOC_CD;
    ArrayList<GetRadOrderForAdm> radorderArray;
    RadOrderAdapter RADAdapter;
    RecyclerView rad_recycler_view;
    LinearLayout statusresult, emptyresult, statuslblresult;
    public String fragment_cd = "6";


    public RadiologyFragment() {
        Log.e("test", "RadiologyFragment");
        // Required empty public constructor
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

        View view = inflater.inflate(R.layout.fragment_radiology, container, false);
        add_rad = view.findViewById(R.id.btn_add_rad);

        statusresult = view.findViewById(R.id.Statusresult_linearLayout);
        emptyresult = view.findViewById(R.id.emptyresult_linearLayout);
        rad_recycler_view = view.findViewById(R.id.rad_recycler_view);
        radorderArray = new ArrayList<>();
        rad_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        user_id = Controller.pref.getString("USER_ID", "");
        Prepareradorders();
        add_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddNotes();
            }
        });
        if(!Controller.pref.getString(USER_TYPE, "").equals("1"))
            add_rad.setVisibility(View.GONE);
        return view;
    }


    public void Prepareradorders() {
        Map<String, String> map = new HashMap<>();
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "أشعة");
        mInterfacePatient.showLoading(true);
        MyRequest.makeRquest(getContext(), GET_RAD_ORDER_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {

                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("rad_orders");
                    if (jsonArray.length() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GetRadOrderForAdm>>() {
                        }.getType();
                        radorderArray = gson.fromJson(jsonArray.toString(), type);
                        InitAadapter();
                    } else {
                        statusresult.setVisibility(View.GONE);
                        emptyresult.setVisibility(View.VISIBLE);
                    }

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

    public void InitAadapter() {
        RADAdapter = new RadOrderAdapter(getContext(), radorderArray, this);
        rad_recycler_view.setAdapter(RADAdapter);
        RADAdapter.notifyDataSetChanged();
    }

    public void ShowAddNotes() {

        ((ActivityPatient)getActivity()).CallFragment(new AddRadiologyOrderFragment());

    }




    private void sendDeletOrder(GetRadOrderForAdm mGetRadOrderForAdm) {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", mGetRadOrderForAdm.getORDERCODE());
        map.put("dorderid", mGetRadOrderForAdm.getoRDERDCODE());
        map.put("HOS_NO", "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("test", mGetRadOrderForAdm.getSERVICENAMEAR());
        mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DEL_RAD_ORDER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int res = response.getInt("Result");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        radorderArray.remove(mGetRadOrderForAdm);
                        RADAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getContext(), "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();
                    }

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
        ((ActivityPatient) getActivity()).setTitle("الأشعة");
        Log.e("Ameen","onResume");
    }


    @Override
    public void onradClick(GetRadOrderForAdm mGetRadOrderForAdm) {

//        if(mGetRadOrderForAdm.getDIFF_MIN()<5 && Controller.pref.getString(EMP_SERIAL, "").equals(mGetRadOrderForAdm.getORDERCREATEDBY())) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendDeletOrder(mGetRadOrderForAdm);
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
//        }
    }
}
