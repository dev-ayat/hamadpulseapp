package com.moh.hamadpulse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.EfileAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.EfileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Efile_Fragment extends Fragment {
    private static RecyclerView.Adapter efileAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<EfileModel> Carddata;
    TextView txtscnane, txtpatid, txtpatName;
    String patname, patid, indate, patmrpid;
    ImageView card_arrow;
    Context con;
    String fragment_cd = "16";
    private RecyclerView.LayoutManager layoutManager;

    public Efile_Fragment() {

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
        View view = inflater.inflate(R.layout.fragment_efile, container, false);
        recyclerView = view.findViewById(R.id.efile_recycler_view);
        Carddata = new ArrayList<>();
        efileAdapter = new EfileAdapter(Carddata, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(efileAdapter);
        preparedeptData();
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtscnane = view.findViewById(R.id.txtscnane);
        recyclerView = view.findViewById(R.id.efile_recycler_view);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "What", Toast.LENGTH_SHORT).show();
                ((ActivityPatient)getActivity()).CallFragment(new Webview_Fragment());
            }
        });


    }

    private void preparedeptData() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("MRP_ID", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPtmrpid());//"351989"
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "الأرشيف الطبي");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.Efile_VISIT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONArray documents_arr = jsonObject.getJSONArray("VISITS");
                    if (documents_arr.length() > 0) {
                        for (int i = 0; i < documents_arr.length(); i++) {
                            JSONObject obj = documents_arr.getJSONObject(i);
                            if (obj != null) {
                                if (obj.getString("VISTE_DATE") != null) {

                                    EfileModel Card = new EfileModel(obj.getString("VISTE_DATE"),
                                            obj.getString("HOSSID"),
                                            obj.getString("IDNUMBER"));
                                    Carddata.add(Card);
                                }
                            }
                        }
                    } else {
                        LinearLayout emptyEfile = getView().findViewById(R.id.emptyEfile_layout);
                        emptyEfile.setVisibility(View.VISIBLE);
                    }
                    recyclerView.setAdapter(efileAdapter);
                    efileAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("الملف الإلكتروني");
    }


}
