package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.ProtocoloncologyAdapter;
import com.moh.hamadpulse.models.GetProtocolOncology;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProtocolFragment extends Fragment {
    RecyclerView protocol_recycler_view;
    ProtocoloncologyAdapter protocolAdapter;
    ArrayList<GetProtocolOncology> getIcdConstArrayList;
    String fragment_cd = "22";
    FloatingActionButton btn_add_protocol;
    private Context context;


    public ProtocolFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_protocol, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        protocol_recycler_view = view.findViewById(R.id.protocol_recycler_view);
        btn_add_protocol = view.findViewById(R.id.btn_add_protocol);
        getIcdConstArrayList = new ArrayList<>();
        protocolAdapter = new ProtocoloncologyAdapter(getIcdConstArrayList, getContext());
        protocol_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        protocol_recycler_view.setHasFixedSize(true);
        protocol_recycler_view.setAdapter(protocolAdapter);
        if (!(Controller.pref.getString(USER_TYPE, "").equals("1") ||
                Controller.pref.getString(USER_TYPE, "").equals("3")))
            btn_add_protocol.setVisibility(View.GONE);

            btn_add_protocol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String admission_cd = ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "";
//                    String patrec_code = ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "";
//                    String loc_cd = ((ActivityPatient) getActivity()).getmCardviewDataModel().getLOC_CODE() + "";
//                    byte[] adm_cd = new byte[0];
//                    byte[] patric_cd = new byte[0];
//                    byte[] location_cd = new byte[0];
//
//                    try {
//                        adm_cd = admission_cd.getBytes("UTF-8");
//                        patric_cd = patrec_code.getBytes("UTF-8");
//                        location_cd = loc_cd.getBytes("UTF-8");
//                        String adm_base64 = Base64.encodeToString(adm_cd, Base64.DEFAULT);
//                        String patric_base64 = Base64.encodeToString(patric_cd, Base64.DEFAULT);
//                        String loc_base64 = Base64.encodeToString(location_cd, Base64.DEFAULT);
//
//                        String myurl = "http://pulse.moh.gov.ps/newehos/index.php/doctor_admission/Doctor_admission_dept_cont_pulse/" +
//                                "admission_protocol_patient_cancer?admission_cd=" + adm_base64 + "&patrec_code=" + patric_base64 + "&loc_cd=" + loc_base64;
//                        Log.e("myurl", myurl);
//
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myurl));
//                        getContext().startActivity(browserIntent);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }

                        AddNewProtocol f = new AddNewProtocol();
                        ((ActivityPatient) getActivity()).CallFragment(f);

                }
            });

        Map<String, String> map = new HashMap<>();
        map.put("PATREIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        //  map.put("PATREIC_CD","229017");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "عرض البروتوكول");
        MyRequest.makeRquest(getContext(), Controller.GET_PROTOCOL_ONCOLOGY_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("ONCOLOGY");
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetProtocolOncology>>() {
                    }.getType();
                    getIcdConstArrayList = gson.fromJson(jsonArray.toString(), type);

                    protocolAdapter.setProtocolList(getIcdConstArrayList);
                    protocolAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });

    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("البروتوكول العلاجي");
    }

}
