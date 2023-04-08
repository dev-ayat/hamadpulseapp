package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.DepatmentsAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.Departments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DepartmentsFragment extends DialogFragment {
    public static Button btndeptlist;
    public RadioGroup deptradioGroup;
    public RadioButton deptradio;
    public TextView deptcd;
    ArrayList<HashMap<String, String>> USER_Depts = new ArrayList<HashMap<String, String>>();
    private List<Departments> deptsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DepatmentsAdapter mAdapter;
    private TextView txtResponse;
    private String jsonResponse;

    //private String _myTag="Dept_dialog_tag";
    public DepartmentsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dept, container, false);
        recyclerView = view.findViewById(R.id.dept_recycler_view);
        btndeptlist = view.findViewById(R.id.btndeptlist);
        deptcd = view.findViewById(R.id.txtdeptcd);
        deptradio = view.findViewById(R.id.deptnameradio);
        mAdapter = new DepatmentsAdapter(deptsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        //  this.getDialog().setTitle("الأقسام");
        getActivity().invalidateOptionsMenu();
        Log.e("test","Dept");
        preparedeptData();
        btndeptlistListener();


        return view;

    }

    private void preparedeptData() {
        Log.e("test","preparedeptData");
        String userid = Controller.pref.getString("USER_ID", "");
        Map<String, String> map = new HashMap<>();
        map.put("USER_ID", userid);
//        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        Log.e("test", map.toString());
        Log.e("test", "url" + Controller.USER_DEPT_URL);
        Log.e("HOS_NO", "");
        String url = "";
        if (Controller.pref.getString(USER_TYPE, "").equals("1") ){
            url = Controller.USER_DEPT_URL;
        } else if(Controller.pref.getString(USER_TYPE, "").equals("3")){
            url = Controller.USER_ALL_MED_DEPT_URL;
        }

                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("json:", jsonObject.toString());

                try {
                    JSONArray userdept_arr = jsonObject.getJSONArray("DEPARTMENTS");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + userdept_arr.length());
                    if (userdept_arr.length() > 0) {
                        Log.e("json", "" + userdept_arr.getJSONObject(0).getString("DEPT_CODE"));

                        Departments none = new Departments("عرض جميع مرضى الأقسام", "");
                        deptsList.add(none);
                        for (int i = 0; i < userdept_arr.length(); i++) {
                            JSONObject obj = userdept_arr.getJSONObject(i);

                            if (obj != null) {
                                if (obj.getString("DEPT_CODE") != null) {

                                    Departments dept = new Departments(obj.getString("DEPT_NAME_AR"), obj.getString("DEPT_CODE"));
                                    deptsList.add(dept);
                                }
                            }
                        }
                    }
                    Log.e("json", "" + deptsList.size());
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }

            }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        Controller.view_error(volleyError, getContext());
                        Log.e("json", "ErrorListener");
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

    public void btndeptlistListener() {
        btndeptlist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("testdeptsha", Controller.pref.getString("DEPT_CD_SELEC", ""));
                replaceYourDialogFragment();
            }

        });

    }

    private void replaceYourDialogFragment() {
        getDialog().dismiss();
        PatientFragment PatientFragment = new PatientFragment(537);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, PatientFragment, "pateint_tag")
                .addToBackStack("Dept_dialog_tag").commit();

    }

}


