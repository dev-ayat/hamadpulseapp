package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.ExaminationoncologyAdapter;
import com.moh.hamadpulse.models.GetPhysicalOncology;

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
public class ExaminationFragment extends Fragment {

    ExaminationoncologyAdapter icd10ConstAdapter;
    ArrayList<GetPhysicalOncology> getIcdConstArrayList;
    RecyclerView notificationrecyclerview;
    InterfacePatient mInterfacePatient;


    public ExaminationFragment(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient=mInterfacePatient;
    }        // Required empty public constructor

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
        View view = inflater.inflate(R.layout.fragment_examination, container, false);
        notificationrecyclerview = view.findViewById(R.id.notification_recycler_view);
        icd10ConstAdapter = new ExaminationoncologyAdapter(new ArrayList<>(),getContext());
        //getIcdConstArrayList = new ArrayList<>();
        notificationrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationrecyclerview.setHasFixedSize(true);
        notificationrecyclerview.setAdapter(icd10ConstAdapter);
       // PreparegetIcd();

        return view;
    }

    public void PreparegetIcd() {
        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATREIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
       // map.put("PATREIC_CD","420087");


        MyRequest.makeRquest(getContext(), Controller.GET_EXAMINATION_ONCOLOGY_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {

                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ONCOLOGY");


                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetPhysicalOncology>>() {
                    }.getType();

                    getIcdConstArrayList = gson.fromJson(jsonArray.toString(), type);
                    Log.e("ayat",getIcdConstArrayList.size()+"");
                    Log.e("ayat","ayyyat"+jsonArray.toString());
                    Log.e("ayat","ayyyat"+getIcdConstArrayList.get(0).getBasisDiagId());
                    icd10ConstAdapter.setProtocolList(getIcdConstArrayList);
                    icd10ConstAdapter.notifyDataSetChanged();

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

//        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_ICDS_URL, map, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("response", "ayat resp" + response.toString());
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(final VolleyError volleyError) {
//                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
//                Toast.makeText(getContext(), "حدث خلل في الاتصال  ", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("البروتوكول العلاجي");
    }

}
