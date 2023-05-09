package com.moh.hamadpulse.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView txth_username, txth_ID, texth_email, txtdept;
    ImageView personalimg;
    String userid, doctor_spc;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txth_username = view.findViewById(R.id.txth_username);
        txth_ID = view.findViewById(R.id.txth_ID);
        txtdept = view.findViewById(R.id.txtdept);
        personalimg = view.findViewById(R.id.personalimg);
        texth_email = view.findViewById(R.id.texth_email);
        txth_username.setText(Controller.pref.getString("USER_NAME", ""));
        txth_ID.setText(Controller.pref.getString("USER_ID", ""));
        txtdept.setText(Controller.pref.getString("DEPT_NAME_AR", ""));
        texth_email.setText(Controller.pref.getString("USER_EMAIL", ""));
        userid = Controller.pref.getString("USER_ID", "");
        GetDoctorSpecalist();
        Log.e("img_url", Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""));
        Picasso.with(getContext())
                .load(Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""))
                .into(personalimg);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_dept);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//
//    }

    private void GetDoctorSpecalist() {
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID", userid);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ayat", "map");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_GET_DOCTOR_SPECALIST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("USER_SPC");
                    Log.e("doc", "doc" + res);
                    doctor_spc = Integer.toString(res);
                    Controller.editor.putString("Doctor_spc", doctor_spc);
                    Controller.editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(getContext())
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(getContext(),LoginActivity.class);
//                        startActivity(intent);
//                        LoginActivity.super.onBackPressed();
//
//                    }
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        LoginActivity.super.onBackPressed();
//                    }
//                }).create().show();
//    }

//    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//
//      if (currentFragment instanceof ClothOptionFragment) {
//
//    }

}
