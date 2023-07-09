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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.DoctorOrdersAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.GetDoctorsOrders;

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
public class DoctorOrdersFragment extends Fragment implements DoctorOrdersAdapter.NoteInterface {

    FloatingActionButton add_note;
    TextView txtpatid, txtpatName, txtadmdate, txtdaycount;
    String patid, patadmcd, user_id;
    ArrayList<GetDoctorsOrders> patientNoteArray;
    DoctorOrdersAdapter doctorOrdersAdapter;
    RecyclerView note_recycler_view;
    LinearLayout statusresult, emptyresult, statuslblresult;
    String fragment_cd = "50";
    InterfacePatient mInterfacePatient;

    public DoctorOrdersFragment() {
        // Required empty public constructor
        Log.e("test", "DoctorOrdersFragment");
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

        View view = inflater.inflate(R.layout.fragment_doctor_orders, container, false);
        add_note = view.findViewById(R.id.btn_add_note);
        statusresult = view.findViewById(R.id.Statusresult_linearLayout);
        emptyresult = view.findViewById(R.id.emptyresult_linearLayout);
        note_recycler_view = view.findViewById(R.id.note_recycler_view);
        patientNoteArray = new ArrayList<>();
        note_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        if (Controller.pref.getString(USER_TYPE, "").equals("2") || Controller.pref.getString(USER_TYPE, "").equals("4"))
            add_note.setVisibility(View.GONE);
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ShowAddNotes();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patid = ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "";
        patadmcd = ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "";
        user_id = Controller.pref.getString("USER_ID", "");
        PrepareGetpatientorders();
    }


    public void PrepareGetpatientorders() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", patadmcd);
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "عرض أوامر الطبيب");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //  mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_DOC_ORDERS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", response.toString());
                try {
                    Log.e("response2", response.toString());
                    JSONArray jsonArray = response.getJSONArray("ORDERS_CUR");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());
                    if (jsonArray.length() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GetDoctorsOrders>>() {
                        }.getType();
                        patientNoteArray = gson.fromJson(jsonArray.toString(), type);
                        Log.e("list", patientNoteArray.toString());
                        Log.e("size", patientNoteArray.size() + "");
                        InitAadapter();
                    } else {
                        statusresult.setVisibility(View.GONE);
                        emptyresult.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
                //  mInterfacePatient.showLoading(false);
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

    public void InitAadapter() {
        doctorOrdersAdapter = new DoctorOrdersAdapter(getContext(), patientNoteArray, this);
        note_recycler_view.setAdapter(doctorOrdersAdapter);
        doctorOrdersAdapter.notifyDataSetChanged();
    }

    public void ShowAddNotes() {
        ((ActivityPatient) getActivity()).CallFragment(new AddDoctorOrdersFragment());
    }

    @Override
    public void onNoteClick(GetDoctorsOrders mGetDoctorsOrders) {
        //ShowDeleteMsg(serial);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Controller.LOADER_DIALOG.showDialog("جاري الحذف");
                        sendDeletOrder(mGetDoctorsOrders);

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


    private void sendDeletOrder(GetDoctorsOrders mGetDoctorsOrders) {
        Map<String, String> map = new HashMap<>();
        map.put("P_ORDER_CD", mGetDoctorsOrders.getDocOrderCode());
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "3");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "حذف اوامر الطبيب");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //  mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DELETE_DOCTOR_ORDER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        patientNoteArray.remove(mGetDoctorsOrders);
                        doctorOrdersAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();

                    }
                    //  mInterfacePatient.showLoading(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                //  mInterfacePatient.showLoading(false);
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
        ((ActivityPatient) getActivity()).setTitle("أوامر الطبيب");
    }


}
